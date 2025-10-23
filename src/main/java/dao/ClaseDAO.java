package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Clase;

public class ClaseDAO implements CrudDAO<Clase> {
    private Connection con;

    public ClaseDAO(Connection connection) {
        con = connection;
    }

    @Override
    public List<Clase> listar() throws SQLException {
        List<Clase> clases = new ArrayList<>();
        String sql = "SELECT c.id, " +
                "c.nombre " +
                "FROM clases c";
        try (var st = con.createStatement()) {
            var rs = st.executeQuery(sql);

            while (rs.next()) {
                Clase clase = new Clase();
                clase.setId(rs.getLong("id"));
                clase.setNombre(rs.getString("nombre"));
                clases.add(clase);
            }
        }
        return clases;        
    }

    @Override
    public Clase obtener(Long id) throws SQLException {
        Clase clase = null;

        String sql = "SELECT c.id, " +
                "c.nombre " +
                "FROM clases c " +
                "WHERE c.id = " + id;
        try (var st = con.createStatement()) {

            var rs = st.executeQuery(sql);

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                clase = new Clase(id, nombre);
            }
        }
        return clase;
    }

    @Override
    public void alta(Clase elemento) throws SQLException {
        String sql = "INSERT INTO clases( nombre ) VALUES (?)";
        try (PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, elemento.getNombre());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) elemento.setId(rs.getLong(1));
        }
    }

}
