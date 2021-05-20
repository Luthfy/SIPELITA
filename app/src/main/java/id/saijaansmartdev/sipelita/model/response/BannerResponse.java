package id.saijaansmartdev.sipelita.model.response;

import id.saijaansmartdev.sipelita.model.Banner;

public class BannerResponse {

    private String status;
    private Banner data;

    public BannerResponse(String status, Banner data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Banner getData() {
        return data;
    }

    public void setData(Banner data) {
        this.data = data;
    }
}
