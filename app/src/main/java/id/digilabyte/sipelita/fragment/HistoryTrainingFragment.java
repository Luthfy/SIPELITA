package id.digilabyte.sipelita.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.adapter.HistoryTrainingAdapter;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.helper.UserPreferences;
import id.digilabyte.sipelita.model.HistoryTraining;
import id.digilabyte.sipelita.model.response.HistoryTrainingResponse;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryTrainingFragment extends Fragment {

    private RecyclerView rcHistoryTrainingList;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<HistoryTraining.Training> historyTrainingArrayList;
    private HistoryTraining historyData;
    private int page = 1;

    public HistoryTrainingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history_training, container, false);

        rcHistoryTrainingList = (RecyclerView) view.findViewById(R.id.rc_history_training);
        rcHistoryTrainingList.setHasFixedSize(true);

        trainingHistoryRequest();
        return view;
    }

    private void trainingHistoryRequest() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<HistoryTrainingResponse> historyTrainingResponseCall = api.historyTrainingResponseCall(UserPreferences.getKeyUserType(getContext())+" "+UserPreferences.getKeyUserToken(getContext()), "terima");

        historyTrainingResponseCall.enqueue(new Callback<HistoryTrainingResponse>() {
            @Override
            public void onResponse(Call<HistoryTrainingResponse> call, Response<HistoryTrainingResponse> response) {
                historyData = response.body().getData();
                historyTrainingArrayList = historyData.getData();
                setRecyclerTrainingHistory(historyTrainingArrayList);
            }

            @Override
            public void onFailure(Call<HistoryTrainingResponse> call, Throwable t) {

            }
        });
    }

    private void setRecyclerTrainingHistory(ArrayList<HistoryTraining.Training> data) {

        Log.d("_data_", String.valueOf(data.size()));

        layoutManager = new LinearLayoutManager(getContext());
        rcHistoryTrainingList.setLayoutManager(layoutManager);

        HistoryTrainingAdapter adapter = new HistoryTrainingAdapter(data);
        rcHistoryTrainingList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        rcHistoryTrainingList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisiblesItems = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if ((visibleItemCount + pastVisiblesItems) == totalItemCount) {
                    Log.d("_data_", "Visble : "+(visibleItemCount + pastVisiblesItems)+" dan total item : "+totalItemCount);
                    Log.d("_data_", "Current : "+historyData.getCurrent_page()+" dan last : "+historyData.getLast_page());
                    Log.d("_data_", "Current : "+page);
                    if(historyData.getLast_page() > historyData.getCurrent_page()) {
                        page++;
                        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
                        Call<HistoryTrainingResponse> historyTrainingScrollResponseCall = api.historyTrainingScrollResponseCall(UserPreferences.getKeyUserType(getContext()) + " " + UserPreferences.getKeyUserToken(getContext()), "terima", String.valueOf(page));

                        historyTrainingScrollResponseCall.enqueue(new Callback<HistoryTrainingResponse>() {
                            @Override
                            public void onResponse(Call<HistoryTrainingResponse> call, Response<HistoryTrainingResponse> response) {
                                historyData = response.body().getData();
                                historyTrainingArrayList.addAll(response.body().getData().getData());
                                setRecyclerTrainingHistory(historyTrainingArrayList);
                            }

                            @Override
                            public void onFailure(Call<HistoryTrainingResponse> call, Throwable t) {

                            }
                        });
                    }
                }
            }

        });
    }

}
