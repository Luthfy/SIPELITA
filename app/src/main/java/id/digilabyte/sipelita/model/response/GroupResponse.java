package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.Groups;

public class GroupResponse {

    private String status;
    private Groups data;

    public GroupResponse(String status, Groups data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Groups getData() {
        return data;
    }

    public void setData(Groups data) {
        this.data = data;
    }
}
