package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import model.Datasource;

/**
 * Created by Alicja on 2017-11-17.
 */


public class RegistrationController implements Initializable {
    @FXML
    private TextField name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void przeslij(ActionEvent actionEvent) throws SQLException {
//
    }
}
