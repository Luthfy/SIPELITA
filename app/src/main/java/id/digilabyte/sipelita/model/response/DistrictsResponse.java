package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.Districts;

public class DistrictsResponse {

    private String status;
    private Districts data;

    public DistrictsResponse(String status, Districts data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Districts getData() {
        return data;
    }

    public void setData(Districts data) {
        this.data = data;
    }
}
