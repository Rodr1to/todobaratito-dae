<%@page import="pe.com.todobaratito.model.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pe.com.todobaratito.model.Empleado" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //validamos la sesion
    HttpSession sesion = request.getSession();
    List<Empleado> empleados = new ArrayList<>();
    //validamos
    if (sesion != null && sesion.getAttribute("empleados") != null) {
        empleados = (List<Empleado>) sesion.getAttribute("empleados");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Todo Baratito | Listar Categoria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<%
    //recibimos los valores del Servlet
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<div class="container">
    <h1>Listado de Categorias</h1>
    <div>
        <a href="CategoriaServlet?accion=registro" class="btn btn-primary">Registrar Categoria</a>
        <a href="CategoriaServlet?accion=habilita" class="btn btn-warning">Habilitar Categoria</a>
        <a href="CategoriaServlet?accion=menu" class="btn btn-dark">Regresar</a>
    </div>
    <div class="mb-3"></div>
    <!-- inicio de la tabla -->
    <div class="table-responsive">
        <table class="table table-striped table-hover table-bordered">
            <thead class="table-dark">
            <tr>
                <th scope="col">Codigo</th>
                <th scope="col">Nombre</th>
                <th scope="col">Estado</th>
                <th scope="col">Actualizar</th>
                <th scope="col">Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <%
                //recorremos la lista de los valores
                for (Categoria cat : categorias) {
            %>
            <tr>
                <th scope="row"><%= cat.getCodigo()%></th>
                <td><%= cat.getNombre()%></td>
                <td><%= cat.isEstado() ? "Habilitado" : "Deshabilitado"%></td>
                <td><a href="CategoriaServlet?accion=actualiza&id=<%= cat.getCodigo()%>" class="btn btn-success">Seleccionar</a></td>
                <td><a href="CategoriaServlet?accion=eliminar&id=<%= cat.getCodigo()%>" class="btn btn-danger">Seleccionar</a></td>
            </tr>
            <% }%>
            </tbody>
        </table>
    </div>
    <!-- fin de la tabla -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>

