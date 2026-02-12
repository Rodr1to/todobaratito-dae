package pe.com.todobaratito.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.SexoDAO;
import pe.com.todobaratito.model.Sexo;

public class SexoDAOImpl implements SexoDAO{

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public SexoDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Sexo> findAll() {
        List<Sexo> lista = new ArrayList<>();
        String sql = "select * from sexo";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Sexo obj = new Sexo();
                obj.setCodigo(rs.getInt("codsex"));
                obj.setNombre(rs.getString("nomsex"));
                obj.setEstado(rs.getBoolean("estsex"));
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
    public List<Sexo> findAllCustom() {
        List<Sexo> lista = new ArrayList<>();
        String sql = "select * from sexo where estsex=1";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Sexo obj = new Sexo();
                obj.setCodigo(rs.getInt("codsex"));
                obj.setNombre(rs.getString("nomsex"));
                obj.setEstado(rs.getBoolean("estsex"));
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
    public Sexo findById(int id) {
        Sexo obj = new Sexo();
        String sql = "select * from sexo where codsex=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodigo(rs.getInt("codsex"));
                obj.setNombre(rs.getString("nomsex"));
                obj.setEstado(rs.getBoolean("estsex"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Sexo obj) {
        boolean resultado = false;
        String sql = "insert into sexo(nomsex,estsex) values(?,?)";
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
    public boolean update(Sexo obj) {
        boolean resultado = false;
        String sql = "update sexo set nomsex=?,estsex=? where codsex=?";
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
    public boolean delete(Sexo obj) {
        boolean resultado = false;
        String sql = "update sexo set estsex=0 where codsex=?";
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
    public boolean enable(Sexo obj) {
        boolean resultado = false;
        String sql = "update sexo set estsex=1 where codsex=?";
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
    public boolean disable(Sexo obj) {
        return delete(obj);
    }

}
