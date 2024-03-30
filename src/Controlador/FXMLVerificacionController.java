/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.ConexionDB;
import Modelo.Mod_General;
import java.util.ArrayList;
import Modelo.Perfiles;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cisneros
 */
public class FXMLVerificacionController implements Initializable {

    Mod_General General = new Mod_General();
    @FXML
    private Button btn_Ingresar;
    @FXML
    private TextField txt_usuario;
    @FXML
    private TextField txt_clave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void verificacionInicio(ActionEvent event) throws Exception {
        String auxUsuario = this.txt_usuario.getText();
        String auxClave = this.txt_clave.getText();
        //Verificar si los datos son correctos
        if (auxUsuario.length() == 0 || auxClave.length() == 0) {
            Modelo.Mod_General.fun_mensajeInformacion("Todos los campos son obligatorios");
        } else {
            //int auxCredenciales = ValidarCredenciales(auxUsuario, auxClave);
            int auxCredenciales = Modelo.Mod_General.ValidarCredenciales(auxUsuario, auxClave);
            switch (auxCredenciales) {
                case 1 ->
                    ir_principal();
                case 2 ->
                    recuperacionDatos();
                case 3 ->
                    ir_principalVentas();
            }
        }
    }
    private void recuperacionDatos() {
        Modelo.Mod_General.fun_mensajeInformacion("Su contraseña es incorrecta, sera dirigido para que pueda restaurarla");
        General.setAuxUsuarioRecuperacion(this.txt_usuario.getText());
        try {
            // Cargo la vista
            String miformulario = "/Vista/" + "FXMLRecuperacionContraseña.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(miformulario));
            // Cargo el padre
            Parent root = loader.load();
            // Obtengo el controlador
            FXMLRecuperacionContraseñaController controlador = loader.getController();
            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

            // Indico que debe hacer al cerrar
            stage.setOnCloseRequest(e -> controlador.closeWindows());

            // Ciero la ventana donde estoy
            Stage myStage = (Stage) btn_Ingresar.getScene().getWindow();
            myStage.close();
            //Me ayudo de un axiliar para poder pasar el Usuario  a la nueva ventana            
        } catch (IOException ex) {
            //   Logger.getLogger(MenuContolador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ir_principalVentas() {
        try {
            // Cargo la vista
            String miformulario = "/Vista/" + "FXMLPrincipalVentas.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(miformulario));
            // Cargo el padre
            Parent root = loader.load();
            // Obtengo el controlador
            FXMLPrincipalVentasController controlador = loader.getController();
            // Creo la scene y el stage
            Scene scene = new Scene(root, 800, 580);
            Stage stage = new Stage();
            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

            // Indico que debe hacer al cerrar
            stage.setOnCloseRequest(e -> controlador.closeWindows());

            // Ciero la ventana donde estoy
            Stage myStage = (Stage) btn_Ingresar.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            //   Logger.getLogger(MenuContolador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ir_principal() {
        try {
            // Cargo la vista
            String miformulario = "/Vista/" + "FXMLPrincipal.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(miformulario));
            // Cargo el padre
            Parent root = loader.load();
            // Obtengo el controlador
            FXMLPrincipalController controlador = loader.getController();
            // Creo la scene y el stage
            Scene scene = new Scene(root, 800, 580);
            Stage stage = new Stage();
            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

            // Indico que debe hacer al cerrar
            stage.setOnCloseRequest(e -> controlador.closeWindows());

            // Ciero la ventana donde estoy
            Stage myStage = (Stage) btn_Ingresar.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            //   Logger.getLogger(MenuContolador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
