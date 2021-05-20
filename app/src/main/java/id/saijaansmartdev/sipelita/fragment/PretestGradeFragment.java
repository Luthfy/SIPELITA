package id.saijaansmartdev.sipelita.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.adapter.GradeAdapter;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.helper.UserPreferences;
import id.saijaansmartdev.sipelita.model.GradeResult;
import id.saijaansmartdev.sipelita.model.response.GradeResultResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PretestGradeFragment extends Fragment {

    private RecyclerView rcPreTestGradeList;
    private RecyclerView.LayoutManager layoutManager;

    private GradeResult gradeResultArrayList;
    private String UUID = "";

    private ProgressDialog mLoading;

    public PretestGradeFragment(String UUID) {
        this.UUID = UUID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pre_test_grade, container, false);

        rcPreTestGradeList = (RecyclerView) view.findViewById(R.id.rc_pre_test);
        rcPreTestGradeList.setHasFixedSize(true);
        showLoading();
        preGradeRequest();
        return view;

    }

    private void preGradeRequest() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<GradeResultResponse> gradeResultResponseCall = api.preTestResponzeCall(UserPreferences.getKeyUserType(getContext())+" "+UserPreferences.getKeyUserToken(getContext()), UUID);

        gradeResultResponseCall.enqueue(new Callback<GradeResultResponse>() {
            @Override
            public void onResponse(Call<GradeResultResponse> call, Response<GradeResultResponse> response) {
                assert response.body() != null;
                gradeResultArrayList = response.body().getData();
                setRecyclerPostTestGrade(gradeResultArrayList);
            }

            @Override
            public void onFailure(Call<GradeResultResponse> call, Throwable t) {

            }
        });
    }

    private void setRecyclerPostTestGrade(GradeResult gradeResultArrayList) {
        layoutManager = new LinearLayoutManager(getContext());
        rcPreTestGradeList.setLayoutManager(layoutManager);

        GradeAdapter gradeAdapter = new GradeAdapter(gradeResultArrayList.getResult());
        rcPreTestGradeList.setAdapter(gradeAdapter);

        mLoading.dismiss();
    }

    private void showLoading() {
        mLoading = new ProgressDialog(getContext());
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }
}
