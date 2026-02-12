package pe.com.todobaratito.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.EmpleadoDAO;
import pe.com.todobaratito.model.*;

public class EmpleadoDAOImpl implements EmpleadoDAO{

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private CallableStatement cst;
    private PreparedStatement pst;
    private ResultSet rs;

    public EmpleadoDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "select e.*, d.nomdis, r.nomrol, s.nomsex, t.nomtipd " +
                "from empleado e " +
                "inner join distrito d on e.coddis = d.coddis " +
                "inner join rol r on e.codrol = r.codrol " +
                "inner join sexo s on e.codsex = s.codsex " +
                "inner join tipodocumento t on e.codtipd = t.codtipd";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Empleado obj = new Empleado();
                obj.setCodigo(rs.getInt("codemp"));
                obj.setNombre(rs.getString("nomemp"));
                obj.setApellidoPaterno(rs.getString("apepemp"));
                obj.setApellidoMaterno(rs.getString("apememp"));
                obj.setDocumento(rs.getString("docemp"));
                obj.setFechaNacimiento(rs.getDate("fecemp"));
                obj.setDireccion(rs.getString("diremp"));
                obj.setTelefono(rs.getString("telemp"));
                obj.setCelular(rs.getString("celemp"));
                obj.setCorreo(rs.getString("coremp"));
                obj.setUsuario(rs.getString("usuemp"));
                obj.setClave(rs.getString("claemp"));
                obj.setCargo(rs.getString("cargo"));
                obj.setFechaIngreso(rs.getDate("fecingreso"));
                obj.setEstado(rs.getBoolean("estemp"));

                // se mapean los objetos relacionados
                Distrito d = new Distrito(); d.setCodigo(rs.getInt("coddis")); d.setNombre(rs.getString("nomdis"));
                obj.setDistrito(d);

                Rol r = new Rol(); r.setCodigo(rs.getInt("codrol")); r.setNombre(rs.getString("nomrol"));
                obj.setRol(r);

                Sexo s = new Sexo(); s.setCodigo(rs.getInt("codsex")); s.setNombre(rs.getString("nomsex"));
                obj.setSexo(s);

                TipoDocumento t = new TipoDocumento(); t.setCodigo(rs.getInt("codtipd")); t.setNombre(rs.getString("nomtipd"));
                obj.setTipoDocumento(t);

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
    public List<Empleado> findAllCustom() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "select e.*, d.nomdis, r.nomrol, s.nomsex, t.nomtipd " +
                "from empleado e " +
                "inner join distrito d on e.coddis = d.coddis " +
                "inner join rol r on e.codrol = r.codrol " +
                "inner join sexo s on e.codsex = s.codsex " +
                "inner join tipodocumento t on e.codtipd = t.codtipd " +
                "where e.estemp=1";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Empleado obj = new Empleado();
                obj.setCodigo(rs.getInt("codemp"));
                obj.setNombre(rs.getString("nomemp"));
                obj.setApellidoPaterno(rs.getString("apepemp"));
                obj.setApellidoMaterno(rs.getString("apememp"));
                obj.setDocumento(rs.getString("docemp"));
                obj.setFechaNacimiento(rs.getDate("fecemp"));
                obj.setDireccion(rs.getString("diremp"));
                obj.setTelefono(rs.getString("telemp"));
                obj.setCelular(rs.getString("celemp"));
                obj.setCorreo(rs.getString("coremp"));
                obj.setUsuario(rs.getString("usuemp"));
                obj.setClave(rs.getString("claemp"));
                obj.setCargo(rs.getString("cargo"));
                obj.setFechaIngreso(rs.getDate("fecingreso"));
                obj.setEstado(rs.getBoolean("estemp"));

                Distrito d = new Distrito(); d.setCodigo(rs.getInt("coddis")); d.setNombre(rs.getString("nomdis"));
                obj.setDistrito(d);
                Rol r = new Rol(); r.setCodigo(rs.getInt("codrol")); r.setNombre(rs.getString("nomrol"));
                obj.setRol(r);
                Sexo s = new Sexo(); s.setCodigo(rs.getInt("codsex")); s.setNombre(rs.getString("nomsex"));
                obj.setSexo(s);
                TipoDocumento t = new TipoDocumento(); t.setCodigo(rs.getInt("codtipd")); t.setNombre(rs.getString("nomtipd"));
                obj.setTipoDocumento(t);

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
    public Empleado findById(int id) {
        Empleado obj = new Empleado();
        String sql = "select e.*, d.nomdis, r.nomrol, s.nomsex, t.nomtipd " +
                "from empleado e " +
                "inner join distrito d on e.coddis = d.coddis " +
                "inner join rol r on e.codrol = r.codrol " +
                "inner join sexo s on e.codsex = s.codsex " +
                "inner join tipodocumento t on e.codtipd = t.codtipd " +
                "where e.codemp=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodigo(rs.getInt("codemp"));
                obj.setNombre(rs.getString("nomemp"));
                obj.setApellidoPaterno(rs.getString("apepemp"));
                obj.setApellidoMaterno(rs.getString("apememp"));
                obj.setDocumento(rs.getString("docemp"));
                obj.setFechaNacimiento(rs.getDate("fecemp"));
                obj.setDireccion(rs.getString("diremp"));
                obj.setTelefono(rs.getString("telemp"));
                obj.setCelular(rs.getString("celemp"));
                obj.setCorreo(rs.getString("coremp"));
                obj.setUsuario(rs.getString("usuemp"));
                obj.setClave(rs.getString("claemp"));
                obj.setCargo(rs.getString("cargo"));
                obj.setFechaIngreso(rs.getDate("fecingreso"));
                obj.setEstado(rs.getBoolean("estemp"));

                Distrito d = new Distrito(); d.setCodigo(rs.getInt("coddis")); d.setNombre(rs.getString("nomdis"));
                obj.setDistrito(d);
                Rol r = new Rol(); r.setCodigo(rs.getInt("codrol")); r.setNombre(rs.getString("nomrol"));
                obj.setRol(r);
                Sexo s = new Sexo(); s.setCodigo(rs.getInt("codsex")); s.setNombre(rs.getString("nomsex"));
                obj.setSexo(s);
                TipoDocumento t = new TipoDocumento(); t.setCodigo(rs.getInt("codtipd")); t.setNombre(rs.getString("nomtipd"));
                obj.setTipoDocumento(t);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Empleado obj) {
        boolean resultado = false;
        String sql = "insert into empleado(nomemp, apepemp, apememp, docemp, fecemp, diremp, telemp, celemp, coremp, usuemp, claemp, cargo, fecingreso, estemp, coddis, codrol, codsex, codtipd) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getApellidoPaterno());
            pst.setString(3, obj.getApellidoMaterno());
            pst.setString(4, obj.getDocumento());
            pst.setDate(5, new java.sql.Date(obj.getFechaNacimiento().getTime()));
            pst.setString(6, obj.getDireccion());
            pst.setString(7, obj.getTelefono());
            pst.setString(8, obj.getCelular());
            pst.setString(9, obj.getCorreo());
            pst.setString(10, obj.getUsuario());
            pst.setString(11, obj.getClave());
            pst.setString(12, obj.getCargo());
            pst.setDate(13, new java.sql.Date(obj.getFechaIngreso().getTime()));
            pst.setBoolean(14, obj.isEstado());
            // FKs
            pst.setInt(15, obj.getDistrito().getCodigo());
            pst.setInt(16, obj.getRol().getCodigo());
            pst.setInt(17, obj.getSexo().getCodigo());
            pst.setInt(18, obj.getTipoDocumento().getCodigo());

            resultado = pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean update(Empleado obj) {
        boolean resultado = false;
        String sql = "update empleado set nomemp=?, apepemp=?, apememp=?, docemp=?, fecemp=?, diremp=?, telemp=?, celemp=?, coremp=?, usuemp=?, claemp=?, cargo=?, fecingreso=?, estemp=?, coddis=?, codrol=?, codsex=?, codtipd=? where codemp=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getApellidoPaterno());
            pst.setString(3, obj.getApellidoMaterno());
            pst.setString(4, obj.getDocumento());
            pst.setDate(5, new java.sql.Date(obj.getFechaNacimiento().getTime()));
            pst.setString(6, obj.getDireccion());
            pst.setString(7, obj.getTelefono());
            pst.setString(8, obj.getCelular());
            pst.setString(9, obj.getCorreo());
            pst.setString(10, obj.getUsuario());
            pst.setString(11, obj.getClave());
            pst.setString(12, obj.getCargo());
            pst.setDate(13, new java.sql.Date(obj.getFechaIngreso().getTime()));
            pst.setBoolean(14, obj.isEstado());
            // FKs
            pst.setInt(15, obj.getDistrito().getCodigo());
            pst.setInt(16, obj.getRol().getCodigo());
            pst.setInt(17, obj.getSexo().getCodigo());
            pst.setInt(18, obj.getTipoDocumento().getCodigo());
            // ID
            pst.setInt(19, obj.getCodigo());

            resultado = pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean delete(Empleado obj) {
        boolean resultado = false;
        String sql = "update empleado set estemp=0 where codemp=?";
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
    public boolean enable(Empleado obj) {
        boolean resultado = false;
        String sql = "update empleado set estemp=1 where codemp=?";
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
    public boolean disable(Empleado obj) {
        return delete(obj);
    }

    @Override
    public List<Empleado> login(Empleado obj) {
        List<Empleado> lista = new ArrayList<>();
        String sql ="{call sp_ValidarUsuario(?,?)}";
        try {
            xcon = objconexion.obtenerConexion();
            cst = xcon.prepareCall(sql);
            rs = cst.executeQuery();
            while(rs.next()){
                Empleado obje = new Empleado();
                Rol objr = new Rol();

                obje.setCodigo(rs.getInt("codemp"));
                obje.setNombre(rs.getString("nomemp"));
                obje.setApellidoPaterno(rs.getString("apepemp"));
                obje.setApellidoMaterno(rs.getString("apememp"));
                obje.setUsuario(rs.getString("usuemp"));

                objr.setCodigo(rs.getInt("codrol"));
                objr.setNombre(rs.getString("nomemp"));
                obje.setRol(objr);

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
}
