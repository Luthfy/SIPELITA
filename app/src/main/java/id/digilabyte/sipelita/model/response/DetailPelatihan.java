package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.Pelatihan;

public class DetailPelatihan {

    private String status;
    private Pelatihan data;

    public DetailPelatihan(String status, Pelatihan data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pelatihan getData() {
        return data;
    }

    public void setData(Pelatihan data) {
        this.data = data;
    }
}
