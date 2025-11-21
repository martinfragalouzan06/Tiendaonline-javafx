package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.model.Usuario;

public interface UsuarioDAO {
    boolean validarCredenciales(String nickname , String password);
    Usuario getUsuarioPorNickname(String nickname);

}
