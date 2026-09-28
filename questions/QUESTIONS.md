#### 1 ¿Por qué instalar solo un entorno de ejecución no alcanza para desarrollar?

El JRE (Java Runtime Environment) está destinado a ejecutar aplicaciones Java ya compiladas. No proporciona las
herramientas necesarias para desarrollar, como el compilador `javac`.
Para desarrollar necesitamos el JDK (Java Development Kit), que incluye las herramientas de desarrollo y el entorno de
ejecución. Por ejemplo, `javac` transforma los archivos `.java`, que contienen código fuente, en archivos `.class` que
contienen bytecode. Luego, la JVM se encarga de ejecutar ese bytecode.

#### 2 ¿Qué tarea realiza Maven que no debería realizar manualmente el IDE?

Maven automatiza la construcción y gestión del proyecto Java. Entre otras cosas, administra las dependencias declaradas
en el `pom.xml`, resuelve sus dependencias transitivas y descarga las versiones necesarias.

Además, permite compilar, ejecutar tests y empaquetar la aplicación de forma reproducible e independiente del IDE. El
IDE puede interactuar con Maven, pero la configuración real del proyecto no debería depender exclusivamente del IDE.

#### 3 ¿Por qué el cliente HTTP no forma parte de la aplicación?

El cliente HTTP no forma parte de la aplicación backend porque es un componente externo que consume la API. Puede ser un
navegador, una aplicación móvil, otro backend, Postman, etc.

El cliente se encarga de enviar peticiones HTTP y recibir respuestas, mientras que el backend se encarga de procesar
esas peticiones, aplicar la lógica de negocio, validar los datos y, cuando sea necesario, comunicarse con una base de
datos u otros servicios.

Por lo tanto, el cliente y el backend son componentes separados que se comunican mediante HTTP.

#### 4 ¿Qué datos no deberían subirse nunca al repositorio?

Nunca deberían subirse al repositorio credencial o secretos como contraseñas, API keys, tokens, claves privadas,
credenciales de bases de datos o archivos `.env` que contengan información sensible.
Estos datos deberían gestionarse mediante variables de entorno, gestores de secretos o mecanismos de configuración
externos. El código fuente sí puede estar en el repositorio, pero los secretos necesarios para ejecutarlo no deberían
estar expuestos allí.


___


1 ¿Quién descargará las bibliotecas que seleccionaste?
Maven es la herramienta de build que, a partir de las dependencias declaradas en pom.xml, las resuelve, descarga desde
los repositorios configurados y las incorpora al classpath del proyecto junto con sus dependencias transitivas.
2 ¿Qué diferencia conceptual hay entre Spring y Spring Boot?
Spring es un ecosistema/framework de Java que proporciona infraestructura para desarrollar aplicaciones, incluyendo
IoC/Dependency Injection, Spring MVC, acceso a datos, seguridad, transacciones, etc.
Spring Boot está construido sobre Spring y simplifica su configuración y puesta en marcha mediante autoconfiguración,
starters, configuración convencional y servidores embebidos.
3 ¿Qué consecuencias tiene elegir un nombre de paquete que no controlas?
El nombre del paquete debería basarse normalmente en un dominio que controlemos, porque los paquetes funcionan como
namespaces y ayudan a evitar colisiones con otros proyectos. Además, en Spring Boot la ubicación del paquete raíz
influye en el component scanning, por lo que una estructura incorrecta puede impedir que Spring encuentre determinados
componentes.
4 ¿Por qué conviene comenzar con pocas dependencias?
Conviene comenzar con las dependencias estrictamente necesarias para reducir la complejidad, el acoplamiento y la
superficie de configuración del proyecto. Las dependencias adicionales pueden introducir configuraciones automáticas,
dependencias transitivas, vulnerabilidades y conflictos de versiones que no necesitamos todavía.

### Explícame por qué es un error:

- Abrir una carpeta interna en vez de la raíz:
  Porque si abris solamente src/main/java IntelliJ cree que simplemente estás abriendo una carpeta con código java, no
  el proyecto Maven completo.
- Modificar archivo Maven mientras todavía está importando:
  Si lo haces puede aparecer “Modify Maven file while it is being imported” esto pasa porque maven interpreto que
  modificaste el pom.xml mientras él todavía está sincronizando/importando. Porque si modificas el proyecto en ese
  momento maven tiene dos procesos ejecutándose al mismo tiempo
- Usar una versión de Java incompatible:
  nosotros tenemos una version de java en

  `Project SDK → 21 Esta en File → Project Structure → Project → SDK:21
  Maven JDK → 21 Settings → Build,Execution, Deployment → Build Tools → Maven → JDK for importer
  Run/Debug JDK → 21 → <java.version>21</java.version>`
  Además esta: `<maven.compiler.source>21</maven.compiler.source>
  <maven.compiler.target>21</maven.compiler.target>`
  Idealmente en las 3 versiones debería ser la misma