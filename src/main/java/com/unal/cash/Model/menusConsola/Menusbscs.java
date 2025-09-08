package com.unal.cash.Model.menusConsola;

import com.unal.cash.Model.JSON.JsonCRUD;           // NUEVO IMPORT
import com.unal.cash.Model.Login.*;
import java.util.Scanner;
import com.unal.cash.Model.crud.ModificarInfo;
import com.unal.cash.Model.crud.ConsultaInfo;
import com.unal.cash.Model.tranYmetpago.*;          // NUEVO IMPORT

public class Menusbscs {
    private final Scanner scanner;

    public Menusbscs(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMenuInicio() {
        System.out.println("+--------------------+");
        System.out.println("|    Menú de inicio  |");
        System.out.println("+--------------------+");
        System.out.println("| Hola, bienvenido a |");
        System.out.println("|   Cash Control     |");
        System.out.println("+--------------------+");
        System.out.println("| Opciones:          |");
        System.out.println("| 1. Iniciar sesión  |");
        System.out.println("| 2. Crear cuenta    |");
        System.out.println("| 0. Salir           |");
        System.out.println("+--------------------+");
    }

    public void mostrarMenuOperaciones() {
        System.out.println("+------------------------+");
        System.out.println("|   Menú de Operaciones  |");
        System.out.println("+------------------------+");
        System.out.println("| 1. Consultar               |");
        System.out.println("| 2. Modificar               |");
        System.out.println("| 3. Agregar Transacción     |");             ///// NUEVA FUNCION
        System.out.println("| 4. Consultar Transacciones |");             ///// NUEVA FUNCION
        System.out.println("+------------------------+");
        boolean running = true;
        while (running) {
            int opcionOperaciones = obtenerOpcion(4);    // se cambió el dos por un 4
            switch (opcionOperaciones) {
                case 1 -> {
                    menuConsultar();
                    volverAlMenuOperaciones(scanner);
                }
                case 2 -> {
                    menuModificar();
                    volverAlMenuOperaciones(scanner);
                }
                case 3 -> {                                            /// NUEVA FUNCION
                    menuRegistroTransaccion();
                    volverAlMenuOperaciones(scanner);
                }
                case 4 -> {                                             /// NUEVA FUNCION
                    menuConsultaTransacciones();
                    volverAlMenuOperaciones(scanner); 
                }
                default -> System.out.print("Por favor, ingrese una opción válida ");
            }
        }
    }

    public int obtenerOpcion(int maxOpcion) {
        int opcion;
        do {
            System.out.print("\nIngrese el número de la opción deseada (0-" + maxOpcion + "): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingrese una opcion válida: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
        } while (opcion < 0 || opcion > maxOpcion);
        return opcion;
    }

    public void menuIniciarSesion() {
        System.out.println("+------------------------+");
        System.out.println("|   Menú Iniciar Sesión  |");
        System.out.println("+------------------------+");
        System.out.println("| Frame 2.1: Iniciar      |");
        System.out.println("|      sesión             |");
        System.out.println("+------------------------+");
        LoginIS login = new LoginIS();
        login.iniciarSesion();
    }

    public void menuCrearCuenta() {
        System.out.println("+------------------------+");
        System.out.println("|   Menú Crear Cuenta    |");
        System.out.println("+------------------------+");
        System.out.println("| Frame 2.2: Crear        |");
        System.out.println("|        Cuenta          |");
        System.out.println("+------------------------+");
        Registroinfo registroInfo = new Registroinfo();
        registroInfo.IngresoInfoStr();
        registroInfo.ingresoinfoDouble();
        registroInfo.insertarInfoBD(registroInfo.getdatosStr(), registroInfo.getdatosDbl());
        menuIniciarSesion();
    }

    public void menuConsultar() {
        System.out.println("+------------------------+");
        System.out.println("|   Menú Consultar       |");
        System.out.println("+------------------------+");
        System.out.println("| Frame 4.1: Consultar    |");
        System.out.println("+------------------------+");
        ConsultaInfo consultaInfo = new ConsultaInfo();
        consultaInfo.consultarInformacion();
    }

    public void menuModificar() {
        System.out.println("+------------------------+");
        System.out.println("|   Menú Modificar       |");
        System.out.println("+------------------------+");
        System.out.println("| Frame 4.2: Modificar    |");
        System.out.println("+------------------------+");
        ModificarInfo modificarInfo = new ModificarInfo();
        modificarInfo.modificarInfrmcn();
    }
    
    public void menuRegistroTransaccion() {                                     //// NUEVA FUNCION (NUEVO MENU EN TERMINAL)
        System.out.println("+------------------------+");
        System.out.println("|   Menú Registro de transacción  |");
        System.out.println("+------------------------+");
        System.out.println("| Frame: Registro      |");
        System.out.println("|       Transacción    |");
        System.out.println("+------------------------+");
        Transaccion transaccion = new Transaccion();
        transaccion.realizarTransaccion(scanner);
    }
    
    public void menuConsultaTransacciones() {                                     //// NUEVA FUNCION (NUEVO MENU EN TERMINAL)
        System.out.println("+------------------------+");
        System.out.println("|   Menú Consulta de transacción  |");
        System.out.println("+------------------------+");
        System.out.println("| Frame: Consulta de      |");
        System.out.println("|      Transacciones        |");
        System.out.println("+------------------------+");
        JsonCRUD.leerJSONYMostrar(SesionUsuario.getUsuarioLog());
        /*
        System.out.println("\n\nSuma total de los montos de las transacciones registradas: ");
        
        double sumaMontosFinales = JsonCRUD.obtenerSumaMontosFinalesUsuario(SesionUsuario.getUsuarioLog());
        System.out.println(sumaMontosFinales);
        */
    }

    public void volverAlMenuOperaciones(Scanner scanner) {
        System.out.println("\nPresione 0 para volver al menú de opciones: ");
        while (true) {
            String opcion = scanner.nextLine();
            if (opcion.equals("0")) {
                mostrarMenuOperaciones();
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}