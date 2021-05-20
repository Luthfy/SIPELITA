package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.Kamar;

public class KamarResponse {

    private String status;
    private Kamar data;

    public KamarResponse(String status, Kamar data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Kamar getData() {
        return data;
    }

    public void setData(Kamar data) {
        this.data = data;
    }
}
