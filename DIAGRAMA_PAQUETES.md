# Diagrama de Paquetes - Aplicación Opemay

## Estructura General del Proyecto

```
┌─────────────────────────────────────────────────────────────────┐
│                      co.edu.uniajc.estudiante.opemay            │
│                         (Paquete Raíz)                         │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                OpemayApplication                        │    │
│  │            @SpringBootApplication                      │    │
│  │         [Patrón: Singleton - Spring Boot]              │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                    config                               │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │            FirebaseInitializer                 │    │    │
│  │  │               @Service                         │    │    │
│  │  │    [Patrón: Factory + Singleton]               │    │    │
│  │  │    [Patrón: Configuration Object]              │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                   security                              │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │            SecurityConfig                      │    │    │
│  │  │            @Configuration                      │    │    │
│  │  │    [Patrón: Strategy + Chain of Responsibility]│    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │           FirebaseTokenFilter                  │    │    │
│  │  │             @Component                         │    │    │
│  │  │    [Patrón: Filter/Interceptor]                │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │           FirebaseAuthService                  │    │    │
│  │  │              @Service                          │    │    │
│  │  │    [Patrón: Facade + Service Layer]           │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                    model                                │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │                Product                         │    │    │
│  │  │                @Data                           │    │    │
│  │  │    [Patrón: Data Transfer Object (DTO)]        │    │    │
│  │  │    [Patrón: JavaBean/POJO]                     │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │                 User                           │    │    │
│  │  │                @Data                           │    │    │
│  │  │    [Patrón: Data Transfer Object (DTO)]        │    │    │
│  │  │    [Patrón: Value Object]                      │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                 restController                          │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │            ProductController                   │    │    │
│  │  │            @RestController                     │    │    │
│  │  │    [Patrón: MVC - Controller]                  │    │    │
│  │  │    [Patrón: RESTful API]                       │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │             AuthController                     │    │    │
│  │  │            @RestController                     │    │    │
│  │  │    [Patrón: MVC - Controller]                  │    │    │
│  │  │    [Patrón: Authentication Gateway]            │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                   Service                               │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │            ProductService                      │    │    │
│  │  │               @Service                         │    │    │
│  │  │    [Patrón: Service Layer]                     │    │    │
│  │  │    [Patrón: Business Logic Layer]              │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │             UserService                        │    │    │
│  │  │               @Service                         │    │    │
│  │  │    [Patrón: Service Layer]                     │    │    │
│  │  │    [Patrón: Domain Service]                    │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                 IRespository                            │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │           ProductRepository                    │    │    │
│  │  │             @Repository                        │    │    │
│  │  │    [Patrón: Repository]                        │    │    │
│  │  │    [Patrón: Data Access Object (DAO)]          │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  │  ┌─────────────────────────────────────────────────┐    │    │
│  │  │            UserRepository                      │    │    │
│  │  │             @Repository                        │    │    │
│  │  │    [Patrón: Repository]                        │    │    │
│  │  │    [Patrón: Data Access Object (DAO)]          │    │    │
│  │  └─────────────────────────────────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                 db.migration                            │    │
│  │                 (Vacío - NoSQL)                         │    │
│  └─────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────┘
```

## Patrones de Diseño Utilizados

### 🏗️ **Patrones Arquitectónicos**

1. **MVC (Model-View-Controller)**
   - **Controller**: ProductController, AuthController
   - **Model**: Product, User (entidades)
   - **View**: Frontend (React/Angular) - no incluido en backend

2. **Layered Architecture (Arquitectura en Capas)**
   - **Presentation Layer**: restController
   - **Business Layer**: Service
   - **Data Access Layer**: IRespository
   - **Infrastructure Layer**: config

3. **RESTful API**
   - Endpoints REST estándar
   - HTTP verbs (GET, POST, PUT, DELETE)
   - Resource-based URLs

### 🔧 **Patrones de Creación**

4. **Singleton**
   - Spring Boot Application Context
   - Firebase beans (@Bean)
   - Service beans (@Service)

5. **Factory Pattern**
   - FirebaseInitializer crea instancias de Firestore
   - Spring IoC Container como Factory

6. **Dependency Injection**
   - Constructor injection en todos los componentes
   - @Autowired implícito con constructores

### 🔄 **Patrones de Comportamiento**

7. **Strategy Pattern**
   - SecurityConfig para diferentes estrategias de autenticación
   - Diferentes implementaciones de autenticación

8. **Chain of Responsibility**
   - Spring Security Filter Chain
   - FirebaseTokenFilter en la cadena

9. **Template Method**
   - Spring Boot auto-configuration
   - JPA Repository methods

10. **Observer Pattern**
    - Spring Events y Listeners
    - Application lifecycle events

### 🏛️ **Patrones Empresariales**

11. **Repository Pattern**
    - ProductRepository, UserRepository
    - Abstracción del acceso a datos

12. **Service Layer Pattern**
    - ProductService, UserService
    - Encapsulación de lógica de negocio

13. **Data Transfer Object (DTO)**
    - Product, User como DTOs
    - Transferencia de datos entre capas

14. **Facade Pattern**
    - FirebaseAuthService como facade para Firebase Auth
    - Controllers como facade para services

### 🛡️ **Patrones de Seguridad**

15. **Filter/Interceptor Pattern**
    - FirebaseTokenFilter
    - Intercepta requests para validar tokens

16. **Authentication Gateway**
    - AuthController centraliza autenticación
    - Punto único de entrada para auth

17. **Role-Based Access Control (RBAC)**
    - @PreAuthorize con roles
    - Autorización basada en roles

### 🗂️ **Patrones de Datos**

18. **Data Access Object (DAO)**
    - Repository classes
    - Abstracción del acceso a Firestore

19. **Value Object**
    - User como value object inmutable
    - Product attributes

20. **Configuration Object**
    - FirebaseInitializer
    - Centraliza configuración

### 📦 **Patrones de Integración**

21. **Adapter Pattern**
    - Firebase SDK adapters
    - Spring Security adapters

22. **Proxy Pattern**
    - Spring AOP proxies
    - Transaction management

### 🔍 **Beneficios de los Patrones Utilizados**

- **Mantenibilidad**: Separación clara de responsabilidades
- **Escalabilidad**: Arquitectura modular y desacoplada
- **Testabilidad**: Dependency injection facilita testing
- **Reutilización**: Componentes reutilizables
- **Flexibilidad**: Fácil intercambio de implementaciones
- **Seguridad**: Patrones de seguridad robustos
