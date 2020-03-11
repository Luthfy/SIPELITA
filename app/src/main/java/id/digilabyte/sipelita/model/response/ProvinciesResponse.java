package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.Provincies;

public class ProvinciesResponse {

    private String status;
    private Provincies data;

    public ProvinciesResponse(String status, Provincies data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Provincies getData() {
        return data;
    }

    public void setData(Provincies data) {
        this.data = data;
    }
}
