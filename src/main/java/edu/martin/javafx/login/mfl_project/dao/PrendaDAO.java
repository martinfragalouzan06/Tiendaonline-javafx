package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.model.Prenda;

import java.util.List;

public interface PrendaDAO {

    // Prendas de un usuario concreto
    List<Prenda> getPrendasPorUsuario(int usuarioId);

    // Todas las prendas
    List<Prenda> getALL();

    // Una prenda por id
    Prenda getById(int id);

    // CRUD
    void insertar(Prenda prenda);

    void actualizar(Prenda prenda);

    void eliminar(int id);
}
