package modelo;

public class Clase {
    private long id;
    private String nombre;

    public Clase() {
    }

    public Clase(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //constructor o método para obtener nombre desde id
    public Clase(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
