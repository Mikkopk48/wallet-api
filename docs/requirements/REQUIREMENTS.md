# Reglas de negocio – Matriz de trazabilidad

Estados: ⬜ Pendiente → 🟨 Diseñada → 🟧 Implementada → ✅ Probada

# Reglas de negocio – Matriz de trazabilidad

Estados: ⬜ Pendiente → 🟨 Diseñada → 🟧 Implementada → ✅ Probada

| ID    | Regla                                                     | Escenario     | Dónde se aplica | Prueba que lo demuestra | Estado |
|-------|-----------------------------------------------------------|---------------|-----------------|-------------------------|--------|
| RN-01 | Documento y email son únicos por cliente                  | A02           |                 |                         | ⬜     |
| RN-02 | Cliente inactivo no recibe billetera ni opera             | —             |                 |                         | ⬜     |
| RN-03 | Máximo una billetera activa por cliente                   | A04           |                 |                         | ⬜     |
| RN-04 | Saldo inicial cero, no lo fija quien crea                 | A03           |                 |                         | ⬜     |
| RN-05 | Moneda ARS; evitar mezclar monedas                        | A03           |                 |                         | ⬜     |
| RN-06 | Monto > 0 y máximo 2 decimales                            | A06           |                 |                         | ⬜     |
| RN-07 | Un retiro no deja saldo negativo                          | A08           |                 |                         | ⬜     |
| RN-08 | Transferencia: origen ≠ destino                           | A10           |                 |                         | ⬜     |
| RN-09 | Transferencia: ambas existen, activas, fondos suficientes | A09           |                 |                         | ⬜     |
| RN-10 | Transferencia exitosa = 2 movimientos con ID común        | A09           |                 |                         | ⬜     |
| RN-11 | Operación rechazada no genera movimientos                 | A06, A08, A11 |                 |                         | ⬜     |
| RN-12 | Fecha y hora las decide el servidor                       | —             |                 |                         | ⬜     |
| RN-13 | Un movimiento no se modifica ni elimina                   | —             |                 |                         | ⬜     |
| RN-14 | Historial: más reciente primero                           | A12           |                 |                         | ⬜     |
| RN-15 | Saldo resultante del movimiento = saldo real posterior    | A05, A07      |                 |                         | ⬜     |