package id.digilabyte.sipelita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.digilabyte.sipelita.R;

public class SlideBannerAdapter extends PagerAdapter {

    private ArrayList<String> images;
    private LayoutInflater layoutInflater;
    private Context context;

    public SlideBannerAdapter(@NonNull Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = layoutInflater.inflate(R.layout.slide_small_banner, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.img_small_banner);

        Glide.with(context).load(images.get(position)).into(imageView);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
