package com.unal.cash.Model.tranYmetpago;
/*
Esta clase permite que el usuario registre aquellas transacciones, las cuales son exclusivamente EGRESOS y además
que superan el presupuesto diario sugerido por la aplicación.
*/
import com.unal.cash.Model.JSON.JsonCRUD;
import com.unal.cash.Model.Login.SesionUsuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.DatePicker;

public class Transaccion {

    private String usuario;
    private String fecha;
    private String tipoTransaccion;
    private double monto;
    private double intereses;
    private double cashback;
    private double descuento;
    private double costoTransaccion;
    private double montoFinal;

    // Getters y setters para cada campo...

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }
    public Date _getFecha() {
        // parsear string fecha dd/MM/yyyy a Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getCashback() {
        return cashback;
    }

    public void setCashback(double cashback) {
        this.cashback = cashback;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getCostoTransaccion() {
        return costoTransaccion;
    }

    public void setCostoTransaccion(double costoTransaccion) {
        this.costoTransaccion = costoTransaccion;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }
    public Transaccion() {
    }
    
    //Método que registra la transacción y sube la respectiva información al JSON
    public void realizarTransaccion(Scanner scanner) {
        String usuario = SesionUsuario.getUsuarioLog();
        String fecha;
        double monto;
        int tipoTransaccion;
        String tipoTransaccionStr = "";
        double intereses;
        double cashback;
        double descuento;
        double costoTransaccion;
                
        CaracteristicasTransaccion infoTransaccion;
        infoTransaccion = new CaracteristicasTransaccion();
        
        System.out.println("**************************\nRegistro de Transacción (Egresos)\n**************************");
        System.out.println("\n¡El registro de una transacción es porque esta excedió el presupuesto diarío de ese día que la aplicación le recomienda!\n");

        while (true){
            
            //List<Double> montosFinalesTran = new ArrayList<>(); IGNORAR ESTA LINEA
            
            // Capturar la fecha en el formato día/mes/año
            System.out.print("Por favor ingrese la fecha de la transacción (dd/mm/yyyy): ");
            fecha = scanner.next();
            scanner.nextLine(); // Limpiar el buffer del scanner
            
            // Obtener características de la transacción
            double[] ctrTran = infoTransaccion.registroCaracteristicas(); // Llamada sin argumentos
            tipoTransaccion = (int) ctrTran[0];
            
            // Convertir tipo de transacción a texto
            switch (tipoTransaccion){
                case 1 -> tipoTransaccionStr = "Tarjeta débito";
                case 2 -> tipoTransaccionStr = "Tarjeta crédito";
                case 3 -> tipoTransaccionStr = "Efectivo";
                case 4 -> tipoTransaccionStr = "Transferencia por aplicación";
            }
            
            // Asignar valores a las variables
            monto = ctrTran[1];
            intereses = ctrTran[2];
            cashback = ctrTran[3];
            descuento = ctrTran[4];
            costoTransaccion = ctrTran[5];
           
            // Calcular monto final de la transacción
            double montoFinal = calcularMontoFinal(monto, intereses, cashback, descuento, costoTransaccion);
            
            //montosFinalesTran.add(montoFinal); IGNORAR ESTA LINEA

            // Verificar que la fecha no sea nula antes de agregarla a la lista
            if (fecha != null) {
                // Pasar los datos a JsonCRUD para guardarlos en el JSON
                List<String> data = List.of(usuario, fecha, tipoTransaccionStr, String.valueOf(monto),
                                            String.valueOf(intereses), String.valueOf(cashback),
                                            String.valueOf(descuento), String.valueOf(costoTransaccion));
                JsonCRUD.agregarEntradaAlJSON(data, montoFinal);
                System.out.println("\n\n¡Registro de información exitosa!\n\n");
            } else {
                System.out.println("Error: La fecha ingresada es nula. La transacción no se registrará.");
                continue; // Continuar con la siguiente iteración del bucle
            }
            
            // Consultar si desea registrar otra transacción
            System.out.print("¿Desea registrar otra transacción? (si/no): ");
            String respuesta = scanner.nextLine(); // Usar Scanner para leer entrada del usuario
            if (!respuesta.equalsIgnoreCase("si")) {
                break;
            }
        }
    }
    
    // Método que calcula el monto final de la transacción ya teniendo en cuenta todos los parámetros digitados por el usuario
    private double calcularMontoFinal(double monto, double intereses, double cashback, double descuento, double costoTransaccion) {
        return monto + intereses - cashback - descuento + costoTransaccion;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "usuario='" + usuario + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", monto=" + monto +
                ", intereses=" + intereses +
                ", cashback=" + cashback +
                ", descuento=" + descuento +
                ", costoTransaccion=" + costoTransaccion +
                ", montoFinal=" + montoFinal +
                '}';
    }
}
