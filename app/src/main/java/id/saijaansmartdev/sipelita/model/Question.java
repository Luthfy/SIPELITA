package id.saijaansmartdev.sipelita.model;

import java.util.ArrayList;

public class Question {

    private String id;
    private String question;
    private String type;
    private String evaluation_name;
    private ArrayList<Option> options;

    public Question(String id, String question, String type, String evaluation_name, ArrayList<Option> options) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.evaluation_name = evaluation_name;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvaluation_name() {
        return evaluation_name;
    }

    public void setEvaluation_name(String evaluation_name) {
        this.evaluation_name = evaluation_name;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }
}
