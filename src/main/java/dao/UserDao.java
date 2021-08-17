package dao;

import config.Config;
import entity.User;
import util.ConnectionManager;

import java.sql.*;

public class UserDao {
    public static final String INSERT_USER = "INSERT INTO users (login, u_password) VALUES (?, ?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id_user = ?";
    public static final String DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE login = ?";
    public static final String UPDATE_USER = "UPDATE users SET login = ?, u_password = ? WHERE id_user = ?";


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

    public boolean deleteUser(Long id) {
        boolean change = false;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_USER_BY_ID))
        {
            stmt.setLong(1, id);
            int i = stmt.executeUpdate();
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return change;
    }

    public boolean deleteUser(String login) {
        boolean change = false;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_USER_BY_LOGIN))
        {
            stmt.setString(1, login);
            int i = stmt.executeUpdate();
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return change;
    }

    public boolean updateUser(User user) {
        boolean change = false;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_USER))
        {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setLong(3, user.getId());

            int i = stmt.executeUpdate();
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return change;
    }
}
