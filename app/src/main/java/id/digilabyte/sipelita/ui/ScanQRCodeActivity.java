package id.digilabyte.sipelita.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.helper.UserPreferences;
import id.digilabyte.sipelita.model.request.AbsensiRequest;
import id.digilabyte.sipelita.model.response.AbsensiResponse;
import id.digilabyte.sipelita.model.response.DetailPelatihan;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanQRCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private String UUID;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);

        if (getIntent().hasExtra("UUID")) {
            UUID = getIntent().getStringExtra("UUID");
        } else {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        FrameLayout contentFrame = (FrameLayout) findViewById(R.id.container_frame);
        mScannerView = new ZXingScannerView(this);
        mScannerView.setAutoFocus(true);
        mScannerView.setResultHandler(this);
        contentFrame.addView(mScannerView);
    }

    @Override
    protected void onStart() {
        mScannerView.startCamera();
        super.onStart();
    }

    @Override
    protected void onResume() {
        mScannerView.startCamera();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mScannerView.stopCamera();
        super.onPause();
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(getApplicationContext(), "QRCode Accept : " + rawResult.getText(), Toast.LENGTH_SHORT).show();

        if (rawResult.getText().equals(UUID)) {
            AbsensiRequest absensiRequest = new AbsensiRequest(UUID);

            BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
            Call<AbsensiResponse> absensiResponseCall = api.absensiResponseCall(UserPreferences.getKeyUserType(this)+" "+UserPreferences.getKeyUserToken(this), absensiRequest);

            absensiResponseCall.enqueue(new Callback<AbsensiResponse>() {
                @Override
                public void onResponse(Call<AbsensiResponse> call, Response<AbsensiResponse> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        Toast.makeText(getApplicationContext(), response.body().getMessage().get(0), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                        intent.putExtra("PELATIHAN", UUID);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<AbsensiResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("PELATIHAN", UUID);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("PELATIHAN", UUID);
            startActivity(intent);
            finish();
        }

//        Log.d("_HANDLE_RESULT_", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mScannerView.resumeCameraPreview(ScanQRCodeActivity.this);
//            }
//        }, 2000);
    }
}
