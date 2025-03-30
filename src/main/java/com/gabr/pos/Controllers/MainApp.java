package com.gabr.pos.Controllers;


import com.gabr.pos.Services.SettingService;
import com.gabr.pos.db.DbConnect;
import javafx.application.Application;
import javafx.stage.Stage;

import static com.gabr.pos.Logging.logging.*;
import static com.gabr.pos.Services.SettingService.*;
import static com.gabr.pos.Services.WindowUtils.*;


/**
 * @author Ahmed Gabr
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            //    createLog();
            // load Setting xml data
            SettingService.getXmlFile();
            checkLicense();

            if (DbConnect.getConnect() == null) {
                ALERT("", APP_BUNDLE().getString("CONNECTION_ERROR"), ALERT_ERROR);
                logMessage(ERROR, this.getClass().getName(), "start", "Connection error getConnect(): %s", null);
            } else {
                //OPEN_LOGIN_PAGE();
                OPEN_BACKUP_PAGE();
            }
        } catch (Exception ex) {
            logException(ERROR, this.getClass().getName(), "start", ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
