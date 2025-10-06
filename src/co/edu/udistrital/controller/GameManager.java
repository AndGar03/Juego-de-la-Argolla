package co.edu.udistrital.controller;

import co.edu.udistrital.model.*;
import co.edu.udistrital.persistence.PersistenciaManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controlador principal del juego que orquesta el flujo general.
 * Implementa el patrón de responsabilidad única (SRP) al delegar
 * responsabilidades específicas a gestores especializados.
 * 
 * @author Sistema Juego de la Argolla
 * @version 1.0
 */
public class GameManager implements IControladorJuego {
    
    /** Gestor de equipos */
    private GestorEquipos gestorEquipos;
    
    /** Gestor de jugadores */
    private GestorJugadores gestorJugadores;
    
    /** Gestor de partidas */
    private GestorPartidas gestorPartidas;
    
    /** Gestor de persistencia */
    private PersistenciaManager persistenciaManager;
    
    /** Partida actual */
    private Partida partidaActual;
    
    /** Configuración del juego */
    private ConfiguracionJuego configuracion;
    
    /**
     * Constructor del GameManager.
     * Inicializa todos los gestores especializados.
     */
    public GameManager() {
        this.gestorEquipos = new GestorEquipos();
        this.gestorJugadores = new GestorJugadores();
        this.gestorPartidas = new GestorPartidas();
        this.persistenciaManager = new PersistenciaManager();
        this.configuracion = new ConfiguracionJuego();
        this.partidaActual = null;
    }
    
    @Override
    public boolean iniciarNuevaPartida(ConfiguracionJuego configuracion) {
        if (configuracion == null || !configuracion.esValida()) {
            return false;
        }
        
        this.configuracion = configuracion;
        String idPartida = UUID.randomUUID().toString();
        
        this.partidaActual = new Partida(
            idPartida,
            configuracion.getMaxRondasPorPartida(),
            configuracion.getPuntosParaGanar()
        );
        
        return true;
    }
    
    @Override
    public boolean agregarEquipo(Equipo equipo) {
        if (partidaActual == null || equipo == null) {
            return false;
        }
        
        if (partidaActual.getNumeroEquipos() >= configuracion.getMaxEquiposPorPartida()) {
            return false;
        }
        
        return partidaActual.agregarEquipo(equipo);
    }
    
    @Override
    public boolean removerEquipo(Equipo equipo) {
        if (partidaActual == null || equipo == null) {
            return false;
        }
        
        return partidaActual.removerEquipo(equipo);
    }
    
    @Override
    public boolean agregarJugadorAEquipo(Equipo equipo, Jugador jugador) {
        if (equipo == null || jugador == null) {
            return false;
        }
        
        if (equipo.getNumeroJugadores() >= configuracion.getMaxJugadoresPorEquipo()) {
            return false;
        }
        
        return equipo.agregarJugador(jugador);
    }
    
    @Override
    public boolean removerJugadorDeEquipo(Equipo equipo, Jugador jugador) {
        if (equipo == null || jugador == null) {
            return false;
        }
        
        return equipo.removerJugador(jugador);
    }
    
    @Override
    public boolean iniciarPartida() {
        if (partidaActual == null || !partidaActual.sePuedeIniciar()) {
            return false;
        }
        
        partidaActual.iniciarPartida();
        return true;
    }
    
    @Override
    public void finalizarPartida() {
        if (partidaActual != null && partidaActual.estaEnCurso()) {
            partidaActual.finalizarPartida();
        }
    }
    
    @Override
    public void cancelarPartida() {
        if (partidaActual != null && partidaActual.estaEnCurso()) {
            partidaActual.cancelarPartida();
        }
    }
    
    @Override
    public int registrarIntento(Jugador jugador, boolean esAcierto) {
        if (partidaActual == null || !partidaActual.estaEnCurso() || jugador == null) {
            return 0;
        }
        
        jugador.incrementarIntentos();
        
        int puntosObtenidos = 0;
        if (esAcierto) {
            jugador.incrementarAciertos();
            puntosObtenidos = configuracion.getPuntosPorAcierto();
        } else {
            puntosObtenidos = configuracion.getPuntosPorIntento();
        }
        
        jugador.agregarPuntos(puntosObtenidos);
        
        // Verificar si algún equipo ha alcanzado la puntuación para ganar
        verificarCondicionVictoria();
        
        return puntosObtenidos;
    }
    
    @Override
    public boolean avanzarRonda() {
        if (partidaActual == null || !partidaActual.estaEnCurso()) {
            return false;
        }
        
        if (partidaActual.getRondaActual() < partidaActual.getMaxRondas()) {
            partidaActual.avanzarRonda();
            return true;
        }
        
        // Si se han completado todas las rondas, finalizar la partida
        partidaActual.finalizarPartida();
        return false;
    }
    
    @Override
    public Partida getPartidaActual() {
        return partidaActual;
    }
    
    @Override
    public List<Equipo> getEquipos() {
        if (partidaActual == null) {
            return new ArrayList<>();
        }
        return partidaActual.getEquipos();
    }
    
    @Override
    public Equipo getEquipoGanador() {
        if (partidaActual == null) {
            return null;
        }
        return partidaActual.getEquipoGanador();
    }
    
    @Override
    public ConfiguracionJuego getConfiguracion() {
        return configuracion;
    }
    
    @Override
    public void setConfiguracion(ConfiguracionJuego configuracion) {
        if (configuracion != null && configuracion.esValida()) {
            this.configuracion = configuracion;
        }
    }
    
    @Override
    public boolean estaPartidaEnCurso() {
        return partidaActual != null && partidaActual.estaEnCurso();
    }
    
    @Override
    public boolean haTerminadoPartida() {
        return partidaActual != null && partidaActual.haTerminado();
    }
    
    @Override
    public String obtenerEstadisticasPartida() {
        if (partidaActual == null) {
            return "No hay partida activa";
        }
        
        StringBuilder estadisticas = new StringBuilder();
        estadisticas.append("=== ESTADÍSTICAS DE LA PARTIDA ===\n");
        estadisticas.append("ID: ").append(partidaActual.getId()).append("\n");
        estadisticas.append("Estado: ").append(partidaActual.getEstado()).append("\n");
        estadisticas.append("Ronda: ").append(partidaActual.getRondaActual())
                   .append("/").append(partidaActual.getMaxRondas()).append("\n");
        
        if (partidaActual.getFechaInicio() != null) {
            estadisticas.append("Inicio: ").append(partidaActual.getFechaInicio()).append("\n");
        }
        
        if (partidaActual.getFechaFin() != null) {
            estadisticas.append("Fin: ").append(partidaActual.getFechaFin()).append("\n");
        }
        
        estadisticas.append("\n=== EQUIPOS ===\n");
        for (Equipo equipo : partidaActual.getEquipos()) {
            estadisticas.append(equipo.getNombre())
                       .append(" - Puntuación: ").append(equipo.getPuntuacionTotal())
                       .append(" - Aciertos: ").append(equipo.getTotalAciertos())
                       .append("/").append(equipo.getTotalIntentos())
                       .append(" (").append(String.format("%.1f", equipo.getPorcentajeAciertos()))
                       .append("%)\n");
        }
        
        return estadisticas.toString();
    }
    
    @Override
    public boolean guardarPartida() {
        if (partidaActual == null) {
            return false;
        }
        
        return persistenciaManager.guardarPartida(partidaActual);
    }
    
    @Override
    public boolean cargarPartida(String idPartida) {
        if (idPartida == null || idPartida.trim().isEmpty()) {
            return false;
        }
        
        Partida partidaCargada = persistenciaManager.cargarPartida(idPartida);
        if (partidaCargada != null) {
            this.partidaActual = partidaCargada;
            return true;
        }
        
        return false;
    }
    
    /**
     * Verifica si algún equipo ha alcanzado la condición de victoria.
     */
    private void verificarCondicionVictoria() {
        if (partidaActual == null || !partidaActual.estaEnCurso()) {
            return;
        }
        
        for (Equipo equipo : partidaActual.getEquipos()) {
            if (equipo.getPuntuacionTotal() >= configuracion.getPuntosParaGanar()) {
                partidaActual.finalizarPartida();
                break;
            }
        }
    }
    
    /**
     * Obtiene el gestor de equipos.
     * 
     * @return Gestor de equipos
     */
    public GestorEquipos getGestorEquipos() {
        return gestorEquipos;
    }
    
    /**
     * Obtiene el gestor de jugadores.
     * 
     * @return Gestor de jugadores
     */
    public GestorJugadores getGestorJugadores() {
        return gestorJugadores;
    }
    
    /**
     * Obtiene el gestor de partidas.
     * 
     * @return Gestor de partidas
     */
    public GestorPartidas getGestorPartidas() {
        return gestorPartidas;
    }
}
