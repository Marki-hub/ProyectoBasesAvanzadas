package dao;

import config.ConexionDB;
import interfaces.IDireccionesDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Direcciones;

public class DireccionesDAO implements IDireccionesDAO {

    @Override
    public int insertar(Direcciones direcciones) {
        String sql = "INSERT INTO Direcciones (Calle, Numero, Colonia) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, direcciones.getCalle());
            ps.setString(2, direcciones.getNumero());
            ps.setString(3, direcciones.getColonia());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar dirección: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public Direcciones obtenerPorID(int idDireccion) {
        String sql = "SELECT ID_Direccion, Calle, Numero, Colonia FROM Direcciones WHERE ID_Direccion = ?";
        Direcciones direccion = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDireccion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                direccion = mapearDireccion(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener dirección por ID: " + e.getMessage());
        }
        return direccion;
    }

    @Override
    public Direcciones obtenerPorDonador(int idDonador) {
        String sql = "SELECT dir.ID_Direccion, dir.Calle, dir.Numero, dir.Colonia FROM Direcciones dir JOIN Donadores d ON dir.ID_Direccion = d.ID_Direccion WHERE d.ID_Donador = ?";
        Direcciones direccion = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDonador);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                direccion = mapearDireccion(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener dirección por donador: " + e.getMessage());
        }
        return direccion;
    }

    @Override
    public Direcciones obtenerPorOrganizacion(int idOrganizacion) {
        String sql = "SELECT dir.ID_Direccion, dir.Calle, dir.Numero, dir.Colonia FROM Direcciones dir JOIN Organizaciones o ON dir.ID_Direccion = o.ID_Direccion WHERE o.ID_Organizacion = ?";
        Direcciones direccion = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrganizacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                direccion = mapearDireccion(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener dirección por organización: " + e.getMessage());
        }
        return direccion;
    }

    @Override
    public List<Direcciones> obtenerTodos() {
        String sql = "SELECT ID_Direccion, Calle, Numero, Colonia FROM Direcciones";
        List<Direcciones> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearDireccion(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todas las direcciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Direcciones direccion) {
        String sql = "UPDATE Direcciones SET Calle = ?, Numero = ?, Colonia = ? WHERE ID_Direccion = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, direccion.getCalle());
            ps.setString(2, direccion.getNumero());
            ps.setString(3, direccion.getColonia());
            ps.setInt(4, direccion.getIdDirecciones());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar dirección: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idDireccion) {
        String sql = "DELETE FROM Direcciones WHERE ID_Direccion = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDireccion);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar dirección: " + e.getMessage());
            return false;
        }
    }

    private Direcciones mapearDireccion(ResultSet rs) throws SQLException {
        Direcciones d = new Direcciones();
        d.setIdDirecciones(rs.getInt("ID_Direccion"));
        d.setCalle(rs.getString("Calle"));
        d.setNumero(rs.getString("Numero"));
        d.setColonia(rs.getString("Colonia"));
        return d;
    }
}
