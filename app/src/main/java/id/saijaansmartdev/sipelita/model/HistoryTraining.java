package id.saijaansmartdev.sipelita.model;

import java.util.ArrayList;

public class HistoryTraining {

    private ArrayList<Training> data;
    private Integer current_page;
    private Integer last_page;

    public HistoryTraining(ArrayList<Training> data, Integer current_page, Integer last_page) {
        this.data = data;
        this.current_page = current_page;
        this.last_page = last_page;
    }

    public ArrayList<Training> getData() {
        return data;
    }

    public void setData(ArrayList<Training> data) {
        this.data = data;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getLast_page() {
        return last_page;
    }

    public void setLast_page(Integer last_page) {
        this.last_page = last_page;
    }

    public class Training {

        private String id;
        private String code;
        private String event_id;
        private String user_id;
        private String status;
        private String nip;
        private String name;
        private String birthplace;
        private String birthday;
        private String address;
        private String phone;
        private String jenkel;
        private String agency;
        private String agency_address;
        private String agency_province;
        private String agency_city;
        private String agency_district;
        private String agency_village;
        private String work_of_unit;
        private String unit_of_work;
        private String work_category;
        private String work_position_name;
        private String group_id;
        private String position_id;
        private String education_id;
        private String majors;
        private Integer graduation;
        private String created_at;
        private String updated_at;
        private Pelatihan event;
        private Invoice invoiced;

        public Training(String id, String code, String event_id, String user_id, String status, String nip, String name, String birthplace, String birthday, String address, String phone, String jenkel, String agency, String agency_address, String agency_province, String agency_city, String agency_district, String agency_village, String work_of_unit, String unit_of_work, String work_category, String work_position_name, String group_id, String position_id, String education_id, String majors, Integer graduation, String created_at, String updated_at, Pelatihan event, Invoice invoiced) {
            this.id = id;
            this.code = code;
            this.event_id = event_id;
            this.user_id = user_id;
            this.status = status;
            this.nip = nip;
            this.name = name;
            this.birthplace = birthplace;
            this.birthday = birthday;
            this.address = address;
            this.phone = phone;
            this.jenkel = jenkel;
            this.agency = agency;
            this.agency_address = agency_address;
            this.agency_province = agency_province;
            this.agency_city = agency_city;
            this.agency_district = agency_district;
            this.agency_village = agency_village;
            this.work_of_unit = work_of_unit;
            this.unit_of_work = unit_of_work;
            this.work_category = work_category;
            this.work_position_name = work_position_name;
            this.group_id = group_id;
            this.position_id = position_id;
            this.education_id = education_id;
            this.majors = majors;
            this.graduation = graduation;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.event = event;
            this.invoiced = invoiced;
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

        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNip() {
            return nip;
        }

        public void setNip(String nip) {
            this.nip = nip;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBirthplace() {
            return birthplace;
        }

        public void setBirthplace(String birthplace) {
            this.birthplace = birthplace;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getJenkel() {
            return jenkel;
        }

        public void setJenkel(String jenkel) {
            this.jenkel = jenkel;
        }

        public String getAgency() {
            return agency;
        }

        public void setAgency(String agency) {
            this.agency = agency;
        }

        public String getAgency_address() {
            return agency_address;
        }

        public void setAgency_address(String agency_address) {
            this.agency_address = agency_address;
        }

        public String getAgency_province() {
            return agency_province;
        }

        public void setAgency_province(String agency_province) {
            this.agency_province = agency_province;
        }

        public String getAgency_city() {
            return agency_city;
        }

        public void setAgency_city(String agency_city) {
            this.agency_city = agency_city;
        }

        public String getAgency_district() {
            return agency_district;
        }

        public void setAgency_district(String agency_district) {
            this.agency_district = agency_district;
        }

        public String getAgency_village() {
            return agency_village;
        }

        public void setAgency_village(String agency_village) {
            this.agency_village = agency_village;
        }

        public String getWork_of_unit() {
            return work_of_unit;
        }

        public void setWork_of_unit(String work_of_unit) {
            this.work_of_unit = work_of_unit;
        }

        public String getUnit_of_work() {
            return unit_of_work;
        }

        public void setUnit_of_work(String unit_of_work) {
            this.unit_of_work = unit_of_work;
        }

        public String getWork_category() {
            return work_category;
        }

        public void setWork_category(String work_category) {
            this.work_category = work_category;
        }

        public String getWork_position_name() {
            return work_position_name;
        }

        public void setWork_position_name(String work_position_name) {
            this.work_position_name = work_position_name;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getPosition_id() {
            return position_id;
        }

        public void setPosition_id(String position_id) {
            this.position_id = position_id;
        }

        public String getEducation_id() {
            return education_id;
        }

        public void setEducation_id(String education_id) {
            this.education_id = education_id;
        }

        public String getMajors() {
            return majors;
        }

        public void setMajors(String majors) {
            this.majors = majors;
        }

        public Integer getGraduation() {
            return graduation;
        }

        public void setGraduation(Integer graduation) {
            this.graduation = graduation;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Pelatihan getEvent() {
            return event;
        }

        public void setEvent(Pelatihan event) {
            this.event = event;
        }

        public Invoice getInvoiced() {
            return invoiced;
        }

        public void setInvoiced(Invoice invoiced) {
            this.invoiced = invoiced;
        }
    }

    public class Invoice {

        private String id;
        private String officer_id;
        private String customer_id;
        private String code_prefix;
        private String code_suffix;
        private String datetime_in;
        private String datetime_out;
        private String invoiced_type;
        private String invoiced_id;
        private String desc;
        private Integer total;
        private Integer paidoff;
        private Integer is_company;
        private Integer is_booking;
        private String created_at;
        private String updated_at;

        public Invoice(String id, String officer_id, String customer_id, String code_prefix, String code_suffix, String datetime_in, String datetime_out, String invoiced_type, String invoiced_id, String desc, Integer total, Integer paidoff, Integer is_company, Integer is_booking, String created_at, String updated_at) {
            this.id = id;
            this.officer_id = officer_id;
            this.customer_id = customer_id;
            this.code_prefix = code_prefix;
            this.code_suffix = code_suffix;
            this.datetime_in = datetime_in;
            this.datetime_out = datetime_out;
            this.invoiced_type = invoiced_type;
            this.invoiced_id = invoiced_id;
            this.desc = desc;
            this.total = total;
            this.paidoff = paidoff;
            this.is_company = is_company;
            this.is_booking = is_booking;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOfficer_id() {
            return officer_id;
        }

        public void setOfficer_id(String officer_id) {
            this.officer_id = officer_id;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getCode_prefix() {
            return code_prefix;
        }

        public void setCode_prefix(String code_prefix) {
            this.code_prefix = code_prefix;
        }

        public String getCode_suffix() {
            return code_suffix;
        }

        public void setCode_suffix(String code_suffix) {
            this.code_suffix = code_suffix;
        }

        public String getDatetime_in() {
            return datetime_in;
        }

        public void setDatetime_in(String datetime_in) {
            this.datetime_in = datetime_in;
        }

        public String getDatetime_out() {
            return datetime_out;
        }

        public void setDatetime_out(String datetime_out) {
            this.datetime_out = datetime_out;
        }

        public String getInvoiced_type() {
            return invoiced_type;
        }

        public void setInvoiced_type(String invoiced_type) {
            this.invoiced_type = invoiced_type;
        }

        public String getInvoiced_id() {
            return invoiced_id;
        }

        public void setInvoiced_id(String invoiced_id) {
            this.invoiced_id = invoiced_id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getPaidoff() {
            return paidoff;
        }

        public void setPaidoff(Integer paidoff) {
            this.paidoff = paidoff;
        }

        public Integer getIs_company() {
            return is_company;
        }

        public void setIs_company(Integer is_company) {
            this.is_company = is_company;
        }

        public Integer getIs_booking() {
            return is_booking;
        }

        public void setIs_booking(Integer is_booking) {
            this.is_booking = is_booking;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
