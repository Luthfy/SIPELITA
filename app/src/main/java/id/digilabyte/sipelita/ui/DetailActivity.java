package id.digilabyte.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.adapter.SebaranAdapter;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.helper.UserPreferences;
import id.digilabyte.sipelita.model.Pelatihan;
import id.digilabyte.sipelita.model.Sebaran;
import id.digilabyte.sipelita.model.response.DetailPelatihan;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgDetail;
    private LinearLayout llSebaranDetail, llButtonTest;
    private RecyclerView rcDetailSebaran;
    private TextView txtTitle, txtDesc, txtInCharge, txtPrice, txtQty;
    private TextView txtStartDate, txtPretest, txtPostTest;
    private ImageView  btnEvaluation, btnPreTest, btnPostTest, btnScanQR;
    private Button txtButton;
    private ProgressDialog mLoading;

    private RecyclerView.LayoutManager layoutManager;

    private String UUID;
    private String Auth;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        Auth = UserPreferences.getKeyUserToken(this);

        if (Auth.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Anda Harus Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DetailActivity.this, LoginActivity.class));
            finish();
        }

        initComp();

        if (getIntent().getStringExtra("PELATIHAN") == null) {
            Toast.makeText(getApplicationContext(), "Maaf UUID tidak Valid", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DetailActivity.this, HomeActivity.class));
            finish();
        } else {
            showLoading();
            UUID = getIntent().getStringExtra("PELATIHAN");
            requestDetail(UUID);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestDetail(UUID);
    }

    private void requestDetail(String id) {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<DetailPelatihan> detailPelatihanResponseCall = api.detailPelatihanResponseCall(UserPreferences.getKeyUserType(this)+" "+Auth, id);

        detailPelatihanResponseCall.enqueue(new Callback<DetailPelatihan>() {
            @Override
            public void onResponse(Call<DetailPelatihan> call, Response<DetailPelatihan> response) {
                mLoading.dismiss();
                setDataDetail(response.body().getData());
            }

            @Override
            public void onFailure(Call<DetailPelatihan> call, Throwable t) {
                mLoading.dismiss();
            }
        });
    }

    private void showLoading() {
        mLoading = new ProgressDialog(DetailActivity.this);
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    @SuppressLint("SetTextI18n")
    private void setDataDetail(Pelatihan pelatihan) {
        assert pelatihan != null;
        txtTitle.setText(pelatihan.getTitle());
        txtDesc.setText("Deskripsi : \n"+ pelatihan.getDesc());
        txtInCharge.setText("Penanggung Jawab : "+ pelatihan.getIncharge());
        txtPrice.setText("Rp. "+ pelatihan.getPrice());
        txtQty.setText("Kuota : "+ pelatihan.getQuota()+"/"+ pelatihan.getAccept_participant());
        txtStartDate.setText("Tanggal Pelatihan : "+ pelatihan.getStart_time() +" - "+ pelatihan.getEnd_time());
        txtPretest.setText("Pretest : " + (pelatihan.getNilai_pretest() == null ? "" : pelatihan.getNilai_pretest()));
        txtPostTest.setText("Post Test : " + (pelatihan.getNilai_posttest() == null ? "" : pelatihan.getNilai_posttest()));

        Glide.with(this).load(pelatihan.getPoster()).into(imgDetail);

        ArrayList<Sebaran> sebaranArrayList = pelatihan.getParticipants();

        Log.d("_sebaran", String.valueOf(sebaranArrayList.size()));

        SebaranAdapter adapter = new SebaranAdapter(DetailActivity.this, sebaranArrayList);
        rcDetailSebaran.setAdapter(adapter);

        if (pelatihan.isIs_registered()) {
            txtPretest.setVisibility(View.VISIBLE);
            txtPostTest.setVisibility(View.VISIBLE);
            llButtonTest.setVisibility(View.VISIBLE);
            txtButton.setVisibility(View.GONE);
        } else {
            txtPretest.setVisibility(View.GONE);
            txtPostTest.setVisibility(View.GONE);
            llButtonTest.setVisibility(View.GONE);
            txtButton.setText("Registrasi Pelatihan");
        }

        txtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, FormPelatihanActivity.class);
                intent.putExtra("UUID", pelatihan.getId());
                startActivity(intent);
            }
        });

        btnEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, TestActivity.class);
                intent.putExtra("TYPE_TEST", "evaluation");
                intent.putExtra("UUID", UUID);
                startActivity(intent);
            }
        });

        btnPreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, TestActivity.class);
                intent.putExtra("TYPE_TEST", "pretest");
                intent.putExtra("UUID", UUID);
                startActivity(intent);
            }
        });

        btnPostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, TestActivity.class);
                intent.putExtra("TYPE_TEST", "posttest");
                intent.putExtra("UUID", UUID);
                startActivity(intent);
            }
        });

        btnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, ScanQRCodeActivity.class);
                intent.putExtra("UUID", UUID);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void initComp() {
        llSebaranDetail = (LinearLayout) findViewById(R.id.ll_sebaran_detail);
        llButtonTest    = (LinearLayout) findViewById(R.id.button_test_detail);
        imgDetail       = (ImageView) findViewById(R.id.img_detail);
        txtTitle        = (TextView) findViewById(R.id.txt_title_detail);
        txtDesc         = (TextView) findViewById(R.id.txt_deskripsi_detail);
        txtInCharge     = (TextView) findViewById(R.id.txt_penanggungjawab_detail);
        txtPrice        = (TextView) findViewById(R.id.txt_harga_detail);
        txtQty          = (TextView) findViewById(R.id.txt_qty_detail);
        txtPretest      = (TextView) findViewById(R.id.txt_pretest);
        txtPostTest     = (TextView) findViewById(R.id.txt_post_test);
        txtStartDate    = (TextView) findViewById(R.id.txt_tanggal_mulai_detail);
        txtButton       = (Button) findViewById(R.id.btn_register_detail);
        btnEvaluation   = (ImageView) findViewById(R.id.btn_evaluasi_detail);
        btnPreTest      = (ImageView) findViewById(R.id.btn_pretest_detail);
        btnPostTest     = (ImageView) findViewById(R.id.btn_post_test_detail);
        btnScanQR       = (ImageView) findViewById(R.id.btn_scan_qr_detail);

        rcDetailSebaran = (RecyclerView) findViewById(R.id.rc_sebaran);
        rcDetailSebaran.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(DetailActivity.this);
        rcDetailSebaran.setLayoutManager(layoutManager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            txtDesc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(DetailActivity.this, PelatihanActivity.class));
        finish();
        return true;
    }
}
