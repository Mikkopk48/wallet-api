# Estructura planeada

## NombreClase

- **Paquete:**
- **Qué es:** una frase.
- **Datos que guarda:**
- **Qué hace:**
- **Qué NO hace:**
- **Estado:** Planeada / Creada / Descartada
# Estructura planeada

## Client
- **Paquete:** (Etapa 3)
- **Qué es:** Una persona registrada que puede tener una billetera.
- **Datos que guarda:** id, fullName, documentNumber, email, createdAt, status
- **Qué hace:** (Etapa 4)
- **Qué NO hace:** No modifica saldos ni accede a billeteras de otros clientes.
- **Estado:** Planeada

## Wallet
- **Paquete:** (Etapa 3)
- **Qué es:** La cuenta de dinero de un cliente, en una única moneda (ARS).
- **Datos que guarda:** id, owner, currency, balance, status, createdAt
- **Qué hace:** (Etapa 4)
- **Qué NO hace:** No permite que su saldo se cambie libremente desde afuera;
  el saldo solo cambia a través de una operación válida (depósito, retiro o transferencia).
- **Estado:** Planeada

## Movement
- **Paquete:** (Etapa 3)
- **Qué es:** El registro histórico de un cambio de saldo en una billetera.
- **Datos que guarda:** id, wallet, type, amount, description, createdAt,
  resultingBalance, operationId
- **Qué hace:** (Etapa 4)
- **Qué NO hace:** Nunca se modifica ni se elimina una vez creado.
- **Estado:** Planeada

## Transfer
- **Paquete:** (Etapa 3)
- **Qué es:** El envío de dinero de una billetera a otra como una sola operación indivisible.
- **Datos que guarda:** originWallet, destinationWallet, amount, reference, operationId
- **Qué hace:** (Etapa 4)
- **Qué NO hace:** No mueve dinero entre la misma billetera ni queda completada a medias.
- **Estado:** Por decidir → ¿clase propia o solo dos Movement unidos por operationId? (D-XXX)