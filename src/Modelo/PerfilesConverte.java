/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.util.StringConverter;

/**
 *
 * @author Cisneros
 */
public class PerfilesConverte extends StringConverter<Perfiles>{

    @Override
    public String toString(Perfiles t) {
            return t==null ? null: t.Descripcion_Perfil;
    }

    @Override
    public Perfiles fromString(String string) {
       return null;
    }
    
}
