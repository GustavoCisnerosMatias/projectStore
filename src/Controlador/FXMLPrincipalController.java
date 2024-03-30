/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Mod_General;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Cisneros
 */
public class FXMLPrincipalController implements Initializable {

    Mod_General General = new Mod_General();
    @FXML
    private Label txt_titulo;
    @FXML
    private Button btnSalir2;
    @FXML
    private ImageView btnSalir;
    @FXML
    private SplitPane splitcontenedor;
    @FXML
    private Button btn_boton2;
    @FXML
    private Button btn_boton21;
    @FXML
    private Button btn_boton22;
    @FXML
    private VBox dataPane;
    @FXML
    private Button btn_registrarCliente;
    @FXML
    private Button btn_boton;
    @FXML
    private Button btn_registrarPerfil;
    @FXML
    private Button btn_registrarUsuarios;
    @FXML
    private Button btn_registrarProductos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Todo Cerrar
    @FXML
    private void ac_cerrar(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void ac_btn_cerrar(ActionEvent event) {
        closeWindows();
    }

    public static void cerrarVentana(ActionEvent e) {
        Node source = (Node) e.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();                          //Me cierra la ventana
    }
    //Fin Cerar

    @FXML
    private void pruebaaccion(KeyEvent event) {
        System.out.println("Activado");
    }

    //fin Page
    @FXML
    private void ac_btn_IR_VerPerfiles(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerPerfiles.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    @FXML
    private void ac_btn_IR_VerUsuarios(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerUsuarios.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

    @FXML
    private void ac_btn_IR_VerClientes(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerClientes.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

    @FXML
    private void ac_btn_IR_VerProductos(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerProductos.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

    @FXML
    private void ac_MenuItemVerPefiles(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerPerfiles.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

    @FXML
    private void ac_MenuItemVerUsuarios(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerUsuarios.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

    @FXML
    private void ac_MenuItemVerClientes(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerClientes.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

    @FXML
    private void ac_MenuItemVerProductos(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerProductos.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

    @FXML
    private void Ir_registroPerfil(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLPerfil.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/FXMLVerificacion.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnSalir.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println("errooooo");
        }
    }

    @FXML
    private void Ir_registroUsuario(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLUsuario.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    @FXML
    private void Ir_registroCliente(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLCliente.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    @FXML
    private void Ir_registroProducto(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLProducto.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

}
