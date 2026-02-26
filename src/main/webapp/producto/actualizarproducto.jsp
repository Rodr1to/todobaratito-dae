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
    Producto producto = (Producto) request.getAttribute("producto");
    List<Marca> marcas = (List<Marca>) request.getAttribute("listaMarcas");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("listaCategorias");
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("listaProveedores");
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Actualizar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4 mb-5">
    <div class="card">
        <div class="card-header"><h1>Actualización de Producto</h1></div>
        <div class="card-body">
            <form method="post" action="ProductoServlet?accion=actualizar" accept-charset="UTF-8">
                <div class="mb-3 col-3">
                    <label class="form-label">Código:</label>
                    <input type="text" class="form-control" name="txtCod" value="<%= producto.getCodigo() %>" readonly>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Nombre del Producto:</label>
                        <input type="text" class="form-control" name="txtNom" value="<%= producto.getNombre() %>" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Precio Unitario:</label>
                        <input type="number" step="0.01" class="form-control" name="txtPre" value="<%= producto.getPrecio() %>" required>
                    </div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Descripción:</label>
                    <textarea class="form-control" name="txtDes" rows="2"><%= producto.getDescripcion() %></textarea>
                </div>
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Cantidad Inicial:</label>
                        <input type="number" class="form-control" name="txtCan" value="<%= producto.getCantidad() %>" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Stock Mínimo:</label>
                        <input type="number" class="form-control" name="txtSto" value="<%= producto.getStockminimo() %>" required>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Marca:</label>
                        <select class="form-select" name="cboMarca" required>
                            <% for(Marca m : marcas) { %>
                            <option value="<%= m.getCodigo() %>" <%= producto.getMarca().getCodigo() == m.getCodigo() ? "selected" : "" %>><%= m.getNombre() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Categoría:</label>
                        <select class="form-select" name="cboCategoria" required>
                            <% for(Categoria c : categorias) { %>
                            <option value="<%= c.getCodigo() %>" <%= producto.getCategoria().getCodigo() == c.getCodigo() ? "selected" : "" %>><%= c.getNombre() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Proveedor:</label>
                        <select class="form-select" name="cboProveedor" required>
                            <% for(Proveedor p : proveedores) { %>
                            <option value="<%= p.getCodigo() %>" <%= producto.getProveedor().getCodigo() == p.getCodigo() ? "selected" : "" %>><%= p.getNombre() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" name="chkEst" value="true" <%= producto.isEstado() ? "checked" : "" %>>
                    <label class="form-check-label">Habilitado</label>
                </div>
                <button type="submit" class="btn btn-success">Actualizar</button>
                <a href="ProductoServlet?accion=regresar" class="btn btn-dark">Regresar</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
