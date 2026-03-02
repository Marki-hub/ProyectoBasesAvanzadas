package dao;

import config.ConexionDB;
import interfaces.IAlimentosDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Alimentos;

public class AlimentosDAO implements IAlimentosDAO {

    @Override
    public boolean insertar(Alimentos alimento) {
        String sql = "INSERT INTO Alimentos (Nombre_Alimento, Categoria, Fecha_Caducidad, Cantidad, ID_Donador) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alimento.getNombre());
            ps.setString(2, alimento.getCategoria());
            ps.setDate(3, alimento.getFechaCaducidad());
            ps.setInt(4, alimento.getCantidad());
            ps.setInt(5, alimento.getIdDonador());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar alimento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Alimentos buscarPorId(int idAlimento) {
        String sql = "SELECT ID_Alimento, Nombre_Alimento, Categoria, Fecha_Caducidad, Cantidad, ID_Donador FROM Alimentos WHERE ID_Alimento = ?";
        Alimentos alimento = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAlimento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                alimento = mapearAlimento(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar alimento por ID: " + e.getMessage());
        }
        return alimento;
    }

    @Override
    public List<Alimentos> obtenerTodos() {
        String sql = "SELECT ID_Alimento, Nombre_Alimento, Categoria, Fecha_Caducidad, Cantidad, ID_Donador FROM Alimentos";
        List<Alimentos> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearAlimento(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los alimentos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Alimentos alimento) {
        String sql = "UPDATE Alimentos SET Nombre_Alimento = ?, Categoria = ?, Fecha_Caducidad = ?, Cantidad = ?, ID_Donador = ? WHERE ID_Alimento = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alimento.getNombre());
            ps.setString(2, alimento.getCategoria());
            ps.setDate(3, alimento.getFechaCaducidad());
            ps.setInt(4, alimento.getCantidad());
            ps.setInt(5, alimento.getIdDonador());
            ps.setInt(6, alimento.getIdAlimentos());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar alimento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idAlimento) {
        String sql = "DELETE FROM Alimentos WHERE ID_Alimento = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAlimento);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar alimento: " + e.getMessage());
            return false;
        }
    }

    private Alimentos mapearAlimento(ResultSet rs) throws SQLException {
        Alimentos a = new Alimentos();
        a.setIdAlimentos(rs.getInt("ID_Alimento"));
        a.setNombre(rs.getString("Nombre_Alimento"));
        a.setCategoria(rs.getString("Categoria"));
        a.setFechaCaducidad(rs.getDate("Fecha_Caducidad"));
        a.setCantidad(rs.getInt("Cantidad"));
        a.setIdDonador(rs.getInt("ID_Donador"));
        return a;
    }
}
