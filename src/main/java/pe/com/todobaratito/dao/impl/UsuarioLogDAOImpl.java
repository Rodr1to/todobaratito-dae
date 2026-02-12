package pe.com.todobaratito.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.UsuarioLogDAO;
import pe.com.todobaratito.model.Empleado;
import pe.com.todobaratito.model.UsuarioLog;

public class UsuarioLogDAOImpl implements UsuarioLogDAO {
    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public UsuarioLogDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<UsuarioLog> findAll() {
        List<UsuarioLog> lista = new ArrayList<>();
        String sql = "select u.*, e.nomemp, e.apepemp from usuario_log u inner join empleado e on u.codemp = e.codemp";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                UsuarioLog obj = new UsuarioLog();
                obj.setIdLog(rs.getInt("idlog"));
                obj.setAccion(rs.getString("accion"));
                obj.setFecha(rs.getTimestamp("fecha"));

                Empleado e = new Empleado();
                e.setCodigo(rs.getInt("codemp"));
                e.setNombre(rs.getString("nomemp"));
                e.setApellidoPaterno(rs.getString("apepemp"));
                obj.setEmpleado(e);

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
    public List<UsuarioLog> findAllCustom() {
        List<UsuarioLog> lista = new ArrayList<>();
        String sql = "select u.*, e.nomemp, e.apepemp from usuario_log u " +
                "inner join empleado e on u.codemp = e.codemp " +
                "where date(u.fecha) = curdate() order by u.fecha desc";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                UsuarioLog obj = new UsuarioLog();
                obj.setIdLog(rs.getInt("idlog"));
                obj.setAccion(rs.getString("accion"));
                obj.setFecha(rs.getTimestamp("fecha"));

                Empleado e = new Empleado();
                e.setCodigo(rs.getInt("codemp"));
                e.setNombre(rs.getString("nomemp"));
                e.setApellidoPaterno(rs.getString("apepemp"));
                obj.setEmpleado(e);

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
    public UsuarioLog findById(int id) {
        UsuarioLog obj = new UsuarioLog();
        String sql = "select u.*, e.nomemp, e.apepemp from usuario_log u inner join empleado e on u.codemp = e.codemp where idlog=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                obj.setIdLog(rs.getInt("idlog"));
                obj.setAccion(rs.getString("accion"));
                obj.setFecha(rs.getTimestamp("fecha"));

                Empleado e = new Empleado();
                e.setCodigo(rs.getInt("codemp"));
                e.setNombre(rs.getString("nomemp"));
                e.setApellidoPaterno(rs.getString("apepemp"));
                obj.setEmpleado(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(UsuarioLog obj) {
        boolean resultado = false;
        String sql = "insert into usuario_log(codemp, accion, fecha) values(?,?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, obj.getEmpleado().getCodigo());
            pst.setString(2, obj.getAccion());
            pst.setTimestamp(3, new java.sql.Timestamp(obj.getFecha().getTime()));
            resultado = pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean update(UsuarioLog obj) {
        System.out.println("ADVERTENCIA: No se permite modificar un log por seguridad.");
        return false; // no aplica
    }

    @Override
    public boolean delete(UsuarioLog obj) {
        System.out.println("ADVERTENCIA: No se permite eliminar un log por seguridad.");
        return false; // no aplica
    }

    @Override
    public boolean enable(UsuarioLog obj) {
        System.out.println("AVISO: No se puede realizar esta acción.");
        return false; // no aplica
    }

    @Override
    public boolean disable(UsuarioLog obj) {
        System.out.println("AVISO: No se puede realizar esta acción.");
        return false; // no aplica
    }
}
