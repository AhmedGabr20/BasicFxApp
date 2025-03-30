package com.gabr.pos.Controllers;

import com.gabr.pos.Services.BackupTask;
import com.gabr.pos.models.BackupResult;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static com.gabr.pos.Services.SettingService.DATABASES_BACKUP;

public class DatabaseBackup implements Initializable {

    @FXML
    private TableView<BackupResult> databaseTable;

    @FXML
    private TableColumn<String, String> editCol;

    @FXML
    private TableColumn<String, String> resultcol;

    @FXML
    private TableColumn<String, String> datacol;

    @FXML
    private TableColumn<String, String> namecol;
    @FXML
    private Label failed_count;

    @FXML
    private Label success_count;
    @FXML
    private Label total_count;

    private ObservableList<BackupResult> backupResultList = FXCollections.observableArrayList();
    private static final List<String> DATABASES = List.of("POS", "Market"); // List of databases to back up
    int success = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        datacol.setCellValueFactory(new PropertyValueFactory<>("data"));
        resultcol.setCellValueFactory(new PropertyValueFactory<>("result"));
        editCol.setCellValueFactory(new PropertyValueFactory<>("icon"));

        namecol.setStyle("-fx-alignment: CENTER;-fx-font-size:20px;-fx-font-weight:bold;");
        datacol.setStyle("-fx-alignment: CENTER;-fx-font-size:20px;-fx-font-weight:bold;");
        resultcol.setStyle("-fx-alignment: CENTER;-fx-font-size:20px;-fx-font-weight:bold;");
        editCol.setStyle("-fx-alignment: CENTER;-fx-font-size:20px;-fx-font-weight:bold;");

        // Set row factory to change row color based on result
        databaseTable.setRowFactory(tv -> {
            TableRow<BackupResult> row = new TableRow<>();

            // Add listener to item property to update the row style based on result
            row.itemProperty().addListener((ObservableValue<? extends BackupResult> observable, BackupResult oldValue, BackupResult newValue) -> {
                if (newValue != null) {
                    if ("Success".equalsIgnoreCase(newValue.getResult())) {
                        success ++ ;
                        row.setStyle("-fx-background-color: lightgreen;");
                    } else if ("Failed".equalsIgnoreCase(newValue.getResult())) {
                        row.setStyle("-fx-background-color: lightcoral;");
                    } else {
                        row.setStyle(""); // Reset row style if no condition matches
                    }
                }
            });

            return row;
        });
        success_count.setText(success+"");

        //    databaseTable.getColumns().addAll(namecol, datacol, resultcol);
        databaseTable.setItems(backupResultList);

        startBackup();
        // Schedule backup every 24 hour
        Timeline timeline = new Timeline(new KeyFrame(Duration.hours(24), e -> startBackup()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void startBackup() {

        backupResultList.clear();
        String[] databaseList = DATABASES_BACKUP.split(",");
        total_count.setText(databaseList.length+"");
        for (String dbName : databaseList) {
            Task<BackupResult> backupTask = new BackupTask(dbName);
            backupTask.setOnSucceeded(e -> {
                backupResultList.add(backupTask.getValue());
            });
            new Thread(backupTask).start();
        }
    }


}