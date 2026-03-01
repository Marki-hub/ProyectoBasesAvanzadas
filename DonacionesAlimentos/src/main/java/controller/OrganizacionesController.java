/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DireccionesDAO;
import dao.OrganizacionesDAO;
import interfaces.IDireccionesDAO;
import interfaces.IOrganizacionesDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Direcciones;
import model.Organizaciones;

/**
 *
 * @author marki
 */
public class OrganizacionesController {
    private final IOrganizacionesDAO organizacionDAO;
    private final IDireccionesDAO direccionDAO;

    public OrganizacionesController() {
        this.organizacionDAO = new OrganizacionesDAO();
        this.direccionDAO = new DireccionesDAO();
    }

    public boolean agregarOrganizacion(String nombreOrg, String nombreResp, String apPat, String apMat, 
                                      String calle, String numero, String colonia, String tel, String correo) {
        
        if (nombreOrg == null || nombreOrg.trim().isEmpty()) {
            System.err.println("El nombre de la organización es obligatorio.");
            return false;
        }

        // insertamos la dirección para obtener su ID (FK)
        Direcciones dir = new Direcciones();
        dir.setCalle(calle.trim());
        dir.setNumero(numero.trim());
        dir.setColonia(colonia.trim());

        int idDireccionGenerado = direccionDAO.insertar(dir);

        if (idDireccionGenerado <= 0) return false;

        
        Organizaciones org = new Organizaciones();
        org.setNombreOrganizacion(nombreOrg.trim());
        org.setNombreResponsable(nombreResp.trim());
        org.setApellidoPaternoResponsable(apPat.trim());
        org.setApellidoMaternoResponsable(apMat.trim());
        org.setIdDireccion(idDireccionGenerado); // Usamos la FK
        org.setTelefono(tel.trim());
        org.setCorreo(correo.trim());

        return organizacionDAO.insertar(org);
    }
    
    
    public Organizaciones obtenerPorId(int id) {
        if (id <= 0) return null;
        return organizacionDAO.obtenerPorID(id);
    }

    
    public boolean actualizarOrganizacion(int idOrg, int idDir, String nombreOrg, String nombreResp, 
                                          String apPat, String apMat, String calle, String numero, 
                                          String colonia, String tel, String correo) {
        
        if (idOrg <= 0 || idDir <= 0) return false;

        // 1. Actualizar Dirección
        Direcciones dir = new Direcciones();
        dir.setIdDirecciones(idDir);
        dir.setCalle(calle.trim());
        dir.setNumero(numero.trim());
        dir.setColonia(colonia.trim());
        
        boolean dirActualizada = direccionDAO.actualizar(dir);

        // 2. Actualizar Organización
        Organizaciones org = new Organizaciones();
        org.setIdOrganizaciones(idOrg);
        org.setNombreOrganizacion(nombreOrg.trim());
        org.setNombreResponsable(nombreResp.trim());
        org.setApellidoPaternoResponsable(apPat.trim());
        org.setApellidoMaternoResponsable(apMat.trim());
        org.setIdDireccion(idDir);
        org.setTelefono(tel.trim());
        org.setCorreo(correo.trim());

        return dirActualizada && organizacionDAO.actualizar(org);
    }

    /**
     * Elimina una organización. 
     * Nota: Dependiendo de tu lógica, podrías querer eliminar la dirección también.
     */
    public boolean eliminarOrganizacion(int idOrg) {
        if (idOrg <= 0) return false;
        return organizacionDAO.eliminar(idOrg);
    }
    
    public DefaultTableModel obtenerTablaOrganizaciones() {
        String[] columnas = {"ID", "ORGANIZACIÓN", "RESPONSABLE", "ID_DIR", "TELÉFONO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        
        List<Organizaciones> lista = organizacionDAO.obtenerTodos();
        for (Organizaciones o : lista) {
            String responsableFull = o.getNombreResponsable() + " " + o.getApellidoPaternoResponsable()+ " " + o.getApellidoMaternoResponsable();
            modelo.addRow(new Object[]{
                o.getIdOrganizaciones(),
                o.getNombreOrganizacion(),
                responsableFull,
                o.getIdDireccion(),
                o.getTelefono()
            });
        }
        return modelo;
    }
}
