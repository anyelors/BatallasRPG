package modelo;

import java.util.Random;

public class Personaje {

    private Long id;
    private String nombre;
    private Clase clase;
    private Long nivel;
    private Long experiencia;
    private Long vida;

    public Personaje() {
    }

    public Personaje(String nombre, Clase id_clase) {
        this.nombre = nombre;
        this.clase = id_clase;
        this.nivel = 0L;
        this.experiencia = 0L;
        this.vida = 100L;
    }

    public Personaje(Long id, String nombre, Clase id_clase, Long nivel, Long experiencia, Long vida) {
        this.id = id;
        this.nombre = nombre;
        this.clase = id_clase;
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

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
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

    // Reduce la vida del personaje por el daño recibido. Si el daño es superior a la vida, esta se reduce 0.
    public void pierdeVida( Long daño ) {
        if (daño >= this.vida) {
            this.vida = 0L;
        } else {
            this.vida -= daño;
        }
    }

    //Indica si la vida del persona es 0
    public boolean estaMuerto() {
        return this.vida <= 0;
    }

    /*
    Si el personaje es un Guerrero la bonificación es su nivel * 3 que lo hace depender de su fuerza
    SI el personaje es un Mago la bonificación es su experiencia / 5 que lo hace depender de su experiencia
    Si el personaje es un Arquero la bonificación es un valor aleatorio entre 0 y 20 que lo hace depender de su puntería
    Si el personaje es un Paladín la bonificación es su vida / 10 que lo hace depender de su vitalidad en el momento.
    */
    public float obtenerBonus(Clase id_clase){
        Random rnd = new Random();
        return  switch (this.clase.getNombre()) {
            case "Guerrero" -> (this.nivel * 3);
            case "Mago" -> ((float) this.experiencia / 5);
            case "Arquero" -> rnd.nextInt(21);
            case "Paladín" -> ((float) this.vida / 10);
            default -> 0.0f;
        };
    }

    /*
    Aumenta la experiencia del guerrero con el valor dado.
    Si la experiencia resultante rebasa el valor 100 + ( nivel * 5 ),
    entonces se incrementa en 1 el nivel del personaje y se deja la experiencia con el remanente.
    */
    public void subirExperiencia( int experiencia ) {
        this.experiencia += experiencia;
        Long experienciaParaSubirNivel = 100 + ( this.nivel * 5 );
        if ( this.experiencia >= experienciaParaSubirNivel ) {
            this.experiencia = this.experiencia - experienciaParaSubirNivel;
            this.nivel += 1;
        }
    }

    /*
     * Los personajes pueden combatir entre ellos. En un combate deben seleccionarse dos personajes ( uno hace de atacante y el otro de defensor ). 
       Cuando dos personajes combaten se calculan unos puntos de ataque para cada uno de ellos empleando una fórmula concreta que depende del nivel, vida y clase del personaje:

       puntosAtaque = ( nivel * 10 ) + ( vida / 5 ) + ( bonificacion_clase ) + ( bonificación_suerte )

       La bonificación de la suerte es un valor aleatorio que suma entre 0 y 15 puntos de ataque máximo

       El personaje que saca una mayor cantidad de puntos de ataque es declarado vencedor de la batalla. Este aumenta su experiencia un total de 10 * nivel puntos de experiencia y recupera vida un valor equivalente a la mitad de la diferencia entre los puntos de ataque de ambos personajes. La vida no puede incrementarse por encima de 100

       El personaje vencido pierde en vida la diferencia entre los puntos de ataque del vencedor y los suyos. SI el total de vida remanente queda inferior a 0 se asigna 0 y el personaje es considerado muerto. En caso contrario, se considera que el personaje ha sobrevivido al ataque y aumenta su experiencia un total de 3 * nivel puntos de experiencia por haber sobrevivido.
     */
    public float calcularAtaque() {
        Random rnd = new Random();
        float bonificacionClase = obtenerBonus(this.clase);
        float bonificacionSuerte = rnd.nextInt(15);
        return (this.nivel * 10) + (this.vida / 5) + bonificacionClase + bonificacionSuerte;
    }

    @Override
    public String toString() {
        return "#" + id +
                " " + nombre +
                "[" + clase.getNombre() +
                "] - Nivel " + nivel +
                " (Exp " + experiencia +
                ", Vida  " + vida +
                ')';
    }

}
