package pe.com.todobaratito.dao;

import java.util.List;
import pe.com.todobaratito.model.Marca;

public interface MarcaDAO {

    List<Marca> findAll();
    List<Marca> findAllCustom();
    Marca findById(int id);
    boolean add(Marca obj);
    boolean update(Marca obj);
    boolean delete(Marca obj);
    boolean enable(Marca obj);
    boolean disable(Marca obj);
}
