<%@page import="pe.com.todobaratito.model.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
    <title>Todo Baratito | Menu Principal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <!-- inicio del menu -->
    <nav class="navbar bg-dark navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Todo Baratito</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Quienes Somos</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Mantenimiento Simple
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="CategoriaServlet">Categoria</a></li>
                            <li><a class="dropdown-item" href="MarcaServlet">Marca</a></li>
                            <li><a class="dropdown-item" href="RolServlet">Rol</a></li>
                            <li><a class="dropdown-item" href="SexoServlet">Sexo</a></li>
                            <li><a class="dropdown-item" href="DistritoServlet">Distrito</a></li>
                            <li><a class="dropdown-item" href="TipoDocumentoServlet">Tipo de Documento</a></li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Mantenimiento Cruzado
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="ProductoServlet">Producto</a></li>
                            <li><a class="dropdown-item" href="ProveedorServlet">Proveedor</a></li>
                            <li><a class="dropdown-item" href="EmpleadoServlet">Empleado</a></li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Proceso
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="RegistroCompraServlet">Registro de Compra</a></li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Cuenta
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Usuario: <%= empleados.get(0).getUsuario()%></a></li>
                            <li><a class="dropdown-item" href="#">Empleado: <%= empleados.get(0).getNombre()%> <%= empleados.get(0).getApellidoPaterno()%> <%= empleados.get(0).getApellidoMaterno()%></a></li>
                            <li><a class="dropdown-item" href="#">Cargo: <%= empleados.get(0).getRol().getNombre()%></a></li>
                            <li><a class="dropdown-item" href="EmpleadoServlet?accion=cerrarsesion">Cerrar Sesion</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- fin del menu -->
    <div class="mb-3"></div>

    <!-- inicio de un card -->
    <div class="card" style="width: 25rem; margin: auto;">
        <img src="img/logo.jpg" class="card-img-top" alt="Logo Todo Baratito">
        <div class="card-body">
            <h2 class="card-title text-center">Sistema de Gestion de Compras</h2>
            <p class="card-text">Bienvenidos al Sistema de Gestion de Compras Web de la empresa Todo Baratito</p>
        </div>
    </div>
    <!-- fin del card -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>





