package com.unal.cash.Model.Login;

// clase para poder usar el usuario logeado en todo el codigo 

import com.unal.cash.Model.PerfilesConsumo.PerfilesConsumo;

import java.util.Objects;

public class SesionUsuario {
    private static String Susuario;

    private static PerfilesConsumo PerfilStatus;

    public SesionUsuario() {
    }

    public static void asignacionUsuario(String usuarioLog) {
        SesionUsuario.Susuario = usuarioLog;
    }
    
    public static String getUsuarioLog(){
        return Susuario;
    }

    public static boolean session(){
        return Objects.nonNull(Susuario);
    }

    public static boolean Logout() {
        setPerfilStatus(null);
        Susuario = null;
        return true;
    }

    public static PerfilesConsumo getPerfilStatus() {
        return PerfilStatus;
    }

    public static void setPerfilStatus(PerfilesConsumo perfilStatus) {
        SesionUsuario.PerfilStatus = perfilStatus;
    }

}
