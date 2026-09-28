# Registro de errores – Billetera Clara API

## E-001 – Título corto del problema

- **Fecha:** AAAA-MM-DD
- **Etapa:** (ej. Etapa 2 – Arrancar la aplicación)
- **Estado:** Resuelto | Pendiente

### Qué intentaba hacer

Una frase: la acción concreta que ejecuté.

### Qué esperaba que pasara

### Qué pasó realmente

### Mensaje de error

(Pega el PRIMER error relevante, no solo la última línea.
Borra contraseñas, tokens o datos personales antes de guardarlo.)

    pegar aquí el error

### Hipótesis probadas

| # | Hipótesis (qué creo que causa el error) | Cómo la probé | Resultado               |
|---|-----------------------------------------|---------------|-------------------------|
| 1 |                                         |               | Descartada / Confirmada |
| 2 |                                         |               |                         |

### Causa real

### Solución aplicada

### Por qué la solución funciona

(Con mis palabras. Si no puedo explicarlo, el estado sigue "Pendiente".)

### Cómo lo detectaría más rápido la próxima vez

## E-00X – Puerto ocupado al arrancar

- **Fecha:** AAAA-MM-DD
- **Etapa:** Etapa 2
- **Estado:** Resuelto

### Síntoma (cómo lo reconozco)

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug'
enabled.
2026-09-26T18:38:40.778-03:00 ERROR 2253 --- [wallet-api] [main] o.s.b.d.LoggingFailureAnalysisReporter   :

***************************
APPLICATION FAILED TO START
***************************

Description:

Web server failed to start. Port 8081 was already in use.

Action:

Identify and stop the process that's listening on port 8081 or configure this application to listen on another port.

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.219 s
[INFO] Finished at: 2026-09-26T18:38:40-03:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:4.1.1:run (default-cli) on project
wallet-api: Process terminated with exit code: 1 -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException

### Causa

(Con tus palabras: ¿qué significa que un puerto esté "ocupado"?
¿Por qué dos programas no pueden escuchar en el mismo puerto?)
Porque el sistema operativo administra los puertos y normalmente, no permite que dos procesos hagan bind al mismo puerto
y direccion IP al mismo tiempo. Esto evita que una conexión entrante sea ambigua: el sistema operativo necesita saber
que proceso debe entregarla.

### Cómo lo diagnostico

1. Leer el bloque "APPLICATION FAILED TO START" en los logs.
2. Ver qué proceso usa el puerto: `lsof -i :8080`
3. Decidir: ¿ese proceso es otra instancia mía o un programa ajeno?

### Soluciones

- **Opción A – Detener el proceso:** cuándo conviene y cómo.
- **Opción B – Cambiar el puerto:** cuándo conviene y dónde se configura.

### Hipótesis que probé

| # | Hipótesis                              | Cómo la probé           | Resultado |
|---|----------------------------------------|-------------------------|-----------|
| 1 | Hay otra instancia de mi app corriendo | lsof / revisar IntelliJ |           |

### Cómo evitarlo

(Ej.: revisar que no quede una instancia abierta antes de volver a arrancar.)
