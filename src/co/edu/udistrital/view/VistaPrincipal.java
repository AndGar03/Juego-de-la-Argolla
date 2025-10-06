package co.edu.udistrital.view;

import co.edu.udistrital.controller.IControladorJuego;
import co.edu.udistrital.model.Equipo;
import co.edu.udistrital.model.Jugador;
import co.edu.udistrital.model.Partida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Vista principal del juego de la argolla.
 * Implementa estrictamente el patrón MVC al contener únicamente
 * componentes de interfaz gráfica y delegar toda la lógica al controlador.
 * 
 * @author Sansantax
 * @version 1.0
 */
public class VistaPrincipal extends JFrame {
    
    /** Controlador del juego (inyectado por dependencias) */
    private IControladorJuego controlador;
    
    // Componentes de la interfaz
    private JPanel panelPrincipal;
    private JPanel panelEquipos;
    private JPanel panelJugadores;
    private JPanel panelPartida;
    private JPanel panelEstadisticas;
    
    private JLabel labelTitulo;
    private JLabel labelEstadoPartida;
    private JLabel labelRonda;
    private JLabel labelEquipoActual;
    
    private JButton botonNuevaPartida;
    private JButton botonIniciarPartida;
    private JButton botonFinalizarPartida;
    private JButton botonConfiguracion;
    private JButton botonGuardarPartida;
    private JButton botonCargarPartida;
    
    private JList<Equipo> listaEquipos;
    private JList<Jugador> listaJugadores;
    private JTextArea areaEstadisticas;
    
    private JScrollPane scrollEquipos;
    private JScrollPane scrollJugadores;
    private JScrollPane scrollEstadisticas;
    
    /**
     * Constructor de la vista principal.
     * 
     * @param controlador Controlador del juego (inyectado por DIP)
     */
    public VistaPrincipal(IControladorJuego controlador) {
        this.controlador = controlador;
        inicializarComponentes();
        configurarInterfaz();
        configurarEventos();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz.
     */
    private void inicializarComponentes() {
        // Panel principal
        panelPrincipal = new JPanel(new BorderLayout());
        
        // Paneles secundarios
        panelEquipos = new JPanel(new BorderLayout());
        panelJugadores = new JPanel(new BorderLayout());
        panelPartida = new JPanel(new FlowLayout());
        panelEstadisticas = new JPanel(new BorderLayout());
        
        // Etiquetas
        labelTitulo = new JLabel("JUEGO DE LA ARGOLLA", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setForeground(Color.BLUE);
        
        labelEstadoPartida = new JLabel("Estado: Sin partida");
        labelRonda = new JLabel("Ronda: 0/0");
        labelEquipoActual = new JLabel("Equipo actual: Ninguno");
        
        // Botones
        botonNuevaPartida = new JButton("Nueva Partida");
        botonIniciarPartida = new JButton("Iniciar Partida");
        botonFinalizarPartida = new JButton("Finalizar Partida");
        botonConfiguracion = new JButton("Configuración");
        botonGuardarPartida = new JButton("Guardar Partida");
        botonCargarPartida = new JButton("Cargar Partida");
        
        // Listas
        listaEquipos = new JList<>();
        listaJugadores = new JList<>();
        
        // Área de texto
        areaEstadisticas = new JTextArea(10, 30);
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        // Scroll panes
        scrollEquipos = new JScrollPane(listaEquipos);
        scrollJugadores = new JScrollPane(listaJugadores);
        scrollEstadisticas = new JScrollPane(areaEstadisticas);
    }
    
    /**
     * Configura la disposición de los componentes en la interfaz.
     */
    private void configurarInterfaz() {
        setTitle("Juego de la Argolla");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Configurar panel de equipos
        panelEquipos.setBorder(BorderFactory.createTitledBorder("Equipos"));
        panelEquipos.add(scrollEquipos, BorderLayout.CENTER);
        
        // Configurar panel de jugadores
        panelJugadores.setBorder(BorderFactory.createTitledBorder("Jugadores del Equipo Seleccionado"));
        panelJugadores.add(scrollJugadores, BorderLayout.CENTER);
        
        // Configurar panel de partida
        panelPartida.setBorder(BorderFactory.createTitledBorder("Control de Partida"));
        panelPartida.add(botonNuevaPartida);
        panelPartida.add(botonIniciarPartida);
        panelPartida.add(botonFinalizarPartida);
        panelPartida.add(botonConfiguracion);
        panelPartida.add(botonGuardarPartida);
        panelPartida.add(botonCargarPartida);
        
        // Configurar panel de estadísticas
        panelEstadisticas.setBorder(BorderFactory.createTitledBorder("Estadísticas"));
        panelEstadisticas.add(scrollEstadisticas, BorderLayout.CENTER);
        
        // Configurar panel principal
        panelPrincipal.add(labelTitulo, BorderLayout.NORTH);
        
        JPanel panelCentral = new JPanel(new GridLayout(1, 2));
        panelCentral.add(panelEquipos);
        panelCentral.add(panelJugadores);
        
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelPartida, BorderLayout.NORTH);
        panelInferior.add(panelEstadisticas, BorderLayout.CENTER);
        
        JPanel panelInfo = new JPanel(new FlowLayout());
        panelInfo.add(labelEstadoPartida);
        panelInfo.add(labelRonda);
        panelInfo.add(labelEquipoActual);
        
        panelPrincipal.add(panelInfo, BorderLayout.CENTER);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        add(panelPrincipal);
        
        // Configurar estado inicial de los botones
        actualizarEstadoBotones();
    }
    
    /**
     * Configura los eventos de los componentes.
     * Los métodos actionPerformed son mínimos y solo delegan al controlador.
     */
    private void configurarEventos() {
        botonNuevaPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.iniciarNuevaPartida(controlador.getConfiguracion());
                actualizarVista();
            }
        });
        
        botonIniciarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.iniciarPartida();
                actualizarVista();
            }
        });
        
        botonFinalizarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.finalizarPartida();
                actualizarVista();
            }
        });
        
        botonConfiguracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoConfiguracion();
            }
        });
        
        botonGuardarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.guardarPartida();
                actualizarVista();
            }
        });
        
        botonCargarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoCargarPartida();
            }
        });
        
        // Evento de selección de equipo
        listaEquipos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                actualizarJugadoresDelEquipo();
            }
        });
    }
    
    /**
     * Actualiza toda la vista con la información del controlador.
     */
    public void actualizarVista() {
        actualizarListaEquipos();
        actualizarEstadoPartida();
        actualizarEstadisticas();
        actualizarEstadoBotones();
    }
    
    /**
     * Actualiza la lista de equipos.
     */
    private void actualizarListaEquipos() {
        List<Equipo> equipos = controlador.getEquipos();
        listaEquipos.setListData(equipos.toArray(new Equipo[0]));
    }
    
    /**
     * Actualiza los jugadores del equipo seleccionado.
     */
    private void actualizarJugadoresDelEquipo() {
        Equipo equipoSeleccionado = listaEquipos.getSelectedValue();
        if (equipoSeleccionado != null) {
            List<Jugador> jugadores = equipoSeleccionado.getJugadores();
            listaJugadores.setListData(jugadores.toArray(new Jugador[0]));
        } else {
            listaJugadores.setListData(new Jugador[0]);
        }
    }
    
    /**
     * Actualiza el estado de la partida en la interfaz.
     */
    private void actualizarEstadoPartida() {
        Partida partida = controlador.getPartidaActual();
        if (partida != null) {
            labelEstadoPartida.setText("Estado: " + partida.getEstado());
            labelRonda.setText("Ronda: " + partida.getRondaActual() + "/" + partida.getMaxRondas());
        } else {
            labelEstadoPartida.setText("Estado: Sin partida");
            labelRonda.setText("Ronda: 0/0");
        }
    }
    
    /**
     * Actualiza las estadísticas en el área de texto.
     */
    private void actualizarEstadisticas() {
        String estadisticas = controlador.obtenerEstadisticasPartida();
        areaEstadisticas.setText(estadisticas);
    }
    
    /**
     * Actualiza el estado de los botones según el estado del juego.
     */
    private void actualizarEstadoBotones() {
        boolean hayPartida = controlador.getPartidaActual() != null;
        boolean partidaEnCurso = controlador.estaPartidaEnCurso();
        boolean partidaTerminada = controlador.haTerminadoPartida();
        
        botonNuevaPartida.setEnabled(!partidaEnCurso);
        botonIniciarPartida.setEnabled(hayPartida && !partidaEnCurso && !partidaTerminada);
        botonFinalizarPartida.setEnabled(partidaEnCurso);
        botonGuardarPartida.setEnabled(hayPartida);
        botonCargarPartida.setEnabled(!partidaEnCurso);
    }
    
    /**
     * Muestra el diálogo de configuración.
     */
    private void mostrarDialogoConfiguracion() {
        // Crear diálogo de configuración
        DialogoConfiguracion dialogo = new DialogoConfiguracion(this, controlador.getConfiguracion());
        dialogo.setVisible(true);
        
        if (dialogo.isConfiguracionAceptada()) {
            controlador.setConfiguracion(dialogo.getConfiguracion());
            actualizarVista();
        }
    }
    
    /**
     * Muestra el diálogo para cargar una partida.
     */
    private void mostrarDialogoCargarPartida() {
        // Implementación simplificada - en una implementación completa
        // se mostraría una lista de partidas guardadas
        String idPartida = JOptionPane.showInputDialog(this, "Ingrese el ID de la partida a cargar:");
        if (idPartida != null && !idPartida.trim().isEmpty()) {
            controlador.cargarPartida(idPartida.trim());
            actualizarVista();
        }
    }
    
    /**
     * Muestra un mensaje de información.
     * 
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de error.
     * 
     * @param mensaje Mensaje de error a mostrar
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de confirmación.
     * 
     * @param mensaje Mensaje de confirmación
     * @return true si el usuario confirma, false en caso contrario
     */
    public boolean mostrarConfirmacion(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar", 
                                                    JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
