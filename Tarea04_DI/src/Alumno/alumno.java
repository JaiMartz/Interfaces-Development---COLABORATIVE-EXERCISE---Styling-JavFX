/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alumno;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jairo
 */
public class alumno {
    
    public SimpleStringProperty dni = new SimpleStringProperty();
    public SimpleIntegerProperty nota = new SimpleIntegerProperty();
    public SimpleStringProperty modulo = new SimpleStringProperty();
    public SimpleIntegerProperty recuperacion = new SimpleIntegerProperty();
    
    /**
     * Constructor de la clase alumno
     * @param dni
     * @param nota
     * @param modulo
     * @param recuperacion
     */
    public alumno (String dni, String modulo, int nota, int recuperacion){
        this.dni = new SimpleStringProperty(dni);
        this.modulo = new SimpleStringProperty(modulo);
        this.nota = new SimpleIntegerProperty(nota);
        this.recuperacion = new SimpleIntegerProperty(recuperacion);
    }
    
    public String getDNI(){
        return dni.get();
    }
    public String getModulo(){
        return modulo.get();
    }
    public int getNota(){
        return nota.get();
    }
    public int getRecuperacion(){
        return recuperacion.get();
    }
    
    //Establecemos métodos set aunque javaFX XML nos proporciona herramientas para realizar la misma función
    public void setDNI(String dni){
        this.dni.set(dni);
    }
    public void setModulo(String modulo){
        this.modulo.set(modulo);
    }
    public void setNota(int nota){
        this.nota.set(nota);
    }
    public void setRecuperacion(int recuperacion){
        this.recuperacion.set(recuperacion);  
    }
    
}
