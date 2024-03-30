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
public class Usuario {

    String usr_id;
    String per_id;
    String usr_nombres;
    String usr_usuario;
    String usr_clave;
    String usr_estado;

    public Usuario() {
    }

    public Usuario(String usr_id, String per_id, String usr_nombres, String usr_usuario, String usr_clave, String usr_estado) {
        this.usr_id = usr_id;
        this.per_id = per_id;
        this.usr_nombres = usr_nombres;
        this.usr_usuario = usr_usuario;
        this.usr_clave = usr_clave;
        this.usr_estado = usr_estado;
    }

    public String getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(String usr_id) {
        this.usr_id = usr_id;
    }

    public String getPer_id() {
        return per_id;
    }

    public void setPer_id(String per_id) {
        this.per_id = per_id;
    }

    public String getUsr_nombres() {
        return usr_nombres;
    }

    public void setUsr_nombres(String usr_nombres) {
        this.usr_nombres = usr_nombres;
    }

    public String getUsr_usuario() {
        return usr_usuario;
    }

    public void setUsr_usuario(String usr_usuario) {
        this.usr_usuario = usr_usuario;
    }

    public String getUsr_clave() {
        return usr_clave;
    }

    public void setUsr_clave(String usr_clave) {
        this.usr_clave = usr_clave;
    }

    public String getUsr_estado() {
        return usr_estado;
    }

    public void setUsr_estado(String usr_estado) {
        this.usr_estado = usr_estado;
    }

    public ObservableList<Usuario> getUsuario() {
        ObservableList<Usuario> AuxUsuario = FXCollections.observableArrayList();
        try {
            String cadenaSQL = "";
            cadenaSQL = cadenaSQL + "  select [usr_id],[per_id],[usr_nombres],[usr_usuario] , [usr_clave],[usr_estado] ";
            cadenaSQL = cadenaSQL + " FROM Usuario ";
            //conectar a la BD
            Mod_General modG = new Mod_General();
            ConexionDB conec = new ConexionDB();
            conec.conectarBD(modG.getMotorDB());//conectar
            //consular 
            conec.ejecutarConsultaSQL(cadenaSQL);
            //presenta los datos
            ResultSet rs = conec.getResulSet();
            while (rs.next()) {
                String aux_usr_id = rs.getString("usr_id");
                String aux_per_id = rs.getString("per_id");
                String aux_usr_nombres = rs.getString("usr_nombres");
                String aux_usr_usuario = rs.getString("usr_usuario");
                String aux_usr_clave = rs.getString("usr_clave");
                String aux_usr_estado = rs.getString("usr_estado");

                Usuario objUsuario = new Usuario(aux_usr_id, aux_per_id, aux_usr_nombres, aux_usr_usuario, aux_usr_clave, aux_usr_estado);
                AuxUsuario.add(objUsuario);
            }
            //desconectar
            conec.cerrarResulSet();
            conec.cerrarBD();
            return AuxUsuario;
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.WARNING, null, ex);
            return null;
        }

    }

}
