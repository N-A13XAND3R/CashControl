package com.unal.cash.Database.domain;

import java.util.Date;

public class Transaccion {
    private int id;
    private double persona;
    private Date fecha_registro;
    private String monto;
    private String metodo_de_pago;

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", persona=" + persona +
                ", fecha_registro=" + fecha_registro +
                ", monto='" + monto + '\'' +
                ", metodo_de_pago='" + metodo_de_pago + '\'' +
                ", tipo_de_transaccion='" + tipo_de_transaccion + '\'' +
                ", nuevo_presupuesto_diario='" + nuevo_presupuesto_diario + '\'' +
                '}';
    }

    private String tipo_de_transaccion;
    private String nuevo_presupuesto_diario;

//    public int getPersona() {
//        return persona;
//    }

    public void setPersona(int persona) {
        this.persona = persona;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMetodo_de_pago() {
        return metodo_de_pago;
    }

    public void setMetodo_de_pago(String metodo_de_pago) {
        this.metodo_de_pago = metodo_de_pago;
    }

    public String getTipo_de_transaccion() {
        return tipo_de_transaccion;
    }

    public void setTipo_de_transaccion(String tipo_de_transaccion) {
        this.tipo_de_transaccion = tipo_de_transaccion;
    }

    public String getNuevo_presupuesto_diario() {
        return nuevo_presupuesto_diario;
    }

    public void setNuevo_presupuesto_diario(String nuevo_presupuesto_diario) {
        this.nuevo_presupuesto_diario = nuevo_presupuesto_diario;
    }

    public Transaccion( double id_persona, Date fecha_registro, String monto, String metodo_de_pago, String tipo_de_transaccion, String nuevo_presupuesto_diario) {

        this.persona = id_persona;
        this.fecha_registro = fecha_registro;
        this.monto = monto;
        this.metodo_de_pago = metodo_de_pago;
        this.tipo_de_transaccion = tipo_de_transaccion;
        this.nuevo_presupuesto_diario = nuevo_presupuesto_diario;
    }

    public Transaccion(double id_persona , String monto, String metodo_de_pago, String tipo_de_transaccion, String nuevo_presupuesto_diario) {
        this.persona = id_persona;
        this.monto = monto;
        this.metodo_de_pago = metodo_de_pago;
        this.tipo_de_transaccion = tipo_de_transaccion;
        this.nuevo_presupuesto_diario = nuevo_presupuesto_diario;
    }

//    public int getId() {
//        return this.id;
//    }
}
