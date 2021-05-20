package id.saijaansmartdev.sipelita.adapter;

import android.content.Context;
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

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.model.Bangunan;
import id.saijaansmartdev.sipelita.ui.BangunanActivity;

public class BangunanAdapter extends RecyclerView.Adapter<BangunanAdapter.ViewHolder> {

    private ArrayList<Bangunan> bangunanList;
    private Context mContext;

    public BangunanAdapter(ArrayList<Bangunan> bangunanList, Context mContext) {
        this.bangunanList   = bangunanList;
        this.mContext       = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBangunanList;
        private TextView txtNamaBangunan;
        private CardView cardViewBangunan;
        private int position;

        public ViewHolder(View view) {
            super(view);
            cardViewBangunan        = (CardView) view.findViewById(R.id.card_view_bangunan_list);
            imgBangunanList = (ImageView) view.findViewById(R.id.img_bangunan_list);
            txtNamaBangunan = (TextView) view.findViewById(R.id.txt_nama_bangunan_list);

            cardViewBangunan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BangunanActivity)mContext).setBangunanDetail(bangunanList.get(position));
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bangunan, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bangunan bangunan = bangunanList.get(position);
        holder.txtNamaBangunan.setText(bangunan.getName());

        Glide.with(holder.itemView.getContext()).load(bangunan.getMedia().getBuilding().get(0)).into(holder.imgBangunanList);

        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return bangunanList.size();
    }
    
}

//    public BangunanAdapter(Context context, ArrayList values, ItemListener itemListener) {
//        bangunanList = values;
//        mContext = context;
//        mListener=itemListener;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        public TextView textView;
//        public ImageView imageView;
//        public RelativeLayout relativeLayout;
//        DataModel item;
//
//        public ViewHolder(View v) {
//
//            super(v);
//
//            v.setOnClickListener(this);
//            textView = (TextView) v.findViewById(R.id.textView);
//            imageView = (ImageView) v.findViewById(R.id.imageView);
//            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
//
//        }
//
//        public void setData(DataModel item) {
//            this.item = item;
//
//            textView.setText(item.text);
//            imageView.setImageResource(item.drawable);
//            relativeLayout.setBackgroundColor(Color.parseColor(item.color));
//
//        }
//
//
//        @Override
//        public void onClick(View view) {
//            if (mListener != null) {
//                mListener.onItemClick(item);
//            }
//        }
//    }
//
//    @Override
//    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder Vholder, int position) {
//        Vholder.setData(bangunanList.get(position));
//
//    }
//
//    @Override
//    public int getItemCount() {
//
//        return bangunanList.size();
//    }
//
//    public interface ItemListener {
//        void onItemClick(DataModel item);
//    }
