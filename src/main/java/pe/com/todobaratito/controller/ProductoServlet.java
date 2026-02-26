package pe.com.todobaratito.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import pe.com.todobaratito.dao.*;
import pe.com.todobaratito.dao.impl.*;
import pe.com.todobaratito.model.*;
import pe.com.todobaratito.util.StringManager;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    private ProductoDAO daopro;
    private MarcaDAO daomar;
    private CategoriaDAO daocat;
    private ProveedorDAO daoprov;
    private int cod = 0;

    @Override
    public void init() throws ServletException {
        super.init();
        daopro = new ProductoDAOImpl();
        daomar = new MarcaDAOImpl();
        daocat = new CategoriaDAOImpl();
        daoprov = new ProveedorDAOImpl();
    }

    private void cargarCombos(HttpServletRequest request) {
        request.setAttribute("listaMarcas", daomar.findAllCustom());
        request.setAttribute("listaCategorias", daocat.findAllCustom());
        request.setAttribute("listaProveedores", daoprov.findAllCustom());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";
            switch (accion) {
                case "registro":
                    cargarCombos(request);
                    request.getRequestDispatcher("/producto/registrarproducto.jsp").forward(request, response);
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
            request.setAttribute("productos", daopro.findAllCustom());
            request.getRequestDispatcher("/producto/listarproducto.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("productos", daopro.findAll());
            request.getRequestDispatcher("/producto/habilitarproducto.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        try {
            Producto obj = capturarDatos(request);
            if (obj != null && daopro.add(obj)) response.sendRedirect("ProductoServlet");
            else response.sendRedirect("ProductoServlet?accion=registro");
        } catch (IOException ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("producto", daopro.findById(id));
            cargarCombos(request);
            request.getRequestDispatcher("/producto/actualizarproducto.jsp").forward(request, response);
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            Producto obj = capturarDatos(request);
            if (obj != null) {
                cod = Integer.parseInt(request.getParameter("txtCod"));
                obj.setCodigo(cod);
                if (daopro.update(obj)) response.sendRedirect("ProductoServlet");
                else response.sendRedirect("ProductoServlet?accion=actualiza&id=" + cod);
            }
        } catch (IOException | NumberFormatException ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            Producto obj = new Producto();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            daopro.delete(obj);
            response.sendRedirect("ProductoServlet");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void enable(HttpServletRequest request, HttpServletResponse response) {
        try {
            Producto obj = new Producto();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            daopro.enable(obj);
            response.sendRedirect("ProductoServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private void disable(HttpServletRequest request, HttpServletResponse response) {
        try {
            Producto obj = new Producto();
            obj.setCodigo(Integer.parseInt(request.getParameter("id")));
            daopro.disable(obj);
            response.sendRedirect("ProductoServlet?accion=habilita");
        } catch (Exception ex) { System.out.println("Error: " + ex.toString()); }
    }

    private Producto capturarDatos(HttpServletRequest request) {
        Producto obj = new Producto();
        try {
            obj.setNombre(StringManager.convertUTF8(request.getParameter("txtNom")));
            obj.setDescripcion(StringManager.convertUTF8(request.getParameter("txtDes")));
            obj.setPrecio(Double.parseDouble(request.getParameter("txtPre")));
            obj.setCantidad(Integer.parseInt(request.getParameter("txtCan")));
            obj.setStockminimo(Integer.parseInt(request.getParameter("txtSto")));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fIng = request.getParameter("txtFecIng");
            if (fIng != null && !fIng.isEmpty()) obj.setFechaingreso(sdf.parse(fIng));

            String est = request.getParameter("chkEst");
            obj.setEstado(est != null && (est.equals("true") || est.equals("on")));

            obj.setMarca(new Marca());
            obj.getMarca().setCodigo(Integer.parseInt(request.getParameter("cboMarca")));

            obj.setCategoria(new Categoria());
            obj.getCategoria().setCodigo(Integer.parseInt(request.getParameter("cboCategoria")));

            obj.setProveedor(new Proveedor());
            obj.getProveedor().setCodigo(Integer.parseInt(request.getParameter("cboProveedor")));
        } catch (Exception ex) {
            System.out.println("Error al capturar: " + ex.toString());
            return null;
        }
        return obj;
    }
}
