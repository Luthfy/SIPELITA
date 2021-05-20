package id.saijaansmartdev.sipelita.model;

import java.util.ArrayList;

public class Test {

    private Integer jumlah_soal;
    private String type_test;
    private ArrayList<Question> soal;

    public Test(Integer jumlah_soal, String type_test, ArrayList<Question> soal) {
        this.jumlah_soal = jumlah_soal;
        this.type_test = type_test;
        this.soal = soal;
    }

    public Integer getJumlah_soal() {
        return jumlah_soal;
    }

    public void setJumlah_soal(Integer jumlah_soal) {
        this.jumlah_soal = jumlah_soal;
    }

    public String getType_test() {
        return type_test;
    }

    public void setType_test(String type_test) {
        this.type_test = type_test;
    }

    public ArrayList<Question> getSoal() {
        return soal;
    }

    public void setSoal(ArrayList<Question> soal) {
        this.soal = soal;
    }
}
