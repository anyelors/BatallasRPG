package controlador;

import modelo.Batalla;
import modelo.Clase;
import modelo.Personaje;
import servicio.Servicio;
import servicio.ServicioImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.CrudDAO;

public class App {

    public static int menu(Scanner scn) {

        int opt;

        do {
            System.out.println("=== RPG MANAGER ===");
            System.out.println("1. Crear personaje");
            System.out.println("2. Listar personajes");
            System.out.println("3. Combatir");
            System.out.println("4. Listar Batallas");
            System.out.println("0. Salir");
            System.out.println("Selecciona opción:");
            opt = Integer.parseInt(scn.nextLine());
        } while (opt < 0 || opt > 5);

        return opt;
    }

    public static List<Personaje> listarPersonajes(Servicio ser){
        List<Personaje> personajes = null;
        try {
                personajes = ser.listarPersonajes();
                personajes.forEach(System.out::println);
            } catch (SQLException ex) {
                System.out.println("Código de error: " + ex.getErrorCode());
                System.out.println("Mensaje: " + ex.getMessage());
                System.out.println("Estado SQL" + ex.getSQLState());
            }
            return personajes;
    }

    public static void main(String[] args) {

        Servicio servicio = new ServicioImpl();
        Scanner scn = new Scanner(System.in);
        int opt = -1, optClase = 0, optPersonaje = 0;
        String nombrePersonaje;
        long valid = 0;

        opt = menu(scn);

        //Salir
        if (opt == 0) {
            System.out.println("FINALIZA RPG MANAGER");
            return;
        }

        //Crear personaje
        if (opt == 1) {
            do {
                try {
                    System.out.println("Indica clase del personaje:");
                    List<Clase> clases = servicio.listarClases();
                    clases.forEach(System.out::println);
                    System.out.println("Clase ID:");
                    optClase = Integer.parseInt(scn.nextLine());
                    int finalOptClase = optClase;
                    valid = clases.stream()
                            .filter(p -> p.getId() == finalOptClase)
                            .count();
                } catch (SQLException ex) {
                    System.out.println("Código de error: " + ex.getErrorCode());
                    System.out.println("Mensaje: " + ex.getMessage());
                    System.out.println("Estado SQL" + ex.getSQLState());
                }
            } while (valid == 0);

            System.out.println("Nombre del personaje:");
            nombrePersonaje = scn.nextLine();

            try {
                Clase clase = servicio.obtenerClase(optClase);
                Personaje personaje = new Personaje(nombrePersonaje, clase);
                servicio.crearPersonaje(personaje);
                System.out.println("Personaje Creado");
            } catch (SQLException ex) {
                System.out.println("Código de error: " + ex.getErrorCode());
                System.out.println("Mensaje: " + ex.getMessage());
                System.out.println("Estado SQL" + ex.getSQLState());
            }

            opt = menu(scn);
        }

        //Listar personajes
        if (opt == 2) {
            
            listarPersonajes(servicio);
            opt = menu(scn);
        }

        //Combatir
        if (opt == 3) {      
            
            try {
                Batalla batalla = new Batalla();
                Personaje ataPersonaje = new Personaje();
                Personaje defPersonaje = new Personaje();
                Long seleccion = 0L;

                do {
                List<Personaje> personajes = listarPersonajes(servicio);
                System.out.println("Selecciona personaje atacante:");
                seleccion =  Long.parseLong(scn.nextLine());
                    Long finalSelecciAtc = seleccion;
                    valid = personajes.stream()
                            .filter(p -> p.getId() == finalSelecciAtc)
                            .count();
                } while (valid == 0);

                ataPersonaje = servicio.obtenerPersonaje(seleccion);

                do{
                    List<Personaje> personajes = listarPersonajes(servicio);
                    System.out.println("Selecciona personaje atacado:");
                    seleccion = Long.parseLong(scn.nextLine());
                    Long finalSeleccionDef = seleccion;
                    valid = personajes.stream()
                            .filter(p -> p.getId() == finalSeleccionDef)
                            .count();
                } while (valid == 0);

                defPersonaje = servicio.obtenerPersonaje(seleccion);

                ataPersonaje.calcularAtaque();
                defPersonaje.calcularAtaque();
                
                batalla = servicio.combatePersonaje(ataPersonaje, defPersonaje, batalla.getVencedor());
                System.out.println("Resultado de la batalla:");
                System.out.println(batalla);

            } catch (SQLException ex) {
                System.out.println("Código de error: " + ex.getErrorCode());
                System.out.println("Mensaje: " + ex.getMessage());
                System.out.println("Estado SQL" + ex.getSQLState());
            }

            opt = menu(scn);
        }

        //Listar Batallas
        if (opt == 4) {
            try {
                List<Batalla> batallas = servicio.listarBatallas();
                batallas.forEach(System.out::println);
            } catch (SQLException ex) {
                System.out.println("Código de error: " + ex.getErrorCode());
                System.out.println("Mensaje: " + ex.getMessage());
                System.out.println("Estado SQL" + ex.getSQLState());
            }
            opt = menu(scn);
        }

    }
}
