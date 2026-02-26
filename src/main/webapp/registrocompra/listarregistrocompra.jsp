<%@page import="pe.com.todobaratito.model.RegistroCompra"%>
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
  <title>Todo Baratito | Listar Registro de Compra</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<%
  List<RegistroCompra> registrocompras = (List<RegistroCompra>) request.getAttribute("registrocompras");
%>
<div class="container">
  <h1>Listado de Registros de Compra</h1>
  <div>
    <a href="RegistroCompraServlet?accion=registro" class="btn btn-primary">Registrar Compra</a>
    <a href="RegistroCompraServlet?accion=menu" class="btn btn-dark">Regresar</a>
  </div>
  <div class="mb-3"></div>
  <!-- inicio de la tabla -->
  <div class="table-responsive">
    <table class="table table-striped table-hover table-bordered">
      <thead class="table-dark">
      <tr>
        <th scope="col">Numero</th>
        <th scope="col">Fecha</th>
        <th scope="col">Empleado</th>
        <th scope="col">Proveedor</th>
        <th scope="col">Total</th>
        <th scope="col">Estado</th>
        <th scope="col">Habilitar</th>
        <th scope="col">Anular</th>
      </tr>
      </thead>
      <tbody>
      <%if(registrocompras==null){%>
      <tr>
        <td colspan="8"><h3>No hay datos disponibles</h3></td>
      </tr>
      <% }else{ %>
      <%for (RegistroCompra regcom : registrocompras) {%>
      <tr>
        <th scope="row"><%= regcom.getNumero()%></th>
        <td><%= regcom.getFechacompra()%></td>
        <td><%= regcom.getEmpleado().getNombre()%> <%= regcom.getEmpleado().getApellidoPaterno()%> <%= regcom.getEmpleado().getApellidoMaterno()%></td>
        <td><%= regcom.getProveedor().getNombre()%> - <%= regcom.getProveedor().getRepresentante()%></td>
        <td><%= regcom.getTotal()%></td>
        <td><%= regcom.isEstado() ? "Habilitado" : "Deshabilitado"%></td>
        <td><a href="RegistroCompraServlet?accion=habilitar&id=<%= regcom.getNumero()%>" class="btn btn-warning">Seleccionar</a></td>
        <td><a href="RegistroCompraServlet?accion=deshabilitar&id=<%= regcom.getNumero()%>" class="btn btn-danger">Seleccionar</a></td>
      </tr>
      <% }%>
      <% } %>
      </tbody>
    </table>
  </div>
  <!-- fin de la tabla -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>



