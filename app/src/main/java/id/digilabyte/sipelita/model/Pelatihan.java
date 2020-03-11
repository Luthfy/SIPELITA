package id.digilabyte.sipelita.model;

import java.util.ArrayList;

public class Pelatihan  {

    private String id;
    private String start_time;
    private String end_time;
    private String title;
    private String desc;
    private String incharge;
    private String price;
    private Integer quota;
    private String location;
    private String created_at;
    private Integer accept_participant;
    private String poster;
    private ArrayList<Sebaran> participants;

    public Pelatihan(String id, String start_time, String end_time, String title, String desc, String incharge, String price, Integer quota, String location, String created_at, Integer accept_participant, String poster, ArrayList<Sebaran> participants) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.title = title;
        this.desc = desc;
        this.incharge = incharge;
        this.price = price;
        this.quota = quota;
        this.location = location;
        this.created_at = created_at;
        this.accept_participant = accept_participant;
        this.poster = poster;
        this.participants = participants;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIncharge() {
        return incharge;
    }

    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
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

    public Integer getAccept_participant() {
        return accept_participant;
    }

    public void setAccept_participant(Integer accept_participant) {
        this.accept_participant = accept_participant;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public ArrayList<Sebaran> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Sebaran> participants) {
        this.participants = participants;
    }
}
