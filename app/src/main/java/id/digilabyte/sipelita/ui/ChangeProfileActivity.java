package id.digilabyte.sipelita.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.fragment.ProfileFragment;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.helper.FileUtils;
import id.digilabyte.sipelita.helper.UserPreferences;
import id.digilabyte.sipelita.model.User;
import id.digilabyte.sipelita.model.request.UpdateProfileRequest;
import id.digilabyte.sipelita.model.response.UpdateProfileResponse;
import id.digilabyte.sipelita.model.response.UserResponse;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeProfileActivity extends AppCompatActivity {

    private RadioGroup radioGroupAdditionalIdType;
    private RadioButton radioButton;
    private TextInputEditText edtId, edtNama, edtNoHp, edtAddress;
    private TextInputEditText edtEmail;
    private TextInputEditText edtCompanyName, edtCompanyAddress, edtCompanyHp;
    private ImageView imgChangeProfile;
    private Button btnChangeProfile;

    private final static int PROFILE_IMAGE_REQ_CODE = 101;
    private final static int GALLERY_IMAGE_REQ_CODE = 102;

    private ProgressDialog mLoading;
    private Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        radioGroupAdditionalIdType = (RadioGroup) findViewById(R.id.radio_group_additional_id_profile);
        edtId       = (TextInputEditText) findViewById(R.id.edt_nik_profile);
        edtNama     = (TextInputEditText) findViewById(R.id.edt_nama_profile);
        edtNoHp     = (TextInputEditText) findViewById(R.id.edt_nohp_profile);
        edtAddress  = (TextInputEditText) findViewById(R.id.edt_address_profile);
        edtEmail    = (TextInputEditText) findViewById(R.id.edt_email_profile);
        edtCompanyName      = (TextInputEditText) findViewById(R.id.edt_company_name_profile);
        edtCompanyAddress   = (TextInputEditText) findViewById(R.id.edt_company_address_profile);
        edtCompanyHp        = (TextInputEditText) findViewById(R.id.edt_company_nohp_profile);
        imgChangeProfile    = (ImageView) findViewById(R.id.img_profile_change_profile);
        btnChangeProfile    = (Button) findViewById(R.id.btn_update_profile);

        getUserProfile();
        imgChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ChangeProfileActivity.this)
                        .compress(1024)
                        .start(PROFILE_IMAGE_REQ_CODE);
            }
        });

        btnChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                updateUser();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Log.d("_image_", data.getData().toString());

            file = data.getData();

            Glide.with(ChangeProfileActivity.this)
                    .load(file.getPath())
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(imgChangeProfile);
        }
    }

    private void showLoading() {
        mLoading = new ProgressDialog(ChangeProfileActivity.this);
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    private void uploadFile() {

        File file = FileUtils.getFile(ChangeProfileActivity.this, this.file);

        Log.d("_image_", file.getPath());

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(), requestBody);

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<UpdateProfileResponse> uploadFile = api.uploadProfilePicture(UserPreferences.getKeyUserType(this)+" "+UserPreferences.getKeyUserToken(this), body);

        uploadFile.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                mLoading.dismiss();
                startActivity(new Intent(ChangeProfileActivity.this, ProfileFragment.class));
                finish();
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {

            }
        });
    }

    private void updateUser() {

        int selectedAddId = radioGroupAdditionalIdType.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedAddId);

        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(
                edtId.getText().toString(),
                radioButton.getText().toString(),
                edtNama.getText().toString(),
                edtNoHp.getText().toString(),
                edtAddress.getText().toString(),
                edtEmail.getText().toString(),
                edtCompanyName.getText().toString(),
                edtCompanyHp.getText().toString(),
                edtCompanyAddress.getText().toString()
        );

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<UpdateProfileResponse> updateProfileResponseCall = api.updateProfileResponseCall(UserPreferences.getKeyUserType(this)+" "+UserPreferences.getKeyUserToken(this), updateProfileRequest);

        updateProfileResponseCall.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if (file != null) {
                    uploadFile();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {

            }
        });
    }

    private void getUserProfile(){
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<UserResponse> userResponseCall = api.userResponseCall(UserPreferences.getKeyUserType(this) +" "+ UserPreferences.getKeyUserToken(this));

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                User user = response.body().getData();
                User.UserData userData = user.getUser();

                int selectedAdditionalId;
                if(userData.getAdditionalIdTypeUser().equals("ktp")) {
                    selectedAdditionalId = R.id.radio_ktp_profile;
                } else {
                    selectedAdditionalId = R.id.radio_nip_profile;
                }

                RadioButton radioCheck = (RadioButton) findViewById(selectedAdditionalId);
                radioCheck.setChecked(true);

                edtId.setText(userData.getAdditionalIdUser());
                edtNama.setText(userData.getNameUser());
                edtNoHp.setText(userData.getPhoneUser());
                edtAddress.setText(userData.getAddressUser());
                edtEmail.setText(userData.getEmailUser());
                edtCompanyName.setText(userData.getCompanyUser());
                edtCompanyAddress.setText(userData.getCompanyAddressUser());
                edtCompanyHp.setText(userData.getCompanyTelephoneUser());

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(ChangeProfileActivity.this, t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("_user", t.getMessage());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
