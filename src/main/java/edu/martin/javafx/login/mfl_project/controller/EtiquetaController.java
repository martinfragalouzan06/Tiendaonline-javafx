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

    private final ObservableList<Etiqueta> disponibles = FXCollections.observableArrayList();
    private final ObservableList<Etiqueta> dePrenda = FXCollections.observableArrayList();

    private Prenda prendaActual;

    public void cargarDatos(Prenda prenda) {
        this.prendaActual = prenda;
        prendaLabel.setText(prenda.getNombre());

        List<Etiqueta> todas = etiquetaDAO.getAll();
        List<Etiqueta> asignadas = etiquetaDAO.getEtiquetasDePrenda(prenda.getId());

        disponibles.setAll(todas);
        disponibles.removeAll(asignadas);

        dePrenda.setAll(asignadas);

        listaDisponibles.setItems(disponibles);
        listaDePrenda.setItems(dePrenda);
    }

    @FXML
    private void agregarEtiqueta() {
        Etiqueta seleccionada = listaDisponibles.getSelectionModel().getSelectedItem();
        if (seleccionada == null) return;

        disponibles.remove(seleccionada);
        dePrenda.add(seleccionada);

        prendaEtiquetaDAO.insertar(prendaActual.getId(), seleccionada.getId());
    }

    @FXML
    private void quitarEtiqueta() {
        Etiqueta seleccionada = listaDePrenda.getSelectionModel().getSelectedItem();
        if (seleccionada == null) return;

        dePrenda.remove(seleccionada);
        disponibles.add(seleccionada);

        prendaEtiquetaDAO.eliminar(prendaActual.getId(), seleccionada.getId());
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) prendaLabel.getScene().getWindow();
        stage.close();
    }
}
