<%@page import="pe.com.todobaratito.model.TipoDocumento"%>
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
    List<TipoDocumento> tipodocumentos = (List<TipoDocumento>) request.getAttribute("tipodocumentos");
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Todo Baratito | Listar Tipo de Documento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Listado de Tipos de Documento</h1>
    <div class="mb-3">
        <a href="TipoDocumentoServlet?accion=registro" class="btn btn-primary">Registrar Tipo Doc</a>
        <a href="TipoDocumentoServlet?accion=habilita" class="btn btn-warning">Habilitar Tipo Doc</a>
        <a href="TipoDocumentoServlet?accion=menu" class="btn btn-dark">Regresar al Menú</a>
    </div>

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
            <% if(tipodocumentos != null) {
                for (TipoDocumento obj : tipodocumentos) { %>
            <tr>
                <th scope="row"><%= obj.getCodigo()%></th>
                <td><%= obj.getNombre()%></td>
                <td><%= obj.isEstado() ? "Habilitado" : "Deshabilitado"%></td>
                <td><a href="TipoDocumentoServlet?accion=actualiza&id=<%= obj.getCodigo()%>" class="btn btn-success">Seleccionar</a></td>
                <td><a href="TipoDocumentoServlet?accion=eliminar&id=<%= obj.getCodigo()%>" class="btn btn-danger">Seleccionar</a></td>
            </tr>
            <%  } } %>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
