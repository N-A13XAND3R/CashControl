package com.unal.cash.Controller.SobreNosotros;

import com.unal.cash.App;
import com.unal.cash.Model.Login.SesionUsuario;
import javafx.event.ActionEvent;

public class SobreNosotrosController {


    public void cambioInterfaz(ActionEvent actionEvent) {
        // Aquí va tu código para cambiar la interfaz
        if (SesionUsuario.session()){
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        }else{
            new App().mostrarVista("Home.fxml");
        }

    }

    public void prevSoporte(ActionEvent actionEvent) {
        // Aquí va tu código para cambiar la interfaz

            new App().mostrarVista("SobreNosotros/SobreNosotros.fxml");

    }

    public void nextSoporte(ActionEvent actionEvent) {
        new App().mostrarVista("SobreNosotros/SobreNosotros2.fxml");


    }
}
