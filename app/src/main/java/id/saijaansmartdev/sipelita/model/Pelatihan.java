package id.saijaansmartdev.sipelita.model;

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
    private String nilai_pretest;
    private String nilai_posttest;
    private boolean evaluation;
    private boolean is_registered;

    public Pelatihan(String id, String start_time, String end_time, String title, String desc, String incharge, String price, Integer quota, String location, String created_at, Integer accept_participant, String poster, ArrayList<Sebaran> participants, String nilai_pretest, String nilai_posttest, boolean evaluation, boolean is_registered) {
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
        this.nilai_pretest = nilai_pretest;
        this.nilai_posttest = nilai_posttest;
        this.evaluation = evaluation;
        this.is_registered = is_registered;
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

    public String getNilai_pretest() {
        return nilai_pretest;
    }

    public void setNilai_pretest(String nilai_pretest) {
        this.nilai_pretest = nilai_pretest;
    }

    public String getNilai_posttest() {
        return nilai_posttest;
    }

    public void setNilai_posttest(String nilai_posttest) {
        this.nilai_posttest = nilai_posttest;
    }

    public boolean isEvaluation() {
        return evaluation;
    }

    public void setEvaluation(boolean evaluation) {
        this.evaluation = evaluation;
    }

    public boolean isIs_registered() {
        return is_registered;
    }

    public void setIs_registered(boolean is_registered) {
        this.is_registered = is_registered;
    }
}
