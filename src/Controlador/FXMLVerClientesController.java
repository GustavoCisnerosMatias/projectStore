/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Clientes;
import Modelo.Mod_General;
import Modelo.Perfiles;
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
public class FXMLVerClientesController implements Initializable {
    Mod_General General = new Mod_General();
    @FXML
    private TableView<Clientes> tv_datos;
    @FXML
    private TableColumn<?, ?> id_clientes;
    @FXML
    private TableColumn<?, ?> cedula_cliente;
    @FXML
    private TableColumn<?, ?> nombre_Cliente;
    @FXML
    private TableColumn<?, ?> apellidos_Cliente;
    @FXML
    private TableColumn<?, ?> direccion_Cliente;
    @FXML
    private TableColumn<?, ?> telefono_cliente;
    @FXML
    private TableColumn<?, ?> estado_cliente;
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
        this.id_clientes.setCellValueFactory(new PropertyValueFactory("Cliente_ID"));
        this.cedula_cliente.setCellValueFactory(new PropertyValueFactory("Cliente_cedula"));
        this.nombre_Cliente.setCellValueFactory(new PropertyValueFactory("Cliente_nombres"));
        this.apellidos_Cliente.setCellValueFactory(new PropertyValueFactory("Cliente_apellidos"));
        this.direccion_Cliente.setCellValueFactory(new PropertyValueFactory("Cliente_direccion"));
        this.telefono_cliente.setCellValueFactory(new PropertyValueFactory("Cliente_telefono"));
        this.estado_cliente.setCellValueFactory(new PropertyValueFactory("Cliente_estado"));
        this.cargarClientes();
    }

    public void cargarClientes() {
        Clientes objClientes = new Clientes();
        ObservableList<Clientes> listaClientes = objClientes.getClientes();
        this.tv_datos.setItems(listaClientes);
    }

    @FXML
    private void nuevoRegistro(ActionEvent event) throws IOException {
        General.setAuxIdCliente(0);
        String pantalla = "/Vista/FXMLCliente.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    @FXML
    private void ir_editarClientes(MouseEvent event) throws IOException {
        General.setAuxIdCliente(Integer.parseInt(tv_datos.getSelectionModel().getSelectedItem().getCliente_ID()));
        String pantalla = "/Vista/FXMLCliente.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

}
