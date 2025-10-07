package udistrital.avanzada.argolla.control;

import udistrital.avanzada.argolla.modelo.ConfiguracionJuego;
import udistrital.avanzada.argolla.control.PersistenciaManager;
import udistrital.avanzada.argolla.vista.VistaPrincipal;

/**
 * Clase principal que maneja la inicialización del juego.
 * Implementa el patrón de responsabilidad única al tener
 * únicamente la responsabilidad de configurar y lanzar la aplicación.
 * 
 * Esta clase instancia el Modelo, la Vista y el Controlador,
 * los enlaza mediante inyección de dependencias y hace visible
 * la aplicación, sin contener lógica de negocio.
 * 
 * @author Sansantax, And_Gar03
 * @version 3.0
 */
public class JuegoDeLaArgolla {

    /**
     * Inicializa la aplicación creando e interconectando todos los componentes.
     * Implementa la inyección de dependencias (DIP) al inyectar el controlador
     * en la vista a través de su constructor.
     */
    public void inicializar() {
        try {
            // 1. Crear el gestor de persistencia
            PersistenciaManager persistenciaManager = new PersistenciaManager();
            
            // 2. Crear el controlador principal (GameManager)
            IControladorJuego controlador = new GameManager();
            
            // 3. Cargar la configuración guardada o usar la predeterminada
            ConfiguracionJuego configuracion = persistenciaManager.cargarConfiguracion();
            if (configuracion == null) {
                configuracion = new ConfiguracionJuego();
            }
            
            // 4. Establecer la configuración en el controlador
            controlador.setConfiguracion(configuracion);
            
            // 5. Crear la vista principal e inyectar el controlador (DIP)
            VistaPrincipal vistaPrincipal = new VistaPrincipal(controlador);
            
            // 6. Hacer visible la aplicación
            vistaPrincipal.setVisible(true);
            
            // 7. Mostrar mensaje de bienvenida
            vistaPrincipal.mostrarMensaje("¡Bienvenido al Juego de la Argolla!\n\n" +
                                        "Para comenzar:\n" +
                                        "1. Configure el juego si lo desea\n" +
                                        "2. Agregue equipos y jugadores\n" +
                                        "3. Inicie una nueva partida");
            
        } catch (Exception e) {
            // En caso de error crítico, mostrar mensaje y terminar
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Error crítico al inicializar la aplicación: " + e.getMessage(),
                "Error de Inicialización", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
