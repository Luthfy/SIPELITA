package id.digilabyte.sipelita.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.digilabyte.sipelita.fragment.PemesananRoomFragment;
import id.digilabyte.sipelita.fragment.PemesananTrainingFragment;

public class PemesananPagerAdapter extends FragmentPagerAdapter {

    public PemesananPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PemesananTrainingFragment();
            case 1:
                return new PemesananRoomFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
