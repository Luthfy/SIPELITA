package id.digilabyte.sipelita.model.request;

public class BangunanRequest {

    private String date;
    private String building_id;

    public BangunanRequest(String date, String building_id) {
        this.date = date;
        this.building_id = building_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }
}
