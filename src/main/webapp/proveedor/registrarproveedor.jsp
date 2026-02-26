<%@page import="pe.com.todobaratito.model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  if (request.getSession().getAttribute("empleados") == null) {
    response.sendRedirect(request.getContextPath() + "/index.jsp"); return;
  }
  List<Distrito> distritos = (List<Distrito>) request.getAttribute("listaDistritos");
%>
<!doctype html>
<html lang="es">
<head><meta charset="utf-8"><title>Registrar Proveedor</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<div class="container mt-4 mb-5">
  <div class="card">
    <div class="card-header"><h1>Registro Proveedor</h1></div>
    <div class="card-body">
      <form method="post" action="ProveedorServlet?accion=registrar">
        <div class="row">
          <div class="col-6 mb-3"><label>RUC:</label><input type="text" name="txtRuc" class="form-control" required></div>
          <div class="col-6 mb-3"><label>Razón Social:</label><input type="text" name="txtNom" class="form-control" required></div>
        </div>
        <div class="row">
          <div class="col-6 mb-3"><label>Representante:</label><input type="text" name="txtRep" class="form-control" required></div>
          <div class="col-6 mb-3"><label>Contacto Directo:</label><input type="text" name="txtCon" class="form-control" required></div>
        </div>
        <div class="mb-3"><label>Dirección:</label><input type="text" name="txtDir" class="form-control" required></div>
        <div class="row">
          <div class="col-4 mb-3"><label>Teléfono:</label><input type="text" name="txtTel" class="form-control"></div>
          <div class="col-4 mb-3"><label>Celular:</label><input type="text" name="txtCel" class="form-control"></div>
          <div class="col-4 mb-3"><label>Correo:</label><input type="email" name="txtCor" class="form-control"></div>
        </div>
        <div class="col-4 mb-3">
          <label>Distrito:</label>
          <select name="cboDistrito" class="form-select" required>
            <option value="">Seleccione</option>
            <% if(distritos != null) { for(Distrito d : distritos) { %>
            <option value="<%= d.getCodigo() %>"><%= d.getNombre() %></option>
            <% } } %>
          </select>
        </div>
        <div class="form-check mb-3"><input type="checkbox" name="chkEst" value="true" class="form-check-input" checked><label>Habilitado</label></div>
        <button type="submit" class="btn btn-primary">Registrar</button> <a href="ProveedorServlet" class="btn btn-dark">Regresar</a>
      </form>
    </div>
  </div>
</div>
</body>
</html>
