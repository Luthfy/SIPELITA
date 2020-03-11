package id.digilabyte.sipelita.model.response;

import java.util.ArrayList;

import id.digilabyte.sipelita.model.Pelatihan;

public class PelatihanResponse {
    private String status;
    private ArrayList<Pelatihan> data;

    public PelatihanResponse(String status, ArrayList<Pelatihan> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Pelatihan> getData() {
        return data;
    }

    public void setData(ArrayList<Pelatihan> data) {
        this.data = data;
    }
}
