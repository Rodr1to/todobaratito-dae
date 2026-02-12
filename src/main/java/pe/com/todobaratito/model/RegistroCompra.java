package pe.com.todobaratito.model;

import java.util.Date;


public class RegistroCompra {
    private int numero;
    private Date fechacompra;
    private double total;
    private String observaciones;
    private boolean estado;
    private Empleado empleado;
    private Proveedor proveedor;

    public RegistroCompra() {
    }

    public RegistroCompra(int numero, Date fechacompra, double total, String observaciones, boolean estado, Empleado empleado, Proveedor proveedor) {
        this.numero = numero;
        this.fechacompra = fechacompra;
        this.total = total;
        this.observaciones = observaciones;
        this.estado = estado;
        this.empleado = empleado;
        this.proveedor = proveedor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }


}

