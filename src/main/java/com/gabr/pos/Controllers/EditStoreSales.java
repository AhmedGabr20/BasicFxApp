/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabr.pos.Controllers;

import com.gabr.pos.db.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.gabr.pos.models.UserContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import static com.gabr.pos.Services.WindowUtils.CLOSE;
import static com.gabr.pos.Services.exceptionHandling.LOG_EXCEP;

/**
 *
 * @author Ahmed Gabr
 */
public class EditStoreSales implements Initializable {

    @FXML
    private ComboBox<String> storeNameField;
    @FXML
    private Spinner<Double> persent;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    int USER_ID;
    int ROLE;
    int STORE_CODE;

    ViewItemsController viewItemsController = new ViewItemsController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserContext.getCurrentUser() != null){
            USER_ID = UserContext.getCurrentUser().getId();
            ROLE = UserContext.getCurrentUser().getRole();
        }
        Spinner<Integer> spinner = new Spinner<>(1, 10, 2);
        //    persent.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100,1));
        persent.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-100, 100, 1));
        
        storeNameField.getSelectionModel().select(0);

        storeNameField.setOnAction(e -> {

            String StoreName = storeNameField.getSelectionModel().getSelectedItem();

            try {

                STORE_CODE = viewItemsController.getStoreCode(StoreName);

            } catch (Exception ex) {
                LOG_EXCEP(this.getClass().getName(), "initialize", ex);
            }

        });

    }

    @FXML
    private void close(MouseEvent event) {
        CLOSE(event);
    }

    @FXML
    private void updateAll(MouseEvent event) {

        double PERSENT = persent.getValueFactory().getValue();

        //   if (PERSENT > 0) {
        try {
            PERSENT = PERSENT / 100;

            connection = DbConnect.getConnect();
            query = "UPDATE `items` SET "
                    + "`price`= price+?*price,"
                    + "`priceforcustomer`= priceforcustomer+?*priceforcustomer ,"
                    + "`lastUpdatedDate`=? "
                    + "WHERE storeCode = '" + STORE_CODE + "'";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, PERSENT);
            preparedStatement.setDouble(2, PERSENT);
            preparedStatement.setString(3, LocalDate.now()+"");
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("تم تحديث الأسعار");
                alert.showAndWait();

            //    loadItemByStore(STORE_CODE);
                //    persent.setText("0");
                PERSENT = 0;
                close(event);

            }

        } catch (Exception ex) {
            LOG_EXCEP(this.getClass().getName(), "updateAll", ex);
        }
        /*
         } else {
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText(null);
         alert.setContentText("الرجاء ادخال نسبة صحيحة");
         alert.showAndWait();
         }
         */
    }

    void setTextField(ArrayList<String> stores) {
        storeNameField.getItems().addAll(stores);
        storeNameField.getSelectionModel().select(0);
    }

}
