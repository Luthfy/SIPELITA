package id.saijaansmartdev.sipelita.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.model.request.RegisterRequest;
import id.saijaansmartdev.sipelita.model.response.RegisterResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private RadioGroup radioGroupAdditionalIdType;
    private RadioButton radioButton;
    private TextInputEditText edtId, edtNama, edtNoHp, edtAddress;
    private TextInputEditText edtEmail, edtPass1, edtPass2;
    private TextInputEditText edtCompanyName, edtCompanyAddress, edtCompanyHp;
    private TextView btnMasuk, btnLupa;
    private Button btnDaftar;

    private ProgressDialog mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        initComponent();

        btnDaftar = (Button) findViewById(R.id.btn_daftar_regis);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPass1.getText().toString().equals(edtPass2.getText().toString())) {
                    registerRequest();
                } else {
                    Toast.makeText(getApplicationContext(), "Password tidak cocok", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnMasuk = (TextView) findViewById(R.id.txt_masuk_regis);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnLupa = (TextView) findViewById(R.id.txt_lupa_pass_regis);
        btnLupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fitur Lupa Password Belum Aktif", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoading() {
        mLoading = new ProgressDialog(RegisterActivity.this);
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    private void registerRequest() {

        int selectedAddId = radioGroupAdditionalIdType.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedAddId);

        RegisterRequest register = new RegisterRequest();

        if (radioButton.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Jenis Identitas kosong", (int) 500).show();
            radioButton.requestFocus();
        } else if (edtId.getText().toString().isEmpty()) {
            edtId.setError("Kolom ini tidak boleh kosong");
            edtId.requestFocus();
        } else if (edtNama.getText().toString().isEmpty()) {
            edtNama.setError("Kolom ini tidak boleh kosong");
            edtNama.requestFocus();
        } else if (edtNoHp.getText().toString().isEmpty()) {
            edtNoHp.setError("Kolom ini tidak boleh kosong");
            edtNoHp.requestFocus();
        } else if (edtAddress.getText().toString().isEmpty()) {
            edtAddress.setError("Kolom ini tidak boleh kosong");
            edtAddress.requestFocus();
        } else if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Kolom ini tidak boleh kosong");
            edtEmail.requestFocus();
        } else if (edtPass1.getText().toString().isEmpty()) {
            edtPass1.setError("Kolom ini tidak boleh kosong");
            edtPass1.requestFocus();
        } else if (edtPass2.getText().toString().isEmpty()) {
            edtPass2.setError("Kolom ini tidak boleh kosong");
            edtPass2.requestFocus();
        } else if (edtPass2.getText().equals(edtPass1.getText())) {
            edtPass1.setError("Password tidak cocok");
            edtPass2.setError("Password tidak cocok");
            edtPass1.requestFocus();
        } else {
            showLoading();
            register.setAdditional_id_type(radioButton.getText().toString());
            register.setAdditional_id(edtId.getText().toString());
            register.setName(edtNama.getText().toString());
            register.setPhone(edtNoHp.getText().toString());
            register.setAddress(edtAddress.getText().toString());
            register.setEmail(edtEmail.getText().toString());
            register.setPassword(edtPass1.getText().toString());
            register.setCompany(edtCompanyName.getText().toString());
            register.setCompany_address(edtCompanyAddress.getText().toString());
            register.setCompany_phone(edtCompanyHp.getText().toString());

            BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
            Call<RegisterResponse> registerResponseCall = api.registerResponseCall(register);

            registerResponseCall.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("success")) {
                            mLoading.dismiss();
                            Toast.makeText(getApplicationContext(), "Registrasi Berhasil, Silakan Login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            mLoading.dismiss();
                            Toast.makeText(getApplicationContext(), response.body().getMessage().get(0), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mLoading.dismiss();
                        Toast.makeText(getApplicationContext(), "Data gagal dibuat, silakan coba lagi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    mLoading.dismiss();
                }
            });

        }

    }

    private void initComponent() {
        radioGroupAdditionalIdType = (RadioGroup) findViewById(R.id.radio_group_additional_id);
        edtId       = (TextInputEditText) findViewById(R.id.edt_nik_regis);
        edtNama     = (TextInputEditText) findViewById(R.id.edt_nama_regis);
        edtNoHp     = (TextInputEditText) findViewById(R.id.edt_nohp_regis);
        edtAddress  = (TextInputEditText) findViewById(R.id.edt_address_regis);
        edtEmail    = (TextInputEditText) findViewById(R.id.edt_email_regis);
        edtPass1    = (TextInputEditText) findViewById(R.id.edt_pass1_regis);
        edtPass2    = (TextInputEditText) findViewById(R.id.edt_pass2_regis);
        edtCompanyName      = (TextInputEditText) findViewById(R.id.edt_company_name_regis);
        edtCompanyAddress   = (TextInputEditText) findViewById(R.id.edt_company_address_regis);
        edtCompanyHp        = (TextInputEditText) findViewById(R.id.edt_company_nohp_regis);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
        finish();
    }
}
