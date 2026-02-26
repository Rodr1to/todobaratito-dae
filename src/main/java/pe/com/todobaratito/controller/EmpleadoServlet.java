package pe.com.todobaratito.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.com.todobaratito.dao.*;
import pe.com.todobaratito.dao.impl.*;
import pe.com.todobaratito.model.*;
import pe.com.todobaratito.util.StringManager;

@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/EmpleadoServlet"})
public class EmpleadoServlet extends HttpServlet {

    private EmpleadoDAO daoemp;
    private DistritoDAO daodis;
    private RolDAO daorol;
    private SexoDAO daosex;
    private TipoDocumentoDAO daotip;

    private int cod = 0;
    private String nom = "", usu = "", cla = "";
    private boolean est = false;

    @Override
    public void init() throws ServletException {
        super.init();
        daoemp = new EmpleadoDAOImpl();
        daodis = new DistritoDAOImpl();
        daorol = new RolDAOImpl();
        daosex = new SexoDAOImpl();
        daotip = new TipoDocumentoDAOImpl();
    }

    private void cargarCombos(HttpServletRequest request) {
        request.setAttribute("listaDistritos", daodis.findAllCustom());
        request.setAttribute("listaRoles", daorol.findAllCustom());
        request.setAttribute("listaSexos", daosex.findAllCustom());
        request.setAttribute("listaTipos", daotip.findAllCustom());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";
            switch (accion) {
                case "registro":
                    cargarCombos(request);
                    request.getRequestDispatcher("/empleado/registrarempleado.jsp").forward(request, response);
                    break;
                case "actualiza":
                    findById(request, response);
                    break;
                case "habilita":
                    findAll(request, response);
                    break;
                case "eliminar":
                    delete(request, response);
                    break;
                case "habilitar":
                    enable(request, response);
                    break;
                case "deshabilitar":
                    disable(request, response);
                    break;
                case "regresar":
                    findAllCustom(request, response);
                    break;
                case "menu":
                    request.getRequestDispatcher("menuprincipal.jsp").forward(request, response);
                    break;
                case "cerrarsesion":
                    HttpSession sesion=request.getSession();
                    if(sesion!=null){
                        sesion.invalidate();
                        response.sendRedirect("index.jsp");
                    }
                    break;
                default:
                    findAllCustom(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error doGet: " + ex.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";
            switch (accion) {
                case "registrar":
                    request.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=UTF-8");
                    add(request, response);
                    break;
                case "actualizar":
                    request.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=UTF-8");
                    update(request, response);
                    break;
                case "validar":
                    login(request, response);
                    break;
                default:
                    findAllCustom(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error doPost: " + ex.toString());
        }
    }


    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("empleados", daoemp.findAllCustom());
            request.getRequestDispatcher("/empleado/listarempleado.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("empleados", daoemp.findAll());
            request.getRequestDispatcher("/empleado/habilitarempleado.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        try {
            Empleado obj = capturarDatos(request);
            if (obj != null && daoemp.add(obj)) {
                response.sendRedirect("EmpleadoServlet");
            } else {
                response.sendRedirect("EmpleadoServlet?accion=registro");
            }
        } catch (IOException ex) { System.out.println("Error add: " + ex.toString()); }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("empleado", daoemp.findById(id));
            cargarCombos(request);
            request.getRequestDispatcher("/empleado/actualizarempleado.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error findById: " + ex.toString()); }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            Empleado obj = capturarDatos(request);
            if (obj != null) {
                cod = Integer.parseInt(request.getParameter("txtCod"));
                obj.setCodigo(cod);
                if (daoemp.update(obj)) {
                    response.sendRedirect("EmpleadoServlet");
                } else {
                    response.sendRedirect("EmpleadoServlet?accion=actualiza&id=" + cod);
                }
            }
        } catch (IOException | NumberFormatException ex) { System.out.println("Error update: " + ex.toString()); }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            daoemp.delete(obj);
            response.sendRedirect("EmpleadoServlet");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void enable(HttpServletRequest request, HttpServletResponse response) {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            daoemp.enable(obj);
            response.sendRedirect("EmpleadoServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void disable(HttpServletRequest request, HttpServletResponse response) {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            daoemp.disable(obj);
            response.sendRedirect("EmpleadoServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    //validarusuario
    private void login(HttpServletRequest request, HttpServletResponse response) {
        //declaramos una variable para el resultado
        boolean res = false;
        try {
            //creamos un objeto de Empleado
            Empleado objemp = new Empleado();
            //capturamos los valores
            usu = request.getParameter("txtUsu");
            cla = request.getParameter("txtCla");
            //enviamos los valores a la clase
            objemp.setUsuario(usu);
            objemp.setClave(cla);
            //validamos el usuario
            List<Empleado> empleados = daoemp.login(objemp);
            res = empleados.size() == 1;
            //evaluamos el resultado de la validacion
            if (res == true) {
                //creamos una sesion
                HttpSession sesion = request.getSession();
                //asignamos los valores a la sesion
                sesion.setAttribute("empleados", empleados);
                //redirigimos hacia la pagina que ncesitamos enviar los valores de sesion
                response.sendRedirect("menuprincipal.jsp");
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (ServletException | IOException ex) {
            System.out.println(ex.toString());
        }

    }

    private Empleado capturarDatos(HttpServletRequest request) {
        Empleado obj = new Empleado();
        try {
            obj.setNombre(StringManager.convertUTF8(request.getParameter("txtNom")));
            obj.setApellidoPaterno(StringManager.convertUTF8(request.getParameter("txtApeP")));
            obj.setApellidoMaterno(StringManager.convertUTF8(request.getParameter("txtApeM")));
            obj.setDocumento(request.getParameter("txtDoc"));
            obj.setDireccion(StringManager.convertUTF8(request.getParameter("txtDir")));
            obj.setTelefono(request.getParameter("txtTel"));
            obj.setCelular(request.getParameter("txtCel"));
            obj.setCorreo(request.getParameter("txtCor"));
            obj.setUsuario(request.getParameter("txtUsu"));
            obj.setClave(request.getParameter("txtCla"));
            obj.setCargo(request.getParameter("txtCar"));

            String estStr = request.getParameter("chkEst");
            obj.setEstado(estStr != null && (estStr.equals("true") || estStr.equals("on")));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fNac = request.getParameter("txtFecNac");
            String fIng = request.getParameter("txtFecIng");
            if (fNac != null && !fNac.isEmpty()) obj.setFechaNacimiento(sdf.parse(fNac));
            if (fIng != null && !fIng.isEmpty()) obj.setFechaIngreso(sdf.parse(fIng));

            obj.setDistrito(new Distrito());
            obj.getDistrito().setCodigo(Integer.parseInt(request.getParameter("cboDistrito")));

            obj.setRol(new Rol());
            obj.getRol().setCodigo(Integer.parseInt(request.getParameter("cboRol")));

            obj.setSexo(new Sexo());
            obj.getSexo().setCodigo(Integer.parseInt(request.getParameter("cboSexo")));

            obj.setTipoDocumento(new TipoDocumento());
            obj.getTipoDocumento().setCodigo(Integer.parseInt(request.getParameter("cboTipoDoc")));

        } catch (Exception ex) {
            System.out.println("Error al capturar datos: " + ex.toString());
            return null;
        }
        return obj;
    }
}

