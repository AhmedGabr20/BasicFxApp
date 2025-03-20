/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabr.pos.Controllers;

import com.gabr.pos.Services.WindowUtils;
import com.gabr.pos.db.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.gabr.pos.models.User;
import org.mindrot.jbcrypt.BCrypt;

import static com.gabr.pos.Services.WindowUtils.CLOSE;
import static com.gabr.pos.Services.exceptionHandling.LOG_EXCEP;

/**
 * FXML Controller class
 *
 * @author Ahmed Gabr
 */
public class AddUserController implements Initializable {

    @FXML
    private TextField nameFild;
    @FXML
    private TextField phoneFild;
    @FXML
    private TextField passwordfild;
    @FXML
    private ComboBox<String> rolesFild;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    User user = null;
    int userId;
    private boolean update;

    int ADMIN_ID;
    int ROLE;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rolesFild.getItems().add(0, "موظف");
        rolesFild.getItems().add(1, "مشرف");
       // rolesFild.getItems().addAll(item3);

        rolesFild.getSelectionModel().select(0);

    }

    @FXML
    private void close(MouseEvent event) {
        CLOSE(event);
    }

    @FXML
    private void save(MouseEvent event) {

        connection = DbConnect.getConnect();
        final String name = nameFild.getText();
        String phone = phoneFild.getText();
        String password = passwordfild.getText();

        // String roleName =  rolesFild.getSelectionModel().getSelectedItem();
        int role_id = rolesFild.getSelectionModel().getSelectedIndex();

        if (name.isEmpty() || phone.isEmpty() || password.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("الرجاء ادخال البيانات");
            alert.showAndWait();
        } else {
            getQuery();
            insert();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("تم التسجيل");
            alert.showAndWait();

            nameFild.setText("");
            phoneFild.setText("");
            passwordfild.setText("");
        }

    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `user`(`name`, `phone`, `password`, `role`) VALUES (?,?,?,?)";

        } else {
            query = "UPDATE `user` SET "
                    + "`name`=?,"
                    + "`phone`=?,"
                    + "`password`=?,"
                    + "`role`= ? WHERE id = '" + userId + "'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameFild.getText());
            preparedStatement.setString(2, phoneFild.getText());
            preparedStatement.setString(3, hashPassword(passwordfild.getText()));
            preparedStatement.setInt(4, rolesFild.getSelectionModel().getSelectedIndex());
            preparedStatement.execute();

        } catch (SQLException ex) {
            LOG_EXCEP(this.getClass().getName(), "insert", ex);
        }

    }

    void setTextField(int AdminId, int Admin_Role, int UserId, String name, String phone, String password, String User_Role, boolean b) {

        userId = UserId;
        nameFild.setText(name);
        phoneFild.setText(phone);
        passwordfild.setText(password);
        rolesFild.getSelectionModel().select(User_Role);
        ADMIN_ID = AdminId;
        ROLE = Admin_Role;
        this.update = b;

    }

    private String hashPassword(String password) {
        // Generates a salt automatically with a work factor of 12
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return hashedPassword;
    }

    // Method to check if the entered password matches the hashed one
    public static boolean checkPassword(String enteredPassword, String hashedPassword) {
        // Verifies if the entered password matches the hashed password
        return BCrypt.checkpw(enteredPassword, hashedPassword);
    }

}
