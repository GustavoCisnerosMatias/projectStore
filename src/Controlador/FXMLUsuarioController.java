/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.ConexionDB;
import Modelo.Mod_General;
import Modelo.Perfiles;
import Modelo.PerfilesConverte;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Cisneros
 */
public class FXMLUsuarioController implements Initializable {

    Mod_General General = new Mod_General();
    @FXML
    private VBox dataPane;
    @FXML
    private TextField input_ID;
    @FXML
    private Button btn_Guardar;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private TextField input_nombre;
    @FXML
    private TextField input_usuario;
    @FXML
    private TextField input_clave;
    @FXML
    private CheckBox chk_estado;
    @FXML
    private ComboBox<Perfiles> comboBox_perfiles;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Perfiles auxPerfiles = new Perfiles();
        ObservableList<Perfiles> listaPerfiles = auxPerfiles.getPerfiles();
        comboBox_perfiles.getItems().addAll(listaPerfiles);
        comboBox_perfiles.setConverter(new PerfilesConverte());

        // TODO
        if (General.getAuxIdUsuario() == 0) {
            //es nuevo el registro
            this.input_ID.setText("EL ID se establece en Automatico");
        } else {
            //vamos a editar un registro 
            int IDUSUARIO = General.getAuxIdUsuario();
            this.input_ID.setText(Integer.toString(IDUSUARIO));
            Usuario objUsuario = cargarUsuario(IDUSUARIO);
            if (objUsuario != null) {
                this.input_ID.setText(objUsuario.getUsr_id());
                this.input_nombre.setText(objUsuario.getUsr_nombres());
                this.input_usuario.setText(objUsuario.getUsr_usuario());
                this.input_clave.setText(objUsuario.getUsr_clave());
                //Configuracion predeterminada del checkbox
                if ("A".equals(objUsuario.getUsr_estado())) {
                    this.chk_estado.setSelected(true);
                } else {
                    this.chk_estado.setSelected(false);
                }
                //Configuracion del combox
                for (Perfiles objPerfiles : listaPerfiles) {
                    if (objPerfiles.getID_Perfil().equals(objUsuario.getPer_id())) {
                        this.comboBox_perfiles.setValue(objPerfiles);
                    }
                }
            }
        }
        General.setAuxIdUsuario(0);
    }

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        //obtenemos los datos a modificar
        String auxID = this.input_ID.getText();
        String auxNombre = this.input_nombre.getText();
        String auxUsuario = this.input_usuario.getText();
        String auxClave = this.input_clave.getText();
        String auxIDPerfil = this.comboBox_perfiles.getValue().getID_Perfil();
        String Estado = null;

        //Checkbox
        if (this.chk_estado.isSelected()) {
            Estado = "A";
        } else {
            Estado = "D";
        }
        //Lineas sql a ejecutar
        String cadenaSQL = "";
        if ("EL ID se establece en Automatico".equals(auxID)) {
            //Es un registro nuevo
            cadenaSQL = cadenaSQL + " INSERT INTO dbo.Usuario ";
            cadenaSQL = cadenaSQL + " (usr_nombres, usr_usuario, usr_clave, per_id, usr_estado)";
            cadenaSQL = cadenaSQL + " VALUES ('" + auxNombre + "' , '";
            cadenaSQL = cadenaSQL + auxUsuario + "' , '";
            cadenaSQL = cadenaSQL + auxClave + "' , '";
            cadenaSQL = cadenaSQL + auxIDPerfil + "' , '";
            cadenaSQL = cadenaSQL + Estado + "');";
        } else {
            //Editaremos un registro ya existente
            cadenaSQL = cadenaSQL + " update dbo.Usuario ";
            cadenaSQL = cadenaSQL + " set usr_nombres= '" + auxNombre + "' , ";
            cadenaSQL = cadenaSQL + " usr_usuario= '" + auxUsuario + "',";
            cadenaSQL = cadenaSQL + " usr_clave= '" + auxClave + "',";
            cadenaSQL = cadenaSQL + " per_id= '" + auxIDPerfil + "',";
            cadenaSQL = cadenaSQL + " usr_estado= '" + Estado + "'";
            cadenaSQL = cadenaSQL + " where usr_id= " + auxID + ";";
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
        String pantalla = "/Vista/FXMLVerUsuarios.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    public Usuario cargarUsuario(int IDUSUARIO) {
        Usuario objUsuario = new Usuario();
        ObservableList<Usuario> listaUsuario = objUsuario.getUsuario();
        for (Usuario auxUsuario : listaUsuario) {
            if (auxUsuario.getUsr_id().equals(Integer.toString(IDUSUARIO))) {
                return auxUsuario;
            }
        }
        return null;
    }
}
