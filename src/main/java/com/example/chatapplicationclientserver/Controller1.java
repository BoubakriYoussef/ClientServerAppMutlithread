package com.example.chatapplicationclientserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller1 {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/peli";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";

    public void userLogin(ActionEvent event) throws IOException {
        MainApp mainApp = new MainApp();

        // Établir une connexion à la base de données
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Préparer la requête SQL pour vérifier les informations d'identification de l'utilisateur
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username.getText());
            statement.setString(2, password.getText());

            // Exécuter la requête
            ResultSet resultSet = statement.executeQuery();

            // Vérifier si l'utilisateur existe dans la base de données
            if (resultSet.next()) {
                System.out.println("Success !");
                mainApp.changeScene("Scene2.fxml");
            } else {
                showLoginErrorAlert();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de connexion à la base de données
            showDatabaseErrorAlert();
        }
    }

    private void showLoginErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Authentication Error");
        alert.setHeaderText("Wrong username or password !");
        alert.setContentText("You can retry again !");
        alert.show();
    }

    private void showDatabaseErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText("Failed to connect to the database !");
        alert.setContentText("Please try again later.");
        alert.show();
    }
}