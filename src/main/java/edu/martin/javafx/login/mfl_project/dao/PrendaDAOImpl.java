package edu.martin.javafx.login.mfl_project.dao;

import edu.martin.javafx.login.mfl_project.db.DatabaseConnection;
import edu.martin.javafx.login.mfl_project.model.Categoria;
import edu.martin.javafx.login.mfl_project.model.Prenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrendaDAOImpl implements PrendaDAO {

    @Override
    public List<Prenda> getPrendasPorUsuario(int usuarioId) {
        List<Prenda> lista = new ArrayList<>();

        String sql =
                "SELECT prendas.id AS prenda_id, prendas.nombre AS prenda_nombre, " +
                        "prendas.color AS prenda_color, prendas.talla AS prenda_talla, " +
                        "prendas.usuario_id AS prenda_usuario_id, " +
                        "categorias.id AS categoria_id, categorias.nombre AS categoria_nombre " +
                        "FROM prendas " +
                        "JOIN categorias ON prendas.categoria_id = categorias.id " +
                        "WHERE prendas.usuario_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, usuarioId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Categoria categoria = new Categoria(
                        rs.getInt("categoria_id"),
                        rs.getString("categoria_nombre")
                );

                Prenda prenda = new Prenda(
                        rs.getInt("prenda_id"),
                        rs.getString("prenda_nombre"),
                        rs.getString("prenda_color"),
                        rs.getString("prenda_talla"),
                        rs.getInt("prenda_usuario_id"),
                        categoria
                );

                lista.add(prenda);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las prendas por usuario: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<Prenda> getALL() {
        List<Prenda> lista = new ArrayList<>();

        String sql =
                "SELECT prendas.id AS prenda_id, prendas.nombre AS prenda_nombre, " +
                        "prendas.color AS prenda_color, prendas.talla AS prenda_talla, " +
                        "prendas.usuario_id AS prenda_usuario_id, " +
                        "categorias.id AS categoria_id, categorias.nombre AS categoria_nombre " +
                        "FROM prendas " +
                        "JOIN categorias ON prendas.categoria_id = categorias.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Categoria categoria = new Categoria(
                        rs.getInt("categoria_id"),
                        rs.getString("categoria_nombre")
                );

                Prenda prenda = new Prenda(
                        rs.getInt("prenda_id"),
                        rs.getString("prenda_nombre"),
                        rs.getString("prenda_color"),
                        rs.getString("prenda_talla"),
                        rs.getInt("prenda_usuario_id"),
                        categoria
                );

                lista.add(prenda);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener todas las prendas: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public Prenda getById(int id) {
        Prenda prenda = null;

        String sql =
                "SELECT prendas.id AS prenda_id, prendas.nombre AS prenda_nombre, " +
                        "prendas.color AS prenda_color, prendas.talla AS prenda_talla, " +
                        "prendas.usuario_id AS prenda_usuario_id, " +
                        "categorias.id AS categoria_id, categorias.nombre AS categoria_nombre " +
                        "FROM prendas " +
                        "JOIN categorias ON prendas.categoria_id = categorias.id " +
                        "WHERE prendas.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Categoria categoria = new Categoria(
                        rs.getInt("categoria_id"),
                        rs.getString("categoria_nombre")
                );

                prenda = new Prenda(
                        rs.getInt("prenda_id"),
                        rs.getString("prenda_nombre"),
                        rs.getString("prenda_color"),
                        rs.getString("prenda_talla"),
                        rs.getInt("prenda_usuario_id"),
                        categoria
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener prenda por id: " + e.getMessage());
        }

        return prenda;
    }

    @Override
    public void insertar(Prenda prenda) {
        String sql = "INSERT INTO prendas (nombre, color, talla, usuario_id, categoria_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, prenda.getNombre());
            pst.setString(2, prenda.getColor());
            pst.setString(3, prenda.getTalla());
            pst.setInt(4, prenda.getUsuarioId());
            pst.setInt(5, prenda.getCategoria().getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar prenda: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Prenda prenda) {
        String sql =
                "UPDATE prendas " +
                        "SET nombre = ?, color = ?, talla = ?, usuario_id = ?, categoria_id = ? " +
                        "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, prenda.getNombre());
            pst.setString(2, prenda.getColor());
            pst.setString(3, prenda.getTalla());
            pst.setInt(4, prenda.getUsuarioId());
            pst.setInt(5, prenda.getCategoria().getId());
            pst.setInt(6, prenda.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar prenda: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM prendas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar prenda: " + e.getMessage());
        }
    }
}

