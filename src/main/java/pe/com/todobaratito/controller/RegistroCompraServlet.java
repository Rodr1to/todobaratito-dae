package pe.com.todobaratito.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.dao.EmpleadoDAO;
import pe.com.todobaratito.dao.ProductoDAO;
import pe.com.todobaratito.dao.ProveedorDAO;
import pe.com.todobaratito.dao.RegistroCompraDAO;
import pe.com.todobaratito.dao.impl.EmpleadoDAOImpl;
import pe.com.todobaratito.dao.impl.ProductoDAOImpl;
import pe.com.todobaratito.dao.impl.ProveedorDAOImpl;
import pe.com.todobaratito.dao.impl.RegistroCompraDAOImpl;
import pe.com.todobaratito.model.DetalleRegistroCompra;
import pe.com.todobaratito.model.Empleado;
import pe.com.todobaratito.model.Producto;
import pe.com.todobaratito.model.Proveedor;
import pe.com.todobaratito.model.RegistroCompra;
import pe.com.todobaratito.util.StringManager;

@WebServlet(name = "RegistroCompraServlet", urlPatterns = {"/RegistroCompraServlet"})
public class RegistroCompraServlet extends HttpServlet {

    //declaramos el DAO
    private RegistroCompraDAO daoregcom;
    private EmpleadoDAO daoemp;
    private ProveedorDAO daoprov;
    private ProductoDAO daopro;
    //declarando variables
    private int num = 0, codemp = 0, codprov = 0;
    private String obs="";
    private boolean est = false,res=false;

    @Override
    public void init() throws ServletException {
        super.init();
        //implementamos el dao
        daoregcom = new RegistroCompraDAOImpl();
        daoemp = new EmpleadoDAOImpl();
        daoprov = new ProveedorDAOImpl();
        daopro = new ProductoDAOImpl();
    }

    //    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RegistroCompraServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RegistroCompraServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
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
                    List<Producto> productos=daopro.findAllCustom();
                    int siguiente=daoregcom.setNumber();
                    List<Proveedor> proveedores=daoprov.findAllCustom();
                    request.setAttribute("productos", productos);
                    request.setAttribute("numero", siguiente);
                    request.setAttribute("proveedores", proveedores);
                    request.getRequestDispatcher("/registrocompra/registrarcompra.jsp").forward(request, response);
                    break;
                case "actualiza":
                    findById(request, response);
                    break;
                //acciones
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
                    findAll(request, response);
                    break;
                case "menu":
                    request.getRequestDispatcher("menuprincipal.jsp").forward(request, response);
                    break;
                default:
                    findAll(request, response);
                    break;
            }

        } catch (ServletException | IOException ex) {
            System.out.println("Error: " + ex.toString());
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
                    update(request, response);
                    break;
                case "regresar":
                    findAllCustom(request, response);
                    break;
                default:
                    findAllCustom(request, response);
                    break;
            }
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }


    //creamos procedimientos para las acciones
    //listar
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) {

    }

    //listar todo
    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<RegistroCompra> registrocompras = daoregcom.findAll();
            request.setAttribute("registrocompras", registrocompras);
            request.getRequestDispatcher("/registrocompra/listarregistrocompra.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }


    //registrar
    private void add(HttpServletRequest request, HttpServletResponse response) {
        try {
            //PrintWriter out = response.getWriter();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            codprov = Integer.parseInt(request.getParameter("cboProveedor"));
            codemp = Integer.parseInt(request.getParameter("txtCodEmp"));
            obs = request.getParameter("txtObs");
            RegistroCompra objrc = new RegistroCompra();
            Proveedor objprov = new Proveedor();
            Empleado objemp = new Empleado();
            //le asignamos los valores
            objrc.setObservaciones(obs);
            objrc.setEstado(est);

            objprov.setCodigo(codprov);
            objrc.setProveedor(objprov);

            objemp.setCodigo(codemp);
            objrc.setEmpleado(objemp);
            res = daoregcom.add(objrc);
            //out.print(res);

            if (res == true) {
                int numtic = daoregcom.getNumber();
//                out.print(numtic);
                int canpro = Integer.parseInt(request.getParameter("txtCanPed"));
                List<String> codigos = new ArrayList<>();
                List<String> cantidades = new ArrayList<>();
                List<String> precios = new ArrayList<>();

//                out.print("nro:" + numtic);
//                out.print("cantidad:" + canpro);
                for (int i = 0; i < canpro; i++) {
                    codigos.add(request.getParameter("codigos[" + i + "]"));
                    cantidades.add(request.getParameter("cantidades[" + i + "]"));
                    precios.add(request.getParameter("precios[" + i + "]"));
                }
//                out.print("codigos:" + codigos.size());
//                out.print("cantidades:" + cantidades.size());
//                out.print("precios:" + precios.size());

                for (int i = 0; i < canpro; i++) {
                    Producto objproducto = new Producto();
                    DetalleRegistroCompra objdetrc = new DetalleRegistroCompra();
                    objdetrc.setPreciocompra(Double.parseDouble(precios.get(i)));
                    objdetrc.setCantidadcompra(Integer.parseInt(cantidades.get(i)));
                    objrc.setNumero(numtic);
                    objdetrc.setRegistrocompra(objrc);
                    objproducto.setCodigo(Integer.parseInt(codigos.get(i)));
                    objdetrc.setProducto(objproducto);
                    boolean res = daoregcom.addDetails(objdetrc);
                    //out.println(resultado);
                }
            }

            if (res == true) {
                response.sendRedirect("RegistroCompraServlet");
            } else {
                response.sendRedirect("RegistroCompra?accion=registro");
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }

    //buscar por codigo
    private void findById(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            RegistroCompra obj = daoregcom.findById(id);
            request.setAttribute("registrocompra", obj);
            // request.getRequestDispatcher("/registrocompra/detallecompra.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Error findById: " + ex.toString());
        }
    }

    //actualizar
    private void update(HttpServletRequest request, HttpServletResponse response) {
    }

    //eliminar
    private void delete(HttpServletRequest request, HttpServletResponse response) {

    }

    //habilitar
    private void enable(HttpServletRequest request, HttpServletResponse response) {
        try {
            int num = Integer.parseInt(request.getParameter("id"));
            RegistroCompra obj = new RegistroCompra();
            obj.setNumero(num);
            daoregcom.enable(obj);
            response.sendRedirect("RegistroCompraServlet");
        } catch (Exception ex) {
            System.out.println("Error enable: " + ex.toString());
        }
    }

    //deshabilitar
    private void disable(HttpServletRequest request, HttpServletResponse response) {
        try {
            int num = Integer.parseInt(request.getParameter("id"));
            RegistroCompra obj = new RegistroCompra();
            obj.setNumero(num);
            daoregcom.disable(obj);
            response.sendRedirect("RegistroCompraServlet");
        } catch (Exception ex) {
            System.out.println("Error disable: " + ex.toString());
        }
    }

}

