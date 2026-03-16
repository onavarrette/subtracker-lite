# 🚀 SubTracker Lite
[![Java CI with Maven and Docker](https://github.com/onavarrette/subtracker-lite/actions/workflows/maven.yml/badge.svg)](https://github.com/onavarrette/subtracker-lite/actions/workflows/maven.yml)

SubTracker Lite es una API REST moderna desarrollada con **Java 25** y **Spring Boot 4.0.3**. El proyecto nace como un laboratorio personal para experimentar con las funcionalidades más recientes del framework, la gestión de contenedores nativa y la observabilidad.

## 🌟 Características Principales

- **Java 25 Ready**: Uso de *Records* para DTOs y aprovechamiento de las últimas mejoras del JDK.
- **Spring Boot 4.0.x**: Implementación de las nuevas APIs de prueba y configuración.
- **Docker Compose Support**: Gestión automática de la base de datos PostgreSQL durante el desarrollo.
- **Observabilidad con Actuator**: Monitoreo de salud y métricas mediante endpoints estándar.
- **Validación Robusta**: Implementación de Jakarta Validation con respuestas de error estandarizadas (RFC 7807).

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java 25
- **Framework:** Spring Boot 4.0.3
- **Base de Datos:** PostgreSQL
- **Persistencia:** Spring Data JPA
- **Contenedores:** Docker & Docker Compose
- **Pruebas:** JUnit 5, MockMvcTester (Spring 4 style), AssertJ

## 🚀 Cómo empezar

### Requisitos previos
* JDK 25 instalado.
* Docker Desktop o Docker Engine corriendo.
* Maven 3.9+ (o usa el `./mvnw` incluido).

### Instalación y Ejecución

1. **Clona el repositorio:**
   ```bash
   git clone [https://github.com/onavarrette/subtracker-lite.git](https://github.com/onavarrette/subtracker-lite.git)
   cd subtracker-lite

2.  **Ejecuta la aplicación:**
    Gracias al soporte nativo de Docker Compose en Spring Boot, la base de datos se levantará automáticamente al iniciar el contexto de la app:
    ```bash
    ./mvnw spring-boot:run
    ```

3.  **Verifica el estado:**
    Accede al endpoint de salud para confirmar la conexión con la DB: `http://localhost:8080/actuator/health`

---

## 📡 API Endpoints

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/subscriptions` | Lista todas las suscripciones registradas. |
| `POST` | `/api/subscriptions` | Registra una nueva suscripción (valida datos). |
| `GET` | `/api/subscriptions/total-cost` | Retorna la suma total de los costos mensuales. |

### Ejemplo de solicitud (Crear suscripción)
```bash
curl -X POST http://localhost:8080/api/subscriptions \
-H "Content-Type: application/json" \
-d '{
 "name": "Netflix",
 "price": 15.99,
 "renewalDate": "2026-04-01"
}'
```

## 🧪 Pruebas

Para ejecutar la suite de pruebas (incluyendo los tests de integración que utilizan contenedores temporales mediante Docker Compose):

```bash
./mvnw test
```

## 📝 Notas de Desarrollo

Este proyecto implementa cambios significativos propios de **Spring Boot 4.0**:

* **Testing Moderno**: Sustitución de `MockMvc` por el nuevo **`MockMvcTester`** para aserciones más legibles y fluidas.
* **Error Handling**: Configuración de `spring.mvc.problemdetails.enabled: true` para habilitar respuestas semánticas en caso de errores de validación.
* **Mocking**: Uso de la nueva anotación **`@MockitoBean`** para la inyección de mocks en el contexto de prueba, reemplazando la antigua `@MockBean`.

---
## 👤 Creado por

Desarrollado por [onavarrette](https://github.com/onavarrette) | 2026
