package com.gabr.pos.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.gabr.pos.Services.SettingService;
import com.gabr.pos.Services.UserService;
import com.gabr.pos.Services.WindowUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static com.gabr.pos.Logging.logging.*;
import static com.gabr.pos.Services.SettingService.APP_BUNDLE;
import static com.gabr.pos.Services.WindowUtils.*;

/**
 * FXML Controller class
 *
 * @author Ahmed Gabr
 */
public class LogInController implements Initializable {

    @FXML
    private TextField user_name;
    @FXML
    private TextField password;
    @FXML
    private ImageView image;
    @FXML
    private Label CompanyName;
    @FXML
    private Label CompanySpecialty;

    UserService userService ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            userService = new UserService();
            Image img = new Image(getClass().getResourceAsStream(WindowUtils.iconImagePath));
            image.setImage(img);
            CompanyName.setText(SettingService.COMPANY_NAME);
            CompanySpecialty.setText(SettingService.COMPANY_SPECIALITY);
        } catch (Exception ex) {
            logException(ERROR,this.getClass().getName(),"initialize",ex);
            ALERT(APP_BUNDLE().getString("LOGIN_ERROR"),ex.getMessage(),WindowUtils.ALERT_ERROR);
        }
    }


    private int checkLogIn() {
        return  userService.checkLogIn(user_name.getText(),password.getText());
    }

    @FXML
    private void EnterLogin(ActionEvent event) {
        if (checkLogIn() != -1) {
            OPEN_MAIN_PAGE();
            CLOSE(event);
        }
    }
    @FXML
    private void login(MouseEvent event) {
        if (checkLogIn() != -1) {
            OPEN_MAIN_PAGE();
            CLOSE(event);
        }
    }

}
