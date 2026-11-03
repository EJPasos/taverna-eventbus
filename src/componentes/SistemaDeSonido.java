package componentes;

import bus.Suscriptor;
import eventos.BandaTocandoEvent;

/**
 * Suscriptor que escucha cuando la banda toca una canción y ajusta el ecualizador.
 */
public class SistemaDeSonido implements Suscriptor<BandaTocandoEvent> {

    @Override
    public void onEvent(BandaTocandoEvent evento) {
        System.out.println("[SISTEMA DE SONIDO] Ajustando ecualizador para: " + evento.getNombreCancion());
    }
}