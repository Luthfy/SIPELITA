package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.Terdaftar;

public class RegistrasiPelatihanResponse {

    private String status;
    private String message;
    private Terdaftar data;

    public RegistrasiPelatihanResponse(String status, String message, Terdaftar data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Terdaftar getData() {
        return data;
    }

    public void setData(Terdaftar data) {
        this.data = data;
    }
}
