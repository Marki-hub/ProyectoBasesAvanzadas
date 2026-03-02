/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EntregasDAO;
import interfaces.IEntregasDAO;
import java.sql.Date;
import java.util.List;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import model.Entregas;
        

/**
 *
 * @author MoriTejo
 */
public class EntregasController {
    private final IEntregasDAO entregasDAO;
    private final List<String> estadosValido = Arrays.asList("pendiente", "en tránsito", "completada", "cancelada");
    
    public EntregasController(){
        this.entregasDAO= new EntregasDAO();
    }
    
    public boolean agregarEntrega (Date fechaEntrega, String estado, int idOrganizacion, int idAlimento){
        if(estado == null){
            estado="pendiente";
        }
        if(!estadosValido.contains(estado.toLowerCase())){
            System.err.println("Estado invalido.");
            return false;
        }
        
        if(idOrganizacion<=0){
            System.err.println("Id de otganizacion invalido ");
            return false;
        }
        if(idAlimento<=0){
            System.err.println("Id de alimento invalido ");
            return false;
        }
        
        Entregas entrega = new Entregas();
        entrega.setFechaEntrega(fechaEntrega);
        entrega.setEstado(estado);
        entrega.setIdOrganizaciones(idOrganizacion);
        entrega.setIdAlimentos(idAlimento);
        
        return entregasDAO.insertar(entrega);
    }
    
    
    public Entregas obtenerEntrega(int idEntrega){
        if(idEntrega<= 0 ){
            System.err.println("Id de entrega invalido");
            return null;
        }
        return entregasDAO.obtenerPorID(idEntrega);
    }
    
    public List<Entregas> listarEntregas(){
        return entregasDAO.obtenerTodos();
    }
    
    public boolean actualizarEntrega (int idEntrega, Date fechaEntrega, String estado, int idOrganizacion, int idAlimento){
        if(idEntrega<= 0){
            System.err.println("id de entrega invalido");
        }
        if(estado == null){
            estado="pendiente";
        }
        if(estado == null || !estadosValido.contains(estado.toLowerCase())){
            System.err.println("Estado invalido.");
            return false;
        }
        
        if(idOrganizacion<=0){
            System.err.println("Id de otganizacion invalido ");
            return false;
        }
        if(idAlimento<=0){
            System.err.println("Id de alimento invalido ");
            return false;
        }
        
        Entregas entrega = new Entregas();
        entrega.setIdEntrega(idEntrega);
        entrega.setFechaEntrega(fechaEntrega);
        entrega.setEstado(estado);
        entrega.setIdOrganizaciones(idOrganizacion);
        entrega.setIdAlimentos(idAlimento);
        
        return entregasDAO.actualizar(entrega);
    }
    
    public boolean eliminarEntrega(int idEntrega){
        if(idEntrega<= 0){
            System.err.println("Id de entrega invalido");
            return false;
        }
        return entregasDAO.eliminar(idEntrega);     
    }
    
    
    public DefaultTableModel obtenerTablaEntregas(){
        String[] columnas = {"ID Entrega", "Fecha entrega", "Estado", "id Organizacion", "Id Alimentos"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Entregas> lista = entregasDAO.obtenerTodos();
        for (Entregas e : lista){
            modelo.addRow(new Object[]{e.getIdEntrega(), e.getFechaEntrega(), e.getEstado(), e.getIdOrganizaciones(), e.getIdAlimentos()});
        }
        return modelo;
    }
    
    
    
}
