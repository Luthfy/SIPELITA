package id.digilabyte.sipelita.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.helper.UserPreferences;
import id.digilabyte.sipelita.model.request.ChangePasswordRequest;
import id.digilabyte.sipelita.model.response.ChangePasswordResponse;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import id.digilabyte.sipelita.ui.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    TextInputEditText edtOldPass, edtNewPass, edtConfirmPass;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        edtOldPass      = (TextInputEditText) view.findViewById(R.id.edt_pass_set1);
        edtNewPass      = (TextInputEditText) view.findViewById(R.id.edt_pass_set2);
        edtConfirmPass  = (TextInputEditText) view.findViewById(R.id.edt_pass_set3);

        Button btnChangePassword = (Button) view.findViewById(R.id.btn_ganti_pass_setting);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtOldPass.getText().toString().isEmpty() && edtNewPass.getText().toString().isEmpty() && edtConfirmPass.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    changePasswordRequest();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void changePasswordRequest() {
        ChangePasswordRequest newPass = new ChangePasswordRequest(
                edtOldPass.getText().toString(),
                edtNewPass.getText().toString(),
                edtConfirmPass.getText().toString()
        );
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<ChangePasswordResponse> changePasswordResponseCall = api.changePasswordResponseCall(UserPreferences.getKeyUserType(getContext())+" "+UserPreferences.getKeyUserToken(getContext()), newPass);

        changePasswordResponseCall.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                Toast.makeText(getContext(), "Password Berhasil diganti", Toast.LENGTH_SHORT).show();
                UserPreferences.clearKeyUserToken(getContext());
                startActivity(new Intent(getContext(), HomeActivity.class));
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Password Gagal diganti", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
