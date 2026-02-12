package pe.com.todobaratito.crud;

import pe.com.todobaratito.dao.TipoDocumentoDAO;
import pe.com.todobaratito.dao.impl.TipoDocumentoDAOImpl;
import pe.com.todobaratito.model.TipoDocumento;

public class TipoDocumentoTest {
    public static void main(String[] args) {
        TipoDocumentoDAO dao = new TipoDocumentoDAOImpl();
        String nombreTest = "Doc Test " + System.currentTimeMillis();
        int idGenerado = 0;

        // REGISTRAR
        System.out.println("***************** 1. Registrar TipoDoc *****************");
        TipoDocumento obj = new TipoDocumento();
        obj.setNombre(nombreTest);
        obj.setEstado(true);
        System.out.println("Registro exitoso: " + dao.add(obj));

        // RECUPERAR ID
        for(TipoDocumento td : dao.findAll()){
            if(td.getNombre().equals(nombreTest)){
                idGenerado = td.getCodigo();
                break;
            }
        }
        System.out.println(">>> ID TEST GENERADO: " + idGenerado + " <<<");

        // MOSTRAR
        System.out.println("***************** 2. Mostrar Todos *****************");
        for(TipoDocumento td : dao.findAll()) System.out.println(td.getCodigo()+" - "+td.getNombre());

        System.out.println("***************** 3. Mostrar Habilitados *****************");
        for(TipoDocumento td : dao.findAllCustom()) System.out.println(td.getCodigo()+" - "+td.getNombre());

        if(idGenerado != 0){
            // BUSCAR
            System.out.println("***************** 4. Buscar TipoDoc *****************");
            TipoDocumento enc = dao.findById(idGenerado);
            System.out.println("Encontrado: " + enc.getNombre());

            // ACTUALIZAR
            System.out.println("***************** 5. Actualizar TipoDoc *****************");
            enc.setNombre(nombreTest + " UPD");
            System.out.println("Actualizacion: " + dao.update(enc));

            // ELIMINAR
            System.out.println("***************** 6. Eliminar TipoDoc *****************");
            System.out.println("Eliminacion: " + dao.delete(enc));

            // HABILITAR
            System.out.println("***************** 7. Habilitar TipoDoc *****************");
            System.out.println("Habilitacion: " + dao.enable(enc));

            // DESHABILITAR
            System.out.println("***************** 8. Deshabilitar TipoDoc *****************");
            System.out.println("Deshabilitacion: " + dao.disable(enc));
        }
    }
}