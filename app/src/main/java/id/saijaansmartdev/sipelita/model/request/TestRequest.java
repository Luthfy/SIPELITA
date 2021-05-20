package id.saijaansmartdev.sipelita.model.request;

import java.util.List;

import id.saijaansmartdev.sipelita.model.AnswerTest;

public class TestRequest {

    private String event_id;
    private String jumlah_soal;
    private List<AnswerTest> test_lines;

    public TestRequest(String event_id, String jumlah_soal, List<AnswerTest> test_lines) {
        this.event_id = event_id;
        this.jumlah_soal = jumlah_soal;
        this.test_lines = test_lines;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public List<AnswerTest> getTest_lines() {
        return test_lines;
    }

    public void setTest_lines(List<AnswerTest> test_lines) {
        this.test_lines = test_lines;
    }

    public String getJumlah_soal() {
        return jumlah_soal;
    }

    public void setJumlah_soal(String jumlah_soal) {
        this.jumlah_soal = jumlah_soal;
    }
}
