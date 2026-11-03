package eventos;

import bus.Evento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Evento inmutable emitido cuando un cliente realiza un pedido en la taberna.
 */
public final class PedidoRealizadoEvent implements Evento {

    private final UUID id;
    private final LocalDateTime timestamp;
    private final String mesaId;
    private final String pedidoId;
    private final List<String> items;

    public PedidoRealizadoEvent(String mesaId, String pedidoId, List<String> items) {
        this.id = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.mesaId = mesaId;
        this.pedidoId = pedidoId;
        this.items = List.copyOf(items); // Garantiza inmutabilidad
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

    public String getPedidoId() {
        return pedidoId;
    }

    public List<String> getItems() {
        return items;
    }
}