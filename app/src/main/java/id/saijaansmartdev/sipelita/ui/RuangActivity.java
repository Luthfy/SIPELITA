package id.saijaansmartdev.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.adapter.KamarAdapter;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.helper.UserPreferences;
import id.saijaansmartdev.sipelita.model.Kamar;
import id.saijaansmartdev.sipelita.model.request.BangunanRequest;
import id.saijaansmartdev.sipelita.model.request.RuangRequest;
import id.saijaansmartdev.sipelita.model.response.KamarResponse;
import id.saijaansmartdev.sipelita.model.response.RuangResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RuangActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rcKamarList;
    private GridLayoutManager gridLayoutManager;
    private String UUID = "", Auth = "", Date = "", Type = "";
    private ArrayList<String> idKamar = new ArrayList<>();
    private Button btnPesanRuang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        Auth = UserPreferences.getKeyUserToken(this);

        if (Auth.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Anda Harus Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RuangActivity.this, LoginActivity.class));
            finish();
        }

        UUID = getIntent().getStringExtra("UUID"); // Building ID
        Date = getIntent().getStringExtra("DATE");
        Type = getIntent().getStringExtra("TYPE");

        if (UUID.isEmpty() && Date.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Pilih Bangunan Terlebih Dahulu", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RuangActivity.this, BangunanActivity.class));
            finish();
        }

        btnPesanRuang = (Button) findViewById(R.id.btn_ruang);

        rcKamarList = (RecyclerView) findViewById(R.id.rc_kamar);
        rcKamarList.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rcKamarList.setLayoutManager(gridLayoutManager);

        btnPesanRuang.setOnClickListener(this);

        idKamar.clear();
        kamarRequest();
    }

    private void kamarRequest() {

        BangunanRequest bangunanRequest = new BangunanRequest(Date, UUID);

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<KamarResponse> kamarResponseCall = api.kamarResponseCall(Auth, bangunanRequest);

        kamarResponseCall.enqueue(new Callback<KamarResponse>() {
            @Override
            public void onResponse(Call<KamarResponse> call, Response<KamarResponse> response) {
                ArrayList<Kamar.RuangKamar> ruangKamar = response.body().getData().getRoom().getData();
                setDataList(ruangKamar);
            }

            @Override
            public void onFailure(Call<KamarResponse> call, Throwable t) {
                Toast.makeText(RuangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataList(ArrayList<Kamar.RuangKamar> ruangKamar) {
        KamarAdapter adapter = new KamarAdapter(ruangKamar);
        rcKamarList.setAdapter(adapter);

    }

    public ArrayList<String> getIdKamar() {
        return idKamar;
    }

    public void setIdKamar(ArrayList<String> idKamar) {
        this.idKamar = idKamar;
    }

    public void updateIdKamar(String id) {
        this.idKamar.add(id);
        setBtnPesanRuang();
    }

    public void removeIdKamar(String id) {
        this.idKamar.remove(id);
        setBtnPesanRuang();
    }

    public void setBtnPesanRuang() {
        if (idKamar.size() > 0) {
            btnPesanRuang.setVisibility(View.VISIBLE);
            btnPesanRuang.setText("Pesan Ruang ("+idKamar.size()+" telah dipilih)");
        } else {
            btnPesanRuang.setVisibility(View.INVISIBLE);
            btnPesanRuang.setText("Pesan Ruang ("+idKamar.size()+" telah dipilih)");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RuangActivity.this, BangunanActivity.class);
        intent.putExtra("TYPE", this.Type);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        RuangRequest ruangRequest = new RuangRequest();
        ruangRequest.setDate(Date);
        ruangRequest.setBuilding_id(UUID);
        ruangRequest.setRoom(idKamar);
        ruangRequest.setCompany(false);

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<RuangResponse> ruangResponseCall = api.ruangResponseCall(UserPreferences.getKeyUserType(this)+" "+Auth, ruangRequest);

        ruangResponseCall.enqueue(new Callback<RuangResponse>() {
            @Override
            public void onResponse(Call<RuangResponse> call, Response<RuangResponse> response) {
                Toast.makeText(RuangActivity.this, "Berhasil dipesan, silakan konfirmasi ke admin", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RuangActivity.this, HistoryActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<RuangResponse> call, Throwable t) {
                Toast.makeText(RuangActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
