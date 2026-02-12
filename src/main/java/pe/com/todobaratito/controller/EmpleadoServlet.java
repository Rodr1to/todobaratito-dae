package pe.com.todobaratito.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.com.todobaratito.dao.*;
import pe.com.todobaratito.dao.impl.*;
import pe.com.todobaratito.model.*;
import pe.com.todobaratito.util.StringManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

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
            if (accion == null) {
                accion = "listar";
            }
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
                default:
                    findAllCustom(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) {
                accion = "listar";
            }
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
                case "regresar":
                    findAllCustom(request, response);
                    break;
                default:
                    findAllCustom(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }


    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Empleado> empleados = daoemp.findAllCustom();
            request.setAttribute("empleados", empleados);
            request.getRequestDispatcher("/empleado/listarempleado.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Empleado> empleados = daoemp.findAll();
            request.setAttribute("empleados", empleados);
            request.getRequestDispatcher("/empleado/habilitarempleado.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        try {
            Empleado obj = capturarDatos(request);
            if (obj != null) {
                boolean res = daoemp.add(obj);
                if (res) {
                    response.sendRedirect("EmpleadoServlet");
                } else {
                    response.sendRedirect("EmpleadoServlet?accion=registro");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Empleado obj = daoemp.findById(id);
            request.setAttribute("empleado", obj);

            cargarCombos(request);

            request.getRequestDispatcher("/empleado/actualizarempleado.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            Empleado obj = capturarDatos(request);
            if (obj != null) {
                cod = Integer.parseInt(request.getParameter("txtCod"));
                obj.setCodigo(cod);

                boolean res = daoemp.update(obj);
                if (res) {
                    response.sendRedirect("EmpleadoServlet");
                } else {
                    response.sendRedirect("EmpleadoServlet?accion=actualiza&id=" + cod);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            boolean res = daoemp.delete(obj);
            if (res) {
                response.sendRedirect("EmpleadoServlet");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void enable(HttpServletRequest request, HttpServletResponse response) {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            daoemp.enable(obj);
            response.sendRedirect("EmpleadoServlet?accion=habilita");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void disable(HttpServletRequest request, HttpServletResponse response) {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            daoemp.disable(obj);
            response.sendRedirect("EmpleadoServlet?accion=habilita");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        boolean res = false;
        try {
            Empleado objemp = new Empleado();
            usu = request.getParameter("txtUsu");
            cla = request.getParameter("txtCla");
            objemp.setUsuario(usu);
            objemp.setClave(cla);
            List<Empleado> empleados = daoemp.login(objemp);
            res = empleados.size() == 1;
            if (res == true) {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("empleados", empleados);
                response.sendRedirect("menuprincipal.jsp");
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception ex) {
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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fecNac = request.getParameter("txtFecNac");
            String fecIng = request.getParameter("txtFecIng");

            if (fecNac != null && !fecNac.isEmpty()) {
                obj.setFechaNacimiento(sdf.parse(fecNac));
            }
            if (fecIng != null && !fecIng.isEmpty()) {
                obj.setFechaIngreso(sdf.parse(fecIng));
            }

            obj.setDireccion(StringManager.convertUTF8(request.getParameter("txtDir")));
            obj.setTelefono(request.getParameter("txtTel"));
            obj.setCelular(request.getParameter("txtCel"));
            obj.setCorreo(request.getParameter("txtCor"));
            obj.setUsuario(request.getParameter("txtUsu"));
            obj.setClave(request.getParameter("txtCla"));
            obj.setCargo(request.getParameter("txtCar"));

            String estStr = request.getParameter("chkEst");
            obj.setEstado(estStr != null && (estStr.equals("true") || estStr.equals("on")));

            Distrito d = new Distrito();
            d.setCodigo(Integer.parseInt(request.getParameter("cboDistrito")));
            obj.setDistrito(d);

            Rol r = new Rol();
            r.setCodigo(Integer.parseInt(request.getParameter("cboRol")));
            obj.setRol(r);

            Sexo s = new Sexo();
            s.setCodigo(Integer.parseInt(request.getParameter("cboSexo")));
            obj.setSexo(s);

            TipoDocumento td = new TipoDocumento();
            td.setCodigo(Integer.parseInt(request.getParameter("cboTipoDoc")));
            obj.setTipoDocumento(td);

        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
        return obj;
    }

}

