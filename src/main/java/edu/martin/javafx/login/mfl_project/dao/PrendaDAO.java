package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.model.Prenda;
import java.util.List;

public interface PrendaDAO {

    void insertar(Prenda prenda, int usuarioId);

    void actualizar(Prenda prenda);

    void eliminar(int id);

    List<Prenda> getPrendasDeUsuario(int usuarioId);
}
