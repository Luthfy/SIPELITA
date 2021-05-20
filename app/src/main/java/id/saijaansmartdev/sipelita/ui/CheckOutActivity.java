package id.saijaansmartdev.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.model.FormPelatihan;

public class CheckOutActivity extends AppCompatActivity {

    private Button btnKembaliCheckOut;
    private TextView txtRefCheckOut, txtStatusCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        FormPelatihan.Participant fm = getIntent().getParcelableExtra("FORM");

        txtRefCheckOut = (TextView) findViewById(R.id.ref_check_out);
        txtStatusCheckOut = (TextView) findViewById(R.id.status_check_out);

        txtRefCheckOut.setText(fm.getCode().toUpperCase());
        txtStatusCheckOut.setText("Status : "+fm.getStatus());

        btnKembaliCheckOut = (Button) findViewById(R.id.btn_kembali_check_out);
        btnKembaliCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckOutActivity.this, PemesananActivity.class));
                finish();
            }
        });
    }
}
