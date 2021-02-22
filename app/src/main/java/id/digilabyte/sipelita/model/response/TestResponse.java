package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.Test;

public class TestResponse {

    private String status;
    private String message;
    private Test data;

    public TestResponse(String status, String message,Test data) {
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

    public Test getData() {
        return data;
    }

    public void setData(Test data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
