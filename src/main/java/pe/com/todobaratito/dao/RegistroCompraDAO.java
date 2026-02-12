package pe.com.todobaratito.dao;

import java.util.List;

import pe.com.todobaratito.model.DetalleRegistroCompra;
import pe.com.todobaratito.model.RegistroCompra;

public interface RegistroCompraDAO {
    List<RegistroCompra> findAll();
    List<RegistroCompra> findAllCustom();
    RegistroCompra findById(int id);
    boolean add(RegistroCompra obj);
    boolean enable(RegistroCompra obj);
    boolean disable(RegistroCompra obj);
    int setNumber();
    int getNumber();
    boolean addDetails(DetalleRegistroCompra obj);
}
