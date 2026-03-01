/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author MoriTejo
 */
public class EntregasDAO implements IEntregasDAO {

    @Override
    public boolean insertar(Entregas entrega) {
        String sql = "Insert into entregas (fechaEntrega, estado, idAlimento, idOrganizacion) values (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, entrega.getFechaEntrega());
            ps.setString(2, entrega.getEstado());   // falta validaciones
            ps.setInt(3, entrega.getIdAlimentos());
            ps.setInt(4, entrega.getIdOrganizaciones());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar una entrega " + e.getMessage());
            return false;
        }
    }

    @Override
    public Entregas obtenerPorID(int idEntrega) {
        String sql = "select idEntrega, fechaEntrega, estado, idAlimento, idOrganizacion from entregas where idEntrega = ?";
        Entregas entrega = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEntrega);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                entrega = new Entregas();
                entrega.setIdEntrega(rs.getInt("idEntrega"));
                entrega.setFechaEntrega(rs.getDate("fechaEntrega"));
                entrega.setEstado(rs.getString("estado"));
                entrega.setIdAlimentos(rs.getInt("idAlimento"));
                entrega.setIdOrganizaciones(rs.getInt("idOrganizacion"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar una entrega por su id " + e.getMessage());
        }
        return entrega;
    }

    @Override
    public List<Entregas> obtenerTodos() {
        String sql = "select idEntrega, fechaEntrega, estado, idAlimento, idOrganizacion from entregas";
        List<Entregas> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Entregas entrega = new Entregas();
                entrega.setIdEntrega(rs.getInt("idEntrega"));
                entrega.setFechaEntrega(rs.getDate("fechaEntrega"));
                entrega.setEstado(rs.getString("estado"));
                entrega.setIdAlimentos(rs.getInt("idAlimento"));
                entrega.setIdOrganizaciones(rs.getInt("idOrganizacion"));
                lista.add(entrega);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todas las entregas " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Entregas obtenerPorOrganizacion(int idOrganizacion) {
        String sql = "select idEntrega, fechaEntrega, estado, idAlimento, idOrganizacion from entregas where idOrganizacion = ?";
        Entregas entrega = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrganizacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                entrega = new Entregas();
                entrega.setIdEntrega(rs.getInt("idEntrega"));
                entrega.setFechaEntrega(rs.getDate("fechaEntrega"));
                entrega.setEstado(rs.getString("estado"));
                entrega.setIdAlimentos(rs.getInt("idAlimento"));
                entrega.setIdOrganizaciones(rs.getInt("idOrganizacion"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar entrega por organización " + e.getMessage());
        }
        return entrega;
    }

    @Override
    public boolean actualizar(Entregas entrega) {
        String sql = "update entregas set fechaEntrega = ?, estado = ?, idAlimento = ?, idOrganizacion = ? where idEntrega = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, entrega.getFechaEntrega());
            ps.setString(2, entrega.getEstado());       // validaciones
            ps.setInt(3, entrega.getIdAlimentos());
            ps.setInt(4, entrega.getIdOrganizaciones());
            ps.setInt(5, entrega.getIdEntrega());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar una entrega " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idEntrega) {
        String sql = "delete from entregas where idEntrega = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEntrega);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar una entrega: " + e.getMessage());
            return false;
        }
    }
}