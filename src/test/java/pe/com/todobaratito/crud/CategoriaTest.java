package pe.com.todobaratito.crud;

import java.util.List;
import pe.com.todobaratito.dao.CategoriaDAO;
import pe.com.todobaratito.dao.impl.CategoriaDAOImpl;
import pe.com.todobaratito.model.Categoria;

public class CategoriaTest {

    public static void main(String[] args) {
        // IMPLEMENTAMOS EL DAO
        CategoriaDAO dao=new CategoriaDAOImpl();

        // REGISTRAR
        System.out.println("***************** Registrar Categoria *****************");
        Categoria obj=new Categoria();
        obj.setNombre("Prueba registro categoria 1");
        obj.setEstado(true);
        boolean res1=dao.add(obj);
        System.out.println("Registro 1 exitoso: "+res1);

        obj.setNombre("Prueba registro categoria 2");
        obj.setEstado(false);
        boolean res2=dao.add(obj);
        System.out.println("Registro 2 exitoso: "+res2);

        // MOSTRAR TODOS
        System.out.println("***************** Mostrar Categoria Todo *****************");
        List<Categoria> lista=dao.findAll();
        for(Categoria c:lista){
            System.out.println(c.getCodigo()+" - "+c.getNombre()+" - "+c.isEstado());
        }

        // MOSTRAR HABILITADOS
        System.out.println("***************** Mostrar Categoria Habilitados *****************");
        List<Categoria> lista2=dao.findAllCustom();
        for(Categoria c:lista2){
            System.out.println(c.getCodigo()+" - "+c.getNombre()+" - "+c.isEstado());
        }

        // BUSCAR
        System.out.println("***************** Buscar Categoria *****************");
        int codigo=1;
        Categoria objcat=dao.findById(codigo);
        System.out.println(objcat.getCodigo()+" - "+objcat.getNombre()+" - "+objcat.isEstado());


        // ACTUALIZAR
        System.out.println("***************** Actualizar Categoria *****************");
        Categoria obj2=new Categoria();
        obj2.setCodigo(3);
        obj2.setNombre("Prueba Actualizacion categoria");
        obj2.setEstado(true);
        boolean res3=dao.update(obj2);
        System.out.println("Actualizacion 1 exitoso: "+res3);
        codigo=3;
        objcat=dao.findById(codigo);
        System.out.println(objcat.getCodigo()+" - "+objcat.getNombre()+" - "+objcat.isEstado());


        // ELIMINAR
        System.out.println("***************** Eliminar Categoria *****************");
        Categoria obj3=new Categoria();
        obj3.setCodigo(3);
        boolean res4=dao.delete(obj3);
        System.out.println("Eliminacion 1 exitoso: "+res4);
        codigo=3;
        objcat=dao.findById(codigo);
        System.out.println(objcat.getCodigo()+" - "+objcat.getNombre()+" - "+objcat.isEstado());


        // HABILITAR
        System.out.println("***************** Habilitar Categoria *****************");
        Categoria obj4=new Categoria();
        obj4.setCodigo(3);
        boolean res5=dao.enable(obj4);
        System.out.println("Habilitacion 1 exitoso: "+res5);
        codigo=3;
        objcat=dao.findById(codigo);
        System.out.println(objcat.getCodigo()+" - "+objcat.getNombre()+" - "+objcat.isEstado());

        // DESHABILITAR
        System.out.println("***************** Deshabilitar Categoria *****************");
        Categoria obj5=new Categoria();
        obj5.setCodigo(3);
        boolean res6=dao.disable(obj5);
        System.out.println("Deshabilitacion 1 exitoso: "+res6);
        codigo=3;
        objcat=dao.findById(codigo);
        System.out.println(objcat.getCodigo()+" - "+objcat.getNombre()+" - "+objcat.isEstado());

    }

}

