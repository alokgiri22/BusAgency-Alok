package BusProject;

import java.sql.*;

public class PassengerList {
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
            String query = "SELECT * FROM passenger ";
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("passenger_id");
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

                System.out.println("-----------------------------");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
