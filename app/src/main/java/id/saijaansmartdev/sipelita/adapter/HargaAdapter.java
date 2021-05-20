package id.saijaansmartdev.sipelita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.model.Bangunan;

public class HargaAdapter extends RecyclerView.Adapter<HargaAdapter.ViewHolder> {

    private ArrayList<Bangunan> bangunanList;
    private Context mContext;

    public HargaAdapter(ArrayList<Bangunan> bangunanList, Context mContext) {
        this.bangunanList = bangunanList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_harga, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bangunan bangunan = bangunanList.get(position);

        DecimalFormat format = new DecimalFormat("Rp #,###,###");
        String CompanyPrice = format.format(Double.parseDouble(bangunan.getCompany_price()));
        String PublicPrice  = format.format(Double.parseDouble(bangunan.getPublic_price()));

        holder.txtNamaBangunan.setText(bangunan.getName());
        holder.txtHargaInstansi.setText("Harga Instansi : \n"+CompanyPrice);
        holder.txtHargaPublik.setText("Harga Umum : \n"+PublicPrice);
        holder.txtFasilitasBangunan.setText("Fasilitas : \n"+bangunan.getFacilities().replaceAll(";", ", "));
    }

    @Override
    public int getItemCount() {
        return bangunanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaBangunan, txtHargaPublik, txtHargaInstansi, txtFasilitasBangunan;
        public ViewHolder(View view) {
            super(view);
            txtNamaBangunan = view.findViewById(R.id.txt_nama_bangunan_harga);
            txtHargaInstansi = view.findViewById(R.id.txt_instansi_harga);
            txtHargaPublik = view.findViewById(R.id.txt_public_harga);
            txtFasilitasBangunan = view.findViewById(R.id.txt_fasilitas_harga);
        }
    }
}
