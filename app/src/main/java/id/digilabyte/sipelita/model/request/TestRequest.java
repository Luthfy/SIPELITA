package id.digilabyte.sipelita.model.request;

import java.util.ArrayList;
import java.util.List;

import id.digilabyte.sipelita.model.AnswerTest;

public class TestRequest {

    private String event_id;
    private List<AnswerTest> test_lines;

    public TestRequest(String event_id, List<AnswerTest> test_lines) {
        this.event_id = event_id;
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
}
