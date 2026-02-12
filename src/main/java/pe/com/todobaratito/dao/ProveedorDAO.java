package pe.com.todobaratito.dao;

import java.util.List;
import pe.com.todobaratito.model.Proveedor;

public interface ProveedorDAO {
    List<Proveedor> findAll();
    List<Proveedor> findAllCustom();
    Proveedor findById(int id);
    boolean add(Proveedor obj);
    boolean update(Proveedor obj);
    boolean delete(Proveedor obj);
    boolean enable(Proveedor obj);
    boolean disable(Proveedor obj);
}
