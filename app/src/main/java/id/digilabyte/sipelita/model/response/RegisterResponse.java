package id.digilabyte.sipelita.model.response;

import java.util.ArrayList;

public class RegisterResponse {

    private String status;
    private ArrayList<String> message;

    public RegisterResponse(String status, ArrayList<String> message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }
}
