package v1;

import dao.CrudDAO;
import dao.PersonajeDAO;
import datasource.Database;
import modelo.Personaje;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EjemploBatallaRPGDAO {
    public static void main(String[] args) {
        List<Personaje> personajes;
        try (Connection con = Database.getConnection()){
            CrudDAO<Personaje> personajesDAO = new PersonajeDAO(con);

            personajes = personajesDAO.listar();
            personajes.forEach(System.out::println);

        } catch (SQLException ex){
            System.out.println("Codigo error: + " + ex.getErrorCode());
            System.out.println("Mensaje error: + " + ex.getMessage());
            System.out.println("Estado SQL: + " + ex.getSQLState());
        }
    }
}
