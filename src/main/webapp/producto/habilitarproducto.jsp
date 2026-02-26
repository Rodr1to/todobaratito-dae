<%@page import="pe.com.todobaratito.model.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion == null || sesion.getAttribute("empleados") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Habilitar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Habilitar / Deshabilitar Productos</h1>
    <div class="mb-3"><a href="ProductoServlet?accion=regresar" class="btn btn-dark">Regresar</a></div>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
                <th>Código</th><th>Nombre</th><th>Estado</th><th>Habilitar</th><th>Deshabilitar</th>
            </tr>
            </thead>
            <tbody>
            <% if(productos != null) { for (Producto obj : productos) { %>
            <tr>
                <td><%= obj.getCodigo()%></td>
                <td><%= obj.getNombre()%></td>
                <td><%= obj.isEstado() ? "Habilitado" : "Deshabilitado"%></td>
                <td><a href="ProductoServlet?accion=habilitar&id=<%= obj.getCodigo()%>" class="btn btn-warning">Seleccionar</a></td>
                <td><a href="ProductoServlet?accion=deshabilitar&id=<%= obj.getCodigo()%>" class="btn btn-danger">Seleccionar</a></td>
            </tr>
            <%  } } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
