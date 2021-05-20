package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.Villages;

public class VillagesResponse {

    private String status;
    private Villages data;

    public VillagesResponse(String status, Villages data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Villages getData() {
        return data;
    }

    public void setData(Villages data) {
        this.data = data;
    }
}
