package com.unal.cash.Model.Login;

import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Database.domain.Persona;
//import Login.LoginIS;
import java.util.*;


public class Registroinfo {
    private String usuario;
    private String contraseña;
    private String nombre;
    private String apellido;
    private String email;
    private double telefono;
    private double ingresosmensuales;
    private double transporte;
    private double alimentacion;
    private double servicios;
    private double educacion;
    private double entretenimiento;
    private double personal;
    private double perfilconsumo;
    private String metodopagomasusado;
    private double datosDbl[];
    private String datosStr[];
    
    Scanner sc = new Scanner(System.in);
    PersonaDAO personaDao = new PersonaDAO();
    LoginIS login = new LoginIS();
    
    public Registroinfo() {
    }

    public Registroinfo(double[] datosDbl, String[] datosStr) {
        this.datosDbl = datosDbl;
        this.datosStr = datosStr;
    }
    
    public void IngresoInfoStr(){
        System.out.print("\nIngrese su nombre sin apellido: ");
        nombre = sc.nextLine();
        System.out.print("\nIngrese su apellido: ");
        apellido = sc.nextLine();
        while(true){
            System.out.print("\nIngrese su usuario de cuenta: ");
            usuario = sc.nextLine();
            boolean validacionUsuario = false;
            List<Persona> personas = personaDao.seleccionar();
            for (Persona persona: personas){
            if (persona.getUsuario().equals(usuario)){
                validacionUsuario = true;
            }
            }
            if (validacionUsuario == true){
                System.out.println("\nUsuario inválido, ya está en uso. Por favor ingrese uno nuevo.");
                continue;
            } else {
                break;
            }
        }
        while (true){
            System.out.print("\nIngrese su contraseña: ");
            contraseña = sc.nextLine();
            System.out.print("\nVuelva a ingresar su contraseña: ");
            String contraseñaconf = sc.nextLine();
            if (contraseña.equals(contraseñaconf)){
                break;
            } else{
                System.out.println("\nLas contraseñas no coinciden, vuelva a ingresarlas.");
                continue;
            }
        }
        while (true){
        System.out.print("\nIngrese su correo electrónico: ");
        email = sc.nextLine();
        System.out.print("\n¿seguro que su correo es "+email+" ? (SI / NO)" );
        String validacion = sc.nextLine().toUpperCase();
        if (validacion.equals("NO")||validacion.equals("N")){
            System.out.print("\nVuelva a digitarlo.");
            continue;
        }else{
            break;
        }
    }
        System.out.print("\nIngrese su método de pago más usado (ej: Tarjeta Débito): ");
        metodopagomasusado = sc.nextLine();

        datosStr = new String[]{nombre, apellido, usuario, contraseña, email, metodopagomasusado};  
    }
    
    public String[] getdatosStr(){
        return datosStr;
    }
    
    public void ingresoinfoDouble(){
        while (true){
            System.out.print("\nIngrese su telefono: ");
            try{
            telefono = sc.nextDouble();
            if (telefono<0){
                throw new InputMismatchException();
            }
            break;
            }catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                continue;
            }
        }
        while (true){
            System.out.print("\nRegistre sus ingresos mensuales: ");
            try{
            ingresosmensuales = sc.nextDouble();
            if (ingresosmensuales<0){
                throw new InputMismatchException();
            }
            break;
            }catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                continue;
            }
        }
        while (true){
            System.out.print("\nIngrese su gasto mensual en transporte: ");
            try{
            transporte = sc.nextDouble();
            if (transporte<0){
                throw new InputMismatchException();
            }
            break;
            }catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                continue;
            }
        }
        while (true){
            System.out.print("\nIngrese su gasto mensual en alimentación: ");
            try{
            alimentacion = sc.nextDouble();
            if (alimentacion<0){
                throw new InputMismatchException();
            }
            break;
            }catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                continue;
            }
        }
        while (true){
            System.out.print("\nIngrese su gasto mensual en servicios: ");
            try{
            servicios = sc.nextDouble();
            if (servicios<0){
                throw new InputMismatchException();
            }
            break;
            }catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                continue;
            }
        }
        while (true){
            System.out.print("\nIngrese su gasto mensual en educacion: ");
            try{
            educacion = sc.nextDouble();
            if (educacion<0){
                throw new InputMismatchException();
            }
            break;
            }catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                continue;
            }
        }
        while (true){
            System.out.print("\nIngrese su gasto mensual en entretenimiento: ");
            try{
            entretenimiento = sc.nextDouble();
            if (entretenimiento<0){
                throw new InputMismatchException();
            }
            break;
            }catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                continue;
            }
        }
        while (true){
            System.out.print("\nIngrese su gasto personal mensual: ");
            try{
            personal = sc.nextDouble();
            if (personal<0){
                throw new InputMismatchException();
            }
            break;
            }catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                continue;
            }
        }
        while (true){
            System.out.print("\n\t\tPerfil de consumo\n1. Constante (gasta lo mismo todos los días)\n2. Fin de semana gastador (gasta más los fines de semana que entre semana)\n3. Entre semana gastador (gasta más entre semana que los fines de semana)\n4. Viernes gastador(gasta más los viernes que cualquier otro día de la semana)\nEscoja el perfil de consumo que más se ajusta a sus hábitos: ");
            try{
            int perfilconsumoint = sc.nextInt();
            switch (perfilconsumoint){
                case 1 -> perfilconsumo = 1;
                case 2 -> perfilconsumo = 2;
                case 3 -> perfilconsumo = 3;
                case 4 -> perfilconsumo = 4;
                default -> throw new InputMismatchException();
            }
            break;
            }catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                continue;
            }
        }
        
        datosDbl = new double[]{telefono, ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, perfilconsumo};
        
    }
    
    public double[] getdatosDbl(){
        return datosDbl;
    }
    
    public void insertarInfoBD(String dtsStr[], double dtsDbl[]){
    
    //String datosStr [] = {nombre, apellido, usuario, contraseña, email, metodopagomasusado};
    //double datosDbl[] ={telefono, ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, perfilconsumo};
    //(String usuario, String contraseña, String nombre, String apellido, String email, double telefono, double ingresosmensuales, double transporte, double alimentacion, double servicios, double educacion, double entretenimiento, double personal, double excedentefindemes, double perfilconsumo, String metodopagomasusado)
    
    Persona personaNueva = new Persona(dtsStr[2], dtsStr[3], dtsStr[0], dtsStr[1], dtsStr[4], dtsDbl[0],dtsDbl[1],dtsDbl[2],dtsDbl[3],dtsDbl[4],dtsDbl[5],dtsDbl[6],dtsDbl[7],0.0,dtsDbl[8],dtsStr[5]);
    personaDao.insertar(personaNueva);
    
    System.out.println("¡Registro de datos exitoso!");
    
    }
}
