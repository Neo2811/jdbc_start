package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    Connection connection = DataSource.getConnection();

    public User getById(int id) {
        User user = null;
        String qyery = "select * from user where id = " + id;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(qyery);

            user = buildUser(resultSet);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User getByUser(String username) {
        User user = null;
        String qyery = "select * from user where email = " + username;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(qyery);

            user = buildUser(resultSet);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private User buildUser(ResultSet resultSet) {
        User user = null;
        try {


            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirst_name(resultSet.getString("first_name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
