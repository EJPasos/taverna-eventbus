# La Taberna del Bus de Eventos - "El Gólem Alquímico"

Implementación en Java del patrón arquitectónico **Publisher/Subscriber** mediante un **Event Bus** desacoplado, seguro para concurrencia (*thread-safe*) y fuertemente tipado.

---

## Descripción de la Arquitectura

El sistema modela las operaciones concurrentes de la taberna universitaria **"El Gólem Alquímico"**, eliminando las dependencias directas entre los módulos mediante un canal central de comunicación (`EventBus`):

1. **Núcleo (`bus`)**:
    * `Evento`: Interfaz base para todos los eventos con identificador único (`UUID`) y marca temporal (`LocalDateTime`).
    * `Suscriptor<T extends Evento>`: Interfaz funcional con el método `onEvent(T evento)`.
    * `EventBus`: Gestiona un mapa concurrente de suscriptores por tipo de evento (`Class<T>`) y provee las operaciones `suscribir`, `desuscribir` y `publicar`.

2. **Eventos Inmutables (`com.golemalquimico.eventos`)**:
    * `PedidoRealizadoEvent`: Contiene `mesaId`, `pedidoId` y una lista inmutable de `items`.
    * `BebidaServidaEvent`: Contiene `mesaId` y `bebida`.
    * `ComidaPreparadaEvent`: Contiene `pedidoId` y `plato`.
    * `BandaTocandoEvent`: Contiene `nombreBanda`, `nombreCancion` y `duracion`.

3. **Componentes (`componentes`)**:
    * `SistemaDePedidos` (*Publicador*): Recibe órdenes de las mesas y publica `PedidoRealizadoEvent`.
    * `Barra` (*Suscriptor y Publicador*): Escucha `PedidoRealizadoEvent`, identifica bebidas, las prepara durante **2 segundos** de forma asíncrona y publica `BebidaServidaEvent`.
    * `Cocina` (*Suscriptor y Publicador*): Escucha `PedidoRealizadoEvent`, identifica platillos de comida, los cocina durante **5 segundos** de forma asíncrona y publica `ComidaPreparadaEvent`.
    * `Banda` (*Publicador*): Publica `BandaTocandoEvent` al interpretar canciones.
    * `SistemaDeSonido` (*Suscriptor*): Escucha `BandaTocandoEvent` y ajusta el ecualizador automáticamente.
    * `PanelLED` (*Suscriptor*): Escucha todos los eventos de la taberna y despliega notificaciones con colores ANSI en la consola.
    * `SistemaDeHumo` (*Suscriptor - Extensibilidad*): Se suscribe a `BandaTocandoEvent` sin modificar `Banda` ni `EventBus`, activando efectos de humo exclusivamente cuando suena *"Through the Fire and Flames"*.

---

## Requisitos Previos

* **Java Development Kit (JDK)**: Versión 17 o superior.

Para verificar tu versión instalada:
```bash
java -version
javac -version
```

---

## Cómo Compilar y Ejecutar el Proyecto

Ubicado en la carpeta raíz del proyecto (`taberna-eventbus/`), ejecuta los siguientes comandos en tu terminal:

### 1. Compilar el código fuente
```bash
mkdir -p out
javac -d out $(find src -name "*.java")
```

### 2. Ejecutar la simulación
```bash
java -cp out Main
```

