/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Clientes;
import Modelo.ConexionDB;
import Modelo.Mod_General;
import Modelo.Producto;
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
public class FXMLProductoController implements Initializable {

    Mod_General General = new Mod_General();
    @FXML
    private VBox dataPane;
    @FXML
    private TextField input_ID;
    @FXML
    private TextField input_codigo;
    @FXML
    private Button btn_Guardar;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private CheckBox chk_estado;
    @FXML
    private TextField input_nombre;
    @FXML
    private TextField input_precio;
    @FXML
    private TextField input_stock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (General.getAuxIdProducto() == 0) {
            //es nuevo el registro
            this.input_ID.setText("EL ID se establece en Automatico");
        } else {
            //vamos a editar un registro 
            int IDProducto = General.getAuxIdProducto();
            this.input_ID.setText(Integer.toString(IDProducto));
            Producto objProducto = cargarProducto(IDProducto);
            if (objProducto != null) {
                this.input_ID.setText(objProducto.getProducto_id());
                this.input_codigo.setText(objProducto.getProducto_codigo());
                this.input_nombre.setText(objProducto.getProducto_nombre());
                this.input_precio.setText(objProducto.getProducto_precio());
                this.input_stock.setText(objProducto.getProducto_stock());

                //checkBox
                if ("A".equals(objProducto.getProducto_estado())) {
                    this.chk_estado.setSelected(true);
                }
            }
        }
        General.setAuxIdProducto(0);
    }

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        String IdProducto = this.input_ID.getText();
        String Codigo = this.input_codigo.getText();
        String Nombre = this.input_nombre.getText();
        String Precio = this.input_precio.getText();
        String Stock = this.input_stock.getText();

        String Estado = null;
        //Checkbox
        if (this.chk_estado.isSelected()) {
            Estado = "A";
        } else {
            Estado = "D";
        }
        //Lineas sql a ejecutar
        String cadenaSQL = "";
        if ("EL ID se establece en Automatico".equals(IdProducto)) {
            //Es un registro nuevo
            cadenaSQL = cadenaSQL + " INSERT INTO dbo.Producto ";
            cadenaSQL = cadenaSQL + " ([prod_codigo], [prod_nombre],[prod_precio], [prod_stock], [prod_estado])";
            cadenaSQL = cadenaSQL + " VALUES ('" + Codigo + "' , '";
            cadenaSQL = cadenaSQL + Nombre + "' , '";
            cadenaSQL = cadenaSQL + Precio + "' , '";
            cadenaSQL = cadenaSQL + Stock + "' , '";
            cadenaSQL = cadenaSQL + Estado + "');";
        } else {
            //Editaremos un registro ya existente
            cadenaSQL = cadenaSQL + " update dbo.Producto ";
            cadenaSQL = cadenaSQL + " set prod_codigo= '" + Codigo + "' , ";
            cadenaSQL = cadenaSQL + " prod_nombre= '" + Nombre + "',";
            cadenaSQL = cadenaSQL + " prod_precio= '" + Precio + "',";
            cadenaSQL = cadenaSQL + " prod_stock= '" + Stock + "',";
            cadenaSQL = cadenaSQL + " prod_estado= '" + Estado + "'";
            cadenaSQL = cadenaSQL + " where [prod_id]= " + IdProducto + ";";
        }
        //conectar a la BD
        Mod_General modG = new Mod_General();
        ConexionDB conec = new ConexionDB();
        conec.conectarBD(modG.getMotorDB());//conectar    
        conec.ejecutarSQLCommit(cadenaSQL, true);
        cancelar(event);
    }

    public Producto cargarProducto(int IDProducto) {
        Producto objProducto = new Producto();
        ObservableList<Producto> listaProducto = objProducto.getProducto();
        for (Producto auxProducto : listaProducto) {
            if (auxProducto.getProducto_id().equals(Integer.toString(IDProducto))) {
                return auxProducto;
            }
        }
        return null;
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerProductos.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

}
