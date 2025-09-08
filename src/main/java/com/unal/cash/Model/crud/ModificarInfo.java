package com.unal.cash.Model.crud;

import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Database.domain.Persona;
import com.unal.cash.Model.Login.*;
import java.util.*;
/*
// Esta clase es pára modificar cualquier dato del usuario
*/
public class ModificarInfo {
    Scanner sc = new Scanner(System.in);

    PersonaDAO personaDao = new PersonaDAO();

    public ModificarInfo() {
    }

    public void modificarInfrmcn() {

        while (true){

            String usuario = SesionUsuario.getUsuarioLog();
            String datosStr[] = personaDao.SeleccionarUnoDS(usuario);
            /*
            // indices para acceder a un dato en especifico del metodo SeleccionarUnoDS() de la clase PersonaDAO:
             personaDS[0] = usuarioActual;
             personaDS[1] = contraseña;
             personaDS[2] = nombre;
             personaDS[3] = apellido;
             personaDS[4] = email;
             personaDS[5] = metodopagomasusado;
            */
            double datosDbl[] = personaDao.SeleccionarUnoDDouble(usuario);
            /*
            // indices para acceder a un dato en especifico del metodo SeleccionarUnoDDouble() de la clase PersonaDAO:
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

            String contraseña = datosStr[1];
            String nombre = datosStr[2];
            String apellido = datosStr[3];
            String email = datosStr[4];
            String metodopagomasusado = datosStr[5];
            double id_persona = datosDbl[0];
            double telefono = datosDbl[1];
            double ingresosmensuales = datosDbl[2];
            double transporte = datosDbl[3];
            double alimentacion = datosDbl[4];
            double servicios = datosDbl[5];
            double educacion = datosDbl[6];
            double entretenimiento = datosDbl[7];
            double personal = datosDbl[8];
            double excedentefindemes = datosDbl[9];
            double perfilconsumo = datosDbl[10];

            System.out.println("\nModificar informacion\n1. Usuario\n2. Contraseña\n3. Nombre\n4. Apellido\n5. email\n6. Método de pago más usado");
            System.out.println("7. Telefono\n8. Ingresos mensuales\n9. Gasto mesual en transporte\n10. Gasto mesual en alimentación\n11. Gasto mesual en servicios");
            System.out.print("12. Gasto mesual en educación\n13. Gasto mesual en entretenimiento\n14. Gasto mesual personal\n15. Perfil de consumo\n\n¿Qué dato desea modificar?\n\n--> ");
            String opcion = sc.nextLine();
            if (opcion.equals("1")){
                while (true){
                    System.out.print("\nIngrese su nuevo usuario: ");
                    String usuarioNuevo = sc.nextLine();
                    boolean validacionUsuario = false;
                    List<Persona> personas = personaDao.seleccionar();
                    for (Persona persona: personas){
                        if (persona.getUsuario().equals(usuarioNuevo)){
                            validacionUsuario = true;
                        }
                    }
                    if (validacionUsuario == true){
                        System.out.println("\nUsuario inválido, ya está en uso. Por favor ingrese uno nuevo.");
                        continue;
                    } else {
                        // Modificar el usuario de la respectiva persona
                        Persona personaModificar = new Persona(id_persona, usuarioNuevo, contraseña, nombre, apellido, email, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de usuario exitosa!");
                        break;
                    }
                }

            } else if (opcion.equals("2")){
                while (true){
                    System.out.print("\nIngrese su contraseña actual: ");
                    String verificacionContraseña = sc.nextLine();
                    if (contraseña.equals(verificacionContraseña)){
                        System.out.print("\nIngrese su nueva contraseña: ");
                        String nuevaContraseña = sc.nextLine();
                        // Modificar la contraseña de la respectiva persona
                        Persona personaModificar = new Persona(id_persona, usuario, nuevaContraseña, nombre, apellido, email, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de contraseña exitosa!");
                        break;
                    }else {
                        System.out.println("\nLas contraseñas no coinciden, vuelva a ingresarlas.");
                        continue;
                    }
                }

            } else if (opcion.equals("3")){
                System.out.print("\nIngrese su nuevo nombre: ");
                String nombreNuevo = sc.nextLine();
                // Modificar el nombre de la respectiva persona
                Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombreNuevo, apellido, email, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                personaDao.actualizar(personaModificar);
                System.out.println("\n¡Actualización de nombre exitosa!");

            } else if (opcion.equals("4")){
                System.out.print("\nIngrese su nuevo apellido: ");
                String apellidoNuevo = sc.nextLine();
                // Modificar el apellido de la respectiva persona
                Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellidoNuevo, email, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                personaDao.actualizar(personaModificar);
                System.out.println("\n¡Actualización de apellido exitosa!");
            }

            else if (opcion.equals("5")){
                System.out.print("\nIngrese su nuevo email: ");
                String emailNuevo = sc.nextLine();
                // Modificar el email de la respectiva persona
                Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, emailNuevo, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                personaDao.actualizar(personaModificar);
                System.out.println("\n¡Actualización de email exitosa!");
            }

            else if (opcion.equals("6")){
                System.out.print("\nIngrese su nuevo método de pago más usado: ");
                String metodopagomasusadoNuevo = sc.nextLine();
                // Modificar el nombre de la respectiva persona
                Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusadoNuevo);
                personaDao.actualizar(personaModificar);
                System.out.println("\n¡Actualización de método de pago exitosa!");
            }

            else if (opcion.equals("7")){
                while (true){
                    System.out.print("\nIngrese su nuevo número de telefono: ");
                    try{
                        double telefonoNuevo = sc.nextDouble();
                        sc.nextLine();
                        if (telefonoNuevo<0){
                            throw new InputMismatchException();
                        }
                        Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefonoNuevo , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de número de teléfono exitosa!");
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                        continue;
                    }
                }
            }

            else if (opcion.equals("8")){
                while (true){
                    System.out.print("\nRegistre sus nuevos ingresos mensuales: ");
                    try{
                        double ingresosmensualesNuevo = sc.nextDouble();
                        sc.nextLine();
                        if (ingresosmensualesNuevo<0){
                            throw new InputMismatchException();
                        }
                        Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefono , ingresosmensualesNuevo, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de ingresos mensuales exitosa!");
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                        continue;
                    }
                }
            }

            else if (opcion.equals("9")){
                while (true){
                    System.out.print("\nIngrese su nuevo gasto mensual en transporte: ");
                    try{
                        double transporteNuevo = sc.nextDouble();
                        sc.nextLine();
                        if (transporteNuevo<0){
                            throw new InputMismatchException();
                        }
                        Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefono , ingresosmensuales, transporteNuevo, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de gasto mensual en transporte exitosa!");
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                        continue;
                    }
                }
            }

            else if (opcion.equals("10")){
                while (true){
                    System.out.print("\nIngrese su nuevo gasto mensual en alimentación: ");
                    try{
                        double alimentacionNuevo = sc.nextDouble();
                        sc.nextLine();
                        if (alimentacionNuevo<0){
                            throw new InputMismatchException();
                        }
                        Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefono , ingresosmensuales, transporte, alimentacionNuevo, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de gasto mensual en alimentación exitosa!");
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                        continue;
                    }
                }
            }

            else if (opcion.equals("11")){
                while (true){
                    System.out.print("\nIngrese su nuevo gasto mensual en servicios: ");
                    try{
                        double serviciosNuevo = sc.nextDouble();
                        sc.nextLine();
                        if (serviciosNuevo<0){
                            throw new InputMismatchException();
                        }
                        Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefono , ingresosmensuales, transporte, alimentacion, serviciosNuevo, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de gasto mensual en servicios exitosa!");
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                        continue;
                    }
                }
            }

            //System.out.print("12. Gasto mesual en educación\n13. Gasto mesual en entretenimiento\n14. Gasto mesual personal\n15. Perfil de consumo
            else if (opcion.equals("12")){
                while (true){
                    System.out.print("\nIngrese su nuevo gasto mensual en educación: ");
                    try{
                        double educacionNuevo = sc.nextDouble();
                        sc.nextLine();
                        if (educacionNuevo<0){
                            throw new InputMismatchException();
                        }
                        Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacionNuevo, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de gasto mensual en educación exitosa!");
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                        continue;
                    }
                }
            }

            else if (opcion.equals("13")){
                while (true){
                    System.out.print("\nIngrese su nuevo gasto mensual en entretenimiento: ");
                    try{
                        double entretenimientoNuevo = sc.nextDouble();
                        sc.nextLine();
                        if (entretenimientoNuevo<0){
                            throw new InputMismatchException();
                        }
                        Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimientoNuevo, personal, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de gasto mensual en entretenimiento exitosa!");
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                        continue;
                    }
                }
            }

            else if (opcion.equals("14")){
                while (true){
                    System.out.print("\nIngrese su nuevo gasto personal mensual: ");
                    try{
                        double personalNuevo = sc.nextDouble();
                        sc.nextLine();
                        if (personalNuevo<0){
                            throw new InputMismatchException();
                        }
                        Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personalNuevo, excedentefindemes, perfilconsumo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de gasto personal mensual exitosa!");
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                        continue;
                    }
                }
            }

            else if (opcion.equals("15")){
                while (true){
                    System.out.print("\nIngrese su nuevo perfil de consumo: ");
                    try{
                        double perfilconsumoNuevo = sc.nextDouble();
                        sc.nextLine();
                        if (perfilconsumoNuevo<0){
                            throw new InputMismatchException();
                        }
                        Persona personaModificar = new Persona(id_persona, usuario, contraseña, nombre, apellido, email, telefono , ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumoNuevo, metodopagomasusado);
                        personaDao.actualizar(personaModificar);
                        System.out.println("\n¡Actualización de gasto mensual en transporte exitosa!");
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número válido. Vuelva a ingresarlo.");
                        continue;
                    }
                }
            }

            else {
                System.out.println("Error: Digite una opción válida. Vuela a intentarlo");
                continue;
            }

            System.out.print("\n¿Desea modificar algún otro dato? (SI / NO)\n--> ");
            String opcionVolver = sc.nextLine().toUpperCase();
            if (opcionVolver.equals("SI")||opcionVolver.equals("S")){
                continue;
            } else {
                break;
            }

        }
    }
}