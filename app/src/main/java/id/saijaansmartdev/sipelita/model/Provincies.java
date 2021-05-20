package id.saijaansmartdev.sipelita.model;

import java.util.ArrayList;

public class Provincies {

    private ArrayList<Provincy> provinces;

    public Provincies(ArrayList<Provincy> provinces) {
        this.provinces = provinces;
    }

    public ArrayList<Provincy> getProvinces() {
        return provinces;
    }

    public void setProvinces(ArrayList<Provincy> provinces) {
        this.provinces = provinces;
    }

    public class Provincy {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
