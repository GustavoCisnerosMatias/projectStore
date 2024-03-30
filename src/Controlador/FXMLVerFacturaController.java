/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Facturas;
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
public class FXMLVerFacturaController implements Initializable {

    Mod_General General = new Mod_General();

    @FXML
    private VBox dataPane;
    @FXML
    private TableView<Producto> tv_datos;
    @FXML
    private TextField idFactura;
    @FXML
    private TextField fecha;
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
    private TextField Total;
    @FXML
    private TextField totalVA;
    @FXML
    private TextField subTotal;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private Button btn_regreso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.producto_id.setCellValueFactory(new PropertyValueFactory("Producto_id"));
        this.producto_codigo.setCellValueFactory(new PropertyValueFactory("Producto_codigo"));
        this.producto_nombre.setCellValueFactory(new PropertyValueFactory("Producto_nombre"));
        this.producto_precio.setCellValueFactory(new PropertyValueFactory("Producto_precio"));
        this.producto_stock.setCellValueFactory(new PropertyValueFactory("Producto_stock"));
        this.producto_estado.setCellValueFactory(new PropertyValueFactory("Producto_estado"));
        // TODO
        if ("".equals(General.getAuxIDFactura())) {
            //es nuevo el registro
            this.idFactura.setText("EL ID se establece en Automatico");
        } else {
            //vamos a editar un registro 
            this.cargarProducto();
            String IDFactura = General.getAuxIDFactura();
            this.idFactura.setText(IDFactura);
            Facturas objFacturas = cargarFacturas(IDFactura);
            if (objFacturas != null) {
                this.idFactura.setText(objFacturas.getFacturas_idFactura());
                this.fecha.setText(objFacturas.getFacturas_fecha());
                this.Total.setText(objFacturas.getFacturas_total());
                this.totalVA.setText(objFacturas.getFacturas_Iva());
                this.subTotal.setText(objFacturas.getFacturas_subTotal());
                this.nombre.setText(objFacturas.getFacturas_nombreCliente());
                this.apellido.setText(objFacturas.getFacturas_apellidosCliente());

            }
        }
        General.setAuxIDFactura("");
    }
    public void cargarProducto() {
        Producto objProducto = new Producto();
        ObservableList<Producto> listaProducto = objProducto.getProductoFactura(General.getAuxIDFactura());
        this.tv_datos.setItems(listaProducto);
    }
    public Facturas cargarFacturas(String IDFactura) {
        Facturas objFacturas = new Facturas();
        ObservableList<Facturas> listaFacturas = objFacturas.getFacturas();
        for (Facturas auxFacturas : listaFacturas) {
            if (auxFacturas.getFacturas_idFactura().equals((IDFactura))) {
                return auxFacturas;
            }
        }
        return null;
    }

    @FXML
    private void regresarTotalFacturas(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerTotalFacturas.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

}
