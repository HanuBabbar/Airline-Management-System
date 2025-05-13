package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    @JsonProperty("ticket_id")
    private String ticketId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("source")
    private String source;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("plane")
    private Plane plane;

    public Ticket(String ticketId, String userId, String source, String destination, Plane Plane) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.plane = Plane;
    }

    public Ticket(){}

    public String getTicketInfo() {
        return String.format("Ticket ID: %s belongs to User %s from %s to %s.", ticketId, userId, source,
                destination);
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

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane Plane) {
        this.plane = Plane;
    }

}
