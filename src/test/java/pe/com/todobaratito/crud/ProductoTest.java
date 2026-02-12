package pe.com.todobaratito.crud;

import java.util.Date;
import java.util.List;
import pe.com.todobaratito.dao.ProductoDAO;
import pe.com.todobaratito.dao.impl.ProductoDAOImpl;
import pe.com.todobaratito.model.Categoria;
import pe.com.todobaratito.model.Marca;
import pe.com.todobaratito.model.Producto;
import pe.com.todobaratito.model.Proveedor;

public class ProductoTest {
    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAOImpl();

        String nombreUnico = "Producto Test " + System.currentTimeMillis();
        int idPrueba = 0;

        // REGISTRAR
        System.out.println("***************** 1. Registrar Producto *****************");
        Producto p = new Producto();
        p.setNombre(nombreUnico); // Usamos el nombre único
        p.setDescripcion("Descripcion de prueba generada automáticamente");
        p.setPrecio(100.50);
        p.setCantidad(50);
        p.setStockminimo(5);
        p.setFechaingreso(new Date());
        p.setEstado(true);

        Marca m = new Marca(); m.setCodigo(1); p.setMarca(m);
        Categoria c = new Categoria(); c.setCodigo(1); p.setCategoria(c);
        Proveedor pv = new Proveedor(); pv.setCodigo(1); p.setProveedor(pv);

        boolean res = dao.add(p);
        System.out.println("Registro exitoso: " + res);


        // Buscamos el producto
        List<Producto> listaTemp = dao.findAll();
        for(Producto prod : listaTemp){
            if(prod.getNombre().equals(nombreUnico)){
                idPrueba = prod.getCodigo();
                break;
            }
        }

        if (idPrueba == 0) {
            System.out.println("ERROR CRÍTICO: No se pudo recuperar el ID del producto de prueba. Abortando.");
            return;
        } else {
            System.out.println(">>> ID AUTOGENERADO PARA PRUEBAS: " + idPrueba + " <<<");
        }


        // MOSTRAR TODOS
        System.out.println("***************** 2. Mostrar Productos Todo *****************");
        List<Producto> lista = dao.findAll();
        for(Producto prod : lista){
            System.out.println("ID: " + prod.getCodigo() + " | " + prod.getNombre() +
                    " | Marca: " + prod.getMarca().getNombre() +
                    " | Est: " + prod.isEstado());
        }

        // MOSTRAR HABILITADOS
        System.out.println("***************** 3. Mostrar Productos Habilitados *****************");
        for(Producto prod : dao.findAllCustom()){
            System.out.println("ID: " + prod.getCodigo() + " | " + prod.getNombre());
        }

        // BUSCAR
        System.out.println("***************** 4. Buscar Producto (ID: " + idPrueba + ") *****************");
        Producto encontrado = dao.findById(idPrueba);
        if(encontrado.getCodigo() != 0){
            System.out.println("Encontrado: " + encontrado.getNombre() + " | Precio: " + encontrado.getPrecio());
        } else {
            System.out.println("Producto no encontrado");
        }

        // ACTUALIZAR
        System.out.println("***************** 5. Actualizar Producto (ID: " + idPrueba + ") *****************");
        Producto pUpdate = new Producto();
        pUpdate.setCodigo(idPrueba);
        pUpdate.setNombre(nombreUnico + " EDITADO");
        pUpdate.setDescripcion("Descripcion editada");
        pUpdate.setPrecio(299.99);
        pUpdate.setCantidad(20);
        pUpdate.setStockminimo(2);
        pUpdate.setFechaingreso(new Date());
        pUpdate.setEstado(true);

        pUpdate.setMarca(m);
        pUpdate.setCategoria(c);
        pUpdate.setProveedor(pv);

        boolean resUpdate = dao.update(pUpdate);
        System.out.println("Actualizacion exitosa: " + resUpdate);

        // Verificacion
        Producto verif = dao.findById(idPrueba);
        System.out.println("Nuevo nombre: " + verif.getNombre() + " | Nuevo Precio: " + verif.getPrecio());

        // ELIMINAR
        System.out.println("***************** 6. Eliminar Producto (ID: " + idPrueba + ") *****************");
        Producto pDel = new Producto();
        pDel.setCodigo(idPrueba);
        boolean resDel = dao.delete(pDel);
        System.out.println("Eliminacion exitosa: " + resDel);

        // HABILITAR
        System.out.println("***************** 7. Habilitar Producto (ID: " + idPrueba + ") *****************");
        Producto pEnable = new Producto();
        pEnable.setCodigo(idPrueba);
        boolean resEn = dao.enable(pEnable);
        System.out.println("Habilitacion exitosa: " + resEn);

        // DESHABILITAR
        System.out.println("***************** 8. Deshabilitar Producto (ID: " + idPrueba + ") *****************");
        Producto pDis = new Producto();
        pDis.setCodigo(idPrueba);
        boolean resDis = dao.disable(pDis);
        System.out.println("Deshabilitacion exitosa: " + resDis);
    }
}