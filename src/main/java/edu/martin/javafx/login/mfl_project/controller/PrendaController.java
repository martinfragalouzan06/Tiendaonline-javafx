package edu.martin.javafx.login.mfl_project.controller;

import edu.martin.javafx.login.mfl_project.dao.CategoriaDAO;
import edu.martin.javafx.login.mfl_project.dao.CategoriaDAOImpl;
import edu.martin.javafx.login.mfl_project.dao.PrendaDAO;
import edu.martin.javafx.login.mfl_project.dao.PrendaDAOImpl;
import edu.martin.javafx.login.mfl_project.model.Categoria;
import edu.martin.javafx.login.mfl_project.model.Prenda;
import edu.martin.javafx.login.mfl_project.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class PrendaController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField tallaField;

    @FXML
    private ComboBox<Categoria> categoriaCombo;

    @FXML
    private Button guardarButton;

    private final PrendaDAO prendaDAO = new PrendaDAOImpl();
    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

    private final ObservableList<Categoria> categorias = FXCollections.observableArrayList();

    private Usuario usuarioActual;
    private Prenda prendaActual;

    @FXML
    private void initialize() {
        List<Categoria> lista = categoriaDAO.getAll();
        categorias.setAll(lista);
        categoriaCombo.setItems(categorias);
    }

    public void cargarDatos(Usuario usuario, Prenda prenda) {
        usuarioActual = usuario;
        prendaActual = prenda;

        if (prenda != null) {
            nombreField.setText(prenda.getNombre());
            colorField.setText(prenda.getColor());
            tallaField.setText(prenda.getTalla());
            if (prenda.getCategoria() != null) {
                categoriaCombo.getSelectionModel().select(prenda.getCategoria());
            }
        }
    }

    @FXML
    private void guardarRopa() {
        String nombre = nombreField.getText().trim();
        String color = colorField.getText().trim();
        String talla = tallaField.getText().trim();
        Categoria categoria = categoriaCombo.getSelectionModel().getSelectedItem();

        if (nombre.isEmpty() || color.isEmpty() || talla.isEmpty() || categoria == null) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText(null);
            a.setContentText("Rellena todos los campos.");
            a.showAndWait();
            return;
        }

        if (prendaActual == null) {
            Prenda nueva = new Prenda(0, nombre, color, talla, categoria);
            prendaDAO.insertar(nueva, usuarioActual.getId());
        } else {
            prendaActual.setNombre(nombre);
            prendaActual.setColor(color);
            prendaActual.setTalla(talla);
            prendaActual.setCategoria(categoria);
            prendaDAO.actualizar(prendaActual);
        }

        cerrarVentana();
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) guardarButton.getScene().getWindow();
        stage.close();
    }
}
