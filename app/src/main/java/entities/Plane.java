package entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Plane {

    @JsonProperty("plane_id")
    private String planeId;

    @JsonProperty("plane_no")
    private String planeNumber;

    private List<List<Integer>> seats;

    @JsonProperty("airport_times")
    private Map<String, Time> airportTimes;

    private List<String> airports;

    @JsonProperty("plane_info")
    private String planeInfo;

    public Plane() {
    }

    public Plane(String planeId, String planeNumber, List<List<Integer>> seats, Map<String, Time> airportTimes,
            List<String> airports) {
        this.planeId = planeId;
        this.planeNumber = planeNumber;
        this.seats = seats;
        this.airportTimes = airportTimes;
        this.airports = airports;
    }

    public List<String> getAirports() {
        return airports;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public String getPlaneId() {
        return planeId;
    }

    public Map<String, Time> getAirportTimes() {
        return airportTimes;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }

    public void setPlaneNumber(String planeNumber) {
        this.planeNumber = planeNumber;
    }

    public void setAirportTimes(Map<String, Time> airportTimes) {
        this.airportTimes = airportTimes;
    }

    public void setAirports(List<String> airports) {
        this.airports = airports;
    }

    public void setPlaneInfo(String planeInfo) {
        this.planeInfo = planeInfo;
    }

    public String getPlaneInfo() {
        return planeInfo;
    }

}
