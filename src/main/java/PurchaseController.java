import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseController implements Initializable {

    //@FXML
    //private TextField model;

    @FXML
    private TextField quantity;

    @FXML
    private Button purchase;

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> made;

    @FXML
    private ChoiceBox<String> model;

    public String userID;
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
    void onPurchase(ActionEvent event) throws SQLException {
        String purchaseModel = model.getValue();
        String purchaseMade = made.getValue();
        int purchaseQuantity = Integer.parseInt(quantity.getText());
        int purchaseGrapId = 0;

        String SQL1 = "SELECT graphic_id FROM graphic_card WHERE model='" + purchaseModel + "'";
        Statement checkData = connect.createStatement();
        ResultSet result = checkData.executeQuery(SQL1);

        if (result.next()){
            purchaseGrapId = result.getInt(1);
        }

        String SQL2 = "INSERT INTO purchase(customer_id, graphic_id, quantity) VALUE(?,?,?)";
        PreparedStatement insert = connect.prepareStatement(SQL2);
        insert.setInt(1, Integer.parseInt(userID));
        insert.setInt(2, purchaseGrapId);
        insert.setInt(3, purchaseQuantity);

        insert.execute();
        insert.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Purchase Submit Successful");
        alert.setHeaderText("You have successfully submit a purchase request!");
        alert.showAndWait();
    }

    public void storeInfo (String userID){
        this.userID = userID;
    }

    private void setType(ChoiceBox<String> choice) throws SQLException {
        choice.getItems().addAll("NVIDIA", "AMD");

        ResultSet resultSet = connect.createStatement().executeQuery("SELECT model FROM graphic_card");

        while (resultSet.next()) {
            model.getItems().add(resultSet.getString("model"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        made.getItems().addAll("NVIDIA", "AMD");

        DatabaseConnection connection = new DatabaseConnection();
        connect = connection.DBConnect(connect);
        try {
            ResultSet resultSet = connect.createStatement().executeQuery("SELECT model FROM graphic_card");

            while (resultSet.next()) {
                model.getItems().add(resultSet.getString("model"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
