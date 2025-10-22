package dao;

import modelo.Clase;
import modelo.Personaje;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonajeDAO implements CrudDAO<Personaje> {

    private Connection con;

    public PersonajeDAO(Connection connection) {
        con = connection;
    }

    @Override
    public List<Personaje> listar() throws SQLException {
        List<Personaje> personajes = new ArrayList<>();
        String sql = "SELECT p.id, " +
                "p.nombre, " +
                "(SELECT cl.nombre FROM clases cl WHERE cl.id = p.id_clase) des_clase, " +
                "p.experiencia, " +
                "p.nivel, " +
                "p.vida " +
                "FROM personajes p " +
                "WHERE p.vida > 0";
        try (Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                Clase clase = new Clase(rs.getString("des_clase"));
                long experiencia = rs.getLong("experiencia");
                long nivel = rs.getLong("nivel");
                long vida = rs.getLong("vida");
                personajes.add(new Personaje(id, nombre, clase, experiencia, nivel, vida));
            }
        }
        return personajes;
    }
}
