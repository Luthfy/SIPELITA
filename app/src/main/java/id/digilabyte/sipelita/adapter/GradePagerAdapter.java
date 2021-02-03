package id.digilabyte.sipelita.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import id.digilabyte.sipelita.fragment.PostTestGradeFragment;
import id.digilabyte.sipelita.fragment.PretestGradeFragment;

public class GradePagerAdapter extends FragmentPagerAdapter {

    private String UUID = "";
    public GradePagerAdapter(@NonNull FragmentManager fm, String UUID) {
        super(fm);
        this.UUID = UUID;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PretestGradeFragment(UUID);
            case 1:
                return new PostTestGradeFragment(UUID);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
