package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Batalla {

    private Long id;
    private Personaje id_atacante;
    private Personaje id_defensor;
    private Personaje id_vencedor;
    private LocalDateTime fecha;
    private String resumen;

    public Batalla() {
    }

    public Batalla(Personaje id_atacante, Personaje id_defensor, Personaje id_vencedor, LocalDateTime fecha, String resumen) {
        this.id_atacante = id_atacante;
        this.id_defensor = id_defensor;
        this.id_vencedor = id_vencedor;
        this.fecha = fecha;
        this.resumen = resumen;
    }

    public Batalla(Long id, Personaje id_atacante, Personaje id_defensor, Personaje id_vencedor, LocalDateTime fecha, String resumen) {
        this.id = id;
        this.id_atacante = id_atacante;
        this.id_defensor = id_defensor;
        this.id_vencedor = id_vencedor;
        this.fecha = fecha;
        this.resumen = resumen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personaje getId_atacante() {
        return id_atacante;
    }

    public void setId_atacante(Personaje id_atacante) {
        this.id_atacante = id_atacante;
    }

    public Personaje getId_defensor() {
        return id_defensor;
    }

    public void setId_defensor(Personaje id_defensor) {
        this.id_defensor = id_defensor;
    }

    public Personaje getId_vencedor() {
        return id_vencedor;
    }

    public void setId_vencedor(Personaje id_vencedor) {
        this.id_vencedor = id_vencedor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return  "#" + id +
                "El ( " + fecha.format(formatter) +
                " ) Combate " + id_atacante.getNombre() +
                " VS " + id_defensor.getNombre() +
                " Vencedor " + id_vencedor.getNombre() +
                ". " + resumen;
    }
}
