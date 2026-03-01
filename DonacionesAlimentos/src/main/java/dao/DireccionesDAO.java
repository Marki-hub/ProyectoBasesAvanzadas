/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author marki
 */
public class DireccionesDAO implements IDireccionesDAO{
    @Override
    public int insertar(Direcciones direcciones) {
        String sql = "INSERT INTO Direcciones (calle, numero, colonia) VALUES (?, ?, ?)";
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
            System.err.println("Error al insertar direccion: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public Direcciones obtenerPorID(int idDireccion) {
        String sql = "SELECT ID_Direccion, calle, numero, colonia FROM Direcciones WHERE idDireccion = ?";
        Direcciones direccion = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDireccion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                direccion = new Direcciones();
                direccion.setIdDirecciones(rs.getInt("ID_Direccion"));
                direccion.setCalle(rs.getString("calle"));
                direccion.setNumero(rs.getString("numero"));
                direccion.setColonia(rs.getString("colonia"));
                
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener Direccion por ID: " + e.getMessage());
        }
        return direccion;
    }
    
    @Override
    public Direcciones obtenerPorDonador(int idDonador){
        String sql = "SELECT ID_Direccion, Calle, Numero, Colonia FROM Direcciones dir JOIN Donadores d ON dir.ID_Direccion = d.ID_Direccion WHERE d.ID_Donador = ?";
        Direcciones direccion = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDonador);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                direccion = new Direcciones();
                direccion.setIdDirecciones(rs.getInt("ID_Direccion"));
                direccion.setCalle(rs.getString("calle"));
                direccion.setNumero(rs.getString("numero"));
                direccion.setColonia(rs.getString("colonia"));
                
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener direccion por donador: " + e.getMessage());
        }
        return direccion;
    }
    @Override
    public Direcciones obtenerPorOrganizacion(int idOrganizacion){
        String sql = "SELECT ID_Direccion, Calle, Numero, Colonia FROM Direcciones d JOIN Organizaciones o ON d.ID_Direccion = o.ID_Direccion WHERE o.ID_Organizacion = ?";
        Direcciones direccion = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrganizacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                direccion = new Direcciones();
                direccion.setIdDirecciones(rs.getInt("ID_Direccion"));
                direccion.setCalle(rs.getString("calle"));
                direccion.setNumero(rs.getString("numero"));
                direccion.setColonia(rs.getString("colonia"));
                
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener direccion por donador: " + e.getMessage());
        }
        return direccion;
    }
    
    @Override
    public List<Direcciones> obtenerTodos() {
        String sql = "SELECT ID_Direcciones , calle , numero , colonia FROM Direcciones";
        List<Direcciones> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Direcciones direccion = new Direcciones();
                direccion.setIdDirecciones(rs.getInt("ID_Direccion"));
                direccion.setCalle(rs.getString("calle"));
                direccion.setNumero(rs.getString("numero"));
                direccion.setColonia(rs.getString("colonia"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todas las direcciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Direcciones direccion) {
        String sql = "UPDATE Direcciones SET calle = ?, numero = ? , colonia = ?  WHERE idDireccion = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, direccion.getCalle());
            ps.setString(2, direccion.getNumero());
            ps.setString(3, direccion.getColonia());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar direccion: " + e.getMessage());
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
            System.err.println("Error al eliminar direccion: " + e.getMessage());
            return false;
        }
    }
}
