package pe.com.todobaratito.dao;

import java.util.List;
import pe.com.todobaratito.model.TipoDocumento;

public interface TipoDocumentoDAO {
    List<TipoDocumento> findAll();
    List<TipoDocumento> findAllCustom();
    TipoDocumento findById(int id);
    boolean add(TipoDocumento obj);
    boolean update(TipoDocumento obj);
    boolean delete(TipoDocumento obj);
    boolean enable(TipoDocumento obj);
    boolean disable(TipoDocumento obj);
}
