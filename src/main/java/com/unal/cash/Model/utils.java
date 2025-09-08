package com.unal.cash.Model;

import com.unal.cash.Controller.Login.AfterLoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class utils {

    public void cambiarVista(String fxml, Node node) {
        try {
            // Crea un nuevo FXMLLoader
            FXMLLoader loader = new FXMLLoader();

            // Establece la ubicación del archivo FXML que quieres cargar
            loader.setLocation(getClass().getResource(fxml));

            // Crea una nueva instancia del controlador
            AfterLoginController controller = new AfterLoginController();

            // Establece el controlador del FXMLLoader
            loader.setController(controller);

            // Carga el archivo FXML
            Parent root = loader.load();

            // Obtiene la escena actual y establece la nueva raíz
            Scene scene = node.getScene();
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
