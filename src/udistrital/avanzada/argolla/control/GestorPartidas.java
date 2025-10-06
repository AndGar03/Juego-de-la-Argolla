package udistrital.avanzada.argolla.control;

import udistrital.avanzada.argolla.modelo.Partida;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestor especializado para la administración de partidas.
 * Implementa el principio de responsabilidad única (SRP) al manejar
 * únicamente las operaciones relacionadas con partidas.
 * 
 * @author Sansantax
 * @version 3.0
 */
public class GestorPartidas {
    
    /** Lista de partidas registradas */
    private List<Partida> partidas;
    
    /**
     * Constructor del gestor de partidas.
     */
    public GestorPartidas() {
        this.partidas = new ArrayList<>();
    }
    
    /**
     * Agrega una partida a la lista de partidas.
     * 
     * @param partida Partida a agregar
     * @return true si se agregó exitosamente, false en caso contrario
     */
    public boolean agregarPartida(Partida partida) {
        if (partida == null || partidas.contains(partida)) {
            return false;
        }
        
        partidas.add(partida);
        return true;
    }
    
    /**
     * Remueve una partida de la lista de partidas.
     * 
     * @param partida Partida a remover
     * @return true si se removió exitosamente, false en caso contrario
     */
    public boolean removerPartida(Partida partida) {
        return partidas.remove(partida);
    }
    
    /**
     * Obtiene una partida por su ID.
     * 
     * @param id ID de la partida a buscar
     * @return Partida encontrada, null si no existe
     */
    public Partida buscarPartidaPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        
        return partidas.stream()
                .filter(partida -> partida.getId().equals(id.trim()))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene todas las partidas registradas.
     * 
     * @return Lista de partidas
     */
    public List<Partida> obtenerTodasLasPartidas() {
        return new ArrayList<>(partidas);
    }
    
    /**
     * Obtiene las partidas que están en curso.
     * 
     * @return Lista de partidas en curso
     */
    public List<Partida> obtenerPartidasEnCurso() {
        List<Partida> partidasEnCurso = new ArrayList<>();
        for (Partida partida : partidas) {
            if (partida.estaEnCurso()) {
                partidasEnCurso.add(partida);
            }
        }
        return partidasEnCurso;
    }
    
    /**
     * Obtiene las partidas que han terminado.
     * 
     * @return Lista de partidas terminadas
     */
    public List<Partida> obtenerPartidasTerminadas() {
        List<Partida> partidasTerminadas = new ArrayList<>();
        for (Partida partida : partidas) {
            if (partida.haTerminado()) {
                partidasTerminadas.add(partida);
            }
        }
        return partidasTerminadas;
    }
    
    /**
     * Obtiene las partidas en preparación.
     * 
     * @return Lista de partidas en preparación
     */
    public List<Partida> obtenerPartidasEnPreparacion() {
        List<Partida> partidasEnPreparacion = new ArrayList<>();
        for (Partida partida : partidas) {
            if (partida.getEstado() == Partida.EstadoPartida.PREPARACION) {
                partidasEnPreparacion.add(partida);
            }
        }
        return partidasEnPreparacion;
    }
    
    /**
     * Obtiene el número de partidas registradas.
     * 
     * @return Número de partidas
     */
    public int getNumeroPartidas() {
        return partidas.size();
    }
    
    /**
     * Obtiene el número de partidas en curso.
     * 
     * @return Número de partidas en curso
     */
    public int getNumeroPartidasEnCurso() {
        return obtenerPartidasEnCurso().size();
    }
    
    /**
     * Obtiene el número de partidas terminadas.
     * 
     * @return Número de partidas terminadas
     */
    public int getNumeroPartidasTerminadas() {
        return obtenerPartidasTerminadas().size();
    }
    
    /**
     * Verifica si existe una partida con el ID especificado.
     * 
     * @param id ID de la partida
     * @return true si existe, false en caso contrario
     */
    public boolean existePartida(String id) {
        return buscarPartidaPorId(id) != null;
    }
    
    /**
     * Obtiene las partidas ordenadas por fecha de inicio (más recientes primero).
     * 
     * @return Lista de partidas ordenadas por fecha
     */
    public List<Partida> obtenerPartidasOrdenadasPorFecha() {
        List<Partida> partidasOrdenadas = new ArrayList<>(partidas);
        partidasOrdenadas.sort((p1, p2) -> {
            if (p1.getFechaInicio() == null && p2.getFechaInicio() == null) {
                return 0;
            }
            if (p1.getFechaInicio() == null) {
                return 1;
            }
            if (p2.getFechaInicio() == null) {
                return -1;
            }
            return p2.getFechaInicio().compareTo(p1.getFechaInicio());
        });
        return partidasOrdenadas;
    }
    
    /**
     * Obtiene las partidas ordenadas por duración (más largas primero).
     * 
     * @return Lista de partidas ordenadas por duración
     */
    public List<Partida> obtenerPartidasOrdenadasPorDuracion() {
        List<Partida> partidasOrdenadas = new ArrayList<>(partidas);
        partidasOrdenadas.sort((p1, p2) -> {
            long duracion1 = p1.getDuracionEnMinutos();
            long duracion2 = p2.getDuracionEnMinutos();
            return Long.compare(duracion2, duracion1);
        });
        return partidasOrdenadas;
    }
    
    /**
     * Obtiene la partida más reciente.
     * 
     * @return Partida más reciente, null si no hay partidas
     */
    public Partida obtenerPartidaMasReciente() {
        return partidas.stream()
                .filter(p -> p.getFechaInicio() != null)
                .max((p1, p2) -> p1.getFechaInicio().compareTo(p2.getFechaInicio()))
                .orElse(null);
    }
    
    /**
     * Obtiene la partida más larga.
     * 
     * @return Partida más larga, null si no hay partidas
     */
    public Partida obtenerPartidaMasLarga() {
        return partidas.stream()
                .filter(p -> p.getDuracionEnMinutos() > 0)
                .max((p1, p2) -> Long.compare(p1.getDuracionEnMinutos(), p2.getDuracionEnMinutos()))
                .orElse(null);
    }
    
    /**
     * Obtiene las partidas con una duración mayor a un valor específico.
     * 
     * @param duracionMinima Duración mínima en minutos
     * @return Lista de partidas con duración mayor a la especificada
     */
    public List<Partida> obtenerPartidasConDuracionMayorA(long duracionMinima) {
        List<Partida> partidasFiltradas = new ArrayList<>();
        for (Partida partida : partidas) {
            if (partida.getDuracionEnMinutos() > duracionMinima) {
                partidasFiltradas.add(partida);
            }
        }
        return partidasFiltradas;
    }
    
    /**
     * Calcula la duración promedio de todas las partidas terminadas.
     * 
     * @return Duración promedio en minutos, -1 si no hay partidas terminadas
     */
    public double calcularDuracionPromedio() {
        List<Partida> partidasTerminadas = obtenerPartidasTerminadas();
        if (partidasTerminadas.isEmpty()) {
            return -1;
        }
        
        long duracionTotal = partidasTerminadas.stream()
                .mapToLong(Partida::getDuracionEnMinutos)
                .sum();
        
        return (double) duracionTotal / partidasTerminadas.size();
    }
    
    /**
     * Obtiene las estadísticas generales de todas las partidas.
     * 
     * @return String con las estadísticas generales
     */
    public String obtenerEstadisticasGenerales() {
        if (partidas.isEmpty()) {
            return "No hay partidas registradas";
        }
        
        StringBuilder estadisticas = new StringBuilder();
        estadisticas.append("=== ESTADÍSTICAS DE PARTIDAS ===\n");
        estadisticas.append("Total de partidas: ").append(getNumeroPartidas()).append("\n");
        estadisticas.append("Partidas en curso: ").append(getNumeroPartidasEnCurso()).append("\n");
        estadisticas.append("Partidas terminadas: ").append(getNumeroPartidasTerminadas()).append("\n");
        
        double duracionPromedio = calcularDuracionPromedio();
        if (duracionPromedio > 0) {
            estadisticas.append("Duración promedio: ")
                       .append(String.format("%.1f", duracionPromedio))
                       .append(" minutos\n");
        }
        
        return estadisticas.toString();
    }
    
    /**
     * Limpia la lista de partidas.
     */
    public void limpiarPartidas() {
        partidas.clear();
    }
    
    /**
     * Verifica si la lista de partidas está vacía.
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return partidas.isEmpty();
    }
    
    /**
     * Obtiene los IDs de todas las partidas.
     * 
     * @return Lista de IDs de partidas
     */
    public List<String> obtenerIdsPartidas() {
        List<String> ids = new ArrayList<>();
        for (Partida partida : partidas) {
            ids.add(partida.getId());
        }
        return ids;
    }
}
