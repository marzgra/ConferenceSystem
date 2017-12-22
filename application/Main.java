package application;

import controller.ScreensController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Server;


public class Main extends Application {
    public static String screen1ID = "main";
    public static String screen1File = "/view/Homepage.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "/view/Registration.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "/view/LogIn.fxml";
    public static String screen4ID = "screen4";
    public static String screen4File = "/view/MyAccount.fxml";
    public static String screen5ID = "screen5";
    public static String screen5File = "/view/HomepageUP.fxml";
    public static String screen6ID = "screen6";
    public static String screen6File = "/view/HomepageOrganizator.fxml";
    public static String screen7ID = "screen7";
    public static String screen7File = "/view/EditAccount.fxml";


    @Override
    public void start(Stage primaryStage) throws Exception{
        try{

//            primaryStage.setScene(new Scene(parent, 900,600));

            ScreensController mainContainer = new ScreensController();
            mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
            mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
            mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
            mainContainer.loadScreen(Main.screen4ID, Main.screen4File);
            mainContainer.loadScreen(Main.screen5ID, Main.screen5File);
            mainContainer.loadScreen(Main.screen6ID, Main.screen6File);
            mainContainer.loadScreen(Main.screen7ID, Main.screen7File);

            mainContainer.setScreen(Main.screen1ID);

            Group root = new Group();
            root.getChildren().addAll(mainContainer);
            //Scene scene = new Scene(root);

            primaryStage.setScene(new Scene(root, 900,600));
            primaryStage.show();

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

        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    //    @Override
    public void init() throws Exception {
//        super.init();
//
        Server.getInstance().open();
        //{
//            System.out.println("FATAL ERROR: Couldn't connect to database");
//            Platform.exit();
//        }
    }
    //
//    @Override
//    public void stop() throws Exception {
//        super.stop();
//        Server.getInstance().close();
//    }
    public static void main(String[] args) {
        launch(args);
    }
}
