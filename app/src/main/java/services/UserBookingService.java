package services;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.Plane;
import entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import util.UserServiceUtil;
import entities.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String users_path = "users.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(users_path);
        if (inputStream == null) {
            throw new FileNotFoundException("users.json not found in resources");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        userList = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {
        });
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName())
                    && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(users_path);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings() {
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName())
                    && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        if (userFetched.isPresent()) {
            userFetched.get().printTickets();
        }
    }

    // todo complete this function
    // public Boolean cancelBooking(String TicketId){
    // Scanner sc = new Scanner(System.in);
    // System.out.println("Enter the ticket id to cancel");
    // String ticketId = sc.next();
    //
    // if(ticketId==null||ticketId.isEmpty()){
    // System.out.println("Ticket Id cannot be null or empty");
    // return Boolean.FALSE;
    // }
    //
    // String finalTicketId = ticketId;
    // boolean removed = user.getTicketsBooked().removeIf(ticket ->
    // ticket.getTicketId().equals(finalTicketId));
    // if(removed){
    // System.out.println("Ticket with ID"+ticketId+"has been cancelled.");
    // }
    // else{
    // System.out.println("No ticket with with ID"+ticketId);
    // return Boolean.FALSE;
    // }
    //
    // }

    public List<Plane> getPlanes(String source, String destination) {
        try {
            PlaneService planeService = new PlaneService();
            return planeService.searchPlanes(source, destination);
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Plane plane) {
        return plane.getSeats();
    }

    public Boolean bookSeat(Plane plane, int row, int seat) {
        try {
            PlaneService planeService = new PlaneService();
            List<List<Integer>> seats = plane.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    plane.setSeats(seats);
                    planeService.addPlane(plane);
                    return true; // booked
                } else {
                    return false; // already booked
                }
            } else {
                return false; // invalid seat
            }
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

}
