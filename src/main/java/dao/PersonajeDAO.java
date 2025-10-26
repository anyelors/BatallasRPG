package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Clase;
import modelo.Personaje;

public class PersonajeDAO implements CrudDAO<Personaje> {

    private Connection con;

    public PersonajeDAO(Connection connection) {
        con = connection;
    }

    @Override
    public List<Personaje> listar() throws SQLException {
        List<Personaje> personajes = new ArrayList<>();
        CrudDAO<Clase> claseDAO = new ClaseDAO(con);

        String sql = "SELECT p.id, " +
                "p.nombre, " +
                "p.id_clase, " +
                "p.experiencia, " +
                "p.nivel, " +
                "p.vida " +
                "FROM personajes p " +
                "WHERE p.vida > 0";
        try (Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Personaje personaje = new Personaje();
                personaje.setId(rs.getLong("id"));
                personaje.setNombre(rs.getString("nombre"));
                Clase clase = claseDAO.obtener(rs.getLong("id_clase"));
                personaje.setClase(clase);
                personaje.setExperiencia(rs.getLong("experiencia"));
                personaje.setNivel(rs.getLong("nivel"));
                personaje.setVida(rs.getLong("vida"));
                personajes.add(personaje);
            }
        }
        return personajes;
    }

    @Override
    public Personaje obtener(Long id) throws SQLException {
        Personaje personaje = null;
        CrudDAO<Clase> claseDAO = new ClaseDAO(con);

        String sql = "SELECT p.id, " +
                "p.nombre, " +
                "p.id_clase, " +
                "p.experiencia, " +
                "p.nivel, " +
                "p.vida " +
                "FROM personajes p " +
                "WHERE p.id = " + id;
        try (Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                personaje = new Personaje();
                personaje.setId(rs.getLong("id"));
                personaje.setNombre(rs.getString("nombre"));
                Clase clase = claseDAO.obtener(rs.getLong("id_clase"));
                personaje.setClase(clase);
                personaje.setExperiencia(rs.getLong("experiencia"));
                personaje.setNivel(rs.getLong("nivel"));
                personaje.setVida(rs.getLong("vida"));
            }
        }
        return personaje;
    }

    @Override
    public void insertar(Personaje elemento) throws SQLException {
        String sql = "INSERT INTO personajes( nombre, id_clase, nivel, experiencia, vida ) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, elemento.getNombre());
            pst.setLong(2, elemento.getClase().getId());
            pst.setLong(3, elemento.getNivel());
            pst.setLong(4, elemento.getExperiencia());
            pst.setLong(5, elemento.getVida());

            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) elemento.setId(rs.getLong(1));
        }
    }

    @Override
    public void actualizar(Personaje elemento) throws SQLException {
        String sql = "UPDATE personajes SET " +
                "nombre = ? " +
                ", id_clase = ? " +
                ", nivel = ? " +
                ", experiencia = ?" +
                ", vida = ? " +
                "WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, elemento.getNombre());
            pst.setLong(2, elemento.getClase().getId());
            pst.setLong(3, elemento.getNivel());
            pst.setLong(4, elemento.getExperiencia());
            pst.setLong(5, elemento.getVida());
            pst.setLong(6, elemento.getId());

            pst.executeUpdate();
        }
    }

    @Override
    public void eliminar(Personaje obj) throws SQLException {
        throw new SQLException("No implementado");
    }
}
