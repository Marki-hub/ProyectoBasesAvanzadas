/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author marki
 */
public class DonadoresDAO implements IDonadoresDAO{
    @Override
    public boolean insertar(Donadores donador) {
        String sql = "INSERT INTO Donadores (nombre, tipo, iddireccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, donador.getNombre());
            ps.setString(2, donador.getTipo());
            ps.setInt(3, donador.getIdDireccion());
            ps.setString(4, donador.getTelefono());
            ps.setString(5, donador.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar donador: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Donadores obtenerPorID(int idDonador) {
        String sql = "SELECT ID_Donador, Nombre, Tipo, ID_Direccion, Telefono, Correo_Electronico FROM Donadores WHERE idDonador = ?";
        Donadores donador = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDonador);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                donador = new Donadores();
                donador.setIdDonador(rs.getInt("idDonador"));
                donador.setNombre(rs.getString("nombre"));
                donador.setTipo(rs.getString("tipo"));
                donador.setIdDireccion(rs.getInt("idDireccion"));
                donador.setTelefono(rs.getString("telefono"));
                donador.setCorreo("correo");
                
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener donador por ID: " + e.getMessage());
        }
        return donador;
    }

    @Override
    public List<Donadores> obtenerTodos() {
        String sql = "SELECT ID_Donador, Nombre, Tipo, ID_Direccion, Telefono, Correo_Electronico FROM Donadores";
        List<Donadores> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Donadores donador = new Donadores();
                donador.setIdDonador(rs.getInt("idDonador"));
                donador.setNombre(rs.getString("nombre"));
                donador.setTipo(rs.getString("tipo"));
                donador.setIdDireccion(rs.getInt("idDireccion"));
                donador.setTelefono(rs.getString("telefono"));
                donador.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los donadores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Donadores donador) {
        String sql = "UPDATE Donadores SET nombre = ?, tipo = ? , ID_Direccion = ? , telefono = ?, Correo_Electronico = ? WHERE idDonador = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, donador.getNombre());
            ps.setString(2, donador.getTipo());
            ps.setInt(3, donador.getIdDireccion());
            ps.setString(4, donador.getTelefono());
            ps.setString(5, donador.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar donador: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idDonador) {
        String sql = "DELETE FROM Donadores WHERE idDonador = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDonador);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar donador: " + e.getMessage());
            return false;
        }
    }
    
}
