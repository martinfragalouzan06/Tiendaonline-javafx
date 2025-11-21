package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.db.DatabaseConnection;
import edu.martin.javafx.login.mfl_project.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDAOImpl implements UsuarioDAO{

    @Override
    public boolean validarCredenciales(String nickname, String password) {
    }

    @Override
    public Usuario getUsuarioPorNickname(String nickname) {
        return null;
    }
}
