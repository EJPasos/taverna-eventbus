

import bus.EventBus;
import componentes.Banda;
import componentes.Barra;
import componentes.Cocina;
import componentes.PanelLED;
import componentes.SistemaDeHumo;
import componentes.SistemaDePedidos;
import componentes.SistemaDeSonido;
import eventos.BandaTocandoEvent;
import eventos.BebidaServidaEvent;
import eventos.ComidaPreparadaEvent;
import eventos.PedidoRealizadoEvent;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================================================================");
        System.out.println("   BIENVENIDOS A LA TABERNA UNIVERSITARIA 'EL GÓLEM ALQUÍMICO'   ");
        System.out.println("==================================================================\n");

        // 1. Inicializar el EventBus
        EventBus eventBus = new EventBus();

        // 2. Instanciar Publicadores y Suscriptores
        SistemaDePedidos sistemaDePedidos = new SistemaDePedidos(eventBus);
        Barra barra = new Barra(eventBus);
        Cocina cocina = new Cocina(eventBus);
        Banda banda = new Banda("Los Seguidores de Dijkstra", eventBus);
        SistemaDeSonido sistemaDeSonido = new SistemaDeSonido();
        PanelLED panelLED = new PanelLED();

        // 3. Registrar suscripciones iniciales en el EventBus
        eventBus.suscribir(PedidoRealizadoEvent.class, barra);
        eventBus.suscribir(PedidoRealizadoEvent.class, cocina);
        eventBus.suscribir(BandaTocandoEvent.class, sistemaDeSonido);

        // El PanelLED se suscribe a todos los eventos de la taberna
        eventBus.suscribir(PedidoRealizadoEvent.class, panelLED);
        eventBus.suscribir(BebidaServidaEvent.class, panelLED);
        eventBus.suscribir(ComidaPreparadaEvent.class, panelLED);
        eventBus.suscribir(BandaTocandoEvent.class, panelLED);

        // 4. Flujo Principal: Pedido en la Mesa 5 (1 Cerveza, 1 Hamburguesa)
        System.out.println("--- [PASO 1] Cliente realiza un pedido en la Mesa 5 ---");
        sistemaDePedidos.realizarPedido("mesa_5", "pedido_123", List.of("cerveza", "hamburguesa"));

        // En paralelo (mientras la Barra tarda 2s y la Cocina 5s), la Banda comienza a tocar
        Thread.sleep(1000);
        System.out.println("\n--- [PASO 2] En paralelo, la Banda comienza su espectáculo ---");
        banda.tocarCancion("El Algoritmo del Amor", 180);

        // Esperamos a que tanto la Barra (2s) como la Cocina (5s) terminen sus procesos asíncronos
        Thread.sleep(5000);

        // 5. Demostración de Extensibilidad: Agregar SistemaDeHumo en caliente
        // sin modificar el código de Banda ni de EventBus
        System.out.println("\n==================================================================");
        System.out.println("   DEMOSTRACIÓN DE EXTENSIBILIDAD: CONECTANDO SISTEMA DE HUMO    ");
        System.out.println("==================================================================");

        SistemaDeHumo sistemaDeHumo = new SistemaDeHumo();
        eventBus.suscribir(BandaTocandoEvent.class, sistemaDeHumo);

        System.out.println("\n-> La banda toca una canción normal (el humo NO debe activarse):");
        banda.tocarCancion("Balada del Puntero Nulo", 210);

        Thread.sleep(1000);

        System.out.println("\n-> La banda toca 'Through the Fire and Flames' (el humo SÍ se activa):");
        banda.tocarCancion("Through the Fire and Flames", 442);

        // 6. Demostración de desuscripción
        System.out.println("\n--- [OPCIONAL] Desuscribiendo el Sistema de Humo por mantenimiento ---");
        eventBus.desuscribir(BandaTocandoEvent.class, sistemaDeHumo);
        banda.tocarCancion("Through the Fire and Flames", 442);

        System.out.println("\n==================================================================");
        System.out.println("                SIMULACIÓN FINALIZADA CON ÉXITO                   ");
        System.out.println("==================================================================");
    }
}