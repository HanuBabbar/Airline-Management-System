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

    public User user(){
        return user;
    }

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {
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
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings() {
        if(user==null){
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
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(USERS_PATH);
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            String userIdToFind = user.getUserId();
            Ticket ticket = new Ticket(UUID.randomUUID().toString(), user.getUserId(), source, destination, plane );
            for (User u : users) {
                if (u.getUserId() != null && u.getUserId().equals(user.getUserId())) {
                    if (u.getTicketsBooked() == null) {
                        u.setTicketsBooked(new ArrayList<>());
                    }
                    u.getTicketsBooked().add(ticket);
                    break;
                }
            }

            PlaneService planeService = new PlaneService();

            List<List<Integer>> seats = plane.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    plane.setSeats(seats);
                    planeService.addPlane(plane);
                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
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
