package com.unal.cash.Model.mainConsola;

//import JSON.JsonCRUD;
//import tranYmetpago.Transaccion;
import java.util.Scanner;

import com.unal.cash.Model.JSON.JsonCRUD;
import com.unal.cash.Model.menusConsola.Menusbscs;
//import tranYmetpago.Transaccion;

public class App {


    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Crear una instancia de Menusbscs
        Menusbscs menus = new Menusbscs(scanner);

        while (running) {
            menus.mostrarMenuInicio();
            int opcionInicio = menus.obtenerOpcion(2);

            switch (opcionInicio) {
                case 1 -> menus.menuIniciarSesion();
                case 2 -> menus.menuCrearCuenta();
                case 0 -> running = false;
            }

            if (!running) {
                break;
            }

            menus.mostrarMenuOperaciones();
            
        }

        System.out.println("\nGracias por usar Cash Control");

    }  
}
