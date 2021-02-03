package id.digilabyte.sipelita.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.adapter.SebaranPelatihanAdapter;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.helper.UserPreferences;
import id.digilabyte.sipelita.model.Pelatihan;
import id.digilabyte.sipelita.model.response.PelatihanResponse;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SebaranFragment extends Fragment {

    private RecyclerView rcPelatihanList;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog mLoading;
    private View view;

    public SebaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sebaran, container, false);

        showLoading();

        rcPelatihanList = (RecyclerView) view.findViewById(R.id.sebaran_fragment);
        rcPelatihanList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        rcPelatihanList.setLayoutManager(layoutManager);

        listDataRequest();

        // Inflate the layout for this fragment
        return view;
    }

    private void showLoading() {
        mLoading = new ProgressDialog(getContext());
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    private void listDataRequest() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<PelatihanResponse> pelatihanResponseCall = api.pelatihanResponse(UserPreferences.getKeyUserType(getContext())+" "+UserPreferences.getKeyUserToken(getContext()));

        pelatihanResponseCall.enqueue(new Callback<PelatihanResponse>() {
            @Override
            public void onResponse(Call<PelatihanResponse> call, Response<PelatihanResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("success")) {
                        setListDataPelatihan(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<PelatihanResponse> call, Throwable t) {

            }
        });
    }

    private void setListDataPelatihan(ArrayList<Pelatihan> list) {
        mLoading.dismiss();
        SebaranPelatihanAdapter adapter = new SebaranPelatihanAdapter(list);
        rcPelatihanList.setAdapter(adapter);
    }


}
