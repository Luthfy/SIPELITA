package id.digilabyte.sipelita.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import id.digilabyte.sipelita.fragment.HistoryRoomFragment;
import id.digilabyte.sipelita.fragment.HistoryTrainingFragment;

public class HistoryPagerAdapter extends FragmentPagerAdapter {

    public HistoryPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HistoryTrainingFragment();
            case 1:
                return new HistoryRoomFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
