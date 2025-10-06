package co.edu.udistrital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un equipo en el juego de la argolla.
 * Un equipo está compuesto por varios jugadores y mantiene
 * estadísticas agregadas del equipo.
 * 
 * @author Sistema Juego de la Argolla
 * @version 1.0
 */
public class Equipo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Nombre del equipo */
    private String nombre;
    
    /** Lista de jugadores del equipo */
    private List<Jugador> jugadores;
    
    /** Color del equipo (para identificación visual) */
    private String color;
    
    /**
     * Constructor por defecto del equipo.
     */
    public Equipo() {
        this.nombre = "";
        this.jugadores = new ArrayList<>();
        this.color = "";
    }
    
    /**
     * Constructor con parámetros del equipo.
     * 
     * @param nombre Nombre del equipo
     * @param color Color del equipo
     */
    public Equipo(String nombre, String color) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.color = color;
    }
    
    /**
     * Obtiene el nombre del equipo.
     * 
     * @return Nombre del equipo
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del equipo.
     * 
     * @param nombre Nombre del equipo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene la lista de jugadores del equipo.
     * 
     * @return Lista de jugadores
     */
    public List<Jugador> getJugadores() {
        return new ArrayList<>(jugadores);
    }
    
    /**
     * Establece la lista de jugadores del equipo.
     * 
     * @param jugadores Lista de jugadores
     */
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = new ArrayList<>(jugadores);
    }
    
    /**
     * Obtiene el color del equipo.
     * 
     * @return Color del equipo
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Establece el color del equipo.
     * 
     * @param color Color del equipo
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    /**
     * Agrega un jugador al equipo.
     * 
     * @param jugador Jugador a agregar
     * @return true si se agregó exitosamente, false si ya existe
     */
    public boolean agregarJugador(Jugador jugador) {
        if (jugador != null && !jugadores.contains(jugador)) {
            jugadores.add(jugador);
            return true;
        }
        return false;
    }
    
    /**
     * Remueve un jugador del equipo.
     * 
     * @param jugador Jugador a remover
     * @return true si se removió exitosamente, false si no existía
     */
    public boolean removerJugador(Jugador jugador) {
        return jugadores.remove(jugador);
    }
    
    /**
     * Obtiene el número de jugadores en el equipo.
     * 
     * @return Número de jugadores
     */
    public int getNumeroJugadores() {
        return jugadores.size();
    }
    
    /**
     * Calcula la puntuación total del equipo.
     * 
     * @return Puntuación total del equipo
     */
    public int getPuntuacionTotal() {
        return jugadores.stream()
                .mapToInt(Jugador::getPuntuacion)
                .sum();
    }
    
    /**
     * Calcula el número total de intentos del equipo.
     * 
     * @return Total de intentos del equipo
     */
    public int getTotalIntentos() {
        return jugadores.stream()
                .mapToInt(Jugador::getIntentos)
                .sum();
    }
    
    /**
     * Calcula el número total de aciertos del equipo.
     * 
     * @return Total de aciertos del equipo
     */
    public int getTotalAciertos() {
        return jugadores.stream()
                .mapToInt(Jugador::getAciertos)
                .sum();
    }
    
    /**
     * Calcula el porcentaje de aciertos del equipo.
     * 
     * @return Porcentaje de aciertos del equipo (0-100)
     */
    public double getPorcentajeAciertos() {
        int totalIntentos = getTotalIntentos();
        if (totalIntentos == 0) {
            return 0.0;
        }
        return (double) getTotalAciertos() / totalIntentos * 100;
    }
    
    /**
     * Obtiene el jugador con mayor puntuación del equipo.
     * 
     * @return Jugador con mayor puntuación, null si no hay jugadores
     */
    public Jugador getJugadorConMayorPuntuacion() {
        return jugadores.stream()
                .max((j1, j2) -> Integer.compare(j1.getPuntuacion(), j2.getPuntuacion()))
                .orElse(null);
    }
    
    /**
     * Verifica si el equipo tiene jugadores.
     * 
     * @return true si tiene jugadores, false en caso contrario
     */
    public boolean tieneJugadores() {
        return !jugadores.isEmpty();
    }
    
    /**
     * Verifica si el equipo está completo (tiene el número máximo de jugadores).
     * 
     * @param maxJugadores Número máximo de jugadores permitidos
     * @return true si está completo, false en caso contrario
     */
    public boolean estaCompleto(int maxJugadores) {
        return jugadores.size() >= maxJugadores;
    }
    
    /**
     * Reinicia las estadísticas de todos los jugadores del equipo.
     */
    public void reiniciarEstadisticas() {
        jugadores.forEach(Jugador::reiniciarEstadisticas);
    }
    
    /**
     * Busca un jugador por nombre en el equipo.
     * 
     * @param nombre Nombre del jugador a buscar
     * @return Jugador encontrado, null si no existe
     */
    public Jugador buscarJugadorPorNombre(String nombre) {
        return jugadores.stream()
                .filter(jugador -> jugador.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", numeroJugadores=" + jugadores.size() +
                ", puntuacionTotal=" + getPuntuacionTotal() +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Equipo equipo = (Equipo) obj;
        return nombre != null ? nombre.equals(equipo.nombre) : equipo.nombre == null;
    }
    
    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
}
