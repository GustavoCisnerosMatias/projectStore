/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Mod_General;
import Modelo.Perfiles;
import Modelo.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Cisneros
 */
public class FXMLVerProductosController implements Initializable {
    Mod_General General = new Mod_General();
    @FXML
    private TableView<Producto> tv_datos;
    @FXML
    private TableColumn<?, ?> producto_id;
    @FXML
    private TableColumn<?, ?> producto_codigo;
    @FXML
    private TableColumn<?, ?> producto_nombre;
    @FXML
    private TableColumn<?, ?> producto_precio;
    @FXML
    private TableColumn<?, ?> producto_stock;
    @FXML
    private TableColumn<?, ?> producto_estado;
    @FXML
    private Button btn_nuevo;
    @FXML
    private VBox dataPane;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.producto_id.setCellValueFactory(new PropertyValueFactory("Producto_id"));
        this.producto_codigo.setCellValueFactory(new PropertyValueFactory("Producto_codigo"));
        this.producto_nombre.setCellValueFactory(new PropertyValueFactory("Producto_nombre"));
        this.producto_precio.setCellValueFactory(new PropertyValueFactory("Producto_precio"));
        this.producto_stock.setCellValueFactory(new PropertyValueFactory("Producto_stock"));
        this.producto_estado.setCellValueFactory(new PropertyValueFactory("Producto_estado"));
        this.cargarProducto();
    }

    public void cargarProducto() {
        Producto objProducto = new Producto();
        ObservableList<Producto> listaProducto = objProducto.getProducto();
        this.tv_datos.setItems(listaProducto);
    }

    @FXML
    private void nuevoRegistro(ActionEvent event) throws IOException {
        General.setAuxIdProducto(0);
        String pantalla = "/Vista/FXMLProducto.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    @FXML
    private void ir_registrosProducto(MouseEvent event) throws IOException {
        General.setAuxIdProducto(Integer.parseInt(tv_datos.getSelectionModel().getSelectedItem().getProducto_id()));
        String pantalla = "/Vista/FXMLProducto.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

}
