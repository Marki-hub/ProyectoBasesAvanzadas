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

public class OrganizacionesDAO implements IOrganizacionesDAO {

    @Override
    public boolean insertar(Organizaciones organizacion) {
        String sql = "INSERT INTO Organizaciones (Nombre_Organizacion, Nombre_Responsable, Apellido_Paterno_Responsable, Apellido_Materno_Responsable, Telefono, Correo_Electronico, ID_Direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, organizacion.getNombreOrganizacion());
            ps.setString(2, organizacion.getNombreResponsable());
            ps.setString(3, organizacion.getApellidoPaternoResponsable());
            ps.setString(4, organizacion.getApellidoMaternoResponsable());
            ps.setString(5, organizacion.getTelefono());
            ps.setString(6, organizacion.getCorreo());
            ps.setInt(7, organizacion.getIdDireccion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar organización: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Organizaciones obtenerPorID(int idOrganizacion) {
        String sql = "SELECT ID_Organizacion, Nombre_Organizacion, Nombre_Responsable, Apellido_Paterno_Responsable, Apellido_Materno_Responsable, Telefono, Correo_Electronico, ID_Direccion FROM Organizaciones WHERE ID_Organizacion = ?";
        Organizaciones organizacion = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrganizacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                organizacion = mapearOrganizacion(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener organización por ID: " + e.getMessage());
        }
        return organizacion;
    }

    @Override
    public List<Organizaciones> obtenerTodos() {
        String sql = "SELECT ID_Organizacion, Nombre_Organizacion, Nombre_Responsable, Apellido_Paterno_Responsable, Apellido_Materno_Responsable, Telefono, Correo_Electronico, ID_Direccion FROM Organizaciones";
        List<Organizaciones> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearOrganizacion(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todas las organizaciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Organizaciones organizacion) {
        String sql = "UPDATE Organizaciones SET Nombre_Organizacion = ?, Nombre_Responsable = ?, Apellido_Paterno_Responsable = ?, Apellido_Materno_Responsable = ?, Telefono = ?, Correo_Electronico = ?, ID_Direccion = ? WHERE ID_Organizacion = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, organizacion.getNombreOrganizacion());
            ps.setString(2, organizacion.getNombreResponsable());
            ps.setString(3, organizacion.getApellidoPaternoResponsable());
            ps.setString(4, organizacion.getApellidoMaternoResponsable());
            ps.setString(5, organizacion.getTelefono());
            ps.setString(6, organizacion.getCorreo());
            ps.setInt(7, organizacion.getIdDireccion());
            ps.setInt(8, organizacion.getIdOrganizaciones());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar organización: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idOrganizacion) {
        String sql = "DELETE FROM Organizaciones WHERE ID_Organizacion = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrganizacion);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar organización: " + e.getMessage());
            return false;
        }
    }

    private Organizaciones mapearOrganizacion(ResultSet rs) throws SQLException {
        Organizaciones o = new Organizaciones();
        o.setIdOrganizaciones(rs.getInt("ID_Organizacion"));
        o.setNombreOrganizacion(rs.getString("Nombre_Organizacion"));
        o.setNombreResponsable(rs.getString("Nombre_Responsable"));
        o.setApellidoPaternoResponsable(rs.getString("Apellido_Paterno_Responsable"));
        o.setApellidoMaternoResponsable(rs.getString("Apellido_Materno_Responsable"));
        o.setTelefono(rs.getString("Telefono"));
        o.setCorreo(rs.getString("Correo_Electronico"));
        o.setIdDireccion(rs.getInt("ID_Direccion"));
        return o;
    }
}
