<%@page import="pe.com.todobaratito.model.RegistroCompra"%>
<%@page import="pe.com.todobaratito.model.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pe.com.todobaratito.model.Empleado"%>
<%@page import="pe.com.todobaratito.model.Proveedor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession();
    List<Empleado> empleados = new ArrayList<>();

    if (sesion != null && sesion.getAttribute("empleados") != null) {
        empleados = (List<Empleado>) sesion.getAttribute("empleados");
    } else {
        response.sendRedirect("index.jsp");
        return;
    }

    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
    Integer numeroObj = (Integer) request.getAttribute("numero");
    int numero = (numeroObj != null) ? numeroObj : 0;

    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>

<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registro de Compra</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">

    <script>
        function actualizarfechayhora() {
            var fecha = new Date();
            var dia = String(fecha.getDate()).padStart(2, '0');
            var mes = String(fecha.getMonth() + 1).padStart(2, '0');
            var anio = fecha.getFullYear();
            var hora = String(fecha.getHours()).padStart(2, '0');
            var minutos = String(fecha.getMinutes()).padStart(2, '0');
            var segundos = String(fecha.getSeconds()).padStart(2, '0');
            var fechaHora = dia + '/' + mes + '/' + anio + ' ' + hora + ':' + minutos + ':' + segundos;
            document.getElementById("txtFec").value = fechaHora;
        }
        setInterval(actualizarfechayhora, 1000);
        window.onload = actualizarfechayhora;

        /* ===== ACTUALIZAR PRODUCTO ===== */
        function actualizarProducto(sel, index) {
            let cod = sel.options[sel.selectedIndex].getAttribute("data-cod");
            let pre = sel.options[sel.selectedIndex].getAttribute("data-pre");
            document.getElementById("cod_" + index).value = cod || "";
            document.getElementById("pre_" + index).value = pre || "";
        }

        /* ===== AGREGAR FILA ===== */
        function agregarFila() {
            let tabla = document.getElementById("detallePedido");
            let index = tabla.rows.length;

            let fila = tabla.insertRow();
            fila.innerHTML =
                "<td><input type='text' class='form-control' name='codigos[" + index + "]' id='cod_" + index + "' readonly></td>" +
                "<td><select class='form-select' name='productos[" + index + "]' onchange='actualizarProducto(this," + index + ")' required>" +
                "<option value=''>Seleccione</option>" +
                document.getElementById("productosOpciones").innerHTML +
                "</select></td>" +
                "<td><input type='text' class='form-control' name='precios[" + index + "]' id='pre_" + index + "' readonly></td>" +
                "<td><input type='number' class='form-control' name='cantidades[" + index + "]' min='1' required></td>" +
                "<td><button type='button' class='btn btn-danger' onclick='eliminarFila(this)'>Eliminar</button></td>";

            document.getElementById("txtCanPed").value = tabla.rows.length;
        }

        /* ===== ELIMINAR FILA ===== */
        function eliminarFila(btn) {
            let tabla = document.getElementById("detallePedido");
            if (tabla.rows.length > 1) {
                btn.closest("tr").remove();
                document.getElementById("txtCanPed").value = tabla.rows.length;
            }
        }
    </script>
</head>

<body>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h1>Registro de Compra</h1>
        </div>

        <div class="card-body">
            <form method="post" action="RegistroCompraServlet?accion=registrar">

                <div class="col-5 mb-3">
                    <label class="form-label">Numero de Compra:</label>
                    <input type="text" class="form-control" readonly value="<%= numero%>">
                </div>

                <div class="col-5 mb-3">
                    <label class="form-label">Fecha:</label>
                    <input type="text" class="form-control" id="txtFec" name="txtFec" readonly>
                </div>

                <div class="col-5 mb-3">
                    <label class="form-label">Empleado:</label>
                    <input type="hidden" id="txtCodEmp"name="txtCodEmp" value="<%= empleados.get(0).getCodigo()%>">
                    <input type="text" class="form-control" readonly
                           value="<%= empleados.get(0).getNombre()%> <%= empleados.get(0).getApellidoPaterno()%> <%= empleados.get(0).getApellidoMaterno()%>">
                </div>
                <div class="col-5 mb-3">
                    <label class="form-label">Proveedor:</label>
                    <select class="form-select" id="cboProveedor" name="cboProveedor" required>
                        <% if (proveedores != null) { %>
                        <option value="0">Seleccione un proveedor</option>
                        <% for (Proveedor prov : proveedores) {%>
                        <option value="<%= prov.getCodigo()%>"><%= prov.getNombre()%></option>
                        <% }
                        } else { %>
                        <option value="0">No hay proveedores registrados</option>
                        <% } %>
                    </select>
                </div>
                <div class="col-5">
                    <label for="txtObs" class="form-label">Observaciones:</label>
                    <textarea class="form-control" id="txtObs" name="txtObs" rows="3"></textarea>
                </div>
                <div class="col-5">
                    <label for="exampleInputPassword1" class="form-label">Estado:</label>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true">
                        <label class="form-check-label" for="chkEst">Habilitado</label>
                    </div>
                </div>

                <hr>
                <div class="mb-3"></div>
                <hr>
                <h3>Detalle de Pedido</h3>
                <div class="col-3 mb-3">
                    <label class="form-label">Cantidad de productos</label>
                    <input type="text" class="form-control" id="txtCanPed" name="txtCanPed" readonly value="1">
                </div>
                <button type="button" class="btn btn-dark mb-3" onclick="agregarFila()">Agregar Producto</button>
                <!-- TABLA -->
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead class="table-dark">
                        <tr>
                            <th>Código</th>
                            <th>Producto</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Eliminar</th>
                        </tr>
                        </thead>
                        <tbody id="detallePedido">
                        <tr>
                            <td><input type="text" class="form-control" id="cod_0" name="codigos[0]" readonly></td>
                            <td>
                                <select class="form-select" name="productos[0]" onchange="actualizarProducto(this, 0)" required>
                                    <option value="">Seleccione</option>
                                    <% for (Producto p : productos) {%>
                                    <option value="<%=p.getCodigo()%>"
                                            data-cod="<%=p.getCodigo()%>"
                                            data-pre="<%=p.getPrecio()%>">
                                        <%=p.getNombre()%>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                            <td><input type="text" class="form-control" id="pre_0" name="precios[0]" readonly></td>
                            <td><input type="number" class="form-control" name="cantidades[0]" min="1" required></td>
                            <td><button type="button" class="btn btn-danger" onclick="eliminarFila(this)">Eliminar</button></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- OPCIONES OCULTAS DE PRODUCTOS -->
                <select id="productosOpciones" style="display:none;">
                    <% for (Producto p : productos) {%>
                    <option value="<%=p.getCodigo()%>"
                            data-cod="<%=p.getCodigo()%>"
                            data-pre="<%=p.getPrecio()%>">
                        <%=p.getNombre()%>
                    </option>
                    <% }%>
                </select>
                <button type="submit" class="btn btn-primary">Registrar</button>
                <a href="RegistroCompraServlet?accion=regresar" class="btn btn-dark">Regresar</a>

            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


