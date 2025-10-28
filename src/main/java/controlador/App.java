package controlador;

import helpers.ConsoleHelper;
import modelo.Batalla;
import modelo.Clase;
import modelo.Personaje;
import servicio.Servicio;
import servicio.ServicioImpl;

import java.sql.SQLException;
import java.util.List;

public class App {

    public static void menu() {
        do {
            System.out.println("\n=== RPG MANAGER ===");
            System.out.println("1. Crear personaje");
            System.out.println("2. Listar personajes");
            System.out.println("3. Combatir");
            System.out.println("4. Listar Batallas");
            System.out.println("0. Salir");
            int op = ConsoleHelper.pedirEntero("Selecciona opción: ", 0, 4);
            switch (op) {
                case 1: {
                    altaPersonaje();
                    menu();
                }
                ;
                break;
                case 2: {
                    listarPersonajes();
                    menu();
                }
                ;
                break;
                case 3: {
                    combatir();
                    menu();
                }
                ;
                break;
                case 4: {
                    listarBatallas();
                    menu();
                }
                ;
                case 0:
                    System.out.println("FIN RPG MANAGER");
                    System.exit(0);
            }
        } while (true);
    }

    public static void altaPersonaje() {
        try {
            Servicio servicio = new ServicioImpl();
            Clase clase = null;
            List<Clase> clases = null;
            clases = servicio.listarClases();
            clases.forEach(System.out::println);
            do {
                long optClase = ConsoleHelper.pedirEntero("Indica clase del personaje: ");
                clase = clases.stream()
                        .filter(p -> p.getId() == optClase)
                        .findFirst()
                        .orElse(null);
                if (clase == null) System.out.println("Clase no existe. Vuelve a intentarlo.");
            } while (clase == null);
            String nombrePersonaje = ConsoleHelper.pedirCadena("Nombre del personaje:");
            Personaje personaje = new Personaje(nombrePersonaje, clase);
            servicio.crearPersonaje(personaje);
            System.out.println("Personaje Creado");
            System.out.println(personaje);
        } catch (SQLException ex) {
            System.out.println("Código de error: " + ex.getErrorCode());
            System.out.println("Mensaje: " + ex.getMessage());
            System.out.println("Estado SQL" + ex.getSQLState());
        }
    }

    public static void listarPersonajes() {
        try {
            Servicio servicio = new ServicioImpl();
            List<Personaje> personajes = null;
            personajes = servicio.listarPersonajes();
            if (!personajes.isEmpty())
                personajes.forEach(System.out::println);
            else
                System.out.println("No hay personajes registrados");
        } catch (SQLException ex) {
            System.out.println("Error obteniendo listado de personajes. " + ex.getMessage());
        }
    }

    public static void listarBatallas() {
        try {
            Servicio servicio = new ServicioImpl();
            List<Batalla> batallas = null;
            batallas = servicio.listarBatallas();
            if (!batallas.isEmpty())
                batallas.forEach(System.out::println);
            else
                System.out.println("No hay clases registrados");
        } catch (SQLException ex) {
            System.out.println("Error obteniendo listado de clases. " + ex.getMessage());
        }
    }

    public static void combatir() {
        try {
            Servicio servicio = new ServicioImpl();
            Batalla batalla = null;
            Personaje ataPersonaje = null;
            Personaje defPersonaje = null;
            Personaje vencedor = null;

            List<Personaje> personajes = servicio.listarPersonajes();
            personajes.forEach(System.out::println);

            do {
                long optPersonajeAta = ConsoleHelper.pedirEntero("Selecciona personaje atacante: ");
                ataPersonaje = personajes.stream()
                        .filter(p -> p.getId() == optPersonajeAta)
                        .findFirst()
                        .orElse(null);
                if (ataPersonaje == null) System.out.println("Personaje no existe. Vuelve a intentarlo.");
            } while (ataPersonaje == null);

            do {
                long optPersonajeDef = ConsoleHelper.pedirEntero("Selecciona personaje defensor: ");
                defPersonaje = personajes.stream()
                        .filter(p -> p.getId() == optPersonajeDef)
                        .findFirst()
                        .orElse(null);
                if (defPersonaje == null || ataPersonaje == defPersonaje)
                    System.out.println("Personaje no existe o es igual al atacante. Vuelve a intentarlo.");
            } while (defPersonaje == null || ataPersonaje == defPersonaje);

            float ataque_atacante = ataPersonaje.calcularAtaque();
            float ataque_defensor = defPersonaje.calcularAtaque();

            if (ataque_atacante > ataque_defensor) {
                vencedor = ataPersonaje;
                ataPersonaje.subirExperiencia((int) (10 * ataPersonaje.getNivel()));
                ataPersonaje.setVida((int) (ataPersonaje.getVida() + Math.abs(ataque_atacante - ataque_defensor)));
                defPersonaje.subirExperiencia((int) (3 * ataPersonaje.getNivel()));
                defPersonaje.pierdeVida((int) Math.abs(ataque_atacante - ataque_defensor));
            } else {
                vencedor = defPersonaje;
                defPersonaje.subirExperiencia((int) (10 * ataPersonaje.getNivel()));
                defPersonaje.setVida((int) (ataPersonaje.getVida() + (Math.abs(ataque_atacante - ataque_defensor))));
                ataPersonaje.subirExperiencia((int) (3 * ataPersonaje.getNivel()));
                ataPersonaje.pierdeVida((int) Math.abs(ataque_atacante - ataque_defensor));
            }

            batalla = servicio.combatePersonaje(ataPersonaje, defPersonaje, vencedor);
            System.out.println("Resultado de la batalla:");
            System.out.println(batalla);

        } catch (SQLException ex) {
            System.out.println("Código de error: " + ex.getErrorCode());
            System.out.println("Mensaje: " + ex.getMessage());
            System.out.println("Estado SQL" + ex.getSQLState());
        }
    }

    static void main(String[] args) {
        menu();
    }
}

