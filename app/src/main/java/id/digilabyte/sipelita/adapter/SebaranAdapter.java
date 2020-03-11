package id.digilabyte.sipelita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.model.Sebaran;


public class SebaranAdapter extends RecyclerView.Adapter<SebaranAdapter.MyViewHolder>  {

    private Context context;
    private ArrayList<Sebaran> sebaranArrayList;

    public SebaranAdapter(Context context, ArrayList<Sebaran> sebaranArrayList) {
        this.context = context;
        this.sebaranArrayList = sebaranArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtProv, txtKab, txtKec, txtKel, txtJumlah;
        public MyViewHolder(View view) {
            super(view);
//            txtProv = (TextView) view.findViewById(R.id.provinsi_sebaran);
//            txtKab = (TextView) view.findViewById(R.id.kab_sebaran);
//            txtKec = (TextView) view.findViewById(R.id.kec_sebaran);
            txtKel = (TextView) view.findViewById(R.id.kel_sebaran);
            txtJumlah = (TextView) view.findViewById(R.id.jumlah_sebaran);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sebarang, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SebaranAdapter.MyViewHolder holder, int position) {
        Sebaran sebaran = sebaranArrayList.get(position);
//        holder.txtProv.setText(sebaran.getProvince());
//        holder.txtKab.setText(sebaran.getCity());
//        holder.txtKec.setText(sebaran.getDistrict());
        holder.txtKel.setText(sebaran.getVillage()+" - "+sebaran.getCity());
        holder.txtJumlah.setText(sebaran.getTotal().toString());
    }

    @Override
    public int getItemCount() {
        return sebaranArrayList.size();
    }
}
