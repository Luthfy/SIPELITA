package id.saijaansmartdev.sipelita.model;

public class Contact {

    private String address;
    private String telephone;
    private String email;
    private String operation_time;

    public Contact(String address, String telephone, String email, String operation_time) {
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.operation_time = operation_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(String operation_time) {
        this.operation_time = operation_time;
    }
}
