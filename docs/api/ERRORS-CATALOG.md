# Catálogo de errores

| Categoría            | Ejemplo concreto                       | Estado HTTP | Mensaje público | ¿Hay efectos? |
|----------------------|----------------------------------------|-------------|-----------------|---------------|
| Datos inválidos      | Monto negativo                         |             |                 | No            |
| Recurso inexistente  | Billetera que no existe                |             |                 | No            |
| Conflicto de negocio | Email duplicado / fondos insuficientes |             |                 | No            |
| Error inesperado     | Falla la base de datos                 |             |                 | No            |

## Formato uniforme de error

Campos que tendrá toda respuesta de error: (Etapa 9: "identificador temporal,
estado, categoría, mensaje, ruta y detalles de campos cuando existan")