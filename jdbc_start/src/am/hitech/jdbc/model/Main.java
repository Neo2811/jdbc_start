package am.hitech.jdbc.model;

import am.hitech.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println(getAllUsers());
        System.out.println(getById(1));
        System.out.println(getByUsername("hayk@gmail.com"));
        System.out.println(getFreeNumberByCode(45));
    }

    static List getAllUsers() throws SQLException {
        User user;
        List<User> list = new ArrayList<>();

        String query = "select * from user";
        Connection connection = DataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");

            user = new User(id, first_name, last_name, email, age);
            list.add(user);
        }
        return list;
    }

    static User getById(int n) throws SQLException {
        User user = null;

        String query = "select * from user";
        Connection connection = DataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");

            if (n == id) {
                user = new User(id, first_name, last_name, email, age);
                return user;
            }
        }
        return user;
    }

    static User getByUsername(String s) throws SQLException {
        User user = null;

        String query = "select * from user";
        Connection connection = DataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");

            if (s.equals(email)) {
                user = new User(id, first_name, last_name, email, age);
                return user;
            }
        }
        return user;
    }

    static List getFreeNumberByCode(int n) throws SQLException {
        List<Integer> list = new ArrayList<>();
        String query = "SELECT * FROM phone_codes p1 " +
                "JOIN phone_numbers p2 ON p1.id = p2.phone_code_id WHERE user_id IS NULL";
        Connection connection = DataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int code = resultSet.getInt("code");
            int number = resultSet.getInt("number");
            if (n == code) {
                list.add(number);
                return list;
            }
        }
        return list;
    }
}
