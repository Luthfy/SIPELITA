package id.digilabyte.sipelita.model.response;

import id.digilabyte.sipelita.model.HistoryRoom;

public class HistoryRoomResponse {

    private String status;
    private HistoryRoom data;

    public HistoryRoomResponse(String status, HistoryRoom data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HistoryRoom getData() {
        return data;
    }

    public void setData(HistoryRoom data) {
        this.data = data;
    }
}
