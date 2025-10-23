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

    public Clase(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Clase [" +
                "Id = " + id +
                ", Nombre = '" + nombre + '\'' +
                ']';
    }
}
