package id.saijaansmartdev.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.adapter.HargaAdapter;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.model.response.BangunanResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HargaActivity extends AppCompatActivity {

    RecyclerView rcPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harga);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        rcPrice = findViewById(R.id.rc_price);
        rcPrice.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HargaActivity.this);
        rcPrice.setLayoutManager(layoutManager);

        bangunanRequest();
    }

    private void bangunanRequest() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<BangunanResponse> hargaResponseCall = api.hargaResponseCall();

        hargaResponseCall.enqueue(new Callback<BangunanResponse>() {
            @Override
            public void onResponse(@NotNull Call<BangunanResponse> call, @NotNull Response<BangunanResponse> response) {
                if (response.body() != null) {
                    HargaAdapter hargaAdapter = new HargaAdapter(response.body().getData(), HargaActivity.this);
                    rcPrice.setAdapter(hargaAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<BangunanResponse> call, @NotNull Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HargaActivity.this, HomeActivity.class));
        finish();
    }
}
