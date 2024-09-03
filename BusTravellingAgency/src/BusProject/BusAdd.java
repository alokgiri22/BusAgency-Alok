package BusProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BusAdd extends  BusAvailability{
    private static final String url = "jdbc:mysql://127.0.0.1:3306/agency";
    private static final String username = "root";
    private static final String password = "Jordan@22@sql";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException e ){
            System.out.println(e.getMessage());
        }

        try{
            String query = "INSERT INTO bus (bus_no, date_booked, capacity) VALUES (?, ?, ?) ";
            Connection connection = DriverManager.getConnection(url, username , password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner scanner = new Scanner(System.in);

//            while (true) {
//                System.out.print("Enter Bus Number: ");
//                int busNumber = scanner.nextInt();
//
//                System.out.print("Enter Bus Booking Date: ");
//                String dateBook = scanner.next();
//
//                System.out.print("Enter Bus Capacity: ");
//                int busCapacity = scanner.nextInt();
//
//
//                System.out.print("Do you want to add more Passenger? (Yes/No) :");
//                String choice = scanner.next();
//
//                preparedStatement.setInt(1, busNumber);
//                preparedStatement.setString(2, dateBook);
//                preparedStatement.setInt(3, busCapacity);
//
//                preparedStatement.addBatch();
//
//                if (choice.equalsIgnoreCase("No")){
//                    System.out.println("Alright. Thank you!!");
//                    break;
//                }
//            }
//            int [] a = preparedStatement.executeBatch();
//            for (int i=0; i<a.length; i++){
//                if (a[i]==0){
//                    System.out.println("Failed to added passenger at query number " + i);
//                } else {
//                    System.out.println("Successfully Added.");
//                }
//            }
            preparedStatement.close();
            connection.close();
            scanner.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        //BUS AVAILABILITY CHECK -->
        // True -> when is bus available AND False -> when is not bus available!!
        System.out.print("Bus availability Status is ");
        try {
            System.out.print(BusAdd.isBusAvailable(5648, "22-11-2024" ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
