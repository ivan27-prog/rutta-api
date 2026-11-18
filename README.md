# Rutta | Equípate para avanzar

**Rutta** es una plataforma de comercio electrónico enfocada en equipamiento para senderismo, trail y hiking, desarrollada por el equipo **Trail Coders** como proyecto Java Full Stack de Generation México.

El proyecto nace de una necesidad: facilitar la elección de equipamiento adecuado para personas que comienzan a explorar actividades al aire libre.

Más allá de ofrecer productos, Rutta busca acompañar al usuario mediante información sobre niveles de experiencia, características del terreno y recomendaciones que le permitan tomar decisiones de compra informadas.

**[Explorar Rutta](https://rutta-virid.vercel.app/)** | **[Repositorio Backend](https://github.com/ivan27-prog/rutta-api)**

<img width="1877" height="833" alt="image" src="https://github.com/user-attachments/assets/975400aa-2e88-4e74-be1e-5149bb4abb87" />


## Funcionalidades principales

### Experiencia de usuario
- Catálogo de equipamiento organizado por categorías.
- Búsqueda y filtrado de productos.
- Fichas de producto con información sobre nivel, actividad y terreno recomendado.
- Carrito de compras con actualización de cantidades y totales.
- Registro e inicio de sesión con validación de formularios.
- Guías de senderismo y recomendaciones de aplicaciones para planificar rutas.

<img width="1917" height="842" alt="image" src="https://github.com/user-attachments/assets/562ea4a5-841f-4dc3-aa25-e28824461810" />

### Administración
- Registro de nuevos productos.
- Actualización de información y precios.
- Eliminación de productos.
- Integración con la API REST para gestionar la información almacenada.

### Ayuda y acompañamiento
- Sección de preguntas frecuentes.
- Formulario de contacto.
- **Rutilio**, mascota y asistente interactivo que facilita el acceso a guías, niveles de explorador e información sobre Rutta.

## Tecnologías utilizadas

| Área | Tecnologías |
|---|---|
| Frontend | HTML5, CSS3, JavaScript, Bootstrap |
| Backend | Java 21, Spring Boot, Spring Data JPA |
| Base de datos | MySQL, Hibernate |
| Herramientas | Git, GitHub, Gradle, IntelliJ IDEA, VS Code |
| Diseño y organización | Figma, Jira |
| Despliegue | Vercel, AWS |

## Arquitectura del proyecto

Rutta fue desarrollada siguiendo una arquitectura cliente-servidor, separando la interfaz de usuario, la lógica del backend y la persistencia de datos.

**Frontend → API REST → Backend → Base de datos MySQL**

El frontend permite a los usuarios interactuar con el catálogo y enviar solicitudes HTTP.

El backend, desarrollado con Java y Spring Boot, procesa estas solicitudes mediante controladores, servicios y repositorios.

Para la persistencia de datos utilizamos Spring Data JPA e Hibernate, facilitando el mapeo de entidades Java y sus relaciones con la base de datos.

## Modelo de datos

Diseñamos un modelo entidad-relación compuesto por ocho entidades principales:

- Product
- Category
- Description
- Color
- Size
- User
- Order
- OrderDetails

Este modelo permite organizar la información de los productos, sus características y las relaciones necesarias para las funcionalidades de la plataforma.

## Desarrollo colaborativo

Rutta fue construido durante ocho semanas mediante un proceso de desarrollo colaborativo.

El equipo participó en distintas etapas del ciclo de vida del software:

- Identificación del problema y definición del MVP.
- Diseño de interfaces y experiencia de usuario.
- Modelado de la base de datos.
- Desarrollo e integración de frontend y backend.
- Gestión de versiones mediante Git y GitHub.
- Organización de tareas y seguimiento con Jira.
- Pruebas, resolución de incidencias y despliegue.

## Próximas mejoras

Rutta continúa siendo un proyecto con oportunidades de crecimiento. Entre las funcionalidades contempladas para futuras versiones se encuentran:

- Fortalecimiento de la autenticación y seguridad.
- Implementación de pedidos y pagos reales.
- Ampliación del catálogo de productos.
- Incorporación de recomendaciones más personalizadas mediante Rutilio.
- Implementación de pruebas automatizadas.

## Equipo

**Trail Coders**

Proyecto desarrollado de manera colaborativa por ocho integrantes durante el programa de formación Java Full Stack de Generation México.

## Sobre el proyecto

Rutta representa la aplicación práctica de nuestros conocimientos en desarrollo Full Stack, integración de tecnologías, diseño de soluciones digitales y trabajo colaborativo.

Nuestro objetivo fue transformar una necesidad cotidiana en una experiencia digital que combinara comercio electrónico, orientación y accesibilidad para nuevos exploradores.

**Rutta — Equípate para avanzar.**
