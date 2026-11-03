package componentes;

import bus.EventBus;
import eventos.PedidoRealizadoEvent;

import java.util.List;

/**
 * Publicador encargado de recibir las órdenes de los clientes
 * y publicar PedidoRealizadoEvent en el EventBus.
 */
public class SistemaDePedidos {

    private final EventBus eventBus;

    public SistemaDePedidos(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void realizarPedido(String mesaId, String pedidoId, List<String> items) {
        System.out.println("[SISTEMA DE PEDIDOS] Recibiendo pedido '" + pedidoId + "' de '" + mesaId + "': " + items);
        PedidoRealizadoEvent evento = new PedidoRealizadoEvent(mesaId, pedidoId, items);
        eventBus.publicar(evento);
    }
}