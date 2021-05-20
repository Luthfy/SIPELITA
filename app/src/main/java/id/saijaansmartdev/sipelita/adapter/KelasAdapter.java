package id.saijaansmartdev.sipelita.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.model.Kelas;
import id.saijaansmartdev.sipelita.ui.DetailActivity;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.MyViewHolder> {

    private ArrayList<Kelas> kelasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public Integer position = 0;

        public MyViewHolder(View view) {
            super(view);
            cardView        = (CardView) view.findViewById(R.id.card_view_kelas);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Kelas kelas = kelasList.get(position);
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
//                    intent.putExtra("KELAS", kelas);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kelas, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
