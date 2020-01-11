/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04_di;

import Alumno.alumno;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Jairo
 */

public class FXMLDocumentController implements Initializable {
    
    
    //Creamos los elementos
    @FXML public Button btnGuardar;
    @FXML public TextField txfDNI;
    @FXML public TextField txfModulo;
    @FXML public TextField txfNota;
    @FXML public TextField txfRecuperacion;
    
    //Añadimos la tabla
    @FXML public TableView<alumno> tablaAlumnos; 
    @FXML public TableColumn clDNI;
    @FXML public TableColumn clModulo;
    @FXML public TableColumn clNota;
    @FXML public TableColumn clRecuperacion;
    
    private final ObservableList<alumno>listaAlumnos = FXCollections.observableArrayList();
    
    
    
    /**
     * Method that clean all the fields.
     */
    @FXML
    private void limpiarCampos(){
    
        txfDNI.setText("");
        txfModulo.setText("");
        txfRecuperacion.setText("");
        txfNota.setText("");
    }
    
    /**
     * Method that allow to save the information and show in the list.
     * @param event 
     */
        @FXML
    private void accionGuardarNotas(ActionEvent event) {
        String dniRegExp = "\\d{8}[A-HJ-NP-TV-Z]";
        int parseNota;
        int parseRecu;  
            
       if(txfModulo.getText().equals("") || txfRecuperacion.getText().equals("") || txfNota.getText().equals("")|| txfDNI.getText().equals("")){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setHeaderText("Error en inserción de campos");
            alert.setContentText("Debe introducir todos los campos.");
            
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/Estilos/modales.css").toExternalForm());
            
            alert.show();
       }else{
            parseNota = Integer.parseInt(txfNota.getText());
            parseRecu = Integer.parseInt(txfRecuperacion.getText());
           
            if((parseNota<0) || (parseNota>10)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error.");
                alert.setHeaderText("Error en inserción de campos");
                alert.setContentText("El campo nota debe estar comprendido entre 0 y 10.");
                
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Estilos/modales.css").toExternalForm());
                
                alert.show();
                
            }else if((parseRecu<0) ||(parseRecu>5)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error.");
                alert.setHeaderText("Error en inserción de campos");
                alert.setContentText("El campo Recuperación debe estar comprendido entre 0 y 5");
                
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Estilos/modales.css").toExternalForm());
                
                alert.show();
                
            }else if(!Pattern.matches(dniRegExp, txfDNI.getText())){
                
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error.");
                alert.setHeaderText("Error en inserción de campos");
                alert.setContentText("Por favor inserte un DNI Válido");
                
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Estilos/modales.css").toExternalForm());
                
                alert.show();
                
            }else if((parseNota>= 5)&&(parseRecu >= 5)){
                
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error.");
                alert.setHeaderText("Error en inserción de Recuperación.");
                alert.setContentText("Si el alumno obtuvo un valor igual o mayor a 5, no puede tener valor de recuperación");
                
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Estilos/modales.css").toExternalForm());
                
                alert.show();
                
            }else{
                if(parseNota >= 5 && parseNota<=10){
                parseRecu = 0   ;
                }
                alumno Alumno = new alumno(txfDNI.getText() ,txfModulo.getText(), Integer.parseInt(txfNota.getText()), parseRecu);
                listaAlumnos.add(Alumno);
                limpiarCampos();
            }
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clDNI.setCellValueFactory(new PropertyValueFactory<alumno, String>("DNI"));
        clModulo.setCellValueFactory(new PropertyValueFactory<alumno, String>("Modulo"));
        clNota.setCellValueFactory(new PropertyValueFactory<alumno, Integer>("Nota"));
        clRecuperacion.setCellValueFactory(new PropertyValueFactory<alumno, String>("Recuperacion"));

        tablaAlumnos.setItems(listaAlumnos);
    }    
    
    @FXML
    public void archivoSalir(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("Salir");
        alert.setContentText("¿Desea salir? La aplicación se cerrará.");
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Estilos/modales.css").toExternalForm());
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                System.exit(1);
            }
    }
    /**
     * Method that allow to clean the fields.
     * @param event 
     */
    @FXML
    public void nuevaNota(ActionEvent event){
    if(txfModulo.getText().isEmpty() && txfRecuperacion.getText().isEmpty()&& txfNota.getText().isEmpty() && txfDNI.getText().isEmpty()){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Editar");
        alert.setHeaderText("Editar");
        alert.setContentText("Los campos de texto ya están vacíos.");
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Estilos/modales.css").toExternalForm());
        
        alert.show();
    }else{
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Editar");
        alert.setHeaderText("Editar");
        alert.setContentText("¿Deseas limpiar los campos de texto?");
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Estilos/modales.css").toExternalForm());
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
               limpiarCampos();
            }
        txfDNI.requestFocus();
        }
    }
    @FXML
    
    /**
     * Method that show a modal window giving information about the developer.
     */
    public void acercaDe(ActionEvent event){
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Acerca de");
    alert.setHeaderText("Acerca de");
    alert.setContentText("Alumno: Jairo Martínez Garrido" +"\n\nPrograma desarrollado para mostrar la " +  
            "estructura que debemos seguir en la creación de una " +  
            "aplicación y la aplicacion de estilos mediante CSS.");
    
    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add(getClass().getResource("/Estilos/modales.css").toExternalForm());
    
    alert.show();
    
    } 
    
    
}