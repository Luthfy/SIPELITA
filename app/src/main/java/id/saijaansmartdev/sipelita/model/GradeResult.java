package id.saijaansmartdev.sipelita.model;

import java.util.ArrayList;

public class GradeResult {

    private String type;
    private String event_name;
    private ArrayList<Grade> result;

    public GradeResult(String type, String event_name, ArrayList<Grade> result) {
        this.type = type;
        this.event_name = event_name;
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public ArrayList<Grade> getResult() {
        return result;
    }

    public void setResult(ArrayList<Grade> result) {
        this.result = result;
    }

    public class Grade {
        private String nilai;
        private String name;
        private String keterangan;

        public Grade(String nilai, String name, String keterangan) {
            this.nilai = nilai;
            this.name = name;
            this.keterangan = keterangan;
        }

        public String getNilai() {
            return nilai;
        }

        public void setNilai(String nilai) {
            this.nilai = nilai;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }
    }
}
