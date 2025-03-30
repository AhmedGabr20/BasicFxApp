package com.gabr.pos.Services;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.gabr.pos.Logging.logging.ERROR;
import static com.gabr.pos.Logging.logging.logException;

public class WindowUtils {

    /**
     * Opens a new window with the specified FXML view and custom close behavior.
     *
     * @param fxmlPath      The path to the FXML file to load.
     * @param onCloseAction The action to execute when the window is closed.
     */
    public static void OPEN_WINDOW(String fxmlPath, Runnable onCloseAction) {
        try {
            Parent root = FXMLLoader.load(WindowUtils.class.getResource(fxmlPath));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image(WindowUtils.class.getResourceAsStream(iconImagePath)));

            if (onCloseAction != null)
                // Handle window close event
                stage.setOnCloseRequest(event -> onCloseAction.run());

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CLOSE(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public static void CLOSE(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public static String iconImagePath = "/images/company_logo.png";
    public static String EXCEL_ICON = "/images/excel.png";
    public static int ALERT_WARNING = 1;
    public static int ALERT_ERROR = 2;
    public static int ALERT_CONFIRMATION = 3;
    public static int ALERT_INFORMATION = 4;

    public static final String LOGIN_PAGE = "/screen/logIn.fxml";
    public static final String ADD_CUSTOMER_PAGE = "/screen/addCustomer.fxml";
    public static final String MAIN_PAGE = "/screen/main.fxml";
    public static final String ITEMS_PAGE = "/screen/viewItems.fxml";
    public static final String STORE_PAGE = "/screen/viewStore.fxml";
    public static final String NEW_INVOICE_PAGE = "/screen/NewInvoice.fxml";
    public static final String OFFER_PRICE_PAGE = "/screen/viewOfferPrice.fxml";
    public static final String COMPANIES_PAGE = "/screen/viewCompanes.fxml";
    public static final String ADD_COMPANIES_PAGE = "/screen/addCompany.fxml";
    public static final String ADD_ITEM_PAGE = "/screen/addItem.fxml";
    public static final String VIEW_CUSTOMER_PAGE = "/screen/viewCustomer.fxml";
    public static final String CUSTOMER_PROFILE_PAGE = "/screen/customerProfile.fxml";
    public static final String VIEW_SALES_PAGE = "/screen/viewSales.fxml";
    public static final String EDIT_SALES_PAGE = "/screen/editSales.fxml";
    public static final String BACKUP_PAGE = "/screen/databaseBackup.fxml";


    // Alert
    public static void ALERT(String header, String message, int type) {
        Alert.AlertType alertType = Alert.AlertType.INFORMATION;
        if (type == 1) {
            alertType = Alert.AlertType.WARNING;
        } else if (type == 2) {
            alertType = Alert.AlertType.ERROR;
        } else if (type == 3) {
            alertType = Alert.AlertType.CONFIRMATION;
        } else if (type == 4) {
            alertType = Alert.AlertType.INFORMATION;
        }
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void OPEN_MAIN_PAGE() {
        try {
            //            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/screen/main.fxml"));
//            loader.load();
//
//            MainController mainController = loader.getController();
//        //    mainController.setText(id);
//            Parent parent = loader.getRoot();
//            Stage stage = new Stage();
//            stage.getIcons().add(new Image(getClass().getResourceAsStream(WindowUtils.iconImagePath)));
//            stage.setScene(new Scene(parent));
//            // stage.initStyle(StageStyle.UTILITY);
//            stage.show();
            OPEN_WINDOW(
                    MAIN_PAGE,
                    () -> OPEN_LOGIN_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_MAIN_PAGE", ex);

        }
    }

    public static void OPEN_LOGIN_PAGE() {
        try {
            OPEN_WINDOW(
                    LOGIN_PAGE,
                    null
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_LOGIN_PAGE", ex);
        }
    }

    public static void OPEN_ITEMS_PAGE() {
        try {
            OPEN_WINDOW(
                    ITEMS_PAGE,
                    () -> OPEN_MAIN_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_ITEMS_PAGE", ex);
        }
    }

    public static void OPEN_STORE_PAGE() {
        try {
            OPEN_WINDOW(
                    STORE_PAGE,
                    () -> OPEN_MAIN_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_STORE_PAGE", ex);
        }
    }

    public static void OPEN_NEW_INVOICE_PAGE() {
        try {
            OPEN_WINDOW(
                    NEW_INVOICE_PAGE,
                    () -> OPEN_MAIN_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_NEW_INVOICE_PAGE", ex);
        }
    }

    public static void OPEN_OFFER_PRICE_PAGE() {
        try {
            OPEN_WINDOW(
                    OFFER_PRICE_PAGE,
                    () -> OPEN_MAIN_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_OFFER_PRICE_PAGE", ex);
        }
    }

    public static void OPEN_COMPANIES_PAGE() {
        try {
            OPEN_WINDOW(
                    COMPANIES_PAGE,
                    () -> OPEN_MAIN_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_COMPANIES_PAGE", ex);
        }
    }

    public static void OPEN_BACKUP_PAGE() {
        try {
            OPEN_WINDOW(
                    BACKUP_PAGE,
                     null
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_BACKUP_PAGE", ex);
        }
    }

    public static void OPEN_ADD_ITEM_PAGE() {
        try {
            OPEN_WINDOW(
                    ADD_ITEM_PAGE,
                    () -> OPEN_ITEMS_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_ADD_ITEM_PAGE", ex);
        }
    }

    public static void OPEN_CUSTOMER_PAGE() {
        try {
            OPEN_WINDOW(
                    VIEW_CUSTOMER_PAGE,
                    () -> OPEN_MAIN_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_CUSTOMER_PAGE", ex);
        }
    }

    public static void OPEN_ADD_CUSTOMER_PAGE() {
        try {
            OPEN_WINDOW(
                    ADD_CUSTOMER_PAGE,
                    () -> OPEN_CUSTOMER_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_ADD_CUSTOMER_PAGE", ex);
        }
    }

    public static void OPEN_ADD_COMPANE_PAGE() {
        try {
            OPEN_WINDOW(
                    ADD_COMPANIES_PAGE,
                    () -> OPEN_COMPANIES_PAGE()
            );
        } catch (Exception ex) {
            logException(ERROR, WindowUtils.class.getName(), "OPEN_ADD_COMPANE_PAGE", ex);
        }
    }
//    public static void OPEN_EDIT_SALES_PAGE(LocalDate from , LocalDate to) {
//        try {
//            OPEN_WINDOW(
//                    EDIT_SALES_PAGE,
//                    () -> OPEN_SALES_PAGE(from, to)
//            );
//        } catch (Exception ex) {
//            logException(ERROR, WindowUtils.class.getName(), "OPEN_EDIT_SALES_PAGE", ex);
//        }
//    }
////    public static void OPEN_SALES_PAGE(LocalDate from, LocalDate to) {
////        try {
////            FXMLLoader loader = new FXMLLoader();
////            loader.setLocation(WindowUtils.class.getResource(VIEW_SALES_PAGE));
////            loader.load();
////            ViewSalesController viewSalesController = loader.getController();
////            viewSalesController.setTextField(from, to);
////            Scene scene = new Scene(loader.getRoot());
////            scene.setFill(Color.TRANSPARENT);
////            Stage stage = new Stage();
////            stage.setScene(scene);
////            stage.getIcons().add(new Image(WindowUtils.class.getResourceAsStream(iconImagePath)));
////            stage.setOnCloseRequest(event -> OPEN_MAIN_PAGE());
////            stage.show();
////        } catch (IOException ex) {
////            logException(ERROR, WindowUtils.class.getName(), "OPEN_SALES_PAGE", ex);
////        }
////    }
//    public static void OPEN_CUSTOMER_PROFILE(int cusId, String name) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(WindowUtils.class.getResource(CUSTOMER_PROFILE_PAGE));
//            loader.load();
//            CustomerProfileController customerProfileController = loader.getController();
//            customerProfileController.setTextField(cusId, name);
//            Parent parent = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(parent));
//            stage.setTitle("العميل:  " + name);
//            stage.getIcons().add(new Image(WindowUtils.class.getResourceAsStream(WindowUtils.iconImagePath)));
//            stage.setOnCloseRequest(event -> OPEN_CUSTOMER_PAGE());
//            stage.show();
//        } catch (Exception ex) {
//            logException(ERROR, WindowUtils.class.getName(), "OPEN_CUSTOMER_PROFILE", ex);
//        }
//
//    }
//    public static void OPEN_EDIT_CUSTOMER_PAGE(customer customer) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(WindowUtils.class.getResource(ADD_CUSTOMER_PAGE));
//            loader.load();
//            AddCustomerController addCustomerController = loader.getController();
//            addCustomerController.setTextField(customer,true);
//            Scene scene = new Scene(loader.getRoot());
//            scene.setFill(Color.TRANSPARENT);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.getIcons().add(new Image(WindowUtils.class.getResourceAsStream(WindowUtils.iconImagePath)));
//            stage.setOnCloseRequest(event -> OPEN_CUSTOMER_PAGE());
//            stage.show();
//        } catch (Exception ex) {
//            logException(ERROR, WindowUtils.class.getName(), "OPEN_EDIT_CUSTOMER_PAGE", ex);
//        }
//    }

//    public static void OPEN_EDIT_ITEM_PAGE(items item) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(ViewItemsController.class.getResource(ADD_ITEM_PAGE));
//            loader.load();
//            AddItemController addItemController = loader.getController();
//            addItemController.setTextField(item,true);
//            Scene scene = new Scene(loader.getRoot());
//            scene.setFill(Color.TRANSPARENT);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.getIcons().add(new Image(ViewItemsController.class.getResourceAsStream(WindowUtils.iconImagePath)));
//            stage.setOnCloseRequest(event -> OPEN_ITEMS_PAGE());
//            stage.show();
//        } catch (Exception ex) {
//            logException(ERROR, WindowUtils.class.getName(), "OPEN_EDIT_ITEM_PAGE", ex);
//        }
//    }

}

