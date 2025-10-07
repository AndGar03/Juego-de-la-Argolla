package udistrital.avanzada.argolla.modelo;

import java.io.Serializable;

/**
 * Representa la configuración del juego de la argolla.
 * Esta clase contiene todos los parámetros configurables
 * que afectan el comportamiento del juego.
 * 
 * @author Sansantax, And_Gar03
 * @version 3.0
 */
public class ConfiguracionJuego implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Número máximo de jugadores por equipo */
    private int maxJugadoresPorEquipo;
    
    /** Número máximo de equipos por partida */
    private int maxEquiposPorPartida;
    
    /** Número máximo de rondas por partida */
    private int maxRondasPorPartida;
    
    /** Puntos necesarios para ganar una partida */
    private int puntosParaGanar;
    
    /** Puntos otorgados por acierto */
    private int puntosPorAcierto;
    
    /** Puntos otorgados por intento (bonificación) */
    private int puntosPorIntento;
    
    /** Tiempo límite por ronda en segundos */
    private int tiempoLimitePorRonda;
    
    /** Distancia de la argolla al poste en metros */
    private double distanciaArgolla;
    
    /** Dificultad del juego (1-5) */
    private int dificultad;
    
    /** Sonido habilitado */
    private boolean sonidoHabilitado;
    
    /** Efectos visuales habilitados */
    private boolean efectosVisualesHabilitados;
    
    /**
     * Constructor por defecto con valores predeterminados.
     */
    public ConfiguracionJuego() {
        this.maxJugadoresPorEquipo = 4;
        this.maxEquiposPorPartida = 2;
        this.maxRondasPorPartida = 10;
        this.puntosParaGanar = 100;
        this.puntosPorAcierto = 10;
        this.puntosPorIntento = 1;
        this.tiempoLimitePorRonda = 60;
        this.distanciaArgolla = 2.5;
        this.dificultad = 3;
        this.sonidoHabilitado = true;
        this.efectosVisualesHabilitados = true;
    }
    
    /**
     * Constructor con parámetros personalizados.
     * 
     * @param maxJugadoresPorEquipo Número máximo de jugadores por equipo
     * @param maxEquiposPorPartida Número máximo de equipos por partida
     * @param maxRondasPorPartida Número máximo de rondas por partida
     * @param puntosParaGanar Puntos necesarios para ganar
     * @param puntosPorAcierto Puntos por acierto
     * @param tiempoLimitePorRonda Tiempo límite por ronda en segundos
     * @param distanciaArgolla Distancia de la argolla al poste
     * @param dificultad Dificultad del juego (1-5)
     */
    public ConfiguracionJuego(int maxJugadoresPorEquipo, int maxEquiposPorPartida, 
                             int maxRondasPorPartida, int puntosParaGanar, 
                             int puntosPorAcierto, int tiempoLimitePorRonda, 
                             double distanciaArgolla, int dificultad) {
        this.maxJugadoresPorEquipo = maxJugadoresPorEquipo;
        this.maxEquiposPorPartida = maxEquiposPorPartida;
        this.maxRondasPorPartida = maxRondasPorPartida;
        this.puntosParaGanar = puntosParaGanar;
        this.puntosPorAcierto = puntosPorAcierto;
        this.puntosPorIntento = 1;
        this.tiempoLimitePorRonda = tiempoLimitePorRonda;
        this.distanciaArgolla = distanciaArgolla;
        this.dificultad = dificultad;
        this.sonidoHabilitado = true;
        this.efectosVisualesHabilitados = true;
    }
    
    // Getters y Setters
    
    public int getMaxJugadoresPorEquipo() {
        return maxJugadoresPorEquipo;
    }
    
    public void setMaxJugadoresPorEquipo(int maxJugadoresPorEquipo) {
        this.maxJugadoresPorEquipo = maxJugadoresPorEquipo;
    }
    
    public int getMaxEquiposPorPartida() {
        return maxEquiposPorPartida;
    }
    
    public void setMaxEquiposPorPartida(int maxEquiposPorPartida) {
        this.maxEquiposPorPartida = maxEquiposPorPartida;
    }
    
    public int getMaxRondasPorPartida() {
        return maxRondasPorPartida;
    }
    
    public void setMaxRondasPorPartida(int maxRondasPorPartida) {
        this.maxRondasPorPartida = maxRondasPorPartida;
    }
    
    public int getPuntosParaGanar() {
        return puntosParaGanar;
    }
    
    public void setPuntosParaGanar(int puntosParaGanar) {
        this.puntosParaGanar = puntosParaGanar;
    }
    
    public int getPuntosPorAcierto() {
        return puntosPorAcierto;
    }
    
    public void setPuntosPorAcierto(int puntosPorAcierto) {
        this.puntosPorAcierto = puntosPorAcierto;
    }
    
    public int getPuntosPorIntento() {
        return puntosPorIntento;
    }
    
    public void setPuntosPorIntento(int puntosPorIntento) {
        this.puntosPorIntento = puntosPorIntento;
    }
    
    public int getTiempoLimitePorRonda() {
        return tiempoLimitePorRonda;
    }
    
    public void setTiempoLimitePorRonda(int tiempoLimitePorRonda) {
        this.tiempoLimitePorRonda = tiempoLimitePorRonda;
    }
    
    public double getDistanciaArgolla() {
        return distanciaArgolla;
    }
    
    public void setDistanciaArgolla(double distanciaArgolla) {
        this.distanciaArgolla = distanciaArgolla;
    }
    
    public int getDificultad() {
        return dificultad;
    }
    
    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
    
    public boolean isSonidoHabilitado() {
        return sonidoHabilitado;
    }
    
    public void setSonidoHabilitado(boolean sonidoHabilitado) {
        this.sonidoHabilitado = sonidoHabilitado;
    }
    
    public boolean isEfectosVisualesHabilitados() {
        return efectosVisualesHabilitados;
    }
    
    public void setEfectosVisualesHabilitados(boolean efectosVisualesHabilitados) {
        this.efectosVisualesHabilitados = efectosVisualesHabilitados;
    }
    
    /**
     * Valida si la configuración es válida.
     * 
     * @return true si la configuración es válida, false en caso contrario
     */
    public boolean esValida() {
        return maxJugadoresPorEquipo > 0 &&
               maxEquiposPorPartida > 0 &&
               maxRondasPorPartida > 0 &&
               puntosParaGanar > 0 &&
               puntosPorAcierto > 0 &&
               puntosPorIntento >= 0 &&
               tiempoLimitePorRonda > 0 &&
               distanciaArgolla > 0 &&
               dificultad >= 1 && dificultad <= 5;
    }
    
    /**
     * Aplica la configuración de dificultad.
     * 
     * @param nuevaDificultad Nueva dificultad (1-5)
     */
    public void aplicarDificultad(int nuevaDificultad) {
        if (nuevaDificultad >= 1 && nuevaDificultad <= 5) {
            this.dificultad = nuevaDificultad;
            
            // Ajustar parámetros según la dificultad
            switch (nuevaDificultad) {
                case 1: // Muy fácil
                    this.distanciaArgolla = 1.5;
                    this.tiempoLimitePorRonda = 90;
                    this.puntosPorAcierto = 15;
                    break;
                case 2: // Fácil
                    this.distanciaArgolla = 2.0;
                    this.tiempoLimitePorRonda = 75;
                    this.puntosPorAcierto = 12;
                    break;
                case 3: // Normal
                    this.distanciaArgolla = 2.5;
                    this.tiempoLimitePorRonda = 60;
                    this.puntosPorAcierto = 10;
                    break;
                case 4: // Difícil
                    this.distanciaArgolla = 3.0;
                    this.tiempoLimitePorRonda = 45;
                    this.puntosPorAcierto = 8;
                    break;
                case 5: // Muy difícil
                    this.distanciaArgolla = 3.5;
                    this.tiempoLimitePorRonda = 30;
                    this.puntosPorAcierto = 6;
                    break;
            }
        }
    }
    
    /**
     * Obtiene el multiplicador de puntos según la dificultad.
     * 
     * @return Multiplicador de puntos
     */
    public double getMultiplicadorPuntos() {
        return 1.0 + (dificultad - 1) * 0.2;
    }
    
    /**
     * Reinicia la configuración a los valores predeterminados.
     */
    public void reiniciarAPredeterminados() {
        this.maxJugadoresPorEquipo = 4;
        this.maxEquiposPorPartida = 2;
        this.maxRondasPorPartida = 10;
        this.puntosParaGanar = 100;
        this.puntosPorAcierto = 10;
        this.puntosPorIntento = 1;
        this.tiempoLimitePorRonda = 60;
        this.distanciaArgolla = 2.5;
        this.dificultad = 3;
        this.sonidoHabilitado = true;
        this.efectosVisualesHabilitados = true;
    }
    
    @Override
    public String toString() {
        return "ConfiguracionJuego{" +
                "maxJugadoresPorEquipo=" + maxJugadoresPorEquipo +
                ", maxEquiposPorPartida=" + maxEquiposPorPartida +
                ", maxRondasPorPartida=" + maxRondasPorPartida +
                ", puntosParaGanar=" + puntosParaGanar +
                ", puntosPorAcierto=" + puntosPorAcierto +
                ", tiempoLimitePorRonda=" + tiempoLimitePorRonda +
                ", distanciaArgolla=" + distanciaArgolla +
                ", dificultad=" + dificultad +
                '}';
    }
}
