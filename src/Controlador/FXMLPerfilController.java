/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.ConexionDB;
import Modelo.Mod_General;
import Modelo.Perfiles;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Cisneros
 */
public class FXMLPerfilController implements Initializable {

    Mod_General General = new Mod_General();
    @FXML
    private TextField input_ID;
    @FXML
    private TextField input_descripcion;

    @FXML
    private Button btn_Guardar;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private VBox dataPane;
    @FXML
    private CheckBox chk_estado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (General.getAuxIdPerfil() == 0) {
            //es nuevo el registro
            this.input_ID.setText("EL ID se establece en Automatico");
        } else {
            //vamos a editar un registro 
            int IDPERFIL = General.getAuxIdPerfil();
            this.input_ID.setText(Integer.toString(IDPERFIL));
            Perfiles objPerfil = cargarPerfil(IDPERFIL);
            if (objPerfil != null) {
                this.input_ID.setText(objPerfil.getID_Perfil());
                this.input_descripcion.setText(objPerfil.getDescripcion_Perfil());
                //checkBox
                if("A".equals(objPerfil.getEstado_perfil())){
                    this.chk_estado.setSelected(true);
                }
            }
        }
        General.setAuxIdPerfil(0);
    }

    public Perfiles cargarPerfil(int IDPERFIL) {
        Perfiles objPerfiles = new Perfiles();
        ObservableList<Perfiles> listaPerfiles = objPerfiles.getPerfiles();
        for (Perfiles auxPerfiles : listaPerfiles) {
            if (auxPerfiles.getID_Perfil().equals(Integer.toString(IDPERFIL))) {
                return auxPerfiles;
            }
        }
        return null;
    }

    @FXML
    private void guardar(ActionEvent event) throws SQLException, IOException {
        //obtenemos los datos a modificar
        String IdPerfil = this.input_ID.getText();
        String DescripcionPerfil = this.input_descripcion.getText();
        String EstadoPerfil=null;
        //Checkbox
        if(this.chk_estado.isSelected()){
            EstadoPerfil="A";
        }else{
            EstadoPerfil="D";
        }
        //Lineas sql a ejecutar
        String cadenaSQL = "";
        if ("EL ID se establece en Automatico".equals(IdPerfil)) {
            //Es un registro nuevo
            cadenaSQL = cadenaSQL + " INSERT INTO dbo.perfil ";
            cadenaSQL = cadenaSQL + " (per_descripcion, per_estado)";
            cadenaSQL = cadenaSQL + " VALUES ('"+DescripcionPerfil+"' , '" ;
            cadenaSQL = cadenaSQL + EstadoPerfil + "');";
        } else {
            //Editaremos un registro ya existente
            cadenaSQL = cadenaSQL + " update dbo.perfil ";
            cadenaSQL = cadenaSQL + " set per_estado= '" + EstadoPerfil + "'";
            cadenaSQL = cadenaSQL + " , per_descripcion= '" + DescripcionPerfil + "'";
            cadenaSQL = cadenaSQL + " where per_id= " + IdPerfil + ";";
        }
        //conectar a la BD
        Mod_General modG = new Mod_General();
        ConexionDB conec = new ConexionDB();
        conec.conectarBD(modG.getMotorDB());//conectar    
        conec.ejecutarSQLCommit(cadenaSQL, true);
        cancelar(event);

    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerPerfiles.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

}
