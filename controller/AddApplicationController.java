package controller;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Server;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class AddApplicationController implements Initializable, ControlledScreen {
    @FXML
    public ChoiceBox choiceBoxLecture;
    @FXML
    public ChoiceBox choiceBoxConference;
    @FXML
    public TextField lecturesTopic;

    ScreensController myController;
    private String nazwaKonf;
    private String nazwaWyk;
    private File destinationFile;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listaKonf = FXCollections.observableArrayList();
        listaKonf.addAll(Server.getInstance().getFutureConferences());

        choiceBoxConference.setItems(listaKonf);
    }

    public void showLectures(){
        nazwaKonf = choiceBoxConference.getSelectionModel().getSelectedItem().toString();
        System.out.println(nazwaKonf);
        ObservableList<String> listaLectures = FXCollections.observableArrayList();
        listaLectures.addAll(Server.getInstance().getLecturesByConfName(nazwaKonf));

        choiceBoxLecture.setItems(listaLectures);
    }


    public void selectFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Pliki PDF", "*.pdf"));

        File file = fileChooser.showOpenDialog(null);
        System.out.println(file.getPath());
        nazwaWyk = choiceBoxLecture.getSelectionModel().getSelectedItem().toString();
        String filename = nazwaKonf + nazwaWyk + Server.getInstance().createApplicationFilename() + ".pdf";

        destinationFile = new File("C:\\temp\\" + filename.replaceAll("\\s",""));
        try {
            Files.copy(file.toPath(), destinationFile.toPath());
            System.out.println(destinationFile.getPath());
            System.out.println(filename);
        }catch (IOException e){
            System.out.println("AddaplicationController error: " + e.getMessage());
        }
    }

    public void onClickSave(ActionEvent actionEvent){
        String nazwaKonf = choiceBoxConference.getSelectionModel().getSelectedItem().toString();
        String nazwaWyk = choiceBoxLecture.getSelectionModel().getSelectedItem().toString();
        String temat = lecturesTopic.getText();

        Server.getInstance().saveApplicationToDatabase(destinationFile, nazwaKonf, nazwaWyk, temat);
        myController.setScreen(Main.screen16ID);
    }

    public void onClickBack(ActionEvent actionEvent) {
        myController.setScreen(Main.screen16ID);
    }
}
