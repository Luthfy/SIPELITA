package id.saijaansmartdev.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.adapter.BangunanAdapter;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.helper.UserPreferences;
import id.saijaansmartdev.sipelita.model.Bangunan;
import id.saijaansmartdev.sipelita.model.response.BangunanResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BangunanActivity extends AppCompatActivity {

    private EditText edtDate;
    private RecyclerView rcBangunan;
    private GridLayoutManager gridLayoutManager;
    private CardView cardViewBangunanDetail;

    private String type = "", Auth;

    private ProgressDialog mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangunan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        Auth = UserPreferences.getKeyUserToken(this);

        if (Auth.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Anda Harus Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BangunanActivity.this, LoginActivity.class));
            finish();
        }

        if (getIntent().getStringExtra("TYPE") != null) {
            type = getIntent().getStringExtra("TYPE");
        } else {
            Toast.makeText(getApplicationContext(), "Illegal IOException", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BangunanActivity.this, HomeActivity.class));
            finish();
        }

        edtDate = findViewById(R.id.edt_get_date);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear   = c.get(Calendar.YEAR);
                int mMonth  = c.get(Calendar.MONTH);
                int mDay    = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(BangunanActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                bangunanRequest(edtDate.getText());
                                showLoading();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        rcBangunan = findViewById(R.id.rc_bangunan);
        rcBangunan.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        rcBangunan.setLayoutManager(gridLayoutManager);
    }

    private void showLoading() {
        mLoading = new ProgressDialog(BangunanActivity.this);
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    private void bangunanRequest(Editable date) {
        Log.d("_bangunan_date", date.toString());

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<BangunanResponse> bangunanResponseCall = api.bangunanResponseCall(type);

        bangunanResponseCall.enqueue(new Callback<BangunanResponse>() {
            @Override
            public void onResponse(Call<BangunanResponse> call, Response<BangunanResponse> response) {
                setBangunan(response.body().getData());
            }

            @Override
            public void onFailure(Call<BangunanResponse> call, Throwable t) {
                Toast.makeText(BangunanActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setBangunan(ArrayList<Bangunan> list) {
        mLoading.dismiss();
        Log.d("bangunan_list", String.valueOf(list.size()));
        TextView txtPilihBangunan = findViewById(R.id.txt_pilih_bangunan);
        txtPilihBangunan.setVisibility(View.VISIBLE);
        BangunanAdapter adapter = new BangunanAdapter(list, this);
        rcBangunan.setAdapter(adapter);
    }

    public void setBangunanDetail(Bangunan bangunan) {
        cardViewBangunanDetail  = findViewById(R.id.card_view_bangunan_detail);
        cardViewBangunanDetail.setVisibility(View.VISIBLE);

        ImageView imgGambarBangunan       = findViewById(R.id.img_gambar_bangunan);
        ImageView imgRoom1Bangunan        = findViewById(R.id.img_room_1_bangunan);
        ImageView imgRoom2Bangunan        = findViewById(R.id.img_room_2_bangunan);
        ImageView imgRoom3Bangunan        = findViewById(R.id.img_room_3_bangunan);

        TextView txtNamaBangunan          = findViewById(R.id.txt_nama_bangunan);
        TextView txtDeskripsiBangunan     = findViewById(R.id.txt_deskripsi_bangunan);
        TextView txtFasilitasBangunan     = findViewById(R.id.txt_fasilitas_bangunan);
        TextView txtHargaInstansiBangunan = findViewById(R.id.txt_company_price_bangunan);
        TextView txtHargaPublicBangunan   = findViewById(R.id.txt_public_price_bangunan);

        Button btnCheckRoom = findViewById(R.id.btn_pilih_bangunan);

        txtNamaBangunan.setText(bangunan.getName());
        if (bangunan.getDesc() == null) {
            txtDeskripsiBangunan.setVisibility(View.GONE);
        } else {
            txtDeskripsiBangunan.setText(bangunan.getDesc());
        }
        if (bangunan.getFacilities() == null) {
            txtFasilitasBangunan.setText("");
        } else {
            txtFasilitasBangunan.setText(bangunan.getFacilities().replaceAll(";", ", "));
        }
        txtHargaInstansiBangunan.setText("Harga Instansi : \nRp. "+bangunan.getCompany_price());
        txtHargaPublicBangunan.setText("Harga Umum : \nRp. "+bangunan.getPublic_price());

        Glide.with(this).load(bangunan.getMedia().getBuilding().get(0)).into(imgGambarBangunan);

        int i = 0;

        ArrayList<ImageView> imagePlace = new ArrayList<>();
        imagePlace.add(imgRoom1Bangunan);
        imagePlace.add(imgRoom2Bangunan);
        imagePlace.add(imgRoom3Bangunan);

        for (String room : bangunan.getMedia().getRoom()) {
            Glide.with(this).load(room).into(imagePlace.get(i));
            i++;
        }

        String UUID = bangunan.getId();
        String date = edtDate.getText().toString();

        btnCheckRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("_bangunan_type", type);
                if (type.equals("bedroom")) {
                    Intent intent = new Intent(BangunanActivity.this, RuangActivity.class);
                    intent.putExtra("DATE", date);
                    intent.putExtra("UUID", UUID);
                    intent.putExtra("TYPE", type);
                    startActivity(intent);
                } else if (type.equals("auditorium")) {
                    Intent intent = new Intent(BangunanActivity.this, RuangActivity.class);
                    intent.putExtra("DATE", date);
                    intent.putExtra("UUID", UUID);
                    intent.putExtra("TYPE", type);
                    startActivity(intent);
                } else if (type.equals("classroom")) {
                    Intent intent = new Intent(BangunanActivity.this, RuangActivity.class);
                    intent.putExtra("DATE", date);
                    intent.putExtra("UUID", UUID);
                    intent.putExtra("TYPE", type);
                    startActivity(intent);
                } else {
                    Toast.makeText(BangunanActivity.this, "Illegal Activity", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BangunanActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

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
        startActivity(new Intent(BangunanActivity.this, HomeActivity.class));
        finish();
    }
}
