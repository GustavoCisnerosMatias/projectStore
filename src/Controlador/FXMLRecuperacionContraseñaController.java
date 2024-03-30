/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.ConexionDB;
import Modelo.Mod_General;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cisneros
 */
public class FXMLRecuperacionContraseñaController implements Initializable {

    Mod_General General = new Mod_General();
    @FXML
    private TextField txt_usario;
    @FXML
    private TextField txt_clave;
    @FXML
    private Button btn_salir;
    @FXML
    private Button btn_recuperar;
    @FXML
    private TextField txt_usuarioARecuperar;
    @FXML
    private TextField txt_claveNueva;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.txt_usuarioARecuperar.setText(General.getAuxUsuarioRecuperacion());
        System.out.println(General.getAuxUsuarioRecuperacion());
        System.out.println(General);
    }

    @FXML
    private void Salir(ActionEvent event) {
        closeWindows();
    }

    @FXML
    private void RestaurarContraseña(ActionEvent event) {
        String auxUsuario = this.txt_usario.getText();
        String auxClave = this.txt_clave.getText();
        if (auxUsuario.length() == 0 || auxClave.length() == 0) {
            Modelo.Mod_General.fun_mensajeInformacion("Todos los campos son obligatorios");
        } else {
            //int auxCredenciales = ValidarCredenciales(auxUsuario, auxClave);
            int auxCredenciales = Modelo.Mod_General.ValidarCredenciales(auxUsuario, auxClave);

            switch (auxCredenciales) {
                case 1 ->
                    ResturarContraseña();
                case 2 ->
                    Modelo.Mod_General.fun_mensajeInformacion("Los datos del usuario que tiene acceso al sistema son incorrectos");
            }
        }

    }

    public void ResturarContraseña() {
        //datos del usuario al resturar
        String Usuario = this.txt_usuarioARecuperar.getText();
        String Clave = this.txt_claveNueva.getText();
        //Lineas sql a ejecutar
        String cadenaSQL = "";
        cadenaSQL = cadenaSQL + " update dbo.Usuario";
        cadenaSQL = cadenaSQL + " set usr_clave= '" + Clave + "'";
        cadenaSQL = cadenaSQL + " where usr_usuario= '" + Usuario + "';";
        //conectar a la BD
        Mod_General modG = new Mod_General();
        ConexionDB conec = new ConexionDB();
        conec.conectarBD(modG.getMotorDB());//conectar    
        conec.ejecutarSQLCommit(cadenaSQL, true);
        closeWindows();
    }

    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/FXMLVerificacion.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btn_salir.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println("errooooo");
        }
    }
}
