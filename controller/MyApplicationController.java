package controller;


import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Server;
import model.UserData;

import java.net.URL;
import java.util.ResourceBundle;

public class MyApplicationController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private TableView<UserData> applicationTable;
    @FXML
    private TableColumn<UserData, String> nameCol;
    @FXML
    private TableColumn<UserData, String> subjectCol;
    @FXML
    private TableColumn<UserData, Integer> statusCol;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void initialize(URL location, ResourceBundle resources) {


    }

    public void onClickBack(ActionEvent actionEvent) {
        myController.setScreen(Main.screen4ID);
    }

    public void onPokaz(ActionEvent actionEvent) {
        Server.getUserDataInstance().getUserData().removeAll(Server.getUserDataInstance().getUserData());
        ObservableList<UserData> dane = FXCollections.observableArrayList();

        if (Server.getInstance().applicationInfo(Server.getUserInstance().getId()))
            for (UserData x : Server.getUserDataInstance().getUserData()) {
                dane.add(new UserData(x.getNazwaKonferencji(), x.getTemat(), x.getStatus()));

            }

        // Ustawienie danych dla tabeli
        applicationTable.itemsProperty().setValue(dane);


        nameCol.setCellValueFactory(
                new PropertyValueFactory<UserData, String>("nazwaKonferencji")
        );


        subjectCol.setCellValueFactory(
                new PropertyValueFactory<UserData, String>("temat")
        );

        statusCol.setCellValueFactory(
                new PropertyValueFactory<UserData, Integer>("status")
        );
    }
}