<%@page import="pe.com.todobaratito.model.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  HttpSession sesion = request.getSession();
  if (sesion == null || sesion.getAttribute("empleados") == null) {
    response.sendRedirect(request.getContextPath() + "/index.jsp"); return;
  }
  Empleado empleado = (Empleado) request.getAttribute("empleado");
  List<Distrito> distritos = (List<Distrito>) request.getAttribute("listaDistritos");
  List<Rol> roles = (List<Rol>) request.getAttribute("listaRoles");
  List<Sexo> sexos = (List<Sexo>) request.getAttribute("listaSexos");
  List<TipoDocumento> tiposDoc = (List<TipoDocumento>) request.getAttribute("listaTipos");

  // Formateo de fechas para que se muestren correctamente en el input type="date"
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  String fecNac = (empleado.getFechaNacimiento() != null) ? sdf.format(empleado.getFechaNacimiento()) : "";
  String fecIng = (empleado.getFechaIngreso() != null) ? sdf.format(empleado.getFechaIngreso()) : "";
%>
<!doctype html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <title>Actualizar Empleado</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4 mb-5">
  <div class="card">
    <div class="card-header"><h1>Actualización de Empleado</h1></div>
    <div class="card-body">
      <form method="post" action="EmpleadoServlet?accion=actualizar" accept-charset="UTF-8">
        <input type="hidden" name="txtCod" value="<%= empleado.getCodigo() %>">
        <h5 class="mb-3">Datos Personales</h5>
        <div class="row">
          <div class="col-md-4 mb-3"><label>Nombre:</label><input type="text" name="txtNom" value="<%= empleado.getNombre() %>" class="form-control" required></div>
          <div class="col-md-4 mb-3"><label>Apellido Paterno:</label><input type="text" name="txtApeP" value="<%= empleado.getApellidoPaterno() %>" class="form-control" required></div>
          <div class="col-md-4 mb-3"><label>Apellido Materno:</label><input type="text" name="txtApeM" value="<%= empleado.getApellidoMaterno() %>" class="form-control" required></div>
        </div>
        <div class="row">
          <div class="col-md-3 mb-3">
            <label>Tipo Doc:</label>
            <select name="cboTipoDoc" class="form-select" required>
              <% for(TipoDocumento td : tiposDoc){ %>
              <option value="<%=td.getCodigo()%>" <%= empleado.getTipoDocumento().getCodigo() == td.getCodigo() ? "selected" : "" %>><%=td.getNombre()%></option>
              <% } %>
            </select>
          </div>
          <div class="col-md-3 mb-3"><label>Nro. Documento:</label><input type="text" name="txtDoc" value="<%= empleado.getDocumento() %>" class="form-control" required></div>
          <div class="col-md-3 mb-3"><label>Fecha Nacimiento:</label><input type="date" name="txtFecNac" value="<%= fecNac %>" class="form-control" required></div>
          <div class="col-md-3 mb-3">
            <label>Sexo:</label>
            <select name="cboSexo" class="form-select" required>
              <% for(Sexo s : sexos){ %>
              <option value="<%=s.getCodigo()%>" <%= empleado.getSexo().getCodigo() == s.getCodigo() ? "selected" : "" %>><%=s.getNombre()%></option>
              <% } %>
            </select>
          </div>
        </div>
        <hr><h5 class="mb-3">Contacto y Ubicación</h5>
        <div class="row">
          <div class="col-md-6 mb-3"><label>Dirección:</label><input type="text" name="txtDir" value="<%= empleado.getDireccion() %>" class="form-control" required></div>
          <div class="col-md-3 mb-3">
            <label>Distrito:</label>
            <select name="cboDistrito" class="form-select" required>
              <% for(Distrito d : distritos){ %>
              <option value="<%=d.getCodigo()%>" <%= empleado.getDistrito().getCodigo() == d.getCodigo() ? "selected" : "" %>><%=d.getNombre()%></option>
              <% } %>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4 mb-3"><label>Teléfono:</label><input type="text" name="txtTel" value="<%= empleado.getTelefono() %>" class="form-control"></div>
          <div class="col-md-4 mb-3"><label>Celular:</label><input type="text" name="txtCel" value="<%= empleado.getCelular() %>" class="form-control" required></div>
          <div class="col-md-4 mb-3"><label>Correo Electrónico:</label><input type="email" name="txtCor" value="<%= empleado.getCorreo() %>" class="form-control" required></div>
        </div>
        <hr><h5 class="mb-3">Datos Laborales y Sistema</h5>
        <div class="row">
          <div class="col-md-3 mb-3"><label>Cargo:</label><input type="text" name="txtCar" value="<%= empleado.getCargo() %>" class="form-control" required></div>
          <div class="col-md-3 mb-3"><label>Fecha de Ingreso:</label><input type="date" name="txtFecIng" value="<%= fecIng %>" class="form-control" required></div>
          <div class="col-md-3 mb-3">
            <label>Rol de Sistema:</label>
            <select name="cboRol" class="form-select" required>
              <% for(Rol r : roles){ %>
              <option value="<%=r.getCodigo()%>" <%= empleado.getRol().getCodigo() == r.getCodigo() ? "selected" : "" %>><%=r.getNombre()%></option>
              <% } %>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4 mb-3"><label>Usuario:</label><input type="text" name="txtUsu" value="<%= empleado.getUsuario() %>" class="form-control" required></div>
          <div class="col-md-4 mb-3"><label>Clave:</label><input type="text" name="txtCla" value="<%= empleado.getClave() %>" class="form-control" required></div>
          <div class="col-md-4 mb-3 d-flex align-items-end">
            <div class="form-check">
              <input type="checkbox" class="form-check-input" name="chkEst" value="true" <%= empleado.isEstado() ? "checked" : "" %>>
              <label class="form-check-label">Habilitado</label>
            </div>
          </div>
        </div>
        <div class="mt-3">
          <button type="submit" class="btn btn-success">Actualizar</button>
          <a href="EmpleadoServlet?accion=regresar" class="btn btn-dark">Regresar</a>
        </div>
      </form>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
