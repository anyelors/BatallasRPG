package v1;

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
import java.util.List;

public class EjemploBatallaRPGDAO {
    public static void main(String[] args) {
        List<Personaje> personajes;
        List<Clase> clases;
        List<Batalla> batallas;

        try (Connection con = Database.getConnection()) {
            CrudDAO<Clase> claseDAO = new ClaseDAO(con);
            CrudDAO<Personaje> personajesDAO = new PersonajeDAO(con);
            CrudDAO<Batalla> batallaDAO = new BatallaDAO(con);


            System.out.println("Listado de Clases de Jugador");
            clases = claseDAO.listar();
            clases.forEach(System.out::println);

            /*
            Personaje atc = personajesDAO.obtener(2L);
            Personaje def = personajesDAO.obtener(4L);
            Batalla batalla = new Batalla(atc, def, def, LocalDateTime.of(2025, 10, 22, 15, 25), "batalla a muerte");
            batallaDAO.alta(batalla);

            atc = personajesDAO.obtener(3L);
            def = personajesDAO.obtener(5L);
            batalla = new Batalla(atc, def, atc, LocalDateTime.of(2025, 10, 22, 15, 25), "batalla o muere");
            batallaDAO.alta(batalla);
            */

            System.out.println("Listado de Batallas");
            batallas = batallaDAO.listar();
            batallas.forEach(System.out::println);

            /*
            Clase clase = claseDAO.obtener(1L);
            Personaje personaje1 = new Personaje("Pie Grande", clase);
            personajesDAO.alta(personaje1);

            clase = claseDAO.obtener(4L);
            personaje1 = new Personaje("Mascara", clase);
            personajesDAO.alta(personaje1);

            clase = claseDAO.obtener(2L);
            personaje1 = new Personaje("El Pecas", clase);
            personajesDAO.alta(personaje1);

            clase = claseDAO.obtener(3L);
            personaje1 = new Personaje("Care Puntilla", clase);
            personajesDAO.alta(personaje1);
            */

            System.out.println("Listar personajes");
            personajes = personajesDAO.listar();
            personajes.forEach(System.out::println);

            System.out.println("Obtener personaje");
            Personaje personaje = personajesDAO.obtener(1L);
            System.out.println(personaje);

        } catch (SQLException ex) {
            System.out.println("Código error: + " + ex.getErrorCode());
            System.out.println("Mensaje error: + " + ex.getMessage());
            System.out.println("Estado SQL: + " + ex.getSQLState());
        }
    }
}
