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

public class EditController {

    @FXML
    private TextField model;

    @FXML
    private TextField made;

    @FXML
    private TextField ram;

    @FXML
    private TextField boost;

    @FXML
    private TextField memory;

    @FXML
    private TextField price;

    @FXML
    private TextField inventory;

    @FXML
    private Button update;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Button back;

    @FXML
    private Button restock;

    @FXML
    void onAdd(ActionEvent event) {

    }

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
    void onDelete(ActionEvent event) {

    }

    @FXML
    void onUpdate(ActionEvent event) {

    }

    @FXML
    void onRestock(ActionEvent event) {
        try {
            Stage stage = (Stage) restock.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Restock.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
