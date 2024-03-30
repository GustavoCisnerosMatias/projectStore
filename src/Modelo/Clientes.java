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
public class Clientes {

    String Cliente_ID;
    String Cliente_cedula;
    String Cliente_nombres;
    String Cliente_apellidos;
    String Cliente_direccion;
    String Cliente_telefono;
    String Cliente_estado;

    public Clientes() {
    }

    public Clientes(String Cliente_ID, String Cliente_cedula, String Cliente_nombres, String Cliente_apellidos, String Cliente_direccion, String Cliente_telefono, String Cliente_estado) {
        this.Cliente_ID = Cliente_ID;
        this.Cliente_cedula = Cliente_cedula;
        this.Cliente_nombres = Cliente_nombres;
        this.Cliente_apellidos = Cliente_apellidos;
        this.Cliente_direccion = Cliente_direccion;
        this.Cliente_telefono = Cliente_telefono;
        this.Cliente_estado = Cliente_estado;
    }

    public String getCliente_ID() {
        return Cliente_ID;
    }

    public void setCliente_ID(String Cliente_ID) {
        this.Cliente_ID = Cliente_ID;
    }

    public String getCliente_cedula() {
        return Cliente_cedula;
    }

    public void setCliente_cedula(String Cliente_cedula) {
        this.Cliente_cedula = Cliente_cedula;
    }

    public String getCliente_nombres() {
        return Cliente_nombres;
    }

    public void setCliente_nombres(String Cliente_nombres) {
        this.Cliente_nombres = Cliente_nombres;
    }

    public String getCliente_apellidos() {
        return Cliente_apellidos;
    }

    public void setCliente_apellidos(String Cliente_apellidos) {
        this.Cliente_apellidos = Cliente_apellidos;
    }

    public String getCliente_direccion() {
        return Cliente_direccion;
    }

    public void setCliente_direccion(String Cliente_direccion) {
        this.Cliente_direccion = Cliente_direccion;
    }

    public String getCliente_telefono() {
        return Cliente_telefono;
    }

    public void setCliente_telefono(String Cliente_telefono) {
        this.Cliente_telefono = Cliente_telefono;
    }

    public String getCliente_estado() {
        return Cliente_estado;
    }

    public void setCliente_estado(String Cliente_estado) {
        this.Cliente_estado = Cliente_estado;
    }

    public ObservableList<Clientes> getClientes() {
        ObservableList<Clientes> AuxClientes = FXCollections.observableArrayList();
        try {
            String cadenaSQL = "";
            cadenaSQL = cadenaSQL + " select [cli_id],[cli_cedula],[cli_nombres],[cli_apellidos],[cli_direccion],[cli_telefono],[cli_estado] ";
            cadenaSQL = cadenaSQL + " from Cliente ";
            //conectar a la BD
            Mod_General modG = new Mod_General();
            ConexionDB conec = new ConexionDB();
            conec.conectarBD(modG.getMotorDB());//conectar
            //consular 
            conec.ejecutarConsultaSQL(cadenaSQL);
            //presenta los datos
            ResultSet rs = conec.getResulSet();
            while (rs.next()) {

                String aux_Cliente_ID = rs.getString("cli_id");//lo que esta entre comillas son las variables del SQL
                String aux_Cliente_cedula = rs.getString("cli_cedula");//lo que esta entre comillas son las variables del SQL
                String aux_Cliente_nombres = rs.getString("cli_nombres");//lo que esta entre comillas son las variables del SQL
                String aux_Cliente_apellidos = rs.getString("cli_apellidos");//lo que esta entre comillas son las variables del SQL
                String aux_Cliente_direccion = rs.getString("cli_direccion");//lo que esta entre comillas son las variables del SQL
                String aux_Cliente_telefono = rs.getString("cli_telefono");//lo que esta entre comillas son las variables del SQL
                String aux_Cliente_estado = rs.getString("cli_estado");//lo que esta entre comillas son las variables del SQL

                Clientes objClientes = new Clientes(aux_Cliente_ID, aux_Cliente_cedula,aux_Cliente_nombres, aux_Cliente_apellidos,aux_Cliente_direccion , aux_Cliente_telefono,aux_Cliente_estado );
                AuxClientes.add(objClientes);
            }
            //desconectar
            conec.cerrarResulSet();
            conec.cerrarBD();
            return AuxClientes;
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.WARNING, null, ex);
            return null;
        }

    }
}
