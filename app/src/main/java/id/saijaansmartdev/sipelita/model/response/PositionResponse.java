package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.Positions;

public class PositionResponse {

    private String status;
    private Positions data;

    public PositionResponse(String status, Positions data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Positions getData() {
        return data;
    }

    public void setData(Positions data) {
        this.data = data;
    }
}
