package pe.com.todobaratito.dao.impl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.CategoriaDAO;
import pe.com.todobaratito.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

    //CREAMOS UN OBJETO DE LA CLASE CONEXION
    private final Conexion objconexion;
    //CREAMOS UN OBJETO DE TIPO CONNECTION
    private Connection xcon;
    //CREAMOS UN STATEMENT -> SENTENCIASL SQL
    private Statement st;
    //CREAMOS UN PREPAREDSTATEMENT -> SENTENCIAL SQL CON PARAMETRO
    private PreparedStatement pst;
    //CREAMOS UN RESULSET -> ALMACENAR LOS RESULTADO DE UNA CONSULTA
    private ResultSet rs;

    //CREAMOS EL METODO CONSTRUCTOR PARA INICIALIZAR LA CONEXION

    public CategoriaDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Categoria> findAll() {
        //CREAMOS UNA LISTA DE TIPO CATEGORIA
        List<Categoria> lista = new ArrayList<>();
        //REALIZAMOS LA CONSULTA SQL
        String sql="select * from categoria";
        try {
            //CONECTAMOS A LA BASE DE DATOS
            xcon=objconexion.obtenerConexion();
            //CREAMOS EL STATEMENT PARA REALIZAR LA CONSULTA
            st=xcon.createStatement();
            //EJECUTAMOS EL STATMENT Y GUARDAMOS LOS RESULTADOS EN EL RESULTSET
            rs=st.executeQuery(sql);
            //REALIZAMOS UN BUCLE PARA OBTENER LOS DATOS DEL RESULSET
            while(rs.next()){
                //CREAMOS UN OBJETO DEL MODELO CATEGORIA
                Categoria obj=new Categoria();
                //ASIGNAMOS LOS VALORES DEL RESULTSET
                obj.setCodigo(rs.getInt("codcat"));
                obj.setNombre(rs.getString("nomcat"));
                obj.setEstado(rs.getBoolean("estcat"));
                //AGREGAMOSEL OBJETO A LA LISTA
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public List<Categoria> findAllCustom() {
        //CREAMOS UNA LISTA DE TIPO CATEGORIA
        List<Categoria> lista = new ArrayList<>();
        //REALIZAMOS LA CONSULTA SQL
        String sql="select * from categoria where estcat=1";
        try {
            //CONECTAMOS A LA BASE DE DATOS
            xcon=objconexion.obtenerConexion();
            //CREAMOS EL STATEMENT PARA REALIZAR LA CONSULTA
            st=xcon.createStatement();
            //EJECUTAMOS EL STATMENT Y GUARDAMOS LOS RESULTADOS EN EL RESULTSET
            rs=st.executeQuery(sql);
            //REALIZAMOS UN BUCLE PARA OBTENER LOS DATOS DEL RESULSET
            while(rs.next()){
                //CREAMOS UN OBJETO DEL MODELO CATEGORIA
                Categoria obj=new Categoria();
                //ASIGNAMOS LOS VALORES DEL RESULTSET
                obj.setCodigo(rs.getInt("codcat"));
                obj.setNombre(rs.getString("nomcat"));
                obj.setEstado(rs.getBoolean("estcat"));
                //AGREGAMOSEL OBJETO A LA LISTA
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public Categoria findById(int id) {
        //CREAMOS UNA LISTA DE TIPO CATEGORIA
        Categoria lista= new Categoria();
        //CONSULTA SQL QUE NO DEBERIAS DE REALIZAR
        //String sql="select * from categoria where codcat="+id;
        //REALIZAMOS LA CONSULTA SQL
        //UTILIZAMOS PARAMETROS Y CADA PARAMETRO SE REPRESENTA CON ?
        String sql="select * from categoria where codcat=?";
        try {
            //CONECTAMOS A LA BASE DE DATOS
            xcon=objconexion.obtenerConexion();
            //CREAMOS EL PREPAREDSTATEMENT PARA REALIZAR LA CONSULTA
            pst=xcon.prepareStatement(sql);
            //ASIGNAMOS EL PARAMETRO
            pst.setInt(1, id);
            //EJECUTAMOS EL PREPAREDSTATMENT Y GUARDAMOS LOS RESULTADOS EN EL RESULTSET
            rs=pst.executeQuery();
            //REALIZAMOS UN BUCLE PARA OBTENER LOS DATOS DEL RESULSET
            while(rs.next()){
                //ASIGNAMOS LOS VALORES DEL RESULTSET
                lista.setCodigo(rs.getInt("codcat"));
                lista.setNombre(rs.getString("nomcat"));
                lista.setEstado(rs.getBoolean("estcat"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public boolean add(Categoria obj) {
        boolean resultado = false;
        String sql="insert into categoria(nomcat,estcat) values(?,?)";
        try {
            xcon=objconexion.obtenerConexion();
            pst=xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setBoolean(2, obj.isEstado());
            // EJECUTAMOS SENTENCIA SQL -> PARA ACTUALIZAR LA TABLA
            int res=pst.executeUpdate();
            resultado = res==1;
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean update(Categoria obj) {
        boolean resultado = false;
        String sql="update categoria set nomcat=?,estcat=? where codcat=?";
        try {
            xcon=objconexion.obtenerConexion();
            pst=xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setBoolean(2, obj.isEstado());
            pst.setInt(3, obj.getCodigo());
            // EJECUTAMOS SENTENCIA SQL -> PARA ACTUALIZAR LA TABLA
            int res=pst.executeUpdate();
            resultado = res==1;
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean delete(Categoria obj) {
        boolean resultado = false;
        // ELIMINAR EN UNA DB
        // ELIMINACION FISICA -> BORRA EL DATO Y NO PERMITE RECUPERARLO (NUNCA)
        // String sql="delete from categoria where codcat=?";
        // ELIMINACION LOGICA -> CAMBIA EL ESTADO DE UN DATO
        String sql="update categoria set estcat=0 where codcat=?";
        try {
            xcon=objconexion.obtenerConexion();
            pst=xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            // EJECUTAMOS SENTENCIA SQL -> PARA ACTUALIZAR LA TABLA
            int res=pst.executeUpdate();
            resultado = res==1;
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean enable(Categoria obj) {
        boolean resultado = false;
        // ELIMINAR EN UNA DB
        // ELIMINACION FISICA -> BORRA EL DATO Y NO PERMITE RECUPERARLO (NUNCA)
        // String sql="delete from categoria where codcat=?";
        // ELIMINACION LOGICA -> CAMBIA EL ESTADO DE UN DATO
        String sql="update categoria set estcat=1 where codcat=?";
        try {
            xcon=objconexion.obtenerConexion();
            pst=xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            // EJECUTAMOS SENTENCIA SQL -> PARA ACTUALIZAR LA TABLA
            int res=pst.executeUpdate();
            resultado = res==1;
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean disable(Categoria obj) {
        boolean resultado = false;
        // ELIMINAR EN UNA DB
        // ELIMINACION FISICA -> BORRA EL DATO Y NO PERMITE RECUPERARLO (NUNCA)
        // String sql="delete from categoria where codcat=?";
        // ELIMINACION LOGICA -> CAMBIA EL ESTADO DE UN DATO
        String sql="update categoria set estcat=0 where codcat=?";
        try {
            xcon=objconexion.obtenerConexion();
            pst=xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            // EJECUTAMOS SENTENCIA SQL -> PARA ACTUALIZAR LA TABLA
            int res=pst.executeUpdate();
            resultado = res==1;
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }
}


