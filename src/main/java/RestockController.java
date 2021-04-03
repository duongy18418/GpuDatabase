import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestockController {

    @FXML
    private TextField model;

    @FXML
    private TextField made;

    @FXML
    private TextField quantity;

    @FXML
    private Button submit;

    @FXML
    private Button back;

    @FXML
    void onBack(ActionEvent event) {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Edit.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void onSubmit(ActionEvent event) {

    }

}
