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

public class SebaranActivity extends AppCompatActivity {

    private ImageView imgDetail;
    private LinearLayout llSebaranDetail;
    private RecyclerView rcDetailSebaran;
    private TextView txtTitle, txtDesc, txtInCharge, txtPrice, txtQty;
    private TextView txtStartDate;
    private ProgressDialog mLoading;

    private RecyclerView.LayoutManager layoutManager;

    private String UUID;
    private String Auth;

    @SuppressLint("SetTextI18n")
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sebaran);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        Auth = UserPreferences.getKeyUserToken(this);

        if (Auth.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Anda Harus Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SebaranActivity.this, LoginActivity.class));
            finish();
        }

        initComp();

        if (getIntent().getStringExtra("PELATIHAN") == null) {
            Toast.makeText(getApplicationContext(), "Maaf UUID tidak Valid", Toast.LENGTH_SHORT).show();
            Log.d("SebaranActivity", "Null");
            startActivity(new Intent(SebaranActivity.this, HomeActivity.class));
            finish();
        } else {
            showLoading();
            UUID = getIntent().getStringExtra("PELATIHAN");
            requestDetail(UUID);
        }
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
        mLoading = new ProgressDialog(SebaranActivity.this);
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    private void setDataDetail(Pelatihan pelatihan) {
        assert pelatihan != null;
        txtTitle.setText(pelatihan.getTitle());
        txtDesc.setText("Deskripsi : \n"+ pelatihan.getDesc());
        txtInCharge.setText("Penanggung Jawab : "+ pelatihan.getIncharge());
        txtPrice.setText("Rp. "+ pelatihan.getPrice());
        txtQty.setText("Kuota : "+ pelatihan.getQuota()+"/"+ pelatihan.getAccept_participant());
        txtStartDate.setText("Tanggal Pelatihan : "+ pelatihan.getStart_time() +" - "+ pelatihan.getEnd_time());

        Glide.with(this).load(pelatihan.getPoster()).into(imgDetail);

        ArrayList<Sebaran> sebaranArrayList = pelatihan.getParticipants();

        Log.d("_sebaran", String.valueOf(sebaranArrayList.size()));
        SebaranAdapter adapter = new SebaranAdapter(SebaranActivity.this, sebaranArrayList);
        rcDetailSebaran.setAdapter(adapter);

    }

    private void setRecyclerData() {

    }

    private void initComp() {
        llSebaranDetail = (LinearLayout) findViewById(R.id.ll_sebaran_detail);
        imgDetail       = (ImageView) findViewById(R.id.img_detail);
        txtTitle        = (TextView) findViewById(R.id.txt_title_detail);
        txtDesc         = (TextView) findViewById(R.id.txt_deskripsi_detail);
        txtInCharge     = (TextView) findViewById(R.id.txt_penanggungjawab_detail);
        txtPrice        = (TextView) findViewById(R.id.txt_harga_detail);
        txtQty          = (TextView) findViewById(R.id.txt_qty_detail);
        txtStartDate    = (TextView) findViewById(R.id.txt_tanggal_mulai_detail);

        rcDetailSebaran = (RecyclerView) findViewById(R.id.rc_sebaran);
        rcDetailSebaran.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(SebaranActivity.this);
        rcDetailSebaran.setLayoutManager(layoutManager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            txtDesc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(SebaranActivity.this, HomeActivity.class));
        finish();
        return true;
    }
}
