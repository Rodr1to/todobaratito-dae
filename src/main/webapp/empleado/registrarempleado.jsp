<%@page import="pe.com.todobaratito.model.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  HttpSession sesion = request.getSession();
  if (sesion == null || sesion.getAttribute("empleados") == null) {
    response.sendRedirect(request.getContextPath() + "/index.jsp");
    return;
  }
  List<Distrito> distritos = (List<Distrito>) request.getAttribute("listaDistritos");
  List<Rol> roles = (List<Rol>) request.getAttribute("listaRoles");
  List<Sexo> sexos = (List<Sexo>) request.getAttribute("listaSexos");
  List<TipoDocumento> tiposDoc = (List<TipoDocumento>) request.getAttribute("listaTipos");
%>
<!doctype html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <title>Todo Baratito | Registrar Empleado</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4 mb-5">
  <div class="card">
    <div class="card-header">
      <h1>Registro de Empleado</h1>
    </div>
    <div class="card-body">
      <form method="post" action="EmpleadoServlet?accion=registrar" accept-charset="UTF-8">
        <h5 class="mb-3">Datos Personales</h5>
        <div class="row">
          <div class="col-md-4 mb-3"><label>Nombre:</label><input type="text" name="txtNom" class="form-control" required></div>
          <div class="col-md-4 mb-3"><label>Apellido Paterno:</label><input type="text" name="txtApeP" class="form-control" required></div>
          <div class="col-md-4 mb-3"><label>Apellido Materno:</label><input type="text" name="txtApeM" class="form-control" required></div>
        </div>
        <div class="row">
          <div class="col-md-3 mb-3">
            <label>Tipo Doc:</label>
            <select name="cboTipoDoc" class="form-select" required>
              <option value="">Seleccione</option>
              <% if(tiposDoc!=null){ for(TipoDocumento td : tiposDoc){ %><option value="<%=td.getCodigo()%>"><%=td.getNombre()%></option><% }} %>
            </select>
          </div>
          <div class="col-md-3 mb-3"><label>Nro. Documento:</label><input type="text" name="txtDoc" class="form-control" required></div>
          <div class="col-md-3 mb-3"><label>Fecha Nacimiento:</label><input type="date" name="txtFecNac" class="form-control" required></div>
          <div class="col-md-3 mb-3">
            <label>Sexo:</label>
            <select name="cboSexo" class="form-select" required>
              <option value="">Seleccione</option>
              <% if(sexos!=null){ for(Sexo s : sexos){ %><option value="<%=s.getCodigo()%>"><%=s.getNombre()%></option><% }} %>
            </select>
          </div>
        </div>

        <hr><h5 class="mb-3">Contacto y Ubicación</h5>
        <div class="row">
          <div class="col-md-6 mb-3"><label>Dirección:</label><input type="text" name="txtDir" class="form-control" required></div>
          <div class="col-md-3 mb-3">
            <label>Distrito:</label>
            <select name="cboDistrito" class="form-select" required>
              <option value="">Seleccione</option>
              <% if(distritos!=null){ for(Distrito d : distritos){ %><option value="<%=d.getCodigo()%>"><%=d.getNombre()%></option><% }} %>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4 mb-3"><label>Teléfono:</label><input type="text" name="txtTel" class="form-control"></div>
          <div class="col-md-4 mb-3"><label>Celular:</label><input type="text" name="txtCel" class="form-control" required></div>
          <div class="col-md-4 mb-3"><label>Correo Electrónico:</label><input type="email" name="txtCor" class="form-control" required></div>
        </div>

        <hr><h5 class="mb-3">Datos Laborales y Sistema</h5>
        <div class="row">
          <div class="col-md-3 mb-3"><label>Cargo:</label><input type="text" name="txtCar" class="form-control" required></div>
          <div class="col-md-3 mb-3"><label>Fecha de Ingreso:</label><input type="date" name="txtFecIng" class="form-control" required></div>
          <div class="col-md-3 mb-3">
            <label>Rol de Sistema:</label>
            <select name="cboRol" class="form-select" required>
              <option value="">Seleccione</option>
              <% if(roles!=null){ for(Rol r : roles){ %><option value="<%=r.getCodigo()%>"><%=r.getNombre()%></option><% }} %>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4 mb-3"><label>Usuario:</label><input type="text" name="txtUsu" class="form-control" required></div>
          <div class="col-md-4 mb-3"><label>Clave:</label><input type="password" name="txtCla" class="form-control" required></div>
          <div class="col-md-4 mb-3 d-flex align-items-end">
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true" checked>
              <label class="form-check-label" for="chkEst">Habilitado</label>
            </div>
          </div>
        </div>

        <div class="mt-3">
          <button type="submit" class="btn btn-primary">Registrar</button>
          <a href="EmpleadoServlet?accion=regresar" class="btn btn-dark">Regresar</a>
        </div>
      </form>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
