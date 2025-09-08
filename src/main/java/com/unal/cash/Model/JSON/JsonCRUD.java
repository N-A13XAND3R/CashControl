package com.unal.cash.Model.JSON;
/*
Esta clase es para la conexión y manipulación del archivo tipo JSON, donde guardamos todos los datos de las transacciones.
Es decir que funciona como una especie de base de datos.
*/
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import com.unal.cash.Model.tranYmetpago.Transaccion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonCRUD {
    private static final String NOMBRE_JSON = "Transacciones.json";
    private static final String RUTA_PADRE = System.getProperty("user.dir") + "/src/main/java/com/unal/cash/Database/";
    private static final String RUTA_ARCHIVO_JSON = RUTA_PADRE + NOMBRE_JSON;

    public JsonCRUD() {
        try {
            String path = RUTA_ARCHIVO_JSON;
            if (!Files.exists(Paths.get(path))) {
                Files.createFile(Paths.get(path));
                System.out.println("Archivo creado: " + Paths.get(path).getFileName());
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(new JSONObject()); // Agrega un objeto JSON vacío al array
                try (FileWriter escritor = new FileWriter(path)) {
                    escritor.write(jsonArray.toJSONString());
                    escritor.flush();
                }
            }
        }catch(IOException e){
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }

    public static boolean isEmpty() {


        return false;
    }
    // Método que lee el JSON y extrae toda su información
    public static String obtenerJSONDesdeArchivo() {
        System.out.println("ruta actual: " + System.getProperty("user.dir"));
        StringBuilder textoJSON = new StringBuilder();
        try (BufferedReader lector = new BufferedReader(new FileReader(RUTA_ARCHIVO_JSON))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                textoJSON.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        return textoJSON.toString();
    }

    
    public static void escribirEnArchivoJSON(JSONArray jsonArray) {
        try (FileWriter escritor = new FileWriter(RUTA_ARCHIVO_JSON)) {
            escritor.write(jsonArray.toJSONString());
            escritor.flush();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    // Método para imprimir la información del JSON. Este método es el que se usa cuando se consulta el registro de las transacciones
    public static void leerJSONYMostrar(String usuario) {
        String fecha;
        String tipoTransaccion; 
        double monto;
        double intereses;
        double cashback;
        double descuento;
        double costoTransaccion;
        double montoFinal;
        String strJson = obtenerJSONDesdeArchivo();
        try {
            JSONParser parser = new JSONParser();
            Object objeto = parser.parse(strJson);
            JSONArray jsonArray = (JSONArray) objeto;

            System.out.println("Transacciones almacenadas:\n");

            // Iterar sobre cada transacción en el JSON
            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                if (jsonObj.get("usuario").equals(usuario)){
                    //ASIGNACION DE CADA DATO
                    fecha = (String) jsonObj.get("fecha");
                    tipoTransaccion = (String) jsonObj.get("tipoTransaccionStr");
                    monto = (double) jsonObj.get("monto");
                    intereses = (double) jsonObj.get("intereses");
                    cashback = (double) jsonObj.get("cashback");
                    descuento = (double) jsonObj.get("descuento");
                    costoTransaccion = (double) jsonObj.get("costoTransaccion");
                    montoFinal = (double) jsonObj.get("montoFinal");
                    //IMPRESION DE CADA DATO en la terminal
                    System.out.println("Usuario: " + usuario);
                    System.out.println("Fecha: " + fecha);
                    System.out.println("Tipo de Transacción: " + tipoTransaccion);
                    System.out.println("Monto: " + monto);
                    System.out.println("Intereses: " + intereses);
                    System.out.println("Cashback: " + cashback);
                    System.out.println("Descuento: " + descuento);
                    System.out.println("Costo de Transacción: " + costoTransaccion);
                    System.out.println("Monto Final: " + montoFinal);
                    System.out.println("--------------------------------------");
            }
            }
        } catch (ParseException ex) {
            ex.printStackTrace(); // Manejo básico de excepciones
        }
        
    }

    public static List<Transaccion> getTransaccionesList(String usuario) {
        List<Transaccion> transacciones = new ArrayList<>();

        String strJson = obtenerJSONDesdeArchivo();
        try {
            JSONParser parser = new JSONParser();
            Object objeto = parser.parse(strJson);
            JSONArray jsonArray = (JSONArray) objeto;

            // Iterar sobre cada transacción en el JSON
            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                if (jsonObj.get("usuario").equals(usuario)){
                    // Crear una nueva transacción y asignarle los datos
                    Transaccion transaccion = new Transaccion();
                    transaccion.setUsuario(usuario);
                    transaccion.setFecha((String) jsonObj.get("fecha"));
                    transaccion.setTipoTransaccion((String) jsonObj.get("tipoTransaccionStr"));
                    transaccion.setMonto((double) jsonObj.get("monto"));
                    transaccion.setIntereses((double) jsonObj.get("intereses"));
                    transaccion.setCashback((double) jsonObj.get("cashback"));
                    transaccion.setDescuento((double) jsonObj.get("descuento"));
                    transaccion.setCostoTransaccion((double) jsonObj.get("costoTransaccion"));
                    transaccion.setMontoFinal((double) jsonObj.get("montoFinal"));

                    // Agregar la transacción a la lista
                    transacciones.add(transaccion);
                }
            }
        } catch (ParseException ex) {
            ex.printStackTrace(); // Manejo básico de excepciones
        }

        return transacciones;
    }

    // Método para subir información al archivo tipo JSON
    public static void agregarEntradaAlJSON(List<String> datos, double montoFinal) {

        System.out.println("LISTA DE DATOS: " + datos);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("usuario", datos.get(0));
        jsonObject.put("fecha", datos.get(1));
        jsonObject.put("tipoTransaccionStr", datos.get(2));
        jsonObject.put("monto", Double.parseDouble(datos.get(3)));
        jsonObject.put("intereses", Double.parseDouble(datos.get(4)));
        jsonObject.put("cashback", Double.parseDouble(datos.get(5)));
        jsonObject.put("descuento", Double.parseDouble(datos.get(6)));
        jsonObject.put("costoTransaccion", Double.parseDouble(datos.get(7)));
        jsonObject.put("montoFinal", Double.parseDouble(datos.get(3)) + Double.parseDouble(datos.get(4)) - Double.parseDouble(datos.get(5)) - Double.parseDouble(datos.get(6)) + Double.parseDouble(datos.get(7)));

        JSONArray jsonArray = new JSONArray();
        String strJson = obtenerJSONDesdeArchivo();
        try {
            JSONParser parser = new JSONParser();
            Object objeto = parser.parse(strJson);
            jsonArray = (JSONArray) objeto;
        } catch (ParseException ex) { 
            ex.printStackTrace(); 
        }

        jsonArray.add(jsonObject);
        escribirEnArchivoJSON(jsonArray);
        System.out.println("Entrada agregada con éxito al JSON.");
    }

    // Método que suma todos los montos finales del usuario. Se usa para restar esta sumatoria en el excendente mensual en la clase de PerfilesConsumo.
    public static double obtenerSumaMontosFinalesUsuario(String usuario) {
        double sumaMontosFinales = 0.0;
        boolean confirmacion = false; 
        String strJson = obtenerJSONDesdeArchivo();
        try {
            JSONParser parser = new JSONParser();
            Object objeto = parser.parse(strJson);
            JSONArray jsonArray = (JSONArray) objeto;

            // Iterar sobre cada transacción en el JSON
            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                if (jsonObj.get("usuario").equals(usuario)) {
                    double montoFinal = Double.parseDouble(jsonObj.get("montoFinal").toString());
                    sumaMontosFinales += montoFinal;
                    confirmacion = true; 
                }
            }
            if (confirmacion == false){
                sumaMontosFinales = 0;
            }
        } catch (ParseException ex) {
            ex.printStackTrace(); // Manejo básico de excepciones
        }
        return sumaMontosFinales;
    }
}
