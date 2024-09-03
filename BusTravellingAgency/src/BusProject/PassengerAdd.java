package BusProject;

import java.sql.*;
import java.util.Scanner;

public class PassengerAdd {
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

        try {
            String query = "INSERT INTO passenger (name, age, date_booked, amount, boarding, destination) VALUES ( ?, ?, ?, ?, ?, ?) ";
            Connection connection = DriverManager.getConnection(url, username , password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner scanner = new Scanner(System.in);

            while (true) {
//                System.out.print("Enter Passenger ID: ");
//                int passengerId = scanner.nextInt();
                System.out.print("Enter Name: ");
                String name = scanner.next();

                System.out.print("Enter Age: ");
                int age = scanner.nextInt();

                System.out.print("Enter Date of Booking: ");
                String dateBooked = scanner.next();

                System.out.print("Enter amount: ");
                int amount = scanner.nextInt();

                System.out.print("Enter Boarding: ");
                String boarding = scanner.next();

                System.out.print("Enter Destination: ");
                String destination = scanner.next();

                System.out.print("Do you want to add more Passenger? (Yes/No) :");
                String choice = scanner.next();

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setString(3, dateBooked);
                preparedStatement.setInt(4, amount);
                preparedStatement.setString(5, boarding);
                preparedStatement.setString(6, destination);

                preparedStatement.addBatch();

                if (choice.equalsIgnoreCase("No")){
                    System.out.println("Alright. Thank you!!");
                    break;
                }
            }
            int [] a = preparedStatement.executeBatch();
            for (int i=0; i<a.length; i++){
                if (a[i]==0){
                    System.out.println("Failed to added passenger at query number " + i);
                } else {
                    System.out.println("Successfully Added.");
                }
            }
            preparedStatement.close();
            connection.close();
            scanner.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        try {
            String query = "SELECT * FROM passenger ";
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                System.out.println("Passenger ID: " + id);
                String name  = resultSet.getString("name");
                System.out.println("Name: " + name);
                int age = resultSet.getInt("age");
                System.out.println("Age: " + age);
                String date = resultSet.getString("date_booked");
                int amount = resultSet.getInt("amount");
                System.out.println("Ticket amount: " + amount);
                String boarding = resultSet.getString("boarding");
                System.out.println("Boarding Location: " + boarding);
                String destination = resultSet.getString("destination");
                System.out.println("Destination Location: " + destination);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
