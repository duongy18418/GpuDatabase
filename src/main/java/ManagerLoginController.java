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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerLoginController {

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button back;

    public String user, pass, email;
    Connection connect;

    @FXML
    void onBack(ActionEvent event) {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void onLogin(ActionEvent event) {
        DatabaseConnection connection = new DatabaseConnection();
        connect = connection.DBConnect(connect);
        checkManager();
    }

    private void checkManager() {
        try {
            user = username.getText();
            pass = password.getText();
            String SQL = "SELECT * FROM manager WHERE username='" + user + "' and password='" + pass + "'";
            Statement checkData = connect.createStatement();
            ResultSet result = checkData.executeQuery(SQL);

            if (result.next()) {
                user = result.getString("username");
                pass = result.getString("password");
                email = result.getString("email");

                Stage stage = (Stage) login.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit.fxml"));
                Parent root = loader.load();

                //TableController controller = loader.getController();
                //controller.setInfo(user, pass, email);
                //controller.displayInfo(first, last, income, bill, saving, period);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("The username or password were incorrect!");
                alert.setContentText("Please try again or create a new account");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
