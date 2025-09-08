package com.unal.cash.Database.datos;

import static com.unal.cash.Database.datos.Conexion.*;
import com.unal.cash.Database.domain.Persona;
import java.sql.*;
import java.util.*;


//esta clase es la que maneja la interaccion con la clase persona y la base de datos para manipular la base de datos
public class PersonaDAO {

    private static final String SQL_SELECT = "SELECT id_persona, usuario, contraseña, nombre, apellido, email, telefono, ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado FROM informacionpersonal";
    private static final String SQL_INSERT = "INSERT INTO informacionpersonal (usuario, contraseña, nombre, apellido, email, telefono, ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE informacionpersonal SET usuario = ?, contraseña = ?, nombre = ?, apellido = ?, email = ?, telefono = ?, ingresosmensuales = ?, transporte = ?, alimentacion = ?, servicios = ?, educacion = ?, entretenimiento = ?, personal = ?, excedentefindemes = ?, perfilconsumo = ?, metodopagomasusado = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM informacionpersonal WHERE id_persona = ?";

    // lista de objetos de tipo persona
    public List<Persona> seleccionar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();


        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                double idPersona = rs.getDouble("id_persona");
                String usuario = rs.getString("usuario");
                String contraseña = rs.getString("contraseña");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                double telefono = rs.getDouble("telefono");
                double ingresosmensuales = rs.getDouble("ingresosmensuales");
                double transporte = rs.getDouble("transporte");
                double alimentacion = rs.getDouble("alimentacion");
                double servicios = rs.getDouble("servicios");
                double educacion = rs.getDouble("educacion");
                double entretenimiento = rs.getDouble("entretenimiento");
                double personal = rs.getDouble("personal");
                double excedentefindemes = rs.getDouble("excedentefindemes");
                double perfilconsumo = rs.getDouble("perfilconsumo");
                String metodopagomasusado = rs.getString("metodopagomasusado");

                // con esta variable estamos convirtiendo información de la base de datos hacia objetos java
                persona = new Persona(idPersona, usuario, contraseña, nombre, apellido, email, telefono, ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo, metodopagomasusado);

                //añadimos a la lista tipo Persona, la variable persona, la cual contiene toda la informacion de una persona
                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return personas;
    }

    public int insertar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, persona.getUsuario());
            stmt.setString(2, persona.getContraseña());
            stmt.setString(3, persona.getNombre());
            stmt.setString(4, persona.getApellido());
            stmt.setString(5, persona.getEmail());
            stmt.setDouble(6, persona.getTelefono());
            stmt.setDouble(7, persona.getIngresosmensuales());
            stmt.setDouble(8, persona.getTransporte());
            stmt.setDouble(9, persona.getAlimentacion());
            stmt.setDouble(10, persona.getServicios());
            stmt.setDouble(11, persona.getEducacion());
            stmt.setDouble(12, persona.getEntretenimiento());
            stmt.setDouble(13, persona.getPersonal());
            stmt.setDouble(14, persona.getExcedentefindemes());
            stmt.setDouble(15, persona.getPerfilconsumo());
            stmt.setString(16, persona.getMetodopagomasusado());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERRORR");
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int actualizar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, persona.getUsuario());
            stmt.setString(2, persona.getContraseña());
            stmt.setString(3, persona.getNombre());
            stmt.setString(4, persona.getApellido());
            stmt.setString(5, persona.getEmail());
            stmt.setDouble(6, persona.getTelefono());
            stmt.setDouble(7, persona.getIngresosmensuales());
            stmt.setDouble(8, persona.getTransporte());
            stmt.setDouble(9, persona.getAlimentacion());
            stmt.setDouble(10, persona.getServicios());
            stmt.setDouble(11, persona.getEducacion());
            stmt.setDouble(12, persona.getEntretenimiento());
            stmt.setDouble(13, persona.getPersonal());
            stmt.setDouble(14, persona.getExcedentefindemes());
            stmt.setDouble(15, persona.getPerfilconsumo());
            stmt.setString(16, persona.getMetodopagomasusado());
            stmt.setDouble(17, persona.getIdPersona());
            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int eliminar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setDouble(1, persona.getIdPersona());
            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public double[] SeleccionarUnoDDouble(String usuarioActual){
        double idPersona = 0;
        double telefono = 0;
        double ingresosmensuales = 0;
        double transporte = 0;
        double alimentacion = 0;
        double servicios = 0;
        double educacion = 0;
        double entretenimiento = 0;
        double personal = 0;
        double excedentefindemes = 0;
        double perfilconsumo = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //Persona persona = null;
        List<Persona> personas = seleccionar();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                for (Persona personaBd:personas){
                    if (personaBd.getUsuario().equals(usuarioActual)){
                        idPersona = personaBd.getIdPersona();
                        telefono = personaBd.getTelefono();
                        ingresosmensuales = personaBd.getIngresosmensuales();
                        transporte = personaBd.getTransporte();
                        alimentacion = personaBd.getAlimentacion();
                        servicios = personaBd.getServicios();
                        educacion = personaBd.getEducacion();
                        entretenimiento = personaBd.getEntretenimiento();
                        personal = personaBd.getPersonal();
                        excedentefindemes = personaBd.getExcedentefindemes();
                        perfilconsumo = personaBd.getPerfilconsumo();

                    }
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        double personaDDbl[] = {idPersona, telefono, ingresosmensuales, transporte, alimentacion, servicios, educacion, entretenimiento, personal, excedentefindemes, perfilconsumo};
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
        return personaDDbl;
    }

    public String[] SeleccionarUnoDS(String usuarioActual){
        String contraseña = "";
        String nombre = "";
        String apellido = "" ;
        String email = "";
        String metodopagomasusado = "";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //Persona persona = null;
        List<Persona> personas = seleccionar();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                for (Persona personaBd:personas){
                    if (personaBd.getUsuario().equals(usuarioActual)){
                        usuarioActual = personaBd.getUsuario();
                        contraseña = personaBd.getContraseña();
                        nombre = personaBd.getNombre();
                        apellido = personaBd.getApellido();
                        email = personaBd.getEmail();
                        metodopagomasusado = personaBd.getMetodopagomasusado();
                    }
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        String personaDS[] = {usuarioActual, contraseña, nombre, apellido, email, metodopagomasusado};
        /*
        // indices para acceder a un dato en especifico:
         personaDS[0] = usuarioActual;
         personaDS[1] = contraseña;
         personaDS[2] = nombre;
         personaDS[3] = apellido;
         personaDS[4] = email;
         personaDS[5] = metodopagomasusado;
        */
        return personaDS;
    }


    public Persona getPersona(String username){
        Persona U=null;
        List<Persona> personas = this.seleccionar();
        for (Persona p : personas){
            if (Objects.equals(p.getUsuario(), username)){
                U = p;
            }
        }
        return U;
    }
}
