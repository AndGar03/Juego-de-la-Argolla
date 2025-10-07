package udistrital.avanzada.argolla.modelo;

import java.io.Serializable;

/**
 * Representa un jugador en el juego de la argolla.
 * Esta clase contiene únicamente la lógica de negocio del jugador
 * sin dependencias de la interfaz gráfica.
 * 
 * @author Sansantax, And_Gar03
 * @version 3.0
 */
public class Jugador implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Nombre del jugador */
    private String nombre;
    
    /** Puntuación actual del jugador */
    private int puntuacion;
    
    /** Número de intentos realizados */
    private int intentos;
    
    /** Número de aciertos (argollas exitosas) */
    private int aciertos;
    
    /**
     * Constructor por defecto del jugador.
     */
    public Jugador() {
        this.nombre = "";
        this.puntuacion = 0;
        this.intentos = 0;
        this.aciertos = 0;
    }
    
    /**
     * Constructor con parámetros del jugador.
     * 
     * @param nombre Nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntuacion = 0;
        this.intentos = 0;
        this.aciertos = 0;
    }
    
    /**
     * Obtiene el nombre del jugador.
     * 
     * @return Nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del jugador.
     * 
     * @param nombre Nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene la puntuación actual del jugador.
     * 
     * @return Puntuación del jugador
     */
    public int getPuntuacion() {
        return puntuacion;
    }
    
    /**
     * Establece la puntuación del jugador.
     * 
     * @param puntuacion Nueva puntuación
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    /**
     * Obtiene el número de intentos realizados.
     * 
     * @return Número de intentos
     */
    public int getIntentos() {
        return intentos;
    }
    
    /**
     * Establece el número de intentos.
     * 
     * @param intentos Número de intentos
     */
    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
    
    /**
     * Obtiene el número de aciertos del jugador.
     * 
     * @return Número de aciertos
     */
    public int getAciertos() {
        return aciertos;
    }
    
    /**
     * Establece el número de aciertos.
     * 
     * @param aciertos Número de aciertos
     */
    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }
    
    /**
     * Incrementa el número de intentos del jugador.
     */
    public void incrementarIntentos() {
        this.intentos++;
    }
    
    /**
     * Incrementa el número de aciertos del jugador.
     */
    public void incrementarAciertos() {
        this.aciertos++;
    }
    
    /**
     * Calcula el porcentaje de aciertos del jugador.
     * 
     * @return Porcentaje de aciertos (0-100)
     */
    public double calcularPorcentajeAciertos() {
        if (intentos == 0) {
            return 0.0;
        }
        return (double) aciertos / intentos * 100;
    }
    
    /**
     * Agrega puntos a la puntuación del jugador.
     * 
     * @param puntos Puntos a agregar
     */
    public void agregarPuntos(int puntos) {
        this.puntuacion += puntos;
    }
    
    /**
     * Reinicia las estadísticas del jugador.
     */
    public void reiniciarEstadisticas() {
        this.puntuacion = 0;
        this.intentos = 0;
        this.aciertos = 0;
    }
    
    /**
     * Verifica si el jugador tiene un nombre válido.
     * 
     * @return true si el nombre es válido, false en caso contrario
     */
    public boolean tieneNombreValido() {
        return nombre != null && !nombre.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", puntuacion=" + puntuacion +
                ", intentos=" + intentos +
                ", aciertos=" + aciertos +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Jugador jugador = (Jugador) obj;
        return nombre != null ? nombre.equals(jugador.nombre) : jugador.nombre == null;
    }
    
    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
}
