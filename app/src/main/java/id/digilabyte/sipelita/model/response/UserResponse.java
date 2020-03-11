package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.User;

public class UserResponse {
    private String status;
    private User data;

    public UserResponse(String status, User data) {
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