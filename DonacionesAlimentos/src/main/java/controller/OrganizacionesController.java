package controller;

import dao.DireccionesDAO;
import dao.OrganizacionesDAO;
import interfaces.IDireccionesDAO;
import interfaces.IOrganizacionesDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Direcciones;
import model.Organizaciones;

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

        Direcciones dir = new Direcciones();
        dir.setCalle(calle != null ? calle.trim() : "");
        dir.setNumero(numero != null ? numero.trim() : "");
        dir.setColonia(colonia != null ? colonia.trim() : "");

        int idDireccion = direccionDAO.insertar(dir);
        if (idDireccion <= 0) return false;

        Organizaciones org = new Organizaciones();
        org.setNombreOrganizacion(nombreOrg.trim());
        org.setNombreResponsable(nombreResp != null ? nombreResp.trim() : "");
        org.setApellidoPaternoResponsable(apPat != null ? apPat.trim() : "");
        org.setApellidoMaternoResponsable(apMat != null ? apMat.trim() : "");
        org.setIdDireccion(idDireccion);
        org.setTelefono(tel != null ? tel.trim() : "");
        org.setCorreo(correo != null ? correo.trim() : "");

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

        Direcciones dir = new Direcciones();
        dir.setIdDirecciones(idDir);
        dir.setCalle(calle != null ? calle.trim() : "");
        dir.setNumero(numero != null ? numero.trim() : "");
        dir.setColonia(colonia != null ? colonia.trim() : "");

        boolean dirActualizada = direccionDAO.actualizar(dir);

        Organizaciones org = new Organizaciones();
        org.setIdOrganizaciones(idOrg);
        org.setNombreOrganizacion(nombreOrg != null ? nombreOrg.trim() : "");
        org.setNombreResponsable(nombreResp != null ? nombreResp.trim() : "");
        org.setApellidoPaternoResponsable(apPat != null ? apPat.trim() : "");
        org.setApellidoMaternoResponsable(apMat != null ? apMat.trim() : "");
        org.setIdDireccion(idDir);
        org.setTelefono(tel != null ? tel.trim() : "");
        org.setCorreo(correo != null ? correo.trim() : "");

        return dirActualizada && organizacionDAO.actualizar(org);
    }

    public boolean eliminarOrganizacion(int idOrg) {
        if (idOrg <= 0) return false;
        return organizacionDAO.eliminar(idOrg);
    }

    public DefaultTableModel obtenerTablaOrganizaciones() {
        String[] columnas = {"ID", "Organización", "Responsable", "ID Dirección", "Teléfono"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        List<Organizaciones> lista = organizacionDAO.obtenerTodos();
        for (Organizaciones o : lista) {
            String responsable = o.getNombreResponsable() + " "
                    + o.getApellidoPaternoResponsable() + " "
                    + o.getApellidoMaternoResponsable();
            modelo.addRow(new Object[]{
                o.getIdOrganizaciones(),
                o.getNombreOrganizacion(),
                responsable.trim(),
                o.getIdDireccion(),
                o.getTelefono()
            });
        }
        return modelo;
    }
}
