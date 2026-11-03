package eventos;

import bus.Evento;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Evento inmutable emitido cuando la cocina termina de preparar un plato.
 */
public final class ComidaPreparadaEvent implements Evento {

    private final UUID id;
    private final LocalDateTime timestamp;
    private final String pedidoId;
    private final String plato;

    public ComidaPreparadaEvent(String pedidoId, String plato) {
        this.id = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.pedidoId = pedidoId;
        this.plato = plato;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public String getPlato() {
        return plato;
    }
}