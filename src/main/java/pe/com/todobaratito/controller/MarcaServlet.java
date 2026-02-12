package pe.com.todobaratito.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Esta línea @WebServlet hace MAGIA: reemplaza toda la configuración del web.xml
// Es lo mismo que poner "URL Pattern: /MarcaServlet" en el asistente de NetBeans.
@WebServlet(name = "MarcaServlet", urlPatterns = {"/MarcaServlet"})
public class MarcaServlet extends HttpServlet {

    // El método para procesar peticiones (equivale a processRequest en NetBeans)
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MarcaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MarcaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}