package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrendaEtiquetaDAOImpl implements PrendaEtiquetaDAO {

    @Override
    public void insertar(int prendaId, int etiquetaId) {
        String sql = "INSERT INTO prenda_etiqueta (prenda_id, etiqueta_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prendaId);
            stmt.setInt(2, etiquetaId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int prendaId, int etiquetaId) {
        String sql = "DELETE FROM prenda_etiqueta WHERE prenda_id = ? AND etiqueta_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prendaId);
            stmt.setInt(2, etiquetaId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
