<%@page import="pe.com.todobaratito.model.Marca"%>
<%@page import="pe.com.todobaratito.model.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="pe.com.todobaratito.model.Distrito" %>
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
    Distrito distrito = (Distrito) request.getAttribute("distrito");
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Todo Baratito | Actualizar Distritos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h1>Actualización de Distritos</h1>
        </div>
        <div class="card-body">
            <form method="post" action="DistritoServlet?accion=actualizar" accept-charset="UTF-8">
                <div class="col-5 mb-3">
                    <label for="txtCod" class="form-label">Codigo:</label>
                    <input type="text" class="form-control" id="txtCod" name="txtCod" readonly value="<%= distrito.getCodigo()%>">
                </div>
                <div class="col-5 mb-3">
                    <label for="txtNom" class="form-label">Nombre:</label>
                    <input type="text" class="form-control" id="txtNom" name="txtNom" value="<%= distrito.getNombre()%>" required>
                </div>
                <div class="col-5 mb-3">
                    <label class="form-label">Estado:</label>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true" <%= distrito.isEstado() ? "checked" : "" %>>
                        <label class="form-check-label" for="chkEst">Habilitado</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-success">Actualizar</button>
                <a href="DistritoServlet?accion=regresar" class="btn btn-dark">Regresar</a>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
