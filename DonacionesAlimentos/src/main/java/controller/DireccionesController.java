/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.DireccionesDAO;
import interfaces.IDireccionesDAO;
import model.Direcciones;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author marki
 */
public class DireccionesController {
    private final IDireccionesDAO direccionDAO;

    public DireccionesController() {
        this.direccionDAO = new DireccionesDAO();
    }

    public int agregarDireccion(String calle, String numero, String colonia) {
        if (calle == null || calle.trim().isEmpty()){
            System.err.println("La calle es obligatoria");
            return 0;
        }

        Direcciones dir = new Direcciones();
        dir.setCalle(calle.trim());
        dir.setNumero(numero.trim());
        dir.setColonia(colonia.trim());

        return direccionDAO.insertar(dir);
    }
    
    
    public Direcciones buscarPorDonador(int idDonador) {
        if (idDonador <= 0) return null;
        return direccionDAO.obtenerPorDonador(idDonador);
    }

    
    public Direcciones buscarPorOrganizacion(int idOrganizacion) {
        if (idOrganizacion <= 0) return null;
        return direccionDAO.obtenerPorOrganizacion(idOrganizacion);
    }

    
    public boolean actualizarDireccion(int id, String calle, String numero, String colonia) {
        if (id <= 0 || calle == null || calle.trim().isEmpty()) return false;

        Direcciones dir = new Direcciones();
        dir.setIdDirecciones(id); 
        dir.setCalle(calle.trim());
        dir.setNumero(numero.trim());
        dir.setColonia(colonia.trim());

        return direccionDAO.actualizar(dir);
    }

    
    public boolean eliminarDireccion(int id) {
        if (id <= 0) return false;
        return direccionDAO.eliminar(id);
    }

    public DefaultTableModel obtenerTablaDirecciones() {
        String[] columnas = {"ID", "CALLE", "NÚMERO", "COLONIA"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        
        
        List<Direcciones> lista = direccionDAO.obtenerTodos(); 
        for (Direcciones d : lista) {
            modelo.addRow(new Object[]{
                d.getIdDirecciones(),
                d.getCalle(),
                d.getNumero(),
                d.getColonia()
            });
        }
        return modelo;
    }
}
