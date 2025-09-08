package com.unal.cash.Controller.RegistroIngresosMensuales;

import java.net.URL;
import java.util.ResourceBundle;

import com.unal.cash.App;
import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Database.domain.Persona;
import com.unal.cash.Model.Login.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistroIngresosMensualesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_Continuar;

    @FXML
    private TextField txt_salario;


    @FXML
    void initialize() {
        assert bt_Continuar != null : "fx:id=\"bt_Continuar\" was not injected: check your FXML file 'IngresosMensuales.fxml'.";
        assert txt_salario != null : "fx:id=\"txt_salario\" was not injected: check your FXML file 'IngresosMensuales.fxml'.";

    }

    @FXML
    void CambiarSoporte(ActionEvent event) {
        new App().mostrarVista("SobreNosotros/Soporte.fxml");
    }

    @FXML
    public void cambioInterfaz(ActionEvent mouseEvent) {
        // Aquí va tu código para cambiar la interfaz
        if (SesionUsuario.session()){
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        }else{
            new App().mostrarVista("Home.fxml");
        }
    }


    @FXML
    void CambiarContinuar(ActionEvent event) {
        if(SesionUsuario.session()){
            try {
                PersonaDAO P0 = new PersonaDAO();
                Persona P = P0.getPersona(SesionUsuario.getUsuarioLog());
                P.setIngresosmensuales(Double.parseDouble(this.txt_salario.getText()));
                P0.actualizar(P);
                new App().mostrarVista("DashBoard/DashBoard.fxml");
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

}
