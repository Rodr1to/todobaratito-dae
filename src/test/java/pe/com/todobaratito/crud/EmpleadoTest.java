package pe.com.todobaratito.crud;

import java.util.Date;
import pe.com.todobaratito.dao.EmpleadoDAO;
import pe.com.todobaratito.dao.impl.EmpleadoDAOImpl;
import pe.com.todobaratito.model.*;

public class EmpleadoTest {
    public static void main(String[] args) {
        EmpleadoDAO dao = new EmpleadoDAOImpl();
        long tiempo = System.currentTimeMillis();
        String dniUnico = String.valueOf(tiempo).substring(5);
        String userUnico = "user" + tiempo;
        int idGenerado = 0;

        System.out.println("***************** 1. Registrar Empleado *****************");
        Empleado emp = new Empleado();
        emp.setNombre("Empleado Test");
        emp.setApellidoPaterno("Paterno");
        emp.setApellidoMaterno("Materno");
        emp.setDocumento(dniUnico);
        emp.setFechaNacimiento(new Date());
        emp.setDireccion("Dir");
        emp.setTelefono("123");
        emp.setCelular("987");
        emp.setCorreo("emp@test.com");
        emp.setUsuario(userUnico);
        emp.setClave("123");
        emp.setCargo("Test");
        emp.setFechaIngreso(new Date());
        emp.setEstado(true);

        Distrito d = new Distrito(); d.setCodigo(1); emp.setDistrito(d);
        Rol r = new Rol(); r.setCodigo(1); emp.setRol(r);
        Sexo s = new Sexo(); s.setCodigo(1); emp.setSexo(s);
        TipoDocumento t = new TipoDocumento(); t.setCodigo(1); emp.setTipoDocumento(t);

        System.out.println("Registro exitoso: " + dao.add(emp));

        // RECUPERAR ID
        for(Empleado e : dao.findAll()){
            if(e.getUsuario().equals(userUnico)){
                idGenerado = e.getCodigo();
                break;
            }
        }
        System.out.println(">>> ID TEST GENERADO: " + idGenerado + " <<<");

        System.out.println("***************** 2. Mostrar Todo *****************");
        for(Empleado e : dao.findAll()) System.out.println(e.getNombre() + " " + e.getApellidoPaterno());

        System.out.println("***************** 3. Mostrar Habilitados *****************");
        for(Empleado e : dao.findAllCustom()) System.out.println(e.getNombre());

        if(idGenerado != 0){
            System.out.println("***************** 4. Buscar Empleado *****************");
            Empleado found = dao.findById(idGenerado);
            System.out.println("Encontrado: " + found.getUsuario());

            System.out.println("***************** 5. Actualizar Empleado *****************");
            found.setNombre("Empleado UPD");
            System.out.println("Actualizacion exitosa: " + dao.update(found));

            System.out.println("***************** 6. Eliminar Empleado *****************");
            System.out.println("Eliminacion exitosa: " + dao.delete(found));

            System.out.println("***************** 7. Habilitar Empleado *****************");
            System.out.println("Habilitacion exitosa: " + dao.enable(found));

            System.out.println("***************** 8. Deshabilitar Empleado *****************");
            System.out.println("Deshabilitacion exitosa: " + dao.disable(found));
        }
    }
}
