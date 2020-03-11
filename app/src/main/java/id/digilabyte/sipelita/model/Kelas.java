package id.digilabyte.sipelita.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Kelas implements Parcelable {

    private String id;
    private String title;
    private String time;
    private String status;
    private String poster_path;
    private String location;
    private String created_at;

    public Kelas(String id, String title, String time, String status, String poster_path, String location, String created_at) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.status = status;
        this.poster_path = poster_path;
        this.location = location;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.time);
        dest.writeString(this.status);
        dest.writeString(this.poster_path);
        dest.writeString(this.location);
        dest.writeString(this.created_at);
    }

    protected Kelas(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.time = in.readString();
        this.status = in.readString();
        this.poster_path = in.readString();
        this.location = in.readString();
        this.created_at = in.readString();
    }

    public static final Parcelable.Creator<Kelas> CREATOR = new Parcelable.Creator<Kelas>() {
        @Override
        public Kelas createFromParcel(Parcel source) {
            return new Kelas(source);
        }

        @Override
        public Kelas[] newArray(int size) {
            return new Kelas[size];
        }
    };
}
