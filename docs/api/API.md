# Catálogo de operaciones

| Operación                         | Método HTTP     | Ruta              |
|-----------------------------------|-----------------|-------------------|
| Registrar cliente                 | GET             | /clients/register |
| Consultar cliente                 | GET             | /client/{id}      |
| Crear billetera                   | POST            | /wallets          |
| Consultar billetera               | GET             | /wallet/{id}      |
| Depositar                         | PATH(no seguro) | /wallet           |
| Retirar                           | PATH            | /wallet/{id}      |
| Transferir                        | PATH            | /wallet/{id}      |
| Consultar historial (con filtros) | GET             | /wallet           | 