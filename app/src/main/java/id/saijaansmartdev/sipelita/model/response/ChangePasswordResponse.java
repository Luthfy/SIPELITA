package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.User;

public class ChangePasswordResponse {

    private String status;
    private User data;

    public ChangePasswordResponse(String status, User data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
