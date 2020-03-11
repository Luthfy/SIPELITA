package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.Transaksi;

public class TransaksiResponse {

    private String status;
    private Transaksi data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Transaksi getData() {
        return data;
    }

    public void setData(Transaksi data) {
        this.data = data;
    }

    public TransaksiResponse(String status, Transaksi data) {
        this.status = status;
        this.data = data;
    }
}
