package pe.com.todobaratito.crud;

import java.util.List;
import pe.com.todobaratito.dao.ProveedorDAO;
import pe.com.todobaratito.dao.impl.ProveedorDAOImpl;
import pe.com.todobaratito.model.Distrito;
import pe.com.todobaratito.model.Proveedor;

public class ProveedorTest {
    public static void main(String[] args) {
        ProveedorDAO dao = new ProveedorDAOImpl();

        long tiempo = System.currentTimeMillis();
        String rucUnico = String.valueOf(tiempo).substring(0, 11);
        String nombreTest = "Prov Test " + tiempo;
        int idGenerado = 0;

        System.out.println("***************** 1. Registrar Proveedor *****************");
        Proveedor obj = new Proveedor();
        obj.setNombre(nombreTest);
        obj.setRepresentante("Rep Test");
        obj.setRuc(rucUnico);
        obj.setDireccion("Dir Test");
        obj.setTelefono("555");
        obj.setCelular("999");
        obj.setCorreo("test@test.com");
        obj.setContacto("Contacto Test");
        obj.setEstado(true);

        Distrito d = new Distrito(); d.setCodigo(1); obj.setDistrito(d);

        System.out.println("Registro exitoso: " + dao.add(obj));

        // RECUPERAR ID
        for(Proveedor p : dao.findAll()){
            if(p.getRuc().equals(rucUnico)){
                idGenerado = p.getCodigo();
                break;
            }
        }
        System.out.println(">>> ID TEST GENERADO: " + idGenerado + " <<<");

        System.out.println("***************** 2. Mostrar Todo *****************");
        for(Proveedor p : dao.findAll()) System.out.println(p.getNombre());

        System.out.println("***************** 3. Mostrar Habilitados *****************");
        for(Proveedor p : dao.findAllCustom()) System.out.println(p.getNombre());

        if(idGenerado != 0){
            System.out.println("***************** 4. Buscar Proveedor *****************");
            Proveedor found = dao.findById(idGenerado);
            System.out.println("Encontrado: " + found.getNombre());

            System.out.println("***************** 5. Actualizar Proveedor *****************");
            found.setNombre(nombreTest + " UPD");
            System.out.println("Actualizacion exitosa: " + dao.update(found));

            System.out.println("***************** 6. Eliminar Proveedor *****************");
            System.out.println("Eliminacion exitosa: " + dao.delete(found));

            System.out.println("***************** 7. Habilitar Proveedor *****************");
            System.out.println("Habilitacion exitosa: " + dao.enable(found));

            System.out.println("***************** 8. Deshabilitar Proveedor *****************");
            System.out.println("Deshabilitacion exitosa: " + dao.disable(found));
        }
    }
}
