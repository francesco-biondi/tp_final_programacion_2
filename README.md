# One Piece: King of Java

Un juego clicker basado en One Piece desarrollado en Java utilizando JavaFX.

## Descripción

One Piece: King of Java es un juego de tipo clicker donde los jugadores toman el rol de Monkey D. Luffy en su aventura para convertirse en el Rey de los Piratas. Enfréntate a enemigos icónicos de la serie, mejora tus habilidades y recluta nakamas para tu tripulación.

## Características

- Sistema de combate clicker
- Múltiples enemigos de la serie One Piece
- Sistema de habilidades y mejoras
- Reclutamiento de nakamas
- Sistema de guardado y carga de partidas
- Música y efectos de sonido de la serie
- Interfaz gráfica inspirada en One Piece

## Requisitos del Sistema

- Java 22 o superior
- JavaFX 22.0.1
- Maven 3.13.0

## Instalación

1. Clona el repositorio
2. Navega al directorio del proyecto
3. Click derecho en App y seleccionar Run 'App.main()'

## Testing (En progreso)

El proyecto incluye una suite completa de pruebas unitarias utilizando JUnit 5. Las pruebas cubren:

### Modelos
- `AbilityTest`: Prueba las funcionalidades básicas de las habilidades
- `AttackAbilityTest`: Verifica el sistema de daño y cooldown
- `NakamaTest`: Prueba el sistema de nakamas y sus interacciones
- `ShopTest`: Valida las transacciones y mejoras de la tienda

### Sistema de Guardado
- `SaveManagerTest`: Verifica la gestión de archivos de guardado
- Validación de nombres de guardado
- Carga y guardado de partidas

Para ejecutar las pruebas:

1. Abrir carpeta test
2. Click derecho en java y seleccionar Run 'All Tests'

## Tecnologías Utilizadas

- Java
- JavaFX
- Maven
- GSON
- CSS

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/                 # Código fuente principal de la aplicación
│   │   └── game/
│   │       ├── models/       # Modelos y lógica de negocio
│   │       ├── scenes/       # Controladores de las distintas escenas del juego
│   │       └── services/     # Servicios auxiliares
│   └── resources/            # Recursos estáticos como imágenes y archivos de configuración
└── test/                     # Pruebas unitarias
    └── java/
        └── game/
```

## Controles

- Click izquierdo: Atacar al enemigo
- Botones de interfaz: Navegar por el juego y usar habilidades

## Vista Previa del Juego

<p align="center">
  <img src="/src/main/resources/assets/views/mainMenu.png" alt="Menú principal" width="400"/>
  <img src="/src/main/resources/assets/views/map.png" alt="Mapa" width="400"/>
  <img src="/src/main/resources/assets/views/battle.png" alt="Pantalla de combate" width="400"/>
</p>
