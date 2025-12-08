package edu.martin.javafx.login.mfl_project.controller;

import edu.martin.javafx.login.mfl_project.dao.PrendaDAO;
import edu.martin.javafx.login.mfl_project.dao.PrendaDAOImpl;
import edu.martin.javafx.login.mfl_project.model.Prenda;
import edu.martin.javafx.login.mfl_project.model.Usuario;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label usuarioLabel;

    @FXML
    private TableView<Prenda> tablaPrendas;

    @FXML
    private TableColumn<Prenda, String> colNombre;

    @FXML
    private TableColumn<Prenda, String> colColor;

    @FXML
    private TableColumn<Prenda, String> colTalla;

    @FXML
    private TableColumn<Prenda, String> colCategoria;

    private final PrendaDAO prendaDAO = new PrendaDAOImpl();
    private Usuario usuarioActual;
    private final ObservableList<Prenda> prendas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colTalla.setCellValueFactory(new PropertyValueFactory<>("talla"));

        colCategoria.setCellValueFactory(celda ->
                Bindings.createStringBinding(() ->
                        celda.getValue().getCategoria() != null
                                ? celda.getValue().getCategoria().getNombre()
                                : ""
                )
        );

        tablaPrendas.setItems(prendas);
    }

    public void initData(Usuario usuario) {
        this.usuarioActual = usuario;
        usuarioLabel.setText(usuario.getNombre());
        cargarRopaUsuario();
    }

    private void cargarRopaUsuario() {
        prendas.clear();
        List<Prenda> lista = prendaDAO.getPrendasPorUsuario(usuarioActual.getId());
        prendas.addAll(lista);
    }

    @FXML
    private void anadirRopa() {
        abrirFormularioRopa(null);
    }

    @FXML
    private void editarRopa() {
        Prenda seleccionada = tablaPrendas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAviso("Editar prenda", "Selecciona una prenda para editar.");
            return;
        }
        abrirFormularioRopa(seleccionada);
    }

    @FXML
    private void borrarRopa() {
        Prenda seleccionada = tablaPrendas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAviso("Eliminar prenda", "Selecciona una prenda para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Seguro que quieres borrar \"" + seleccionada.getNombre() + "\"?");
        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            prendaDAO.eliminar(seleccionada.getId());
            cargarRopaUsuario();
        }
    }

    private void abrirFormularioRopa(Prenda prenda) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/martin/javafx/login/mfl_project/prenda-view.fxml")
            );
            Parent root = loader.load();

            PrendaController controller = loader.getController();
            controller.cargarDatos(usuarioActual, prenda);

            Stage ventana = new Stage();
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tablaPrendas.getScene().getWindow());
            ventana.setScene(new Scene(root));
            ventana.setTitle(prenda == null ? "Añadir prenda" : "Editar prenda");
            ventana.showAndWait();

            cargarRopaUsuario();

        } catch (IOException e) {
            mostrarAviso("Error", "No se ha podido abrir el formulario.");
        }
    }

    private void mostrarAviso(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
