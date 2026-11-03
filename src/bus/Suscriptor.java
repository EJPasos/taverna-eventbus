package bus;

/**
 * Interfaz funcional para cualquier componente o función que desee
 * suscribirse a un tipo específico de Evento en el EventBus.
 *
 * @param <T> Tipo de evento al que reacciona el suscriptor.
 */
@FunctionalInterface
public interface Suscriptor<T extends Evento> {
    void onEvent(T evento);
}