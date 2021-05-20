package id.saijaansmartdev.sipelita.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.adapter.HistoryRoomAdapter;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.helper.UserPreferences;
import id.saijaansmartdev.sipelita.model.HistoryRoom;
import id.saijaansmartdev.sipelita.model.response.HistoryRoomResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryRoomFragment extends Fragment {

    private RecyclerView rcHistoryRoomList;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<HistoryRoom.ReservedRoom> historyRoomArrayList;
    private HistoryRoom historyRoom;
    private int pageRoom = 1;

    private ProgressDialog mLoading;

    public HistoryRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        showLoading();
        View view = inflater.inflate(R.layout.fragment_history_room, container, false);

        rcHistoryRoomList = (RecyclerView) view.findViewById(R.id.rc_history_room);
        rcHistoryRoomList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        rcHistoryRoomList.setLayoutManager(layoutManager);

        roomHistoryRequest();
        return view;
    }


    private void showLoading() {
        mLoading = new ProgressDialog(getContext());
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }


    private void roomHistoryRequest() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<HistoryRoomResponse> historyTrainingResponseCall = api.historyRoomReponseCall(UserPreferences.getKeyUserType(getContext())+" "+UserPreferences.getKeyUserToken(getContext()), "verify");

        historyTrainingResponseCall.enqueue(new Callback<HistoryRoomResponse>() {
            @Override
            public void onResponse(@NotNull Call<HistoryRoomResponse> call, @NotNull Response<HistoryRoomResponse> response) {
                assert response.body() != null;
                if (response.body().getData().getData() != null) {
                    historyRoom = response.body().getData();
                    historyRoomArrayList = response.body().getData().getData();
                    setRecyclerTrainingHistory(historyRoomArrayList);
                    mLoading.dismiss();
                }

            }

            @Override
            public void onFailure(Call<HistoryRoomResponse> call, Throwable t) {
                mLoading.dismiss();
            }
        });
    }

    private void setRecyclerTrainingHistory(ArrayList<HistoryRoom.ReservedRoom> data) {
        HistoryRoomAdapter adapter = new HistoryRoomAdapter(data);
        rcHistoryRoomList.setAdapter(adapter);

        rcHistoryRoomList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisiblesItems = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if ((visibleItemCount + pastVisiblesItems) == totalItemCount) {
                    Log.d("_data_", "Visble : "+(visibleItemCount + pastVisiblesItems)+" dan total item : "+totalItemCount);
                    Log.d("_data_", "Current : "+ historyRoom.getCurrent_page()+" dan last : "+ historyRoom.getLast_page());
                    Log.d("_data_", "Current : "+ pageRoom);
                    if(historyRoom.getLast_page() > historyRoom.getCurrent_page()) {
                        pageRoom++;
                        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
                        Call<HistoryRoomResponse> historyRoomScrollReponseCall = api.historyRoomScrollReponseCall(UserPreferences.getKeyUserType(getContext()) + " " + UserPreferences.getKeyUserToken(getContext()), "verify", String.valueOf(pageRoom));

                        historyRoomScrollReponseCall.enqueue(new Callback<HistoryRoomResponse>() {
                            @Override
                            public void onResponse(Call<HistoryRoomResponse> call, Response<HistoryRoomResponse> response) {
                                historyRoom = response.body().getData();
                                historyRoomArrayList.addAll(response.body().getData().getData());
                                setRecyclerTrainingHistory(historyRoomArrayList);
                            }

                            @Override
                            public void onFailure(Call<HistoryRoomResponse> call, Throwable t) {

                            }
                        });
                    }
                }
            }

        });
    }

}
