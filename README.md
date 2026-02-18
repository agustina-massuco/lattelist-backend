# ☕ LatteList - API Backend

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.x-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Spring Security](https://img.shields.io/badge/Security-JWT_RBAC-lightgrey)

Esta es la API REST de **LatteList**, encargada de la lógica de negocio, persistencia de datos y seguridad del sistema. Desarrollada bajo principios de Clean Code y arquitectura de capas.

## Funcionalidades Principales

* Autenticación y autorización mediante **JWT**
* Gestión de usuarios con estados (Activo, Inactivo, Desactivado, Eliminado)
* Roles y permisos (**CLIENTE / ADMIN**) con RBAC
* Sistema de reseñas con likes/dislikes y moderación
* Listas personalizadas de cafés
* Encriptación de contraseñas con BCrypt
* Integración con **Geoapify API**
* Estrategia de **Soft Delete** para usuarios y reseñas


## Stack Técnico
* **Lenguaje:** Java 17 (LTS).
* **Framework:** Spring Boot 3.x.
* **Persistencia:** Spring Data JPA + Hibernate.
* **Seguridad:** Spring Security 6 + JWT + BCrypt
* **Base de Datos:** MySQL.
* **Mensajería:**  JavaMailSender para recuperación de cuentas y notificaciones.
* **Integraciones:** Geoapify API (Geolocalización y POIs).
* **Gestión de dependencias:** Maven
* **Testing de API:** Postman


## Modelo de Estados

### Usuarios
- ACTIVO
- INACTIVO (Suspendido por admin)
- DESACTIVADO (Pausa voluntaria)
- ELIMINADO (Definitivo)

### Reseñas
- ACTIVO
- INACTIVO (Moderada por admin)
- ELIMINADO (Soft delete)


## Configuración y Ejecución

### Requisitos
- Java 17
- MySQL 8
- Maven


1. **Base de Datos:**
   Cree una base de datos en MySQL:
   ```sql
   CREATE DATABASE lattelist_db;

2.  **Variables de entorno:**
    Verifique el archivo y ajuste sus credenciales


#### src/main/resources/application.properties

### Database
    spring.datasource.url=jdbc:mysql://localhost:3306/lattelist_db                     
    spring.datasource.username=TU_USUARIO 
    spring.datasource.password=TU_PASSWORD 

### External APIs
    geoapify.api.key=TU_API_KEY 

### Mail Service

```md
   spring.mail.host=smtp.gmail.com 
   spring.mail.port= tu_codigo_port
   spring.mail.username=tu_correo@gmail.com
   spring.mail.password=tu_app_password
```

## Usuario Administrativo Inicial

```md

| Rol | Email | Contraseña |
|---|---|---|
| Super Admin | admin@lattelist.com | admin123 |
```

## Endpoints Principales

### Autenticación
- `POST /auth/login`
- `POST /auth/register`
- `POST /auth/forgot-password`
- `POST /auth/reset-password`

### Usuarios
- `GET /usuarios/{id}`
- `PUT /usuarios/me`
- `PATCH /usuarios/me/password`
- `GET /usuarios/listado` (ADMIN)
- `PATCH /usuarios/{id}/estado` (ADMIN)

### Cafés
- `GET /cafes`
- `GET /cafes/{id}`

### Reseñas
- `POST /reviews`
- `PUT /reviews/{id}`
- `DELETE /reviews/{id}`
- `PATCH /reviews/{id}/activar` (ADMIN)
- `PATCH /reviews/{id}/desactivar` (ADMIN)

### Listas
- `GET /listas/mis-listas`
- `POST /listas`
- `DELETE /listas/{id}`

##  Licencia
Proyecto académico – Tecnicatura Universitaria en Programación (UTN).

**Desarrollado por:**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Agustina_Massuco-0077B5?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/agustina-massuco/)

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Cecilia_Novelli-0077B5?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/cecilia-novelli-93a4bb247/)