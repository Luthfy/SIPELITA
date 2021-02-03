package id.digilabyte.sipelita.fragment;

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

import java.util.ArrayList;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.adapter.GradeAdapter;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.helper.UserPreferences;
import id.digilabyte.sipelita.model.GradeResult;
import id.digilabyte.sipelita.model.HistoryRoom;
import id.digilabyte.sipelita.model.response.GradeResultResponse;
import id.digilabyte.sipelita.model.response.TestResultResponse;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostTestGradeFragment extends Fragment {

    private RecyclerView rcPostTestGradeList;
    private RecyclerView.LayoutManager layoutManager;

    private GradeResult gradeResultArrayList;
    private int page = 1;
    private String UUID = "";

    private ProgressDialog mLoading;

    public PostTestGradeFragment(String UUID) {
        this.UUID = UUID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_test_grade, container, false);

        rcPostTestGradeList = (RecyclerView) view.findViewById(R.id.rc_post_test);
        rcPostTestGradeList.setHasFixedSize(true);

        postGradeRequest();
        return view;

    }

    private void postGradeRequest() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<GradeResultResponse> gradeResultResponseCall = api.postTestResponzeCall(UserPreferences.getKeyUserType(getContext())+" "+UserPreferences.getKeyUserToken(getContext()), UUID);

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
        rcPostTestGradeList.setLayoutManager(layoutManager);

        GradeAdapter gradeAdapter = new GradeAdapter(gradeResultArrayList.getResult());
        rcPostTestGradeList.setAdapter(gradeAdapter);
    }

    private void showLoading() {
        mLoading = new ProgressDialog(getContext());
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }
}
