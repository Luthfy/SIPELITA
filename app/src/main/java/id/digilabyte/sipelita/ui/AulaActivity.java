package id.digilabyte.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.adapter.AulaAdapter;
public class AulaActivity extends AppCompatActivity {

    private RecyclerView rcAulaList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        rcAulaList = (RecyclerView) findViewById(R.id.rc_aula);
        rcAulaList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(AulaActivity.this);
        rcAulaList.setLayoutManager(layoutManager);

        AulaAdapter adapter = new AulaAdapter();
        rcAulaList.setAdapter(adapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AulaActivity.this, HomeActivity.class));
        finish();
    }
}
