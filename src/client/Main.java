package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.Socket;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = new FXMLLoader().getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Let's Talk!");
        primaryStage.setScene(new Scene(root, 421, 400));
//        Network network = new Network();
//        if (!network.Connect()) {
//            System.out.println("Ошибка подключения!");
//        }
//        Controller controller = loader.getController();
//        controller.setNetwork(network);

    }



    public static void main(String[] args) {
        launch(args);
    }
}
