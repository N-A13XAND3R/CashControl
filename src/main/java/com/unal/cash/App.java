package com.unal.cash;

import com.unal.cash.Model.JSON.JsonCRUD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Stack;


public class App extends Application {

    private static Stack<Parent> historialVistas = new Stack<>();
    private static Parent vistaActual;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        JsonCRUD json= new JsonCRUD();
        mostrarVista("Home.fxml"); // Use FXML filename directly
    }

    private Parent cargarVista(String nombreVista) throws Exception {
        return FXMLLoader.load(getClass().getResource(nombreVista)); // Add .fxml extension
    }

    private void cambiarVista(String nombreVista) {
        try {
            Parent vista = cargarVista(nombreVista);
            cambiarVista(vista);
        } catch (Exception e) {
            // Handle exception if FXML file is not found
            e.printStackTrace();
        }
    }

    private void cambiarVista(Parent vista) {
        historialVistas.push(vistaActual);
        stage.setScene(new Scene(vista));
        vistaActual = vista;
        stage.setTitle("CashControl");
        stage.show();
    }

    public void mostrarVista(String nombreVista) {
        cambiarVista(nombreVista);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
