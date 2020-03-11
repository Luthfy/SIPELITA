package id.digilabyte.sipelita.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.model.HistoryRoom;

public class HistoryRoomAdapter extends RecyclerView.Adapter<HistoryRoomAdapter.MyViewHolder>  {

    private ArrayList<HistoryRoom.ReservedRoom> data;

    public HistoryRoomAdapter(ArrayList<HistoryRoom.ReservedRoom> data) {
        Log.d("_history_room", String.valueOf(data.size()));
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
        HistoryRoom.ReservedRoom training = data.get(position);
        holder.txtTitle.setText(training.getCode());
        holder.txtStatus.setText(training.getStatus());
        holder.txtTanggal.setText(training.getUpdated_at());
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
}
