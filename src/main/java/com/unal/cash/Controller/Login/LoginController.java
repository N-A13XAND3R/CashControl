package com.unal.cash.Controller.Login;

import com.unal.cash.App;
import com.unal.cash.Database.datos.Conexion;
import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Database.domain.Persona;
import com.unal.cash.Model.Login.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    Scanner sc = new Scanner(System.in);
    PersonaDAO personaDao = new PersonaDAO();
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;
    public void login(ActionEvent actionEvent) {
        
        System.out.print("Boton login presionado!!!!");

        List<Persona> personas = personaDao.seleccionar();

        boolean validacionUsuario = false;
        boolean validacionContraseña = false;
        for (Persona persona: personas){
            if (persona.getUsuario().equals(this.tfUsername.getText())){
                validacionUsuario = true;
                if (persona.getContraseña().equals(this.tfPassword.getText())){
                    validacionContraseña = true;
                }
            }
        }
        if (validacionUsuario == false || validacionContraseña == false){
            System.out.println("Datos incorrectos, vuelva a ingresarlos.");


        }
        else {
            System.out.println("\n¡Inicio de sesión exitoso!");
            SesionUsuario.asignacionUsuario(this.tfUsername.getText());



            System.out.print("DATOS DESDE CUALQUIER LUGAR ACCESIBLES: " + SesionUsuario.getUsuarioLog());

            new App().mostrarVista("AfterLoginData/AfterLogin.fxml");

        }
    }
    public void cambioInterfaz (ActionEvent event) throws IOException{
    new App().mostrarVista("Home.fxml");

    }

    public void cambioSoporte (ActionEvent event)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SobreNosotros/Soporte.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cambioDashBoard(ActionEvent event) throws IOException{
        // Implementar la lógica para cambiar al dashboard aquí
    }

    @FXML
    void eventKey(KeyEvent event) throws IOException {
        // Implementar la lógica para el evento de tecla aquí
        System.out.println("usuario: " + this.tfUsername.getText());
    }


}