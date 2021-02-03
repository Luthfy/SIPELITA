package id.digilabyte.sipelita.model;

public class AnswerTest {
    private String id;
    private String answer;
    private String type;

    public AnswerTest(String id, String answer, String type) {
        this.id = id;
        this.answer = answer;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
