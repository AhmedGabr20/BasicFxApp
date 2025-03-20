/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabr.pos.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.gabr.pos.Services.WindowUtils;
import com.gabr.pos.db.DbConnect;
import com.gabr.pos.models.UserContext;
import com.gabr.pos.models.customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import static com.gabr.pos.Services.WindowUtils.CLOSE;
import static com.gabr.pos.Services.exceptionHandling.LOG_EXCEP;

/**
 * FXML Controller class
 *
 * @author Ahmed Gabr
 */
public class AddCompanyController implements Initializable {

    @FXML
    private TextField nameFild;
    @FXML
    private TextField telFild;
    @FXML
    private TextField emailfild;
    @FXML
    private TextField addressFild;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    customer custmr = null;
    private boolean update;
    int studentId;

    int USER_ID;
    int ROLE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserContext.getCurrentUser() != null){
            USER_ID = UserContext.getCurrentUser().getId();
            ROLE = UserContext.getCurrentUser().getRole();
        }
    }

    @FXML
    private void close(MouseEvent event) {
        CLOSE(event);
    }

    @FXML
    private void save(MouseEvent event) {

        try {

            connection = DbConnect.getConnect();
            final String name = nameFild.getText();
            String phone = telFild.getText();
            String email = emailfild.getText();
            String address = addressFild.getText();

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {

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

           // close(event);
                nameFild.setText("");
                telFild.setText("");
                emailfild.setText("");
                addressFild.setText("");

                connection.close();
            }
        } catch (Exception ex) {
            LOG_EXCEP(this.getClass().getName(), "save", ex);
            STATICDATA.AlertMessage(ex.getMessage());
        }
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `companes`(`name`, `phone`, `email`, `address`) VALUES (?,?,?,?)";

        } else {
            query = "UPDATE `companes` SET "
                    + "`name`=?,"
                    + "`phone`=?,"
                    + "`email`=?,"
                    + "`address`= ? WHERE id = '" + studentId + "'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameFild.getText());
            preparedStatement.setString(2, telFild.getText());
            preparedStatement.setString(3, emailfild.getText());
            preparedStatement.setString(4, addressFild.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            LOG_EXCEP(this.getClass().getName(), "insert", ex);
        }

    }

    void setTextField(customer custmr) {
        studentId = custmr.getId();
        nameFild.setText(custmr.getName());
        telFild.setText(custmr.getPhone());
        emailfild.setText(custmr.getEmail());
        addressFild.setText(custmr.getAddress());

    }

    void setUpdate(boolean b) {
        this.update = b;
    }

}
