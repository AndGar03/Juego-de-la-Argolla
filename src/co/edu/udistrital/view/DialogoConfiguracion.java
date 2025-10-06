package co.edu.udistrital.view;

import co.edu.udistrital.model.ConfiguracionJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Diálogo para la configuración del juego.
 * Implementa el patrón MVC al delegar la lógica de configuración
 * al controlador a través de la vista principal.
 * 
 * @author Sansantax
 * @version 1.0
 */
public class DialogoConfiguracion extends JDialog {
    
    /** Configuración del juego */
    private ConfiguracionJuego configuracion;
    
    /** Indica si la configuración fue aceptada */
    private boolean configuracionAceptada;
    
    // Componentes de la interfaz
    private JSpinner spinnerMaxJugadores;
    private JSpinner spinnerMaxEquipos;
    private JSpinner spinnerMaxRondas;
    private JSpinner spinnerPuntosParaGanar;
    private JSpinner spinnerPuntosPorAcierto;
    private JSpinner spinnerPuntosPorIntento;
    private JSpinner spinnerTiempoLimite;
    private JSpinner spinnerDistancia;
    private JSpinner spinnerDificultad;
    
    private JCheckBox checkBoxSonido;
    private JCheckBox checkBoxEfectosVisuales;
    
    private JButton botonAceptar;
    private JButton botonCancelar;
    private JButton botonPredeterminados;
    
    /**
     * Constructor del diálogo de configuración.
     * 
     * @param parent Ventana padre
     * @param configuracion Configuración actual
     */
    public DialogoConfiguracion(JFrame parent, ConfiguracionJuego configuracion) {
        super(parent, "Configuración del Juego", true);
        this.configuracion = configuracion;
        this.configuracionAceptada = false;
        
        inicializarComponentes();
        configurarInterfaz();
        configurarEventos();
        cargarConfiguracionActual();
    }
    
    /**
     * Inicializa todos los componentes del diálogo.
     */
    private void inicializarComponentes() {
        // Spinners para valores numéricos
        spinnerMaxJugadores = new JSpinner(new SpinnerNumberModel(4, 1, 10, 1));
        spinnerMaxEquipos = new JSpinner(new SpinnerNumberModel(4, 2, 8, 1));
        spinnerMaxRondas = new JSpinner(new SpinnerNumberModel(10, 1, 20, 1));
        spinnerPuntosParaGanar = new JSpinner(new SpinnerNumberModel(100, 50, 500, 10));
        spinnerPuntosPorAcierto = new JSpinner(new SpinnerNumberModel(10, 1, 50, 1));
        spinnerPuntosPorIntento = new JSpinner(new SpinnerNumberModel(1, 0, 10, 1));
        spinnerTiempoLimite = new JSpinner(new SpinnerNumberModel(60, 30, 300, 15));
        spinnerDistancia = new JSpinner(new SpinnerNumberModel(2.5, 1.0, 5.0, 0.5));
        spinnerDificultad = new JSpinner(new SpinnerNumberModel(3, 1, 5, 1));
        
        // Check boxes para opciones booleanas
        checkBoxSonido = new JCheckBox("Sonido habilitado");
        checkBoxEfectosVisuales = new JCheckBox("Efectos visuales habilitados");
        
        // Botones
        botonAceptar = new JButton("Aceptar");
        botonCancelar = new JButton("Cancelar");
        botonPredeterminados = new JButton("Valores Predeterminados");
    }
    
    /**
     * Configura la disposición de los componentes en el diálogo.
     */
    private void configurarInterfaz() {
        setSize(500, 600);
        setLocationRelativeTo(getParent());
        setResizable(false);
        
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Panel de configuración
        JPanel panelConfiguracion = new JPanel(new GridBagLayout());
        panelConfiguracion.setBorder(BorderFactory.createTitledBorder("Parámetros del Juego"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Configuración de equipos y jugadores
        agregarCampo(panelConfiguracion, gbc, "Máximo jugadores por equipo:", spinnerMaxJugadores, 0);
        agregarCampo(panelConfiguracion, gbc, "Máximo equipos por partida:", spinnerMaxEquipos, 1);
        agregarCampo(panelConfiguracion, gbc, "Máximo rondas por partida:", spinnerMaxRondas, 2);
        
        // Configuración de puntuación
        agregarCampo(panelConfiguracion, gbc, "Puntos para ganar:", spinnerPuntosParaGanar, 3);
        agregarCampo(panelConfiguracion, gbc, "Puntos por acierto:", spinnerPuntosPorAcierto, 4);
        agregarCampo(panelConfiguracion, gbc, "Puntos por intento:", spinnerPuntosPorIntento, 5);
        
        // Configuración de juego
        agregarCampo(panelConfiguracion, gbc, "Tiempo límite por ronda (seg):", spinnerTiempoLimite, 6);
        agregarCampo(panelConfiguracion, gbc, "Distancia argolla (metros):", spinnerDistancia, 7);
        agregarCampo(panelConfiguracion, gbc, "Dificultad (1-5):", spinnerDificultad, 8);
        
        // Opciones adicionales
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        panelConfiguracion.add(checkBoxSonido, gbc);
        
        gbc.gridy = 10;
        panelConfiguracion.add(checkBoxEfectosVisuales, gbc);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(botonPredeterminados);
        panelBotones.add(botonAceptar);
        panelBotones.add(botonCancelar);
        
        panelPrincipal.add(panelConfiguracion, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    /**
     * Agrega un campo de configuración al panel.
     * 
     * @param panel Panel al que agregar el campo
     * @param gbc Restricciones de GridBag
     * @param etiqueta Texto de la etiqueta
     * @param componente Componente a agregar
     * @param fila Fila en la que agregar el componente
     */
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String etiqueta, 
                             JComponent componente, int fila) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        panel.add(new JLabel(etiqueta), gbc);
        
        gbc.gridx = 1;
        panel.add(componente, gbc);
    }
    
    /**
     * Configura los eventos de los componentes.
     */
    private void configurarEventos() {
        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarConfiguracion();
                configuracionAceptada = true;
                dispose();
            }
        });
        
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracionAceptada = false;
                dispose();
            }
        });
        
        botonPredeterminados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarConfiguracionPredeterminada();
            }
        });
    }
    
    /**
     * Carga la configuración actual en los componentes.
     */
    private void cargarConfiguracionActual() {
        if (configuracion != null) {
            spinnerMaxJugadores.setValue(configuracion.getMaxJugadoresPorEquipo());
            spinnerMaxEquipos.setValue(configuracion.getMaxEquiposPorPartida());
            spinnerMaxRondas.setValue(configuracion.getMaxRondasPorPartida());
            spinnerPuntosParaGanar.setValue(configuracion.getPuntosParaGanar());
            spinnerPuntosPorAcierto.setValue(configuracion.getPuntosPorAcierto());
            spinnerPuntosPorIntento.setValue(configuracion.getPuntosPorIntento());
            spinnerTiempoLimite.setValue(configuracion.getTiempoLimitePorRonda());
            spinnerDistancia.setValue(configuracion.getDistanciaArgolla());
            spinnerDificultad.setValue(configuracion.getDificultad());
            checkBoxSonido.setSelected(configuracion.isSonidoHabilitado());
            checkBoxEfectosVisuales.setSelected(configuracion.isEfectosVisualesHabilitados());
        }
    }
    
    /**
     * Carga la configuración predeterminada en los componentes.
     */
    private void cargarConfiguracionPredeterminada() {
        ConfiguracionJuego configPredeterminada = new ConfiguracionJuego();
        
        spinnerMaxJugadores.setValue(configPredeterminada.getMaxJugadoresPorEquipo());
        spinnerMaxEquipos.setValue(configPredeterminada.getMaxEquiposPorPartida());
        spinnerMaxRondas.setValue(configPredeterminada.getMaxRondasPorPartida());
        spinnerPuntosParaGanar.setValue(configPredeterminada.getPuntosParaGanar());
        spinnerPuntosPorAcierto.setValue(configPredeterminada.getPuntosPorAcierto());
        spinnerPuntosPorIntento.setValue(configPredeterminada.getPuntosPorIntento());
        spinnerTiempoLimite.setValue(configPredeterminada.getTiempoLimitePorRonda());
        spinnerDistancia.setValue(configPredeterminada.getDistanciaArgolla());
        spinnerDificultad.setValue(configPredeterminada.getDificultad());
        checkBoxSonido.setSelected(configPredeterminada.isSonidoHabilitado());
        checkBoxEfectosVisuales.setSelected(configPredeterminada.isEfectosVisualesHabilitados());
    }
    
    /**
     * Aplica la configuración desde los componentes a la configuración.
     */
    private void aplicarConfiguracion() {
        if (configuracion == null) {
            configuracion = new ConfiguracionJuego();
        }
        
        configuracion.setMaxJugadoresPorEquipo((Integer) spinnerMaxJugadores.getValue());
        configuracion.setMaxEquiposPorPartida((Integer) spinnerMaxEquipos.getValue());
        configuracion.setMaxRondasPorPartida((Integer) spinnerMaxRondas.getValue());
        configuracion.setPuntosParaGanar((Integer) spinnerPuntosParaGanar.getValue());
        configuracion.setPuntosPorAcierto((Integer) spinnerPuntosPorAcierto.getValue());
        configuracion.setPuntosPorIntento((Integer) spinnerPuntosPorIntento.getValue());
        configuracion.setTiempoLimitePorRonda((Integer) spinnerTiempoLimite.getValue());
        configuracion.setDistanciaArgolla((Double) spinnerDistancia.getValue());
        configuracion.setDificultad((Integer) spinnerDificultad.getValue());
        configuracion.setSonidoHabilitado(checkBoxSonido.isSelected());
        configuracion.setEfectosVisualesHabilitados(checkBoxEfectosVisuales.isSelected());
    }
    
    /**
     * Obtiene la configuración del diálogo.
     * 
     * @return Configuración del juego
     */
    public ConfiguracionJuego getConfiguracion() {
        return configuracion;
    }
    
    /**
     * Verifica si la configuración fue aceptada.
     * 
     * @return true si fue aceptada, false en caso contrario
     */
    public boolean isConfiguracionAceptada() {
        return configuracionAceptada;
    }
}
