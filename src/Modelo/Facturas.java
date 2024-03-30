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
public class Facturas {

    Mod_General General = new Mod_General();

    private String Facturas_idFactura;
    private String Facturas_codigoFactura;
    private String Facturas_nombreCliente;
    private String Facturas_fecha;
    private String Facturas_total;
    private String Facturas_estado;

    private String Facturas_apellidosCliente;
    private String Facturas_subTotal;
    private String Facturas_Iva;

    public Facturas() {
    }

    public String getFacturas_idFactura() {
        return Facturas_idFactura;
    }

    public void setFacturas_idFactura(String Facturas_idFactura) {
        this.Facturas_idFactura = Facturas_idFactura;
    }

    public String getFacturas_codigoFactura() {
        return Facturas_codigoFactura;
    }

    public void setFacturas_codigoFactura(String Facturas_codigoFactura) {
        this.Facturas_codigoFactura = Facturas_codigoFactura;
    }

    public String getFacturas_nombreCliente() {
        return Facturas_nombreCliente;
    }

    public void setFacturas_nombreCliente(String Facturas_nombreCliente) {
        this.Facturas_nombreCliente = Facturas_nombreCliente;
    }

    public String getFacturas_fecha() {
        return Facturas_fecha;
    }

    public void setFacturas_fecha(String Facturas_fecha) {
        this.Facturas_fecha = Facturas_fecha;
    }

    public String getFacturas_total() {
        return Facturas_total;
    }

    public void setFacturas_total(String Facturas_total) {
        this.Facturas_total = Facturas_total;
    }

    public String getFacturas_estado() {
        return Facturas_estado;
    }

    public void setFacturas_estado(String Facturas_estado) {
        this.Facturas_estado = Facturas_estado;
    }

    public String getFacturas_apellidosCliente() {
        return Facturas_apellidosCliente;
    }

    public void setFacturas_apellidosCliente(String Facturas_apellidosCliente) {
        this.Facturas_apellidosCliente = Facturas_apellidosCliente;
    }

    public String getFacturas_subTotal() {
        return Facturas_subTotal;
    }

    public void setFacturas_subTotal(String Facturas_subTotal) {
        this.Facturas_subTotal = Facturas_subTotal;
    }

    public String getFacturas_Iva() {
        return Facturas_Iva;
    }

    public void setFacturas_Iva(String Facturas_Iva) {
        this.Facturas_Iva = Facturas_Iva;
    }

    public Facturas(String Facturas_idFactura, String Facturas_codigoFactura, String Facturas_nombreCliente, String Facturas_fecha, String Facturas_total, String Facturas_estado, String Facturas_apellidosCliente, String Facturas_subTotal, String Facturas_Iva) {
        this.Facturas_idFactura = Facturas_idFactura;
        this.Facturas_codigoFactura = Facturas_codigoFactura;
        this.Facturas_nombreCliente = Facturas_nombreCliente;
        this.Facturas_fecha = Facturas_fecha;
        this.Facturas_total = Facturas_total;
        this.Facturas_estado = Facturas_estado;
        this.Facturas_apellidosCliente = Facturas_apellidosCliente;
        this.Facturas_subTotal = Facturas_subTotal;
        this.Facturas_Iva = Facturas_Iva;
    }

    public ObservableList<Facturas> getFacturas() {
        ObservableList<Facturas> AuxFacturas = FXCollections.observableArrayList();
        try {
            String cadenaSQL = "";
            cadenaSQL = cadenaSQL + " select CABFAC.cab_id , CABFAC.cab_numfac, CLI.cli_nombres, CABFAC.cab_fecha, CABFAC.cab_total, CABFAC.cab_estado, CLI.cli_apellidos, CABFAC.cab_subTotal, CABFAC.cab_iva ";
            cadenaSQL = cadenaSQL + " from Cab_Factura CABFAC, Cliente CLI ";
            cadenaSQL = cadenaSQL + " WHERE CABFAC.cli_id=CLI.cli_id ";

            //conectar a la BD
            Mod_General modG = new Mod_General();
            ConexionDB conec = new ConexionDB();
            conec.conectarBD(modG.getMotorDB());//conectar
            //consular 
            conec.ejecutarConsultaSQL(cadenaSQL);
            //presenta los datos
            ResultSet rs = conec.getResulSet();
            while (rs.next()) {

                String aux_Facturas_idFactura = rs.getString("cab_id");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_codigoFactura = rs.getString("cab_numfac");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_nombreCliente = rs.getString("cli_nombres");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_fecha = rs.getString("cab_fecha");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_total = rs.getString("cab_total");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_estado = rs.getString("cab_estado");//lo que esta entre comillas son las variables del SQL

                String aux_Facturas_apellidosCliente = rs.getString("cli_apellidos");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_subTotal = rs.getString("cab_subTotal");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_Iva = rs.getString("cab_iva");//lo que esta entre comillas son las variables del SQL

                Facturas objFacturas = new Facturas(aux_Facturas_idFactura, aux_Facturas_codigoFactura, aux_Facturas_nombreCliente, aux_Facturas_fecha, aux_Facturas_total, aux_Facturas_estado, aux_Facturas_apellidosCliente, aux_Facturas_subTotal, aux_Facturas_Iva);
                AuxFacturas.add(objFacturas);
                General.setAuxnumFac(Integer.parseInt(aux_Facturas_codigoFactura));
            }
            //desconectar
            conec.cerrarResulSet();
            conec.cerrarBD();
            return AuxFacturas;
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.WARNING, null, ex);
            return null;
        }

    }

    public ObservableList<Facturas> getProductoFactura(String cabNumFac) {
        ObservableList<Facturas> AuxFacturas = FXCollections.observableArrayList();
        try {
            String cadenaSQL = "";
            cadenaSQL = cadenaSQL + " select CABFAC.cab_id ";
            cadenaSQL = cadenaSQL + " from Cab_Factura CABFAC ";
            cadenaSQL = cadenaSQL + " WHERE CABFAC.cab_numfac= " + cabNumFac;

            //conectar a la BD
            Mod_General modG = new Mod_General();
            ConexionDB conec = new ConexionDB();
            conec.conectarBD(modG.getMotorDB());//conectar
            //consular 
            conec.ejecutarConsultaSQL(cadenaSQL);
            //presenta los datos
            ResultSet rs = conec.getResulSet();
            while (rs.next()) {
                String aux_Facturas_idFactura = rs.getString("cab_id");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_codigoFactura = rs.getString("cab_numfac");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_nombreCliente = rs.getString("cli_nombres");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_fecha = rs.getString("cab_fecha");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_total = rs.getString("cab_total");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_estado = rs.getString("cab_estado");//lo que esta entre comillas son las variables del SQL

                String aux_Facturas_apellidosCliente = rs.getString("cli_apellidos");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_subTotal = rs.getString("cab_subTotal");//lo que esta entre comillas son las variables del SQL
                String aux_Facturas_Iva = rs.getString("cab_iva");//lo que esta entre comillas son las variables del SQL

                Facturas objFacturas = new Facturas(aux_Facturas_idFactura, aux_Facturas_codigoFactura, aux_Facturas_nombreCliente, aux_Facturas_fecha, aux_Facturas_total, aux_Facturas_estado, aux_Facturas_apellidosCliente, aux_Facturas_subTotal, aux_Facturas_Iva);
                AuxFacturas.add(objFacturas);
            }
            //desconectar
            conec.cerrarResulSet();
            conec.cerrarBD();
            return AuxFacturas;
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.WARNING, null, ex);
            return null;
        }
    }
}
