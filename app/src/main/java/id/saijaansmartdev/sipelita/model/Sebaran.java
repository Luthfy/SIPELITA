package id.saijaansmartdev.sipelita.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Sebaran implements Parcelable {

    private String province;
    private String city;
    private String district;
    private String village;
    private Integer total;

    public Sebaran(String province, String city, String district, String village, Integer total) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.village = village;
        this.total = total;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.district);
        dest.writeString(this.village);
        dest.writeValue(this.total);
    }

    protected Sebaran(Parcel in) {
        this.province = in.readString();
        this.city = in.readString();
        this.district = in.readString();
        this.village = in.readString();
        this.total = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Sebaran> CREATOR = new Parcelable.Creator<Sebaran>() {
        @Override
        public Sebaran createFromParcel(Parcel source) {
            return new Sebaran(source);
        }

        @Override
        public Sebaran[] newArray(int size) {
            return new Sebaran[size];
        }
    };
}
