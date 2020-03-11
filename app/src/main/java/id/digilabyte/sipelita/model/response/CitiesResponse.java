package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.Cities;

public class CitiesResponse {

    private String status;
    private Cities data;

    public CitiesResponse(String status, Cities data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cities getData() {
        return data;
    }

    public void setData(Cities data) {
        this.data = data;
    }
}
