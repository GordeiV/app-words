package business;

import business.exceptions.NoUserFound;
import dao.DaoException;
import dao.UserDao;
import entity.User;
import util.PoolConnectionManager;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
        userDao.setConnectionManager(new PoolConnectionManager());
    }

    public User logInUser(User user) throws NoUserFound, DaoException {
        User checkedUser = userDao.checkUser(user);
        if(checkedUser == null) {
            String message = "There is no user with - Login: " + user.getLogin() + "| Password: " + user.getPassword();
            throw new NoUserFound(message);
        }
        return checkedUser;
    }
}
