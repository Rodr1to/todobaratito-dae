package pe.com.todobaratito.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.MarcaDAO;
import pe.com.todobaratito.model.Marca;

public class MarcaDAOImpl implements MarcaDAO {
    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public MarcaDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Marca> findAll() {
        List<Marca> lista = new ArrayList<>();
        String sql = "select * from marca";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Marca obj = new Marca();
                obj.setCodigo(rs.getInt("codmar"));
                obj.setNombre(rs.getString("nommar"));
                obj.setEstado(rs.getBoolean("estmar"));
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
    public List<Marca> findAllCustom() {
        List<Marca> lista = new ArrayList<>();
        String sql = "select * from marca where estmar=1";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Marca obj = new Marca();
                obj.setCodigo(rs.getInt("codmar"));
                obj.setNombre(rs.getString("nommar"));
                obj.setEstado(rs.getBoolean("estmar"));
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
    public Marca findById(int id) {
        Marca obj = new Marca();
        String sql = "select * from marca where codmar=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodigo(rs.getInt("codmar"));
                obj.setNombre(rs.getString("nommar"));
                obj.setEstado(rs.getBoolean("estmar"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Marca obj) {
        boolean resultado = false;
        String sql = "insert into marca(nommar, estmar) values(?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setBoolean(2, obj.isEstado());
            int res = pst.executeUpdate();
            resultado = res == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean update(Marca obj) {
        boolean resultado = false;
        String sql = "update marca set nommar=?, estmar=? where codmar=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setBoolean(2, obj.isEstado());
            pst.setInt(3, obj.getCodigo());
            int res = pst.executeUpdate();
            resultado = res == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean delete(Marca obj) {
        boolean resultado = false;

        String sql = "update marca set estmar=0 where codmar=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            int res = pst.executeUpdate();
            resultado = res == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean enable(Marca obj) {
        boolean resultado = false;
        String sql = "update marca set estmar=1 where codmar=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            int res = pst.executeUpdate();
            resultado = res == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean disable(Marca obj) {
        return delete(obj);
    }
}
