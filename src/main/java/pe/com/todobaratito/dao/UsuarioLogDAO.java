package pe.com.todobaratito.dao;

import pe.com.todobaratito.model.UsuarioLog;
import java.util.List;

public interface UsuarioLogDAO {
    List<UsuarioLog> findAll();
    List<UsuarioLog> findAllCustom();
    UsuarioLog findById(int id);
    boolean add(UsuarioLog obj);
    boolean update(UsuarioLog obj);
    boolean delete(UsuarioLog obj);
    boolean enable(UsuarioLog obj);
    boolean disable(UsuarioLog obj);
}

