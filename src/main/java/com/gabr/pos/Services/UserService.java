package com.gabr.pos.Services;

import com.gabr.pos.models.User;

import java.util.Optional;

import com.gabr.pos.DAO.UserDAO;

import static com.gabr.pos.Services.SettingService.APP_BUNDLE;
import static com.gabr.pos.Services.WindowUtils.ALERT;
import static com.gabr.pos.Services.WindowUtils.ALERT_WARNING;

public class UserService {

    private UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAO();
    }

    public Optional<User> loadUserData(int userId) {
        try {
            return Optional.ofNullable(userDao.loadUserData(userId));
        } catch (Exception ex) {
            //    LOG_EXCEP(this.getClass().getName(), "loadUserData", ex);
            return Optional.empty();
        }
    }

    public int checkLogIn(String userName, String pass) {
        // Check for empty or null username
        if (userName == null || userName.isEmpty()) {
            ALERT("", APP_BUNDLE().getString("USER_NAME_INVALID"), ALERT_WARNING);
        }
        // Check for empty or null password
        if (pass == null || pass.isEmpty()) {
            ALERT("", APP_BUNDLE().getString("PASSWORD_INVALID"), ALERT_WARNING);
        }
        // If both username and password are valid, attempt to log in
        if ((userName != null && !userName.isEmpty()) && (pass != null && !pass.isEmpty())) {
            return userDao.logIn(userName, pass);
        } else {
            return -1;
        }
    }
}
