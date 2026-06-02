# WorkTrack API

API REST para gestión de incidencias y tareas en pequeñas y medianas empresas.

Mi intencion con esta API es sustituir la gestión informal por WhatsApp, correo o Excel que aun llevan algunas pymes por un sistema centralizado con trazabilidad, roles y métricas.

---

## Descripción

WorkTrack permite a las empresas:

- Gestionar usuarios y departamentos
- Crear y asignar proyectos
- Abrir, seguir y cerrar incidencias
- Registrar toda la actividad del sistema
- Obtener métricas básicas de productividad

---

## Tecnologías

| Tecnología | Versión | Para qué se usa |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.4.5 | Framework de la API |
| Spring Security | 6.x | Autenticación y autorización |
| JWT | - | Tokens de acceso y refresco |
| PostgreSQL | 16 | Base de datos principal |
| JPA / Hibernate | - | ORM y gestión de entidades |
| Flyway | - | Migraciones de base de datos |
| Docker + Compose | - | Contenerización del proyecto |
| Swagger / OpenAPI | - | Documentación de la API |
| JUnit 5 + Mockito | - | Testing unitario e integración |

---

## Arquitectura

El proyecto sigue una arquitectura modular con principios SOLID.
Cada módulo es autónomo y contiene sus propias capas.
```text
com.worktrack
│
├── auth/
├── user/
├── department/
├── project/
├── ticket/
├── comment/
├── activitylog/
│
├── shared/
├── config/
└── security/
```

Estructura interna de cada módulo:
```text
modulo/
├── controller/
├── service/
├── domain/
│   ├── model/
│   ├── enums/
│   └── repository/
├── infrastructure/
│   ├── persistence/
│   └── mapper/
└── dto/
```
---

## Roles

| Rol | Permisos |
|---|---|
| ADMIN | Gestión completa de usuarios, departamentos y métricas |
| MANAGER | Crear proyectos, asignar tareas y cambiar prioridades |
| EMPLOYEE | Crear incidencias, comentar y actualizar tareas |

---

## Endpoints principales

> Documentación completa disponible en `/swagger-ui` una vez arrancado el proyecto.

| Método | Endpoint | Descripción |
|---|---|---|
| POST | /auth/register | Registro de usuario |
| POST | /auth/login | Login y obtención de token |
| GET | /users | Listar usuarios |
| GET | /projects | Listar proyectos |
| GET | /tickets | Listar tickets |
| POST | /tickets | Crear ticket |
| PUT | /tickets/{id}/status | Cambiar estado de ticket |

---

## Cómo arrancar el proyecto

> Requisitos: Docker y Docker Compose instalados.

```bash
git clone https://github.com/illogons/Api-GestionDeIncidencias.git
cd Api-GestionDeIncidencias
docker compose up
```

La API estará disponible en `http://localhost:8080`

La documentación Swagger en `http://localhost:8080/swagger-ui`

---

## Estado del proyecto

| Fase | Estado |
|---|---|
| Análisis y planificación | ✅ Completado |
| Setup inicial | 🔄 En progreso |
| Autenticación JWT | ⏳ Pendiente |
| Gestión de usuarios | ⏳ Pendiente |
| Gestión de departamentos | ⏳ Pendiente |
| Gestión de proyectos | ⏳ Pendiente |
| Sistema de tickets | ⏳ Pendiente |
| Comentarios y auditoría | ⏳ Pendiente |
| Testing | ⏳ Pendiente |
| Docker completo | ⏳ Pendiente |
| Redis | ⏳ Pendiente |
| CI/CD GitHub Actions | ⏳ Pendiente |

---

## Autor

Desarrollado por **gvega**  
Proyecto de portfolio — Backend Junior Java / Spring Boot
