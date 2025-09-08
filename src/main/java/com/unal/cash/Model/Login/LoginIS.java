package com.unal.cash.Model.Login;

import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Database.domain.Persona;
import java.util.*;

public class LoginIS {
    public String usuario;
    private String contraseña;
    Scanner sc = new Scanner(System.in);
    PersonaDAO personaDao = new PersonaDAO();
    
    public LoginIS() {
    }

    
    public LoginIS(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    
    // Este es el método de iniciar sesión, valida que el usuario exista y coincida con la contraseña, si es exitoso el inicio de sesión, "almacena" al usuario en la clase SesionUsuario para poder usarlo en el resto del programa.
    public void iniciarSesion(){
        while (true){
            System.out.print("Ingrese su usuario: ");
            String usuarioN = sc.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String contraseñaU = sc.next();
            boolean validacionUsuario = false;
            boolean validacionContraseña = false;
     
            List<Persona> personas = personaDao.seleccionar();
            for (Persona persona: personas){
            if (persona.getUsuario().equals(usuarioN)){
                validacionUsuario = true; 
                if (persona.getContraseña().equals(contraseñaU)){
                   validacionContraseña = true; 
                    }
                }
            }
            if (validacionUsuario == false || validacionContraseña == false){
                System.out.println("Datos incorrectos, vuelva a ingresarlos.");
                sc.nextLine();
                continue;
            } 
            else {
                System.out.println("\n¡Inicio de sesión exitoso!");
                SesionUsuario.asignacionUsuario(usuarioN);  
                break;
            }
        }
    }


}
