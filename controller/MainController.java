package controller;

import application.Main;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import model.Conference;
import model.Server;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.*;


public class MainController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private TableView<Conference> conferenceTableView;
    @FXML
    private TableColumn<Conference, String> nameColumn;
    @FXML
    private TableColumn<Conference, String> descColumn;
    @FXML
    private TableColumn<Conference, Boolean> categoryColumn;
    @FXML
    private TableColumn<Conference, Date> dateColumn;
    @FXML
    private Label tematKonf;
    @FXML
    private TextArea opisKonf;


    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tematKonf.setText("Wszystko co chcesz dowiedzieć sie o konferencjach....");
        opisKonf.setText("Wszystko co chcesz dowiedzieć sie o konferencjach....\nWszystko co chcesz dowiedzieć sie o konferencjach....\nWszystko co chcesz dowiedzieć sie o konferencjach....");
        ObservableList<Conference> dane = FXCollections.observableArrayList();


        int i = 1;
        if (Server.getInstance().getConferenceInfo())
            for (Conference x : Server.getConferenceInstance().getConferences()) {
                dane.add(new Conference(x.getId(), x.getData(), x.getName(), x.getDescription()));


            }

        // Ustawienie danych dla tabeli
        conferenceTableView.itemsProperty().setValue(dane);

        // Powiązanie pierwszej kolumny z polem nazwa obiektu typu Seria
        descColumn.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("name")
        );
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<Conference, Date>("data")
        );


        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("description")
        );
        categoryColumn.setCellFactory(cellFactory);

    }

    Callback<TableColumn<Conference, Boolean>, TableCell<Conference, Boolean>> cellFactory =
            new Callback<TableColumn<Conference, Boolean>, TableCell<Conference, Boolean>>() {
                @Override
                public TableCell<Conference, Boolean> call(final TableColumn<Conference, Boolean> param) {
                    final TableCell<Conference, Boolean> cell = new TableCell<Conference, Boolean>() {
                        Image imgEdit = new Image(getClass().getResourceAsStream("/images/About_26px.png"));
                        final Button btnEdit = new Button();

                        @Override
                        public void updateItem(Boolean check, boolean empty) {
                            super.updateItem(check, empty);
                            Server.getConferenceInstance().getConferences().removeAll(Server.getConferenceInstance().getConferences());
                            if (Server.getInstance().getConferenceInfo())
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                int selectdIndex = getTableRow().getIndex();
                                btnEdit.setOnAction(e -> {
                                    System.out.println("akcja!!" + selectdIndex);
                                    opisKonf.setText(Server.getConferenceInstance().getConferences().get(selectdIndex).getDescription());
                                    tematKonf.setText(Server.getConferenceInstance().getConferences().get(selectdIndex).getName());
                                   // System.out.println(Server.getConferenceInstance().getConferences().get(1).toString());
                                   // System.out.println(Server.getConferenceInstance().getConferences().get(1).toString());
                                   // System.out.println(Server.getConferenceInstance().getConferences().get(2).toString());
                                  //  System.out.println(Server.getConferenceInstance().getConferences().get(3).toString());

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


    @FXML
    public void onClickZaloguj(ActionEvent event) {
        myController.setScreen(Main.screen3ID);

    }

    @FXML
    public void onClickZapisz(ActionEvent event) {
        myController.setScreen(Main.screen2ID);

    }


    public void eksportPDF(ActionEvent actionEvent) {
        Document document = new Document();
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Wybierz miejsce do zapisu");
            File outputFolder = directoryChooser.showDialog(null);

            System.out.println(outputFolder.getAbsolutePath());


            PdfWriter.getInstance(document,
                    new FileOutputStream(outputFolder.getAbsolutePath() + "/Konferencja.pdf"));
            document.open();
            document.add(new Paragraph(tematKonf.getText()));
            document.add(new Paragraph(opisKonf.getText()));
            document.setPageSize(PageSize.A4);
            document.close();


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void onClickWyszukaj(ActionEvent actionEvent) {
        myController.setScreen(Main.screen13ID);
    }
}