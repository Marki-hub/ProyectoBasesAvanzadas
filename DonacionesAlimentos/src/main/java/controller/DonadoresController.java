package controller;

import dao.DireccionesDAO;
import dao.DonadoresDAO;
import interfaces.IDireccionesDAO;
import interfaces.IDonadoresDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Direcciones;
import model.Donadores;

public class DonadoresController {
    private final IDonadoresDAO donadoresDAO;
    private final IDireccionesDAO direccionesDAO;

    public DonadoresController() {
        this.donadoresDAO = new DonadoresDAO();
        this.direccionesDAO = new DireccionesDAO();
    }

    public boolean agregarDonador(String nombre, String tipo, String calle, String numero, String colonia, String telefono, String correo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío.");
            return false;
        }

        Direcciones dir = new Direcciones();
        dir.setCalle(calle != null ? calle.trim() : "");
        dir.setNumero(numero != null ? numero.trim() : "");
        dir.setColonia(colonia != null ? colonia.trim() : "");

        int idDireccion = direccionesDAO.insertar(dir);

        if (idDireccion <= 0) {
            System.err.println("Error al crear la dirección.");
            return false;
        }

        Donadores donador = new Donadores();
        donador.setNombre(nombre.trim());
        donador.setTipo(tipo != null ? tipo.trim() : "");
        donador.setIdDireccion(idDireccion);
        donador.setTelefono(telefono != null ? telefono.trim() : "");
        donador.setCorreo(correo != null ? correo.trim() : "");

        return donadoresDAO.insertar(donador);
    }

    public Donadores obtenerDonador(int idDonador) {
        if (idDonador <= 0) {
            System.err.println("ID de donador inválido.");
            return null;
        }
        return donadoresDAO.obtenerPorID(idDonador);
    }

    public List<Donadores> listarDonadores() {
        return donadoresDAO.obtenerTodos();
    }

    public boolean actualizarDonadores(int idDonador, String nombre, String tipo, String calle, String numero, String colonia, String telefono, String correo) {
        if (idDonador <= 0) {
            System.err.println("ID de donador inválido.");
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío.");
            return false;
        }

        Direcciones dir = new Direcciones();
        dir.setCalle(calle != null ? calle.trim() : "");
        dir.setNumero(numero != null ? numero.trim() : "");
        dir.setColonia(colonia != null ? colonia.trim() : "");

        int idDireccion = direccionesDAO.insertar(dir);

        if (idDireccion <= 0) {
            System.err.println("Error al crear la dirección.");
            return false;
        }

        Donadores donador = new Donadores();
        donador.setIdDonador(idDonador);
        donador.setNombre(nombre.trim());
        donador.setTipo(tipo != null ? tipo.trim() : "");
        donador.setIdDireccion(idDireccion);
        donador.setTelefono(telefono != null ? telefono.trim() : "");
        donador.setCorreo(correo != null ? correo.trim() : "");

        return donadoresDAO.actualizar(donador);
    }

    public boolean eliminarDonador(int idDonador) {
        if (idDonador <= 0) {
            System.err.println("ID de donador inválido.");
            return false;
        }
        return donadoresDAO.eliminar(idDonador);
    }

    public DefaultTableModel obtenerTablaDonadores() {
        String[] columnas = {"ID", "Nombre", "Tipo", "ID Dirección", "Teléfono", "Correo"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        for (Donadores d : donadoresDAO.obtenerTodos()) {
            modelo.addRow(new Object[]{
                d.getIdDonador(), d.getNombre(), d.getTipo(),
                d.getIdDireccion(), d.getTelefono(), d.getCorreo()
            });
        }
        return modelo;
    }
}
