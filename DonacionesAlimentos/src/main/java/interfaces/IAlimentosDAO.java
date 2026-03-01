/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.Alimentos;

/**
 *
 * @author MoriTejo
 */
public interface IAlimentosDAO {
    boolean insertar(Alimentos alimento);
    Alimentos buscarPorId(int idAlimento);
    List<Alimentos> obtenerTodos();
    boolean actualizar(Alimentos alimento);
    boolean eliminar(int idAlimento);
    
}
