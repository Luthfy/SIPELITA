package id.digilabyte.sipelita.model.response;

public class RuangResponse {

    private String status;
    private String message = "";

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

    public RuangResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
