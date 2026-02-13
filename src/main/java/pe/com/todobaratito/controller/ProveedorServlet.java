package pe.com.todobaratito.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import pe.com.todobaratito.dao.*;
import pe.com.todobaratito.dao.impl.*;
import pe.com.todobaratito.model.*;
import pe.com.todobaratito.util.StringManager;

@WebServlet(name = "ProveedorServlet", urlPatterns = {"/ProveedorServlet"})
public class ProveedorServlet extends HttpServlet {

    private ProveedorDAO daoprov;
    private DistritoDAO daodis;
    private int cod = 0;

    @Override
    public void init() throws ServletException {
        super.init();
        daoprov = new ProveedorDAOImpl();
        daodis = new DistritoDAOImpl();
    }

    private void cargarCombos(HttpServletRequest request) {
        request.setAttribute("listaDistritos", daodis.findAllCustom());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";
            switch (accion) {
                case "registro":
                    cargarCombos(request);
                    request.getRequestDispatcher("/proveedor/registrarproveedor.jsp").forward(request, response);
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
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";
            switch (accion) {
                case "registrar":
                    request.setCharacterEncoding("UTF-8");
                    add(request, response);
                    break;
                case "actualizar":
                    request.setCharacterEncoding("UTF-8");
                    update(request, response);
                    break;
                default:
                    findAllCustom(request, response);
                    break;
            }
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("proveedores", daoprov.findAllCustom());
            request.getRequestDispatcher("/proveedor/listarproveedor.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("proveedores", daoprov.findAll());
            request.getRequestDispatcher("/proveedor/habilitarproveedor.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        try {
            Proveedor obj = capturarDatos(request);
            if (obj != null && daoprov.add(obj)) response.sendRedirect("ProveedorServlet");
            else response.sendRedirect("ProveedorServlet?accion=registro");
        } catch (IOException ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("proveedor", daoprov.findById(id));
            cargarCombos(request);
            request.getRequestDispatcher("/proveedor/actualizarproveedor.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            Proveedor obj = capturarDatos(request);
            if (obj != null) {
                cod = Integer.parseInt(request.getParameter("txtCod"));
                obj.setCodigo(cod);
                if (daoprov.update(obj)) response.sendRedirect("ProveedorServlet");
                else response.sendRedirect("ProveedorServlet?accion=actualiza&id=" + cod);
            }
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            Proveedor obj = new Proveedor();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            daoprov.delete(obj);
            response.sendRedirect("ProveedorServlet");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void enable(HttpServletRequest request, HttpServletResponse response) {
        try {
            Proveedor obj = new Proveedor();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            daoprov.enable(obj);
            response.sendRedirect("ProveedorServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void disable(HttpServletRequest request, HttpServletResponse response) {
        try {
            Proveedor obj = new Proveedor();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            daoprov.disable(obj);
            response.sendRedirect("ProveedorServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private Proveedor capturarDatos(HttpServletRequest request) {
        Proveedor obj = new Proveedor();
        try {
            obj.setNombre(StringManager.convertUTF8(request.getParameter("txtNom")));
            obj.setRepresentante(StringManager.convertUTF8(request.getParameter("txtRep")));
            obj.setRuc(request.getParameter("txtRuc"));
            obj.setDireccion(StringManager.convertUTF8(request.getParameter("txtDir")));
            obj.setTelefono(request.getParameter("txtTel"));
            obj.setCelular(request.getParameter("txtCel"));
            obj.setCorreo(request.getParameter("txtCor"));
            obj.setContacto(StringManager.convertUTF8(request.getParameter("txtCon")));

            String est = request.getParameter("chkEst");
            obj.setEstado(est != null && (est.equals("true") || est.equals("on")));

            obj.setDistrito(new Distrito());
            obj.getDistrito().setCodigo(Integer.parseInt(request.getParameter("cboDistrito")));
        } catch (Exception ex) {
            System.out.println("Error al capturar: " + ex.toString());
            return null;
        }
        return obj;
    }
}
