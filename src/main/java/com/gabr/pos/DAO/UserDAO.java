package com.gabr.pos.DAO;

import com.gabr.pos.Services.WindowUtils;
import com.gabr.pos.db.DEF;
import com.gabr.pos.db.DbConnect;
import com.gabr.pos.models.User;
import com.gabr.pos.models.UserContext;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import static com.gabr.pos.Logging.logging.*;
import static com.gabr.pos.Services.SettingService.APP_BUNDLE;
import static com.gabr.pos.Services.WindowUtils.ALERT;

public class UserDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DecimalFormat decimal = new DecimalFormat("#.##");

    /**
     * This Method to retrive user data by user id
     * @param userId
     * @return
     */
    public User loadUserData(int userId) {
        User user = null;
        try {
            connection = DbConnect.getConnect();
            // SQL query to fetch user data based on userId
            String SELECT_USER_BY_ID = "SELECT user.name, user.role FROM user WHERE user.id = ?";
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(DEF.USER_NAME);
                int role = resultSet.getInt(DEF.USER_ROLE);
                user = new User(userId, name, role);
            }

        } catch (SQLException ex) {
            logExpWithMessage(ERROR,this.getClass().getName(),"loadUserData", ex,"Sql: %s", preparedStatement.toString());
        }finally {
            // Close resources in the finally block to ensure they are always closed
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                // Handle potential SQL exception when closing resources
                logException(ERROR,this.getClass().getName(),"loadUserData (closing resources)", ex);
            }
        }
        return user;
    }

    public int logIn(String userName , String password) {
        int userId = -1;
        try {
            connection = DbConnect.getConnect();
            // SQL query to fetch user data based on userId
            String QUERY = "SELECT * FROM user WHERE phone = ?";
            preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Get the stored hashed password from the database
                String storedHashedPassword = resultSet.getString("password");
                // Check if the entered password matches the stored hashed password
                if (checkPassword(password, storedHashedPassword)) {
                    userId = resultSet.getInt("id");
                    User user = new User(
                            userId,
                            resultSet.getString(DEF.USER_NAME),
                            resultSet.getString(DEF.USER_PHONE),
                            resultSet.getString(DEF.USER_PASSWORD),
                            resultSet.getInt(DEF.USER_ROLE)
                    );
                    UserContext.setCurrentUser(user);
                } else {
                    ALERT(APP_BUNDLE().getString("LOGIN_ERROR"), APP_BUNDLE().getString("PASSWORD_ERROR"), WindowUtils.ALERT_WARNING);
                }
            } else {
                ALERT(APP_BUNDLE().getString("LOGIN_ERROR"), APP_BUNDLE().getString("USER_NOT_FOUND"), WindowUtils.ALERT_WARNING);
            }

        } catch (SQLException ex) {
            logExpWithMessage(ERROR, this.getClass().getName(), "logIn",ex, "Sql: %s ", preparedStatement.toString());
        } finally {
            // Close resources in the finally block to ensure they are always closed
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // Handle potential SQL exception when closing resources
                logException(ERROR, this.getClass().getName(), "logIn (closing resources)", e);
            }
        }
        return userId ;
    }

    public static boolean checkPassword(String enteredPassword, String hashedPassword) {
        // Verifies if the entered password matches the hashed password
        return BCrypt.checkpw(enteredPassword, hashedPassword);
    }

}
