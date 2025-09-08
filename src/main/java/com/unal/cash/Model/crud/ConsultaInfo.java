package com.unal.cash.Model.crud;

import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Model.Login.*;
import com.unal.cash.Model.PerfilesConsumo.*;
import java.util.*;

public class ConsultaInfo {
    Scanner sc = new Scanner(System.in);
    PersonaDAO personaDao = new PersonaDAO();
    PerfilesConsumo porcentajes = new PerfilesConsumo();
    private static final String[] DIAS_SEMANA = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    public ConsultaInfo() {
    }

    public void consultarInformacion() {
        String usuario = SesionUsuario.getUsuarioLog();
        String[] datosStr = personaDao.SeleccionarUnoDS(usuario);
        double[] datosDbl = personaDao.SeleccionarUnoDDouble(usuario);
        PerfilesConsumo objExc = new PerfilesConsumo();                          // NUEVA INSTANCIA DE OBJETO

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
        double[] porcentajesAI = porcentajes.porcentajesAhorroEInversion();
        double porcentajeAhorro = porcentajesAI[0];
        double porcentajeInversion = porcentajesAI[1];
        double excedenteMensual = objExc.getExcedenteMensual(ingresosmensuales, gastosRecurrentes);          // NUEVO PARAMETRO

        String contraseñaOculta = contraseña.replaceAll(".", "*"); // Reemplazar cada letra por un asterisco

        System.out.println("\nInformación del usuario:");
        System.out.println("Usuario:_______________________" + usuario);
        System.out.println("Contraseña:_____________________" + contraseñaOculta);
        System.out.println("Nombre:_________________________" + nombre);
        System.out.println("Apellido:_______________________" + apellido);
        System.out.println("Email:__________________________" + email);
        System.out.println("Método de pago más usado:_______" + metodopagomasusado);
        System.out.println("Teléfono:_______________________# " + telefono);
        System.out.println("Ingresos mensuales:_____________$ " + ingresosmensuales);
        System.out.println("Gasto mensual en transporte:____$ " + transporte);
        System.out.println("Gasto mensual en alimentación:__$ " + alimentacion);
        System.out.println("Gasto mensual en servicios:_____$ " + servicios);
        System.out.println("Gasto mensual en servicios:_____$ " + educacion);
        System.out.println("Gasto mensual en entretenimiento:$ " + entretenimiento);
        System.out.println("Gasto mensual personal:_________$ " + personal);


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

        // Mostrar distribución semanal de gastos
        System.out.println("\nDistribucion semanal de gastos (presupuesto diario):");

        for (String dia : DIAS_SEMANA) {
            System.out.println(dia + ": $" + Math.round(distribucionSemanal.get(dia)));
        }
    }
}

