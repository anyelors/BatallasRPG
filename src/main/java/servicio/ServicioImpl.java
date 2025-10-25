package servicio;

import dao.BatallaDAO;
import dao.ClaseDAO;
import dao.CrudDAO;
import dao.PersonajeDAO;
import datasource.Database;
import modelo.Batalla;
import modelo.Clase;
import modelo.Personaje;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicioImpl implements Servicio {

    @Override
    public void crearPersonaje(Personaje personaje) throws SQLException {
        try (Connection conn = Database.getConnection()) {
            CrudDAO<Personaje> dao = new PersonajeDAO(conn);
            dao.insertar(personaje);
        }
    }

    @Override
    public List<Clase> listarClases() throws SQLException {
        List<Clase> clases = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            CrudDAO<Clase> dao = new ClaseDAO(conn);
            clases = dao.listar();
        }
        return clases;
    }

    @Override
    public List<Personaje> listarPersonajes() throws SQLException {
        List<Personaje> personajes = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            CrudDAO<Personaje> dao = new PersonajeDAO(conn);
            personajes = dao.listar();
        }
        return personajes;
    }

    @Override
    public Personaje obtenerPersonaje(long id) throws SQLException {
        Personaje personaje = null;
        try (Connection conn = Database.getConnection()) {
            CrudDAO<Personaje> dao = new PersonajeDAO(conn);
            personaje = dao.obtener(id);
        }
        return personaje;
    }

    @Override
    public Clase obtenerClase(long id) throws SQLException {
        Clase clase = null;
        try (Connection conn = Database.getConnection()) {
            CrudDAO<Clase> dao = new ClaseDAO(conn);
            clase = dao.obtener(id);
        }
        return clase;
    }

    @Override
    public List<Batalla> listarBatallas() throws SQLException {
        List<Batalla> batallas = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            CrudDAO<Batalla> dao = new BatallaDAO(conn);
            batallas = dao.listar();
        }
        return batallas;
    }

    @Override
    public Batalla combatePersonaje(Personaje atacante, Personaje defensor, Personaje vencedor) throws SQLException {
        Batalla batalla = null;
        try (Connection conn = Database.getConnection()) {
            CrudDAO<Batalla> batallaDAO = new BatallaDAO(conn);
            CrudDAO<Personaje> personajeDAO = new PersonajeDAO(conn);
            String mensaje = "";
            if (atacante.estaMuerto()) {
                mensaje = atacante.getNombre() + " esta muerto.";
            }
            if ( defensor.estaMuerto()) {
                mensaje = defensor.getNombre() + " esta muerto.";
            }
            batalla = new Batalla(atacante, defensor, vencedor, LocalDate.now().atStartOfDay(), mensaje);
            try {
                // Para eliminar autocommit
                conn.setAutoCommit(false);
                batallaDAO.insertar(batalla);
                personajeDAO.actualizar(atacante);
                personajeDAO.actualizar(defensor);
                conn.commit();
            } catch ( SQLException ex ) {
                conn.rollback();
                throw ex;
            }
        }

        return batalla;
    }
}
