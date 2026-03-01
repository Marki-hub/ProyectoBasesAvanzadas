/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AlimentosDAO;
import interfaces.IAlimentosDAO;
import java.sql.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Alimentos;

/**
 *
 * @author MoriTejo
 */
public class AlimentosController {
    private final IAlimentosDAO alimentosDAO;

    public AlimentosController() {
        this.alimentosDAO = new AlimentosDAO();
    }

    public boolean agregarAlimento(String nombre, String categoria, Date fechaCaducidad, int cantidad, int idDonador) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del alimento no puede estar vacio");
            return false;
        }

        if (cantidad < 0) {
            System.err.println("La cantidad no puede ser negativa");
            return false;
        }

        if (idDonador <= 0) {
            System.err.println("ID de donador invalido");
            return false;
        }

        Alimentos alimento = new Alimentos();
        alimento.setNombre(nombre);
        alimento.setCategoria(categoria);
        alimento.setFechaCaducidad(fechaCaducidad);
        alimento.setCantidad(cantidad);
        alimento.setIdDonador(idDonador);

        return alimentosDAO.insertar(alimento);
    }

    public Alimentos obtenerAlimento(int idAlimento) {
        if (idAlimento <= 0) {
            System.err.println("ID de alimento invalido.");
            return null;
        }
        return alimentosDAO.buscarPorId(idAlimento);
    }

    public List<Alimentos> listarAlimentos() {
        return alimentosDAO.obtenerTodos();
    }

    public boolean actualizarAlimento(int idAlimento, String nombre, String categoria, Date fechaCaducidad, int cantidad, int idDonador) {
        if (idAlimento <= 0) {
            System.err.println("ID de alimento invalido");
            return false;
        }

        Alimentos alimento = new Alimentos();
        alimento.setIdAlimentos(idAlimento);
        alimento.setNombre(nombre);
        alimento.setCategoria(categoria);
        alimento.setFechaCaducidad(fechaCaducidad);
        alimento.setCantidad(cantidad);
        alimento.setIdDonador(idDonador);

        return alimentosDAO.actualizar(alimento);
    }

    public boolean eliminarAlimento(int idAlimento) {
        if (idAlimento <= 0) {
            System.err.println("ID de alimento invalido");
            return false;
        }
        return alimentosDAO.eliminar(idAlimento);
    }

    public DefaultTableModel obtenerTablaAlimentos() {
        String[] columnas = {"ID", "Nombre", "Categoría", "Caducidad", "Cantidad", "ID Donador"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        
        List<Alimentos> lista = alimentosDAO.obtenerTodos();
        for (Alimentos a : lista) {
            modelo.addRow(new Object[]{
                a.getIdAlimentos(),
                a.getNombre(),
                a.getCategoria(),
                a.getFechaCaducidad(),
                a.getCantidad(),
                a.getIdDonador()
            });
        }
        return modelo;
    }
}