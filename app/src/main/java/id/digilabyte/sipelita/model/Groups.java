package id.digilabyte.sipelita.model;

import java.util.ArrayList;

public class Groups {

    private ArrayList<Group> groups;

    public Groups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public class Group {
        private String id;
        private String code;
        private String name;

        public Group(String id, String code, String name) {
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
