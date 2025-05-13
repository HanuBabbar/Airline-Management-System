package services;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.Plane;
import entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
//import util.JsonUploader;
import util.UserServiceUtil;
import entities.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "app/src/main/resources/users.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }

    public User user() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {
        });
    }

    public Boolean loginUser(String inputName, String inputPassword) {
        Optional<User> foundUser = userList.stream()
                .filter(user1 -> user1.getName().equals(inputName))
                .findFirst();

        if (foundUser.isPresent()) {
            User existingUser = foundUser.get();
            if (UserServiceUtil.checkPassword(inputPassword, existingUser.getHashedPassword())) {
                this.user = existingUser;
                return true;
            }
        }

        return false;
    }



    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
//            JsonUploader.uploadUsersToMongo();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings() {
        if (user == null) {
            System.out.println("Please Log In to fetch your bookings");
            return;
        }
        user.printTickets();
//        Optional<User> userFetched = userList.stream().filter(user1 -> {
//            return user1.getName().equals(user.getName())
//                    && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
//        }).findFirst();
//        if (userFetched.isPresent()) {
//            userFetched.get().printTickets();
//        }
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

    public Boolean bookSeat(Plane plane, int row, int seat, String source, String destination) {
        try {
            PlaneService planeService = new PlaneService();
            Ticket ticket = new Ticket(UUID.randomUUID().toString(), user.getUserId(), source, destination, plane);

            List<List<Integer>> seats = plane.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    plane.setSeats(seats);
                    planeService.addPlane(plane);

                    user.addTicket(ticket);

                    // Update the user in userList
                    for (int i = 0; i < userList.size(); i++) {
                        if (userList.get(i).getUserId().equals(user.getUserId())) {
                            userList.set(i, user);
                            break;
                        }
                    }

                    saveUserListToFile();

                    System.out.println("Your ticket is booked from " + source + " to " + destination +
                            " on plane number: " + plane.getPlaneNumber());
                    return true;
                } else {
                    System.out.println("Seat already booked.");
                    return false;
                }
            } else {
                System.out.println("Invalid seat selection.");
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Error booking seat: " + ex.getMessage());
            return false;
        }
    }

}
