package id.saijaansmartdev.sipelita.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.helper.UserPreferences;
import id.saijaansmartdev.sipelita.model.User;
import id.saijaansmartdev.sipelita.model.response.UserResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import id.saijaansmartdev.sipelita.ui.ChangeProfileActivity;
import id.saijaansmartdev.sipelita.ui.ECardActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView txtNamaProfile, txtAlamatProfile, txtEmailProfile, txtNoHpProfile;
    TextView txtCompanyNameProfile, txtCompanyAddressProfile, txtCompanyNoTelpProfile;
    LinearLayout llCompanyUser;
    ImageView imgUserProfile;

    String imagePath = "";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Button btnChangeProfile = (Button) view.findViewById(R.id.btn_change_profile);
        Button btnEcard = (Button) view.findViewById(R.id.btn_ecard);

        llCompanyUser       = (LinearLayout) view.findViewById(R.id.ll_company_profile);
        txtNamaProfile      = (TextView) view.findViewById(R.id.txt_nama_profile);
        txtAlamatProfile    = (TextView) view.findViewById(R.id.txt_alamat_profile);
        txtEmailProfile     = (TextView) view.findViewById(R.id.txt_email_profile);
        txtNoHpProfile      = (TextView) view.findViewById(R.id.txt_no_hp_profile);
        imgUserProfile      = (ImageView) view.findViewById(R.id.img_user_profile);

        txtCompanyNameProfile       = (TextView) view.findViewById(R.id.txt_company_name_profile);
        txtCompanyAddressProfile    = (TextView) view.findViewById(R.id.txt_company_address_profile);
        txtCompanyNoTelpProfile     = (TextView) view.findViewById(R.id.txt_company_telephone_profile);

        getUserProfile();

        btnChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ChangeProfileActivity.class));
            }
        });

        btnEcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ECardActivity.class);
                intent.putExtra("NAME", txtNamaProfile.getText());
                intent.putExtra("IMAGE", imagePath);
                startActivity(intent);
            }
        });

        return view;

    }

    private void getUserProfile(){
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<UserResponse> userResponseCall = api.userResponseCall(UserPreferences.getKeyUserType(getContext()) +" "+ UserPreferences.getKeyUserToken(getContext()));

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                User user = response.body().getData();
                User.UserData userData = user.getUser();

                txtNamaProfile.setText(userData.getNameUser());
                txtAlamatProfile.setText(userData.getAddressUser());
                txtEmailProfile.setText(userData.getEmailUser());
                txtNoHpProfile.setText(userData.getPhoneUser());

                if (userData.getCompanyUser() == null && userData.getCompanyAddressUser() == null && userData.getCompanyTelephoneUser() == null) {
                    llCompanyUser.setVisibility(View.GONE);
                } else {
                    txtCompanyNameProfile.setText(userData.getCompanyUser());
                    txtCompanyAddressProfile.setText(userData.getCompanyAddressUser());
                    txtCompanyNoTelpProfile.setText(userData.getCompanyTelephoneUser());
                }

                Glide.with(getContext())
                        .load(userData.getProfileImageUser())
                        .error(R.drawable.doctor_l)
                        .into(imgUserProfile);

                imagePath = userData.getProfileImageUser();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("_user", t.getMessage());
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserProfile();
    }
}