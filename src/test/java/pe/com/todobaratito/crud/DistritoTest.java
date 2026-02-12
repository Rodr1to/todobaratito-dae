package pe.com.todobaratito.crud;

import java.util.List;
import pe.com.todobaratito.dao.DistritoDAO;
import pe.com.todobaratito.dao.impl.DistritoDAOImpl;
import pe.com.todobaratito.model.Distrito;

public class DistritoTest {
    public static void main(String[] args) {
        DistritoDAO dao = new DistritoDAOImpl();

        String nombreTest = "Distrito Test " + System.currentTimeMillis();
        int idGenerado = 0;

        // REGISTRAR
        System.out.println("***************** 1. Registrar Distrito *****************");
        Distrito obj = new Distrito();
        obj.setNombre(nombreTest);
        obj.setEstado(true);
        System.out.println("Registro exitoso: " + dao.add(obj));

        // RECUPERAR ID
        for(Distrito d : dao.findAll()){
            if(d.getNombre().equals(nombreTest)){
                idGenerado = d.getCodigo();
                break;
            }
        }
        System.out.println(">>> ID TEST GENERADO: " + idGenerado + " <<<");


        // MOSTRAR TODOS
        System.out.println("***************** 2. Mostrar Todos *****************");
        for(Distrito d : dao.findAll()){
            System.out.println(d.getCodigo()+" - "+d.getNombre());
        }

        // MOSTRAR HABILITADOS
        System.out.println("***************** 3. Mostrar Habilitados *****************");
        for(Distrito d : dao.findAllCustom()){
            System.out.println(d.getCodigo()+" - "+d.getNombre());
        }

        if(idGenerado != 0) {
            // BUSCAR
            System.out.println("***************** 4. Buscar Distrito *****************");
            Distrito d = dao.findById(idGenerado);
            System.out.println("Encontrado: " + d.getNombre());

            // ACTUALIZAR
            System.out.println("***************** 5. Actualizar Distrito *****************");
            d.setNombre(nombreTest + " UPD");
            System.out.println("Actualizacion exitosa: " + dao.update(d));

            // ELIMINAR (Lógico)
            System.out.println("***************** 6. Eliminar Distrito *****************");
            System.out.println("Eliminacion exitosa: " + dao.delete(d));

            // HABILITAR
            System.out.println("***************** 7. Habilitar Distrito *****************");
            System.out.println("Habilitacion exitosa: " + dao.enable(d));

            // DESHABILITAR
            System.out.println("***************** 8. Deshabilitar Distrito *****************");
            System.out.println("Deshabilitacion exitosa: " + dao.disable(d));
        }
    }
}
