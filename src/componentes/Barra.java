package componentes;

import bus.EventBus;
import bus.Suscriptor;
import eventos.BebidaServidaEvent;
import eventos.PedidoRealizadoEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Componente híbrido (Suscriptor y Publicador).
 * Escucha PedidoRealizadoEvent, filtra bebidas y tras 2 segundos publica BebidaServidaEvent.
 */
public class Barra implements Suscriptor<PedidoRealizadoEvent> {

    private static final Set<String> CATALOGO_BEBIDAS = Set.of(
            "cerveza", "hidromiel", "vino", "refresco", "poción", "agua", "cafe"
    );

    private final EventBus eventBus;

    public Barra(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onEvent(PedidoRealizadoEvent evento) {
        for (String item : evento.getItems()) {
            if (esBebida(item)) {
                CompletableFuture.runAsync(() -> prepararBebida(evento.getMesaId(), item));
            }
        }
    }

    private boolean esBebida(String item) {
        String normalizado = item.toLowerCase().trim();
        return CATALOGO_BEBIDAS.stream().anyMatch(normalizado::contains);
    }

    private void prepararBebida(String mesaId, String bebida) {
        try {
            System.out.println("[BARRA] Preparando bebida '" + bebida + "' para " + mesaId + " (2s)...");
            Thread.sleep(2000);
            eventBus.publicar(new BebidaServidaEvent(mesaId, bebida));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}