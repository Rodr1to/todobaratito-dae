package pe.com.todobaratito.crud;

import java.util.List;
import pe.com.todobaratito.dao.RolDAO;
import pe.com.todobaratito.dao.impl.RolDAOImpl;
import pe.com.todobaratito.model.Rol;

public class RolTest {
    public static void main(String[] args) {
        RolDAO dao = new RolDAOImpl();
        String nombreTest = "Rol Test " + System.currentTimeMillis();
        int idGenerado = 0;

        // REGISTRAR
        System.out.println("***************** 1. Registrar Rol *****************");
        Rol obj = new Rol();
        obj.setNombre(nombreTest);
        obj.setEstado(true);
        System.out.println("Registro exitoso: " + dao.add(obj));

        // RECUPERAR ID
        for(Rol r : dao.findAll()){
            if(r.getNombre().equals(nombreTest)){
                idGenerado = r.getCodigo();
                break;
            }
        }
        System.out.println(">>> ID TEST GENERADO: " + idGenerado + " <<<");

        // MOSTRAR TODOS
        System.out.println("***************** 2. Mostrar Todos *****************");
        for(Rol r : dao.findAll()) System.out.println(r.getCodigo()+" - "+r.getNombre());

        // MOSTRAR HABILITADOS
        System.out.println("***************** 3. Mostrar Habilitados *****************");
        for(Rol r : dao.findAllCustom()) System.out.println(r.getCodigo()+" - "+r.getNombre());

        if(idGenerado != 0){
            // BUSCAR
            System.out.println("***************** 4. Buscar Rol *****************");
            Rol enc = dao.findById(idGenerado);
            System.out.println("Encontrado: " + enc.getNombre());

            // ACTUALIZAR
            System.out.println("***************** 5. Actualizar Rol *****************");
            enc.setNombre(nombreTest + " UPD");
            System.out.println("Actualizacion: " + dao.update(enc));

            // ELIMINAR
            System.out.println("***************** 6. Eliminar Rol *****************");
            System.out.println("Eliminacion: " + dao.delete(enc));

            // HABILITAR
            System.out.println("***************** 7. Habilitar Rol *****************");
            System.out.println("Habilitacion: " + dao.enable(enc));

            // DESHABILITAR
            System.out.println("***************** 8. Deshabilitar Rol *****************");
            System.out.println("Deshabilitacion: " + dao.disable(enc));
        }
    }
}
