package pe.com.todobaratito.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.ProveedorDAO;
import pe.com.todobaratito.model.Distrito;
import pe.com.todobaratito.model.Proveedor;

public class ProveedorDAOImpl implements ProveedorDAO{

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProveedorDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Proveedor> findAll() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "select p.*, d.nomdis from proveedor p inner join distrito d on p.coddis=d.coddis";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Proveedor obj = new Proveedor();
                obj.setCodigo(rs.getInt("codprov"));
                obj.setNombre(rs.getString("nomprov"));
                obj.setRepresentante(rs.getString("repprov"));
                obj.setRuc(rs.getString("rucprov"));
                obj.setDireccion(rs.getString("dirprov"));
                obj.setTelefono(rs.getString("telprov"));
                obj.setCelular(rs.getString("celprov"));
                obj.setCorreo(rs.getString("corprov"));
                obj.setContacto(rs.getString("contacto"));
                obj.setEstado(rs.getBoolean("estprov"));

                // distrito
                Distrito d = new Distrito();
                d.setCodigo(rs.getInt("coddis"));
                d.setNombre(rs.getString("nomdis")); // del join
                obj.setDistrito(d);

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
    public List<Proveedor> findAllCustom() {
        List<Proveedor> lista = new ArrayList<>();
        // mismo join filtra habilitados
        String sql = "select p.*, d.nomdis from proveedor p inner join distrito d on p.coddis=d.coddis where p.estprov=1";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Proveedor obj = new Proveedor();
                obj.setCodigo(rs.getInt("codprov"));
                obj.setNombre(rs.getString("nomprov"));
                obj.setRepresentante(rs.getString("repprov"));
                obj.setRuc(rs.getString("rucprov"));
                obj.setDireccion(rs.getString("dirprov"));
                obj.setTelefono(rs.getString("telprov"));
                obj.setCelular(rs.getString("celprov"));
                obj.setCorreo(rs.getString("corprov"));
                obj.setContacto(rs.getString("contacto"));
                obj.setEstado(rs.getBoolean("estprov"));

                Distrito d = new Distrito();
                d.setCodigo(rs.getInt("coddis"));
                d.setNombre(rs.getString("nomdis"));
                obj.setDistrito(d);

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
    public Proveedor findById(int id) {
        Proveedor obj = new Proveedor();
        String sql = "select p.*, d.nomdis from proveedor p inner join distrito d on p.coddis=d.coddis where p.codprov=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodigo(rs.getInt("codprov"));
                obj.setNombre(rs.getString("nomprov"));
                obj.setRepresentante(rs.getString("repprov"));
                obj.setRuc(rs.getString("rucprov"));
                obj.setDireccion(rs.getString("dirprov"));
                obj.setTelefono(rs.getString("telprov"));
                obj.setCelular(rs.getString("celprov"));
                obj.setCorreo(rs.getString("corprov"));
                obj.setContacto(rs.getString("contacto"));
                obj.setEstado(rs.getBoolean("estprov"));

                Distrito d = new Distrito();
                d.setCodigo(rs.getInt("coddis"));
                d.setNombre(rs.getString("nomdis"));
                obj.setDistrito(d);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Proveedor obj) {
        boolean resultado = false;
        String sql = "insert into proveedor(nomprov, repprov, rucprov, dirprov, telprov, celprov, corprov, contacto, estprov, coddis) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getRepresentante());
            pst.setString(3, obj.getRuc());
            pst.setString(4, obj.getDireccion());
            pst.setString(5, obj.getTelefono());
            pst.setString(6, obj.getCelular());
            pst.setString(7, obj.getCorreo());
            pst.setString(8, obj.getContacto());
            pst.setBoolean(9, obj.isEstado());
            // distrito del objeto para guardarlo
            pst.setInt(10, obj.getDistrito().getCodigo());
            resultado = pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean update(Proveedor obj) {
        boolean resultado = false;
        String sql = "update proveedor set nomprov=?, repprov=?, rucprov=?, dirprov=?, telprov=?, celprov=?, corprov=?, contacto=?, estprov=?, coddis=? where codprov=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getRepresentante());
            pst.setString(3, obj.getRuc());
            pst.setString(4, obj.getDireccion());
            pst.setString(5, obj.getTelefono());
            pst.setString(6, obj.getCelular());
            pst.setString(7, obj.getCorreo());
            pst.setString(8, obj.getContacto());
            pst.setBoolean(9, obj.isEstado());
            pst.setInt(10, obj.getDistrito().getCodigo());
            pst.setInt(11, obj.getCodigo());
            resultado = pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean delete(Proveedor obj) {
        boolean resultado = false;
        String sql = "update proveedor set estprov=0 where codprov=?";
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
    public boolean enable(Proveedor obj) {
        boolean resultado = false;
        String sql = "update proveedor set estprov=1 where codprov=?";
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
    public boolean disable(Proveedor obj) {
        return delete(obj);
    }

}
