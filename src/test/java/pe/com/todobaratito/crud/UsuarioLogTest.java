package pe.com.todobaratito.crud;

import java.util.Date;
import java.util.List;
import pe.com.todobaratito.dao.UsuarioLogDAO;
import pe.com.todobaratito.dao.impl.UsuarioLogDAOImpl;
import pe.com.todobaratito.model.Empleado;
import pe.com.todobaratito.model.UsuarioLog;

public class UsuarioLogTest {
    public static void main(String[] args) {
        UsuarioLogDAO dao = new UsuarioLogDAOImpl();
        String accionUnica = "LOG TEST " + System.currentTimeMillis();
        int idGenerado = 0;

        System.out.println("***************** 1. Registrar Log *****************");
        UsuarioLog log = new UsuarioLog();
        Empleado emp = new Empleado(); emp.setCodigo(1);
        log.setEmpleado(emp);
        log.setAccion(accionUnica);
        log.setFecha(new Date());
        System.out.println("Registro exitoso: " + dao.add(log));

        // RECUPERAR ID
        for(UsuarioLog l : dao.findAll()){
            if(l.getAccion().equals(accionUnica)){
                idGenerado = l.getIdLog();
                break;
            }
        }
        System.out.println(">>> ID TEST GENERADO: " + idGenerado + " <<<");

        System.out.println("***************** 2. Mostrar Logs Todo *****************");

        List<UsuarioLog> lista = dao.findAll();
        int count = 0;
        for(UsuarioLog l : lista){
            System.out.println("ID: "+l.getIdLog()+" | Accion: "+l.getAccion());
            if(++count >= 5) break;
        }

        System.out.println("***************** 3. Mostrar Logs Custom (Hoy) *****************");
        dao.findAllCustom();

        if(idGenerado != 0){
            System.out.println("***************** 4. Buscar Log *****************");
            UsuarioLog found = dao.findById(idGenerado);
            System.out.println("Encontrado: " + found.getAccion());

            System.out.println("***************** 5. Actualizar Log (Prohibido) *****************");
            found.setAccion("Intento Hack");
            System.out.println("Actualizacion: " + dao.update(found));

            System.out.println("***************** 6. Eliminar Log (Prohibido) *****************");
            System.out.println("Eliminacion: " + dao.delete(found));

            System.out.println("***************** 7 y 8. Habilitar/Deshabilitar (N/A) *****************");
            dao.enable(found);
            dao.disable(found);
        }
    }
}
