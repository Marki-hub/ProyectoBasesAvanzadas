/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.Organizaciones;

/**
 *
 * @author marki
 */
public interface IOrganizacionesDAO {
    boolean insertar(Organizaciones organizacion);
    Organizaciones obtenerPorID(int idOrganizacion);
    List<Organizaciones> obtenerTodos();
    boolean actualizar(Organizaciones organizacion);
    boolean eliminar(int idOrganizacion);
}
