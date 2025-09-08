package com.unal.cash.Database.domain;

//esta es la clase que tiene relacion con la base de datos

public class Persona {
    private double idPersona;
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
    private double excedentefindemes;
    private double perfilconsumo;
    private String metodopagomasusado;

    public Persona(){
    }

    public Persona(double idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Persona(String nombre, String apellido, String email, double telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public void setPerfilconsumo(double perfilconsumo) {
        this.perfilconsumo = perfilconsumo;
    }

    public Persona(String usuario, String contraseña, String nombre, String apellido, String email, double telefono, double ingresosmensuales, double transporte, double alimentacion, double servicios, double educacion, double entretenimiento, double personal, double excedentefindemes, double perfilconsumo, String metodopagomasusado) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.ingresosmensuales = ingresosmensuales;
        this.transporte = transporte;
        this.alimentacion = alimentacion;
        this.servicios = servicios;
        this.educacion = educacion;
        this.entretenimiento = entretenimiento;
        this.personal = personal;
        this.excedentefindemes = excedentefindemes;
        this.perfilconsumo = perfilconsumo;
        this.metodopagomasusado = metodopagomasusado;
    }


    public Persona(double idPersona, String usuario, String contraseña, String nombre, String apellido, String email, double telefono, double ingresosmensuales, double transporte, double alimentacion, double servicios, double educacion, double entretenimiento, double personal, double excedentefindemes, double perfilconsumo, String metodopagomasusado) {

        this.idPersona = idPersona;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.ingresosmensuales = ingresosmensuales;
        this.transporte = transporte;
        this.alimentacion = alimentacion;
        this.servicios = servicios;
        this.educacion = educacion;
        this.entretenimiento = entretenimiento;
        this.personal = personal;
        this.excedentefindemes = excedentefindemes;
        this.perfilconsumo = perfilconsumo;
        this.metodopagomasusado = metodopagomasusado;
    }

    public double getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(double idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public double getIngresosmensuales() {
        return ingresosmensuales;
    }

    public void setIngresosmensuales(double ingresosmensuales) {
        this.ingresosmensuales = ingresosmensuales;
    }

    public double getTransporte() {
        return transporte;
    }

    public void setTransporte(double transporte) {
        this.transporte = transporte;
    }

    public double getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(double alimentacion) {
        this.alimentacion = alimentacion;
    }

    public double getServicios() {
        return servicios;
    }

    public void setServicios(double servicios) {
        this.servicios = servicios;
    }

    public double getEducacion() {
        return educacion;
    }

    public void setEducacion(double educacion) {
        this.educacion = educacion;
    }

    public double getEntretenimiento() {
        return entretenimiento;
    }

    public void setEntretenimiento(double entretenimiento) {
        this.entretenimiento = entretenimiento;
    }

    public double getPersonal() {
        return personal;
    }

    public void setPersonal(double personal) {
        this.personal = personal;
    }

    public double getExcedentefindemes() {
        return excedentefindemes;
    }

    public void setExcedentefindemes(double excedentefindemes) {
        this.excedentefindemes = excedentefindemes;
    }

    public double getPerfilconsumo() {
        return perfilconsumo;
    }

    public void setPresupuestodiario(double perfilconsumo) {
        this.perfilconsumo = perfilconsumo;
    }

    public String getMetodopagomasusado() {
        return metodopagomasusado;
    }

    public void setMetodopagomasusado(String metodopagomasusado) {
        this.metodopagomasusado = metodopagomasusado;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + ", ingresosmensuales=" + ingresosmensuales + ", transporte=" + transporte + ", alimentacion=" + alimentacion + ", servicios=" + servicios + ", educacion=" + educacion + ", entretenimiento=" + entretenimiento + ", personal=" + personal + ", excedentefindemes=" + excedentefindemes + ", perfilconsumo=" + perfilconsumo + ", metodopagomasusado=" + metodopagomasusado + '}';
    }

//    public int getId() {
//        return this.id;
//    }//
}
