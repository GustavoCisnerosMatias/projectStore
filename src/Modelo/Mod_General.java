/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Cisneros
 */
public class Mod_General {
    

    static int auxNumFact;

    static int AuxnumFac;

    static String AuxIDFactura;

    static String AuxUsuarioRecuperacion;
    static int AuxIdPerfil;
    static int AuxIdUsuario;
    static int AuxIdCliente;
    static int AuxIdProducto;

    public static int getAuxNumFact() {
        return auxNumFact;
    }

    public static void setAuxNumFact(int auxNumFact) {
        Mod_General.auxNumFact = auxNumFact;
    }



    public static int getAuxnumFac() {
        return AuxnumFac;
    }

    public static void setAuxnumFac(int AuxnumFac) {
        Mod_General.AuxnumFac = AuxnumFac;
    }

    public static String getAuxIDFactura() {
        return AuxIDFactura;
    }

    public static void setAuxIDFactura(String AuxIDFactura) {
        Mod_General.AuxIDFactura = AuxIDFactura;
    }

    public static int getAuxIdUsuario() {
        return AuxIdUsuario;
    }

    public static void setAuxIdUsuario(int AuxIdUsuario) {
        Mod_General.AuxIdUsuario = AuxIdUsuario;
    }

    public static int getAuxIdCliente() {
        return AuxIdCliente;
    }

    public static void setAuxIdCliente(int AuxIdCliente) {
        Mod_General.AuxIdCliente = AuxIdCliente;
    }

    public static int getAuxIdProducto() {
        return AuxIdProducto;
    }

    public static void setAuxIdProducto(int AuxIdProducto) {
        Mod_General.AuxIdProducto = AuxIdProducto;
    }

    public static int ValidarCredenciales(String Usuario, String Clave) {
        Usuario objUsuario = new Usuario();
        ObservableList<Usuario> listaUsuario = objUsuario.getUsuario();
        for (Usuario auxUsuario : listaUsuario) {
            if (auxUsuario.getUsr_usuario().equals(Usuario)) {
                //usuario correcto
                if (auxUsuario.getUsr_clave().equals(Clave)) {
                    //login valid
                    Modelo.Mod_General.fun_mensajeInformacion("Credenciales Correctas");
                    if ("1".equals(auxUsuario.getPer_id())) {
                        return 1;
                    } else {
                        return 3;
                    }

                } else {
                    //Contraseña Incorrecta

                    return 2;
                }
            }
        }
        Modelo.Mod_General.fun_mensajeInformacion("No se encontro el usuario");
        return 0;
    }

    public static int getAuxIdPerfil() {
        return AuxIdPerfil;
    }

    public static void setAuxIdPerfil(int AuxIdPerfil) {
        Mod_General.AuxIdPerfil = AuxIdPerfil;
    }

    public String getAuxUsuarioRecuperacion() {
        return AuxUsuarioRecuperacion;
    }

    public void setAuxUsuarioRecuperacion(String AuxUsuarioRecuperacion) {
        this.AuxUsuarioRecuperacion = AuxUsuarioRecuperacion;
    }

    public static void fun_mensajeInformacion(String miMensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Mensaje del Sistema");
        alert.setContentText(miMensaje);
        alert.showAndWait();
    }

    private String MotorDB = "SQL";
    //1 paso; nombre del servidor
    private String servidor = "DESKTOP-IJL02JU";
    //2 paso; nombre de la BD
    private String nombreBD = "bd_tienda";
    //3 paso: usuario de la BD
    private String usuarioBD = "usr2023";
    //4 paso: clave del usuario de la BD
    private String claveBD = "usr2023";

    /*
    private String MotorDB = "mysql";
    //1 paso; nombre del servidor
    private String servidor = "127.0.0.1";
    //2 paso; nombre de la BD
    private String nombreBD = "DB2023_1";
    //3 paso: usuario de la BD
    private String usuarioBD = "root";
    //4 paso: clave del usuario de la BD
    private String claveBD = "";
     */
    //5 paso: nombre de la clase o componente a trabajar
    private String classNombre = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //Cadena de conexion
    private String cadenaConexion = "jdbc:sqlserver://" + servidor + ":1433;"
            + "database=" + nombreBD + ";"
            + "user=" + usuarioBD + ";"
            + "password=" + claveBD + ";"
            + "encrypt=false; loginTimeout=30;";

    private String classNombreMySQL = "com.mysql.cj.jdbc.Driver";

    private String cadenaConexionMYSQL = "jdbc:mysql://" + servidor + ":3306/"
            + nombreBD + "?serverTimezone=UTC-5";

    public String getClassNombreMySQL() {
        return classNombreMySQL;
    }

    public void setClassNombreMySQL(String classNombreMySQL) {
        this.classNombreMySQL = classNombreMySQL;
    }

    public String getCadenaConexionMYSQL() {
        return cadenaConexionMYSQL;
    }

    public void setCadenaConexionMYSQL(String cadenaConexionMYSQL) {
        this.cadenaConexionMYSQL = cadenaConexionMYSQL;
    }

    public Mod_General() {
    }

    public String getMotorDB() {
        return MotorDB;
    }

    public void setMotorDB(String bandera) {
        this.MotorDB = bandera;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }

    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    public String getClaveBD() {
        return claveBD;
    }

    public void setClaveBD(String claveBD) {
        this.claveBD = claveBD;
    }

    public String getClassNombre() {
        return classNombre;
    }

    public void setClassNombre(String classNombre) {
        this.classNombre = classNombre;
    }

    public String getCadenaConexion() {
        return cadenaConexion;
    }

    public void setCadenaConexion(String cadenaConexion) {
        this.cadenaConexion = cadenaConexion;
    }

    public VBox fadeAnimate(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(getClass().getResource(url));
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(v);//nodo        
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }

    public void setDataPane(Node node, VBox dataPane) {
        dataPane.getChildren().setAll(node);
        //dataPane.setPadding(new Insets(50, 100, 100, 200)); //centrar
        dataPane.setAlignment(Pos.CENTER_LEFT);

    }
}
