package id.digilabyte.sipelita.model;

public class Terdaftar {

    private String event_id;
    private String status;

    public Terdaftar(String event_id, String status) {
        this.event_id = event_id;
        this.status = status;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
