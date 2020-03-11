package id.digilabyte.sipelita.model;

import java.util.ArrayList;

public class Banner {

    private ArrayList<String> banners;

    public Banner(ArrayList<String> banners) {
        this.banners = banners;
    }

    public ArrayList<String> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<String> banners) {
        this.banners = banners;
    }
}
