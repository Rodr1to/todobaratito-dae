package pe.com.todobaratito.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import pe.com.todobaratito.dao.DistritoDAO;
import pe.com.todobaratito.dao.impl.DistritoDAOImpl;
import pe.com.todobaratito.model.Distrito;
import pe.com.todobaratito.util.StringManager;

@WebServlet(name = "DistritoServlet", urlPatterns = {"/DistritoServlet"})
public class DistritoServlet extends HttpServlet {

    private DistritoDAO dao;
    private int cod = 0;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new DistritoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";
            switch (accion) {
                case "registro":
                    request.getRequestDispatcher("/distrito/registrardistrito.jsp").forward(request, response);
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
            request.setAttribute("distritos", dao.findAllCustom());
            request.getRequestDispatcher("/distrito/listardistrito.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("distritos", dao.findAll());
            request.getRequestDispatcher("/distrito/habilitardistrito.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        try {
            Distrito obj = new Distrito();
            obj.setNombre(StringManager.convertUTF8(request.getParameter("txtNom")));
            String est = request.getParameter("chkEst");
            obj.setEstado(est != null && (est.equals("true") || est.equals("on")));

            if (dao.add(obj)) response.sendRedirect("DistritoServlet");
            else response.sendRedirect("DistritoServlet?accion=registro");
        } catch (IOException ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("distrito", dao.findById(id));
            request.getRequestDispatcher("/distrito/actualizardistrito.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            Distrito obj = new Distrito();
            cod = Integer.parseInt(request.getParameter("txtCod"));
            obj.setCodigo(cod);
            obj.setNombre(StringManager.convertUTF8(request.getParameter("txtNom")));
            String est = request.getParameter("chkEst");
            obj.setEstado(est != null && (est.equals("true") || est.equals("on")));

            if (dao.update(obj)) response.sendRedirect("DistritoServlet");
            else response.sendRedirect("DistritoServlet?accion=actualiza&id=" + cod);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            Distrito obj = new Distrito();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            dao.delete(obj);
            response.sendRedirect("DistritoServlet");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void enable(HttpServletRequest request, HttpServletResponse response) {
        try {
            Distrito obj = new Distrito();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            dao.enable(obj);
            response.sendRedirect("DistritoServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void disable(HttpServletRequest request, HttpServletResponse response) {
        try {
            Distrito obj = new Distrito();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            dao.disable(obj);
            response.sendRedirect("DistritoServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }
}
