package com.unal.cash.Database.datos;

import com.unal.cash.Database.domain.Persona;
import com.unal.cash.Model.tranYmetpago.Transaccion;import com.unal.cash.Model.Login.SesionUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.unal.cash.Database.datos.Conexion.close;
import static com.unal.cash.Database.datos.Conexion.getConnection;

public class TransaccionesDAO {
//    public Persona P;
//    private PersonaDAO P0;


    private static  String SQL_SELECT = "SELECT t.id, t.monto, t.metodo_de_pago, t.fecha_registro\n" +
            "FROM transacciones t\n" +
            "WHERE t.persona = ?\n" +
            "ORDER BY t.fecha_registro DESC;";

    private static  String SQL_SELECT_ALL = "SELECT * FROM transacciones ORDER BY fecha_registro DESC;";

    private static  String SQL_INSERT = "INSERT INTO transacciones (persona, fecha_registro, monto, metodo_de_pago, tipo_de_transaccion, nuevo_presupuesto_diario)\n" +
            "VALUES (?, ?, ?, ?, ?, ?);";


    public TransaccionesDAO() {
//        this.P0 = new PersonaDAO();
//        this.P = P0.getPersona(SesionUsuario.getUsuarioLog());

        //SQL_SELECT= "SELECT t.id, t.monto, t.metodo_de_pago, t.fecha_registro\n" +
//                "FROM transacciones t\n" +
//                "WHERE t.persona = " + P.getId()+
//                "\n ORDER BY t.fecha_registro DESC;";
    }

    public List<Transaccion> seleccionar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Transaccion transaccion = null;
        List<Transaccion> transacciones = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int persona = rs.getInt("persona");
                //if (persona!= this.P.getId()) continue;
                Date fecha_registro = rs.getDate("fecha_registro");
                String monto = rs.getString("monto");
                String metodo_de_pago = rs.getString("metodo_de_pago");
                String tipo_de_transaccion = rs.getString("tipo_de_transaccion");
                String nuevo_presupuesto_diario = rs.getString("nuevo_presupuesto_diario");

                //transaccion = new Transaccion( id,  persona,  fecha_registro,  monto,  metodo_de_pago,  tipo_de_transaccion,  nuevo_presupuesto_diario);

                //añadimos a la lista tipo Persona, la variable transaccion, la cual contiene toda la informacion de una transaccion
                //transacciones.add(transaccion);
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
        return transacciones;
    }



    public int insertar(Transaccion transaccion){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {

// Convert the LocalDate object to a java.sql.Date object
            Date d = new Date(System.currentTimeMillis());
//            java.sql.Date sqld = new java.sql.Date(d.getTime());
//            conn = getConnection();
//            stmt = conn.prepareStatement(SQL_INSERT);
//            stmt.setInt(1, transaccion.getPersona());
//            stmt.setDate(2, sqld);
//            stmt.setString(3, transaccion.getMonto());
//            stmt.setString(4, transaccion.getMetodo_de_pago());
//            stmt.setString(5, transaccion.getTipo_de_transaccion());
//            stmt.setString(6, transaccion.getNuevo_presupuesto_diario());
//
//
//            registros = stmt.executeUpdate();

        } catch (Exception ex) {
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


}
