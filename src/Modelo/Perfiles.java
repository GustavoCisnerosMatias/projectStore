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
public class Perfiles {

    String ID_Perfil;
    String Descripcion_Perfil;
    String Estado_perfil;

    public Perfiles() {
    }

    public String getID_Perfil() {
        return ID_Perfil;
    }

    public void setID_Perfil(String ID_Perfil) {
        this.ID_Perfil = ID_Perfil;
    }

    public String getDescripcion_Perfil() {
        return Descripcion_Perfil;
    }

    public void setDescripcion_Perfil(String Descripcion_Perfil) {
        this.Descripcion_Perfil = Descripcion_Perfil;
    }

    public String getEstado_perfil() {
        return Estado_perfil;
    }

    public void setEstado_perfil(String Estado_perfil) {
        this.Estado_perfil = Estado_perfil;
    }

    public Perfiles(String ID_Perfil, String Descripcion_Perfil, String Estado_perfil) {
        this.ID_Perfil = ID_Perfil;
        this.Descripcion_Perfil = Descripcion_Perfil;
        this.Estado_perfil = Estado_perfil;
    }

    
    
    
    public ObservableList<Perfiles> getPerfiles() {
        ObservableList<Perfiles> AuxPerfiles = FXCollections.observableArrayList();
        try {
            String cadenaSQL = "";
            cadenaSQL = cadenaSQL + " select [per_id],[per_descripcion],[per_estado] ";
            cadenaSQL = cadenaSQL + " from Perfil ";
            //conectar a la BD
            Mod_General modG = new Mod_General();
            ConexionDB conec = new ConexionDB();
            conec.conectarBD(modG.getMotorDB());//conectar
            //consular 
            conec.ejecutarConsultaSQL(cadenaSQL);
            //presenta los datos
            ResultSet rs = conec.getResulSet();
            while (rs.next()) {
                String aux_per_id=rs.getString("per_id");
                String aux_per_descripcion=rs.getString("per_descripcion");
                String aux_per_estado=rs.getString("per_estado");
                
                Perfiles objPerfiles=new Perfiles(aux_per_id, aux_per_descripcion, aux_per_estado);
                AuxPerfiles.add(objPerfiles);
            }
            //desconectar
            conec.cerrarResulSet();
            conec.cerrarBD();
           return AuxPerfiles;
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.WARNING, null, ex);
            return null;
        }
    }
    
}
