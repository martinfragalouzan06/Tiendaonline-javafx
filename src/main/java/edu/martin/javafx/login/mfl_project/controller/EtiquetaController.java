package edu.martin.javafx.login.mfl_project.controller;

import edu.martin.javafx.login.mfl_project.dao.EtiquetaDAO;
import edu.martin.javafx.login.mfl_project.dao.EtiquetaDAOImpl;
import edu.martin.javafx.login.mfl_project.dao.PrendaEtiquetaDAO;
import edu.martin.javafx.login.mfl_project.dao.PrendaEtiquetaDAOImpl;
import edu.martin.javafx.login.mfl_project.model.Etiqueta;
import edu.martin.javafx.login.mfl_project.model.Prenda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class EtiquetaController {

    @FXML
    private Label prendaLabel;

    @FXML
    private ListView<Etiqueta> listaDisponibles;

    @FXML
    private ListView<Etiqueta> listaDePrenda;

    private final EtiquetaDAO etiquetaDAO = new EtiquetaDAOImpl();
    private final PrendaEtiquetaDAO prendaEtiquetaDAO = new PrendaEtiquetaDAOImpl();

    private Prenda prendaActual;

    private final ObservableList<Etiqueta> disponibles = FXCollections.observableArrayList();
    private final ObservableList<Etiqueta> dePrenda = FXCollections.observableArrayList();

    public void cargarDatos(Prenda prenda) {
        this.prendaActual = prenda;

        // Aquí ya NO usamos getDescripcionLarga()
        prendaLabel.setText(prenda.getNombre() + " (" + prenda.getColor() + ")");

        listaDisponibles.setItems(disponibles);
        listaDePrenda.setItems(dePrenda);

        cargarListas();
    }

    private void cargarListas() {
        disponibles.clear();
        dePrenda.clear();

        List<Etiqueta> todas = etiquetaDAO.getAll();
        List<Etiqueta> usadas = prendaEtiquetaDAO.getEtiquetasDePrenda(prendaActual.getId());

        dePrenda.addAll(usadas);

        for (Etiqueta e : todas) {
            if (!contieneEtiqueta(usadas, e.getId())) {
                disponibles.add(e);
            }
        }
    }

    private boolean contieneEtiqueta(List<Etiqueta> lista, int etiquetaId) {
        for (Etiqueta e : lista) {
            if (e.getId() == etiquetaId) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void agregarEtiqueta() {
        Etiqueta seleccionada = listaDisponibles.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            aviso("Selecciona una etiqueta disponible para agregar.");
            return;
        }

        prendaEtiquetaDAO.agregarEtiquetaAPrenda(prendaActual.getId(), seleccionada.getId());
        cargarListas();
    }

    @FXML
    private void quitarEtiqueta() {
        Etiqueta seleccionada = listaDePrenda.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            aviso("Selecciona una etiqueta de la prenda para quitar.");
            return;
        }

        prendaEtiquetaDAO.quitarEtiquetaDePrenda(prendaActual.getId(), seleccionada.getId());
        cargarListas();
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) prendaLabel.getScene().getWindow();
        stage.close();
    }

    private void aviso(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Información");
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
