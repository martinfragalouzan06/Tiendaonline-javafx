package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.db.DatabaseConnection;
import edu.martin.javafx.login.mfl_project.model.Etiqueta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaDAOImpl implements EtiquetaDAO {

    @Override
    public List<Etiqueta> getAll() {
        List<Etiqueta> lista = new ArrayList<>();

        String sql = "SELECT id, nombre FROM etiquetas ORDER BY nombre";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Etiqueta e = new Etiqueta(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                lista.add(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public List<Etiqueta> getEtiquetasDePrenda(int prendaId) {
        List<Etiqueta> lista = new ArrayList<>();

        String sql = """
                SELECT e.id, e.nombre
                FROM etiquetas e
                JOIN prenda_etiqueta pe ON pe.etiqueta_id = e.id
                WHERE pe.prenda_id = ?
                ORDER BY e.nombre
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prendaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Etiqueta e = new Etiqueta(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                    lista.add(e);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}
