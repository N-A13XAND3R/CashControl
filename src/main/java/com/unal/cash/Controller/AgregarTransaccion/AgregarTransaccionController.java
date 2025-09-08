package com.unal.cash.Controller.AgregarTransaccion;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import com.unal.cash.App;
import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Database.datos.TransaccionesDAO;
import com.unal.cash.Database.domain.Persona;
import com.unal.cash.Model.tranYmetpago.Transaccion;
import com.unal.cash.Model.JSON.JsonCRUD;
import com.unal.cash.Model.Login.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AgregarTransaccionController {

    public TextField txt_costo_transaccion;
    public DatePicker dt_fecha;
    public TextField txt_monto;
    public TextField txt_intereses;
    public TextField txt_descuento;
    public TextField txt_cashback;

    @FXML
    private Button btGuardarTransaccion;

    @FXML
    private ChoiceBox<String> cbTipoTransaccion;

    private final String[] tipoTransaccion = {"Tarjeta de Débito", "Tarjeta de Crédito", "Efectivo", "Transacción por aplicación"};

    private String cbTipoTransaccionSelected;

    private PersonaDAO personaDao = new PersonaDAO();
    private TransaccionesDAO transaccionesDAO = new TransaccionesDAO();


    @FXML
    void initialize() {

        if (this.cbTipoTransaccion == null) this.cbTipoTransaccion = new ChoiceBox<String>();

        this.cbTipoTransaccion.getItems().addAll(this.tipoTransaccion);
        this.cbTipoTransaccion.setOnAction(this::getTipoTransaccion);

        if (this.dt_fecha == null) this.dt_fecha = new DatePicker();
        // Establecer la fecha actual como valor predeterminado
        this.dt_fecha.setValue(LocalDate.now());

        // Formatear la fecha en el formato "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = this.dt_fecha.getValue().format(formatter);
        System.out.println(fechaFormateada);  // Imprime la fecha en el formato "dd/MM/yyyy"


    }


    private void getTipoTransaccion(ActionEvent actionEvent) {
        this.cbTipoTransaccionSelected = this.cbTipoTransaccion.getValue();

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

    public void GuardarTransaccion(ActionEvent actionEvent) {
        //TODO: guardar transaccion en una tabla posiblemente distinta a la de usuarios

        try {

            String usuario = SesionUsuario.getUsuarioLog();
            String fecha = this.dt_fecha.getValue().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = this.dt_fecha.getValue().format(formatter);
            System.out.println("Fecha: " + fecha);
            fecha = fechaFormateada;
            String monto = this.txt_monto.getText();
            String intereses = this.txt_intereses.getText();
            String descuento = this.txt_descuento.getText();
            String cashback = this.txt_cashback.getText();
            String costoTransaccion = this.txt_costo_transaccion.getText();

            String[] items = {monto, intereses, descuento, cashback, costoTransaccion};

            double[] values = new double[items.length];

            for (int i = 0; i < items.length; i++)
                values[i] = items[i].isEmpty() ? 0 : Double.parseDouble(items[i]);



            double montoFinal = calcularMontoFinal(values[0], values[1], values[2], values[3], values[4]);


            // Verificar que la fecha no sea nula antes de agregarla a la lista

            // Pasar los datos a JsonCRUD para guardarlos en el JSON
            List<String> data = List.of(usuario, fecha, this.cbTipoTransaccionSelected, String.valueOf(monto),
                    String.valueOf(intereses), String.valueOf(cashback),
                    String.valueOf(descuento), String.valueOf(costoTransaccion));
            JsonCRUD.agregarEntradaAlJSON(data, montoFinal);
            System.out.println("\n\n¡Registro de información exitosa!\n\n");

            System.out.println("¡Registro de datos exitoso!");
            List<Transaccion> listaT = JsonCRUD.getTransaccionesList(usuario);
            System.out.println("Lista de transacciones para este usario: \n" + listaT);
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public static Double convertStringToDouble(String str) {
        // Reemplaza las comas por puntos
        String replacedStr = str.replace(',', '.');

        try {
            // Intenta convertir el string a double
            return Double.parseDouble(replacedStr);
        } catch (NumberFormatException e) {
            // Imprime un mensaje de error si el string no puede ser convertido a double
            System.out.println("Error: El string contiene caracteres no numéricos o no es un número válido.");
            return null;
        }
    }

    public static Integer convertStringToInt(String str) {
        try {
            // Intenta convertir el string a int
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // Imprime un mensaje de error si el string no puede ser convertido a int
            System.out.println("Error: El string contiene caracteres no numéricos o no es un número válido.");
            return null;
        }
    }
    // Método que calcula el monto final de la transacción ya teniendo en cuenta todos los parámetros digitados por el usuario
    private double calcularMontoFinal(double monto, double intereses, double cashback, double descuento, double costoTransaccion) {
        return monto + intereses - cashback - descuento + costoTransaccion;
    }

}
