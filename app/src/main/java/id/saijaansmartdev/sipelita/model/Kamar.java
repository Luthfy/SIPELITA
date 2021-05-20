package id.saijaansmartdev.sipelita.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Kamar {

    private Room room;

    public Kamar(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public class Room {

        private int current_page;
        private ArrayList<RuangKamar> data;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String next_page_url;
        private String path;
        private int per_page;
        private String prev_page_url;
        private int to;
        private int total;

        public Room(int current_page, ArrayList<RuangKamar> data, String first_page_url, int from, int last_page, String last_page_url, String next_page_url, String path, int per_page, String prev_page_url, int to, int total) {
            this.current_page = current_page;
            this.data = data;
            this.first_page_url = first_page_url;
            this.from = from;
            this.last_page = last_page;
            this.last_page_url = last_page_url;
            this.next_page_url = next_page_url;
            this.path = path;
            this.per_page = per_page;
            this.prev_page_url = prev_page_url;
            this.to = to;
            this.total = total;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public ArrayList<RuangKamar> getData() {
            return data;
        }

        public void setData(ArrayList<RuangKamar> data) {
            this.data = data;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public String getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(String prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class RuangKamar implements Parcelable {

        private String id;
        private String building_id;
        private String name;
        private int capacity;
        private int active;
        private String created_at;
        private String updated_at;
        private Bangunan building;

        public RuangKamar(String id, String building_id, String name, int capacity, int active, String created_at, String updated_at, Bangunan building) {
            this.id = id;
            this.building_id = building_id;
            this.name = name;
            this.capacity = capacity;
            this.active = active;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.building = building;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBuilding_id() {
            return building_id;
        }

        public void setBuilding_id(String building_id) {
            this.building_id = building_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Bangunan getBuilding() {
            return building;
        }

        public void setBuilding(Bangunan building) {
            this.building = building;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.building_id);
            dest.writeString(this.name);
            dest.writeInt(this.capacity);
            dest.writeInt(this.active);
            dest.writeString(this.created_at);
            dest.writeString(this.updated_at);
        }

        protected RuangKamar(Parcel in) {
            this.id = in.readString();
            this.building_id = in.readString();
            this.name = in.readString();
            this.capacity = in.readInt();
            this.active = in.readInt();
            this.created_at = in.readString();
            this.updated_at = in.readString();
            this.building = in.readParcelable(Bangunan.class.getClassLoader());
        }

        public static final Parcelable.Creator<RuangKamar> CREATOR = new Parcelable.Creator<RuangKamar>() {
            @Override
            public RuangKamar createFromParcel(Parcel source) {
                return new RuangKamar(source);
            }

            @Override
            public RuangKamar[] newArray(int size) {
                return new RuangKamar[size];
            }
        };
    }

}
