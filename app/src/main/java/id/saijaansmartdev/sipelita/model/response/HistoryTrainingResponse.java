package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.HistoryTraining;

public class HistoryTrainingResponse {

    private String status;
    private HistoryTraining data;

    public HistoryTrainingResponse(String status, HistoryTraining data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HistoryTraining getData() {
        return data;
    }

    public void setData(HistoryTraining data) {
        this.data = data;
    }
}
