/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.Direcciones;

/**
 *
 * @author marki
 */
public interface IDireccionesDAO {
    int insertar(Direcciones direccion);
    Direcciones obtenerPorID(int idDireccion);
    Direcciones obtenerPorDonador(int idDonador);
    Direcciones obtenerPorOrganizacion(int idOrganizacion);
    List<Direcciones> obtenerTodos();
    boolean actualizar(Direcciones direccion);
    boolean eliminar(int idDirecciones);
}
