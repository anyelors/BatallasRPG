package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Batalla {

    private Long id;
    private Personaje atacante;
    private Personaje defensor;
    private Personaje vencedor;
    private LocalDateTime fecha;
    private String resumen;

    public Batalla() {
    }

    public Batalla(Personaje atacante, Personaje defensor, Personaje vencedor, LocalDateTime fecha, String resumen) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.vencedor = vencedor;
        this.fecha = fecha;
        this.resumen = resumen;
    }

    public Batalla(Long id, Personaje atacante, Personaje defensor, Personaje vencedor, LocalDateTime fecha, String resumen) {
        this.id = id;
        this.atacante = atacante;
        this.defensor = defensor;
        this.vencedor = vencedor;
        this.fecha = fecha;
        this.resumen = resumen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personaje getAtacante() {
        return atacante;
    }

    public void setAtacante(Personaje atacante) {
        this.atacante = atacante;
    }

    public Personaje getDefensor() {
        return defensor;
    }

    public void setDefensor(Personaje defensor) {
        this.defensor = defensor;
    }

    public Personaje getVencedor() {
        return vencedor;
    }

    public void setVencedor(Personaje vencedor) {
        this.vencedor = vencedor;
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
    public boolean equals(Object o) {
        if (!(o instanceof Batalla batalla)) return false;
        return Objects.equals(id, batalla.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return  "#" + id +
                "El ( " + fecha.format(formatter) +
                " ) Combate " + atacante.getNombre() +
                " VS " + defensor.getNombre() +
                ", Vencedor " + vencedor.getNombre() +
                ". " + resumen;
    }
}
