package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.FormPelatihan;

public class FormResponse {
    private String status;
    private FormPelatihan data;

    public FormResponse(String status, FormPelatihan data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FormPelatihan getData() {
        return data;
    }

    public void setData(FormPelatihan data) {
        this.data = data;
    }
}

