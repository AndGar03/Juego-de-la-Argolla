# Juego de la Argolla - Versión 3.0

## Descripción
Aplicación de escritorio desarrollada en Java con Swing para el "Juego de la Argolla". Implementa una arquitectura MVC estricta siguiendo los principios SOLID.

## Autor
**Sansantax** - Versión 3.0

## Características Principales

### Arquitectura
- **Modelo**: Contiene únicamente lógica de negocio (clases Equipo, Jugador, Partida, ConfiguracionJuego)
- **Vista**: Interfaz gráfica con Swing (VistaPrincipal, DialogoConfiguracion, DialogoGestionEquipos)
- **Controlador**: Orquesta el flujo del juego (GameManager, GestorEquipos, GestorJugadores, GestorPartidas)

### Funcionalidades
1. **Gestión de Partidas**
   - Crear nueva partida
   - Iniciar/finalizar partidas
   - Guardar/cargar partidas
   - Estadísticas en tiempo real

2. **Gestión de Equipos**
   - Agregar equipos con nombre y color
   - Límite configurable de equipos por partida

3. **Gestión de Jugadores**
   - Agregar jugadores a equipos
   - Límite configurable de jugadores por equipo
   - Estadísticas individuales

4. **Simulación del Juego**
   - Simulación de intentos de lanzamiento
   - Sistema de puntuación configurable
   - Detección automática de victoria

5. **Configuración**
   - Parámetros personalizables del juego
   - Dificultad ajustable
   - Persistencia de configuración

## Estructura del Proyecto

```
src/
├── co/edu/udistrital/
│   ├── controller/
│   │   ├── Launcher.java              # Punto de entrada principal
│   │   ├── JuegoDeLaArgolla.java      # Clase de inicialización
│   │   ├── GameManager.java           # Controlador principal
│   │   ├── IControladorJuego.java     # Interfaz del controlador
│   │   ├── GestorEquipos.java         # Gestión de equipos
│   │   ├── GestorJugadores.java       # Gestión de jugadores
│   │   └── GestorPartidas.java        # Gestión de partidas
│   ├── model/
│   │   ├── ConfiguracionJuego.java    # Configuración del juego
│   │   ├── Equipo.java                # Modelo de equipo
│   │   ├── Jugador.java               # Modelo de jugador
│   │   └── Partida.java               # Modelo de partida
│   ├── view/
│   │   ├── VistaPrincipal.java        # Ventana principal
│   │   ├── DialogoConfiguracion.java  # Diálogo de configuración
│   │   └── DialogoGestionEquipos.java # Diálogo de gestión
│   └── persistence/
│       └── PersistenciaManager.java   # Gestión de archivos
```

## Cambios en la Versión 2.0

### Correcciones Implementadas
1. **Estructura del Launcher**: Movido al paquete controller y simplificado siguiendo el patrón solicitado
2. **Flujo del Juego**: Corregido el problema de pantallas en blanco al iniciar partidas
3. **Gestión de Equipos y Jugadores**: Implementada funcionalidad completa para agregar equipos y jugadores desde la interfaz
4. **Simulación del Juego**: Implementado sistema de simulación de intentos con registro de puntuación
5. **Actualización de Autores**: Agregado "And_Gar03" como autor de las modificaciones

### Mejoras en la Arquitectura
- **Principio de Responsabilidad Única (SRP)**: Cada clase tiene una responsabilidad específica
- **Inversión de Dependencias (DIP)**: La vista depende de la interfaz del controlador, no de la implementación concreta
- **Delegación de Eventos**: Los métodos actionPerformed en la vista son mínimos y delegan al controlador
- **Separación de Responsabilidades**: Clara separación entre modelo, vista y controlador

## Cómo Ejecutar

### Prerrequisitos
- Java 8 o superior
- NetBeans IDE (recomendado)

### Compilación y Ejecución
```bash
# Compilar el proyecto
javac -cp "src" -d "build/classes" src/co/edu/udistrital/controller/Launcher.java

# Ejecutar el juego
java -cp "build/classes" co.edu.udistrital.controller.Launcher
```

### Desde NetBeans
1. Abrir el proyecto en NetBeans
2. Ejecutar la clase `Launcher` en el paquete `co.edu.udistrital.controller`

## Instrucciones de Uso

1. **Iniciar el Juego**: Al ejecutar la aplicación, se mostrará la ventana principal
2. **Configurar**: Opcionalmente, configurar parámetros del juego
3. **Nueva Partida**: Crear una nueva partida
4. **Agregar Equipos**: Agregar equipos con nombre y color
5. **Agregar Jugadores**: Agregar jugadores a cada equipo
6. **Iniciar Partida**: Iniciar la partida cuando esté lista
7. **Simular Intentos**: Usar el botón "Simular Intento" para simular lanzamientos
8. **Ver Estadísticas**: Las estadísticas se actualizan en tiempo real

## Archivos de Datos
- **Configuración**: `Specs/data/configuracion.properties`
- **Partidas**: `Specs/data/partidas/`
- **Estadísticas**: `Specs/data/estadisticas/`

## Principios de Diseño Aplicados

### SOLID
- **S** - Single Responsibility Principle: Cada clase tiene una responsabilidad única
- **O** - Open/Closed Principle: Extensible sin modificar código existente
- **L** - Liskov Substitution Principle: Uso de polimorfismo sin instanceof
- **I** - Interface Segregation Principle: Interfaces específicas y cohesivas
- **D** - Dependency Inversion Principle: Dependencias sobre abstracciones

### Patrones de Diseño
- **MVC**: Separación clara entre Modelo, Vista y Controlador
- **Inyección de Dependencias**: El controlador se inyecta en la vista
- **Delegación**: La vista delega toda la lógica al controlador

## Archivos de Datos
- **Configuración**: `Specs/data/configuracion.properties`
- **Partidas**: `Specs/data/partidas/`
- **Estadísticas**: `Specs/data/estadisticas/`
- **Equipos**: `Specs/data/equipos.dat` (archivo de acceso aleatorio)
- **Jugadores**: `Specs/data/jugadores.dat` (archivo de acceso aleatorio)

## Pruebas JUnit
El proyecto incluye un conjunto completo de pruebas unitarias para todas las clases del controlador:

- **GameManagerTest**: 20 pruebas
- **GestorEquiposTest**: 18 pruebas
- **GestorJugadoresTest**: 20 pruebas
- **GestorPartidasTest**: 20 pruebas

**Total**: 78 pruebas unitarias

### Ejecutar Pruebas
```bash
# Compilar las pruebas
javac -cp "src:test" test/co/edu/udistrital/controller/*Test.java

# Ejecutar las pruebas (requiere JUnit 5)
java -cp "build/classes:test" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## Documentación
- **Análisis de Pruebas**: `Docs/AnalisisPruebas.md`
- **Diagrama de Clases**: `Specs/DiagramaClases.txt`
- **Integrantes**: `Docs/integrantes.txt`

## Cumplimiento de Requerimientos

### ✅ Arquitectura MVC Estricta
- **Modelo**: Sin dependencias de Swing, lógica de negocio pura
- **Vista**: Solo interfaz gráfica, delegación al controlador
- **Controlador**: Orquestación del flujo, uso de gestores especializados

### ✅ Principios SOLID
- **SRP**: Cada clase tiene una responsabilidad única
- **OCP**: Extensible sin modificar código existente
- **LSP**: Polimorfismo sin instanceof
- **ISP**: Interfaces específicas y cohesivas
- **DIP**: Dependencias sobre abstracciones

### ✅ Persistencia
- **Archivos de Acceso Aleatorio**: Implementados para equipos y jugadores
- **Serialización**: Partidas guardadas en archivos .dat
- **Configuración**: Archivos .properties
- **Carga Automática**: Datos cargados al iniciar el programa

### ✅ Pruebas JUnit
- **Cobertura Completa**: Todas las clases del controlador
- **Anotaciones**: @BeforeAll, @AfterAll, @BeforeEach, @AfterEach
- **Casos de Prueba**: Válidos, nulos, vacíos, límite, negocio
- **Documentación**: Análisis detallado de resultados

### ✅ Interfaz Gráfica
- **Separación de Responsabilidades**: Vista independiente del controlador
- **Delegación de Eventos**: Métodos actionPerformed mínimos
- **Botones Visibles**: Todos los botones correctamente dispuestos
- **Flujo Completo**: Desde configuración hasta simulación

### ✅ Documentación
- **JavaDoc**: Todos los elementos documentados
- **Estándares**: Convenciones de código Java
- **Análisis**: Documento de resultados de pruebas
- **Diagrama**: UML de clases completo

## Versiones
- **v1.0**: Versión inicial del sistema
- **v2.0**: Correcciones y mejoras implementadas por And_Gar03
  - ✅ Interfaz corregida con todos los botones visibles
  - ✅ Persistencia con archivos de acceso aleatorio
  - ✅ Serialización/deserialización automática
  - ✅ Pruebas JUnit completas (78 pruebas)
  - ✅ Documentación exhaustiva
  - ✅ Principios SOLID aplicados
  - ✅ Arquitectura MVC estricta