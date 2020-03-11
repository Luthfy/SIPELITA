package id.digilabyte.sipelita.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.model.Pelatihan;
import id.digilabyte.sipelita.model.Sebaran;
import id.digilabyte.sipelita.ui.DetailActivity;

public class PelatihanAdapter extends RecyclerView.Adapter<PelatihanAdapter.MyViewHolder> {

    private ArrayList<Pelatihan> pelatihanList;

    public PelatihanAdapter(ArrayList<Pelatihan> pelatihanList) {
        this.pelatihanList = pelatihanList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle, txtDesc, txtStartTime, txtEndTime, txtQtyAndParticipant, txtIncharge, txtParticipant;
        public ImageView imgThumbnail;
        public CardView cardView;
        public Integer position = 0;

        public MyViewHolder(View view) {
            super(view);
            txtTitle        = (TextView) view.findViewById(R.id.txt_title_pelatihan);
            txtDesc         = (TextView) view.findViewById(R.id.txt_deskripsi_pelatihan);
            txtStartTime    = (TextView) view.findViewById(R.id.txt_tanggal_mulai_peltihan);
            txtEndTime      = (TextView) view.findViewById(R.id.txt_tanggal_akhir_peltihan);
            txtQtyAndParticipant = (TextView) view.findViewById(R.id.txt_qty_pelatihan);
            txtIncharge     = (TextView) view.findViewById(R.id.txt_panitia_pelatihan);
            imgThumbnail    = (ImageView) view.findViewById(R.id.img_thumbnail_pelatihan);
            cardView        = (CardView) view.findViewById(R.id.card_view_pelatihan);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pelatihan, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PelatihanAdapter.MyViewHolder holder, int position) {
        Pelatihan pelatihan = pelatihanList.get(position);
        holder.position = position;
        holder.txtTitle.setText(pelatihan.getTitle());
        holder.txtDesc.setText(pelatihan.getDesc());
        holder.txtStartTime.setText(pelatihan.getStart_time());
        holder.txtEndTime.setText(pelatihan.getEnd_time());
        holder.txtQtyAndParticipant.setText(pelatihan.getQuota()+"/"+pelatihan.getAccept_participant());
        holder.txtIncharge.setText("Panitia : "+pelatihan.getIncharge());

        Glide.with(holder.itemView.getContext())
                .load(pelatihan.getPoster())
                .into(holder.imgThumbnail);

        ArrayList<Sebaran> sebaran = pelatihan.getParticipants();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pelatihan pelatihan = pelatihanList.get(position);
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("PELATIHAN", pelatihan.getId());
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return pelatihanList.size();
    }
}
