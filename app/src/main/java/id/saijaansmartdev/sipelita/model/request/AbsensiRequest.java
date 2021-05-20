package id.saijaansmartdev.sipelita.model.request;

public class AbsensiRequest {

    private String event_id;

    public AbsensiRequest(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
}
