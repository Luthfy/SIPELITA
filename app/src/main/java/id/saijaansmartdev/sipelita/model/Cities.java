package id.saijaansmartdev.sipelita.model;

import java.util.ArrayList;

public class Cities {

    private ArrayList<City> cities;

    public Cities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public class City {

        private String id;
        private String name;
        private String province_id;

        public City(String id, String name, String province_id) {
            this.id = id;
            this.name = name;
            this.province_id = province_id;
        }

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

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }
    }

}
