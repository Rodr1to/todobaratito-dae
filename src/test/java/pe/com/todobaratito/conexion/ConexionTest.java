package pe.com.todobaratito.conexion;

import pe.com.todobaratito.bd.Conexion;
import java.sql.*;

public class ConexionTest {
    public static void main(String[] args) {
    // CREAMOS UNA INSTANCIA DE LA CLASE CONEXION

        Conexion objconexion = new Conexion();
        Connection xcon = null;
        try{
            xcon=objconexion.obtenerConexion();
            if(xcon!=null){
                System.out.println("Conexion exitosa");
            }
            else{
                System.out.println("Conexion fallida");
            }
        }
        catch(Exception ex){
            System.out.println("Erro de conexion: " + ex.toString());
        }
        finally{
            objconexion.cerrarConexion(xcon);
            System.out.println("Conexion cerrada");
        }
    }
}
