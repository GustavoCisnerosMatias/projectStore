/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cisneros
 */
public class ConexionDB {

    private Connection conexion;
    private Statement sentenciaSQL;
    private ResultSet resulSet;

    public ConexionDB() {
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Statement getSentenciaSQL() {
        return sentenciaSQL;
    }

    public void setSentenciaSQL(Statement sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    public ResultSet getResulSet() {
        return resulSet;
    }

    public void setResulSet(ResultSet resulSet) {
        this.resulSet = resulSet;
    }

    public boolean conectarBD(String motor) {
        Mod_General conect = new Mod_General();
        Connection conexion = null;

        try {

            switch (motor.toLowerCase()) {
                case "sql":
                    Class.forName(conect.getClassNombre());
                    conexion = DriverManager.getConnection(conect.getCadenaConexion());
                    break;
                case "mysql":
                    Class.forName(conect.getClassNombreMySQL());
                    conexion = DriverManager.getConnection(conect.getCadenaConexionMYSQL(), conect.getUsuarioBD(), conect.getClaveBD());
                    break;
            }

            conexion.setAutoCommit(false);
            this.setConexion(conexion);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //cerrar db
    public void cerrarBD() {
        try {
            conexion.close();
        } catch (SQLException e) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.WARNING, null, e);
        }
    }

    public void cerrarSentencia() {
        try {
            sentenciaSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    public void cerrarResulSet() {
        try {
            resulSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    public void commit() {
        try {
            conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    public void ejecutarConsultaSQL(String cadenaSQL) {
        try {
            if (conexion != null) {
                System.out.println("conectó");
            } else {
                System.out.println("No se conectó");
            }
            this.sentenciaSQL = conexion.createStatement();
            this.resulSet = this.sentenciaSQL.executeQuery(cadenaSQL);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.WARNING, null, ex);
            //System.out.println(ex.getNextException());
        }

    }

    //para insertar, editar y eliminar
      public int ejecutarSQLCommit(String cadenaSQL, boolean commit) {
        //insert,update,delete
        int filas = 0;
        try {
            this.sentenciaSQL = this.conexion.createStatement();
            filas = this.sentenciaSQL.executeUpdate(cadenaSQL);
            if (commit) {
                
                commit();//función que fue creada anteriormente
            }
            Mod_General.fun_mensajeInformacion("Se actualizo la Base de Datos");
        } catch (SQLException ex) {

            Logger.getLogger(ConexionDB.class.getName()).log(Level.WARNING, null, ex);
            //System.out.println(ex.getNextException());
        }
        return filas;
    }
}
