/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Facturas;
import Modelo.Mod_General;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Cisneros
 */
public class FXMLVerTotalFacturasController implements Initializable {
    Mod_General General = new Mod_General();
    @FXML
    private VBox dataPane;
    @FXML
    private TableView<Facturas> tv_datos;
    @FXML
    private TableColumn<?, ?> idFactura;
    @FXML
    private TableColumn<?, ?> codigoFactura;
    @FXML
    private TableColumn<?, ?> nombreCliente;
    @FXML
    private TableColumn<?, ?> fecha;
    @FXML
    private TableColumn<?, ?> total;
    @FXML
    private TableColumn<?, ?> estado;
    @FXML
    private Button btn_nuevo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.idFactura.setCellValueFactory(new PropertyValueFactory("Facturas_idFactura"));
        this.codigoFactura.setCellValueFactory(new PropertyValueFactory("Facturas_codigoFactura"));
        this.nombreCliente.setCellValueFactory(new PropertyValueFactory("Facturas_nombreCliente"));
        this.fecha.setCellValueFactory(new PropertyValueFactory("Facturas_fecha"));
        this.total.setCellValueFactory(new PropertyValueFactory("Facturas_total"));
        this.estado.setCellValueFactory(new PropertyValueFactory("Facturas_estado"));

        this.cargarFacturas();
    }

    @FXML
    private void ir_VerFactura(MouseEvent event) throws IOException {
        General.setAuxIDFactura((tv_datos.getSelectionModel().getSelectedItem().getFacturas_idFactura()));
        String pantalla = "/Vista/FXMLVerFactura.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    @FXML
    private void nuevoRegistro(ActionEvent event) throws IOException {
        General.setAuxIDFactura("");
        String pantalla = "/Vista/FXMLClienteFactura.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    public void cargarFacturas() {
        Facturas objFacturass = new Facturas();
        ObservableList<Facturas> listaFacturas = objFacturass.getFacturas();
        this.tv_datos.setItems(listaFacturas);
    }
}
