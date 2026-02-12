package pe.com.todobaratito.dao;

import java.util.List;
import pe.com.todobaratito.model.Empleado;

public interface EmpleadoDAO {
    List<Empleado> findAll();
    List<Empleado> findAllCustom();
    Empleado findById(int id);
    boolean add(Empleado obj);
    boolean update(Empleado obj);
    boolean delete(Empleado obj);
    boolean enable(Empleado obj);
    boolean disable(Empleado obj);
    List<Empleado> login(Empleado obj);
}
