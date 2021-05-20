package id.saijaansmartdev.sipelita.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.helper.UserPreferences;
import id.saijaansmartdev.sipelita.model.Login;
import id.saijaansmartdev.sipelita.model.request.LoginRequest;
import id.saijaansmartdev.sipelita.model.response.LoginResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView btnLupa, btnDaftar;
    private TextInputEditText edtUsername, edtPassword;
    private Button btnMasuk;

    private ProgressDialog mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        edtUsername = (TextInputEditText) findViewById(R.id.edt_email_login);
        edtPassword = (TextInputEditText) findViewById(R.id.edt_pass_login);

        btnMasuk = (Button) findViewById(R.id.btn_masuk_login);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUsername.getText().toString().isEmpty() && edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username dan Password kosong", (int) 500).show();
                } else if(edtUsername.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username kosong", (int) 500).show();
                } else if(edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password kosong", (int) 500).show();
                } else {
                    showLoading();
                    loginRequest();
                }
            }
        });

        btnDaftar = (TextView) findViewById(R.id.txt_daftar_login);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLupa = (TextView) findViewById(R.id.txt_lupa_pass_login);
        btnLupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fitur Lupa Password Belum Aktif", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoading() {
        mLoading = new ProgressDialog(LoginActivity.this);
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    private void loginRequest() {

        LoginRequest login = new LoginRequest(edtUsername.getText().toString(),edtPassword.getText().toString());

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<LoginResponse> loginResponseCall = api.loginResponseCall(login);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Login login = new Login();
                try {
                    if (response.body().getStatus().equals("success")) {
                        mLoading.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                        login.setToken(response.body().getData().getToken());
                        login.setToken_type(response.body().getData().getToken_type());
                        login.setExpire_in(response.body().getData().getExpire_in());

                        UserPreferences.setKeyUserToken(LoginActivity.this,login.getToken());
                        UserPreferences.setKeyUserType(LoginActivity.this,login.getToken_type());

                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                        mLoading.dismiss();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Username dan Password tidak dikenal", Toast.LENGTH_SHORT).show();
                    mLoading.dismiss();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                mLoading.dismiss();
                Log.e("_login_activity", Objects.requireNonNull(t.getMessage()));
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}
