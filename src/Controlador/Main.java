/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controlador;

import Modelo.ConexionDB;
import Modelo.Mod_General;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Cisneros
 */
public class Main extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Mod_General General = new Mod_General();
        launch(args);
        
    }

    @Override
    public void start(Stage primerstage) throws Exception {
        //verificar si conecta a la BD
       Mod_General modG=new Mod_General();
       ConexionDB conec=new ConexionDB();
       
        if(conec.conectarBD(modG.getMotorDB())){//SQL//MYsql
            System.out.println("Se conectó con la Base de Datos");           
        }else{
            System.out.println("No pudo conectar con la BD");
            return;
        }

        //fin de la prueba de conexión
        VBox mainPanel=(VBox)FXMLLoader.load(getClass().getResource("/Vista/FXMLVerificacion.fxml"));
        Scene scene=new Scene(mainPanel);
        primerstage.setTitle("Tienda");
        primerstage.setScene(scene);
        primerstage.setMaximized(false);
        //modificado
             /*Sirve para que no se pueda cerrar la ventana
        primerstage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override public void handle(WindowEvent event) {
                event.consume();           //Consumar el evento
            }  
        });
            */
        //fin modificado
        primerstage.show();        
        Rectangle2D prinerscreen= Screen.getPrimary().getVisualBounds();
        primerstage.setX((prinerscreen.getWidth()-primerstage.getWidth())/2);
        primerstage.setY((prinerscreen.getHeight()-primerstage.getHeight())/2); 
    }
    
}
