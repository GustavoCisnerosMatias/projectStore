/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Cisneros
 */
public class GeneralBDSql {
     private Connection conexion;
    private Statement sentenciaSQL;
    private ResultSet resulset;

    public GeneralBDSql() {
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

    public ResultSet getResulset() {
        return resulset;
    }

    public void setResulset(ResultSet resulset) {
        this.resulset = resulset;
    }
    
    
    public boolean conectarBD(){
        try {
            //1: el nombre del servidor
            String servidor="JAIMEPC\\SQL2016";
            //2: el nombre de la BD con la quiero conectar
            String nombreBD="BD2023_1";
            //3: el usuario de la Base de datos
            String usuario="usr2023";
            //4: clave del usuario
            String clave="usr2023";
            //5: nombre de del componente para conectar
            String classNombre="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            
         
            String cadenaConexion = "jdbc:sqlserver://" + servidor + ":1433;"
                + "database=" + nombreBD + ";"
                + "user=" + usuario + ";"
                + "password=" + clave + ";"
                + " encrypt=false; loginTimeout=30;";
                    
            
            Class.forName(classNombre);
            Connection conexion=DriverManager.getConnection(cadenaConexion);
            conexion.setAutoCommit(false);
            this.setConexion(conexion);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
