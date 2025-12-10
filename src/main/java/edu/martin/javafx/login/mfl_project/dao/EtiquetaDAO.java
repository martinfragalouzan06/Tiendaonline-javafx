package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.model.Etiqueta;

import java.util.List;

public interface EtiquetaDAO {

    List<Etiqueta> getAll();

    List<Etiqueta> getEtiquetasDePrenda(int prendaId);
}
