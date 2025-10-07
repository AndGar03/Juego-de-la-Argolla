package udistrital.avanzada.argolla.control;

import udistrital.avanzada.argolla.modelo.Equipo;
import udistrital.avanzada.argolla.modelo.Jugador;
import udistrital.avanzada.argolla.modelo.Partida;
import udistrital.avanzada.argolla.modelo.ConfiguracionJuego;
import java.util.List;
import java.util.List;

/**
 * Interfaz que define el contrato para el controlador del juego.
 * Esta interfaz permite la inversión de dependencias (DIP) al desacoplar
 * la vista del controlador concreto.
 * 
 * @author Sansantax, And_Gar03
 * @version 3.0
 */
public interface IControladorJuego {
    
    /**
     * Inicia una nueva partida con la configuración especificada.
     * 
     * @param configuracion Configuración del juego
     * @return true si se inició exitosamente, false en caso contrario
     */
    boolean iniciarNuevaPartida(ConfiguracionJuego configuracion);
    
    /**
     * Agrega un equipo a la partida actual.
     * 
     * @param equipo Equipo a agregar
     * @return true si se agregó exitosamente, false en caso contrario
     */
    boolean agregarEquipo(Equipo equipo);
    
    /**
     * Remueve un equipo de la partida actual.
     * 
     * @param equipo Equipo a remover
     * @return true si se removió exitosamente, false en caso contrario
     */
    boolean removerEquipo(Equipo equipo);
    
    /**
     * Agrega un jugador a un equipo específico.
     * 
     * @param equipo Equipo al que agregar el jugador
     * @param jugador Jugador a agregar
     * @return true si se agregó exitosamente, false en caso contrario
     */
    boolean agregarJugadorAEquipo(Equipo equipo, Jugador jugador);
    
    /**
     * Remueve un jugador de un equipo específico.
     * 
     * @param equipo Equipo del que remover el jugador
     * @param jugador Jugador a remover
     * @return true si se removió exitosamente, false en caso contrario
     */
    boolean removerJugadorDeEquipo(Equipo equipo, Jugador jugador);
    
    /**
     * Inicia la partida actual.
     * 
     * @return true si se inició exitosamente, false en caso contrario
     */
    boolean iniciarPartida();
    
    /**
     * Finaliza la partida actual.
     */
    void finalizarPartida();
    
    /**
     * Cancela la partida actual.
     */
    void cancelarPartida();
    
    /**
     * Registra un intento de lanzamiento de argolla.
     * 
     * @param jugador Jugador que realiza el intento
     * @param esAcierto true si fue acierto, false en caso contrario
     * @return Puntos obtenidos por el intento
     */
    int registrarIntento(Jugador jugador, boolean esAcierto);
    
    /**
     * Avanza a la siguiente ronda.
     * 
     * @return true si se avanzó exitosamente, false si no se puede avanzar
     */
    boolean avanzarRonda();
    
    /**
     * Obtiene la partida actual.
     * 
     * @return Partida actual, null si no hay partida
     */
    Partida getPartidaActual();
    
    /**
     * Obtiene la lista de equipos de la partida actual.
     * 
     * @return Lista de equipos
     */
    List<Equipo> getEquipos();
    
    /**
     * Obtiene el equipo ganador de la partida actual.
     * 
     * @return Equipo ganador, null si no hay ganador
     */
    Equipo getEquipoGanador();
    
    /**
     * Obtiene la configuración actual del juego.
     * 
     * @return Configuración del juego
     */
    ConfiguracionJuego getConfiguracion();
    
    /**
     * Establece la configuración del juego.
     * 
     * @param configuracion Nueva configuración
     */
    void setConfiguracion(ConfiguracionJuego configuracion);
    
    /**
     * Verifica si la partida está en curso.
     * 
     * @return true si está en curso, false en caso contrario
     */
    boolean estaPartidaEnCurso();
    
    /**
     * Verifica si la partida ha terminado.
     * 
     * @return true si ha terminado, false en caso contrario
     */
    boolean haTerminadoPartida();
    
    /**
     * Obtiene las estadísticas de la partida actual.
     * 
     * @return String con las estadísticas
     */
    String obtenerEstadisticasPartida();
    
    /**
     * Guarda la partida actual.
     * 
     * @return true si se guardó exitosamente, false en caso contrario
     */
    boolean guardarPartida();
    
    /**
     * Carga una partida guardada.
     * 
     * @param idPartida ID de la partida a cargar
     * @return true si se cargó exitosamente, false en caso contrario
     */
    boolean cargarPartida(String idPartida);

    /**
     * Lista los IDs de partidas guardadas disponibles.
     * 
     * @return Lista de IDs de partidas guardadas
     */
    List<String> listarPartidasGuardadas();
    
    /**
     * Crea un nuevo equipo.
     * 
     * @param nombre Nombre del equipo
     * @param color Color del equipo
     * @return Equipo creado, null si hay error
     */
    Equipo crearEquipo(String nombre, String color);
    
    /**
     * Crea un nuevo jugador.
     * 
     * @param nombre Nombre del jugador
     * @return Jugador creado, null si hay error
     */
    Jugador crearJugador(String nombre);
}
