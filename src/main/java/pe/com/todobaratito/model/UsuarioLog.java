package pe.com.todobaratito.model;
import java.util.Date;

public class UsuarioLog {
    private int idLog;
    private Empleado empleado; // foreign key
    private String accion;
    private Date fecha;

    public UsuarioLog() {}

    public UsuarioLog(int idLog, Empleado empleado, String accion, Date fecha) {
        this.idLog = idLog;
        this.empleado = empleado;
        this.accion = accion;
        this.fecha = fecha;
    }

    public int getIdLog() { return idLog; }

    public void setIdLog(int idLog) { this.idLog = idLog; }

    public Empleado getEmpleado() { return empleado; }

    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    public String getAccion() { return accion; }

    public void setAccion(String accion) { this.accion = accion; }

    public Date getFecha() { return fecha; }

    public void setFecha(Date fecha) { this.fecha = fecha; }

}
