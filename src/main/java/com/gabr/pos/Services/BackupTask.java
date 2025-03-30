package com.gabr.pos.Services;

import com.gabr.pos.models.BackupResult;
import javafx.concurrent.Task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static com.gabr.pos.Logging.logging.ERROR;
import static com.gabr.pos.Logging.logging.logException;
import static com.gabr.pos.Services.SettingService.DATABASE_PASS;
import static com.gabr.pos.Services.SettingService.DATABASE_USER;

public class BackupTask extends Task<BackupResult> {

    private final String databaseName;

    public BackupTask(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    protected BackupResult call() {
        String timestamp = new SimpleDateFormat("dd/MM/yyyy _ hh:mm:ss").format(new Date());
        String folderPath = "\\"+ LocalDate.now() ;
        File folder = new File(folderPath);
        if (!folder.exists()) {
             folder.mkdir();
        }
        String savePath = folderPath+"\\backup_" + databaseName + "_" + LocalDate.now() + ".sql";
        String executeCmd = "c:\\Program Files\\MySQL\\MySQL Workbench 8.0\\mysqldump --no-defaults --user=" + DATABASE_USER +
                " --password=" + DATABASE_PASS + " -h localhost " + databaseName + " -r " + savePath;

        try {
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return new BackupResult(databaseName, timestamp, "Success"," ✅ ");
            } else {
                File file = new File(savePath);
                if (file.exists()) {
                    // Try to delete the file
                    boolean deleted = file.delete();
                    if (deleted) {
                        System.out.println("File deleted successfully: " + savePath);
                    } else {
                        System.out.println("Failed to delete the file: " + savePath);
                    }
                } else {
                    System.out.println("File does not exist: " + savePath);
                }
                return new BackupResult(databaseName, timestamp, "Failed"," ❌ ");
            }
        } catch (Exception ex) {
            logException(ERROR, this.getClass().getName(), "initialize", ex);
            return new BackupResult(databaseName, timestamp, "Failed"," ❌ ");
        }
    }

}
