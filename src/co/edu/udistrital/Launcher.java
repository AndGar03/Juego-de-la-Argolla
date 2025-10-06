package co.edu.udistrital;

import co.edu.udistrital.controller.GameManager;
import co.edu.udistrital.controller.IControladorJuego;
import co.edu.udistrital.view.VistaPrincipal;
import co.edu.udistrital.model.ConfiguracionJuego;
import co.edu.udistrital.persistence.PersistenciaManager;

import javax.swing.SwingUtilities;

/**
 * Clase principal (Launcher) del Juego de la Argolla.
 * Implementa el principio de responsabilidad única (SRP) al tener
 * únicamente la responsabilidad de inicializar la aplicación.
 * 
 * Esta clase instancia el Modelo, la Vista y el Controlador,
 * los enlaza mediante inyección de dependencias y hace visible
 * la aplicación, sin contener lógica de negocio.
 * 
 * @author Sistema Juego de la Argolla
 * @version 1.0
 */
public class Launcher {
    
    /**
     * Método principal de la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Configurar el look and feel del sistema
        configurarLookAndFeel();
        
        // Ejecutar la inicialización en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inicializarAplicacion();
            }
        });
    }
    
    /**
     * Configura el look and feel del sistema para la aplicación.
     */
    private static void configurarLookAndFeel() {
        /*    // Intentar usar el look and feel del sistema
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());*/
    }
    
    /**
     * Inicializa la aplicación creando e interconectando todos los componentes.
     * Implementa la inyección de dependencias (DIP) al inyectar el controlador
     * en la vista a través de su constructor.
     */
    private static void inicializarAplicacion() {
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
