package edu.martin.javafx.login.mfl_project.controller;

import edu.martin.javafx.login.mfl_project.dao.EtiquetaDAO;
import edu.martin.javafx.login.mfl_project.dao.EtiquetaDAOImpl;
import edu.martin.javafx.login.mfl_project.dao.PrendaDAO;
import edu.martin.javafx.login.mfl_project.dao.PrendaDAOImpl;
import edu.martin.javafx.login.mfl_project.model.Etiqueta;
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
import java.util.stream.Collectors;

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

    @FXML
    private TableColumn<Prenda, Number> colNumEtiquetas;

    @FXML
    private TableColumn<Prenda, String> colEtiquetas;

    private final PrendaDAO prendaDAO = new PrendaDAOImpl();
    private final EtiquetaDAO etiquetaDAO = new EtiquetaDAOImpl();

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

        colNumEtiquetas.setCellValueFactory(new PropertyValueFactory<>("cantidadEtiquetas"));
        colEtiquetas.setCellValueFactory(new PropertyValueFactory<>("etiquetasResumen"));

        tablaPrendas.setItems(prendas);
    }

    public void initData(Usuario usuario) {
        this.usuarioActual = usuario;
        usuarioLabel.setText(usuario.getNombre());
        cargarRopaUsuario();
    }

    private void cargarRopaUsuario() {
        prendas.clear();
        List<Prenda> lista = prendaDAO.getPrendasDeUsuario(usuarioActual.getId());

        for (Prenda p : lista) {
            List<Etiqueta> etiquetas = etiquetaDAO.getEtiquetasDePrenda(p.getId());
            p.setCantidadEtiquetas(etiquetas.size());

            String resumen = etiquetas.stream()
                    .map(Etiqueta::getNombre)
                    .collect(Collectors.joining(", "));

            p.setEtiquetasResumen(resumen);
        }

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

    @FXML
    private void gestionarEtiquetas() {
        Prenda seleccionada = tablaPrendas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAviso("Etiquetas", "Selecciona una prenda para gestionar sus etiquetas.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/martin/javafx/login/mfl_project/etiqueta-view.fxml")
            );
            Parent root = loader.load();

            EtiquetaController controller = loader.getController();
            controller.cargarDatos(seleccionada);

            Stage ventana = new Stage();
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tablaPrendas.getScene().getWindow());
            ventana.setScene(new Scene(root));
            ventana.setTitle("Etiquetas de la prenda");
            ventana.showAndWait();

            cargarRopaUsuario();

        } catch (IOException e) {
            mostrarAviso("Error", "No se ha podido abrir la ventana de etiquetas.");
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
            mostrarAviso("Error", "No se ha podido abrir el formulario de prenda.");
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
