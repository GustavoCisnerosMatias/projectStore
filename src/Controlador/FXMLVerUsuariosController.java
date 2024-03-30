/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Mod_General;
import Modelo.Perfiles;
import Modelo.Usuario;
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
public class FXMLVerUsuariosController implements Initializable {

    Mod_General General = new Mod_General();
    @FXML
    private TableView<Usuario> tv_datos;
    @FXML
    private TableColumn<?, ?> colum_IDUsr;
    @FXML
    private TableColumn<?, ?> colum_PER_ID;
    @FXML
    private TableColumn<?, ?> colum_nombre;
    @FXML
    private TableColumn<?, ?> colum_Usuario;
    @FXML
    private TableColumn<?, ?> colum_Clave;
    @FXML
    private TableColumn<?, ?> colum_Estado;
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
        this.colum_IDUsr.setCellValueFactory(new PropertyValueFactory("usr_id"));
        this.colum_PER_ID.setCellValueFactory(new PropertyValueFactory("per_id"));
        this.colum_nombre.setCellValueFactory(new PropertyValueFactory("usr_nombres"));
        this.colum_Usuario.setCellValueFactory(new PropertyValueFactory("usr_usuario"));
        this.colum_Clave.setCellValueFactory(new PropertyValueFactory("usr_clave"));
        this.colum_Estado.setCellValueFactory(new PropertyValueFactory("usr_estado"));

        this.cargarUsuario();

    }

    public void cargarUsuario() {
        Usuario objUsuario = new Usuario();
        ObservableList<Usuario> listaUsuario = objUsuario.getUsuario();
        this.tv_datos.setItems(listaUsuario);
    }

    @FXML
    private void nuevoRegistro(ActionEvent event) throws IOException {
        General.setAuxIdUsuario(0);
        String pantalla = "/Vista/FXMLUsuario.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    @FXML
    private void ir_editarUsuario(MouseEvent event) throws IOException {
        General.setAuxIdUsuario(Integer.parseInt(tv_datos.getSelectionModel().getSelectedItem().getUsr_id()));
        String pantalla = "/Vista/FXMLUsuario.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

}
