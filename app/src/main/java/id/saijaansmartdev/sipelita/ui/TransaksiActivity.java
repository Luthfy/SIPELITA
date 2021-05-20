package id.saijaansmartdev.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.adapter.TransaksiAdapter;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.helper.UserPreferences;
import id.saijaansmartdev.sipelita.model.Transaksi;
import id.saijaansmartdev.sipelita.model.response.TransaksiResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity {

    private RecyclerView rcTransaksiList;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Transaksi.TransaksiList> transaksiArrayList;

    private String Auth = "";

    private ProgressDialog mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        showLoading();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        Auth = UserPreferences.getKeyUserToken(this);

        if (Auth.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Anda Harus Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TransaksiActivity.this, LoginActivity.class));
            finish();
        }

        rcTransaksiList = (RecyclerView) findViewById(R.id.rc_transaksi);
        rcTransaksiList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(TransaksiActivity.this);
        rcTransaksiList.setLayoutManager(layoutManager);

        getInvoiced();
    }

    private void showLoading() {
        mLoading = new ProgressDialog(TransaksiActivity.this);
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    private void getInvoiced() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<TransaksiResponse> transaksiResponseCall = api.transaksiResponseCall(UserPreferences.getKeyUserType(this)+" "+Auth);

        transaksiResponseCall.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                if (response.body() != null) {
                    transaksiArrayList = response.body().getData().getData();
                    setRecyclerList(transaksiArrayList);
                    mLoading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                mLoading.dismiss();
            }
        });
    }

    private void setRecyclerList(ArrayList<Transaksi.TransaksiList> data) {
        TransaksiAdapter adapter = new TransaksiAdapter(data);
        rcTransaksiList.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TransaksiActivity.this, HomeActivity.class));
        finish();
    }
}
