<%@page import="pe.com.todobaratito.model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  if (request.getSession().getAttribute("empleados") == null) {
    response.sendRedirect(request.getContextPath() + "/index.jsp"); return;
  }
  List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
%>
<!doctype html>
<html lang="es">
<head><meta charset="utf-8"><title>Listar Proveedor</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<div class="container mt-4">
  <h1>Listado de Proveedores</h1>
  <div class="mb-3">
    <a href="ProveedorServlet?accion=registro" class="btn btn-primary">Registrar</a>
    <a href="ProveedorServlet?accion=habilita" class="btn btn-warning">Habilitar</a>
    <a href="ProveedorServlet?accion=menu" class="btn btn-dark">Menú</a>
  </div>
  <table class="table table-striped table-bordered">
    <thead class="table-dark">
    <tr><th>RUC</th><th>Nombre</th><th>Representante</th><th>Distrito</th><th>Celular</th><th>Actualizar</th><th>Eliminar</th></tr>
    </thead>
    <tbody>
    <% if(proveedores != null) { for (Proveedor p : proveedores) { %>
    <tr>
      <td><%= p.getRuc()%></td><td><%= p.getNombre()%></td><td><%= p.getRepresentante()%></td>
      <td><%= p.getDistrito().getNombre()%></td><td><%= p.getCelular()%></td>
      <td><a href="ProveedorServlet?accion=actualiza&id=<%= p.getCodigo()%>" class="btn btn-success">Selec</a></td>
      <td><a href="ProveedorServlet?accion=eliminar&id=<%= p.getCodigo()%>" class="btn btn-danger">Selec</a></td>
    </tr>
    <% } } %>
    </tbody>
  </table>
</div>
</body>
</html>
