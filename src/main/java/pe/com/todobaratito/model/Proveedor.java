package pe.com.todobaratito.model;

public class Proveedor {
    private int codigo;
    private String nombre;
    private String representante;
    private String ruc;
    private String direccion;
    private String telefono;
    private String celular;
    private String correo;
    private String contacto;
    private boolean estado;
    private Distrito distrito;

    public Proveedor() {
    }

    public Proveedor(int codigo, String nombre, String representante, String ruc, String direccion, String telefono, String celular, String correo, String contacto, boolean estado, Distrito distrito) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.representante = representante;
        this.ruc = ruc;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
        this.contacto = contacto;
        this.estado = estado;
        this.distrito = distrito;
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

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }


}

