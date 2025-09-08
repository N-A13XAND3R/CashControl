package com.unal.cash.Controller.InformacionPersonal;

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

public class ActualizarInformacionController {

    
    public Label lb_crearCuenta;
    public Button bt_guardar_cambios;

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



    private static Object[] part1;


    public TextField txt_usuario;
    public TextField txt_contrasena;
    public TextField txt_nombre;
    public TextField txt_apellido;
    public TextField txt_correo;
    public TextField txt_telefono;
    public TextField txt_ingresos_mensuales;
    public TextField txt_transporte;
    public TextField txt_alimentacion;
    public TextField txt_servicios;
    public TextField txt_educacion;
    public TextField txt_entretenimiento;
    public TextField txt_personal;
    public TextField txt_excedente_fin_mes;
    public TextField txt_metodo_pago_mas_usado;
    public Button bt_volver;


    public void initialize() {


        if (SesionUsuario.session()) {
            if (this.cbMetoPago == null) this.cbMetoPago = new ChoiceBox<String>();

            this.cbMetoPago.getItems().addAll(this.metodosPago);
            this.cbMetoPago.setOnAction(this::getMetoPago);

            if (this.cbPerfilConsumo == null) this.cbPerfilConsumo = new ChoiceBox<String>();
            this.cbPerfilConsumo.getItems().addAll(this.perfilesConsumo);
            this.cbPerfilConsumo.setOnAction(this::getPerfilConsumo);


            lb_crearCuenta = new Label("Editar Cuenta");
            System.out.println("SESSION USARIO == TRUE **********************************");

            //Se llega desde el dashboard hasta gastos recurrentes

            Persona P = personaDao.getPersona(SesionUsuario.getUsuarioLog());
            System.out.println("persona p: " + P.toString());
//            this.txt_nombre = new TextField(P.getNombre());
//            this.txt_apellido = new TextField(P.getApellido());
//            this.txt_usuario = new TextField(P.getUsuario());
//            this.txt_correo = new TextField(P.getEmail());
//            this.txt_contrasena = new TextField(P.getContraseña());
//            this.txt_telefono = new TextField(Double.toString(P.getTelefono()));
//            this.txt_ingresos_mensuales = new TextField(Integer.toString((int) P.getIngresosmensuales()));
//
//
//            this.txt_transporte = new TextField(Integer.toString((int) P.getTransporte()));
//            this.txt_alimentacion = new TextField(Integer.toString((int) P.getAlimentacion()));
//            this.txt_servicios = new TextField(Integer.toString((int) P.getServicios()));
//            this.txt_educacion = new TextField(Integer.toString((int) P.getEducacion()));
//            this.txt_entretenimiento = new TextField(Integer.toString((int)P.getEntretenimiento()));
//            this.txt_personal = new TextField(Integer.toString((int)P.getPersonal()));
//
//            this.txt_excedente_fin_mes = new TextField(Integer.toString((int)P.getExcedentefindemes()));

            int index = Arrays.asList(perfilesConsumo).indexOf(cbPerfilConsumoSelected);
            int index2 = Arrays.asList(metodosPago).indexOf(P.getMetodopagomasusado());

            this.cbPerfilConsumo.setValue(cbPerfilConsumoSelected);
            this.cbMetoPago.getSelectionModel().select(index2);
// You can access and modify the elements here before the view is shown.
            // For example, to set the text of txtUsername:

            String usuario = SesionUsuario.getUsuarioLog();
            String[] datosStr = personaDao.SeleccionarUnoDS(usuario);
            double[] datosDbl = personaDao.SeleccionarUnoDDouble(usuario);
            String[] datosDbl2 = new String[datosDbl.length];
            for (int i = 0; i < datosDbl.length; i++) {
                datosDbl2[i] = Double.toString(datosDbl[i]);
            }

            this.txt_usuario.setText(datosStr[0]);
            this.txt_contrasena.setText(datosStr[1]);
            this.txt_nombre.setText(datosStr[2]);
            this.txt_apellido.setText(datosStr[3]);
            this.txt_correo.setText(datosStr[4]);
            this.cbMetoPago.setValue(this.cbMetoPagoSelected);

        /*
        // indices para acceder a un dato en especifico:
         personaDS[0] = usuarioActual;
         personaDS[1] = contraseña;
         personaDS[2] = nombre;
         personaDS[3] = apellido;
         personaDS[4] = email;
         personaDS[5] = metodopagomasusado;
        */

            int tel = (int) datosDbl[1];
            int ingrM = (int) datosDbl[2];
            this.txt_telefono.setText(Integer.toString(tel));
            this.txt_ingresos_mensuales.setText(Integer.toString(ingrM));
            this.txt_transporte.setText(datosDbl2[3]);
            this.txt_alimentacion.setText(datosDbl2[4]);
            this.txt_servicios.setText(datosDbl2[5]);
            this.txt_educacion.setText(datosDbl2[6]);
            this.txt_entretenimiento.setText(datosDbl2[7]);
            this.txt_personal.setText(datosDbl2[8]);
            this.txt_excedente_fin_mes.setText(datosDbl2[9]);
            this.cbPerfilConsumo.setValue(this.perfilesConsumo[(int)datosDbl[10]]);
         /*
         indices para acceder a un dato en especifico:
         personaDDbl[0] = idPersona;
         personaDDbl[1] = telefono;
         personaDDbl[2] = ingresosmensuales;
         personaDDbl[3] = transporte;
         personaDDbl[4] = alimentacion;
         personaDDbl[5] = servicios;
         personaDDbl[6] = educacion;
         personaDDbl[7] = entretenimiento;
         personaDDbl[8] = personal;
         personaDDbl[9] = excedentefindemes;
         personaDDbl[10] = perfilconsumo;
        */

        }

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
        if (this.cbMetoPagoSelected == null) {
            this.cbMetoPagoSelected = "Efectivo";
        }

            //     public Persona(String usuario, String contraseña, String nombre, String apellido, String email, double telefono, double ingresosmensuales, double transporte, double alimentacion, double servicios, double educacion, double entretenimiento, double personal, double excedentefindemes, double perfilconsumo, String metodopagomasusado) {

            if (Objects.equals(this.txt_usuario.getText(), "") || this.cbMetoPagoSelected == "" || this.txt_contrasena.getText() == "" || this.txt_correo.getText() == "" || this.txt_nombre.getText() == "" || this.txt_apellido.getText() == "" || this.txt_telefono.getText() == "" || this.txt_ingresos_mensuales.getText() == "") {
                return;
            } else {
                String nombre = this.txt_nombre.getText(), apellido = this.txt_apellido.getText(), usuario = this.txt_usuario.getText(), contrasena = this.txt_contrasena.getText(), email = this.txt_correo.getText(), metodopagomasusado = this.cbMetoPagoSelected;
                String telefono = this.txt_telefono.getText(), ingresosmensuales = this.txt_ingresos_mensuales.getText();
                String[] datosStr = new String[]{usuario, contrasena, nombre, apellido, email, metodopagomasusado};
                int index = Arrays.asList(this.perfilesConsumo).indexOf(this.cbPerfilConsumoSelected);
                String
                        transporte = this.txt_transporte.getText(),
                        alimentacion = this.txt_alimentacion.getText(),
                        servicios = this.txt_servicios.getText(),
                        educacion = this.txt_educacion.getText(),
                        entretenimiento = this.txt_entretenimiento.getText(),
                        personal = this.txt_personal.getText(),
                        perfilconsumo = Integer.toString(index),
                        excedentefindemes = this.txt_excedente_fin_mes.getText();
                String[] datosStrToDbl = new String[]{telefono, ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo};
                System.out.println("datosStrToDbl: " + Arrays.toString(datosStrToDbl));
                double[] datosDbl = Arrays.stream(datosStrToDbl)
                        .mapToDouble(Double::parseDouble)
                        .toArray();

                try {
                    //     public Persona(String usuario, String contrasena, String nombre, String apellido, String email, double telefono, double ingresosmensuales, double transporte, double alimentacion, double servicios, double educacion, double entretenimiento, double personal, double excedentefindemes, double perfilconsumo, String metodopagomasusado) {


                    Persona P = this.personaDao.getPersona(SesionUsuario.getUsuarioLog());

                    P.setUsuario(usuario);
                    P.setContraseña(contrasena);
                    P.setNombre(nombre);
                    P.setApellido(apellido);
                    P.setEmail(email);
                    P.setTelefono(datosDbl[0]);
                    P.setIngresosmensuales(datosDbl[1]);
                    P.setTransporte(datosDbl[2]);
                    P.setAlimentacion(datosDbl[3]);
                    P.setServicios(datosDbl[4]);
                    P.setEducacion(datosDbl[5]);
                    P.setEntretenimiento(datosDbl[6]);
                    P.setPersonal(datosDbl[7]);
                    P.setExcedentefindemes(datosDbl[8]);
                    P.setPerfilconsumo(datosDbl[9]);
                    P.setMetodopagomasusado(datosStr[5]);

                    this.personaDao.actualizar(P);



                    System.out.println("¡Actualizacion de datos exitosa!");
                    new App().mostrarVista("DashBoard/DashBoard.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }

            }


    }

    @FXML
    void cambioCrearCuenta(ActionEvent event) {
        if (SesionUsuario.session()) {
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        } else {
            new App().mostrarVista("Login/CrearCuenta.fxml");
        }

    }

}