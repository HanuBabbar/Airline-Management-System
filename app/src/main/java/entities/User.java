package entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("hashed_password")
    private String hashedPassword;

    @JsonProperty("ticketsBooked")
    private List<Ticket> ticketsBooked;

    @JsonProperty("userId")
    private String userId;

    public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userId) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void printTickets() {
        if (ticketsBooked == null || ticketsBooked.isEmpty()) {
            System.out.println("No tickets booked.");
        } else {
            for (Ticket t : ticketsBooked) {
                System.out.println(t.getTicketInfo());
            }
        }
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public void addTicket(Ticket ticket){
        if (ticketsBooked == null) {
            ticketsBooked = new ArrayList<>();
        }
        ticketsBooked.add(ticket);
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHashedPasswordPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
