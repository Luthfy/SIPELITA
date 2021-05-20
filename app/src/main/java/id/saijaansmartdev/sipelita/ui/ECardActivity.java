package id.saijaansmartdev.sipelita.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Objects;

import id.saijaansmartdev.sipelita.R;

public class ECardActivity extends AppCompatActivity {

    String textName = "", imageProfil = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_card);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        if (getIntent().hasExtra("NAME"))
            textName = getIntent().getStringExtra("NAME");

        if (getIntent().hasExtra("IMAGE"))
            imageProfil = getIntent().getStringExtra("IMAGE");

        TextView txtFullName = (TextView) findViewById(R.id.text_name);
        ImageView imgProfile = (ImageView) findViewById(R.id.image_profile);

        txtFullName.setText(textName);

        Glide.with(this).load(imageProfil).into(imgProfile);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
