package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.Educations;

public class EducationResponse {

    private String status;
    private Educations data;

    public EducationResponse(String status, Educations data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Educations getData() {
        return data;
    }

    public void setData(Educations data) {
        this.data = data;
    }
}
