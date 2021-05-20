package id.saijaansmartdev.sipelita.model;

import java.util.ArrayList;

public class HistoryRoom {

    private ArrayList<ReservedRoom> data;
    private Integer current_page;
    private Integer last_page;

    public HistoryRoom(ArrayList<ReservedRoom> data, Integer current_page, Integer last_page) {
        this.data = data;
        this.current_page = current_page;
        this.last_page = last_page;
    }

    public ArrayList<ReservedRoom> getData() {
        return data;
    }

    public void setData(ArrayList<ReservedRoom> data) {
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

    public class ReservedRoom {
        private String id;
        private String code;
        private String customer_id;
        private String officer_id;
        private String building_id;
        private String datetime_booking;
        private String datetime_checkin;
        private String datetime_checkout;
        private String status;
        private String via;
        private String created_at;
        private String updated_at;
        private ArrayList<BookingRoom> booking_room;
        private Bangunan building;
        private Invoiced invoiced;

        public ReservedRoom(String id, String code, String customer_id, String officer_id, String building_id, String datetime_booking, String datetime_checkin, String datetime_checkout, String status, String via, String created_at, String updated_at, ArrayList<BookingRoom> booking_room, Bangunan building, Invoiced invoiced) {
            this.id = id;
            this.code = code;
            this.customer_id = customer_id;
            this.officer_id = officer_id;
            this.building_id = building_id;
            this.datetime_booking = datetime_booking;
            this.datetime_checkin = datetime_checkin;
            this.datetime_checkout = datetime_checkout;
            this.status = status;
            this.via = via;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.booking_room = booking_room;
            this.building = building;
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

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getOfficer_id() {
            return officer_id;
        }

        public void setOfficer_id(String officer_id) {
            this.officer_id = officer_id;
        }

        public String getBuilding_id() {
            return building_id;
        }

        public void setBuilding_id(String building_id) {
            this.building_id = building_id;
        }

        public String getDatetime_booking() {
            return datetime_booking;
        }

        public void setDatetime_booking(String datetime_booking) {
            this.datetime_booking = datetime_booking;
        }

        public String getDatetime_checkin() {
            return datetime_checkin;
        }

        public void setDatetime_checkin(String datetime_checkin) {
            this.datetime_checkin = datetime_checkin;
        }

        public String getDatetime_checkout() {
            return datetime_checkout;
        }

        public void setDatetime_checkout(String datetime_checkout) {
            this.datetime_checkout = datetime_checkout;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVia() {
            return via;
        }

        public void setVia(String via) {
            this.via = via;
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

        public ArrayList<BookingRoom> getBooking_room() {
            return booking_room;
        }

        public void setBooking_room(ArrayList<BookingRoom> booking_room) {
            this.booking_room = booking_room;
        }

        public Bangunan getBuilding() {
            return building;
        }

        public void setBuilding(Bangunan building) {
            this.building = building;
        }

        public Invoiced getInvoiced() {
            return invoiced;
        }

        public void setInvoiced(Invoiced invoiced) {
            this.invoiced = invoiced;
        }
    }

    public class BookingRoom {
        private String id;
        private String booking_id;
        private String building_id;
        private String room_id;
        private String room_name;
        private Integer room_price;

        public BookingRoom(String id, String booking_id, String building_id, String room_id, String room_name, Integer room_price) {
            this.id = id;
            this.booking_id = booking_id;
            this.building_id = building_id;
            this.room_id = room_id;
            this.room_name = room_name;
            this.room_price = room_price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBooking_id() {
            return booking_id;
        }

        public void setBooking_id(String booking_id) {
            this.booking_id = booking_id;
        }

        public String getBuilding_id() {
            return building_id;
        }

        public void setBuilding_id(String building_id) {
            this.building_id = building_id;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public Integer getRoom_price() {
            return room_price;
        }

        public void setRoom_price(Integer room_price) {
            this.room_price = room_price;
        }
    }

    public class Invoiced {
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

        public Invoiced(String id, String officer_id, String customer_id, String code_prefix, String code_suffix, String datetime_in, String datetime_out, String invoiced_type, String invoiced_id, String desc, Integer total, Integer paidoff, Integer is_company, Integer is_booking, String created_at, String updated_at) {
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
