package com.gabr.pos.Controllers;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.gabr.pos.Services.WindowUtils;
import com.gabr.pos.models.UserContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import static com.gabr.pos.Logging.logging.ERROR;
import static com.gabr.pos.Logging.logging.logException;
import static com.gabr.pos.Services.SettingService.COMPANY_NAME;
import static com.gabr.pos.Services.SettingService.COMPANY_SPECIALITY;
import static com.gabr.pos.Services.WindowUtils.*;

public class MainController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label user_name;
    @FXML
    private Button btn_users;
    @FXML
    private Label user_date;
    @FXML
    private Button btn_company;
    @FXML
    private Button btn_sales;
    @FXML
    private Button btn_store;
    @FXML
    private Text CompanyName;
    @FXML
    private Text CompanySpecialty;
    int ROle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            CompanyName.setText(COMPANY_NAME);
            CompanySpecialty.setText(COMPANY_SPECIALITY);
            if (UserContext.getCurrentUser() != null){
                ROle = UserContext.getCurrentUser().getRole();
                String msg = "مرحبا : " + UserContext.getCurrentUser().getName();
                user_name.setText(msg);
                if (ROle == 0) {
                    btn_users.setVisible(false);
                    btn_sales.setVisible(false);
                    btn_company.setVisible(false);
                    btn_store.setVisible(false);
                }
            }
            Image img = new Image(getClass().getResourceAsStream(WindowUtils.iconImagePath));
            image.setImage(img);
            String date = LocalDate.now() + "";
            user_date.setText("تاريخ اليوم : " + date);

        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "initialize", ex);
        }
    }


    @FXML
    public void customerPage(MouseEvent event) {
        CLOSE(event);
        OPEN_CUSTOMER_PAGE();
    }

    @FXML
    public void itemsPage(MouseEvent event) {
        CLOSE(event);
        OPEN_ITEMS_PAGE();
    }

    @FXML
    private void storesPage(MouseEvent event) {
        CLOSE(event);
        OPEN_STORE_PAGE();
    }

    @FXML
    private void salesPage(MouseEvent event) {
        CLOSE(event);
        OPEN_SALES_PAGE(LocalDate.now(), LocalDate.now());
    }

    @FXML
    private void invoicesPage(MouseEvent event) {
        CLOSE(event);
        OPEN_NEW_INVOICE_PAGE();
    }

    @FXML
    private void reportsPage(MouseEvent event) {
        CLOSE(event);
        OPEN_OFFER_PRICE_PAGE();
    }


    @FXML
    public void companes(MouseEvent event) {
        CLOSE(event);
        OPEN_COMPANIES_PAGE();
    }

    @FXML
    public void users(MouseEvent event) {
        CLOSE(event);
        OPEN_DASHBOARD_PAGE();
    }

    @FXML
    private void logout(MouseEvent event) {
        CLOSE(event);
        OPEN_LOGIN_PAGE();
    }

}
