package componentes;

import bus.Suscriptor;
import eventos.BandaTocandoEvent;

/**
 * Suscriptor adicional para demostrar el desacoplamiento y la extensibilidad.
 * Reacciona únicamente cuando la banda toca "Through the Fire and Flames".
 */
public class SistemaDeHumo implements Suscriptor<BandaTocandoEvent> {

    private static final String CANCION_EPICA = "Through the Fire and Flames";
    private static final String ROJO_BRILLANTE = "\u001B[91m";
    private static final String RESET = "\u001B[0m";

    @Override
    public void onEvent(BandaTocandoEvent evento) {
        if (CANCION_EPICA.equalsIgnoreCase(evento.getNombreCancion().trim())) {
            System.out.println(ROJO_BRILLANTE
                    + "[SISTEMA DE HUMO] 🔥💨 ¡CANCION ÉPICA DETECTADA! Disparando ráfagas de humo y láseres para \""
                    + evento.getNombreCancion() + "\" 💨🔥" + RESET);
        }
    }
}