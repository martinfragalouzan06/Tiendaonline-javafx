package edu.martin.javafx.login.mfl_project.controller;

import edu.martin.javafx.login.mfl_project.dao.CategoriaDAO;
import edu.martin.javafx.login.mfl_project.dao.CategoriaDAOImpl;
import edu.martin.javafx.login.mfl_project.dao.PrendaDAO;
import edu.martin.javafx.login.mfl_project.dao.PrendaDAOImpl;
import edu.martin.javafx.login.mfl_project.model.Categoria;
import edu.martin.javafx.login.mfl_project.model.Prenda;
import edu.martin.javafx.login.mfl_project.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrendaController implements Initializable {

    @FXML private TextField nombreField;
    @FXML private TextField colorField;
    @FXML private TextField tallaField;
    @FXML private ComboBox<Categoria> categoriaCombo;
    @FXML private CheckBox favoritaCheck;
    @FXML private RadioButton veranoRadio;
    @FXML private RadioButton inviernoRadio;
    @FXML private Slider usoSlider;
    @FXML private Label usoLabel;
    @FXML private Button guardarButton;
    @FXML private Button cancelarButton;

    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
    private final PrendaDAO prendaDAO = new PrendaDAOImpl();

    private Usuario usuarioActual;
    private Prenda prendaActual;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriaCombo.getItems().addAll(categoriaDAO.getAll());

        ToggleGroup grupoTemporada = new ToggleGroup();
        veranoRadio.setToggleGroup(grupoTemporada);
        inviernoRadio.setToggleGroup(grupoTemporada);

        usoSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                usoLabel.setText(String.valueOf(newVal.intValue()))
        );
    }

    public void cargarDatos(Usuario usuario, Prenda prenda) {
        this.usuarioActual = usuario;
        this.prendaActual = prenda;

        if (prenda != null) {
            nombreField.setText(prenda.getNombre());
            colorField.setText(prenda.getColor());
            tallaField.setText(prenda.getTalla());
            categoriaCombo.getSelectionModel().select(prenda.getCategoria());
        }
    }

    @FXML
    private void guardarRopa() {
        String nombre = nombreField.getText();
        String color = colorField.getText();
        String talla = tallaField.getText();
        Categoria categoria = categoriaCombo.getSelectionModel().getSelectedItem();

        if (nombre.isBlank() || color.isBlank() || categoria == null) {
            alerta("Faltan datos", "Rellena nombre, color y categoría.");
            return;
        }

        if (prendaActual == null) {
            Prenda nueva = new Prenda(nombre, color, talla, usuarioActual.getId(), categoria);
            prendaDAO.insertar(nueva);
        } else {
            prendaActual.setNombre(nombre);
            prendaActual.setColor(color);
            prendaActual.setTalla(talla);
            prendaActual.setCategoria(categoria);
            prendaDAO.actualizar(prendaActual);
        }

        cerrar();
    }

    @FXML
    private void cancelarRopa() {
        cerrar();
    }

    private void cerrar() {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    private void alerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
