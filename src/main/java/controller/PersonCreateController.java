package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Persona;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PersonCreateController {

    @FXML
    private Button btnSalir;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;

    private Persona persona;

    private ObservableList<Persona> personas;


    public void initialize() {
        personas = FXCollections.observableArrayList();

    }

    public void setPersonas(ObservableList<Persona> personas, Persona p) {
        this.personas = Objects.requireNonNullElseGet(personas, FXCollections::observableArrayList);
        this.persona = p;
        this.txtNombre.setText(p.getNombre());
        this.txtApellidos.setText(p.getApellidos());
        this.txtEdad.setText(String.valueOf(p.getEdad()));
    }

    @FXML
    private void guardar(ActionEvent event) {
        String nombre = this.txtNombre.getText();
        String apellidos = this.txtApellidos.getText();
        int edad = Integer.parseInt(this.txtEdad.getText());

        Persona p = new Persona(nombre, apellidos, edad);

        if (!personas.contains(p)) {


            if (this.persona != null) {
                //Modificar
                this.persona.setNombre(nombre);
                this.persona.setApellidos(apellidos);
                this.persona.setEdad(edad);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Persona modificada");
                alert.setContentText("La persona ha sido modificada correctamente");
                alert.showAndWait();


            }else{
                //Añadir
                this.persona = p;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Persona añadida");
                alert.setContentText("La persona ha sido añadida correctamente");
                alert.showAndWait();
            }

            Stage stage = (Stage) btnGuardar.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Persona duplicada");
            alert.setContentText("La persona ya existe en la lista");
            alert.showAndWait();
        }
    }

    @FXML
    private void salir(ActionEvent event){
        this.persona = null;
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    public Persona getPersona() {
        return persona;
    }
}
