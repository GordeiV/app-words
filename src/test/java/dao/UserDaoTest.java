package dao;

import entity.User;
import entity.Vocabulary;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.Assert.*;

public class UserDaoTest {
    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    public void saveUser1() throws SQLException {
        User user = new User("Person1", "123");
        new UserDao().saveUser(user);
    }

    @Test(expected = SQLIntegrityConstraintViolationException.class)
    public void saveUser2() throws SQLException {
        User user = new User("Person1", "1234");
        new UserDao().saveUser(user);
    }

    @Test
    public void saveUser3() throws SQLException {
        User user = new User("Person2", "123");
        new UserDao().saveUser(user);
    }

    @Test
    public void getUser1() throws SQLException {
        User user = new UserDao().getUser("theo");
        Assert.assertTrue(user.getId() == 7);
    }

    @Test
    public void getUser2() throws SQLException {
        User user = new UserDao().getUser("dsanköыф");
        Assert.assertTrue(user == null);
    }

    @Test
    public void getUser3() throws SQLException {
        User user = new UserDao().getUser("shela");
        Assert.assertTrue(user.getId() == 6);
    }

    @Test
    public void deleteUser1() throws SQLException {
        UserDao userDao = new UserDao();
        userDao.deleteUser(4L);
        Assert.assertTrue(userDao.getUser("sally") == null);
    }

    @Test
    public void deleteUser2() throws SQLException {
        UserDao userDao = new UserDao();
        userDao.deleteUser("kripoti");
        Assert.assertTrue(userDao.getUser("kripoti") == null);
    }

    @Test
    public void deleteUser3() throws SQLException {
        UserDao userDao = new UserDao();
        boolean deleted = userDao.deleteUser("kripotidsadasdasddasd");
        Assert.assertFalse(deleted);
    }

    @Test
    public void updateUser1() throws SQLException {
        User user = new User("marks2", "passForTest1", 1L);
        UserDao userDao = new UserDao();
        userDao.updateUser(user);
        User finalUser = userDao.getUser("marks2");
        Assert.assertTrue(finalUser.getPassword().equals("passForTest1"));
    }

    @Test
    public void updateUser2() throws SQLException {
        User user = new User("saam", "passForTest2", 2L);
        UserDao userDao = new UserDao();
        userDao.updateUser(user);
        User finalUser = userDao.getUser("saam");
        Assert.assertTrue(finalUser.getPassword().equals("passForTest2"));
    }

    @Test
    public void updateUser3() throws SQLException {
        User user = new User("john", "passForTest3", 3L);
        UserDao userDao = new UserDao();
        userDao.updateUser(user);
        User finalUser = userDao.getUser("john");
        Assert.assertTrue(finalUser.getPassword().equals("passForTest3"));
    }
}