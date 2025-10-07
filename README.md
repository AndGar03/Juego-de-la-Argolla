# Juego de la Argolla - Versión 3.0

## Descripción
Aplicación de escritorio en Java Swing para el "Juego de la Argolla", con arquitectura MVC estricta y principios SOLID.

## Autores
- **Sansantax** — Versión 3.0
- **And_Gar03** — Coautor

## Estructura del Proyecto (paquetes reales)

```
src/
└── udistrital/avanzada/argolla/
    ├── control/
    │   ├── Launcher.java            # Punto de entrada
    │   ├── JuegoDeLaArgolla.java    # Inicialización del juego
    │   ├── GameManager.java         # Controlador principal
    │   ├── IControladorJuego.java   # Contrato del controlador
    │   ├── GestorEquipos.java       # Gestión de equipos
    │   ├── GestorJugadores.java     # Gestión de jugadores
    │   ├── GestorPartidas.java      # Gestión de partidas
    │   ├── PersistenciaManager.java # Configuración/properties
    │   └── ArchivoAccesoAleatorio.java # Acceso aleatorio (equipos/jugadores)
    ├── modelo/
    │   ├── ConfiguracionJuego.java
    │   ├── Equipo.java
    │   ├── Jugador.java
    │   └── Partida.java
    └── vista/
        ├── VistaPrincipal.java
        ├── DialogoConfiguracion.java
        └── DialogoGestionEquipos.java
```

## Requisitos
- Java 8 o superior
- Ant/NetBeans (opcional, proyecto incluye `build.xml`)

## Compilar y ejecutar

### Opción A: Ant (recomendado)
```bash
ant clean jar
ant run
```

### Opción B: Línea de comandos (javac/java)
```bash
# 1) Compilar todas las clases del src
javac -d build/classes -cp src $(Get-ChildItem -Recurse -Filter *.java src | % { $_.FullName })

# 2) Ejecutar el launcher
java -cp build/classes udistrital.avanzada.argolla.control.Launcher
```
En shells POSIX, sustituye la expansión de archivos por `find`/`xargs`.

### Opción C: NetBeans/IDE
1. Abrir el proyecto.
2. Ejecutar la clase `udistrital.avanzada.argolla.control.Launcher`.

## Instrucciones de uso (flujo UI)
1. Abrir la app: se muestra `VistaPrincipal`.
2. Configurar parámetros en `DialogoConfiguracion` (opcional).
3. Crear nueva partida.
4. Agregar equipos y jugadores.
5. Iniciar partida y simular intentos.
6. Consultar estadísticas en tiempo real.

## Datos y persistencia
- Configuración: `Specs/data/configuracion.properties`
- Partidas serializadas: `Specs/data/partidas/`
- Estadísticas: `Specs/data/estadisticas/`
- Archivos de acceso aleatorio:
  - `Specs/data/equipos.dat`
  - `Specs/data/jugadores.dat`

## Pruebas
Ubicación: `test/udistrital/avanzada/argolla/control/`

```bash
# Compilar código de producción y pruebas (requiere tener JUnit si se ejecuta fuera del IDE)
javac -d build/test-classes -cp build/classes;src;test $(Get-ChildItem -Recurse -Filter *.java test | % { $_.FullName })

# Ejecutar con el runner provisto (si existe) o con el runner del IDE
# Ejemplo con JUnit Console (si se dispone del jar en libs):
# java -jar junit-platform-console-standalone.jar -cp build/classes;build/test-classes --scan-classpath
```

Consulta `Docs/AnalisisPruebas.md` y `Docs/test_analysis.txt` para detalles.

## Diseño y buenas prácticas
- MVC estricto: modelo sin dependencias de Swing; vista sin lógica de negocio.
- SOLID: SRP, OCP, LSP, ISP, DIP aplicados.
- Inyección de dependencias desde `Launcher`/`JuegoDeLaArgolla`.

## Créditos y versiones
- v1.0: versión inicial.
- v2.0: mejoras de arquitectura y UI.
- v3.0: consolidación, persistencia por acceso aleatorio y documentación.