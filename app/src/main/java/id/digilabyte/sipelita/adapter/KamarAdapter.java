package id.digilabyte.sipelita.adapter;

import android.util.Log;
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
import id.digilabyte.sipelita.model.Kamar;
import id.digilabyte.sipelita.ui.RuangActivity;

public class KamarAdapter extends RecyclerView.Adapter<KamarAdapter.MyViewHolder>{

    private ArrayList<Kamar.RuangKamar> kamarList;

    public KamarAdapter(ArrayList<Kamar.RuangKamar> kamarList) {
        this.kamarList = kamarList;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitleKamar, txtDeskripsiKamar, txtStatusKamar, txtTanggalKamar;
        public ImageView imgKamar;
        public CardView cardView;
        public Integer position = 0;

        public MyViewHolder(View view) {
            super(view);
            cardView        = (CardView) view.findViewById(R.id.card_view_kamar);
            txtTitleKamar   = (TextView) view.findViewById(R.id.txt_title_kamar);
            imgKamar        = (ImageView) view.findViewById(R.id.img_kamar);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Kamar.RuangKamar kamar = kamarList.get(position);
                    ArrayList<String> kamarList = ((RuangActivity)view.getContext()).getIdKamar();

                    Log.d("_kamar_reserved", kamar.getId() +" "+kamarList.toString());

                    if (kamarList.contains(kamar.getId())) {
                        cardView.setBackgroundColor(view.getResources().getColor(R.color.colorWhiteForBackground));
                        ((RuangActivity)view.getContext()).removeIdKamar(kamar.getId());
                    } else {
                        cardView.setBackgroundColor(view.getResources().getColor(R.color.colorAccent));
                        ((RuangActivity)view.getContext()).updateIdKamar(kamar.getId());
                    }

                    Log.d("_kamar_reserved_", kamar.getId() +" "+kamarList.toString());


                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kamar, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Kamar.RuangKamar kamar = kamarList.get(position);
        holder.txtTitleKamar.setText(kamar.getName());
        holder.position = position;

        Glide.with(holder.itemView.getContext()).load("image_uri").error(R.drawable.ic_image_grey_24dp).into(holder.imgKamar);
    }


    @Override
    public int getItemCount() {
        return kamarList.size();
    }
}
