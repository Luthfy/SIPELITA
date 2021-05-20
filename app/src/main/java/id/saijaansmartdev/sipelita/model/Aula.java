package id.saijaansmartdev.sipelita.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Aula implements Parcelable {

    private String id;
    private String title;
    private String status;
    private String created_at;

    public Aula(String id, String title, String status, String created_at) {
        this.id = id;
        this.title = title;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        dest.writeString(this.status);
        dest.writeString(this.created_at);
    }

    protected Aula(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.status = in.readString();
        this.created_at = in.readString();
    }

    public static final Parcelable.Creator<Aula> CREATOR = new Parcelable.Creator<Aula>() {
        @Override
        public Aula createFromParcel(Parcel source) {
            return new Aula(source);
        }

        @Override
        public Aula[] newArray(int size) {
            return new Aula[size];
        }
    };
}
