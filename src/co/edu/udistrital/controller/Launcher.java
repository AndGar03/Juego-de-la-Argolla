package co.edu.udistrital.controller;

import co.edu.udistrital.model.ConfiguracionJuego;
import co.edu.udistrital.persistence.PersistenciaManager;
import co.edu.udistrital.view.VistaPrincipal;
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
 * @author And_Gar03
 * @version 2.0
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
