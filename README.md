# Proyecto Base Implementando Clean Architecture


## Antes de Iniciar Nuestro Recorrido

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)


# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)



## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.



## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.



## Infrastructure


### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**



### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.


### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.



## Application


Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**

---

## Descripción 

**muebles-sas** es una aplicación backend desarrollada con Spring WebFlux que utiliza DynamoDB como base de datos NoSQL.  
El proyecto sigue principios sólidos de Clean Architecture y está diseñado para ser reactivo, escalable y eficiente.



## Desarrollador

| Nombre         | Albieri Alaña                                                                                 |
|----------------|-----------------------------------------------------------------------------------------------|
| Rol            | Desarrollador Backend Ssr                                                                     |
| Contacto       | [Albieri Alaña - LinkedIn](https://www.linkedin.com/in/albieri-maximiliano-ala%C3%B1a-reyes/) 
|



## Características principales

- Backend reactivo con **Spring WebFlux**
- Base de datos NoSQL usando **Amazon DynamoDB**
- Arquitectura limpia (Clean Architecture)
- Integración con Docker para levantar DynamoDB y RabbitMQ localmente
- Código modular y fácil de extender



## Tecnologías y dependencias

| Tecnología / Framework             | Versión                                 |
|----------------------------------|-----------------------------------------|
| Java                             | 21                                      |
| Spring Boot                      | 3.5.0                                   |
| Spring WebFlux                   | Incluido en Spring Boot 3.5.0           |
| AWS SDK DynamoDB Async Client    | Versión recomendada AWS SDK 2.x         |
| Docker                          | Para levantar DynamoDB y RabbitMQ local |
| JUnit 5                         | Para pruebas unitarias                  |
| Gradle                          | 8.14.2                                  |



## Requisitos previos

- Java 21+ instalado
- Docker instalado y corriendo
- AWS CLI configurado
- Git instalado

---

## Cómo desplegar el proyecto
Sigue estos pasos para preparar y ejecutar el proyecto correctamente:


### 1. Clonar repositorio

```bash
git clone https://github.com/Albieri151/muebles-sas-prueba-tecnica.git
cd muebles-sas-prueba-tecnica
```

---

### 2. Desplegar el docker compose

Desde la raiz del proyecto, abre la terminal ejecuta los siguientes comandos

Primero, desde la raíz del proyecto, entra al folder `deployment`:

```bash
cd deployment
docker compose up
```
---

### 3. Crear la tabla DynamoDB manualmente con AWS CLI

Para que la aplicación funcione, debes crear la tabla `Stat` en DynamoDB local. Ejecuta este comando en tu terminal:

```bash
aws dynamodb create-table \
  --table-name Stat \
  --attribute-definitions AttributeName=timestamp,AttributeType=S \
  --key-schema AttributeName=timestamp,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  --endpoint-url http://localhost:8000 \
  --region us-east-1
```

## Tips 
Para correr las pruebas en el proyecto debe ejecutar el siguiente scrip en la terminal, el mismo ejecuta todos los test de la app, hay algunos que fallara por detalles de que el proyecto esta preconfigurado

```bash
./gradlew test
```

## Ejemplo de request http
El ejemplo puede correrlo en postman.
Actualmente solo esta en funcionamiento este endpoint, la direccion dependera desde donde ejecute el proyecto y su puerto, este es un ejemplo a fines practicos y didacticos.

```bash
POST http://localhost:8080/stats

{
    "totalContactoClientes": 250,
    "motivoReclamo": 25,
    "motivoGarantia": 10,
    "motivoDuda": 100,
    "motivoCompra": 100,
    "motivoFelicitaciones": 7,
    "motivoCambio": 2,
    "hash": "da3c9ed32fe3754bc72194e9a40cf062"
}
```

## Fin
Con esto solo queda pendiente ejecutar el proyecto en su ide de preferencia, muchas gracias por estar aca, si tienen dudas alli esta mi contacto, sera un gusto ayudarles! 🔥🔥