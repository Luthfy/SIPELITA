package id.digilabyte.sipelita.ui;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.fragment.ContactFragment;
import id.digilabyte.sipelita.fragment.DashboardFragment;
import id.digilabyte.sipelita.fragment.ProfileFragment;
import id.digilabyte.sipelita.fragment.SebaranFragment;
import id.digilabyte.sipelita.fragment.SettingFragment;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.helper.UserPreferences;
import id.digilabyte.sipelita.model.User;
import id.digilabyte.sipelita.model.response.LogoutResponse;
import id.digilabyte.sipelita.model.response.UserResponse;
import id.digilabyte.sipelita.model.response.VerifyResponse;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private User.UserData userData;
    private View hView;
    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        drawerLayout = (DrawerLayout) findViewById(R.id.menu_utama);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView = findViewById(R.id.menu_sidebar);

        fragmentManager = getSupportFragmentManager();
        try {
            fragmentManager.beginTransaction().replace(R.id.fl_content, DashboardFragment.class.newInstance()).commit();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void getUserProfile(View s){

        ImageView img = s.findViewById(R.id.user_thumbnail);
        TextView txtHallo = s.findViewById(R.id.txt_hallo);
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<UserResponse> userResponseCall = api.userResponseCall(UserPreferences.getKeyUserType(HomeActivity.this) +" "+ UserPreferences.getKeyUserToken(HomeActivity.this));

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                User user = response.body().getData();
                userData = user.getUser();
                Glide.with(HomeActivity.this).load(userData.getProfileImageUser()).error(R.drawable.doctor_l).into(img);
                txtHallo.setText("Hallo " + userData.getNameUser());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("_user", t.getMessage());
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.hView != null) {
            getUserProfile(this.hView);
        } else {
            navigationViewInit();
        }
    }

    private void navigationViewInit() {

        ImageView logoProvinsi = findViewById(R.id.logo_provinsi);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.e("Width", "" + width);
        Log.e("height", "" + height);

        if (height < 1900) {
            logoProvinsi.setVisibility(View.INVISIBLE);
        }

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<VerifyResponse> verifyResponseCall = api.validateTokenResponse(UserPreferences.getKeyUserType(this)+" "+UserPreferences.getKeyUserToken(this));

        verifyResponseCall.enqueue(new Callback<VerifyResponse>() {

            @Override
            public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {

                if(response.body() != null) {

                    message = response.body().getMessage();
                    Log.d("_home_activity", message);

                    hView = navigationView.inflateHeaderView(R.layout.nav_header_login);

                    getUserProfile(hView);

                    Menu nav_Menu = navigationView.getMenu();
                    nav_Menu.findItem(R.id.menu_logout).setVisible(true);
                    nav_Menu.findItem(R.id.menu_setting ).setVisible(true);
                    nav_Menu.findItem(R.id.menu_profile ).setVisible(true);
                    nav_Menu.findItem(R.id.menu_sebaran ).setVisible(true);

                } else {
                    Log.d("HomeActivity", "Navigation Else");
                    hView = navigationView.inflateHeaderView(R.layout.nav_header);

                    TextView btnMasuk = (TextView) hView.findViewById(R.id.txt_masuk_nav_header);
                    btnMasuk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        }
                    });

                    TextView btnDaftar = (TextView) hView.findViewById(R.id.txt_daftar_nav_header);
                    btnDaftar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<VerifyResponse> call, Throwable t) {
                message = t.getMessage();
                Log.e("_home_activity", t.getMessage());
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home :
                try {
                    fragmentManager.beginTransaction().replace(R.id.fl_content, DashboardFragment.class.newInstance()).commit();
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.menu_profile:
                try {
                    fragmentManager.beginTransaction().replace(R.id.fl_content, ProfileFragment.class.newInstance()).commit();
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                break;
                case R.id.menu_contact:
                try {
                    fragmentManager.beginTransaction().replace(R.id.fl_content, ContactFragment.class.newInstance()).commit();
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.menu_sebaran:
                try {
                    fragmentManager.beginTransaction().replace(R.id.fl_content, SebaranFragment.class.newInstance()).commit();
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.menu_setting:
                try {
                    fragmentManager.beginTransaction().replace(R.id.fl_content, SettingFragment.class.newInstance()).commit();
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.menu_logout:
                logout();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setMessage("Apakah anda ingin logout?")
                .setCancelable(false)
                .setPositiveButton("Ya", (dialog, id) -> {
                    UserPreferences.clearKeyUserToken(this);
                    logoutRequest();
                    startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                    finish();
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    private void logoutRequest() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<LogoutResponse> logoutResponseCall = api.logoutResponseCall(UserPreferences.getKeyUserType(this)+" "+UserPreferences.getKeyUserToken(this));

        logoutResponseCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {

            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {

            }
        });
    }
}
