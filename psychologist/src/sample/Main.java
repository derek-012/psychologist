package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        View.showMainForm(primaryStage);
//        Parent root = FXMLLoader.load(getClass().getResource("designs/MainReception.fxml"));
//        primaryStage.setTitle("АИС \"Психолог\"");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.setResizable(false);
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
