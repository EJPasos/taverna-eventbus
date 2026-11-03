package componentes;

import bus.Evento;
import bus.Suscriptor;
import eventos.BandaTocandoEvent;
import eventos.BebidaServidaEvent;
import eventos.ComidaPreparadaEvent;
import eventos.PedidoRealizadoEvent;

/**
 * Suscriptor que se suscribe a múltiples eventos del sistema y muestra
 * anuncios llamativos con códigos de color ANSI en la consola.
 */
public class PanelLED implements Suscriptor<Evento> {

    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String AMARILLO = "\u001B[33m";
    private static final String VERDE = "\u001B[32m";
    private static final String MAGENTA = "\u001B[35m";

    @Override
    public void onEvent(Evento evento) {
        if (evento instanceof PedidoRealizadoEvent e) {
            System.out.println(CYAN + "[PANEL LED]  ¡Nuevo pedido registrado en " + e.getMesaId()
                    + " (ID: " + e.getPedidoId() + ")!" + RESET);
        } else if (evento instanceof BebidaServidaEvent e) {
            System.out.println(AMARILLO + "[PANEL LED]  ¡Nueva bebida servida (" + e.getBebida()
                    + ") en la Mesa " + e.getMesaId() + "!" + RESET);
        } else if (evento instanceof ComidaPreparadaEvent e) {
            System.out.println(VERDE + "[PANEL LED]  ¡" + e.getPlato()
                    + " lista para entregar en el Pedido " + e.getPedidoId() + "!" + RESET);
        } else if (evento instanceof BandaTocandoEvent e) {
            System.out.println(MAGENTA + "[PANEL LED]  " + e.getNombreBanda()
                    + " tocando en vivo: \"" + e.getNombreCancion() + "\" (" + e.getDuracion() + "s) " + RESET);
        }
    }
}