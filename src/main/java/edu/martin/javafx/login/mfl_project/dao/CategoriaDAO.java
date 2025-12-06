package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.model.Categoria;

import java.util.List;

public interface CategoriaDAO {
    List<Categoria> getAll(); // Obtener todas las categorías camiseta, pantalón, chaqueta y vestido
    Categoria getById(int id);

    void insertar(Categoria categoria);
    void actualizar(Categoria categoria);
    void eliminar(int id);
}
