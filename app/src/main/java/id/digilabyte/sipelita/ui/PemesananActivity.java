package id.digilabyte.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.adapter.PemesananPagerAdapter;
import id.digilabyte.sipelita.helper.UserPreferences;

public class PemesananActivity extends AppCompatActivity {


    private TabLayout tabLayoutPemesanan;
    private ViewPager viewPagerPemesanan;

    private String Auth = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        Auth = UserPreferences.getKeyUserToken(this);

        if (Auth.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Anda Harus Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PemesananActivity.this, LoginActivity.class));
            finish();
        }

        viewPagerPemesanan = findViewById(R.id.view_pager_pemesanan);
        tabLayoutPemesanan = findViewById(R.id.tab_pemesanan);

        PemesananPagerAdapter pemesananPagerAdapter = new PemesananPagerAdapter(getSupportFragmentManager());
        viewPagerPemesanan.setAdapter(pemesananPagerAdapter);

        tabLayoutPemesanan.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerPemesanan.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPagerPemesanan.setCurrentItem(tab.getPosition());
            }
        });
        viewPagerPemesanan.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutPemesanan));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PemesananActivity.this, HomeActivity.class));
        finish();
    }
}
