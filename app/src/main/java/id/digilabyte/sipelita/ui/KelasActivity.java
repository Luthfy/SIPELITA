package id.digilabyte.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.adapter.KelasAdapter;

public class KelasActivity extends AppCompatActivity {

    private RecyclerView rcKelasList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        rcKelasList = (RecyclerView) findViewById(R.id.rc_kelas);
        rcKelasList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(KelasActivity.this);
        rcKelasList.setLayoutManager(layoutManager);

        KelasAdapter adapter = new KelasAdapter();
        rcKelasList.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(KelasActivity.this, HomeActivity.class));
        finish();
    }
}
