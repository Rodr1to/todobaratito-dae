package pe.com.todobaratito.model;

public class DetalleRegistroCompra {
    private int numerodetallecompra;
    private int cantidadcompra;
    private double preciocompra;
    private double subtotal;
    private double descuento;
    private RegistroCompra registrocompra;
    private Producto producto;

    public DetalleRegistroCompra() {
    }

    public DetalleRegistroCompra(int numerodetallecompra, int cantidadcompra, double preciocompra, double subtotal, double descuento, RegistroCompra registrocompra, Producto producto) {
        this.numerodetallecompra = numerodetallecompra;
        this.cantidadcompra = cantidadcompra;
        this.preciocompra = preciocompra;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.registrocompra = registrocompra;
        this.producto = producto;
    }

    public int getNumerodetallecompra() {
        return numerodetallecompra;
    }

    public void setNumerodetallecompra(int numerodetallecompra) {
        this.numerodetallecompra = numerodetallecompra;
    }

    public int getCantidadcompra() {
        return cantidadcompra;
    }

    public void setCantidadcompra(int cantidadcompra) {
        this.cantidadcompra = cantidadcompra;
    }

    public double getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public RegistroCompra getRegistrocompra() {
        return registrocompra;
    }

    public void setRegistrocompra(RegistroCompra registrocompra) {
        this.registrocompra = registrocompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


}
