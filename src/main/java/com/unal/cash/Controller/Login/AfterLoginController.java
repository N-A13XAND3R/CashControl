package com.unal.cash.Controller.Login;

import com.unal.cash.App;
import com.unal.cash.Model.JSON.JsonCRUD;
import com.unal.cash.Model.Login.SesionUsuario;
import com.unal.cash.Model.PerfilesConsumo.PerfilesConsumo;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.unal.cash.Database.datos.PersonaDAO;

import java.util.Map;

public class AfterLoginController {

    public Button bt_cancelar;
    public TextField txt_porcAhorro;
    public TextField txt_porcInversion;

    private PerfilesConsumo porcentajes, objExc;                          // NUEVA INSTANCIA DE OBJETO
    private PersonaDAO personaDao;

    private double ingresosmensuales, gastosRecurrentes, excedenteMensual;
    private int perfilconsumo;
    private static final String[] DIAS_SEMANA = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

//    public void initialize() {
//        this.personaDao = new PersonaDAO();
//
//        String usuario = SesionUsuario.getUsuarioLog();
//
//        String[] datosStr = personaDao.SeleccionarUnoDS(usuario);
//        double[] datosDbl = personaDao.SeleccionarUnoDDouble(usuario);
//
//
//        this.porcentajes = new PerfilesConsumo();
//        this.objExc = new PerfilesConsumo();
//
//        this.ingresosmensuales = datosDbl[2];
//        this.gastosRecurrentes = datosDbl[3]+datosDbl[4]+datosDbl[5]+datosDbl[6]+datosDbl[7]+datosDbl[8];
//
//
//        try {
//            this.excedenteMensual = objExc.getExcedenteMensual(ingresosmensuales, gastosRecurrentes);
//        } catch (Exception e) {
//            this.excedenteMensual = 0;
//        }
//
//
//        this.perfilconsumo = (int) datosDbl[10] + 1;
//
//    }

    public void initialize(){
        this.personaDao = new PersonaDAO();
        String usuario = SesionUsuario.getUsuarioLog();
        String[] datosStr = personaDao.SeleccionarUnoDS(usuario);
        double[] datosDbl = personaDao.SeleccionarUnoDDouble(usuario);
        PerfilesConsumo objExc = new PerfilesConsumo();
        this.porcentajes = new PerfilesConsumo();
        this.objExc = new PerfilesConsumo();// NUEVA INSTANCIA DE OBJETO

        String contraseña = datosStr[1];
        String nombre = datosStr[2];
        String apellido = datosStr[3];
        String email = datosStr[4];
        String metodopagomasusado = datosStr[5];
        double telefono = datosDbl[1];
        double ingresosmensuales = datosDbl[2];
        double transporte = datosDbl[3];
        double alimentacion = datosDbl[4];
        double servicios = datosDbl[5];
        double educacion = datosDbl[6];
        double entretenimiento = datosDbl[7];
        double personal = datosDbl[8];
        int perfilconsumo = ((int) datosDbl[10]) +1;
        double gastosRecurrentes = datosDbl[3]+datosDbl[4]+datosDbl[5]+datosDbl[6]+datosDbl[7]+datosDbl[8];

        this.ingresosmensuales = ingresosmensuales;
        this.gastosRecurrentes = gastosRecurrentes;
        this.perfilconsumo = perfilconsumo;
    }


    public void CambiarContinuar(ActionEvent actionEvent) {

        if(this.txt_porcAhorro.getText().isEmpty() || this.txt_porcInversion.getText().isEmpty()){
            System.out.println("Error: Debe ingresar un porcentaje de ahorro e inversión.");
            return;
        }
        double porcentajeAhorro = convertStringToDouble(this.txt_porcAhorro.getText());
        double porcentajeInversion = convertStringToDouble(this.txt_porcInversion.getText());

        double[] porcentajesAI = porcentajes.porcentajesAhorroEInversion(porcentajeAhorro, porcentajeInversion);

        porcentajeAhorro = porcentajesAI[0];
        porcentajeInversion = porcentajesAI[1];

        double excedenteMensual = objExc.getExcedenteMensual(ingresosmensuales, gastosRecurrentes);

//        String contraseñaOculta = contraseña.replaceAll(".", "*"); // Reemplazar cada letra por un asterisco

        System.out.println("\nInformación del usuario:");
        System.out.println("Ingresos mensuales:_____________$ " + ingresosmensuales);


        // Imprimir perfil de consumo y presupuesto por día
        switch (perfilconsumo){
            case 1:
                System.out.println("Perfil de consumo:______________" + "Constante");
                break;
            case 2:
                System.out.println("Perfil de consumo:______________" + "Fin de semana gastador");
                break;
            case 3:
                System.out.println("Perfil de consumo:______________" + "Entre semana gastador");
                break;
            case 4:
                System.out.println("Perfil de consumo:______________" + "Viernes gastador");
                break;
        }

        System.out.println("Excendente mensual:_________$ " + excedenteMensual);      // NUEVO PRINT

        // Crear instancia de PerfilesConsumo
        PerfilesConsumo perfilGlobal = new PerfilesConsumo (ingresosmensuales, gastosRecurrentes, porcentajeAhorro, porcentajeInversion, perfilconsumo);
        // Calcular distribución semanal y obtener presupuesto por día
        Map<String, Double> distribucionSemanal = perfilGlobal.calcularDistribucionSemanal();



        perfilGlobal.setDistribucionSemanal(distribucionSemanal);
        perfilGlobal.setExcedenteMensual(excedenteMensual);

        SesionUsuario.setPerfilStatus(perfilGlobal);// NUEVO SETTER


//
//
//        // Crear instancia de PerfilesConsumo
//
//        System.out.println("ESTE ES EL PERFIL DE CONSUMO DESDE AFTERLOGIN: " + this.perfilconsumo);
//        PerfilesConsumo perfilGlobal = new PerfilesConsumo (ingresosmensuales, gastosRecurrentes, porcentajeAhorro, porcentajeInversion, this.perfilconsumo);
//        // Calcular distribución semanal y obtener presupuesto por día
//        Map<String, Double> distribucionSemanal = perfilGlobal.calcularDistribucionSemanal();
//        System.out.println("MAPA DE DISTRIBUCION SEMANAL:\n");
//        printMap(distribucionSemanal);
//
//        System.out.println("PERFILES DE CONSUMO:\n" + perfilGlobal);
//        perfilGlobal.setDistribucionSemanal(distribucionSemanal);
//        perfilGlobal.setExcedenteMensual(excedenteMensual);
//
//        SesionUsuario.setPerfilStatus(perfilGlobal);// NUEVO SETTER
//
//        // Mostrar distribución semanal de gastos
//        System.out.println("\nDistribucion semanal de gastos (presupuesto diario):");

        for (String dia : DIAS_SEMANA) {
            System.out.println(dia + ": $" + Math.round(distribucionSemanal.get(dia)));
        }

    new App().mostrarVista("DashBoard/DashBoard.fxml");

    }

    public void logoutAndExit(ActionEvent actionEvent) {
        SesionUsuario.Logout();
        new App().mostrarVista("Home.fxml");
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

    public void printMap(Map<String, Double> map) {
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
