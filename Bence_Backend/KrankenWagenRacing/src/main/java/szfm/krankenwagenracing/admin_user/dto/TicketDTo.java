package szfm.krankenwagenracing.admin_user.dto;

import java.util.Date;

public class TicketDTo {
    private String name;
    private Integer price;
    private String email;
    private String type;
    private String date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public TicketDTo(String name, Integer price, String email, String date, String type) {
        this.name = name;
        this.price = price;
        this.email = email;
        this.date = date;
        this.type = type;
    }
}

