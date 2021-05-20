package id.saijaansmartdev.sipelita.model;

import java.util.ArrayList;

public class Bangunan {

    private String id;
    private String name;
    private String code;
    private String desc;
    private String type;
    private String additional_fee;
    private String apply_if;
    private String single_room;
    private String public_price;
    private String company_price;
    private String facilities;
    private String created_at;
    private String updated_at;
    private Media media;

    public Bangunan(String id, String name, String code, String desc, String type, String additional_fee, String apply_if, String single_room, String public_price, String company_price, String facilities, String created_at, String updated_at, Media media) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.desc = desc;
        this.type = type;
        this.additional_fee = additional_fee;
        this.apply_if = apply_if;
        this.single_room = single_room;
        this.public_price = public_price;
        this.company_price = company_price;
        this.facilities = facilities;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.media = media;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdditional_fee() {
        return additional_fee;
    }

    public void setAdditional_fee(String additional_fee) {
        this.additional_fee = additional_fee;
    }

    public String getApply_if() {
        return apply_if;
    }

    public void setApply_if(String apply_if) {
        this.apply_if = apply_if;
    }

    public String getSingle_room() {
        return single_room;
    }

    public void setSingle_room(String single_room) {
        this.single_room = single_room;
    }

    public String getPublic_price() {
        return public_price;
    }

    public void setPublic_price(String public_price) {
        this.public_price = public_price;
    }

    public String getCompany_price() {
        return company_price;
    }

    public void setCompany_price(String company_price) {
        this.company_price = company_price;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
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

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public class Media {

        ArrayList<String> building;
        ArrayList<String> room;

        public Media(ArrayList<String> building, ArrayList<String> room) {
            this.building = building;
            this.room = room;
        }

        public ArrayList<String> getBuilding() {
            return building;
        }

        public void setBuilding(ArrayList<String> building) {
            this.building = building;
        }

        public ArrayList<String> getRoom() {
            return room;
        }

        public void setRoom(ArrayList<String> room) {
            this.room = room;
        }
    }
}
