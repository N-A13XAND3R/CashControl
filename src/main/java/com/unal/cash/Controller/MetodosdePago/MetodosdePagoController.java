package com.unal.cash.Controller.MetodosdePago;


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

public class MetodosdePagoController {

    public Button btEfectivo;
    public Button btTarjetaDebito;
    public Button btTransaccionAplicacion;
    public Button btTarjetaCredito;

    public TextField txtCostoPorTransaccion;
    public TextField txtCashBack;
    public TextField txtIntereses;
    public TextField txt_descuento;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private String metodo;

    private final String[] metodosPago = {"Tarjeta de Débito", "Tarjeta de Crédito", "Efectivo","Transacción por aplicación"};
    private PersonaDAO personaDao= new PersonaDAO();


    @FXML
    void initialize() {

    }

    public void cambioInterfaz(ActionEvent actionEvent) {
        // Aquí va tu código para cambiar la interfaz
        if (SesionUsuario.session()){
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        }else{
            new App().mostrarVista("Home.fxml");
        }
    }

    public void cambiarEfectivo(ActionEvent actionEvent) {
        this.metodo = this.metodosPago[0];
        this.cambiarActualizacionMetodosdePago(actionEvent);    }
    public void cambiarTarjetaCredito(ActionEvent actionEvent) {
        this.metodo = this.metodosPago[1];
        this.cambiarActualizacionMetodosdePago(actionEvent);    }
    public void cambiarTarjetaDebito(ActionEvent actionEvent) {
        this.metodo = this.metodosPago[2];
        this.cambiarActualizacionMetodosdePago(actionEvent);    }

    public void cambiarTransaccionAplicacion(ActionEvent actionEvent) {
        this.metodo = this.metodosPago[3];
        this.cambiarActualizacionMetodosdePago(actionEvent);
    }

    private void cambiarActualizacionMetodosdePago(ActionEvent actionEvent){
        new App().mostrarVista("MetodosdePago/ActualizacionMetodosdePago.fxml");
    }

    public void cambioMetodosdePago(ActionEvent actionEvent) {
        new App().mostrarVista("MetodosdePago/MetodosdePago.fxml");
    }

    public void ActualizarCaracteristicasPago(ActionEvent actionEvent) {
    //TODO: llamar a bases de datos y actualizar caracterisitcas de pago!

        if(SesionUsuario.session()){
            try {
                Persona P = this.personaDao.getPersona(SesionUsuario.getUsuarioLog());
                P.setMetodopagomasusado(this.metodo);


                System.out.println("Caracteristicas de pago actualizadas correctamente!!");
                System.out.println("persona:\n" + P.toString());
                new App().mostrarVista("MetodosdePago/MetodosdePagoActualizados.fxml");
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
}
