package com.gabr.pos.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

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

public class AddCustomerController implements Initializable {

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

    private static boolean update;

    static int CUST_ID;
    static int USER_ID;
    static int ROLE;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserContext.getCurrentUser() != null){
            USER_ID = UserContext.getCurrentUser().getId();
            ROLE = UserContext.getCurrentUser().getRole();
        }
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

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("تم التسجيل");
                alert.showAndWait();

                nameFild.setText("");
                telFild.setText("");
                emailfild.setText("");
                addressFild.setText("");
            }
        } catch (Exception ex) {
            LOG_EXCEP(this.getClass().getName(), "save", ex);
            STATICDATA.AlertMessage(ex.getMessage());
        }
    }

    @FXML
    private void close(MouseEvent event) {
        CLOSE(event);
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `customers`(`name`, `phone`, `email`, `address`) VALUES (?,?,?,?)";

        } else {
            query = "UPDATE `customers` SET "
                    + "`name`=?,"
                    + "`phone`=?,"
                    + "`email`=?,"
                    + "`address`= ? WHERE id = '" + CUST_ID + "'";
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

        } catch (Exception ex) {
            LOG_EXCEP(this.getClass().getName(), "insert", ex);
        }

    }

    public void setTextField(customer cus,boolean b) {
        update = b;
        CUST_ID = cus.getId();
            nameFild.setText(cus.getName());
            telFild.setText(cus.getPhone());
            emailfild.setText(cus.getEmail());
            addressFild.setText(cus.getAddress());
    }


}
