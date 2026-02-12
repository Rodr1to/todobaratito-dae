package pe.com.todobaratito.dao;

import java.util.List;
import pe.com.todobaratito.model.Producto;

public interface ProductoDAO {

    List<Producto> findAll();
    List<Producto> findAllCustom();
    Producto findById(int id);
    boolean add(Producto obj);
    boolean update(Producto obj);
    boolean delete(Producto obj);
    boolean enable(Producto obj);
    boolean disable(Producto obj);

}
