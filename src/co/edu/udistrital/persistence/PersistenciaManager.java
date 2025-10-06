package co.edu.udistrital.persistence;

import co.edu.udistrital.model.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Gestor de persistencia para el manejo de archivos del juego.
 * Implementa el principio de responsabilidad única (SRP) al manejar
 * únicamente las operaciones de persistencia de datos.
 * 
 * @author And_Gar03
 * @version 2.0
 */
public class PersistenciaManager {
    
    /** Directorio base para los archivos de datos */
    private static final String DIRECTORIO_BASE = "Specs/data";
    
    /** Archivo de configuración del juego */
    private static final String ARCHIVO_CONFIGURACION = "configuracion.properties";
    
    /** Directorio para partidas guardadas */
    private static final String DIRECTORIO_PARTIDAS = "partidas";
    
    /** Directorio para estadísticas */
    private static final String DIRECTORIO_ESTADISTICAS = "estadisticas";
    
    /**
     * Constructor del gestor de persistencia.
     * Crea los directorios necesarios si no existen.
     */
    public PersistenciaManager() {
        crearDirectoriosNecesarios();
    }
    
    /**
     * Crea los directorios necesarios para la persistencia.
     */
    private void crearDirectoriosNecesarios() {
        try {
            Files.createDirectories(Paths.get(DIRECTORIO_BASE));
            Files.createDirectories(Paths.get(DIRECTORIO_BASE, DIRECTORIO_PARTIDAS));
            Files.createDirectories(Paths.get(DIRECTORIO_BASE, DIRECTORIO_ESTADISTICAS));
        } catch (IOException e) {
            // En caso de error, se continúa sin los directorios
            // El sistema intentará crearlos cuando sea necesario
        }
    }
    
    /**
     * Guarda la configuración del juego en un archivo properties.
     * 
     * @param configuracion Configuración a guardar
     * @return true si se guardó exitosamente, false en caso contrario
     */
    public boolean guardarConfiguracion(ConfiguracionJuego configuracion) {
        if (configuracion == null) {
            return false;
        }
        
        try {
            Properties props = new Properties();
            props.setProperty("maxJugadoresPorEquipo", String.valueOf(configuracion.getMaxJugadoresPorEquipo()));
            props.setProperty("maxEquiposPorPartida", String.valueOf(configuracion.getMaxEquiposPorPartida()));
            props.setProperty("maxRondasPorPartida", String.valueOf(configuracion.getMaxRondasPorPartida()));
            props.setProperty("puntosParaGanar", String.valueOf(configuracion.getPuntosParaGanar()));
            props.setProperty("puntosPorAcierto", String.valueOf(configuracion.getPuntosPorAcierto()));
            props.setProperty("puntosPorIntento", String.valueOf(configuracion.getPuntosPorIntento()));
            props.setProperty("tiempoLimitePorRonda", String.valueOf(configuracion.getTiempoLimitePorRonda()));
            props.setProperty("distanciaArgolla", String.valueOf(configuracion.getDistanciaArgolla()));
            props.setProperty("dificultad", String.valueOf(configuracion.getDificultad()));
            props.setProperty("sonidoHabilitado", String.valueOf(configuracion.isSonidoHabilitado()));
            props.setProperty("efectosVisualesHabilitados", String.valueOf(configuracion.isEfectosVisualesHabilitados()));
            
            Path archivoConfig = Paths.get(DIRECTORIO_BASE, ARCHIVO_CONFIGURACION);
            try (FileOutputStream fos = new FileOutputStream(archivoConfig.toFile())) {
                props.store(fos, "Configuración del Juego de la Argolla");
            }
            
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Carga la configuración del juego desde un archivo properties.
     * 
     * @return Configuración cargada, null si no se pudo cargar
     */
    public ConfiguracionJuego cargarConfiguracion() {
        try {
            Path archivoConfig = Paths.get(DIRECTORIO_BASE, ARCHIVO_CONFIGURACION);
            if (!Files.exists(archivoConfig)) {
                return new ConfiguracionJuego(); // Retorna configuración por defecto
            }
            
            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream(archivoConfig.toFile())) {
                props.load(fis);
            }
            
            ConfiguracionJuego configuracion = new ConfiguracionJuego();
            configuracion.setMaxJugadoresPorEquipo(Integer.parseInt(props.getProperty("maxJugadoresPorEquipo", "4")));
            configuracion.setMaxEquiposPorPartida(Integer.parseInt(props.getProperty("maxEquiposPorPartida", "4")));
            configuracion.setMaxRondasPorPartida(Integer.parseInt(props.getProperty("maxRondasPorPartida", "10")));
            configuracion.setPuntosParaGanar(Integer.parseInt(props.getProperty("puntosParaGanar", "100")));
            configuracion.setPuntosPorAcierto(Integer.parseInt(props.getProperty("puntosPorAcierto", "10")));
            configuracion.setPuntosPorIntento(Integer.parseInt(props.getProperty("puntosPorIntento", "1")));
            configuracion.setTiempoLimitePorRonda(Integer.parseInt(props.getProperty("tiempoLimitePorRonda", "60")));
            configuracion.setDistanciaArgolla(Double.parseDouble(props.getProperty("distanciaArgolla", "2.5")));
            configuracion.setDificultad(Integer.parseInt(props.getProperty("dificultad", "3")));
            configuracion.setSonidoHabilitado(Boolean.parseBoolean(props.getProperty("sonidoHabilitado", "true")));
            configuracion.setEfectosVisualesHabilitados(Boolean.parseBoolean(props.getProperty("efectosVisualesHabilitados", "true")));
            
            return configuracion;
        } catch (IOException | NumberFormatException e) {
            return new ConfiguracionJuego(); // Retorna configuración por defecto en caso de error
        }
    }
    
    /**
     * Guarda una partida en un archivo serializado.
     * 
     * @param partida Partida a guardar
     * @return true si se guardó exitosamente, false en caso contrario
     */
    public boolean guardarPartida(Partida partida) {
        if (partida == null || partida.getId() == null || partida.getId().trim().isEmpty()) {
            return false;
        }
        
        try {
            String nombreArchivo = "partida_" + partida.getId() + ".dat";
            Path archivoPartida = Paths.get(DIRECTORIO_BASE, DIRECTORIO_PARTIDAS, nombreArchivo);
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoPartida.toFile()))) {
                oos.writeObject(partida);
            }
            
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Carga una partida desde un archivo serializado.
     * 
     * @param idPartida ID de la partida a cargar
     * @return Partida cargada, null si no se pudo cargar
     */
    public Partida cargarPartida(String idPartida) {
        if (idPartida == null || idPartida.trim().isEmpty()) {
            return null;
        }
        
        try {
            String nombreArchivo = "partida_" + idPartida + ".dat";
            Path archivoPartida = Paths.get(DIRECTORIO_BASE, DIRECTORIO_PARTIDAS, nombreArchivo);
            
            if (!Files.exists(archivoPartida)) {
                return null;
            }
            
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoPartida.toFile()))) {
                return (Partida) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    
    /**
     * Elimina una partida guardada.
     * 
     * @param idPartida ID de la partida a eliminar
     * @return true si se eliminó exitosamente, false en caso contrario
     */
    public boolean eliminarPartida(String idPartida) {
        if (idPartida == null || idPartida.trim().isEmpty()) {
            return false;
        }
        
        try {
            String nombreArchivo = "partida_" + idPartida + ".dat";
            Path archivoPartida = Paths.get(DIRECTORIO_BASE, DIRECTORIO_PARTIDAS, nombreArchivo);
            
            if (Files.exists(archivoPartida)) {
                Files.delete(archivoPartida);
                return true;
            }
            
            return false;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Obtiene la lista de IDs de partidas guardadas.
     * 
     * @return Lista de IDs de partidas guardadas
     */
    public java.util.List<String> obtenerIdsPartidasGuardadas() {
        java.util.List<String> ids = new java.util.ArrayList<>();
        
        try {
            Path directorioPartidas = Paths.get(DIRECTORIO_BASE, DIRECTORIO_PARTIDAS);
            if (Files.exists(directorioPartidas)) {
                Files.list(directorioPartidas)
                     .filter(path -> path.toString().endsWith(".dat"))
                     .forEach(path -> {
                         String nombreArchivo = path.getFileName().toString();
                         if (nombreArchivo.startsWith("partida_") && nombreArchivo.endsWith(".dat")) {
                             String id = nombreArchivo.substring(8, nombreArchivo.length() - 4);
                             ids.add(id);
                         }
                     });
            }
        } catch (IOException e) {
            // En caso de error, retorna lista vacía
        }
        
        return ids;
    }
    
    /**
     * Guarda estadísticas del juego en un archivo.
     * 
     * @param estadisticas Estadísticas a guardar
     * @return true si se guardó exitosamente, false en caso contrario
     */
    public boolean guardarEstadisticas(String estadisticas) {
        if (estadisticas == null || estadisticas.trim().isEmpty()) {
            return false;
        }
        
        try {
            String nombreArchivo = "estadisticas_" + System.currentTimeMillis() + ".txt";
            Path archivoEstadisticas = Paths.get(DIRECTORIO_BASE, DIRECTORIO_ESTADISTICAS, nombreArchivo);
            
            Files.write(archivoEstadisticas, estadisticas.getBytes("UTF-8"));
            
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Carga estadísticas desde un archivo.
     * 
     * @param nombreArchivo Nombre del archivo de estadísticas
     * @return Estadísticas cargadas, null si no se pudo cargar
     */
    public String cargarEstadisticas(String nombreArchivo) {
        if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) {
            return null;
        }
        
        try {
            Path archivoEstadisticas = Paths.get(DIRECTORIO_BASE, DIRECTORIO_ESTADISTICAS, nombreArchivo);
            
            if (!Files.exists(archivoEstadisticas)) {
                return null;
            }
            
            return new String(Files.readAllBytes(archivoEstadisticas), "UTF-8");
        } catch (IOException e) {
            return null;
        }
    }
    
    /**
     * Obtiene la lista de archivos de estadísticas disponibles.
     * 
     * @return Lista de nombres de archivos de estadísticas
     */
    public java.util.List<String> obtenerArchivosEstadisticas() {
        java.util.List<String> archivos = new java.util.ArrayList<>();
        
        try {
            Path directorioEstadisticas = Paths.get(DIRECTORIO_BASE, DIRECTORIO_ESTADISTICAS);
            if (Files.exists(directorioEstadisticas)) {
                Files.list(directorioEstadisticas)
                     .filter(path -> path.toString().endsWith(".txt"))
                     .forEach(path -> archivos.add(path.getFileName().toString()));
            }
        } catch (IOException e) {
            // En caso de error, retorna lista vacía
        }
        
        return archivos;
    }
    
    /**
     * Verifica si existe una partida guardada con el ID especificado.
     * 
     * @param idPartida ID de la partida
     * @return true si existe, false en caso contrario
     */
    public boolean existePartida(String idPartida) {
        if (idPartida == null || idPartida.trim().isEmpty()) {
            return false;
        }
        
        String nombreArchivo = "partida_" + idPartida + ".dat";
        Path archivoPartida = Paths.get(DIRECTORIO_BASE, DIRECTORIO_PARTIDAS, nombreArchivo);
        return Files.exists(archivoPartida);
    }
    
    /**
     * Obtiene el tamaño del archivo de una partida en bytes.
     * 
     * @param idPartida ID de la partida
     * @return Tamaño del archivo en bytes, -1 si no existe
     */
    public long obtenerTamanoPartida(String idPartida) {
        if (idPartida == null || idPartida.trim().isEmpty()) {
            return -1;
        }
        
        try {
            String nombreArchivo = "partida_" + idPartida + ".dat";
            Path archivoPartida = Paths.get(DIRECTORIO_BASE, DIRECTORIO_PARTIDAS, nombreArchivo);
            
            if (Files.exists(archivoPartida)) {
                return Files.size(archivoPartida);
            }
        } catch (IOException e) {
            // En caso de error, retorna -1
        }
        
        return -1;
    }
    
    /**
     * Limpia todos los archivos de partidas guardadas.
     * 
     * @return true si se limpiaron exitosamente, false en caso contrario
     */
    public boolean limpiarPartidasGuardadas() {
        try {
            Path directorioPartidas = Paths.get(DIRECTORIO_BASE, DIRECTORIO_PARTIDAS);
            if (Files.exists(directorioPartidas)) {
                Files.list(directorioPartidas)
                     .filter(path -> path.toString().endsWith(".dat"))
                     .forEach(path -> {
                         try {
                             Files.delete(path);
                         } catch (IOException e) {
                             // Ignora errores individuales
                         }
                     });
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
