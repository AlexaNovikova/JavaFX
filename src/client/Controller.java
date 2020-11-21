package client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable{
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField textField;

    private Network network;

    ObservableList<String> words= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void addMessage (){
        String message = textField.getText();
        if (!message.isBlank())
        {
        listView.getItems().addAll(message);
        textField.setText("");}

    }
    public void Exit (){
        Platform.exit();
    }
    public void Reset(){
    listView.getItems().clear();
    }
    public void messageWindow (){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе.");
        alert.setHeaderText(null);
        alert.setContentText("Простой мессенджер позволяет пользователям обмениваться сообщениями. Версия: 1.0. Разработчик: Новикова А.А.");
        alert.setHeight(300);
        alert.showAndWait();

    }
}
