package id.digilabyte.sipelita.model;

import java.util.ArrayList;

public class Positions {

    private ArrayList<Position> positions;

    public Positions(ArrayList<Position> positions) {
        this.positions = positions;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }

    public class Position {

        private String id;
        private String code;
        private String name;

        public Position(String id, String code, String name) {
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
