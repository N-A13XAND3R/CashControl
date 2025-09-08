package com.unal.cash.Controller.PerfilConsumo;

import com.unal.cash.App;
import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Database.domain.Persona;
import com.unal.cash.Model.Login.SesionUsuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class PerfildeConsumoController {
    public Label labConsumoActual;
    @FXML
    private Button bt_const;

    @FXML
    private Button bt_finDeSemG;

    @FXML
    private Button bt_entreSemG;

    @FXML
    private Button bt_viernesG;

    private Persona P;

    private Button selected;
    private final String[] perfilesConsumo = {
            "Constante",
            "Fin de semana gastador",
            "Entre semana gastador",
            "Viernes gastador"};

    private ArrayList<Button> btlist ;
    private PersonaDAO personaDao= new PersonaDAO();

    @FXML
    void initialize() {
        this.btlist = new ArrayList<>(Arrays.asList(this.bt_const, this.bt_finDeSemG, this.bt_entreSemG, this.bt_viernesG));
        this.selected = new Button();
        // Obtener los datos en un hilo separado
        new Thread(() -> {
            this.P = this.personaDao.getPersona(SesionUsuario.getUsuarioLog());
            int indexConsumoActual = (int)P.getPerfilconsumo();

            // Actualizar la interfaz de usuario en el hilo de la interfaz de usuario
            Platform.runLater(() -> {
                if (this.labConsumoActual == null) this.labConsumoActual = new Label("Perfil de consumo actual: "+ this.perfilesConsumo[indexConsumoActual]);
                this.labConsumoActual.setText("Perfil de consumo actual: "+ this.perfilesConsumo[indexConsumoActual]);
                Button button = this.btlist.get(indexConsumoActual);
                if (button != null) {
                    this.selected = button;
                    this.Colorbt(new ActionEvent());
                } else {
                    System.out.println("El botón obtenido es null");
                }
            });
        }).start();

        if (this.selected == null) this.selected = this.btlist.get((int)(this.personaDao.getPersona(SesionUsuario.getUsuarioLog())).getPerfilconsumo());
    }

    void Colorbt(ActionEvent event) {
        // Cambiar el color del texto del botón seleccionado a rojo
        if (this.selected == null) {
            Button button = this.btlist.get((int)(this.personaDao.getPersona(SesionUsuario.getUsuarioLog())).getPerfilconsumo());
            if (button != null) {
                this.selected = button;
            } else {
                System.out.println("Colorbt:--> El botón obtenido es null");
                return;
            }
        }
        this.selected.setTextFill(Color.RED);
        System.out.print("el seleccionado es: " + this.selected.getText());

        // Cambiar el color del texto de todos los demás botones a negro
        for (Button otherBt : btlist) {
            if (otherBt != this.selected) {
                otherBt.setTextFill(Color.BLACK);
            }
        }
    }

    @FXML
    public void cambioInterfaz(ActionEvent mouseEvent) {
        // Aquí va tu código para cambiar la interfaz
        if (SesionUsuario.session()){
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        }else{
            new App().mostrarVista("Home.fxml");
        }
    }

    @FXML
    void CambiarContinuar(ActionEvent event) {
        if(this.selected ==null) return;
        int index = Arrays.asList(this.perfilesConsumo).indexOf(this.selected.getText());
        Persona P =this.personaDao.getPersona(SesionUsuario.getUsuarioLog());
        double data = Double.parseDouble(Integer.toString(index));
        P.setPerfilconsumo(data);
        System.out.println("data: " + data);
        System.out.println("P: \n" + P.toString());
        this.personaDao.actualizar(P);
        if (SesionUsuario.session()){
            new App().mostrarVista("PerfilConsumo/PerfildeConsumoActualizado.fxml");
        }else{
            new App().mostrarVista("Home.fxml");
        }
    }

    public void set_gconst(ActionEvent actionEvent) {
        this.selected = this.bt_const;
        this.Colorbt(actionEvent);
    }

    public void set_gfindeg(ActionEvent actionEvent) {
        this.selected = this.bt_finDeSemG;
        this.Colorbt(actionEvent);
    }

    public void set_gentresemg(ActionEvent actionEvent) {
        this.selected = this.bt_entreSemG;
        this.Colorbt(actionEvent);
    }

    public void set_gviernesg(ActionEvent actionEvent) {
        this.selected = this.bt_viernesG;
        this.Colorbt(actionEvent);
    }
}
