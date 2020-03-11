package id.digilabyte.sipelita.model;

import java.util.ArrayList;

public class Villages {

    private ArrayList<Village> villages;

    public Villages(ArrayList<Village> villages) {
        this.villages = villages;
    }

    public ArrayList<Village> getVillages() {
        return villages;
    }

    public void setVillages(ArrayList<Village> villages) {
        this.villages = villages;
    }

    public class Village {
        private String id;
        private String name;
        private String district_id;

        public Village(String id, String name, String district_id) {
            this.id = id;
            this.name = name;
            this.district_id = district_id;
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

        public String getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(String district_id) {
            this.district_id = district_id;
        }
    }
}
