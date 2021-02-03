package id.digilabyte.sipelita.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.adapter.GradePagerAdapter;
import id.digilabyte.sipelita.adapter.HistoryPagerAdapter;
import id.digilabyte.sipelita.helper.UserPreferences;

public class GradeEventActivity extends AppCompatActivity {

    private TabLayout tabLayoutGradeEvent;
    private ViewPager viewPagerGradeEvent;
    private TextView txtTitle;

    private String Auth = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_event);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        Auth = UserPreferences.getKeyUserToken(this);

        if (Auth.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Anda Harus Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(GradeEventActivity.this, LoginActivity.class));
            finish();
        }

        String UUID = "";
        if (getIntent().hasExtra("UUID"))
            UUID = getIntent().getStringExtra("UUID");

        String TITLE = "";
        if (getIntent().hasExtra("TITLE"))
            TITLE = getIntent().getStringExtra("TITLE");

        txtTitle = findViewById(R.id.title_grade);
        txtTitle.setText(TITLE.toUpperCase());

        viewPagerGradeEvent = findViewById(R.id.view_pager_grade_event);
        tabLayoutGradeEvent = findViewById(R.id.tab_grade_event);

        GradePagerAdapter gradePagerAdapter = new GradePagerAdapter(getSupportFragmentManager(), UUID);
        viewPagerGradeEvent.setAdapter(gradePagerAdapter);

        tabLayoutGradeEvent.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerGradeEvent.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPagerGradeEvent.setCurrentItem(tab.getPosition());
            }
        });
        viewPagerGradeEvent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutGradeEvent));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GradeEventActivity.this, HomeActivity.class));
        finish();
    }
}
