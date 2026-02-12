package pe.com.todobaratito.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.RolDAO;
import pe.com.todobaratito.model.Rol;

public class RolDAOImpl implements RolDAO {

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public RolDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Rol> findAll() {
        List<Rol> lista = new ArrayList<>();
        String sql = "select * from rol";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Rol obj = new Rol();
                obj.setCodigo(rs.getInt("codrol"));
                obj.setNombre(rs.getString("nomrol"));
                obj.setEstado(rs.getBoolean("estrol"));
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
    public List<Rol> findAllCustom() {
        List<Rol> lista = new ArrayList<>();
        String sql = "select * from rol where estrol=1";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Rol obj = new Rol();
                obj.setCodigo(rs.getInt("codrol"));
                obj.setNombre(rs.getString("nomrol"));
                obj.setEstado(rs.getBoolean("estrol"));
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
    public Rol findById(int id) {
        Rol obj = new Rol();
        String sql = "select * from rol where codrol=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodigo(rs.getInt("codrol"));
                obj.setNombre(rs.getString("nomrol"));
                obj.setEstado(rs.getBoolean("estrol"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Rol obj) {
        boolean resultado = false;
        String sql = "insert into rol(nomrol,estrol) values(?,?)";
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
    public boolean update(Rol obj) {
        boolean resultado = false;
        String sql = "update rol set nomrol=?,estrol=? where codrol=?";
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
    public boolean delete(Rol obj) {
        boolean resultado = false;
        String sql = "update rol set estrol=0 where codrol=?";
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
    public boolean enable(Rol obj) {
        boolean resultado = false;
        String sql = "update rol set estrol=1 where codrol=?";
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
    public boolean disable(Rol obj) {
        return delete(obj);
    }

}
