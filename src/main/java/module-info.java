module com.example.chatapplicationclientserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.chatapplicationclientserver to javafx.fxml;
    exports com.example.chatapplicationclientserver;
}