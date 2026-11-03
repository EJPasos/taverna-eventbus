package eventos;

import bus.Evento;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Evento inmutable emitido cuando la banda comienza a tocar una canción.
 */
public final class BandaTocandoEvent implements Evento {

    private final UUID id;
    private final LocalDateTime timestamp;
    private final String nombreBanda;
    private final String nombreCancion;
    private final int duracion;

    public BandaTocandoEvent(String nombreBanda, String nombreCancion, int duracion) {
        this.id = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.nombreBanda = nombreBanda;
        this.nombreCancion = nombreCancion;
        this.duracion = duracion;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getNombreBanda() {
        return nombreBanda;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public int getDuracion() {
        return duracion;
    }
}