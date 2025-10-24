package dao;

import modelo.Batalla;
import modelo.Personaje;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BatallaDAO implements CrudDAO<Batalla> {

    private Connection con;

    public BatallaDAO(Connection connection) {
        con = connection;
    }

    @Override
    public List<Batalla> listar() throws SQLException {

        List<Batalla> batallas = new ArrayList<>();
        CrudDAO<Personaje> personajeDAO = new PersonajeDAO(con);

        String sql = "SELECT id, " +
                "id_atacante, " +
                "id_defensor, " +
                "id_vencedor, " +
                "fecha, " +
                "resumen " +
                "FROM batallas ";
        try (Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Batalla batalla = new Batalla();
                Personaje atacante = new Personaje();
                Personaje defensor = new Personaje();
                Personaje vencedor = new Personaje();
                batalla.setId(rs.getLong("id"));
                atacante = personajeDAO.obtener(rs.getLong("id_atacante"));
                batalla.setId_atacante(atacante);
                defensor = personajeDAO.obtener(rs.getLong("id_defensor"));
                batalla.setId_defensor(defensor);
                vencedor = personajeDAO.obtener(rs.getLong("id_vencedor"));
                batalla.setId_vencedor(vencedor);
                batalla.setFecha(rs.getDate("fecha").toLocalDate().atStartOfDay());
                batalla.setResumen(rs.getString("resumen"));
                batallas.add(batalla);
            }
        }
        return batallas;
    }

    @Override
    public Batalla obtener(Long id) throws SQLException {
        Batalla batalla = null;
        CrudDAO<Personaje> personajeDAO = new PersonajeDAO(con);

        String sql = "SELECT id, " +
                "id_atacante, " +
                "id_defensor, " +
                "id_vencedor, " +
                "fecha, " +
                "resumen " +
                "FROM batallas " +
                "WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                batalla = new Batalla();
                batalla.setId(rs.getLong("id"));
                Personaje atacante = personajeDAO.obtener(rs.getLong("id_atacante"));
                batalla.setId_atacante(atacante);
                Personaje defensor = personajeDAO.obtener(rs.getLong("id_defensor"));
                batalla.setId_defensor(defensor);
                Personaje vencedor = personajeDAO.obtener(rs.getLong("id_vencedor"));
                batalla.setId_vencedor(vencedor);
                batalla.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                batalla.setResumen(rs.getString("resumen"));
            }
        }
        return batalla;
    }

    @Override
    public void insertar(Batalla elemento) throws SQLException {
        String sql = "INSERT INTO batallas( id_atacante, id_defensor, id_vencedor, fecha, resumen ) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setLong(1, elemento.getId_atacante().getId());
            pst.setLong(2, elemento.getId_defensor().getId());
            pst.setLong(3, elemento.getId_defensor().getId());
            pst.setObject(4, elemento.getFecha());
            pst.setString(5, elemento.getResumen());

            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) elemento.setId(rs.getLong(1));
        }
    }

    @Override
    public void eliminar(Batalla obj) throws SQLException {
        throw new SQLException("No implementado");
    }

    @Override
    public void actualizar(Batalla obj) throws SQLException {
        throw new SQLException("No implementado");
    }
}
