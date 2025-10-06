package co.edu.udistrital.controller;

import co.edu.udistrital.model.Equipo;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestor especializado para la administración de equipos.
 * Implementa el principio de responsabilidad única (SRP) al manejar
 * únicamente las operaciones relacionadas con equipos.
 * 
 * @author Sansantax
 * @version 1.0
 */
public class GestorEquipos {
    
    /** Lista de equipos registrados */
    private List<Equipo> equipos;
    
    /**
     * Constructor del gestor de equipos.
     */
    public GestorEquipos() {
        this.equipos = new ArrayList<>();
    }
    
    /**
     * Crea un nuevo equipo con el nombre y color especificados.
     * 
     * @param nombre Nombre del equipo
     * @param color Color del equipo
     * @return Equipo creado, null si no se pudo crear
     */
    public Equipo crearEquipo(String nombre, String color) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        
        if (color == null || color.trim().isEmpty()) {
            color = "Azul"; // Color por defecto
        }
        
        Equipo nuevoEquipo = new Equipo(nombre.trim(), color.trim());
        
        if (agregarEquipo(nuevoEquipo)) {
            return nuevoEquipo;
        }
        
        return null;
    }
    
    /**
     * Agrega un equipo a la lista de equipos.
     * 
     * @param equipo Equipo a agregar
     * @return true si se agregó exitosamente, false en caso contrario
     */
    public boolean agregarEquipo(Equipo equipo) {
        if (equipo == null || equipos.contains(equipo)) {
            return false;
        }
        
        equipos.add(equipo);
        return true;
    }
    
    /**
     * Remueve un equipo de la lista de equipos.
     * 
     * @param equipo Equipo a remover
     * @return true si se removió exitosamente, false en caso contrario
     */
    public boolean removerEquipo(Equipo equipo) {
        return equipos.remove(equipo);
    }
    
    /**
     * Obtiene un equipo por su nombre.
     * 
     * @param nombre Nombre del equipo a buscar
     * @return Equipo encontrado, null si no existe
     */
    public Equipo buscarEquipoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        
        return equipos.stream()
                .filter(equipo -> equipo.getNombre().equals(nombre.trim()))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene un equipo por su color.
     * 
     * @param color Color del equipo a buscar
     * @return Equipo encontrado, null si no existe
     */
    public Equipo buscarEquipoPorColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            return null;
        }
        
        return equipos.stream()
                .filter(equipo -> equipo.getColor().equals(color.trim()))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene todos los equipos registrados.
     * 
     * @return Lista de equipos
     */
    public List<Equipo> obtenerTodosLosEquipos() {
        return new ArrayList<>(equipos);
    }
    
    /**
     * Obtiene el número de equipos registrados.
     * 
     * @return Número de equipos
     */
    public int getNumeroEquipos() {
        return equipos.size();
    }
    
    /**
     * Verifica si existe un equipo con el nombre especificado.
     * 
     * @param nombre Nombre del equipo
     * @return true si existe, false en caso contrario
     */
    public boolean existeEquipo(String nombre) {
        return buscarEquipoPorNombre(nombre) != null;
    }
    
    /**
     * Verifica si existe un equipo con el color especificado.
     * 
     * @param color Color del equipo
     * @return true si existe, false en caso contrario
     */
    public boolean existeColor(String color) {
        return buscarEquipoPorColor(color) != null;
    }
    
    /**
     * Obtiene los equipos ordenados por puntuación total (descendente).
     * 
     * @return Lista de equipos ordenados por puntuación
     */
    public List<Equipo> obtenerEquiposOrdenadosPorPuntuacion() {
        List<Equipo> equiposOrdenados = new ArrayList<>(equipos);
        equiposOrdenados.sort((e1, e2) -> Integer.compare(e2.getPuntuacionTotal(), e1.getPuntuacionTotal()));
        return equiposOrdenados;
    }
    
    /**
     * Obtiene los equipos ordenados por porcentaje de aciertos (descendente).
     * 
     * @return Lista de equipos ordenados por porcentaje de aciertos
     */
    public List<Equipo> obtenerEquiposOrdenadosPorAciertos() {
        List<Equipo> equiposOrdenados = new ArrayList<>(equipos);
        equiposOrdenados.sort((e1, e2) -> Double.compare(e2.getPorcentajeAciertos(), e1.getPorcentajeAciertos()));
        return equiposOrdenados;
    }
    
    /**
     * Obtiene el equipo con mayor puntuación.
     * 
     * @return Equipo con mayor puntuación, null si no hay equipos
     */
    public Equipo obtenerEquipoConMayorPuntuacion() {
        return equipos.stream()
                .max((e1, e2) -> Integer.compare(e1.getPuntuacionTotal(), e2.getPuntuacionTotal()))
                .orElse(null);
    }
    
    /**
     * Obtiene el equipo con mejor porcentaje de aciertos.
     * 
     * @return Equipo con mejor porcentaje de aciertos, null si no hay equipos
     */
    public Equipo obtenerEquipoConMejorAciertos() {
        return equipos.stream()
                .max((e1, e2) -> Double.compare(e1.getPorcentajeAciertos(), e2.getPorcentajeAciertos()))
                .orElse(null);
    }
    
    /**
     * Reinicia las estadísticas de todos los equipos.
     */
    public void reiniciarEstadisticas() {
        equipos.forEach(Equipo::reiniciarEstadisticas);
    }
    
    /**
     * Limpia la lista de equipos.
     */
    public void limpiarEquipos() {
        equipos.clear();
    }
    
    /**
     * Verifica si la lista de equipos está vacía.
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return equipos.isEmpty();
    }
    
    /**
     * Obtiene los nombres de todos los equipos.
     * 
     * @return Lista de nombres de equipos
     */
    public List<String> obtenerNombresEquipos() {
        List<String> nombres = new ArrayList<>();
        for (Equipo equipo : equipos) {
            nombres.add(equipo.getNombre());
        }
        return nombres;
    }
    
    /**
     * Obtiene los colores de todos los equipos.
     * 
     * @return Lista de colores de equipos
     */
    public List<String> obtenerColoresEquipos() {
        List<String> colores = new ArrayList<>();
        for (Equipo equipo : equipos) {
            colores.add(equipo.getColor());
        }
        return colores;
    }
}
