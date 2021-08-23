package dao;

import config.Config;
import entity.User;
import util.ConnectionManager;

import java.sql.*;

public class UserDao {
    private static final String INSERT_USER = "INSERT INTO users (login, u_password) VALUES (?, ?)";
    private static final String GET_USER = "SELECT * FROM users WHERE login = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id_user = ?";
    private static final String DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE login = ?";
    private static final String UPDATE_USER = "UPDATE users SET login = ?, u_password = ? WHERE id_user = ?";


    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return connection;
    }

    public Long saveUser(User user) throws SQLException {
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
        }


        return id;
    }

    /**
     * @param login
     * @return If there is no user found, return null
     * @throws SQLException
     */
    public User getUser(String login) throws SQLException {
        User user = null;

        try (Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = con.prepareStatement(GET_USER))
        {
            stmt.setString(1, login);

            ResultSet keys = stmt.executeQuery();
            if(keys.next() == true) {
                user = new User(keys.getString("login"), keys.getString("u_password"), keys.getLong("id_user"));
            }
        }
        return user;
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
