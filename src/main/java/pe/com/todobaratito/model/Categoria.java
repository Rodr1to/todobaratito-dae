package pe.com.todobaratito.model;

public class Categoria {
    //ATRIBUTOS
    private int codigo;
    private String nombre;
    private boolean estado;

    //METODOS CONSTRUCTORES
    //VACIO
    public Categoria() {
    }

    //PARAMETROS
    public Categoria(int codigo, String nombre, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
    }

    //GETTER y SETTER
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
