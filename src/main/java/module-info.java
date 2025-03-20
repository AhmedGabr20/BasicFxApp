module com.gabr.pos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;
    requires itextpdf;
    requires java.naming;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;
    requires pdfbox.app;
    requires controlsfx;
    requires mail;
    requires activation;
    requires jbcrypt;
    requires org.apache.logging.log4j;


    opens screen to javafx.fxml;
    exports com.gabr.pos.models;
    exports com.gabr.pos.Controllers;
    opens com.gabr.pos.Controllers to javafx.fxml;
}