package co.edu.udistrital.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una partida del juego de la argolla.
 * Una partida contiene la información de los equipos participantes,
 * el estado del juego y las estadísticas de la partida.
 * 
 * @author Sansantax
 * @version 1.0
 */
public class Partida implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Identificador único de la partida */
    private String id;
    
    /** Lista de equipos participantes */
    private List<Equipo> equipos;
    
    /** Estado actual de la partida */
    private EstadoPartida estado;
    
    /** Fecha y hora de inicio de la partida */
    private LocalDateTime fechaInicio;
    
    /** Fecha y hora de fin de la partida */
    private LocalDateTime fechaFin;
    
    /** Número máximo de rondas de la partida */
    private int maxRondas;
    
    /** Ronda actual de la partida */
    private int rondaActual;
    
    /** Puntos necesarios para ganar */
    private int puntosParaGanar;
    
    /**
     * Enum que representa los posibles estados de una partida.
     */
    public enum EstadoPartida {
        PREPARACION,
        EN_CURSO,
        FINALIZADA,
        CANCELADA
    }
    
    /**
     * Constructor por defecto de la partida.
     */
    public Partida() {
        this.id = "";
        this.equipos = new ArrayList<>();
        this.estado = EstadoPartida.PREPARACION;
        this.fechaInicio = null;
        this.fechaFin = null;
        this.maxRondas = 10;
        this.rondaActual = 0;
        this.puntosParaGanar = 100;
    }
    
    /**
     * Constructor con parámetros de la partida.
     * 
     * @param id Identificador de la partida
     * @param maxRondas Número máximo de rondas
     * @param puntosParaGanar Puntos necesarios para ganar
     */
    public Partida(String id, int maxRondas, int puntosParaGanar) {
        this.id = id;
        this.equipos = new ArrayList<>();
        this.estado = EstadoPartida.PREPARACION;
        this.fechaInicio = null;
        this.fechaFin = null;
        this.maxRondas = maxRondas;
        this.rondaActual = 0;
        this.puntosParaGanar = puntosParaGanar;
    }
    
    /**
     * Obtiene el identificador de la partida.
     * 
     * @return ID de la partida
     */
    public String getId() {
        return id;
    }
    
    /**
     * Establece el identificador de la partida.
     * 
     * @param id ID de la partida
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Obtiene la lista de equipos participantes.
     * 
     * @return Lista de equipos
     */
    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos);
    }
    
    /**
     * Establece la lista de equipos participantes.
     * 
     * @param equipos Lista de equipos
     */
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = new ArrayList<>(equipos);
    }
    
    /**
     * Obtiene el estado actual de la partida.
     * 
     * @return Estado de la partida
     */
    public EstadoPartida getEstado() {
        return estado;
    }
    
    /**
     * Establece el estado de la partida.
     * 
     * @param estado Estado de la partida
     */
    public void setEstado(EstadoPartida estado) {
        this.estado = estado;
    }
    
    /**
     * Obtiene la fecha y hora de inicio de la partida.
     * 
     * @return Fecha de inicio
     */
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    
    /**
     * Establece la fecha y hora de inicio de la partida.
     * 
     * @param fechaInicio Fecha de inicio
     */
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    /**
     * Obtiene la fecha y hora de fin de la partida.
     * 
     * @return Fecha de fin
     */
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    
    /**
     * Establece la fecha y hora de fin de la partida.
     * 
     * @param fechaFin Fecha de fin
     */
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    /**
     * Obtiene el número máximo de rondas de la partida.
     * 
     * @return Número máximo de rondas
     */
    public int getMaxRondas() {
        return maxRondas;
    }
    
    /**
     * Establece el número máximo de rondas de la partida.
     * 
     * @param maxRondas Número máximo de rondas
     */
    public void setMaxRondas(int maxRondas) {
        this.maxRondas = maxRondas;
    }
    
    /**
     * Obtiene la ronda actual de la partida.
     * 
     * @return Ronda actual
     */
    public int getRondaActual() {
        return rondaActual;
    }
    
    /**
     * Establece la ronda actual de la partida.
     * 
     * @param rondaActual Ronda actual
     */
    public void setRondaActual(int rondaActual) {
        this.rondaActual = rondaActual;
    }
    
    /**
     * Obtiene los puntos necesarios para ganar.
     * 
     * @return Puntos para ganar
     */
    public int getPuntosParaGanar() {
        return puntosParaGanar;
    }
    
    /**
     * Establece los puntos necesarios para ganar.
     * 
     * @param puntosParaGanar Puntos para ganar
     */
    public void setPuntosParaGanar(int puntosParaGanar) {
        this.puntosParaGanar = puntosParaGanar;
    }
    
    /**
     * Agrega un equipo a la partida.
     * 
     * @param equipo Equipo a agregar
     * @return true si se agregó exitosamente, false si ya existe
     */
    public boolean agregarEquipo(Equipo equipo) {
        if (equipo != null && !equipos.contains(equipo)) {
            equipos.add(equipo);
            return true;
        }
        return false;
    }
    
    /**
     * Remueve un equipo de la partida.
     * 
     * @param equipo Equipo a remover
     * @return true si se removió exitosamente, false si no existía
     */
    public boolean removerEquipo(Equipo equipo) {
        return equipos.remove(equipo);
    }
    
    /**
     * Obtiene el número de equipos participantes.
     * 
     * @return Número de equipos
     */
    public int getNumeroEquipos() {
        return equipos.size();
    }
    
    /**
     * Inicia la partida.
     */
    public void iniciarPartida() {
        this.estado = EstadoPartida.EN_CURSO;
        this.fechaInicio = LocalDateTime.now();
        this.rondaActual = 1;
    }
    
    /**
     * Finaliza la partida.
     */
    public void finalizarPartida() {
        this.estado = EstadoPartida.FINALIZADA;
        this.fechaFin = LocalDateTime.now();
    }
    
    /**
     * Cancela la partida.
     */
    public void cancelarPartida() {
        this.estado = EstadoPartida.CANCELADA;
        this.fechaFin = LocalDateTime.now();
    }
    
    /**
     * Avanza a la siguiente ronda.
     */
    public void avanzarRonda() {
        if (rondaActual < maxRondas) {
            rondaActual++;
        }
    }
    
    /**
     * Obtiene el equipo ganador de la partida.
     * 
     * @return Equipo ganador, null si no hay ganador aún
     */
    public Equipo getEquipoGanador() {
        if (estado != EstadoPartida.FINALIZADA) {
            return null;
        }
        
        return equipos.stream()
                .max((e1, e2) -> Integer.compare(e1.getPuntuacionTotal(), e2.getPuntuacionTotal()))
                .orElse(null);
    }
    
    /**
     * Verifica si la partida ha terminado.
     * 
     * @return true si la partida ha terminado, false en caso contrario
     */
    public boolean haTerminado() {
        return estado == EstadoPartida.FINALIZADA || estado == EstadoPartida.CANCELADA;
    }
    
    /**
     * Verifica si la partida está en curso.
     * 
     * @return true si la partida está en curso, false en caso contrario
     */
    public boolean estaEnCurso() {
        return estado == EstadoPartida.EN_CURSO;
    }
    
    /**
     * Verifica si se puede iniciar la partida.
     * 
     * @return true si se puede iniciar, false en caso contrario
     */
    public boolean sePuedeIniciar() {
        return estado == EstadoPartida.PREPARACION && equipos.size() >= 2;
    }
    
    /**
     * Calcula la duración de la partida en minutos.
     * 
     * @return Duración en minutos, -1 si no ha terminado
     */
    public long getDuracionEnMinutos() {
        if (fechaInicio == null || fechaFin == null) {
            return -1;
        }
        return java.time.Duration.between(fechaInicio, fechaFin).toMinutes();
    }
    
    @Override
    public String toString() {
        return "Partida{" +
                "id='" + id + '\'' +
                ", estado=" + estado +
                ", numeroEquipos=" + equipos.size() +
                ", rondaActual=" + rondaActual +
                ", maxRondas=" + maxRondas +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Partida partida = (Partida) obj;
        return id != null ? id.equals(partida.id) : partida.id == null;
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
