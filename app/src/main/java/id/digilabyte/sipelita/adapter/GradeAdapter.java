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
import id.digilabyte.sipelita.model.GradeResult;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.MyViewHolder> {

    private ArrayList<GradeResult.Grade> grades;

    public GradeAdapter(ArrayList<GradeResult.Grade> grades) {
        this.grades = grades;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName, txtNilai;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName  = (TextView) itemView.findViewById(R.id.txt_nama_peserta);
            txtNilai = (TextView) itemView.findViewById(R.id.txt_nilai_peserta);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_nilai_grade, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(grades.get(position).getName());
        holder.txtNilai.setText(grades.get(position).getNilai() + " (" + grades.get(position).getKeterangan() + ")");
    }

    @Override
    public int getItemCount() {
        return grades.size();
    }


}
