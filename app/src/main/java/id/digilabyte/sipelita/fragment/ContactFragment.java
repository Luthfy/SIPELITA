package id.digilabyte.sipelita.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.model.Contact;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    private TextView txtOperationalTime, txtAddress, txtEmail, txtPhone;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        txtOperationalTime  = (TextView) view.findViewById(R.id.txt_operational_time_contact);
        txtAddress          = (TextView) view.findViewById(R.id.txt_address_contact);
        txtEmail            = (TextView) view.findViewById(R.id.txt_email_contact);
        txtPhone            = (TextView) view.findViewById(R.id.txt_telephone_contact);

        contectRequest();
        return view;
    }

    private void contectRequest() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<Contact> contactResponseCall = api.contactResponseCall();

        contactResponseCall.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                txtOperationalTime.setText("Waktu Operasional : \n"+response.body().getOperation_time());
                txtAddress.setText("Alamat : \n"+response.body().getAddress());
                txtEmail.setText("Email : "+response.body().getEmail());
                txtPhone.setText("Telephone : "+response.body().getTelephone());
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

            }
        });
    }

}
