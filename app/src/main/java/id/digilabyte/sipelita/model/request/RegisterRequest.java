package id.digilabyte.sipelita.model.request;

public class RegisterRequest {

    private String additional_id = "", additional_id_type = "";
    private String name = "", phone = "", address = "";
    private String email = "", password = "";
    private String company = "", company_address = "", company_telephone = "";

    public String getAdditional_id() {
        return additional_id;
    }

    public void setAdditional_id(String additional_id) {
        this.additional_id = additional_id;
    }

    public String getAdditional_id_type() {
        return additional_id_type;
    }

    public void setAdditional_id_type(String additional_id_type) {
        this.additional_id_type = additional_id_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_phone() {
        return company_telephone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_telephone = company_phone;
    }
}
