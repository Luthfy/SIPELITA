package id.saijaansmartdev.sipelita.model.response;

import java.util.ArrayList;

import id.saijaansmartdev.sipelita.model.FormPelatihan;

public class FormResponse {
    private String status;
    private FormPelatihan data;

    public FormResponse(String status, FormPelatihan data, ArrayList<String> message) {
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

