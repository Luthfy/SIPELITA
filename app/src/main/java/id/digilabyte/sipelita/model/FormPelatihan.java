package id.digilabyte.sipelita.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FormPelatihan {

    private Participant participant;

    public FormPelatihan(Participant participant) {
        this.participant = participant;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public static class Participant implements Parcelable {

        private String code;
        private String status;
        private String name;

        public Participant(String code, String status, String name) {
            this.code = code;
            this.status = status;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.code);
            dest.writeString(this.status);
            dest.writeString(this.name);
        }

        protected Participant(Parcel in) {
            this.code = in.readString();
            this.status = in.readString();
            this.name = in.readString();
        }

        public static final Parcelable.Creator<Participant> CREATOR = new Parcelable.Creator<Participant>() {
            @Override
            public Participant createFromParcel(Parcel source) {
                return new Participant(source);
            }

            @Override
            public Participant[] newArray(int size) {
                return new Participant[size];
            }
        };
    }

}