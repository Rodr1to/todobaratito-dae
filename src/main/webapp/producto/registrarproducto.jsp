<%@page import="pe.com.todobaratito.model.Marca"%>
<%@page import="pe.com.todobaratito.model.Categoria"%>
<%@page import="pe.com.todobaratito.model.Proveedor"%>
<%@page import="pe.com.todobaratito.model.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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

    List<Marca> marcas = (List<Marca>) request.getAttribute("listaMarcas");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("listaCategorias");
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("listaProveedores");
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Todo Baratito | Registrar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4 mb-5">
    <div class="card">
        <div class="card-header">
            <h1>Registro de Producto</h1>
        </div>
        <div class="card-body">
            <form method="post" action="ProductoServlet?accion=registrar" accept-charset="UTF-8">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="txtNom" class="form-label">Nombre del Producto:</label>
                        <input type="text" class="form-control" id="txtNom" name="txtNom" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtPre" class="form-label">Precio Unitario:</label>
                        <input type="number" step="0.01" class="form-control" id="txtPre" name="txtPre" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="txtDes" class="form-label">Descripción:</label>
                    <textarea class="form-control" id="txtDes" name="txtDes" rows="2"></textarea>
                </div>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="txtCan" class="form-label">Cantidad Inicial:</label>
                        <input type="number" class="form-control" id="txtCan" name="txtCan" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="txtSto" class="form-label">Stock Mínimo:</label>
                        <input type="number" class="form-control" id="txtSto" name="txtSto" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="txtFecIng" class="form-label">Fecha de Ingreso:</label>
                        <input type="date" class="form-control" id="txtFecIng" name="txtFecIng" required>
                    </div>
                </div>

                <hr>
                <h5 class="mb-3">Asignación de Llaves Foráneas</h5>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="cboMarca" class="form-label">Marca:</label>
                        <select class="form-select" id="cboMarca" name="cboMarca" required>
                            <option value="">Seleccione Marca</option>
                            <% if(marcas != null) {
                                for(Marca m : marcas) { %>
                            <option value="<%= m.getCodigo() %>"><%= m.getNombre() %></option>
                            <%  } } %>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="cboCategoria" class="form-label">Categoría:</label>
                        <select class="form-select" id="cboCategoria" name="cboCategoria" required>
                            <option value="">Seleccione Categoría</option>
                            <% if(categorias != null) {
                                for(Categoria c : categorias) { %>
                            <option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
                            <%  } } %>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="cboProveedor" class="form-label">Proveedor:</label>
                        <select class="form-select" id="cboProveedor" name="cboProveedor" required>
                            <option value="">Seleccione Proveedor</option>
                            <% if(proveedores != null) {
                                for(Proveedor p : proveedores) { %>
                            <option value="<%= p.getCodigo() %>"><%= p.getNombre() %></option>
                            <%  } } %>
                        </select>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Estado:</label>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true" checked>
                        <label class="form-check-label" for="chkEst">Habilitado</label>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Registrar</button>
                <a href="ProductoServlet?accion=regresar" class="btn btn-dark">Regresar</a>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
