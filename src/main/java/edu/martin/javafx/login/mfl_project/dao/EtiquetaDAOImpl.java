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
        String sql = "SELECT * FROM etiquetas ORDER BY nombre";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Etiqueta e = new Etiqueta(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                lista.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener etiquetas: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public Etiqueta getById(int id) {
        String sql = "SELECT * FROM etiquetas WHERE id = ?";
        Etiqueta et = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    et = new Etiqueta(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener etiqueta por id: " + e.getMessage());
        }

        return et;
    }
}
