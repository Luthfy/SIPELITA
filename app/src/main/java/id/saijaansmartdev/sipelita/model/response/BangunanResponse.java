package id.saijaansmartdev.sipelita.model.response;

import java.util.ArrayList;

import id.saijaansmartdev.sipelita.model.Bangunan;

public class BangunanResponse {

    private String status;
    private ArrayList<Bangunan> data;

    public BangunanResponse(String status, ArrayList<Bangunan> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Bangunan> getData() {
        return data;
    }

    public void setData(ArrayList<Bangunan> data) {
        this.data = data;
    }
}
