package modelo;

public class Personaje {

    private Long id;
    private String nombre;
    private Clase id_clase;
    private Long nivel;
    private Long experiencia;
    private Long vida;

    public Personaje() {
    }

    public Personaje(String nombre, Clase id_clase, Long nivel, Long experiencia, Long vida) {
        this.nombre = nombre;
        this.id_clase = id_clase;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.vida = vida;
    }

    public Personaje(Long id, String nombre, Clase id_clase, Long nivel, Long experiencia, Long vida) {
        this.id = id;
        this.nombre = nombre;
        this.id_clase = id_clase;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.vida = vida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Clase getId_clase() {
        return id_clase;
    }

    public void setId_clase(Clase id_clase) {
        this.id_clase = id_clase;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public Long getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Long experiencia) {
        this.experiencia = experiencia;
    }

    public Long getVida() {
        return vida;
    }

    public void setVida(Long vida) {
        this.vida = vida;
    }

    @Override
    public String toString() {
        return "Personaje [" +
                "Id = " + id +
                ", Nombre = '" + nombre + '\'' +
                ", Clase = " + id_clase.getNombre() +
                ", Nivel = " + nivel +
                ", Experiencia = " + experiencia +
                ", Vida = " + vida +
                ']';
    }
}
