package pe.com.todobaratito.model;

import java.util.Date;

public class Producto {
    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private int stockminimo;
    private Date fechaingreso;
    private boolean estado;
    private Marca marca;
    private Categoria categoria;
    private Proveedor proveedor;

    public Producto() {
    }

    public Producto(int codigo, String nombre, String descripcion, double precio, int cantidad, int stockminimo, Date fechaingreso, boolean estado, Marca marca, Categoria categoria, Proveedor proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.stockminimo = stockminimo;
        this.fechaingreso = fechaingreso;
        this.estado = estado;
        this.marca = marca;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStockminimo() {
        return stockminimo;
    }

    public void setStockminimo(int stockminimo) {
        this.stockminimo = stockminimo;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }



}

