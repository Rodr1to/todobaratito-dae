<%@page import="pe.com.todobaratito.model.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  HttpSession sesion = request.getSession();
  List<Empleado> empleadosSesion = new ArrayList<>();
  if (sesion != null && sesion.getAttribute("empleados") != null) {
    empleadosSesion = (List<Empleado>) sesion.getAttribute("empleados");
  } else {
    response.sendRedirect(request.getContextPath() + "/index.jsp");
    return;
  }

  List<Empleado> listaEmpleados = (List<Empleado>) request.getAttribute("empleados");
%>
<!doctype html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Todo Baratito | Listar Empleado</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h1>Listado de Empleados</h1>
  <div class="mb-3">
    <a href="EmpleadoServlet?accion=registro" class="btn btn-primary">Registrar Empleado</a>
    <a href="EmpleadoServlet?accion=habilita" class="btn btn-warning">Habilitar Empleado</a>
    <a href="EmpleadoServlet?accion=menu" class="btn btn-dark">Regresar al Menú</a>
  </div>

  <div class="table-responsive">
    <table class="table table-striped table-hover table-bordered">
      <thead class="table-dark">
      <tr>
        <th>Codigo</th>
        <th>Nombre Completo</th>
        <th>Usuario</th>
        <th>Cargo</th>
        <th>Rol</th>
        <th>Distrito</th>
        <th>Estado</th>
        <th>Actualizar</th>
        <th>Eliminar</th>
      </tr>
      </thead>
      <tbody>
      <%
        if (listaEmpleados != null && !listaEmpleados.isEmpty()) {
          for (Empleado obj : listaEmpleados) {
      %>
      <tr>
        <th scope="row"><%= obj.getCodigo()%></th>
        <td><%= obj.getNombre()%> <%= obj.getApellidoPaterno()%> <%= obj.getApellidoMaterno()%></td>
        <td><%= obj.getUsuario()%></td>
        <td><%= obj.getCargo()%></td>
        <td><%= obj.getRol().getNombre()%></td>
        <td><%= obj.getDistrito().getNombre()%></td>
        <td><%= obj.isEstado() ? "Habilitado" : "Deshabilitado"%></td>
        <td><a href="EmpleadoServlet?accion=actualiza&id=<%= obj.getCodigo()%>" class="btn btn-success">Seleccionar</a></td>
        <td><a href="EmpleadoServlet?accion=eliminar&id=<%= obj.getCodigo()%>" class="btn btn-danger">Seleccionar</a></td>
      </tr>
      <%      }
      } else {
      %>
      <tr>
        <td colspan="9" class="text-center">No hay empleados registrados</td>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
