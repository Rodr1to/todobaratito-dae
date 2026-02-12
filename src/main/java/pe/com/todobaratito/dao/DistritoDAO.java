package pe.com.todobaratito.dao;

import java.util.List;
import pe.com.todobaratito.model.Distrito;

public interface DistritoDAO {
    List<Distrito> findAll();
    List<Distrito> findAllCustom();
    Distrito findById(int id);
    boolean add(Distrito obj);
    boolean update(Distrito obj);
    boolean delete(Distrito obj);
    boolean enable(Distrito obj);
    boolean disable(Distrito obj);
}
