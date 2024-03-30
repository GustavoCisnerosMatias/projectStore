/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Cisneros
 */
public class Producto {

    String Producto_id;
    String Producto_codigo;
    String Producto_nombre;
    String Producto_precio;
    String Producto_stock;
    String Producto_estado;

    public String getProducto_id() {
        return Producto_id;
    }

    public void setProducto_id(String Producto_id) {
        this.Producto_id = Producto_id;
    }

    public String getProducto_codigo() {
        return Producto_codigo;
    }

    public void setProducto_codigo(String Producto_codigo) {
        this.Producto_codigo = Producto_codigo;
    }

    public String getProducto_nombre() {
        return Producto_nombre;
    }

    public void setProducto_nombre(String Producto_nombre) {
        this.Producto_nombre = Producto_nombre;
    }

    public String getProducto_precio() {
        return Producto_precio;
    }

    public void setProducto_precio(String Producto_precio) {
        this.Producto_precio = Producto_precio;
    }

    public String getProducto_stock() {
        return Producto_stock;
    }

    public void setProducto_stock(String Producto_stock) {
        this.Producto_stock = Producto_stock;
    }

    public String getProducto_estado() {
        return Producto_estado;
    }

    public void setProducto_estado(String Producto_estado) {
        this.Producto_estado = Producto_estado;
    }

    public Producto() {
    }

    public Producto(String Producto_id, String Producto_codigo, String Producto_nombre, String Producto_precio, String Producto_stock, String Producto_estado) {
        this.Producto_id = Producto_id;
        this.Producto_codigo = Producto_codigo;
        this.Producto_nombre = Producto_nombre;
        this.Producto_precio = Producto_precio;
        this.Producto_stock = Producto_stock;
        this.Producto_estado = Producto_estado;
    }

    public ObservableList<Producto> getProducto() {
        ObservableList<Producto> AuxProducto = FXCollections.observableArrayList();
        try {
            String cadenaSQL = "";
            cadenaSQL = cadenaSQL + " select [prod_id],[prod_codigo],[prod_nombre],[prod_precio],[prod_stock],[prod_estado] ";
            cadenaSQL = cadenaSQL + " from Producto ";
            //conectar a la BD
            Mod_General modG = new Mod_General();
            ConexionDB conec = new ConexionDB();
            conec.conectarBD(modG.getMotorDB());//conectar
            //consular 
            conec.ejecutarConsultaSQL(cadenaSQL);
            //presenta los datos
            ResultSet rs = conec.getResulSet();
            while (rs.next()) {
                String aux_prod_id = rs.getString("prod_id");
                String aux_prod_codigo = rs.getString("prod_codigo");
                String aux_prod_nombre = rs.getString("prod_nombre");
                String aux_prod_precio = rs.getString("prod_precio");
                String aux_prod_stock = rs.getString("prod_stock");
                String aux_prod_estado = rs.getString("prod_estado");

                Producto objProducto = new Producto(aux_prod_id, aux_prod_codigo, aux_prod_nombre, aux_prod_precio, aux_prod_stock, aux_prod_estado);
                AuxProducto.add(objProducto);
            }
            //desconectar
            conec.cerrarResulSet();
            conec.cerrarBD();
            return AuxProducto;
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.WARNING, null, ex);
            return null;
        }

    }

    public ObservableList<Producto> getProductoFactura(String cabID) {
        ObservableList<Producto> AuxProducto = FXCollections.observableArrayList();
        try {
            String cadenaSQL = "";
            cadenaSQL = cadenaSQL + " select pro.prod_id, pro.prod_codigo, pro.prod_nombre, pro.prod_precio, detFac.det_cantidad, detFac.det_subtotal ";
            cadenaSQL = cadenaSQL + " from  Cab_Factura cabFac, det_factura detFac , Producto pro ";
            cadenaSQL = cadenaSQL + " where cabFac.cab_id=detFac.cab_id and detFac.prod_id = pro.prod_id and cabFac.cab_id= " + cabID;

            //conectar a la BD
            Mod_General modG = new Mod_General();
            ConexionDB conec = new ConexionDB();
            conec.conectarBD(modG.getMotorDB());//conectar
            //consular 
            conec.ejecutarConsultaSQL(cadenaSQL);
            //presenta los datos
            ResultSet rs = conec.getResulSet();
            while (rs.next()) {
                String aux_prod_id = rs.getString("prod_id");
                String aux_prod_codigo = rs.getString("prod_codigo");
                String aux_prod_nombre = rs.getString("prod_nombre");
                String aux_prod_precio = rs.getString("prod_precio");
                String aux_prod_stock = rs.getString("det_cantidad");
                String aux_prod_estado = rs.getString("det_subtotal");

                Producto objProducto = new Producto(aux_prod_id, aux_prod_codigo, aux_prod_nombre, aux_prod_precio, aux_prod_stock, aux_prod_estado);
                AuxProducto.add(objProducto);
            }
            //desconectar
            conec.cerrarResulSet();
            conec.cerrarBD();
            return AuxProducto;
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.WARNING, null, ex);
            return null;
        }
    }

}
