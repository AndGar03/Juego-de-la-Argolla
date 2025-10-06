# Juego de la Argolla

Una aplicación de escritorio desarrollada en Java que implementa el juego de la argolla siguiendo estrictamente los principios de arquitectura MVC y SOLID.

## 🎯 Características Principales

- **Arquitectura MVC Estricta**: Separación clara entre Modelo, Vista y Controlador
- **Principios SOLID**: Código limpio, mantenible y extensible
- **Inversión de Dependencias**: Uso de interfaces para desacoplar componentes
- **Gestión Completa**: Equipos, jugadores, partidas y estadísticas
- **Persistencia**: Guardado y carga de datos
- **Interfaz Gráfica**: Swing con diseño intuitivo

## 🏗️ Arquitectura del Proyecto

### Estructura de Paquetes

```
src/co/edu/udistrital/
├── model/           # Lógica de negocio pura
├── view/            # Interfaz gráfica (Swing)
├── controller/      # Lógica de control
├── persistence/     # Manejo de archivos
└── Launcher.java    # Punto de entrada
```

### Principios de Diseño Aplicados

1. **Arquitectura MVC Estricta**
   - Modelo: Sin dependencias de Swing
   - Vista: Solo componentes gráficos
   - Controlador: Orquesta el flujo de la aplicación

2. **Principios SOLID**
   - **SRP**: Cada clase tiene una responsabilidad única
   - **OCP**: Extensible sin modificar código existente
   - **LSP**: Polimorfismo sin `instanceof`
   - **ISP**: Interfaces específicas y cohesivas
   - **DIP**: Inversión de dependencias

3. **Patrones de Diseño**
   - MVC: Separación de responsabilidades
   - DIP: Inyección de dependencias
   - Gestores especializados

## 🚀 Funcionalidades

### Gestión de Equipos y Jugadores
- Crear y administrar equipos
- Agregar/remover jugadores
- Estadísticas por equipo

### Configuración del Juego
- Parámetros personalizables
- Dificultad ajustable
- Configuración persistente

### Simulación de Partidas
- Múltiples equipos
- Sistema de rondas
- Puntuación dinámica

### Persistencia de Datos
- Guardado automático
- Carga de partidas
- Configuración persistente

## 🛠️ Tecnologías Utilizadas

- **Java 8+**: Lenguaje de programación
- **Swing**: Interfaz gráfica
- **JUnit 5**: Pruebas unitarias
- **Serialización**: Persistencia de datos
- **Maven/Gradle**: Gestión de dependencias

## 📋 Requisitos del Sistema

- Java 8 o superior
- IDE compatible (NetBeans, IntelliJ, Eclipse)
- Sistema operativo: Windows, macOS, Linux

## 🔧 Instalación y Configuración

### 1. Clonar el Repositorio
```bash
git clone [url-del-repositorio]
cd Juego-de-la-Argolla
```

### 2. Compilar el Proyecto
```bash
javac -cp src src/co/edu/udistrital/Launcher.java
```

### 3. Ejecutar la Aplicación
```bash
java -cp src co.edu.udistrital.Launcher
```

## 🎮 Cómo Jugar

1. **Configurar el Juego**: Ajustar parámetros según preferencia
2. **Crear Equipos**: Agregar equipos con nombres y colores
3. **Agregar Jugadores**: Asignar jugadores a cada equipo
4. **Iniciar Partida**: Comenzar una nueva partida
5. **Registrar Intentos**: Simular lanzamientos de argolla
6. **Ver Estadísticas**: Monitorear progreso y resultados

## 🧪 Pruebas Unitarias

El proyecto incluye pruebas unitarias exhaustivas usando JUnit 5:

```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas específicas
mvn test -Dtest=GameManagerTest
```

### Cobertura de Pruebas
- ✅ Controladores principales
- ✅ Gestores especializados
- ✅ Lógica de negocio
- ✅ Casos edge y errores

## 📚 Documentación

### JavaDoc
Toda la documentación está disponible en formato JavaDoc:
```bash
javadoc -d docs -sourcepath src -subpackages co.edu.udistrital
```

### Estructura de Archivos
```
├── src/                    # Código fuente
├── test/                   # Pruebas unitarias
├── Specs/data/            # Archivos de datos
├── Docs/                   # Documentación
└── README.md              # Este archivo
```

## 🔍 Características Técnicas

### Arquitectura Limpia
- Separación clara de responsabilidades
- Código mantenible y extensible
- Principios SOLID aplicados

### Patrón MVC Estricto
- **Modelo**: Lógica de negocio pura
- **Vista**: Solo interfaz gráfica
- **Controlador**: Orquesta el flujo

### Inversión de Dependencias
- Uso de interfaces
- Inyección de dependencias
- Desacoplamiento de componentes

## 🤝 Contribuciones

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👥 Autores

- **Sistema Juego de la Argolla** - *Desarrollo completo* - [GitHub](https://github.com/usuario)

## 🙏 Agradecimientos

- Comunidad Java por las mejores prácticas
- Patrones de diseño por la arquitectura limpia
- Principios SOLID por el código mantenible

---

**Nota**: Este proyecto fue desarrollado como demostración de arquitectura de software limpia y principios de diseño orientado a objetos.