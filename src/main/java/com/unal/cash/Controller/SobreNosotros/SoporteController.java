package com.unal.cash.Controller.SobreNosotros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.unal.cash.Model.Login.SesionUsuario;
import com.unal.cash.App;

public class SoporteController {

    @FXML
    private Label correoLabel;

    @FXML
    private Label whatsappLabel;

    @FXML
    private Label instagramLabel;

    @FXML
    protected void cambioInterfaz(ActionEvent event) {
        // Aquí va tu código para cambiar la interfaz
        if (SesionUsuario.session()){
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        }else{
            new App().mostrarVista("Home.fxml");
        }
    }
}