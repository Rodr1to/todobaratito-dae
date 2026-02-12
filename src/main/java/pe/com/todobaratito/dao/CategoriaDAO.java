package pe.com.todobaratito.dao;

import java.util.List;
import pe.com.todobaratito.model.Categoria;

public interface CategoriaDAO {
    //DEFINIR LAS OPERACIONES O METODOS A TRABAJAR
    //listar todos
    List<Categoria> findAll();
    //listar solo los habilitadis
    List<Categoria> findAllCustom();
    //buscar por codigo
    Categoria findById(int id);
    //registrar
    boolean add(Categoria obj);
    //actualizar
    boolean update(Categoria obj);
    //eliminar
    boolean delete(Categoria obj);
    //habilitar
    boolean enable(Categoria obj);
    //deshabilitar
    boolean disable(Categoria obj);
}

