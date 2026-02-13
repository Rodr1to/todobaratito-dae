package pe.com.todobaratito.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import pe.com.todobaratito.dao.TipoDocumentoDAO;
import pe.com.todobaratito.dao.impl.TipoDocumentoDAOImpl;
import pe.com.todobaratito.model.TipoDocumento;
import pe.com.todobaratito.util.StringManager;

@WebServlet(name = "TipoDocumentoServlet", urlPatterns = {"/TipoDocumentoServlet"})
public class TipoDocumentoServlet extends HttpServlet {

    private TipoDocumentoDAO dao;
    private int cod = 0;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new TipoDocumentoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";
            switch (accion) {
                case "registro":
                    request.getRequestDispatcher("/tipodocumento/registrartipodocumento.jsp").forward(request, response);
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
            request.setAttribute("tipodocumentos", dao.findAllCustom());
            request.getRequestDispatcher("/tipodocumento/listartipodocumento.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("tipodocumentos", dao.findAll());
            request.getRequestDispatcher("/tipodocumento/habilitartipodocumento.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        try {
            TipoDocumento obj = new TipoDocumento();
            obj.setNombre(StringManager.convertUTF8(request.getParameter("txtNom")));
            String est = request.getParameter("chkEst");
            obj.setEstado(est != null && (est.equals("true") || est.equals("on")));

            if (dao.add(obj)) response.sendRedirect("TipoDocumentoServlet");
            else response.sendRedirect("TipoDocumentoServlet?accion=registro");
        } catch (IOException ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("tipodocumento", dao.findById(id));
            request.getRequestDispatcher("/tipodocumento/actualizartipodocumento.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            TipoDocumento obj = new TipoDocumento();
            cod = Integer.parseInt(request.getParameter("txtCod"));
            obj.setCodigo(cod);
            obj.setNombre(StringManager.convertUTF8(request.getParameter("txtNom")));
            String est = request.getParameter("chkEst");
            obj.setEstado(est != null && (est.equals("true") || est.equals("on")));

            if (dao.update(obj)) response.sendRedirect("TipoDocumentoServlet");
            else response.sendRedirect("TipoDocumentoServlet?accion=actualiza&id=" + cod);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            TipoDocumento obj = new TipoDocumento();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            dao.delete(obj);
            response.sendRedirect("TipoDocumentoServlet");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void enable(HttpServletRequest request, HttpServletResponse response) {
        try {
            TipoDocumento obj = new TipoDocumento();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            dao.enable(obj);
            response.sendRedirect("TipoDocumentoServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void disable(HttpServletRequest request, HttpServletResponse response) {
        try {
            TipoDocumento obj = new TipoDocumento();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            dao.disable(obj);
            response.sendRedirect("TipoDocumentoServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }
}
