package pe.com.todobaratito.crud;

import java.util.List;
import pe.com.todobaratito.dao.SexoDAO;
import pe.com.todobaratito.dao.impl.SexoDAOImpl;
import pe.com.todobaratito.model.Sexo;

public class SexoTest {
    public static void main(String[] args) {
        SexoDAO dao = new SexoDAOImpl();
        String nombreTest = "Sexo Prueba " + System.currentTimeMillis();
        int idGenerado = 0;

        // REGISTRAR
        System.out.println("***************** 1. Registrar Sexo *****************");
        Sexo obj = new Sexo();
        obj.setNombre(nombreTest);
        obj.setEstado(true);
        System.out.println("Registro exitoso: " + dao.add(obj));

        // RECUPERAR ID
        for(Sexo s : dao.findAll()){
            if(s.getNombre().equals(nombreTest)){
                idGenerado = s.getCodigo();
                break;
            }
        }
        System.out.println(">>> ID TEST GENERADO: " + idGenerado + " <<<");

        // MOSTRAR TODOS
        System.out.println("***************** 2. Mostrar Todos *****************");
        for(Sexo s : dao.findAll()) System.out.println(s.getCodigo()+" - "+s.getNombre());

        // MOSTRAR HABILITADOS
        System.out.println("***************** 3. Mostrar Habilitados *****************");
        for(Sexo s : dao.findAllCustom()) System.out.println(s.getCodigo()+" - "+s.getNombre());

        if(idGenerado != 0){
            // BUSCAR
            System.out.println("***************** 4. Buscar Sexo *****************");
            Sexo enc = dao.findById(idGenerado);
            System.out.println("Encontrado: " + enc.getNombre());

            // ACTUALIZAR
            System.out.println("***************** 5. Actualizar Sexo *****************");
            enc.setNombre(nombreTest + " UPD");
            System.out.println("Actualizacion: " + dao.update(enc));

            // ELIMINAR
            System.out.println("***************** 6. Eliminar Sexo *****************");
            System.out.println("Eliminacion: " + dao.delete(enc));

            // HABILITAR
            System.out.println("***************** 7. Habilitar Sexo *****************");
            System.out.println("Habilitacion: " + dao.enable(enc));

            // DESHABILITAR
            System.out.println("***************** 8. Deshabilitar Sexo *****************");
            System.out.println("Deshabilitacion: " + dao.disable(enc));
        }
    }
}
