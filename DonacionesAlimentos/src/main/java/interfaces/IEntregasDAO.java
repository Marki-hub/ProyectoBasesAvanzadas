/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.Entregas;

/**
 *
 * @author MoriTejo
 */
public interface IEntregasDAO {
    boolean insertar(Entregas entrega);
    Entregas obtenerPorID(int idEntrega);
    List<Entregas> obtenerTodos();
    Entregas obtenerPorOrganizacion(int idOrganizacion);
    boolean actualizar(Entregas entrega);
    boolean eliminar(int idEntrega);
}
