<%@page import="pe.com.todobaratito.model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  if (request.getSession().getAttribute("empleados") == null) { response.sendRedirect(request.getContextPath() + "/index.jsp"); return; }
  List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
%>
<!doctype html>
<html lang="es">
<head><meta charset="utf-8"><title>Habilitar Proveedor</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<div class="container mt-4">
  <h1>Habilitar Proveedores</h1>
  <div class="mb-3"><a href="ProveedorServlet" class="btn btn-dark">Regresar</a></div>
  <table class="table table-striped table-bordered">
    <thead class="table-dark">
    <tr><th>RUC</th><th>Nombre</th><th>Estado</th><th>Habilitar</th><th>Deshabilitar</th></tr>
    </thead>
    <tbody>
    <% if(proveedores != null) { for (Proveedor p : proveedores) { %>
    <tr>
      <td><%= p.getRuc()%></td><td><%= p.getNombre()%></td><td><%= p.isEstado() ? "Habilitado" : "Deshabilitado" %></td>
      <td><a href="ProveedorServlet?accion=habilitar&id=<%= p.getCodigo()%>" class="btn btn-warning">Seleccionar</a></td>
      <td><a href="ProveedorServlet?accion=deshabilitar&id=<%= p.getCodigo()%>" class="btn btn-danger">Seleccionar</a></td>
    </tr>
    <% } } %>
    </tbody>
  </table>
</div>
</body>
</html>
