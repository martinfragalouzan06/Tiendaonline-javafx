package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.db.DatabaseConnection;
import edu.martin.javafx.login.mfl_project.model.Categoria;
import edu.martin.javafx.login.mfl_project.model.Prenda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrendaDAOImpl implements PrendaDAO {

    @Override
    public void insertar(Prenda prenda, int usuarioId) {
        String sql = "INSERT INTO prendas (nombre, color, talla, categoria_id, usuario_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prenda.getNombre());
            stmt.setString(2, prenda.getColor());
            stmt.setString(3, prenda.getTalla());
            stmt.setInt(4, prenda.getCategoria().getId());
            stmt.setInt(5, usuarioId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(Prenda prenda) {
        String sql = "UPDATE prendas SET nombre=?, color=?, talla=?, categoria_id=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prenda.getNombre());
            stmt.setString(2, prenda.getColor());
            stmt.setString(3, prenda.getTalla());
            stmt.setInt(4, prenda.getCategoria().getId());
            stmt.setInt(5, prenda.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM prendas WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prenda> getPrendasDeUsuario(int usuarioId) {
        List<Prenda> lista = new ArrayList<>();

        String sql = """
                SELECT p.id, p.nombre, p.color, p.talla,
                       c.id AS cat_id, c.nombre AS cat_nombre
                FROM prendas p
                JOIN categorias c ON p.categoria_id = c.id
                WHERE p.usuario_id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria cat = new Categoria(
                        rs.getInt("cat_id"),
                        rs.getString("cat_nombre")
                );

                Prenda p = new Prenda(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("color"),
                        rs.getString("talla"),
                        cat
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}
