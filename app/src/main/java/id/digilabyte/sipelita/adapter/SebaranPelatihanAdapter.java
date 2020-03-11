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

import java.util.ArrayList;

import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.model.Pelatihan;
import id.digilabyte.sipelita.model.Sebaran;
import id.digilabyte.sipelita.ui.SebaranActivity;

public class SebaranPelatihanAdapter extends RecyclerView.Adapter<SebaranPelatihanAdapter.MyViewHolder> {

    private ArrayList<Pelatihan> pelatihanList;

    public SebaranPelatihanAdapter(ArrayList<Pelatihan> pelatihanList) {
        this.pelatihanList = pelatihanList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle, txtDesc, txtStartTime, txtEndTime, txtQtyAndParticipant, txtIncharge, txtParticipant;
        public ImageView imgThumbnail;
        public CardView cardView;
        public Integer position = 0;

        public MyViewHolder(View view) {
            super(view);
            txtTitle        = (TextView) view.findViewById(R.id.txt_title_sebaran);
            txtStartTime    = (TextView) view.findViewById(R.id.txt_tanggal_mulai_peltihan);
            txtEndTime      = (TextView) view.findViewById(R.id.txt_tanggal_akhir_peltihan);
            txtQtyAndParticipant = (TextView) view.findViewById(R.id.txt_qty_sebaran);
            cardView        = (CardView) view.findViewById(R.id.card_view_sebaran);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sebaran, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SebaranPelatihanAdapter.MyViewHolder holder, int position) {
        Pelatihan pelatihan = pelatihanList.get(position);
        holder.position = position;
        holder.txtTitle.setText(pelatihan.getTitle());
        holder.txtStartTime.setText(pelatihan.getStart_time());
        holder.txtEndTime.setText(pelatihan.getEnd_time());
        holder.txtQtyAndParticipant.setText(""+pelatihan.getAccept_participant());

        ArrayList<Sebaran> sebaran = pelatihan.getParticipants();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pelatihan pelatihan = pelatihanList.get(position);
                Intent intent = new Intent(view.getContext(), SebaranActivity.class);
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
