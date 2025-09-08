package com.unal.cash.Controller.Login;

import com.unal.cash.App;
import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Database.domain.Persona;
import com.unal.cash.Model.Login.SesionUsuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.Objects;

public class RegisterController {

    public TextField tfNumeroDocumento;
    public Label lb_crearCuenta;
    @FXML
    private Button btnContinueSignup;

    @FXML
    private PasswordField pfNewPassword;

    @FXML
    private TextField tfNewUser;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfTelefono;

    @FXML
    private TextField tfIngresosmensuales;

    @FXML
    private ChoiceBox<String> cbMetoPago;

    private String cbMetoPagoSelected;
    private PersonaDAO personaDao = new PersonaDAO();

    private final String[] metodosPago = {"Tarjeta de Débito", "Tarjeta de Crédito", "Efectivo", "Transacción por aplicación"};


    @FXML
    private ChoiceBox<String> cbPerfilConsumo;

    private String cbPerfilConsumoSelected;

    private final String[] perfilesConsumo = {
            "Constante",
            "Fin de semana gastador",
            "Entre semana gastador",
            "Viernes gastador"};


    @FXML
    private TextField tfAlimentacion;

    @FXML
    private TextField tfEducacion;

    @FXML
    private TextField tfEntretenimiento;

    @FXML
    private TextField tfPersonal;

    @FXML
    private TextField tfServicios;

    @FXML
    private TextField tfTransporte;

    private static Object[] part1;

    public void initialize() {
        if (this.cbMetoPago == null) this.cbMetoPago = new ChoiceBox<String>();

        this.cbMetoPago.getItems().addAll(this.metodosPago);
        this.cbMetoPago.setOnAction(this::getMetoPago);

        if (this.cbPerfilConsumo == null) this.cbPerfilConsumo = new ChoiceBox<String>();
        this.cbPerfilConsumo.getItems().addAll(this.perfilesConsumo);
        this.cbPerfilConsumo.setOnAction(this::getPerfilConsumo);

    }

    private void getPerfilConsumo(ActionEvent actionEvent) {
        this.cbPerfilConsumoSelected = this.cbPerfilConsumo.getValue();
    }

    public void getMetoPago(ActionEvent e) {
        this.cbMetoPagoSelected = this.cbMetoPago.getValue();
    }

    @FXML
    void cambioInterfaz(ActionEvent event) {
        // Implementar la lógica para cambiar la interfaz aquí
        // Aquí va tu código para cambiar la interfaz
        if (SesionUsuario.session()) {
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        } else {
            new App().mostrarVista("Home.fxml");
        }
    }

    @FXML
    void cambioSoporte(ActionEvent event) {
        // Implementar la lógica para cambiar al soporte aquí
        // Aquí va tu código para cambiar la interfaz

        new App().mostrarVista("SobreNosotros/Soporte.fxml");


    }

    @FXML
    void continuarRegistro(ActionEvent event) {


        //     public Persona(String usuario, String contraseña, String nombre, String apellido, String email, double telefono, double ingresosmensuales, double transporte, double alimentacion, double servicios, double educacion, double entretenimiento, double personal, double excedentefindemes, double perfilconsumo, String metodopagomasusado) {
        if (this.cbMetoPagoSelected == null) {
            this.cbMetoPagoSelected = "Efectivo";
        }
        if (Objects.equals(this.tfNewUser.getText(), "") || this.cbMetoPagoSelected == "" || this.pfNewPassword.getText() == "" || this.tfEmail.getText() == "" || this.tfNombre.getText() == "" || this.tfApellido.getText() == "" || this.tfTelefono.getText() == "" || this.tfIngresosmensuales.getText() == "") {
            return;
        } else {
            String nombre = this.tfNombre.getText(), apellido = this.tfApellido.getText(), usuario = this.tfNewUser.getText(), contraseña = this.pfNewPassword.getText(), email = this.tfEmail.getText(), metodopagomasusado = this.cbMetoPagoSelected;
            String telefono = this.tfTelefono.getText(), ingresosmensuales = this.tfIngresosmensuales.getText();
            String[] datosStr = new String[]{usuario, contraseña, nombre, apellido, email, metodopagomasusado};
            int index = Arrays.asList(this.perfilesConsumo).indexOf(this.cbPerfilConsumoSelected);
            String
                    transporte = this.tfTransporte.getText(),
                    alimentacion = this.tfAlimentacion.getText(),
                    servicios = this.tfServicios.getText(),
                    educacion = this.tfEducacion.getText(),
                    entretenimiento = this.tfEntretenimiento.getText(),
                    personal = this.tfPersonal.getText(),
                    perfilconsumo = Integer.toString(index);
            String excedentefindemes = "0.0";
            String[] datosStrToDbl = new String[]{telefono, ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo};
            System.out.println("datosStrToDbl: " + Arrays.toString(datosStrToDbl));
            double[] datosDbl = Arrays.stream(datosStrToDbl)
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            try {
                //     public Persona(String usuario, String contraseña, String nombre, String apellido, String email, double telefono, double ingresosmensuales, double transporte, double alimentacion, double servicios, double educacion, double entretenimiento, double personal, double excedentefindemes, double perfilconsumo, String metodopagomasusado) {

                Persona personaNueva = new Persona(datosStr[0], datosStr[1], datosStr[2], datosStr[3], datosStr[4], datosDbl[0], datosDbl[1], datosDbl[2], datosDbl[3], datosDbl[4], datosDbl[5], datosDbl[6], datosDbl[7], datosDbl[8], datosDbl[9], datosStr[5]);

                this.personaDao.insertar(personaNueva);

                System.out.println("¡Registro de datos exitoso!");
                new App().mostrarVista("Login/InicioSesion.fxml");
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }


//    @FXML
//    void guardarGastos(ActionEvent event) {
//        if (SesionUsuario.session()){
//
//            int index = Arrays.asList(this.perfilesConsumo).indexOf(this.cbPerfilConsumoSelected);
//            String transporte = this.tfTransporte.getText(),
//                    alimentacion = this.tfAlimentacion.getText(),
//                    servicios = this.tfServicios.getText(),
//                    educacion = this.tfEducacion.getText(),
//                    entretenimiento = this.tfEntretenimiento.getText(),
//                    personal = this.tfPersonal.getText(),
//                    perfilconsumo = Integer.toString(index);
//            try {
//                Persona P = this.personaDao.getPersona(SesionUsuario.getUsuarioLog());
//                String[] datosStrToDbl = new String[]{ transporte, alimentacion, servicios, educacion, entretenimiento, personal, perfilconsumo};
//
//                double[] d = Arrays.stream(datosStrToDbl)
//                        .mapToDouble(Double::parseDouble)
//                        .toArray();
//                P.setTransporte(d[0]);
//                P.setAlimentacion(d[1]);
//                P.setServicios(d[2]);
//                P.setEducacion(d[3]);
//                P.setEntretenimiento(d[4]);
//                P.setPersonal(d[5]);
//                P.setPerfilconsumo(d[6]);
//                this.personaDao.actualizar(P);
//                System.out.println("Gastos actualizados con exito!");
//                new App().mostrarVista("DashBoard/DashBoard.fxml");
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//        }else{
//            String[] datosStr = new String[]{part1[2].toString(), part1[3].toString(), part1[0].toString(), part1[1].toString(), part1[4].toString(), part1[7].toString()};
//            int index = Arrays.asList(this.perfilesConsumo).indexOf(this.cbPerfilConsumoSelected);
//            String telefono = part1[5].toString(),
//                    ingresosmensuales = part1[6].toString(),
//                    transporte = this.tfTransporte.getText(),
//                    alimentacion = this.tfAlimentacion.getText(),
//                    servicios = this.tfServicios.getText(),
//                    educacion = this.tfEducacion.getText(),
//                    entretenimiento = this.tfEntretenimiento.getText(),
//                    personal = this.tfPersonal.getText(),
//                    perfilconsumo = Integer.toString(index),
//                    NumeroDocumento = part1[8].toString();
//            String[] datosStrToDbl = new String[]{telefono, ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, perfilconsumo, NumeroDocumento};
//            System.out.println("datosStrToDbl: " + Arrays.toString(datosStrToDbl));
//            double[] datosDbl = Arrays.stream(datosStrToDbl)
//                    .mapToDouble(Double::parseDouble)
//                    .toArray();
//
//            try {
//                Persona personaNueva = new Persona(datosStr[0], datosStr[1], datosStr[2], datosStr[3], datosStr[4], datosDbl[0],datosDbl[1],datosDbl[2],datosDbl[3],datosDbl[4],datosDbl[5],datosDbl[6],datosDbl[7],0.0,datosDbl[8],datosStr[5]);
//
//                this.personaDao.insertar(personaNueva);
//
//                System.out.println("¡Registro de datos exitoso!");
//                new App().mostrarVista("Login/InicioSesion.fxml");
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//
//
//
//    }
}