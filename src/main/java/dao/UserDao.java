package dao;

import config.Config;
import content.User;

import java.sql.*;

public class UserDao {
    public static final String INSERT_USER = "INSERT INTO users (login, u_password) VALUES (?, ?);";

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return connection;
    }

    public Long saveUser(User user) {
        Long id = -1L;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, new String[]{"id_user"}))
        {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getLong(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return id;
    }
}
