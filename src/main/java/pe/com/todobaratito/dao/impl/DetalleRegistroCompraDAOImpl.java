package pe.com.todobaratito.dao.impl;

import pe.com.todobaratito.bd.Conexion;
import pe.com.todobaratito.dao.DetalleRegistroCompraDAO;
import pe.com.todobaratito.model.DetalleRegistroCompra;
import pe.com.todobaratito.model.Producto;
import pe.com.todobaratito.model.RegistroCompra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleRegistroCompraDAOImpl implements DetalleRegistroCompraDAO {

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private CallableStatement cst;
    private ResultSet rs;

    public DetalleRegistroCompraDAOImpl() {
        this.objconexion = new Conexion();
    }


    @Override
    public List<DetalleRegistroCompra> findById(int id) {
        List<DetalleRegistroCompra> lista = new ArrayList<>();
        String sql = "{call sp_BuscarDetalleRegistroCompra(?)}";
        try {
            xcon=objconexion.obtenerConexion();
            cst=xcon.prepareCall(sql);
            cst.setInt(1, id);
            rs=cst.executeQuery();
            while(rs.next()){
                DetalleRegistroCompra obj = new DetalleRegistroCompra();
                RegistroCompra objrc = new RegistroCompra();
                Producto objp = new Producto();

                obj.setNumerodetallecompra(rs.getInt("nrodetcom"));
                obj.setPreciocompra(rs.getDouble("precom"));
                obj.setCantidadcompra(rs.getInt("cancom"));

                objrc.setNumero(rs.getInt("nrocom"));
                obj.setRegistrocompra(objrc);

                objp.setCodigo(rs.getInt("codpro"));
                objp.setNombre(rs.getString("nompro"));
                obj.setProducto(objp);

                lista.add(obj);
            }
        }

        catch(Exception ex){
            System.out.println(ex.toString());
        }
        finally {
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }
}
