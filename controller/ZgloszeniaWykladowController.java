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
import model.Zgloszenie;

import java.net.URL;
import java.util.ResourceBundle;

public class ZgloszeniaWykladowController implements Initializable, ControlledScreen {
    @FXML
    public TableColumn<Zgloszenie, String> kolumnaKonf;
    @FXML
    public TableColumn<Zgloszenie, String> kolumnaWyklad;
    @FXML
    public TableColumn<Zgloszenie, String> kolumnaZgloszenie;
    @FXML
    public TableColumn<Zgloszenie, Boolean> kolumnaButton;
    @FXML
    public TableView<Zgloszenie> tabelaZgloszenia;

    ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private Callback<TableColumn<Zgloszenie, Boolean>, TableCell<Zgloszenie, Boolean>> wezUdzialFactory =
            new Callback<TableColumn<Zgloszenie, Boolean>, TableCell<Zgloszenie, Boolean>>() {
                @Override
                public TableCell<Zgloszenie, Boolean> call(final TableColumn<Zgloszenie, Boolean> param) {
                    final TableCell<Zgloszenie, Boolean> cell = new TableCell<Zgloszenie, Boolean>() {
                        Image imgEdit = new Image(getClass().getResourceAsStream("/images/success.png"));
                        final Button btnEdit = new Button();

                        @Override
                        protected void updateItem(Boolean item, boolean empty) {
                            super.updateItem(item, empty);

                            String nazwaZgloszenia = getTableRow().getText();
                            System.out.println("nazwa " + nazwaZgloszenia);
                            int idZgloszenie = Server.getInstance().getZgloszenieID(nazwaZgloszenia);
                            btnEdit.setOnAction(e -> {
                                System.out.println("akcja!!" + idZgloszenie);
                                Server.getInstance().zatwierdzZgloszenie(idZgloszenie);
                            });

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
                    };
                    return cell;
                }
            };


    public void onClickBack(ActionEvent actionEvent) {
        myController.setScreen(Main.screen6ID);
        tabelaZgloszenia.getItems().clear();

    }

    public void onClickWyswietl(ActionEvent actionEvent) {
        ObservableList<Zgloszenie> dane = FXCollections.observableArrayList();
        int idOrganizator = Server.getUserInstance().getId();
        dane.addAll(Server.getInstance().pobierzZgloszenia(idOrganizator));

        tabelaZgloszenia.itemsProperty().setValue(dane);

        kolumnaKonf.setCellValueFactory(new PropertyValueFactory<Zgloszenie, String>("nazwaKonferencji"));

        kolumnaWyklad.setCellValueFactory(new PropertyValueFactory<Zgloszenie, String>("nazwaWykladu"));

        kolumnaZgloszenie.setCellValueFactory(new PropertyValueFactory<Zgloszenie, String>("nazwaZgloszenia"));

        kolumnaButton.setCellFactory(wezUdzialFactory);
    }
}
