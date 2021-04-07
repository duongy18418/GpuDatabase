import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableController implements Initializable {

    @FXML
    private TableView<ModelTable> tableView;

    @FXML
    private TableColumn<ModelTable, String> modelCol;

    @FXML
    private TableColumn<ModelTable, String> madeCol;

    @FXML
    private TableColumn<ModelTable, String> ramCol;

    @FXML
    private TableColumn<ModelTable, String> memCol;

    @FXML
    private TableColumn<ModelTable, String> boostCol;

    @FXML
    private TableColumn<ModelTable, String> priceCol;

    @FXML
    private TableColumn<ModelTable, String> stockCol;

    @FXML
    private TextField search;

    @FXML
    private Button logOut;

    @FXML
    private Button order;

    @FXML
    private Button edit;

    @FXML
    private ChoiceBox<String> category;

    @FXML
    private Button refresh;

    public String userID;
    ObservableList<ModelTable> obList = FXCollections.observableArrayList();
    Connection connect;

    @FXML
    void onEdit(ActionEvent event) {
        try {
            Stage stage = (Stage) edit.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void onLogOut(ActionEvent event) {
        try {
            Stage stage = (Stage) logOut.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void onOrder(ActionEvent event) {
        try {
            Stage stage = (Stage) order.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Purchase.fxml"));
            Parent root = loader.load();

            PurchaseController controller = loader.getController();
            controller.storeInfo(userID);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void onRefresh(ActionEvent event) {
        tableView.getItems().clear();
        try {
            ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM graphic_card");

            while (resultSet.next()) {
                obList.add(new ModelTable(resultSet.getString("model"), resultSet.getString("manufacturer"),
                        resultSet.getString("ram"), resultSet.getString("memory_type"),
                        resultSet.getString("boost_clock"), resultSet.getString("price"),
                        resultSet.getString("inventory")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        madeCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        ramCol.setCellValueFactory(new PropertyValueFactory<>("ram"));
        memCol.setCellValueFactory(new PropertyValueFactory<>("memory"));
        boostCol.setCellValueFactory(new PropertyValueFactory<>("boost"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableView.setItems(obList);
    }

    @FXML
    void onSearch(ActionEvent event) {
        String searchTerm = search.getText();
        String selectCategory = category.getValue();
        tableView.getItems().clear();

        try {
            String SQL = "SELECT * FROM graphic_card WHERE " + selectCategory + " LIKE '%" + searchTerm + "%'";
            Statement checkData = connect.createStatement();
            ResultSet resultSet = checkData.executeQuery(SQL);

            while (resultSet.next()) {
                obList.add(new ModelTable(resultSet.getString("model"), resultSet.getString("manufacturer"),
                        resultSet.getString("ram"), resultSet.getString("memory_type"),
                        resultSet.getString("boost_clock"), resultSet.getString("price"),
                        resultSet.getString("inventory")));
            }

            modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
            madeCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
            ramCol.setCellValueFactory(new PropertyValueFactory<>("ram"));
            memCol.setCellValueFactory(new PropertyValueFactory<>("memory"));
            boostCol.setCellValueFactory(new PropertyValueFactory<>("boost"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

            tableView.setItems(obList);

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setType(category);
        DatabaseConnection connection = new DatabaseConnection();
        connect = connection.DBConnect(connect);

        try {
            ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM graphic_card");

            while (resultSet.next()) {
                obList.add(new ModelTable(resultSet.getString("model"), resultSet.getString("manufacturer"),
                        resultSet.getString("ram"), resultSet.getString("memory_type"),
                        resultSet.getString("boost_clock"), resultSet.getString("price"),
                        resultSet.getString("inventory")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        madeCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        ramCol.setCellValueFactory(new PropertyValueFactory<>("ram"));
        memCol.setCellValueFactory(new PropertyValueFactory<>("memory"));
        boostCol.setCellValueFactory(new PropertyValueFactory<>("boost"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableView.setItems(obList);
    }

    private void setType(ChoiceBox<String> choice) {
        choice.getItems().addAll("Model", "Manufacturer", "Ram", "Memory_Type", "Boost_Clock");
    }

    public void storeInfo (String userID){
        this.userID = userID;

    }
}
