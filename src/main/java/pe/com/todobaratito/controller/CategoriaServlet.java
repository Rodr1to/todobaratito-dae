package pe.com.todobaratito.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import pe.com.todobaratito.dao.CategoriaDAO;
import pe.com.todobaratito.dao.impl.CategoriaDAOImpl;
import pe.com.todobaratito.model.Categoria;
import pe.com.todobaratito.util.StringManager;


//name = "CategoriaServlet" -> define el nombre del Servelet
//urlPatterns = {"/CategoriaServlet"} -> define la ruta de acceso al Servlet
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})
//clase que realiza una herencia de HttpServlet
public class CategoriaServlet extends HttpServlet {

    //declaramos el DAO
    private CategoriaDAO daocat;
    //declarando variables
    private int cod=0;
    private String nom="";
    private boolean est=false;

    @Override
    public void init() throws ServletException {
        super.init();
        //implementamos el dao
        daocat=new CategoriaDAOImpl();
    }




//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CategoriaServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CategoriaServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

    //doGet -> generalmente es para rutas y algunas acciones
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //controlamos los errores
        try {
            //creamos una variable para poder capturar la accion que viene desde el JSP
            String accion=request.getParameter("accion");
            //evaluamos por si al accion es nula
            if(accion==null){
                accion="listar";
            }
            //evaluamos la accion
            switch(accion){
                case "registro":
                    //cargamos el formulario de registro
                    request.getRequestDispatcher("/categoria/registrarcategoria.jsp").forward(request, response);
                    break;
                case "actualiza":
                    findById(request,response);
                    break;
                case "habilita":
                    findAll(request,response);
                    break;
                //acciones
                case "eliminar":
                    delete(request,response);
                    break;
                case "habilitar":
                    enable(request,response);
                    break;
                case "deshabilitar":
                    disable(request,response);
                    break;
                case "regresar":
                    findAllCustom(request,response);
                    break;
                case "menu":
                    request.getRequestDispatcher("menuprincipal.jsp").forward(request, response);
                    break;
                default:
                    findAllCustom(request,response);
                    break;
            }


        } catch (ServletException | IOException ex) {
            System.out.println("Error: "+ex.toString());
        }

    }


    //doPost ->son acciones generadas generalmente por un boton
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //controlamos los errores
        try {
            //creamos una variable para poder capturar la accion que viene desde el JSP
            String accion=request.getParameter("accion");
            //evaluamos por si al accion es nula
            if(accion==null){
                accion="listar";
            }
            //evaluamos la accion
            switch(accion){
                case "registrar":
                    request.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=UTF-8");
                    add(request,response);
                    break;
                case "actualizar":
                    update(request,response);
                    break;
                case "regresar":
                    findAllCustom(request,response);
                    break;
                default:
                    findAllCustom(request,response);
                    break;
            }
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Error: "+ex.toString());
        }
    }

    //creamos procedimientos para las acciones
    //listar
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response){
        //controlamos los errores
        try {
            //creamos una lista que almecene las categorias
            List<Categoria> categorias=daocat.findAllCustom();
            //enviamos las categorias como variables
            request.setAttribute("categorias", categorias);
            //asignamos el formulario al cual se van a enviar las categorias
            request.getRequestDispatcher("/categoria/listarcategoria.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("Error: "+ex.toString());
        }

    }
    //listar todo
    private void findAll(HttpServletRequest request, HttpServletResponse response){
        //controlamos los errores
        try {
            //creamos una lista que almecene las categorias
            List<Categoria> categorias=daocat.findAll();
            //enviamos las categorias como variables
            request.setAttribute("categorias", categorias);
            //asignamos el formulario al cual se van a enviar las categorias
            request.getRequestDispatcher("/categoria/listarcategoria.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("Error: "+ex.toString());
        }
    }
    //registrar
    private void add(HttpServletRequest request, HttpServletResponse response){
        //controlando los errores
        try {
            //codificar en UTF-8
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            //capturando valores
            nom=StringManager.convertUTF8(request.getParameter("txtNom"));
            est=Boolean.parseBoolean(request.getParameter("chkEst"));
            //creamos un objeto
            Categoria obj=new Categoria();
            //le asignamos los valores al objeto
            obj.setNombre(nom);
            obj.setEstado(est);
            //registramos la categoria
            boolean res=daocat.add(obj);
            //evaluamos el registro
            if(res==true){
                response.sendRedirect("CategoriaServlet");
            }else{
                response.sendRedirect("CategoriaServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println("Error: "+ex.toString());
        }

    }
    //buscar por codigo
    private void findById(HttpServletRequest request, HttpServletResponse response){
        //controlando errores
        try {
            //capturamos el codigo de la ruta
            int id=Integer.parseInt(request.getParameter("id"));
            //realizamos la busqueda
            Categoria obj=daocat.findById(id);
            //enviamos los datos por una variables
            request.setAttribute("categorias", obj);
            //asignamos el formulario al cual se van a enviar las categorias
            request.getRequestDispatcher("/categoria/actualizarcategoria.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException ex) {
            System.out.println("Error"+ex.toString());
        }
    }
    //actualizar
    private void update(HttpServletRequest request, HttpServletResponse response){
        //controlando los errores
        try {
            //codificar en UTF-8
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            //capturando valores
            cod=Integer.parseInt(request.getParameter("txtCod"));
            nom=StringManager.convertUTF8(request.getParameter("txtNom"));
            est=Boolean.parseBoolean(request.getParameter("chkEst"));
            //creamos un objeto
            Categoria obj=new Categoria();
            //le asignamos los valores al objeto
            obj.setCodigo(cod);
            obj.setNombre(nom);
            obj.setEstado(est);
            //registramos la categoria
            boolean res=daocat.update(obj);
            //evaluamos el registro
            if(res==true){
                response.sendRedirect("CategoriaServlet");
            }else{
                response.sendRedirect("CategoriaServlet?accion=actualiza&id="+obj.getCodigo());
            }
        } catch (IOException ex) {
            System.out.println("Error: "+ex.toString());
        }
    }
    //eliminar
    private void delete(HttpServletRequest request, HttpServletResponse response){
        //controlando los errores
        try {
            //capturando valores
            cod=Integer.parseInt(request.getParameter("id"));
            //creamos un objeto
            Categoria obj=new Categoria();
            //le asignamos los valores al objeto
            obj.setCodigo(cod);
            //registramos la categoria
            boolean res=daocat.delete(obj);
            //evaluamos el registro
            if(res==true){
                response.sendRedirect("CategoriaServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error: "+ex.toString());
        }
    }
    //habilitar
    private void enable(HttpServletRequest request, HttpServletResponse response){
        //controlando los errores
        try {
            //capturando valores
            cod=Integer.parseInt(request.getParameter("id"));
            //creamos un objeto
            Categoria obj=new Categoria();
            //le asignamos los valores al objeto
            obj.setCodigo(cod);
            //registramos la categoria
            boolean res=daocat.enable(obj);
            //evaluamos el registro
            if(res==true){
                response.sendRedirect("CategoriaServlet");
            }else{
                response.sendRedirect("CategoriaServlet?accion=habilita");
            }
        } catch (IOException ex) {
            System.out.println("Error: "+ex.toString());
        }
    }
    //deshabilitar
    private void disable(HttpServletRequest request, HttpServletResponse response){
        //controlando los errores
        try {
            //capturando valores
            cod=Integer.parseInt(request.getParameter("id"));
            //creamos un objeto
            Categoria obj=new Categoria();
            //le asignamos los valores al objeto
            obj.setCodigo(cod);
            //registramos la categoria
            boolean res=daocat.disable(obj);
            //evaluamos el registro
            if(res==true){
                response.sendRedirect("CategoriaServlet");
            }else{
                response.sendRedirect("CategoriaServlet?accion=habilita");
            }
        } catch (IOException ex) {
            System.out.println("Error: "+ex.toString());
        }
    }


}


