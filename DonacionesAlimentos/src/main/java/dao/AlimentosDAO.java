/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author MoriTejo
 */
public class AlimentosDAO implements IAlimentosDAO{

    @Override
    public boolean insertar(Alimentos alimento) {
        String sql = "Insert into alimentos (nombre, categoria, fechaCaducidad, cantidad, idDonador) values (?, ?, ?, ?, ?)";
        try(Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))     {
            
            ps.setString(1, alimento.getNombre());
            ps.setString(2, alimento.getCategoria());
            ps.setDate(3, alimento.getFechaCaducidad());
            ps.setInt(4, alimento.getCantidad());
            ps.setInt(5, alimento.getIdDonador());
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al insertar un alimento "+ e.getMessage());
            return false;
        
        }
    }

    @Override
    public Alimentos buscarPorId(int idAlimento) {
        String sql = "select idAlimento, nombre, categoria, fechaCaducidad, cantidad, idDonador from alimentos where idAlimento = ?";
        Alimentos alimento = null;
        
        try(Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))     {
            
            ps.setInt(1, idAlimento);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                alimento = new Alimentos();
                alimento.setIdAlimentos(rs.getInt("idAlimento"));
                alimento.setNombre(rs.getString("nombre"));
                alimento.setCategoria(rs.getString("categoria"));
                alimento.setFechaCaducidad(rs.getDate("fechaCaducidad"));
                alimento.setCantidad(rs.getInt("cantidad"));
                alimento.setIdDonador(rs.getInt("idDonador"));
                
            }
            
        }catch(SQLException e){
            System.err.println("Error al buscar un alimento por su id "+ e.getMessage());
            
        }
        return alimento;
        
    }

    @Override
    public List<Alimentos> obtenerTodos() {
        String sql = "select idAlimento, nombre, categoria, fechaCaducidad, cantidad, idDonador from alimentos";
        List<Alimentos> lista = new ArrayList<>();
        
        try(Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery())     {
            
            while (rs.next()){
                Alimentos alimento = new Alimentos();
                alimento.setIdAlimentos(rs.getInt("idAlimento"));
                alimento.setNombre(rs.getString("nombre"));
                alimento.setCategoria(rs.getString("categoria"));
                alimento.setFechaCaducidad(rs.getDate("fechaCaducidad"));
                alimento.setCantidad(rs.getInt("cantidad"));
                alimento.setIdDonador(rs.getInt("idDonador"));
                lista.add(alimento);
                
            }
            
        }catch(SQLException e){
            System.err.println("Error al obtener todos los alimentos "+ e.getMessage());
            
        }
        return lista;
        
    }

    @Override
    public boolean actualizar(Alimentos alimento) {
        String sql = "update Alimentos set nombre = ?, categoria = ?, fechaCaducidad = ?, cantidad = ?, idDonador = ? where idAlimento = ?";
        
        try(Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))     {
            
            ps.setString(1, alimento.getNombre());
            ps.setString(2, alimento.getCategoria());
            ps.setDate(3, alimento.getFechaCaducidad());
            ps.setInt(4, alimento.getCantidad());
            ps.setInt(5, alimento.getIdDonador());
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al actualizar un alimento "+ e.getMessage());
            return false;
        
        }
        
    }

    @Override
    public boolean eliminar(int idAlimento) {
        String sql = "delete from Alimentos where idAlimento = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAlimento);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar un alimento: " + e.getMessage());
            return false;
        }
        
    }
    
}
