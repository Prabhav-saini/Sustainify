package sustainifyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sustainifyapp.dao.UserDao;
import sustainifyapp.model.User;

import java.util.Objects;

@Service
public class UserService {
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    private final UserDao userDao;

    @Transactional
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        User existingUser = userDao.getUserByEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setMobileNumber(user.getMobileNumber());
        existingUser.setPassword(user.getPassword());
        userDao.updateUser(existingUser);
    }

    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Transactional
    public Boolean isUserValid(User user) {
       User existingUser = userDao.getUserByEmailPassword(user);
        return Objects.nonNull(existingUser);
    }

    @Transactional
    public User viewUser(String email) {
        return userDao.getUserByEmail(email);
    }

    @Transactional
    public Long getUserIdByEmail(String email) {
        return userDao.getUserId(email);
    }

}
