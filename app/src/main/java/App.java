import entities.Plane;
import entities.User;
import services.UserBookingService;
import util.UserServiceUtil;

import java.io.IOException;
import java.sql.Time;
import java.util.*;

public class App {

    public static void main(String[] args){
        System.out.println("Runnin Airplane booking system");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        try{
            userBookingService = new UserBookingService();
        }catch (IOException ex){
            System.out.println(ex);
            System.out.println("Something went wrong");
            return;
        }
        while(option!=7){
            System.out.println("Choose Option");
            System.out.println("1. Sign Up");
            System.out.println("2. Log in");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Flights");
            System.out.println("5. Book a seat");
            System.out.println("6. Cancel a Booking");
            System.out.println("7. Exit");
            option = sc.nextInt();
            Plane planeSelected = new Plane();
            switch (option){
                case 1:
                    System.out.println("Enter username to signup");
                    String nameToSignup = sc.next();
                    System.out.println("Enter password");
                    String passSignUp = sc.next();
                    User userToSignup = new User(nameToSignup, passSignUp, UserServiceUtil.hashPassword(passSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignup);
                    break;
                case 2:
                    System.out.println("Enter username to Login");
                    String nameToLogin = sc.next();
                    System.out.println("Enter password");
                    String passLogIn = sc.next();
                    User userToLogin = new User(nameToLogin, passLogIn, UserServiceUtil.hashPassword(passLogIn), new ArrayList<>(),UUID.randomUUID().toString());
                    try{
                        userBookingService = new UserBookingService(userToLogin);
                    }catch (IOException ex){
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    System.out.println("Type your source Airport");
                    String source = sc.next();
                    System.out.println("Type your destination Airport");
                    String destination = sc.next();
                    List<Plane> planes = userBookingService.getPlanes(source, destination);
                    int index = 1;
                    for(Plane p: planes){
                        System.out.println(index+"Plane id:"+p.getPlaneId());
                        for(Map.Entry<String, Time> entry : p.getAirportTimes().entrySet()){
                            System.out.println("Airport "+entry.getKey()+" Time: "+entry.getValue());
                        }
                    }
                    System.out.println("Enter what flight you wanna choose");
                    planeSelected = planes.get(sc.nextInt());
                    break;
                case 5:
                    System.out.println("Select a seat to book");
                    List<List<Integer>> seats = userBookingService.fetchSeats(planeSelected);
                    for (List<Integer> row: seats){
                        for(Integer val: row){
                            System.out.println(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing row and column");
                    System.out.println("Enter row");
                    int row = sc.nextInt();
                    System.out.println("Enter column");
                    int column = sc.nextInt();
                    System.out.println("Booking your seat");
                    Boolean booked = userBookingService.bookSeat(planeSelected, row, column);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Your seat is booked!");
                    }else {
                        System.out.println("Unable to book this seat");
                    }
                    break;
                default:
                    break;
            }
        }



    }

}
