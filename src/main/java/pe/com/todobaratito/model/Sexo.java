package pe.com.todobaratito.model;

public class Sexo {
    // atributos
    private int codigo;
    private String nombre;
    private boolean estado;

    // constructor
    public Sexo() {
    }

    // parametros
    public Sexo(int codigo, String nombre, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
    }

    // getters y setters
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
