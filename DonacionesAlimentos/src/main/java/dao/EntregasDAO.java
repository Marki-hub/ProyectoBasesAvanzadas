package dao;

import config.ConexionDB;
import interfaces.IEntregasDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Entregas;

public class EntregasDAO implements IEntregasDAO {

    @Override
    public boolean insertar(Entregas entrega) {
        String sql = "INSERT INTO Entregas (Fecha_Entrega, Estado, ID_Organizacion, ID_Alimento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, entrega.getFechaEntrega());
            ps.setString(2, entrega.getEstado());
            ps.setInt(3, entrega.getIdOrganizaciones());
            ps.setInt(4, entrega.getIdAlimentos());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar entrega: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Entregas obtenerPorID(int idEntrega) {
        String sql = "SELECT ID_Entrega, Fecha_Entrega, Estado, ID_Organizacion, ID_Alimento FROM Entregas WHERE ID_Entrega = ?";
        Entregas entrega = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEntrega);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                entrega = mapearEntrega(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar entrega por ID: " + e.getMessage());
        }
        return entrega;
    }

    @Override
    public List<Entregas> obtenerTodos() {
        String sql = "SELECT ID_Entrega, Fecha_Entrega, Estado, ID_Organizacion, ID_Alimento FROM Entregas";
        List<Entregas> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearEntrega(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todas las entregas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Entregas obtenerPorOrganizacion(int idOrganizacion) {
        String sql = "SELECT ID_Entrega, Fecha_Entrega, Estado, ID_Organizacion, ID_Alimento FROM Entregas WHERE ID_Organizacion = ?";
        Entregas entrega = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrganizacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                entrega = mapearEntrega(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar entrega por organización: " + e.getMessage());
        }
        return entrega;
    }

    @Override
    public boolean actualizar(Entregas entrega) {
        String sql = "UPDATE Entregas SET Fecha_Entrega = ?, Estado = ?, ID_Organizacion = ?, ID_Alimento = ? WHERE ID_Entrega = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, entrega.getFechaEntrega());
            ps.setString(2, entrega.getEstado());
            ps.setInt(3, entrega.getIdOrganizaciones());
            ps.setInt(4, entrega.getIdAlimentos());
            ps.setInt(5, entrega.getIdEntrega());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar entrega: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idEntrega) {
        String sql = "DELETE FROM Entregas WHERE ID_Entrega = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEntrega);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar entrega: " + e.getMessage());
            return false;
        }
    }

    private Entregas mapearEntrega(ResultSet rs) throws SQLException {
        Entregas e = new Entregas();
        e.setIdEntrega(rs.getInt("ID_Entrega"));
        e.setFechaEntrega(rs.getDate("Fecha_Entrega"));
        e.setEstado(rs.getString("Estado"));
        e.setIdOrganizaciones(rs.getInt("ID_Organizacion"));
        e.setIdAlimentos(rs.getInt("ID_Alimento"));
        return e;
    }
}
