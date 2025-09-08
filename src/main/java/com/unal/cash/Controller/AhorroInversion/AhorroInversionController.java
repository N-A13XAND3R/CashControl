package com.unal.cash.Controller.AhorroInversion;

import com.unal.cash.App;
import com.unal.cash.Model.Login.SesionUsuario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AhorroInversionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    void initialize() {

    }
    @FXML
    void CambiarBitcoin(MouseEvent event) {
        if (SesionUsuario.session()) {
            new App().mostrarVista("AhorroInversion/AhorroInversionBitcoin.fxml");
        }
    }

    @FXML
    void CambiarNasdaq(MouseEvent event) {
        if (SesionUsuario.session()) {
            new App().mostrarVista("AhorroInversion/AhorroInversionNasdaq.fxml");
        }
    }

    @FXML
    void CambiarOro(MouseEvent event) {
        if (SesionUsuario.session()) {
            new App().mostrarVista("AhorroInversion/AhorroInversionOro.fxml");
        }
    }




    @FXML
    void CambiarSP500(MouseEvent event) {
        if (SesionUsuario.session()) {
            new App().mostrarVista("AhorroInversion/AhorroInversionSP500.fxml");
        }
    }

    @FXML
    void cambioInterfaz(ActionEvent event) {
        if (SesionUsuario.session()) {
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        }else {
            new App().mostrarVista("Home.fxml");
        }
    }

    public void CambiarTipoDeActivo(ActionEvent actionEvent) {
        if (SesionUsuario.session()) {
            new App().mostrarVista("AhorroInversion/SeleccioneAhorroInversion.fxml");
        }
    }
}

