package szfm.krankenwagenracing.admin_user.dto;

import java.util.Date;

public class TicketDTo {
    private String name;
    private Integer price;
    private String email;
    private Date date;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public TicketDTo(String name, Integer price, String email, Date date) {
        this.name = name;
        this.price = price;
        this.email = email;
        this.date = date;
    }
}

