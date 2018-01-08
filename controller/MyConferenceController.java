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
import java.util.Date;
import java.util.ResourceBundle;


public class MyConferenceController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private TableView<UserData> myConferenceTable;
    @FXML
    private TableColumn<UserData, String> nameCol;
    @FXML
    private TableColumn<UserData, Date> dateCol;
    @FXML
    private TableColumn<UserData, String> countryCol;
    @FXML
    private TableColumn<UserData, Integer> paymentCol;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickBack(ActionEvent actionEvent) {
        myController.setScreen(Main.screen5ID);
    }

    public void onPokaz(ActionEvent actionEvent) {
//        if(Server.getUserDataInstance().getUserData().removeAll(Server.getUserDataInstance().getUserData()))
//            System.out.println("cos bylo");

        ObservableList<UserData> dane = FXCollections.observableArrayList();

        if (Server.getInstance().conferenceAndPaymentInfo(Server.getUserInstance().getId()))
            for (UserData x : Server.getUserDataInstance().getUserData()) {
                dane.add(new UserData(x.getNazwaKonferencji(), x.getData(), x.getMiejsce(), x.getStatus()));

            }

        // Ustawienie danych dla tabeli
        myConferenceTable.itemsProperty().setValue(dane);


        nameCol.setCellValueFactory(
                new PropertyValueFactory<UserData, String>("nazwaKonferencji")
        );


        dateCol.setCellValueFactory(
                new PropertyValueFactory<UserData, Date>("data")
        );
        countryCol.setCellValueFactory(
                new PropertyValueFactory<UserData, String>("miejsce")
        );


        paymentCol.setCellValueFactory(
                new PropertyValueFactory<UserData, Integer>("status")
        );
    }
}
