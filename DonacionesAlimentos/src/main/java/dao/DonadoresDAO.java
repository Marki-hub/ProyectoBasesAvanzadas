package dao;

import config.ConexionDB;
import interfaces.IDonadoresDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Donadores;

public class DonadoresDAO implements IDonadoresDAO {

    @Override
    public boolean insertar(Donadores donador) {
        String sql = "INSERT INTO Donadores (Nombre, Tipo, Telefono, Correo_Electronico, ID_Direccion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, donador.getNombre());
            ps.setString(2, donador.getTipo());
            ps.setString(3, donador.getTelefono());
            ps.setString(4, donador.getCorreo());
            ps.setInt(5, donador.getIdDireccion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar donador: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Donadores obtenerPorID(int idDonador) {
        String sql = "SELECT ID_Donador, Nombre, Tipo, Telefono, Correo_Electronico, ID_Direccion FROM Donadores WHERE ID_Donador = ?";
        Donadores donador = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDonador);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                donador = mapearDonador(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener donador por ID: " + e.getMessage());
        }
        return donador;
    }

    @Override
    public List<Donadores> obtenerTodos() {
        String sql = "SELECT ID_Donador, Nombre, Tipo, Telefono, Correo_Electronico, ID_Direccion FROM Donadores";
        List<Donadores> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearDonador(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los donadores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Donadores donador) {
        String sql = "UPDATE Donadores SET Nombre = ?, Tipo = ?, Telefono = ?, Correo_Electronico = ?, ID_Direccion = ? WHERE ID_Donador = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, donador.getNombre());
            ps.setString(2, donador.getTipo());
            ps.setString(3, donador.getTelefono());
            ps.setString(4, donador.getCorreo());
            ps.setInt(5, donador.getIdDireccion());
            ps.setInt(6, donador.getIdDonador());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar donador: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idDonador) {
        String sql = "DELETE FROM Donadores WHERE ID_Donador = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDonador);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar donador: " + e.getMessage());
            return false;
        }
    }

    private Donadores mapearDonador(ResultSet rs) throws SQLException {
        Donadores d = new Donadores();
        d.setIdDonador(rs.getInt("ID_Donador"));
        d.setNombre(rs.getString("Nombre"));
        d.setTipo(rs.getString("Tipo"));
        d.setTelefono(rs.getString("Telefono"));
        d.setCorreo(rs.getString("Correo_Electronico"));
        d.setIdDireccion(rs.getInt("ID_Direccion"));
        return d;
    }
}
