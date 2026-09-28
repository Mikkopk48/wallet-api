# Diario de decisiones – Billetera Clara API

Registro de las decisiones técnicas del proyecto: qué elegí, por qué,
y qué alternativas descarté.

## Índice

| ID    | Fecha      | Etapa | Decisión (resumen)       | Estado  |
|-------|------------|-------|--------------------------|---------|
| D-001 | AAAA-MM-DD | 1     | Generar el proyecto base | Vigente |

---

- **Fecha:** 2026-SEP-22
- **Etapa:** (Etapa 0 – Preparar el entorno)
- **Categoría:** Herramienta
- **Estado:** Vigente

### Contexto

Al generar el proyecto en Spring Initializr tuve que elegir qué
bibliotecas incluir desde el inicio.

### Decisión

| Dependencia       | Para qué la necesito (con mis palabras)                                                                       |
|-------------------|---------------------------------------------------------------------------------------------------------------|
| Spring Web        | Proporciona herramientas para construir la capa web                                                           |
| Validation        | Sirve para hacer validaciones de los datos que entran por los JSON                                            |
| Spring Data JPA   | Facilita trabajar con Bases de Datos Relacionales usando Objetos y Repositorios                               |
| H2 Database       | Base de Datos en local que permite testear la aplicacion con datos no permanentes                             |
| PostgreSQL Driver | l conector JDBC que permite que tu aplicación Java se comunique directamente con una base de datos PostgreSQL |

### Alternativas descartadas y por qué

- **Agregar más dependencias desde el inicio (Lombok, Security, DevTools…):**
  las descarté porque no quiero complejidad innecesaria en un punto tan inicial del desarrollo
- **Usar solo H2 o solo PostgreSQL:**
  Porque H2 me permite hacer test rapidos mientras que postgres probablemente sea
  la base de datos oficial para el proyecto

### Consecuencias / costos

¿Qué gano?
No tener complejidad innecesaria en herramientas que nos son esenciales en este punto del desarrollo, y no tener que
agregar dependence que podrían ser eliminadas posteriormente quitándome tiempo en el desarrollo del producto
¿Qué pierdo o qué riesgo acepto? No tener la facilidad luego de que todo ya esté funcionando con las dependencias
completas, me arriesgo a tener problemas con el código ya escrito y las nuevas dependencias
¿Qué tendría que cambiar si más adelante esta decisión resulta incorrecta?
No lo sé

### Dudas pendientes

No conozco perfectamente el funcionamiento de cada dependencia de manera que no estoy seguro si cumplirán completamente
con los requerimientos de las funcionalidades

___ 

- **Fecha:** 2026-SEP-28
- **Etapa:** (Etapa 3 – Creación de las capas)
- **Categoría:** Capas
- **Estado:** Vigente

## D-002 – Nombres de los paquetes

### Contexto

Se agregaron las capas en la aplicación

### Opciones consideradas

1. Opción A – Agregar los paquetes todos en plural
2. Opción A – Agregar los paquetes todos en singular

### Decisión

Elegí **Opción B**.

### Por qué

- Para que todos los paquetes tengan concordancia con su nombre

### Alternativas descartadas y por qué

- **Opción A:** me parece más correcto

### Consecuencias / costos

- Gano una concordancia con todos los paquetes de la app
- No pierdo nada.

___

## D-003 – Organizar paquetes por capa técnica

- **Fecha:** 2026-09-28
- **Etapa:** Etapa 3
- **Categoría:** Estructura
- **Estado:** Vigente

### Contexto
Tenía que decidir cómo agrupar las clases en paquetes: por capa técnica
(web, service, repository...) o por funcionalidad de negocio (client, wallet...).

### Decisión
Organizar por **capa técnica**.

### Por qué
- Respuesta: Por que permite ver el flujo de la aplicacion de forma mas simple y de que partes esta hecha


### Alternativa descartada
- **Por funcionalidad:** la descarté por funcionalidad ya que no permite organizar

### Consecuencias / costos
- (Respuesta a: ¿qué pasaría si el proyecto creciera mucho?)