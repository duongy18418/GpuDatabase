import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
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

    public String graphModel, graphMade, graphMemory;
    public int graphRam, graphStock;
    public double graphBoost, graphPrice;
    Connection connect;

    @FXML
    void onAdd(ActionEvent event) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        connect = connection.DBConnect(connect);

        graphModel = model.getText();
        graphMade = made.getText();
        graphRam = Integer.parseInt(ram.getText());
        graphBoost = Double.parseDouble(boost.getText());
        graphMemory = memory.getText();
        graphPrice = Double.parseDouble(price.getText());
        graphStock = Integer.parseInt(inventory.getText());

        String SQL = "INSERT INTO graphic_card(model, manufacturer, ram, memory_type, boost_clock, price, inventory) VALUE(?,?,?,?,?,?,?)";
        PreparedStatement insert = connect.prepareStatement(SQL);
        insert.setString(1, graphModel);
        insert.setString(2, graphMade);
        insert.setInt(3, graphRam);
        insert.setString(4, graphMemory);
        insert.setDouble(5, graphBoost);
        insert.setDouble(6, graphPrice);
        insert.setInt(7, graphStock);

        insert.execute();
        insert.close();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Insert Successful");
        alert.setHeaderText("You have successfully insert new data!");
        alert.showAndWait();
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
    void onDelete(ActionEvent event) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        connect = connection.DBConnect(connect);

        graphModel = model.getText();
        graphMade = made.getText();

        String SQL = "DELETE FROM graphic_card WHERE model='" + graphModel + "' and manufacturer='" + graphMade + "'";
        PreparedStatement result = connect.prepareStatement(SQL);

        result.executeUpdate();
        result.close();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Successful");
        alert.setHeaderText("You have successfully delete the data!");
        alert.showAndWait();
    }

    @FXML
    void onUpdate(ActionEvent event) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        connect = connection.DBConnect(connect);

        graphModel = model.getText();
        graphMade = made.getText();
        graphRam = Integer.parseInt(ram.getText());
        graphBoost = Double.parseDouble(boost.getText());
        graphMemory = memory.getText();
        graphPrice = Double.parseDouble(price.getText());
        graphStock = Integer.parseInt(inventory.getText());

        String SQL = "UPDATE graphic_card SET ram='" + graphRam + "', memory_type='" + graphMemory + "', boost_clock='" + graphBoost +
                "', price='" + graphPrice + "', inventory='" + graphStock +
                "' WHERE model='" + graphModel + "' and manufacturer='" + graphMade + "'";
        PreparedStatement insert = connect.prepareStatement(SQL);

        insert.execute();
        insert.close();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Successful");
        alert.setHeaderText("You have successfully update the data!");
        alert.showAndWait();

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
