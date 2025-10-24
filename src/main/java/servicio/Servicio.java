package servicio;

import modelo.Batalla;
import modelo.Clase;
import modelo.Personaje;

import java.sql.SQLException;
import java.util.List;

public interface Servicio {

    public void crearPersonaje( Personaje personaje ) throws SQLException;

    public List<Clase> listarClases() throws SQLException;

    public List<Personaje> listarPersonajes() throws SQLException;

    public Personaje obtenerPersonaje( long id ) throws SQLException;

    public Clase obtenerClase( long id ) throws SQLException;

    public List<Batalla> listarBatallas() throws SQLException;

    public Batalla combatePersonaje(Personaje atacante, Personaje defensor, Personaje vencedor) throws SQLException;

}
