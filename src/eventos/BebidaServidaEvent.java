package eventos;

import bus.Evento;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Evento inmutable emitido cuando la barra termina de servir una bebida.
 */
public final class BebidaServidaEvent implements Evento {

    private final UUID id;
    private final LocalDateTime timestamp;
    private final String mesaId;
    private final String bebida;

    public BebidaServidaEvent(String mesaId, String bebida) {
        this.id = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.mesaId = mesaId;
        this.bebida = bebida;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMesaId() {
        return mesaId;
    }

    public String getBebida() {
        return bebida;
    }
}