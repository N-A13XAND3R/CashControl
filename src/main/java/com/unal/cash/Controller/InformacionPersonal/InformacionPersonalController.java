package com.unal.cash.Controller.InformacionPersonal;
import com.unal.cash.App;
import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Model.Login.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class InformacionPersonalController {

    public TextField txt_usuario;
    public TextField txt_contrasena;
    public TextField txt_nombre;
    public TextField txt_apellido;
    public TextField txt_correo;
    public TextField txt_telefono;
    public TextField txt_ingresos_mensuales;
    public TextField txt_transporte;
    public TextField txt_alimentacion;
    public TextField txt_servicios;
    public TextField txt_educacion;
    public TextField txt_entretenimiento;
    public TextField txt_personal;
    public TextField txt_excedente_fin_mes;
    public TextField txt_metodo_pago_mas_usado;
    public Button bt_volver;
    public ChoiceBox cb_perfil_consumo;

    private String cbPerfilConsumoSelected;

    private final String[] perfilesConsumo = {
            "Constante",
            "Fin de semana gastador",
            "Entre semana gastador",
            "Viernes gastador"};

    PersonaDAO personaDao = new PersonaDAO();

    public void initialize() {
        // You can access and modify the elements here before the view is shown.
        // For example, to set the text of txtUsername:

        String usuario = SesionUsuario.getUsuarioLog();
        String[] datosStr = personaDao.SeleccionarUnoDS(usuario);
        double[] datosDbl = personaDao.SeleccionarUnoDDouble(usuario);
        String[] datosDbl2 = new String[datosDbl.length];
        for (int i = 0; i < datosDbl.length; i++) {
            datosDbl2[i] = Double.toString(datosDbl[i]);
        }

        this.txt_usuario.setText(datosStr[0]);
        this.txt_contrasena.setText(datosStr[1]);
        this.txt_nombre.setText(datosStr[2]);
        this.txt_apellido.setText(datosStr[3]);
        this.txt_correo.setText(datosStr[4]);
        this.txt_metodo_pago_mas_usado.setText(datosStr[5]);

        /*
        // indices para acceder a un dato en especifico:
         personaDS[0] = usuarioActual;
         personaDS[1] = contraseña;
         personaDS[2] = nombre;
         personaDS[3] = apellido;
         personaDS[4] = email;
         personaDS[5] = metodopagomasusado;
        */

        int tel = (int) datosDbl[1];
        int ingrM = (int) datosDbl[2];
        this.txt_telefono.setText(Integer.toString(tel));
        this.txt_ingresos_mensuales.setText(Integer.toString(ingrM));
        this.txt_transporte.setText(datosDbl2[3]);
        this.txt_alimentacion.setText(datosDbl2[4]);
        this.txt_servicios.setText(datosDbl2[5]);
        this.txt_educacion.setText(datosDbl2[6]);
        this.txt_entretenimiento.setText(datosDbl2[7]);
        this.txt_personal.setText(datosDbl2[8]);
        this.txt_excedente_fin_mes.setText(datosDbl2[9]);
        this.cb_perfil_consumo.setValue(datosDbl2[10]);
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
    }

    public void cambioInterfaz(ActionEvent mouseEvent) {
        // Aquí va tu código para cambiar la interfaz
        if (SesionUsuario.session()){
            new App().mostrarVista("DashBoard/DashBoard.fxml");
        }else{
            new App().mostrarVista("Home.fxml");
        }
    }
}
