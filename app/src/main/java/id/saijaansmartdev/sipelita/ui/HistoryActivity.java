package id.saijaansmartdev.sipelita.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.adapter.HistoryPagerAdapter;
import id.saijaansmartdev.sipelita.helper.UserPreferences;

public class HistoryActivity extends AppCompatActivity {

    private TabLayout tabLayoutHistory;
    private ViewPager viewPagerHistory;

    private String Auth = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        Auth = UserPreferences.getKeyUserToken(this);

        if (Auth.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Anda Harus Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HistoryActivity.this, LoginActivity.class));
            finish();
        }

        viewPagerHistory = findViewById(R.id.view_pager_history);
        tabLayoutHistory = findViewById(R.id.tab_history);

        HistoryPagerAdapter historyPagerAdapter = new HistoryPagerAdapter(getSupportFragmentManager());
        viewPagerHistory.setAdapter(historyPagerAdapter);

        tabLayoutHistory.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerHistory.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPagerHistory.setCurrentItem(tab.getPosition());
            }
        });
        viewPagerHistory.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutHistory));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HistoryActivity.this, HomeActivity.class));
        finish();
    }
}
