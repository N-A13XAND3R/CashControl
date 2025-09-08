package com.unal.cash.Controller.DashBoard;

import com.mysql.cj.Session;
import com.unal.cash.App;
import com.unal.cash.Database.datos.PersonaDAO;
import com.unal.cash.Database.datos.TransaccionesDAO;
import com.unal.cash.Model.JSON.JsonCRUD;
import com.unal.cash.Model.tranYmetpago.Transaccion;
import com.unal.cash.Model.Login.SesionUsuario;
import com.unal.cash.Model.PerfilesConsumo.PerfilesConsumo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.text.NumberFormat;
import java.util.*;

import javafx.scene.chart.XYChart;
import javafx.collections.ObservableList; // Import ObservableList


public class DashBoardController {
    public Button btLogout;
    public TableColumn col_id;
    public TableColumn col_monto;
    public TableColumn col_metodo;
    public TableColumn col_fecha;
    public BarChart chart_distribucionSemanal;
    public LineChart chart_resumenTransacciones;
    @FXML
    private TableView<Item> tbTabla;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private Button btnSoporte; // Assuming you want this one as well
    @FXML
    private Label txtUsername;

    @FXML
    private Button buttonAgregar;
    @FXML
    private BarChart chartPromedioGastosDiariosMes;
    @FXML
    private BarChart chartResumenGastosMensualesPorCategoria;
    @FXML
    private Label txtIngresosMensuales;
    @FXML
    private Label txtExcedenteFinDeMes;
    @FXML
    private Label txtPresupuestoDiarioSegunPerfilConsumo;
    PersonaDAO personaDao = new PersonaDAO();
    PerfilesConsumo porcentajes = new PerfilesConsumo();
    private TransaccionesDAO transaccionesDAO = new TransaccionesDAO();
    ArrayList<Item> items = new ArrayList<>();
    private  String[] DIAS_SEMANA = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    public DashBoardController() {

    }

    public void initialize() {
        String[] diasDeLaSemana = this.DIAS_SEMANA;
        Collection<Double> _precios = SesionUsuario.getPerfilStatus().getDistribucionSemanal().values();
        Map <String, Double> _distribucionSemanal = SesionUsuario.getPerfilStatus().getDistribucionSemanal();
        double[] precios_semana = _precios.stream().mapToDouble(Double::doubleValue).toArray();
        // You can access and modify the elements here before the view is shown.
        // For example, to set the text of txtUsername:

        String usuario = SesionUsuario.getUsuarioLog();
        String[] datosStr = personaDao.SeleccionarUnoDS(usuario);
        double[] datosDbl = personaDao.SeleccionarUnoDDouble(usuario);
        System.out.println("ESTE ES EL PERFIL DE CONSUMO DESDE DASHBOARD: " + SesionUsuario.getPerfilStatus().getPerfilConsumo());

        //String contraseña = datosStr[1];
        String nombre = datosStr[2];
        String apellido = datosStr[3];
        String email = datosStr[4];
        String metodopagomasusado = datosStr[5];
        double telefono = datosDbl[1];
        double ingresosmensuales = datosDbl[2];
        double transporte = datosDbl[3];
        double alimentacion = datosDbl[4];
        double servicios = datosDbl[5];
        double educacion = datosDbl[6];
        double entretenimiento = datosDbl[7];
        double personal = datosDbl[8];
        int perfilconsumo = (int) datosDbl[10]+1;
        double gastosRecurrentes = datosDbl[3] + datosDbl[4] + datosDbl[5] + datosDbl[6] + datosDbl[7] + datosDbl[8];





        PerfilesConsumo porcentajes = new PerfilesConsumo();
        PerfilesConsumo objExc = new PerfilesConsumo();
        PerfilesConsumo Pfinal = SesionUsuario.getPerfilStatus();
        double excedenteMensual;

        try {
            excedenteMensual = objExc.getExcedenteMensual(ingresosmensuales, gastosRecurrentes);
        } catch (Exception e) {
            excedenteMensual = 0;
        }
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
        double porcentajeAhorro = Pfinal.getAhorroMensual();
        double porcentajeInversion = Pfinal.getInversionMensual();

        double[] porcentajesAI = porcentajes.porcentajesAhorroEInversion(porcentajeAhorro, porcentajeInversion);

        porcentajeAhorro = porcentajesAI[0];
        porcentajeInversion = porcentajesAI[1];


        // Crear instancia de PerfilesConsumo
        PerfilesConsumo perfilGlobal = new PerfilesConsumo (ingresosmensuales, gastosRecurrentes, porcentajeAhorro, porcentajeInversion, perfilconsumo);
        // Calcular distribución semanal y obtener presupuesto por día
        Map<String, Double> distribucionSemanal = perfilGlobal.calcularDistribucionSemanal();





        this.setTxtUsername(nombre + " " + apellido);
        PerfilesConsumo PerfilC = Pfinal;
        String Fingresosmensuales = numberFormat.format(PerfilC.getingresosMensuales());

        String FExcedenteFinDeMes = numberFormat.format(PerfilC.getExcedenteMensual(ingresosmensuales, gastosRecurrentes));
        System.out.println("ESTE ES EL EXCEDENTE FIN DE MES: " + PerfilC.getExcedenteMensual() + " " + FExcedenteFinDeMes);
        this.setTxtIngresosMensuales("$" + Fingresosmensuales);

        this.setTxtExcedenteFinDeMes("$" + FExcedenteFinDeMes);

        this.txtPresupuestoDiarioSegunPerfilConsumo.setText("$" + calculateAverage(precios_semana));

        //TODO:  get form json
        List<Transaccion> trlist = JsonCRUD.getTransaccionesList(usuario);
        int index=1;
        for (Transaccion t : trlist) {
            Item n = new Item();

            n.setId(index);
            n.setMonto(t.getMonto());
            n.setMetodoDePago(t.getTipoTransaccion());
            n.setFecha(t.getFecha());
            n.setMontoFinal(t.getMontoFinal());

            this.items.add(n); // Add the Item object to the ArrayList
            System.out.println("trlist: \n" + t.toString());
            System.out.println("items: \n" + n.toString());
            index++;
        }
        TableColumn<Item, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Item, String> montoCol = new TableColumn<>("Monto");
        montoCol.setCellValueFactory(new PropertyValueFactory<>("monto"));

        TableColumn<Item, String> metodoPagoCol = new TableColumn<>("Método de Pago");
        metodoPagoCol.setCellValueFactory(new PropertyValueFactory<>("metodoDePago"));

        TableColumn<Item, String> fechaCol = new TableColumn<>("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Item, String> montoFinalCol = new TableColumn<>("Monto Final");
        montoFinalCol.setCellValueFactory(new PropertyValueFactory<>("montoFinal"));

        this.col_id.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));

        this.col_monto.setCellValueFactory(new PropertyValueFactory<Item, Double>("monto"));

        this.col_metodo.setCellValueFactory(new PropertyValueFactory<Item, String>("metodoDePago"));

        this.col_fecha.setCellValueFactory(new PropertyValueFactory<Item, String>("fecha"));


        this.tbTabla.getColumns().clear();
        this.tbTabla.getColumns().addAll(idCol, montoCol, metodoPagoCol, montoFinalCol, fechaCol);
//        TableColumn<?, ?> unnamedColumn = this.tbTabla.getColumns().getLast(); // Replace 'index' with the actual column index
//                unnamedColumn.setVisible(false);
         //Set the items list of the TableView to the ArrayList of Item objects
        this.tbTabla.setItems(FXCollections.observableArrayList(items));
        List<Transaccion> listaT = JsonCRUD.getTransaccionesList(usuario);
        System.out.println("Lista de transacciones para este usario: \n" + listaT);




        // CHARTS: GRAFICOS DE BARRAS Y DE LINEAS:

        // this.chart_distribucionSemanal




        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (int i = 0; i < diasDeLaSemana.length; i++) {
            String dia = diasDeLaSemana[i];
            series.getData().add(new XYChart.Data<>(dia,_distribucionSemanal.get(dia)));
        }
        this.chart_distribucionSemanal.getData().add(series);


        // this.chart_resumenTransacciones

        //ordenar las transacciones por fecha: antiguo a mas reciente:

        trlist.sort(Comparator.comparing(Transaccion::_getFecha));

        String[] X_fechas = new String[trlist.size()];
        double[] Y_montosFinales= new double[trlist.size()];

        for (int i = 0; i < trlist.size(); i++) {
            X_fechas[i] = trlist.get(i).getFecha();
            Y_montosFinales[i] = trlist.get(i).getMontoFinal();
        }


        XYChart.Series<String, Number> series2 = new XYChart.Series<>();

        for (int i = 0; i < X_fechas.length; i++) {
            series2.getData().add(new XYChart.Data<>(X_fechas[i], Y_montosFinales[i]));
        }

        this.chart_resumenTransacciones.getData().add(series2);


        for (Object series_a : chart_distribucionSemanal.getData()) {
            series_a = (XYChart.Series<String, Number>) series_a;
            for (Object data : ((XYChart.Series<?, ?>) series_a).getData()) {
                XYChart.Data<String, Number> data_a = (XYChart.Data<String, Number>) data;
                Label label = new Label(data_a.getYValue().toString());
                label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
                Node node = data_a.getNode();
                node.parentProperty().addListener((ov, oldParent, parent) -> {
                    Group parentGroup = (Group) parent;
                    parentGroup.getChildren().add(label);
                });
                node.boundsInParentProperty().addListener((ov, oldBounds, bounds) -> {
                    label.setLayoutX(Math.round(
                            bounds.getMinX() + bounds.getWidth() / 2 - label.prefWidth(-1) / 2));
                    label.setLayoutY(Math.round(
                            bounds.getMinY() - label.prefHeight(-1) * 0.5));
                });
            }
        }

    }


    public void setTxtIngresosMensuales(String txtIngresosMensuales) {
        this.txtIngresosMensuales.setText(txtIngresosMensuales);
    }

    public void setTxtExcedenteFinDeMes(String txtExcedenteFinDeMes) {
        this.txtExcedenteFinDeMes.setText(txtExcedenteFinDeMes);
    }


    public void cambioMisMetodosPago(ActionEvent actionEvent) {
        new App().mostrarVista("MetodosdePago/MetodosdePago.fxml");
    }

    public void setTxtUsername(String txtUsername) {
        this.txtUsername.setText(txtUsername);
    }

    public void cambioInformacionPersonal(ActionEvent actionEvent) {
        new App().mostrarVista("InformacionPersonal/InformacionPersonal.fxml");
    }

    public void cambioSobreNosotros(ActionEvent actionEvent) {

        new App().mostrarVista("SobreNosotros/SobreNosotros.fxml");

    }

    public void cambioSoporte(ActionEvent actionEvent) {
        new App().mostrarVista("SobreNosotros/Soporte.fxml");
    }

    public void cambioActualizarInformacion(ActionEvent actionEvent) {
        if (SesionUsuario.session()) {
            new App().mostrarVista("InformacionPersonal/ModificarInfo.fxml");

        }
    }

    public void cambioPerfilConsumo(ActionEvent actionEvent) {
        new App().mostrarVista("PerfilConsumo/PerfildeConsumo.fxml");
    }

    public void cambioAhorroInversion(ActionEvent actionEvent) {
        new App().mostrarVista("AhorroInversion/SeleccioneAhorroInversion.fxml");

    }

    public void cambioGastosRecurrentes(ActionEvent actionEvent) {
        new App().mostrarVista("Login/GastosRecurrentes.fxml");

    }

    public void cambioIngresosMensuales(ActionEvent actionEvent) {
        new App().mostrarVista("RegistroIngresosMensuales/IngresosMensuales.fxml");

    }

    public void Logout(ActionEvent actionEvent) {
        boolean success = SesionUsuario.Logout();
        if (success) {
            System.out.println("Logout exitoso!");
            new App().mostrarVista("Home.fxml");
        }
    }

    public void Agregar(ActionEvent actionEvent) {

        if (SesionUsuario.session()) {
            new App().mostrarVista("AgregarTransaccion/AgregarTransaccion.fxml");
        }
    }
    public double calculateAverage(double[] array) {
        return Arrays.stream(array).average().orElse(Double.NaN);
    }

    public void cambioInformacionPersonalExtra(ActionEvent actionEvent) {

        if (SesionUsuario.session()) {
            new App().mostrarVista("InformacionPersonal/ExtraInformacionPersonal.fxml");
        }
    }
    public static Double convertStringToDouble(String str) {
        // Reemplaza las comas por puntos
        String replacedStr = str.replace(',', '.');

        try {
            // Intenta convertir el string a double
            return Double.parseDouble(replacedStr);
        } catch (NumberFormatException e) {
            // Imprime un mensaje de error si el string no puede ser convertido a double
            System.out.println("Error: El string contiene caracteres no numéricos o no es un número válido.");
            return null;
        }
    }
}
