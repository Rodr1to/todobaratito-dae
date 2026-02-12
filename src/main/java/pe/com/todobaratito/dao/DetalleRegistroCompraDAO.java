package pe.com.todobaratito.dao;

import pe.com.todobaratito.model.DetalleRegistroCompra;
import java.util.List;

public interface DetalleRegistroCompraDAO {

    List<DetalleRegistroCompra> findById(int id);
}
