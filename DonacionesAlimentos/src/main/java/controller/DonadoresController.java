/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DireccionesDAO;
import dao.DonadoresDAO;
import interfaces.IDireccionesDAO;
import interfaces.IDonadoresDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Direcciones;
import model.Donadores;

/**
 *
 * @author marki
 */
public class DonadoresController {
    private final IDonadoresDAO donadoresDAO;
    private final IDireccionesDAO direccionesDAO;

    public DonadoresController() {
        this.donadoresDAO = new DonadoresDAO();
        this.direccionesDAO = new DireccionesDAO();
    }
    
    //Insertar donadores con validaciones
    public boolean agregarDonador(String nombre, String tipo, String calle, String numero, String colonia, String telefono, String correo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío.");
            return false;
        }

        //Crear e insertar el objeto Direcciones
        Direcciones dir = new Direcciones();
        dir.setCalle(calle.trim());
        dir.setNumero(numero.trim());
        dir.setColonia(colonia.trim());

        // regresa el numero de ID
        int idDireccion = direccionesDAO.insertar(dir);

        if (idDireccion <= 0) {
            System.err.println("Error al crear la dirección.");
            return false;
        }

        Donadores donador = new Donadores();
        donador.setNombre(nombre.trim());
        donador.setTipo(tipo.trim());
        donador.setIdDireccion(idDireccion); 
        donador.setTelefono(telefono.trim());
        donador.setCorreo(correo.trim());

        return donadoresDAO.insertar(donador);
    }
    
     // Obtener un donador por ID
    public Donadores obtenerDonador(int idDonador) {
        if (idDonador <= 0) {
            System.err.println("ID de cliente inválido.");
            return null;
        }
        return donadoresDAO.obtenerPorID(idDonador);
    }

    // Obtener todos los donadores
    public List<Donadores> listarDonadores() {
        return donadoresDAO.obtenerTodos();
    }

    // Actualizar donadores con validaciones
    public boolean actualizarDonadores(int idDonador, String nombre, String tipo, String calle, String numero, String colonia, String telefono, String correo) {
        if (idDonador <= 0) {
            System.err.println("ID de donador inválido.");
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del cliente no puede estar vacío.");
            return false;
        }
        
        Direcciones dir = new Direcciones();
        dir.setCalle(calle.trim());
        dir.setNumero(numero.trim());
        dir.setColonia(colonia.trim());

        int idDireccion = direccionesDAO.insertar(dir);

        if (idDireccion <= 0) {
            System.err.println("Error al crear la dirección.");
            return false;
        }
        
        if (nombre == null) nombre = "";
        if (tipo == null) tipo = "";
        if (calle == null) calle = "";
        if (numero == null) numero = "";
        if (colonia == null) colonia = "";
        if (telefono == null) telefono="";
        if (correo == null) correo = "";

        Donadores donador = new Donadores();
        donador.setNombre(nombre.trim());
        donador.setTipo(tipo.trim());
        donador.setIdDireccion(idDireccion); 
        donador.setTelefono(telefono.trim());
        donador.setCorreo(correo.trim());

        return donadoresDAO.actualizar(donador);
    }

    // Eliminar donador con validación de ID
    public boolean eliminarDonador(int idDonador) {
        if (idDonador <= 0) {
            System.err.println("ID de donador inválido.");
            return false;
        }
        return donadoresDAO.eliminar(idDonador);
    }
    
      public DefaultTableModel obtenerTablaDonadores() {
        String[] columnas = {"ID", "NOMBRE", "TIPO" ,"DIRECCIÓN (ID)", "TELEFÓNO", "CORREO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Donadores> lista = donadoresDAO.obtenerTodos();
        for (Donadores d : lista) {
            modelo.addRow(new Object[]{d.getIdDonador(), d.getNombre(), d.getTipo(), d.getIdDireccion(), d.getTelefono(), d.getCorreo()});
        }
        return modelo;
    }
}
