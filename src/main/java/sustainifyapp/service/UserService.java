package sustainifyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sustainifyapp.dao.UserDao;
import sustainifyapp.model.User;

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
}
