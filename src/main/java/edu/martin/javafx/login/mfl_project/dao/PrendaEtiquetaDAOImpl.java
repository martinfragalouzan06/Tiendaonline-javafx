package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.db.DatabaseConnection;
import edu.martin.javafx.login.mfl_project.model.Etiqueta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrendaEtiquetaDAOImpl implements PrendaEtiquetaDAO {

    @Override
    public List<Etiqueta> getEtiquetasDePrenda(int prendaId) {
        List<Etiqueta> lista = new ArrayList<>();

        String sql = "SELECT e.id, e.nombre " +
                "FROM etiquetas e " +
                "JOIN prenda_etiqueta pe ON e.id = pe.etiqueta_id " +
                "WHERE pe.prenda_id = ? " +
                "ORDER BY e.nombre";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, prendaId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Etiqueta e = new Etiqueta(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                    lista.add(e);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener etiquetas de prenda: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public void agregarEtiquetaAPrenda(int prendaId, int etiquetaId) {
        String sql = "INSERT IGNORE INTO prenda_etiqueta (prenda_id, etiqueta_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, prendaId);
            pst.setInt(2, etiquetaId);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al agregar etiqueta a prenda: " + e.getMessage());
        }
    }

    @Override
    public void quitarEtiquetaDePrenda(int prendaId, int etiquetaId) {
        String sql = "DELETE FROM prenda_etiqueta WHERE prenda_id = ? AND etiqueta_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, prendaId);
            pst.setInt(2, etiquetaId);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al quitar etiqueta de prenda: " + e.getMessage());
        }
    }
}
