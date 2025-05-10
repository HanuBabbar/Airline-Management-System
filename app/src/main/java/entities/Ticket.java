package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    @JsonProperty("ticket_id")
    private String ticketId;

    @JsonProperty("user_id")
    private String userId;

    private String source;

    private String destination;

    @JsonProperty("date_of_travel")
    private String dateOfTravel;

    private Plane plane;

    public Ticket(String ticketId, String userId, String source, String destination, String dateOfTravel, Plane Plane) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.plane = Plane;
    }

    public Ticket() {
    }

    public String getTicketInfo() {
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketId, userId, source,
                destination, dateOfTravel);
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination() {
        this.destination = destination;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel() {
        this.dateOfTravel = dateOfTravel;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane Plane) {
        this.plane = Plane;
    }

}
