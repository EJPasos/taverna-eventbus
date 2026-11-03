package bus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Interfaz base para todos los eventos del sistema.
 * Garantiza que cada evento tenga metadatos básicos inmutables.
 */
public interface Evento {
    UUID getId();
    LocalDateTime getTimestamp();
}