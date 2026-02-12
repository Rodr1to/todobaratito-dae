package pe.com.todobaratito.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.ProductoDAO;
import pe.com.todobaratito.model.Categoria;
import pe.com.todobaratito.model.Marca;
import pe.com.todobaratito.model.Producto;
import pe.com.todobaratito.model.Proveedor;

public class ProductoDAOImpl implements ProductoDAO {

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductoDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> lista = new ArrayList<>();
        String sql = "select p.*, m.nommar, c.nomcat, pv.nomprov " +
                "from producto p " +
                "inner join marca m on p.codmar=m.codmar " +
                "inner join categoria c on p.codcat=c.codcat " +
                "inner join proveedor pv on p.codprov=pv.codprov";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Producto obj = new Producto();
                obj.setCodigo(rs.getInt("codpro"));
                obj.setNombre(rs.getString("nompro"));
                obj.setDescripcion(rs.getString("despro"));
                obj.setPrecio(rs.getDouble("prepro"));
                obj.setCantidad(rs.getInt("canpro"));
                obj.setStockminimo(rs.getInt("stockminimo"));
                obj.setFechaingreso(rs.getDate("fecing"));
                obj.setEstado(rs.getBoolean("estpro"));

                Marca m = new Marca();
                m.setCodigo(rs.getInt("codmar"));
                m.setNombre(rs.getString("nommar"));
                obj.setMarca(m);

                Categoria c = new Categoria();
                c.setCodigo(rs.getInt("codcat"));
                c.setNombre(rs.getString("nomcat"));
                obj.setCategoria(c);

                Proveedor pv = new Proveedor();
                pv.setCodigo(rs.getInt("codprov"));
                pv.setNombre(rs.getString("nomprov"));
                obj.setProveedor(pv);

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
    public List<Producto> findAllCustom() {
        List<Producto> lista = new ArrayList<>();
        String sql = "select p.*, m.nommar, c.nomcat, pv.nomprov " +
                "from producto p " +
                "inner join marca m on p.codmar=m.codmar " +
                "inner join categoria c on p.codcat=c.codcat " +
                "inner join proveedor pv on p.codprov=pv.codprov " +
                "where p.estpro=1";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Producto obj = new Producto();
                obj.setCodigo(rs.getInt("codpro"));
                obj.setNombre(rs.getString("nompro"));
                obj.setDescripcion(rs.getString("despro"));
                obj.setPrecio(rs.getDouble("prepro"));
                obj.setCantidad(rs.getInt("canpro"));
                obj.setStockminimo(rs.getInt("stockminimo"));
                obj.setFechaingreso(rs.getDate("fecing"));
                obj.setEstado(rs.getBoolean("estpro"));

                Marca m = new Marca();
                m.setCodigo(rs.getInt("codmar"));
                m.setNombre(rs.getString("nommar"));
                obj.setMarca(m);

                Categoria c = new Categoria();
                c.setCodigo(rs.getInt("codcat"));
                c.setNombre(rs.getString("nomcat"));
                obj.setCategoria(c);

                Proveedor pv = new Proveedor();
                pv.setCodigo(rs.getInt("codprov"));
                pv.setNombre(rs.getString("nomprov"));
                obj.setProveedor(pv);

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
    public Producto findById(int id) {
        Producto obj = new Producto();
        String sql = "select * from producto where codpro=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodigo(rs.getInt("codpro"));
                obj.setNombre(rs.getString("nompro"));
                obj.setDescripcion(rs.getString("despro"));
                obj.setPrecio(rs.getDouble("prepro"));
                obj.setCantidad(rs.getInt("canpro"));
                obj.setStockminimo(rs.getInt("stockminimo"));
                obj.setFechaingreso(rs.getDate("fecing"));
                obj.setEstado(rs.getBoolean("estpro"));

                Marca m = new Marca();
                m.setCodigo(rs.getInt("codmar"));
                obj.setMarca(m);

                Categoria c = new Categoria();
                c.setCodigo(rs.getInt("codcat"));
                obj.setCategoria(c);

                Proveedor pv = new Proveedor();
                pv.setCodigo(rs.getInt("codprov"));
                obj.setProveedor(pv);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Producto obj) {
        boolean resultado = false;
        String sql = "insert into producto(nompro, despro, prepro, canpro, stockminimo, fecing, estpro, codmar, codcat, codprov) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getDescripcion());
            pst.setDouble(3, obj.getPrecio());
            pst.setInt(4, obj.getCantidad());
            pst.setInt(5, obj.getStockminimo());
            // Convertimos java.util.Date a java.sql.Date
            pst.setDate(6, new java.sql.Date(obj.getFechaingreso().getTime()));
            pst.setBoolean(7, obj.isEstado());
            // FKs
            pst.setInt(8, obj.getMarca().getCodigo());
            pst.setInt(9, obj.getCategoria().getCodigo());
            pst.setInt(10, obj.getProveedor().getCodigo());

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
    public boolean update(Producto obj) {
        boolean resultado = false;
        String sql = "update producto set nompro=?, despro=?, prepro=?, canpro=?, stockminimo=?, fecing=?, estpro=?, codmar=?, codcat=?, codprov=? where codpro=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getDescripcion());
            pst.setDouble(3, obj.getPrecio());
            pst.setInt(4, obj.getCantidad());
            pst.setInt(5, obj.getStockminimo());
            pst.setDate(6, new java.sql.Date(obj.getFechaingreso().getTime()));
            pst.setBoolean(7, obj.isEstado());
            // FKs
            pst.setInt(8, obj.getMarca().getCodigo());
            pst.setInt(9, obj.getCategoria().getCodigo());
            pst.setInt(10, obj.getProveedor().getCodigo());
            // ID para el WHERE
            pst.setInt(11, obj.getCodigo());

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
    public boolean delete(Producto obj) {
        boolean resultado = false;
        String sql = "update producto set estpro=0 where codpro=?";
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
    public boolean enable(Producto obj) {
        boolean resultado = false;
        String sql = "update producto set estpro=1 where codpro=?";
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
    public boolean disable(Producto obj) {
        return delete(obj);
    }
}

