package componentes;

import bus.EventBus;
import bus.Suscriptor;
import eventos.ComidaPreparadaEvent;
import eventos.PedidoRealizadoEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Componente híbrido (Suscriptor y Publicador).
 * Escucha PedidoRealizadoEvent, filtra platillos y tras 5 segundos publica ComidaPreparadaEvent.
 */
public class Cocina implements Suscriptor<PedidoRealizadoEvent> {

    private static final Set<String> CATALOGO_COMIDA = Set.of(
            "hamburguesa", "pizza", "alitas", "papas", "tacos", "nachos", "estofado"
    );

    private final EventBus eventBus;

    public Cocina(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onEvent(PedidoRealizadoEvent evento) {
        for (String item : evento.getItems()) {
            if (esComida(item)) {
                CompletableFuture.runAsync(() -> cocinarComida(evento.getPedidoId(), item));
            }
        }
    }

    private boolean esComida(String item) {
        String normalizado = item.toLowerCase().trim();
        return CATALOGO_COMIDA.stream().anyMatch(normalizado::contains);
    }

    private void cocinarComida(String pedidoId, String plato) {
        try {
            System.out.println("[COCINA] Cocinando '" + plato + "' para el pedido " + pedidoId + " (5s)...");
            Thread.sleep(5000);
            eventBus.publicar(new ComidaPreparadaEvent(pedidoId, plato));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}