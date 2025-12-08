package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.model.Etiqueta;

import java.util.List;

public interface PrendaEtiquetaDAO {

    List<Etiqueta> getEtiquetasDePrenda(int prendaId);

    void agregarEtiquetaAPrenda(int prendaId, int etiquetaId);

    void quitarEtiquetaDePrenda(int prendaId, int etiquetaId);
}
