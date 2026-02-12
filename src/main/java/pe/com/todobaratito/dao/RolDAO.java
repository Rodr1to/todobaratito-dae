package pe.com.todobaratito.dao;

import java.util.List;
import pe.com.todobaratito.model.Rol;

public interface RolDAO {
    List<Rol> findAll();
    List<Rol> findAllCustom();
    Rol findById(int id);
    boolean add(Rol obj);
    boolean update(Rol obj);
    boolean delete(Rol obj);
    boolean enable(Rol obj);
    boolean disable(Rol obj);
}
