package pe.com.todobaratito.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.DistritoDAO;
import pe.com.todobaratito.model.Distrito;

public class DistritoDAOImpl implements DistritoDAO {
    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public DistritoDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Distrito> findAll() {
        List<Distrito> lista = new ArrayList<>();
        String sql = "select * from distrito";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Distrito obj = new Distrito();
                obj.setCodigo(rs.getInt("coddis"));
                obj.setNombre(rs.getString("nomdis"));
                obj.setEstado(rs.getBoolean("estdis"));
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
    public List<Distrito> findAllCustom() {
        List<Distrito> lista = new ArrayList<>();
        String sql = "select * from distrito where estdis=1";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Distrito obj = new Distrito();
                obj.setCodigo(rs.getInt("coddis"));
                obj.setNombre(rs.getString("nomdis"));
                obj.setEstado(rs.getBoolean("estdis"));
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
    public Distrito findById(int id) {
        Distrito obj = new Distrito();
        String sql = "select * from distrito where coddis=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodigo(rs.getInt("coddis"));
                obj.setNombre(rs.getString("nomdis"));
                obj.setEstado(rs.getBoolean("estdis"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Distrito obj) {
        boolean resultado = false;
        String sql = "insert into distrito(nomdis,estdis) values(?,?)";
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
    public boolean update(Distrito obj) {
        boolean resultado = false;
        String sql = "update distrito set nomdis=?,estdis=? where coddis=?";
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
    public boolean delete(Distrito obj) {
        boolean resultado = false;
        String sql = "update distrito set estdis=0 where coddis=?";
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
    public boolean enable(Distrito obj) {
        boolean resultado = false;
        String sql = "update distrito set estdis=1 where coddis=?";
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
    public boolean disable(Distrito obj) {
        return delete(obj);
    }

}
