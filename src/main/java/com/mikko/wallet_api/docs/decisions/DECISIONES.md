# Diario de decisiones – Billetera Clara API

Registro de las decisiones técnicas del proyecto: qué elegí, por qué,
y qué alternativas descarté.

## Índice

| ID    | Fecha      | Etapa | Decisión (resumen)       | Estado  |
|-------|------------|-------|--------------------------|---------|
| D-001 | AAAA-MM-DD | 1     | Generar el proyecto base | Vigente |

---

- **Fecha:** 2026-SEP-22
- **Etapa:** (Etapa 1 – Preparar el entorno)
- **Categoría:** Herramienta
- **Estado:** Vigente

### Contexto

Al generar el proyecto en Spring Initializr tuve que elegir qué
bibliotecas incluir desde el inicio.

### Decisión

| Dependencia       | Para qué la necesito (con mis palabras)                                           | Requisito del PDF que cubre        |
|-------------------|-----------------------------------------------------------------------------------|------------------------------------|
| Spring Web        | Proporciona herramientas para construir la capa web                               | Hacer consultas desde el navegador |
| Validation        | Sirve para hacer validaciones de los datos que entran por los JSON                |                                    |
| Spring Data JPA   | Facilita trabajar con Bases de Datos Relacionales usando Objetos y Repositorios   |                                    |
| H2 Database       | Base de Datos en local que permite testear la aplicacion con datos no permanentes |                                    |
| PostgreSQL Driver |                                                                                   |                                    |

### Alternativas descartadas y por qué

- **Agregar más dependencias desde el inicio (Lombok, Security, DevTools…):**
  las descarté porque no quiero complejidad innecesaria en un punto tan inicial del desarrollo
- **Usar solo H2 o solo PostgreSQL:**
  Porque H2 me permite hacer test rapidos mientras que postgres probablemente sea
  la base de datos oficial para el proyecto

### Consecuencias / costos

¿Qué gano?
No tener complejidad innecesaria en herramientas que nos son esenciales en este punto del desarrollo, y no tener que
agregar depencias que podrian ser eliminadas posteriormente quitandome tiempo en el dessarrollo del producto
¿Qué pierdo o qué riesgo acepto? No tener la facilidad luego de que todo ya esté funcionando con las dependencias
completas, me arriesgo a tener problemas con el código ya escrito y las nuevas dependencias
¿Qué tendría que cambiar si más adelante esta decisión resulta incorrecta?
No lo sé

### Dudas pendientes

No conozco perfectamente el funcionamiento de cada dependencia de manera que no estoy seguro si cumplirán completamente
con los requerimientos de las funcionalidades