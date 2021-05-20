package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.Login;

public class LoginResponse {

    private String status;
    private Login data;

    public LoginResponse(String status, Login data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Login getData() {
        return data;
    }

    public void setData(Login data) {
        this.data = data;
    }
}
