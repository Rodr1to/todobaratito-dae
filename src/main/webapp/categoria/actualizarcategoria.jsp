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
    <title>Todo Baratito | Actualizar Categoria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<%
    //recibimos los valores del Servlet
    Categoria categorias = (Categoria) request.getAttribute("categorias");
%>
<div class="container">
    <h1>Actualizacion de Categoria</h1>
    <form method="post" action="CategoriaServlet?accion=actualizar&id" accept-charset="UTF-8">
        <div class="col-5">
            <label for="txtCod" class="form-label">Codigo:</label>
            <input type="text" class="form-control" id="txtCod" name="txtCod" readonly value="<%= categorias.getCodigo()%>">
        </div>
        <div class="col-5">
            <label for="txtNom" class="form-label">Nombre:</label>
            <input type="text" class="form-control" id="txtNom" name="txtNom" value="<%= categorias.getNombre()%>">
        </div>
        <div class="col-5">
            <label for="exampleInputPassword1" class="form-label">Estado:</label>
            <div class="mb-3 form-check">
                <%if (categorias.isEstado()) {%>
                <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true" checked="true">
                <% } else {%>
                <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true" checked="false">
                <% }%>
                <label class="form-check-label" for="chkEst">Habilitado</label>
            </div>
        </div>
        <button type="submit" class="btn btn-success">Actualizar</button>
        <a href="CategoriaServlet?accion=regresar" class="btn btn-dark">Regresar</a>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>

