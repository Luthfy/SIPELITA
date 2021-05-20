package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.GradeResult;

public class GradeResultResponse {

    private String status;
    private GradeResult data;

    public GradeResultResponse(String status, GradeResult data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GradeResult getData() {
        return data;
    }

    public void setData(GradeResult data) {
        this.data = data;
    }
}
