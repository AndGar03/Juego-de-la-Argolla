package udistrital.avanzada.argolla.control;

import udistrital.avanzada.argolla.modelo.ConfiguracionJuego;
import udistrital.avanzada.argolla.control.PersistenciaManager;
import udistrital.avanzada.argolla.vista.VistaPrincipal;
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
 * @author Sansantax, And_Gar03
 * @version 3.0
 */
public class Launcher {

    /**
     * Método principal de la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Ejecutar la inicialización en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JuegoDeLaArgolla juego = new JuegoDeLaArgolla();
                juego.inicializar();
            }
        });
    }
}
