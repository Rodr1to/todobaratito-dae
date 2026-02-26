<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Todo Baratito | Ingreso al Sistema</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <!-- agregamos un estilo para el color de fondo -->
    <style>
        body{
            background: linear-gradient(to bottom, #7430F8,#000000);
        }
    </style>
</head>
<body>
<!-- iniciamos el contenedor de bootstrap -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <!-- iniciamos el login -->
    <div class="p-5 rounded-5 shadow-lg bg-light">
        <h1 class="text-center">Ingreso al Sistema</h1>
        <!-- inicio del formulario -->
        <form action="EmpleadoServlet?accion=validar" method="post">
            <div class="mb-3">
                <label for="txtUsu" class="form-label">Usuario:</label>
                <input type="text" class="form-control" id="txtUsu" name="txtUsu" required placeholder="Ingrese el Usuario">
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Clave:</label>
                <input type="password" class="form-control" id="txtCla" name="txtCla" required placeholder="Ingrese la Clave">
            </div>
            <div class="d-flex justify-content-center align-items-center">
                <button type="submit" class="btn btn-primary">Ingresar</button>
            </div>

        </form>
        <!-- fin del formulario -->
        <div class="mb-3"></div>
    </div>
    <!-- fin del login -->
</div>
<!-- fin del contenedor -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>