package am.hitech.jdbc;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.service.UserService;
import am.hitech.jdbc.util.DataSource;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    UserService userService = new UserService();
    public static void main(String[] args) throws SQLException, InternalServerError, NotFoundException {

        String query = "select * from user";
        Connection connection = DataSource.getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
//            System.out.println(resultSet.getString("first_name"));
            int id = resultSet.getInt(1);
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            System.out.println(first_name+", " + last_name);
        }


        resultSet.close();
        statement.close();
        connection.close();





        Main main = new Main();
        User user = main.userService.getById(1);
        System.out.println(user);
    }
}
