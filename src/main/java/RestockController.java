import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestockController implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private TextField quantity;

    @FXML
    private Button submit;

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> made;
    Connection connect;

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
    void onSubmit(ActionEvent event) throws SQLException {
        int graphicId = Integer.parseInt(id.getText());
        String selectMade = made.getValue();
        int graphicQuantity = Integer.parseInt(quantity.getText());

        if (selectMade.equals("NVIDIA")){
            String SQL = "INSERT INTO nvidia_request(graphic_id, quantity) VALUE(?,?)";
            PreparedStatement insert = connect.prepareStatement(SQL);
            insert.setInt(1, graphicId);
            insert.setInt(2, graphicQuantity);

            insert.execute();
            insert.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Insert Successful");
            alert.setHeaderText("You have successfully insert new restock request!");
            alert.showAndWait();

        } else if (selectMade.equals("AMD")){
            String SQL = "INSERT INTO amd_request(graphic_id, quantity) VALUE(?,?)";
            PreparedStatement insert = connect.prepareStatement(SQL);
            insert.setInt(1, graphicId);
            insert.setInt(2, graphicQuantity);

            insert.execute();
            insert.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Insert Successful");
            alert.setHeaderText("You have successfully insert new restock request!");
            alert.showAndWait();
        }
    }

    private void setType(ChoiceBox<String> choice) {
        choice.getItems().addAll("NVIDIA", "AMD");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setType(made);
        DatabaseConnection connection = new DatabaseConnection();
        connect = connection.DBConnect(connect);
    }
}
