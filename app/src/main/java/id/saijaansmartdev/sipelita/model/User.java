package id.saijaansmartdev.sipelita.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user")
    @Expose
    private UserData user;

    public User(UserData user) {
        this.user = user;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public class UserData {
        @SerializedName("id")
        @Expose
        private String idUser;

        @SerializedName("additional_id")
        @Expose
        private String additionalIdUser;

        @SerializedName("additional_id_type")
        @Expose
        private String additionalIdTypeUser;

        @SerializedName("email")
        @Expose
        private String emailUser;

        @SerializedName("name")
        @Expose
        private String nameUser;

        @SerializedName("phone")
        @Expose
        private String phoneUser;

        @SerializedName("address")
        @Expose
        private String addressUser;

        @SerializedName("company")
        @Expose
        private String companyUser;

        @SerializedName("company_address")
        @Expose
        private String companyAddressUser;

        @SerializedName("company_telephone")
        @Expose
        private String companyTelephoneUser;

        @SerializedName("main_access")
        @Expose
        private String mainAccessUser;

        @SerializedName("profile_img")
        @Expose
        private String profileImageUser;

        public UserData(String idUser, String additionalIdUser, String additionalIdTypeUser, String emailUser, String nameUser, String phoneUser, String addressUser, String companyUser, String companyAddressUser, String companyTelephoneUser, String mainAccessUser, String profileImageUser) {
            this.idUser = idUser;
            this.additionalIdUser = additionalIdUser;
            this.additionalIdTypeUser = additionalIdTypeUser;
            this.emailUser = emailUser;
            this.nameUser = nameUser;
            this.phoneUser = phoneUser;
            this.addressUser = addressUser;
            this.companyUser = companyUser;
            this.companyAddressUser = companyAddressUser;
            this.companyTelephoneUser = companyTelephoneUser;
            this.mainAccessUser = mainAccessUser;
            this.profileImageUser = profileImageUser;
        }

        public String getIdUser() {
            return idUser;
        }

        public void setIdUser(String idUser) {
            this.idUser = idUser;
        }

        public String getAdditionalIdUser() {
            return additionalIdUser;
        }

        public void setAdditionalIdUser(String additionalIdUser) {
            this.additionalIdUser = additionalIdUser;
        }

        public String getAdditionalIdTypeUser() {
            return additionalIdTypeUser;
        }

        public void setAdditionalIdTypeUser(String additionalIdTypeUser) {
            this.additionalIdTypeUser = additionalIdTypeUser;
        }

        public String getEmailUser() {
            return emailUser;
        }

        public void setEmailUser(String emailUser) {
            this.emailUser = emailUser;
        }

        public String getNameUser() {
            return nameUser;
        }

        public void setNameUser(String nameUser) {
            this.nameUser = nameUser;
        }

        public String getPhoneUser() {
            return phoneUser;
        }

        public void setPhoneUser(String phoneUser) {
            this.phoneUser = phoneUser;
        }

        public String getAddressUser() {
            return addressUser;
        }

        public void setAddressUser(String addressUser) {
            this.addressUser = addressUser;
        }

        public String getCompanyUser() {
            return companyUser;
        }

        public void setCompanyUser(String companyUser) {
            this.companyUser = companyUser;
        }

        public String getCompanyAddressUser() {
            return companyAddressUser;
        }

        public void setCompanyAddressUser(String companyAddressUser) {
            this.companyAddressUser = companyAddressUser;
        }

        public String getCompanyTelephoneUser() {
            return companyTelephoneUser;
        }

        public void setCompanyTelephoneUser(String companyTelephoneUser) {
            this.companyTelephoneUser = companyTelephoneUser;
        }

        public String getMainAccessUser() {
            return mainAccessUser;
        }

        public void setMainAccessUser(String mainAccessUser) {
            this.mainAccessUser = mainAccessUser;
        }

        public String getProfileImageUser() {
            return profileImageUser;
        }

        public void setProfileImageUser(String profileImageUser) {
            this.profileImageUser = profileImageUser;
        }
    }

}



