package componentes;

import bus.EventBus;
import eventos.BandaTocandoEvent;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Publicador que representa a la banda en vivo de la taberna.
 * Publica BandaTocandoEvent al iniciar una canción o periódicamente cada X segundos.
 */
public class Banda {

    private final String nombreBanda;
    private final EventBus eventBus;
    private ScheduledExecutorService scheduler;

    public Banda(String nombreBanda, EventBus eventBus) {
        this.nombreBanda = nombreBanda;
        this.eventBus = eventBus;
    }

    /**
     * Publica inmediatamente que la banda está tocando una canción específica.
     */
    public void tocarCancion(String nombreCancion, int duracionSegundos) {
        BandaTocandoEvent evento = new BandaTocandoEvent(nombreBanda, nombreCancion, duracionSegundos);
        eventBus.publicar(evento);
    }

    /**
     * Simula un repertorio tocando una canción de la lista cada X segundos.
     */
    public void iniciarConciertoPeriodico(List<String> repertorio, int intervaloSegundos) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        AtomicInteger indice = new AtomicInteger(0);

        scheduler.scheduleAtFixedRate(() -> {
            int i = indice.getAndIncrement();
            if (i < repertorio.size()) {
                tocarCancion(repertorio.get(i), 180);
            } else {
                detenerConcierto();
            }
        }, 0, intervaloSegundos, TimeUnit.SECONDS);
    }

    public void detenerConcierto() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
}