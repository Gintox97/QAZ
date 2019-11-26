/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author Miste
 */
public interface CRUD<T> {
       public boolean crear(T t);
    public boolean editar(T t);
    public boolean eliminar(int id);
    public List<T> obtenerTodos();
    public T obtenerId(int id);
}
