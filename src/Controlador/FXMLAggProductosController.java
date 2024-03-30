package Controlador;

import Modelo.ConexionDB;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cisneros
 */
public class FXMLAggProductosController implements Initializable {

    Mod_General General = new Mod_General();

    @FXML
    private VBox dataPane;
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
    private TextField txt_cantidadProduct;

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
        this.cargarProducto();
        //Crear CabFactura en la Cual se trabajara
        crearCabFactura();
        General.setAuxIdCliente(0);

    }

    public void crearCabFactura() {
        int auxNumFac = General.getAuxnumFac() + 1;
        General.setAuxnumFac(auxNumFac);
        String CodigoFactura = Integer.toString(General.getAuxnumFac());
        LocalDate fechaActual = LocalDate.now();
        String Estado = "A";
        String IDCliente = Integer.toString(General.getAuxIdCliente());
        String cadenaSQL = "";
        cadenaSQL = cadenaSQL + " INSERT INTO dbo.Cab_Factura ";
        cadenaSQL = cadenaSQL + " ([cli_id],[cab_numfac],[cab_fecha],[usr_id],[cab_subTotal],[cab_iva],[cab_total],[cab_estado])";
        cadenaSQL = cadenaSQL + " VALUES ('" + IDCliente + "' , '";
        cadenaSQL = cadenaSQL + CodigoFactura + "' , '";
        cadenaSQL = cadenaSQL + fechaActual + "' , '";
        cadenaSQL = cadenaSQL + 2 + "' , '";
        cadenaSQL = cadenaSQL + 0 + "' , '";
        cadenaSQL = cadenaSQL + 0 + "' , '";
        cadenaSQL = cadenaSQL + 0 + "' , '";
        cadenaSQL = cadenaSQL + Estado + " ');";
        Mod_General modG = new Mod_General();
        ConexionDB conec = new ConexionDB();
        conec.conectarBD(modG.getMotorDB());//conectar    
        conec.ejecutarSQLCommit(cadenaSQL, true);
        General.setAuxNumFact(Integer.parseInt(CodigoFactura));
    }

    public void cargarProducto() {
        Producto objProducto = new Producto();
        ObservableList<Producto> listaProducto = objProducto.getProducto();
        this.tv_datos.setItems(listaProducto);
    }

    @FXML
    private void AggProductoFactura(MouseEvent event) throws IOException {
        int numFac = General.getAuxnumFac();
        Facturas objFacturas = cargarFacturas(numFac);
        if (objFacturas != null) {
                String aux_cabID = objFacturas.getFacturas_idFactura();
                String aux_proID = tv_datos.getSelectionModel().getSelectedItem().getProducto_id();
                int aux_cantidad = Integer.parseInt(this.txt_cantidadProduct.getText());
                double aux_precio = Double.parseDouble(tv_datos.getSelectionModel().getSelectedItem().getProducto_precio());
                double aux_subtotal = aux_cantidad * aux_precio;
                double aux_iva = aux_subtotal * 0.14;
                double aux_total = aux_subtotal + aux_iva;
                String aux_estado = "A";

                //Se creara DETFAC
                String cadenaSQL = "";
                cadenaSQL = cadenaSQL + " INSERT INTO dbo.det_factura ([cab_id], [prod_id], [det_cantidad], [det_precio], [det_subtotal], [det_iva], [det_total], [det_estado])";
                cadenaSQL = cadenaSQL + " VALUES ('" + aux_cabID + "' , '";
                cadenaSQL = cadenaSQL + aux_proID + "' , '";
                cadenaSQL = cadenaSQL + aux_cantidad + "' , '";
                cadenaSQL = cadenaSQL + aux_precio + "' , '";
                cadenaSQL = cadenaSQL + aux_subtotal + "' , '";
                cadenaSQL = cadenaSQL + aux_iva + "' , '";
                cadenaSQL = cadenaSQL + aux_total + "' , '";
                cadenaSQL = cadenaSQL + aux_estado + " ');";
                Mod_General modG = new Mod_General();
                ConexionDB conec = new ConexionDB();
                conec.conectarBD(modG.getMotorDB());//conectar    
                conec.ejecutarSQLCommit(cadenaSQL, true);
                //Se resetea cadena
                String cadenaSQL1 = "";
                //cadena para actualizar CabFact

                cadenaSQL1 = cadenaSQL1 + " UPDATE dbo.Cab_Factura ";
                cadenaSQL1 = cadenaSQL1 + " SET cab_subTotal = cab_subTotal +  " + aux_subtotal + " , ";
                cadenaSQL1 = cadenaSQL1 + " cab_iva = cab_iva +  " + aux_iva + " , ";
                cadenaSQL1 = cadenaSQL1 + " cab_total = cab_total +  " + aux_total;
                cadenaSQL1 = cadenaSQL1 + " WHERE cab_id= '" + aux_cabID+"'" ;
                conec.conectarBD(modG.getMotorDB());//conectar    
                conec.ejecutarSQLCommit(cadenaSQL1, true);

        }

    }

    @FXML
    private void FinalizarFactura(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLVerTotalFacturas.fxml";
        General.setDataPane(General.fadeAnimate(pantalla), dataPane);
    }

    public Facturas cargarFacturas(int numFac) {
        Facturas objFacturas = new Facturas();
        ObservableList<Facturas> listaFacturas = objFacturas.getFacturas();
        for (Facturas auxFacturas : listaFacturas) {
            if (auxFacturas.getFacturas_codigoFactura().equals(Integer.toString(numFac))) {
                return auxFacturas;
            }
        }
        return null;
    }
}
