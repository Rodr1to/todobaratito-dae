<%@page import="pe.com.todobaratito.model.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  HttpSession sesion = request.getSession();
  if (sesion == null || sesion.getAttribute("empleados") == null) {
    response.sendRedirect(request.getContextPath() + "/index.jsp"); return;
  }
  List<Empleado> listaEmpleados = (List<Empleado>) request.getAttribute("empleados");
%>
<!doctype html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <title>Habilitar Empleado</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h1>Habilitar / Deshabilitar Empleados</h1>
  <div class="mb-3"><a href="EmpleadoServlet?accion=regresar" class="btn btn-dark">Regresar</a></div>

  <div class="table-responsive">
    <table class="table table-striped table-hover table-bordered">
      <thead class="table-dark">
      <tr>
        <th>Codigo</th>
        <th>Nombre Completo</th>
        <th>Usuario</th>
        <th>Rol</th>
        <th>Estado</th>
        <th>Habilitar</th>
        <th>Deshabilitar</th>
      </tr>
      </thead>
      <tbody>
      <% if(listaEmpleados != null) {
        for (Empleado obj : listaEmpleados) { %>
      <tr>
        <th scope="row"><%= obj.getCodigo()%></th>
        <td><%= obj.getNombre()%> <%= obj.getApellidoPaterno()%> <%= obj.getApellidoMaterno()%></td>
        <td><%= obj.getUsuario()%></td>
        <td><%= obj.getRol().getNombre()%></td>
        <td><%= obj.isEstado() ? "Habilitado" : "Deshabilitado"%></td>
        <td><a href="EmpleadoServlet?accion=habilitar&id=<%= obj.getCodigo()%>" class="btn btn-warning">Seleccionar</a></td>
        <td><a href="EmpleadoServlet?accion=deshabilitar&id=<%= obj.getCodigo()%>" class="btn btn-danger">Seleccionar</a></td>
      </tr>
      <%  } } %>
      </tbody>
    </table>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
