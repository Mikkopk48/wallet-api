# Mapa de datos

## Entidades

### NombreEntidad

| Campo   | Tipo    | Obligatorio | Restricción                  | Regla |
|---------|---------|-------------|------------------------------|-------|
| Cliente | Entidad | SI          | Tiene un identificador único | RN-XX |

(repetir por cada entidad)

## Relaciones

| Entidad A | Relación            | Entidad B | Cardinalidad          | ¿Quién guarda la referencia? |
|-----------|---------------------|-----------|-----------------------|------------------------------|
|           | tiene / pertenece a |           | 1 a 1 / 1 a N / N a N |                              |

## Diagrama

![Modelo de datos](data-model.drawio.svg)