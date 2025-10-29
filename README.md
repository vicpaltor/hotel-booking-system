# 🏨 Sistema de Reservas de Hotel (Hotel Booking System)

Este es el plan de desarrollo y la definición de alcance para la construcción de un sistema de gestión y reservas de hoteles, siguiendo una metodología de **Desarrollo Guiado por Pruebas (TDD)** e implementado con **Spring Boot**.

## 🌟 Funcionalidades Principales

El sistema incluirá las siguientes capacidades clave:

* **Gestión de Hoteles:** Mantenimiento de la información detallada del hotel (nombre, ubicación, estrellas, servicios).

* **Gestión de Habitaciones:** Definición de tipos de habitación, capacidad, precio por noche y gestión de disponibilidad.

* **Gestión de Clientes:** Registro, perfiles de usuario e historial de reservas.

* **Sistema de Reservas:** Creación, modificación y cancelación de reservas.

* **Disponibilidad:** Consulta rápida de habitaciones disponibles por rango de fechas.

* **Pagos:** Registro de transacciones de pago y sus estados.

* **Reseñas:** Sistema de calificaciones y comentarios para huéspedes.

## 🏗️ Entidades Principales (Modelo de Datos)

Las entidades fundamentales del sistema son:

| **Entidad** | **Campos Clave** | **Relación** | 
| ----- | ----- | ----- | 
| **Hotel** | `id`, `nombre`, `dirección`, `ciudad`, `país`, `estrellas`, `descripción` | Base | 
| **Room** | `id`, `hotelId`, `número`, `tipo (SINGLE, DOUBLE, SUITE)`, `capacidad`, `precio` | Múltiples `Room` por un `Hotel` | 
| **Guest** | `id`, `nombre`, `email`, `teléfono`, `documento` | Base | 
| **Booking** | `id`, `roomId`, `guestId`, `checkIn`, `checkOut`, `estado`, `precioTotal` | Liga `Room` y `Guest` | 
| **Payment** | `id`, `bookingId`, `monto`, `método`, `estado`, `fecha` | Múltiples `Payment` por un `Booking` | 
| **Review** | `id`, `hotelId`, `guestId`, `calificación`, `comentario` | Liga `Hotel` y `Guest` | 

## 🛡️ Roles de Seguridad

Para garantizar la correcta segregación de tareas, se definirán los siguientes roles de seguridad:

* **ADMIN:** Gestión completa del sistema.

* **HOTEL_MANAGER:** Gestión de sus hoteles y habitaciones.

* **GUEST:** Ver disponibilidad, hacer reservas, dejar reseñas.

## 📋 Plan de Desarrollo Paso a Paso (Metodología TDD)

El desarrollo se guiará por etapas incrementales, priorizando la implementación basada en pruebas (TDD).

### Fase 1: Setup y Configuración ⚙️

✅ 1. Crear proyecto Spring Boot.

✅ 2. Configurar dependencias clave (e.g., Spring Data JPA, H2/PostgreSQL, Spring Web).

✅ 3. Definir la estructura de paquetes inicial (`model`, `repository`, `service`, `controller`).

### Fase 2: Módulo de Hoteles 🏨

✅ 1. Implementar la **Entidad `Hotel`**.

2. Crear `Repository`, `Service` y `Controller` para `Hotel`.

3. Escribir **Tests completos** para el CRUD básico del `Hotel`.

### Fase 3: Módulo de Habitaciones 🛏️

1. Implementar la **Entidad `Room`** incluyendo la relación con `Hotel`.

2. Crear servicios y controladores.

3. Implementar lógica de **búsqueda y filtrado** de habitaciones por fechas, tipo y precio.

4. Escribir tests.

### Fase 4: Módulo de Huéspedes 👤

1. Implementar la **Entidad `Guest`**.

2. Crear el servicio de gestión de clientes (Registro, perfil, historial).

3. Implementar una **Autenticación básica** inicial (por ejemplo, manejo de sesiones).

4. Escribir tests.

### Fase 5: Sistema de Reservas 📅 (Core del Negocio)

1. Implementar la **Entidad `Booking`**.

2. Desarrollar la **Lógica de disponibilidad** (verificación de conflictos de fechas).

3. Implementar el **CRUD de Reservas**.

4. Añadir **Validaciones de fechas** (`checkIn` anterior a `checkOut`, no reservas en el pasado).

5. Escribir tests de disponibilidad y reserva.

### Fase 6: Seguridad Avanzada 🔐

1. Integrar **Spring Security** para aplicar los **Roles** definidos.

2. Implementar **JWT** (JSON Web Tokens) para la autenticación y autorización sin estado (opcional).

### Fase 7: Features Adicionales ⭐

1. Implementar las entidades y lógica para **Pagos** (`Payment`) y sus estados.

2. Implementar el sistema de **Reseñas** (`Review`) (calificaciones y comentarios).

3. Desarrollar una **Búsqueda avanzada** para huéspedes (filtrado por servicios, estrellas, etc.).


# 📂 Estructura de Paquetes (`com.hotel.booking`)

Esta es la organización modular del proyecto, siguiendo principios de **Separación de Responsabilidades** para facilitar el desarrollo y mantenimiento.

com.hotel.booking
├── BookingSystemApplication.java  // Clase principal de Spring Boot
├── config/                        // Configuraciones generales (Security, DataSources, etc.)
├── domain/                        // **Capa del Dominio** (Modelos de Negocio/Entidades JPA)
│   ├── hotel/                     // Entidad Hotel y lógica asociada
│   ├── room/                      // Entidad Room y lógica asociada
│   ├── guest/                     // Entidad Guest y lógica asociada
│   ├── booking/                   // Entidad Booking y lógica de fechas/disponibilidad
│   ├── payment/                   // Entidad Payment
│   └── review/                    // Entidad Review
├── dto/                           // **Data Transfer Objects** para comunicación entre capas (entrada/salida de la API)
├── mapper/                        // Interfaces de MapStruct para conversiones entre DTOs y Entidades
├── repository/                    // **Capa de Persistencia** (Spring Data Repositories)
├── service/                       // **Capa de Servicios** (Lógica de Negocio Central, transacciones)
├── controller/                    // **Capa de Presentación** (REST Controllers, manejo de peticiones HTTP)
└── exception/                     // Clases y handlers personalizados para el manejo centralizado de errores

# 🧪 Paso C: Verificar configuración de H2

1. Ejecuta la aplicación nuevamente
2. Abre tu navegador en: http://localhost:8080/h2-console
En el login de H2, usa:

* JDBC URL: jdbc:h2:mem:hotelbookingdb
* User Name: sa
* Password: (dejar vacío)
* Click en Connect
