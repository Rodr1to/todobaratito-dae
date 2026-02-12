package pe.com.todobaratito.dao.impl;

import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.RegistroCompraDAO;
import pe.com.todobaratito.model.DetalleRegistroCompra;
import pe.com.todobaratito.model.Empleado;
import pe.com.todobaratito.model.Proveedor;
import pe.com.todobaratito.model.RegistroCompra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroCompraDAOImpl implements RegistroCompraDAO {

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private CallableStatement cst;
    private ResultSet rs;

    public RegistroCompraDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<RegistroCompra> findAll() {
        List<RegistroCompra> lista = new ArrayList<>();
        String sql ="{call sp_MostrarRegistroCompra()}";
        try {
            xcon = objconexion.obtenerConexion();
            cst = xcon.prepareCall(sql);
            rs = cst.executeQuery();
            while(rs.next()){
                RegistroCompra obj = new RegistroCompra();
                Empleado obje = new Empleado();
                Proveedor objpv = new Proveedor();

                obj.setNumero(rs.getInt("nrocom"));
                obj.setFechacompra(rs.getDate("feccom"));
                obj.setTotal(rs.getDouble("Total"));
                obj.setEstado(rs.getBoolean("estcom"));
                objpv.setNombre(rs.getString("nomprov"));
                objpv.setRepresentante(rs.getString("repprov"));
                obj.setProveedor(objpv);

                obje.setNombre(rs.getString("nomemp"));
                obje.setApellidoPaterno(rs.getString("apepemp"));
                obje.setApellidoMaterno(rs.getString("apememp"));
                obj.setEmpleado(obje);

                lista.add(obj);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        finally{
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public List<RegistroCompra> findAllCustom() {
        List<RegistroCompra> lista = new ArrayList<>();
        String sql ="{call sp_MostrarRegistroCompraHabilitado()}";
        try {
            xcon = objconexion.obtenerConexion();
            cst = xcon.prepareCall(sql);
            rs = cst.executeQuery();
            while(rs.next()){
                RegistroCompra obj = new RegistroCompra();
                Empleado obje = new Empleado();
                Proveedor objpv = new Proveedor();

                obj.setNumero(rs.getInt("nrocom"));
                obj.setFechacompra(rs.getDate("feccom"));
                obj.setTotal(rs.getDouble("Total"));
                obj.setEstado(rs.getBoolean("estcom"));
                objpv.setNombre(rs.getString("nomprov"));
                objpv.setRepresentante(rs.getString("repprov"));
                obj.setProveedor(objpv);

                obje.setNombre(rs.getString("nomemp"));
                obje.setApellidoPaterno(rs.getString("apepemp"));
                obje.setApellidoMaterno(rs.getString("apememp"));
                obj.setEmpleado(obje);

                lista.add(obj);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        finally{
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public RegistroCompra findById(int id) {
        RegistroCompra lista = new RegistroCompra();
        String sql ="{call sp_BuscarRegistroCompra(?)}";
        try {
            xcon = objconexion.obtenerConexion();
            cst = xcon.prepareCall(sql);
            cst.setInt(1, id);
            rs = cst.executeQuery();
            while(rs.next()){
                Empleado obje = new Empleado();
                Proveedor objpv = new Proveedor();

                lista.setNumero(rs.getInt("nrocom"));
                lista.setFechacompra(rs.getDate("feccom"));
                lista.setEstado(rs.getBoolean("estcom"));
                objpv.setNombre(rs.getString("nomprov"));
                objpv.setRepresentante(rs.getString("repprov"));
                lista.setProveedor(objpv);

                obje.setNombre(rs.getString("nomemp"));
                obje.setApellidoPaterno(rs.getString("apepemp"));
                obje.setApellidoMaterno(rs.getString("apememp"));
                lista.setEmpleado(obje);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        finally{
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public boolean add(RegistroCompra obj) {
        boolean resultado = false;
        String sql = "insert into registrocompra(feccom,estcom,codprov,codemp,observaciones) values(now(),?,?,?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setBoolean(1, obj.isEstado());
            pst.setInt(2, obj.getProveedor().getCodigo());
            pst.setInt(3, obj.getEmpleado().getCodigo());
            pst.setString(4, obj.getObservaciones());
            int res = pst.executeUpdate();
            resultado = res == 1;
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean enable(RegistroCompra obj) {
        boolean resultado = false;
        String sql = "{call sp_HabilitarRegistroCompra(?)}";
        try {
            xcon = objconexion.obtenerConexion();
            cst = xcon.prepareCall(sql);
            cst.setInt(1, obj.getNumero());
            int res = cst.executeUpdate();
            resultado = res == 1;
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean disable(RegistroCompra obj) {
        boolean resultado = false;
        String sql = "{call sp_DeshabilitarRegistroCompra(?)}";
        try {
            xcon = objconexion.obtenerConexion();
            cst = xcon.prepareCall(sql);
            cst.setInt(1, obj.getNumero());
            int res = cst.executeUpdate();
            resultado = res == 1;
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public int setNumber() {
        int siguiente = 1;
        String sql = "select max(nrocom) as nrocom from registrocompra";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next() && rs.getObject(1) != null){
                int ultimo = rs.getInt("nrocom");
                siguiente = ultimo + 1;
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        finally {
            objconexion.cerrarConexion(xcon);
        }
        return siguiente;
    }

    @Override
    public int getNumber() {
        int numero = 0;
        String sql = "select max(nrocom) as nrocom from registrocompra";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next() && rs.getObject(1) != null){
                int ultimo = rs.getInt("nrocom");
                numero = ultimo;
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        finally {
            objconexion.cerrarConexion(xcon);
        }
        return numero;
    }

    @Override
    public boolean addDetails(DetalleRegistroCompra obj) {
        boolean resultado = false;
        String sql = "insert into detalleregistrocompra(cancom,precom,nrocom,codpro) values(?,?,?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCantidadcompra());
            pst.setDouble(2,obj.getPreciocompra());
            pst.setInt(3, obj.getRegistrocompra().getNumero());
            pst.setInt(4, obj.getProducto().getCodigo());
            int res = pst.executeUpdate();
            resultado = res == 1;
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }
}
