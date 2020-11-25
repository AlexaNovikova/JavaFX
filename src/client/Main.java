package client;

import com.sun.scenario.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 421, 400));
        primaryStage.show();


       Network network = new Network();
       if (!network.Connect()) {
       showErrorMessage("","Ошибка подключения к серверу.");
       }
       Controller controller = loader.getController();
       controller.setNetwork(network);
//       Thread thread= new Thread(()->
//       {
//           String serverMessage = null;
//           try {
//               serverMessage = network.getDataInputStream().readUTF();
//               controller.appendMessage(serverMessage);
//           } catch (IOException e) {
//               e.printStackTrace();
//           }
//
//       });
      Thread thread= new Thread(() -> network.waitMessage(controller));
       // network.waitMessage(controller);
        thread.setDaemon(true);
        thread.start();
        primaryStage.setOnCloseRequest((windowEvent -> {
            network.setDataOutputStream("exit");
            network.Close();
            thread.interrupt();
            Platform.exit();
        }));
    }

   public static void showErrorMessage (String message, String errorMessage){
    Alert alert= new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Ошибка.");
    alert.setHeaderText(errorMessage);
    alert.setContentText(message);
    alert.setHeight(300);
    alert.showAndWait();

}

    public static void main(String[] args) {
        launch(args);

    }
}
