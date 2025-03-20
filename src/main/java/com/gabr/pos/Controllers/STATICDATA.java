package com.gabr.pos.Controllers;

import javafx.scene.control.Alert;

public class STATICDATA {

    public static void AlertMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
