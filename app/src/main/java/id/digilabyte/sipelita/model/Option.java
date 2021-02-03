package id.digilabyte.sipelita.model;

public class Option {

    private String option_key;
    private String option_text;

    public Option(String option_key, String option_text) {
        this.option_key = option_key;
        this.option_text = option_text;
    }

    public String getOption_key() {
        return option_key;
    }

    public void setOption_key(String option_key) {
        this.option_key = option_key;
    }

    public String getOption_text() {
        return option_text;
    }

    public void setOption_text(String option_text) {
        this.option_text = option_text;
    }
}