/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.Donadores;

/**
 *
 * @author marki
 */
public interface IDonadoresDAO {
    boolean insertar(Donadores donador);
    Donadores obtenerPorID(int idDonador);
    List<Donadores> obtenerTodos();
    boolean actualizar(Donadores donador);
    boolean eliminar(int idDonador);
}
