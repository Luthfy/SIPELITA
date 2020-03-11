package id.digilabyte.sipelita.model;

import java.util.ArrayList;

public class Educations {

    private ArrayList<Education> educations;

    public ArrayList<Education> getEducations() {
        return educations;
    }

    public void setEducations(ArrayList<Education> educations) {
        this.educations = educations;
    }

    public Educations(ArrayList<Education> educations) {
        this.educations = educations;
    }

    public class Education {

        private String id;
        private String code;
        private String name;

        public Education(String id, String code, String name) {
            this.id = id;
            this.code = code;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
