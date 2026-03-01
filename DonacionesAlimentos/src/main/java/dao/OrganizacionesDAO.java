/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConexionDB;
import interfaces.IOrganizacionesDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Organizaciones;

/**
 *
 * @author marki
 */
public class OrganizacionesDAO implements IOrganizacionesDAO{
    
    @Override
    public boolean insertar(Organizaciones organizacion) {
        String sql = "INSERT INTO Organizaciones (nombreOrganizacion, nombreResponsable, apellidoPaternoResponsable, apellidoMaternoResponsable, idDireccion, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, organizacion.getNombreOrganizacion());
            ps.setString(2, organizacion.getNombreResponsable());
            ps.setString(3, organizacion.getApellidoPaternoResponsable());
            ps.setString(4, organizacion.getApellidoMaternoResponsable());
            ps.setInt(5, organizacion.getIdDireccion());
            ps.setString(6, organizacion.getTelefono());
            ps.setString(7, organizacion.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar orgnizacion: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Organizaciones obtenerPorID(int idOrganizacion) {
        String sql = "SELECT ID_Organizacion, Nombre_Organizacion, Nombre_Responsable, Apellido_Paterno_Responsable, Apellido_Materno_Respnsable, ID_Direccion, Telefono, Correo_Electronico "
                + "FROM Organizaciones WHERE ID_Organizacion = ?";
        Organizaciones organizacion = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrganizacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                organizacion = new Organizaciones();
                organizacion.setIdOrganizaciones(rs.getInt("idOrganizaciones"));
                organizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));
                organizacion.setNombreResponsable(rs.getString("nombreResponsable"));
                organizacion.setApellidoPaternoResponsable(rs.getString("apellidoPaternoResponsable"));
                organizacion.setApellidoMaternoResponsable(rs.getString("apellidoMaternoResponsable"));
                organizacion.setIdDireccion(rs.getInt("idDireccion"));
                organizacion.setTelefono(rs.getString("telefono"));
                organizacion.setCorreo(rs.getString("correo"));
                
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener organizacion por ID: " + e.getMessage());
        }
        return organizacion;
    }

    @Override
    public List<Organizaciones> obtenerTodos() {
        String sql = "SELECT ID_Organizacion, Nombre_Organizacion, Nombre_Responsable, Apellido_Paterno_Responsable, Apellido_Materno_Respnsable, ID_Direccion, Telefono, Correo_Electronico FROM Organizaciones";
        List<Organizaciones> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Organizaciones organizacion = new Organizaciones();
                organizacion.setIdOrganizaciones(rs.getInt("idOrganizaciones"));
                organizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));
                organizacion.setNombreResponsable(rs.getString("nombreResponsable"));
                organizacion.setApellidoPaternoResponsable(rs.getString("apellidoPaternoResponsable"));
                organizacion.setApellidoMaternoResponsable(rs.getString("apellidoMaternoResponsable"));
                organizacion.setIdDireccion(rs.getInt("idDireccion"));
                organizacion.setTelefono(rs.getString("telefono"));
                organizacion.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todas las organizaciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Organizaciones organizacion) {
        String sql = "UPDATE Organizaciones SET Nombre_Organizacion = ?, Nombre_Responsable = ? , Apellido_Paterno_Responsable = ? , Apellido_Materno_Responsable = ? , ID_Direccion = ? , telefono = ?, Correo_Electronico = ? WHERE idOrganizacion = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, organizacion.getNombreOrganizacion());
            ps.setString(2, organizacion.getNombreResponsable());
            ps.setString(3, organizacion.getApellidoPaternoResponsable());
            ps.setString(4, organizacion.getApellidoMaternoResponsable());
            ps.setInt(5, organizacion.getIdDireccion());
            ps.setString(6, organizacion.getTelefono());
            ps.setString(7, organizacion.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar organizacion: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idOrganizacion) {
        String sql = "DELETE FROM Organizaciones WHERE idOrganizacion = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrganizacion);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar organizacion: " + e.getMessage());
            return false;
        }
    }
}
