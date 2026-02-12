package pe.com.todobaratito.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.TipoDocumentoDAO;
import pe.com.todobaratito.model.TipoDocumento;

public class TipoDocumentoDAOImpl implements TipoDocumentoDAO {

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public TipoDocumentoDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<TipoDocumento> findAll() {
        List<TipoDocumento> lista = new ArrayList<>();
        String sql = "select * from tipodocumento";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                TipoDocumento obj = new TipoDocumento();
                obj.setCodigo(rs.getInt("codtipd"));
                obj.setNombre(rs.getString("nomtipd"));
                obj.setEstado(rs.getBoolean("esttipd"));
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public List<TipoDocumento> findAllCustom() {
        List<TipoDocumento> lista = new ArrayList<>();
        String sql = "select * from tipodocumento where esttipd=1";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                TipoDocumento obj = new TipoDocumento();
                obj.setCodigo(rs.getInt("codtipd"));
                obj.setNombre(rs.getString("nomtipd"));
                obj.setEstado(rs.getBoolean("esttipd"));
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public TipoDocumento findById(int id) {
        TipoDocumento obj = new TipoDocumento();
        String sql = "select * from tipodocumento where codtipd=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodigo(rs.getInt("codtipd"));
                obj.setNombre(rs.getString("nomtipd"));
                obj.setEstado(rs.getBoolean("esttipd"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(TipoDocumento obj) {
        boolean resultado = false;
        String sql = "insert into tipodocumento(nomtipd,esttipd) values(?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setBoolean(2, obj.isEstado());
            resultado = pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean update(TipoDocumento obj) {
        boolean resultado = false;
        String sql = "update tipodocumento set nomtipd=?,esttipd=? where codtipd=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setBoolean(2, obj.isEstado());
            pst.setInt(3, obj.getCodigo());
            resultado = pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean delete(TipoDocumento obj) {
        boolean resultado = false;
        String sql = "update tipodocumento set esttipd=0 where codtipd=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            resultado = pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean enable(TipoDocumento obj) {
        boolean resultado = false;
        String sql = "update tipodocumento set esttipd=1 where codtipd=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            resultado = pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean disable(TipoDocumento obj) {
        return delete(obj);
    }

}
