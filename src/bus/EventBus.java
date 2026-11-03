package bus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * EventBus central encargado de gestionar las suscripciones
 * y enrutar los eventos publicados hacia los suscriptores registrados.
 */
public class EventBus {

    private final Map<Class<? extends Evento>, List<Suscriptor<? extends Evento>>> suscriptores = new ConcurrentHashMap<>();

    /**
     * Registra un suscriptor para ser notificado cuando se publique un evento del tipo indicado.
     *
     * @param tipoDeEvento Clase del evento al que se suscribe.
     * @param suscriptor   Instancia o función suscriptora.
     * @param <T>          Tipo del evento.
     */
    public <T extends Evento> void suscribir(Class<T> tipoDeEvento, Suscriptor<? super T> suscriptor) {
        suscriptores
                .computeIfAbsent(tipoDeEvento, k -> new CopyOnWriteArrayList<>())
                .add(suscriptor);
    }

    /**
     * Remueve un suscriptor previamente registrado para un tipo de evento específico.
     *
     * @param tipoDeEvento Clase del evento.
     * @param suscriptor   Suscriptor a remover.
     * @param <T>          Tipo del evento.
     */
    public <T extends Evento> void desuscribir(Class<T> tipoDeEvento, Suscriptor<? super T> suscriptor) {
        List<Suscriptor<? extends Evento>> lista = suscriptores.get(tipoDeEvento);
        if (lista != null) {
            lista.remove(suscriptor);
        }
    }

    /**
     * Publica un evento y notifica a todos los suscriptores registrados para su tipo concreto.
     *
     * @param evento Instancia inmutable del evento a publicar.
     * @param <T>    Tipo del evento.
     */
    @SuppressWarnings("unchecked")
    public <T extends Evento> void publicar(T evento) {
        if (evento == null) {
            return;
        }
        List<Suscriptor<? extends Evento>> lista = suscriptores.get(evento.getClass());
        if (lista != null) {
            for (Suscriptor<? extends Evento> suscriptor : lista) {
                Suscriptor<? super T> s = (Suscriptor<? super T>) suscriptor;
                s.onEvent(evento);
            }
        }
    }
}