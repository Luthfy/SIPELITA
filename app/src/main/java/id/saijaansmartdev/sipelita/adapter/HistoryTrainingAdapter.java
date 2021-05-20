package id.saijaansmartdev.sipelita.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.model.HistoryTraining;

public class HistoryTrainingAdapter extends RecyclerView.Adapter<HistoryTrainingAdapter.MyViewHolder>  {

    private ArrayList<HistoryTraining.Training> data;
    private Integer mCurrentPosition;

    public HistoryTrainingAdapter(ArrayList<HistoryTraining.Training> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HistoryTraining.Training training = data.get(position);
        holder.txtTitle.setText(training.getEvent().getTitle());
        holder.txtStatus.setText(training.getStatus());
        holder.txtTanggal.setText(training.getUpdated_at());
        mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtStatus, txtTanggal;
        public MyViewHolder(View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.txt_title_history);
            txtStatus = (TextView) view.findViewById(R.id.txt_status_history);
            txtTanggal = (TextView) view.findViewById(R.id.txt_tanggal_history);
        }
    }

    public void addItem(ArrayList<HistoryTraining.Training> data) {
        data.addAll(data);
    }
}
