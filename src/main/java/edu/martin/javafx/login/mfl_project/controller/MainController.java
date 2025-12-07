package edu.martin.javafx.login.mfl_project.controller;

import edu.martin.javafx.login.mfl_project.dao.PrendaDAO;
import edu.martin.javafx.login.mfl_project.dao.PrendaDAOImpl;
import edu.martin.javafx.login.mfl_project.model.Prenda;
import edu.martin.javafx.login.mfl_project.model.Usuario;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Optional;
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

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    private final PrendaDAO prendaDAO = new PrendaDAOImpl();
    private Usuario usuarioActual;
    private final ObservableList<Prenda> prendasObservable = FXCollections.observableArrayList();

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

        tablaPrendas.setItems(prendasObservable);
    }

    public void initData(Usuario usuario) {
        this.usuarioActual = usuario;
        usuarioLabel.setText(usuario.getNombre());
        cargarPrendasUsuario();
    }

    private void cargarPrendasUsuario() {
        prendasObservable.clear();
        List<Prenda> prendas = prendaDAO.getPrendasPorUsuario(usuarioActual.getId());
        prendasObservable.addAll(prendas);
    }

    @FXML
    private void onAddButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Añadir prenda");
        alert.setHeaderText(null);
        alert.setContentText("Funcionalidad de añadir prenda pendiente de implementar.");
        alert.showAndWait();
    }

    @FXML
    private void onEditButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Editar prenda");
        alert.setHeaderText(null);
        alert.setContentText("Funcionalidad de editar prenda pendiente de implementar.");
        alert.showAndWait();
    }

    @FXML
    private void onDeleteButtonClick() {
        Prenda seleccionada = tablaPrendas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Eliminar prenda");
            alert.setHeaderText(null);
            alert.setContentText("Selecciona una prenda para eliminar.");
            alert.showAndWait();
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Seguro que quieres eliminar la prenda \"" + seleccionada.getNombre() + "\"?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            prendaDAO.eliminar(seleccionada.getId());
            cargarPrendasUsuario();
        }
    }
}
