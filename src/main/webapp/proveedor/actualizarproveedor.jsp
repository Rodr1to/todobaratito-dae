<%@page import="pe.com.todobaratito.model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  if (request.getSession().getAttribute("empleados") == null) { response.sendRedirect(request.getContextPath() + "/index.jsp"); return; }
  Proveedor p = (Proveedor) request.getAttribute("proveedor");
  List<Distrito> distritos = (List<Distrito>) request.getAttribute("listaDistritos");
%>
<!doctype html>
<html lang="es">
<head><meta charset="utf-8"><title>Actualizar Proveedor</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<div class="container mt-4 mb-5">
  <div class="card">
    <div class="card-header"><h1>Actualizar Proveedor</h1></div>
    <div class="card-body">
      <form method="post" action="ProveedorServlet?accion=actualizar">
        <input type="hidden" name="txtCod" value="<%= p.getCodigo() %>">
        <div class="row">
          <div class="col-6 mb-3"><label>RUC:</label><input type="text" name="txtRuc" value="<%= p.getRuc() %>" class="form-control" required></div>
          <div class="col-6 mb-3"><label>Razón Social:</label><input type="text" name="txtNom" value="<%= p.getNombre() %>" class="form-control" required></div>
        </div>
        <div class="row">
          <div class="col-6 mb-3"><label>Representante:</label><input type="text" name="txtRep" value="<%= p.getRepresentante() %>" class="form-control" required></div>
          <div class="col-6 mb-3"><label>Contacto Directo:</label><input type="text" name="txtCon" value="<%= p.getContacto() %>" class="form-control" required></div>
        </div>
        <div class="mb-3"><label>Dirección:</label><input type="text" name="txtDir" value="<%= p.getDireccion() %>" class="form-control" required></div>
        <div class="row">
          <div class="col-4 mb-3"><label>Teléfono:</label><input type="text" name="txtTel" value="<%= p.getTelefono() %>" class="form-control"></div>
          <div class="col-4 mb-3"><label>Celular:</label><input type="text" name="txtCel" value="<%= p.getCelular() %>" class="form-control"></div>
          <div class="col-4 mb-3"><label>Correo:</label><input type="email" name="txtCor" value="<%= p.getCorreo() %>" class="form-control"></div>
        </div>
        <div class="col-4 mb-3">
          <label>Distrito:</label>
          <select name="cboDistrito" class="form-select" required>
            <% for(Distrito d : distritos) { %>
            <option value="<%= d.getCodigo() %>" <%= p.getDistrito().getCodigo() == d.getCodigo() ? "selected" : "" %>><%= d.getNombre() %></option>
            <% } %>
          </select>
        </div>
        <div class="form-check mb-3"><input type="checkbox" name="chkEst" value="true" class="form-check-input" <%= p.isEstado() ? "checked" : "" %>><label>Habilitado</label></div>
        <button type="submit" class="btn btn-success">Actualizar</button> <a href="ProveedorServlet" class="btn btn-dark">Regresar</a>
      </form>
    </div>
  </div>
</div>
</body>
</html>
