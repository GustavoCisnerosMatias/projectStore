/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Clientes;
import Modelo.ConexionDB;
import Modelo.Mod_General;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Cisneros
 */
public class FXMLClienteController implements Initializable {

    Mod_General General = new Mod_General();
    @FXML
    private VBox dataPane;
    @FXML
    private TextField input_ID;
    @FXML
    private TextField input_cedula;
    @FXML
    private Button btn_Guardar;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private CheckBox chk_estado;
    @FXML
    private TextField input_nombre;
    @FXML
    private TextField input_apellidos;
    @FXML
    private TextField input_direccion;
    @FXML
    private TextField input_telefono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (General.getAuxIdCliente() == 0) {
            //es nuevo el registro
            this.input_ID.setText("EL ID se establece en Automatico");
        } else {
            //vamos a editar un registro 
            int IDCliente = General.getAuxIdCliente();
            this.input_ID.setText(Integer.toString(IDCliente));
            Clientes objClientes = cargarClientes(IDCliente);
            if (objClientes != null) {
                this.input_ID.setText(objClientes.getCliente_ID());
                this.input_cedula.setText(objClientes.getCliente_cedula());
                this.input_nombre.setText(objClientes.getCliente_nombres());
                this.input_apellidos.setText(objClientes.getCliente_apellidos());
                this.input_direccion.setText(objClientes.getCliente_direccion());
                this.input_telefono.setText(objClientes.getCliente_telefono());

                //checkBox
                if ("A".equals(objClientes.getCliente_estado())) {
                    this.chk_estado.setSelected(true);
                }
            }
        }
        General.setAuxIdCliente(0);
    }

    public Clientes cargarClientes(int IDPERFIL) {
        Clientes objClientes = new Clientes();
        ObservableList<Clientes> listaClientes = objClientes.getClientes();
        for (Clientes auxClientes : listaClientes) {
            if (auxClientes.getCliente_ID().equals(Integer.toString(IDPERFIL))) {
                return auxClientes;
            }
        }
        return null;
    }

    @FXML
    private void guardar(ActionEvent event) throws IOException { //obtenemos los datos a modificar
        String IdCliente = this.input_ID.getText();
        String Cedula = this.input_cedula.getText();
        String Nombre = this.input_nombre.getText();
        String Apellidos = this.input_apellidos.getText();
        String Direccion = this.input_direccion.getText();
        String Telefono = this.input_telefono.getText();

        String Estado = null;
        //Checkbox
        if (this.chk_estado.isSelected()) {
            Estado = "A";
        } else {
            Estado = "D";
        }
        //Lineas sql a ejecutar
        String cadenaSQL = "";
        if ("EL ID se establece en Automatico".equals(IdCliente)) {
            //Es un registro nuevo
            cadenaSQL = cadenaSQL + " INSERT INTO dbo.Cliente ";
            cadenaSQL = cadenaSQL + " ([cli_cedula], [cli_nombres],[cli_apellidos], [cli_direccion], [cli_telefono] ,[cli_estado])";
            cadenaSQL = cadenaSQL + " VALUES ('" + Cedula + "' , '";
            cadenaSQL = cadenaSQL + Nombre + "' , '";
            cadenaSQL = cadenaSQL + Apellidos + "' , '";
            cadenaSQL = cadenaSQL + Direccion + "' , '";
            cadenaSQL = cadenaSQL + Telefono + "' , '";
            cadenaSQL = cadenaSQL + Estado + "');";
        } else {
            //Editaremos un registro ya existente
            cadenaSQL = cadenaSQL + " update dbo.Cliente ";
            cadenaSQL = cadenaSQL + " set cli_cedula= '" + Cedula + "' , ";
            cadenaSQL = cadenaSQL + " cli_nombres= '" + Nombre + "',";
            cadenaSQL = cadenaSQL + " cli_apellidos= '" + Apellidos + "',";
            cadenaSQL = cadenaSQL + " cli_direccion= '" + Direccion + "',";
            cadenaSQL = cadenaSQL + " cli_telefono= '" + Telefono + "',";
            cadenaSQL = cadenaSQL + " cli_estado= '" + Estado + "'";
            cadenaSQL = cadenaSQL + " where [cli_id]= " + IdCliente + ";";
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
        String pantalla = "/Vista/FXMLVerClientes.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

}
