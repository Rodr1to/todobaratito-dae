package pe.com.todobaratito.bd;
//IMPORTAMOS LA BIBLIOTECA PARA TRABAJAR CON BD
import java.sql.*;

public class Conexion {
    //PARA REALIZAR LA CONEXION A LA BD ES NECESARIO:
    //USUARIO de la BD de MySQL
    private final String usuario="root";
    //CLAVE de la BD de MySQL
    private final String clave="";
    //CADENA DE CONEXION
    private final String cadena="jdbc:mysql://localhost:3306/bdtodobaratito20260?useSSL=false&allowPublicKeyRetrieval=true";
    //DRIVER
    private final String driver="com.mysql.cj.jdbc.Driver";

    //CREAMOS UNA FUNCION PARA REALIZAR LA CONEXION
    public Connection obtenerConexion(){
        Connection xcon=null;
        try {
            //CARGANDO EL DRIVER
            Class.forName(driver);
            //ESTABLECEMOS LA CONEXION A LA BD
            xcon=DriverManager.getConnection(cadena, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.toString());
        }
        return xcon;
    }

    //CREAMOS UN PROCEDIMIENTO PARA CERRAR LA CONEXION
    public void cerrarConexion(Connection xcon){
        if(xcon!=null){
            try {
                xcon.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
