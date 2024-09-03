package BusProject;

import java.sql.*;

public class BusAvailability {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/agency";
    private static final String username = "root";
    private static final String password = "Jordan@22@sql";

    public static boolean isBusAvailable(int bus_no, String date) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * From bus Where bus_no = ? AND date_booked = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bus_no);
            preparedStatement.setString(2, date);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int affectedRows = resultSet.getInt(1);
                if (affectedRows == 0){
                    return true;
                } else {
                    return false;
                }
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         return false;
    }
}
