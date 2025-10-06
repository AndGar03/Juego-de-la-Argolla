package co.edu.udistrital.view;

import co.edu.udistrital.controller.IControladorJuego;
import co.edu.udistrital.model.Equipo;
import co.edu.udistrital.model.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Diálogo para la gestión de equipos y jugadores.
 * Implementa el patrón MVC al delegar toda la lógica al controlador.
 * 
 * @author And_Gar03
 * @version 2.0
 */
public class DialogoGestionEquipos extends JDialog {
    
    /** Controlador del juego */
    private IControladorJuego controlador;
    
    // Componentes de la interfaz
    private JList<Equipo> listaEquipos;
    private JList<Jugador> listaJugadores;
    private JTextField campoNombreEquipo;
    private JTextField campoColorEquipo;
    private JTextField campoNombreJugador;
    
    private JButton botonAgregarEquipo;
    private JButton botonRemoverEquipo;
    private JButton botonAgregarJugador;
    private JButton botonRemoverJugador;
    private JButton botonCerrar;
    
    private JLabel labelNombreEquipo;
    private JLabel labelColorEquipo;
    private JLabel labelNombreJugador;
    
    /**
     * Constructor del diálogo de gestión de equipos.
     * 
     * @param parent Ventana padre
     * @param controlador Controlador del juego
     */
    public DialogoGestionEquipos(JFrame parent, IControladorJuego controlador) {
        super(parent, "Gestión de Equipos y Jugadores", true);
        this.controlador = controlador;
        
        inicializarComponentes();
        configurarInterfaz();
        configurarEventos();
        actualizarListas();
    }
    
    /**
     * Inicializa todos los componentes del diálogo.
     */
    private void inicializarComponentes() {
        // Listas
        listaEquipos = new JList<>();
        listaJugadores = new JList<>();
        
        // Campos de texto
        campoNombreEquipo = new JTextField(15);
        campoColorEquipo = new JTextField(15);
        campoNombreJugador = new JTextField(15);
        
        // Botones
        botonAgregarEquipo = new JButton("Agregar Equipo");
        botonRemoverEquipo = new JButton("Remover Equipo");
        botonAgregarJugador = new JButton("Agregar Jugador");
        botonRemoverJugador = new JButton("Remover Jugador");
        botonCerrar = new JButton("Cerrar");
        
        // Etiquetas
        labelNombreEquipo = new JLabel("Nombre del Equipo:");
        labelColorEquipo = new JLabel("Color del Equipo:");
        labelNombreJugador = new JLabel("Nombre del Jugador:");
    }
    
    /**
     * Configura la disposición de los componentes en el diálogo.
     */
    private void configurarInterfaz() {
        setSize(800, 600);
        setLocationRelativeTo(getParent());
        setResizable(true);
        
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Panel superior para equipos
        JPanel panelEquipos = new JPanel(new BorderLayout());
        panelEquipos.setBorder(BorderFactory.createTitledBorder("Gestión de Equipos"));
        
        JPanel panelDatosEquipo = new JPanel(new FlowLayout());
        panelDatosEquipo.add(labelNombreEquipo);
        panelDatosEquipo.add(campoNombreEquipo);
        panelDatosEquipo.add(labelColorEquipo);
        panelDatosEquipo.add(campoColorEquipo);
        panelDatosEquipo.add(botonAgregarEquipo);
        panelDatosEquipo.add(botonRemoverEquipo);
        
        JScrollPane scrollEquipos = new JScrollPane(listaEquipos);
        scrollEquipos.setPreferredSize(new Dimension(300, 150));
        
        panelEquipos.add(panelDatosEquipo, BorderLayout.NORTH);
        panelEquipos.add(scrollEquipos, BorderLayout.CENTER);
        
        // Panel central para jugadores
        JPanel panelJugadores = new JPanel(new BorderLayout());
        panelJugadores.setBorder(BorderFactory.createTitledBorder("Gestión de Jugadores"));
        
        JPanel panelDatosJugador = new JPanel(new FlowLayout());
        panelDatosJugador.add(labelNombreJugador);
        panelDatosJugador.add(campoNombreJugador);
        panelDatosJugador.add(botonAgregarJugador);
        panelDatosJugador.add(botonRemoverJugador);
        
        JScrollPane scrollJugadores = new JScrollPane(listaJugadores);
        scrollJugadores.setPreferredSize(new Dimension(300, 150));
        
        panelJugadores.add(panelDatosJugador, BorderLayout.NORTH);
        panelJugadores.add(scrollJugadores, BorderLayout.CENTER);
        
        // Panel inferior con botón cerrar
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(botonCerrar);
        
        // Organizar paneles
        JPanel panelCentral = new JPanel(new GridLayout(2, 1));
        panelCentral.add(panelEquipos);
        panelCentral.add(panelJugadores);
        
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    /**
     * Configura los eventos de los componentes.
     */
    private void configurarEventos() {
        botonAgregarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEquipo();
            }
        });
        
        botonRemoverEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerEquipo();
            }
        });
        
        botonAgregarJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarJugador();
            }
        });
        
        botonRemoverJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerJugador();
            }
        });
        
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
     * Agrega un nuevo equipo.
     */
    private void agregarEquipo() {
        String nombre = campoNombreEquipo.getText().trim();
        String color = campoColorEquipo.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre para el equipo", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (color.isEmpty()) {
            color = "Azul"; // Color por defecto
        }
        
        Equipo nuevoEquipo = new Equipo(nombre, color);
        
        if (controlador.agregarEquipo(nuevoEquipo)) {
            campoNombreEquipo.setText("");
            campoColorEquipo.setText("");
            actualizarListas();
            JOptionPane.showMessageDialog(this, "Equipo agregado exitosamente", 
                                         "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el equipo", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Remueve el equipo seleccionado.
     */
    private void removerEquipo() {
        Equipo equipoSeleccionado = listaEquipos.getSelectedValue();
        
        if (equipoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un equipo para remover", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea remover el equipo '" + equipoSeleccionado.getNombre() + "'?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            if (controlador.removerEquipo(equipoSeleccionado)) {
                actualizarListas();
                JOptionPane.showMessageDialog(this, "Equipo removido exitosamente", 
                                             "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo remover el equipo", 
                                             "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Agrega un nuevo jugador al equipo seleccionado.
     */
    private void agregarJugador() {
        Equipo equipoSeleccionado = listaEquipos.getSelectedValue();
        String nombreJugador = campoNombreJugador.getText().trim();
        
        if (equipoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un equipo", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (nombreJugador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre para el jugador", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Jugador nuevoJugador = new Jugador(nombreJugador);
        
        if (controlador.agregarJugadorAEquipo(equipoSeleccionado, nuevoJugador)) {
            campoNombreJugador.setText("");
            actualizarJugadoresDelEquipo();
            JOptionPane.showMessageDialog(this, "Jugador agregado exitosamente", 
                                         "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el jugador", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Remueve el jugador seleccionado del equipo seleccionado.
     */
    private void removerJugador() {
        Equipo equipoSeleccionado = listaEquipos.getSelectedValue();
        Jugador jugadorSeleccionado = listaJugadores.getSelectedValue();
        
        if (equipoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un equipo", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (jugadorSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un jugador para remover", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea remover al jugador '" + jugadorSeleccionado.getNombre() + "'?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            if (controlador.removerJugadorDeEquipo(equipoSeleccionado, jugadorSeleccionado)) {
                actualizarJugadoresDelEquipo();
                JOptionPane.showMessageDialog(this, "Jugador removido exitosamente", 
                                             "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo remover el jugador", 
                                             "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Actualiza las listas de equipos y jugadores.
     */
    private void actualizarListas() {
        List<Equipo> equipos = controlador.getEquipos();
        listaEquipos.setListData(equipos.toArray(new Equipo[0]));
        actualizarJugadoresDelEquipo();
    }
    
    /**
     * Actualiza la lista de jugadores del equipo seleccionado.
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
}
