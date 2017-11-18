package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;



import controller.MainController;
import model.Datasource;


public class Main extends Application {
    @FXML
    private MainController MainController;
    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/panelG.fxml"));
            Parent parent = loader.load();
            //Scene scene = new Scene(parent);
            primaryStage.setTitle("System wspomagający organizacje konferencji");
            primaryStage.setScene(new Scene(parent, 700,400));
            primaryStage.show();
            //primaryStage.setResizable(false);

            primaryStage.setOnCloseRequest((WindowEvent we) -> {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Ostrzezenie");
                a.setHeaderText("Czy na pewno chcesz zakonczyc program?");
                a.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK)
                    {
                        Platform.exit();

                    } else
                    {
                        we.consume();
                    }
                });
            });
            MainController = ( MainController) loader.getController();
            MainController.setPrevStage(primaryStage);

            System.out.println( MainController);
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void init() throws Exception {
        super.init();
        if(!Datasource.getInstance().open()) {
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
