package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Persona;


public class PersonController {

    @FXML
    private TableView tablaPersonas;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaApellidos;
    @FXML
    private TableColumn columnaEdad;
    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;

    private ObservableList<Persona> personas;

    @FXML
    public void initialize() {
        personas = FXCollections.observableArrayList();
        this.tablaPersonas.setItems(personas);

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.columnaEdad.setCellValueFactory(new PropertyValueFactory("edad"));
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Persona p = (Persona) this.tablaPersonas.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void agregarPersona(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PersonCreateView.fxml"));

        try {
            Parent root = loader.load();

            PersonCreateController controller = loader.getController();


            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Persona p = controller.getPersona();
            controller.setPersonas(personas, p);

            if (p != null) {
                personas.add(p);
                this.tablaPersonas.refresh();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarPersona(ActionEvent event) {
        Persona p = (Persona) this.tablaPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al eliminar");
            alert.setContentText("Debe seleccionar una persona para eliminarla");
            alert.showAndWait();
        } else {
            personas.remove(p);
            this.tablaPersonas.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Persona eliminada");
            alert.setContentText("La persona ha sido eliminada correctamente");
            alert.showAndWait();
        }
    }

    @FXML
    private void modificarPersona(ActionEvent event) {
        Persona p = (Persona) this.tablaPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al modificar");
            alert.setContentText("Debe seleccionar una persona para modificarla");
            alert.showAndWait();
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PersonCreateView.fxml"));

                Parent root = loader.load();

                PersonCreateController controller = loader.getController();
                controller.setPersonas(personas, p);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
                stage.showAndWait();

                Persona aux = controller.getPersona();
                if(aux != null){
                    this.tablaPersonas.refresh();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}