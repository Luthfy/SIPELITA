package id.digilabyte.sipelita.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.model.Transaksi;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.MyViewHolder>  {

    private ArrayList<Transaksi.TransaksiList> data;

    public TransaksiAdapter(ArrayList<Transaksi.TransaksiList> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transaksi, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Transaksi.TransaksiList transaksi = data.get(position);

        holder.txtTitle.setText(transaksi.getCode_prefix()+"-"+transaksi.getCode_suffix());
        holder.txtStatus.setText(transaksi.getInvoiced_type()+" ("+transaksi.getInvoiced().getName()+")");
        holder.txtDate.setText(transaksi.getUpdated_at());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtStatus, txtDate;
        public MyViewHolder(View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.txt_title_transaksi);
            txtStatus = (TextView) view.findViewById(R.id.txt_status_transaksi);
            txtDate = (TextView) view.findViewById(R.id.txt_tanggal_transaksi);
        }
    }
}
