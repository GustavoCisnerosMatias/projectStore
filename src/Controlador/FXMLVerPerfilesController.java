/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

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
public class FXMLVerPerfilesController implements Initializable {

    Mod_General General = new Mod_General();
    @FXML
    private TableView<Perfiles> tv_datos;
    @FXML
    private TableColumn<?, ?> colm_IDPerfil;
    @FXML
    private TableColumn<?, ?> colum_Descripcion;
    @FXML
    private TableColumn<?, ?> colum_Estadp;
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
        this.colm_IDPerfil.setCellValueFactory(new PropertyValueFactory("ID_Perfil"));
        this.colum_Descripcion.setCellValueFactory(new PropertyValueFactory("Descripcion_Perfil"));
        this.colum_Estadp.setCellValueFactory(new PropertyValueFactory("Estado_perfil"));

        this.cargarPerfil();

    }

    public void cargarPerfil() {
        Perfiles objPerfiles = new Perfiles();
        ObservableList<Perfiles> listaPerfiles = objPerfiles.getPerfiles();
        this.tv_datos.setItems(listaPerfiles);
    }

    @FXML
    private void RegistroPerfil(ActionEvent event) throws IOException {
        General.setAuxIdPerfil(0);
        String pantalla = "/Vista/FXMLPerfil.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);

    }

    @FXML
    private void ir_editarPerfil(MouseEvent event) throws IOException {
        General.setAuxIdPerfil(Integer.parseInt(tv_datos.getSelectionModel().getSelectedItem().getID_Perfil()));
        String pantalla = "/Vista/FXMLPerfil.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
        
    }

}
