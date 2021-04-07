import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateAccountController {

    @FXML
    private Button create;

    @FXML
    private Button logIn;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    public String userID, user, pass, mail;
    Connection connect;

    @FXML
    void onCreate(ActionEvent event) {
        DatabaseConnection connection = new DatabaseConnection();
        connect = connection.DBConnect(connect);
        checkUser();
    }

    @FXML
    void onLogin(ActionEvent event) {
        try {
            Stage stage = (Stage) logIn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registerUser() throws SQLException, ClassNotFoundException, IOException {
        user = username.getText();
        pass = password.getText();
        mail = email.getText();
        int start = 0;

        String query = "INSERT INTO customer(username, password, email) VALUE(?,?,?)";
        PreparedStatement insert = connect.prepareStatement(query);
        insert.setString(1, user);
        insert.setString(2, pass);
        insert.setString(3, mail);

        insert.execute();
        insert.close();

        String SQL = "SELECT * FROM customer WHERE username='" + user + "' and password='" + pass + "'";
        Statement checkData = connect.createStatement();
        ResultSet result = checkData.executeQuery(SQL);

        if (result.next()) {
            userID = result.getString("customer_id");
            System.out.println(userID);
        }

        Stage stage = (Stage) create.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Table.fxml"));
        Parent root = loader.load();

        TableController controller = loader.getController();
        controller.storeInfo(userID);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void checkUser() {
        try {
            String user = username.getText();
            String SQL = "SELECT *FROM customer WHERE username='" + user + "'";
            Statement checkData = connect.createStatement();
            ResultSet result = checkData.executeQuery(SQL);

            if (result.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("The username had been taken!");
                alert.setContentText("Please log in or choose a different username");
                alert.showAndWait();
            } else {
                registerUser();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
