package udistrital.avanzada.argolla.control;

import udistrital.avanzada.argolla.modelo.Equipo;
import udistrital.avanzada.argolla.modelo.Jugador;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar la persistencia de equipos y jugadores usando archivos de acceso aleatorio.
 * Implementa el principio de responsabilidad única (SRP) al manejar únicamente
 * las operaciones de archivos de acceso aleatorio.
 * 
 * @author Sansantax, And_Gar03
 * @version 3.0
 */
public class ArchivoAccesoAleatorio {
    
    /** Tamaño fijo para cada registro de equipo en bytes */
    private static final int TAMANO_REGISTRO_EQUIPO = 256;
    
    /** Tamaño fijo para cada registro de jugador en bytes */
    private static final int TAMANO_REGISTRO_JUGADOR = 128;
    
    /** Archivo de acceso aleatorio para equipos */
    private static final String ARCHIVO_EQUIPOS = "Specs/data/equipos.dat";
    
    /** Archivo de acceso aleatorio para jugadores */
    private static final String ARCHIVO_JUGADORES = "Specs/data/jugadores.dat";
    
    /** Separador de campos */
    private static final String SEPARADOR = "|";
    
    /**
     * Constructor que crea los directorios necesarios.
     */
    public ArchivoAccesoAleatorio() {
        crearDirectoriosNecesarios();
    }
    
    /**
     * Crea los directorios necesarios para la persistencia.
     */
    private void crearDirectoriosNecesarios() {
        try {
            Files.createDirectories(Paths.get("Specs/data"));
        } catch (IOException e) {
            // En caso de error, se continúa sin los directorios
        }
    }
    
    /**
     * Guarda un equipo en el archivo de acceso aleatorio.
     * 
     * @param equipo Equipo a guardar
     * @return true si se guardó exitosamente, false en caso contrario
     */
    public boolean guardarEquipo(Equipo equipo) {
        if (equipo == null || equipo.getNombre() == null || equipo.getNombre().trim().isEmpty()) {
            return false;
        }
        
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO_EQUIPOS, "rw")) {
            // Buscar un espacio disponible o agregar al final
            long posicion = buscarEspacioDisponible(raf);
            
            // Mover al inicio del registro
            raf.seek(posicion * TAMANO_REGISTRO_EQUIPO);
            
            // Escribir los datos del equipo
            String registro = formatearEquipo(equipo);
            raf.writeUTF(registro);
            
            // Completar el registro hasta el tamaño fijo
            int bytesEscritos = registro.getBytes("UTF-8").length;
            while (bytesEscritos < TAMANO_REGISTRO_EQUIPO - 2) { // -2 por el prefijo de longitud de UTF
                raf.writeByte(0);
                bytesEscritos++;
            }
            
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Carga todos los equipos del archivo de acceso aleatorio.
     * 
     * @return Lista de equipos cargados
     */
    public List<Equipo> cargarEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO_EQUIPOS, "r")) {
            long longitud = raf.length();
            int numeroRegistros = (int) (longitud / TAMANO_REGISTRO_EQUIPO);
            
            for (int i = 0; i < numeroRegistros; i++) {
                raf.seek(i * TAMANO_REGISTRO_EQUIPO);
                
                try {
                    String registro = raf.readUTF();
                    if (!registro.trim().isEmpty()) {
                        Equipo equipo = parsearEquipo(registro);
                        if (equipo != null) {
                            equipos.add(equipo);
                        }
                    }
                } catch (EOFException e) {
                    break; // Fin del archivo
                }
            }
        } catch (IOException e) {
            // Archivo no existe o error de lectura, retorna lista vacía
        }
        
        return equipos;
    }
    
    /**
     * Guarda un jugador en el archivo de acceso aleatorio.
     * 
     * @param jugador Jugador a guardar
     * @param nombreEquipo Nombre del equipo al que pertenece
     * @return true si se guardó exitosamente, false en caso contrario
     */
    public boolean guardarJugador(Jugador jugador, String nombreEquipo) {
        if (jugador == null || jugador.getNombre() == null || jugador.getNombre().trim().isEmpty()) {
            return false;
        }
        
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO_JUGADORES, "rw")) {
            // Buscar un espacio disponible o agregar al final
            long posicion = buscarEspacioDisponible(raf);
            
            // Mover al inicio del registro
            raf.seek(posicion * TAMANO_REGISTRO_JUGADOR);
            
            // Escribir los datos del jugador
            String registro = formatearJugador(jugador, nombreEquipo);
            raf.writeUTF(registro);
            
            // Completar el registro hasta el tamaño fijo
            int bytesEscritos = registro.getBytes("UTF-8").length;
            while (bytesEscritos < TAMANO_REGISTRO_JUGADOR - 2) { // -2 por el prefijo de longitud de UTF
                raf.writeByte(0);
                bytesEscritos++;
            }
            
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Carga todos los jugadores del archivo de acceso aleatorio.
     * 
     * @return Lista de jugadores cargados
     */
    public List<Jugador> cargarJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO_JUGADORES, "r")) {
            long longitud = raf.length();
            int numeroRegistros = (int) (longitud / TAMANO_REGISTRO_JUGADOR);
            
            for (int i = 0; i < numeroRegistros; i++) {
                raf.seek(i * TAMANO_REGISTRO_JUGADOR);
                
                try {
                    String registro = raf.readUTF();
                    if (!registro.trim().isEmpty()) {
                        Jugador jugador = parsearJugador(registro);
                        if (jugador != null) {
                            jugadores.add(jugador);
                        }
                    }
                } catch (EOFException e) {
                    break; // Fin del archivo
                }
            }
        } catch (IOException e) {
            // Archivo no existe o error de lectura, retorna lista vacía
        }
        
        return jugadores;
    }
    
    /**
     * Busca un espacio disponible en el archivo.
     * 
     * @param raf Archivo de acceso aleatorio
     * @return Posición del espacio disponible
     * @throws IOException Si hay error de E/O
     */
    private long buscarEspacioDisponible(RandomAccessFile raf) throws IOException {
        long longitud = raf.length();
        int numeroRegistros = (int) (longitud / TAMANO_REGISTRO_EQUIPO);
        
        // Buscar un registro vacío
        for (int i = 0; i < numeroRegistros; i++) {
            raf.seek(i * TAMANO_REGISTRO_EQUIPO);
            try {
                String registro = raf.readUTF();
                if (registro.trim().isEmpty()) {
                    return i;
                }
            } catch (EOFException e) {
                break;
            }
        }
        
        // Si no hay espacios disponibles, agregar al final
        return numeroRegistros;
    }
    
    /**
     * Formatea un equipo para guardar en el archivo.
     * 
     * @param equipo Equipo a formatear
     * @return String formateado
     */
    private String formatearEquipo(Equipo equipo) {
        StringBuilder sb = new StringBuilder();
        sb.append(equipo.getNombre()).append(SEPARADOR);
        sb.append(equipo.getColor()).append(SEPARADOR);
        sb.append(equipo.getPuntuacionTotal()).append(SEPARADOR);
        sb.append(equipo.getTotalAciertos()).append(SEPARADOR);
        sb.append(equipo.getTotalIntentos()).append(SEPARADOR);
        return sb.toString();
    }
    
    /**
     * Parsea un string para crear un equipo.
     * 
     * @param registro String del archivo
     * @return Equipo parseado
     */
    private Equipo parsearEquipo(String registro) {
        try {
            String[] campos = registro.split("\\" + SEPARADOR);
            if (campos.length >= 3) {
                Equipo equipo = new Equipo(campos[0], campos[1]);
                
                // Restaurar estadísticas si están disponibles
                if (campos.length >= 5) {
                    // Las estadísticas se restaurarán cuando se carguen los jugadores
                }
                
                return equipo;
            }
        } catch (Exception e) {
            // Error al parsear, retorna null
        }
        return null;
    }
    
    /**
     * Formatea un jugador para guardar en el archivo.
     * 
     * @param jugador Jugador a formatear
     * @param nombreEquipo Nombre del equipo
     * @return String formateado
     */
    private String formatearJugador(Jugador jugador, String nombreEquipo) {
        StringBuilder sb = new StringBuilder();
        sb.append(jugador.getNombre()).append(SEPARADOR);
        sb.append(nombreEquipo).append(SEPARADOR);
        sb.append(jugador.getPuntuacion()).append(SEPARADOR);
        sb.append(jugador.getAciertos()).append(SEPARADOR);
        sb.append(jugador.getIntentos()).append(SEPARADOR);
        return sb.toString();
    }
    
    /**
     * Parsea un string para crear un jugador.
     * 
     * @param registro String del archivo
     * @return Jugador parseado
     */
    private Jugador parsearJugador(String registro) {
        try {
            String[] campos = registro.split("\\" + SEPARADOR);
            if (campos.length >= 5) {
                Jugador jugador = new Jugador(campos[0]);
                jugador.setPuntuacion(Integer.parseInt(campos[2]));
                jugador.setAciertos(Integer.parseInt(campos[3]));
                jugador.setIntentos(Integer.parseInt(campos[4]));
                return jugador;
            }
        } catch (Exception e) {
            // Error al parsear, retorna null
        }
        return null;
    }
    
    /**
     * Elimina todos los datos guardados.
     * 
     * @return true si se eliminaron exitosamente, false en caso contrario
     */
    public boolean limpiarDatos() {
        try {
            new File(ARCHIVO_EQUIPOS).delete();
            new File(ARCHIVO_JUGADORES).delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Obtiene el número de equipos guardados.
     * 
     * @return Número de equipos
     */
    public int getNumeroEquipos() {
        return cargarEquipos().size();
    }
    
    /**
     * Obtiene el número de jugadores guardados.
     * 
     * @return Número de jugadores
     */
    public int getNumeroJugadores() {
        return cargarJugadores().size();
    }
}


