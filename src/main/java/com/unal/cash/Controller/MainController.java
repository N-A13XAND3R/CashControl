package com.unal.cash.Controller;

import com.unal.cash.App;
import com.unal.cash.Model.JSON.JsonCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean session = false;

    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;


    public void cambioInterfaz (ActionEvent event) throws IOException{

        App app = new App();
        app.mostrarVista("Home.fxml");


    }
    public void cambioInicioSesion (ActionEvent event) throws IOException {
        App app = new App();
        app.mostrarVista("Login/InicioSesion.fxml");
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login/InicioSesion.fxml"));

    }
    public void cambioCrearCuenta (ActionEvent event)throws IOException{
        new App().mostrarVista("Login/CrearCuenta.fxml");
    }
    public void cambioSoporte (ActionEvent event)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SobreNosotros/Soporte.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cambioDashBoard (ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DashBoard/DashBoard.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cambioGastosReccurrentes (ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login/GastosRecurrentes.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void eventKey(KeyEvent keyEvent) {
    }


}
