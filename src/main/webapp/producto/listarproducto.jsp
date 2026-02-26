<%@page import="pe.com.todobaratito.model.Producto"%>
<%@page import="pe.com.todobaratito.model.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    List<Empleado> empleados = new ArrayList<>();
    if (sesion != null && sesion.getAttribute("empleados") != null) {
        empleados = (List<Empleado>) sesion.getAttribute("empleados");
    } else {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Todo Baratito | Listar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Listado de Productos</h1>
    <div class="mb-3">
        <a href="ProductoServlet?accion=registro" class="btn btn-primary">Registrar Producto</a>
        <a href="ProductoServlet?accion=habilita" class="btn btn-warning">Habilitar Producto</a>
        <a href="ProductoServlet?accion=menu" class="btn btn-dark">Regresar al Menú</a>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-hover table-bordered">
            <thead class="table-dark">
            <tr>
                <th scope="col">Codigo</th>
                <th scope="col">Nombre</th>
                <th scope="col">Precio</th>
                <th scope="col">Stock</th>
                <th scope="col">Marca</th>
                <th scope="col">Categoría</th>
                <th scope="col">Proveedor</th>
                <th scope="col">Actualizar</th>
                <th scope="col">Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <% if(productos != null) {
                for (Producto obj : productos) { %>
            <tr>
                <th scope="row"><%= obj.getCodigo()%></th>
                <td><%= obj.getNombre()%></td>
                <td><%= obj.getPrecio()%></td>
                <td><%= obj.getCantidad()%></td>
                <td><%= obj.getMarca().getNombre()%></td>
                <td><%= obj.getCategoria().getNombre()%></td>
                <td><%= obj.getProveedor().getNombre()%></td>
                <td><a href="ProductoServlet?accion=actualiza&id=<%= obj.getCodigo()%>" class="btn btn-success">Seleccionar</a></td>
                <td><a href="ProductoServlet?accion=eliminar&id=<%= obj.getCodigo()%>" class="btn btn-danger">Seleccionar</a></td>
            </tr>
            <%  }
            } else { %>
            <tr><td colspan="9" class="text-center">No hay productos registrados</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
