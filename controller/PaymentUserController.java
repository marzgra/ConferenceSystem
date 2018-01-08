package controller;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.Server;
import model.UserData;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentUserController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private TableView<UserData> UserTable;
    @FXML
    private TableColumn<UserData, String> nameCol;
    @FXML
    private TableColumn<UserData, String> conferenceCol;
    @FXML
    private TableColumn<UserData, Double> paymentCol;
    @FXML
    private TableColumn<UserData, Boolean> statusCol;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void initialize(URL location, ResourceBundle resources) {
        //Server.getUserDataInstance().getUserData().removeAll (Server.getUserDataInstance().getUserData());
        ObservableList<UserData> dane = FXCollections.observableArrayList();

        if (Server.getInstance().paymentInfo())
            for (UserData x : Server.getUserDataInstance().getUserData()) {
                dane.add(new UserData(x.getNazwaKonferencji(), x.getLogin(), x.getKwota(), x.getStatus()));

            }

        // Ustawienie danych dla tabeli
        UserTable.itemsProperty().setValue(dane);


        nameCol.setCellValueFactory(
                new PropertyValueFactory<UserData, String>("login")
        );


        conferenceCol.setCellValueFactory(
                new PropertyValueFactory<UserData, String>("nazwaKonferencji")
        );

        paymentCol.setCellValueFactory(
                new PropertyValueFactory<UserData, Double>("kwota")
        );

        statusCol.setCellFactory(cellFactory);


    }

    Callback<TableColumn<UserData, Boolean>, TableCell<UserData, Boolean>> cellFactory =
            new Callback<TableColumn<UserData, Boolean>, TableCell<UserData, Boolean>>() {
                @Override
                public TableCell<UserData, Boolean> call(final TableColumn<UserData, Boolean> param) {
                    final TableCell<UserData, Boolean> cell = new TableCell<UserData, Boolean>() {
                        Image imgEdit = new Image(getClass().getResourceAsStream("/images/plus.png"));
                        final Button btnEdit = new Button();

                        @Override
                        public void updateItem(Boolean check, boolean empty) {
                            super.updateItem(check, empty);

                            for (UserData x : Server.getUserDataInstance().getUserData()) {
                                if (x.getStatus() == 0)
                                    btnEdit.setDisable(true);
                            }


                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
//                                int selectdIndex = getTableRow().getIndex() ;
                                btnEdit.setOnAction(e -> {
//                                    System.out.println("akcja!!" + selectdIndex );
//                                    opisKonf.setText(Server.getConferenceInstance().getConferences().get(selectdIndex).getDescription());
//                                    tematKonf.setText(Server.getConferenceInstance().getConferences().get(selectdIndex).getName());
                                    System.out.println("zmieniono status platnosci......");

                                    btnEdit.setDisable(true);
                                });

                                btnEdit.setStyle("-fx-background-color: transparent;");
                                ImageView iv = new ImageView();
                                iv.setImage(imgEdit);
                                iv.setPreserveRatio(true);
                                iv.setSmooth(true);
                                iv.setCache(true);
                                btnEdit.setGraphic(iv);

                                setGraphic(btnEdit);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }

//                        }
                    };
                    return cell;
                }
            };


    public void onClickBack(ActionEvent actionEvent) {
        myController.setScreen(Main.screen6ID);
    }
}