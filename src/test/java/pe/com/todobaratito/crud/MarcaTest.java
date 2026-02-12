package pe.com.todobaratito.crud;

import java.util.List;
import pe.com.todobaratito.dao.MarcaDAO;
import pe.com.todobaratito.dao.impl.MarcaDAOImpl;
import pe.com.todobaratito.model.Marca;

public class MarcaTest {
    public static void main(String[] args) {
        MarcaDAO dao = new MarcaDAOImpl();
        String nombreTest = "Marca Test " + System.currentTimeMillis();
        int idGenerado = 0;

        System.out.println("***************** 1. Registrar Marca *****************");
        Marca obj = new Marca();
        obj.setNombre(nombreTest);
        obj.setEstado(true);
        System.out.println("Registro exitoso: " + dao.add(obj));

        // RECUPERAR ID
        for(Marca m : dao.findAll()){
            if(m.getNombre().equals(nombreTest)){
                idGenerado = m.getCodigo();
                break;
            }
        }
        System.out.println(">>> ID TEST GENERADO: " + idGenerado + " <<<");

        System.out.println("***************** 2. Mostrar Marca Todo *****************");
        for (Marca m : dao.findAll()) System.out.println(m.getCodigo() + " - " + m.getNombre());

        System.out.println("***************** 3. Mostrar Marca Habilitados *****************");
        for (Marca m : dao.findAllCustom()) System.out.println(m.getCodigo() + " - " + m.getNombre());

        if(idGenerado != 0){
            System.out.println("***************** 4. Buscar Marca *****************");
            Marca objEncontrado = dao.findById(idGenerado);
            System.out.println("Encontrado: " + objEncontrado.getNombre());

            System.out.println("***************** 5. Actualizar Marca *****************");
            objEncontrado.setNombre(nombreTest + " UPD");
            System.out.println("Actualizacion exitosa: " + dao.update(objEncontrado));

            System.out.println("***************** 6. Eliminar Marca *****************");
            System.out.println("Eliminacion exitosa: " + dao.delete(objEncontrado));

            System.out.println("***************** 7. Habilitar Marca *****************");
            System.out.println("Habilitacion exitosa: " + dao.enable(objEncontrado));

            System.out.println("***************** 8. Deshabilitar Marca *****************");
            System.out.println("Deshabilitacion exitosa: " + dao.disable(objEncontrado));
        }
    }
}
