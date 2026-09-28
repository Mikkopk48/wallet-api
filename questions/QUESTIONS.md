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

___

### Investigar:

• Qué es el método de entrada de Java y qué devuelve el arranque de Spring.
El método de entrada de java es main ya que la JVM al hacer run lo primero que busca es donde está main.
Pero en springboot tenemos: SpringApplication.run (WalletApiApplication.class, args); y lo que ocurre es que main llama
a SpringApplication.run.
Y spring devuelve el ApplicationContext que es el contendor de Spring que administra los objetos/beans.
• Qué es el contexto de aplicación.
Contexto == Contenedor de Spring == ApplicationContext
Es el encargado de crear, guardar, configurar y conectar los objectos que forman tu aplicación
Analogía de restaurante:
ApplicationContext -> El restaurante y su sistema de organización
Beans -> Los empleados y recursos del restaurante
Spring -> El gerente que los crea, configura y coordina.
Dependency Injection -> El gerente le entrega a cada empleado las herramientas/personas que necesita.
• Qué significa servidor web integrado.
Un servidor web integrado en Spring Boot significa que el servidor web viene incluido dentro de tu aplicación, en lugar
de tener que instalar y configurar un servidor web por separado.
No ejecutaste:tomcat start ni instalaste un Tomcat independiente.
Tomcat es el componente que recibe las solicitudes HTTP.
• Qué es un perfil de configuración.
Un perfil de configuración es una forma de decirle a spring "Usa esta configuración dependiendo del entorno en el que
estoy ejecutando la aplicación" ahí aparecen los perfiles como:
aplication.properties
aplication-dev.properties
aplication-prod.properties
y al arrancar haces algo como mvn spring-boot:run -Dspring-boot.run.profiles=dev para arrancar el perfil dev o mediante
una variable de entorno SPRING_PROFILES_ACTIVE=dev.
En tus logs apacere algo como No active profile set, falling back to 1 default profile: "default" en este caso por
defecto
• Cómo distinguir advertencias de errores en los registros.
Al compilar una app de spring tenemos diferentes log:
INFO
↓
"Te estoy contando qué estoy haciendo."

WARN
↓
"Algo merece atención, pero puedo continuar."

ERROR
↓
"Tengo un problema."

FATAL
↓
"Problema crítico."
En Spring Boot normalmente FATAL no es un nivel que vas a ver habitualmente con la configuración estándar.

### Preguntas de control Etapa 2

1 ¿Qué parte pertenece a Java y qué parte agrega Spring Boot?
┌──────────────────────────────────────┐
│ WalletApiApplication │
│ │
│ Java │
│ ├── public class │
│ ├── public static void main ()       │
│ ├── String[] args │
│ └── sintaxis Java │
│ │
│ Spring Boot │
│ ├── @SpringBootApplication │
│ └── SpringApplication.run (...)      │
└──────────────────────────────────────┘
2 ¿Por qué puede existir un servidor aunque todavía no hayas creado una ruta?
No necesitas rotas para tener un servidor pero si necesitas un servidor para tener rutas.
si mandamos una ruta al servidor y dice "404 not found" el servidor respondió, pero la ruta no está creada.
3 ¿Qué diferencia hay entre que la aplicación compile y que arranque correctamente?
Compilar = comprobar que tu código puede convertirse en bytecode ejecutable -> javac
Arrancar = ejecutar ese bytecode y conseguir que toda la aplicación se inicialice correctamente -> JVM
4 ¿Qué revisarías primero si el puerto ya está ocupado?
Lo que haría sería ver quien lo esta usando lsof -i :8080 lo mas probable es que sea otra app springboot

### Investiga Etapa 3

• Qué significan arquitectura por capas, cohesión y acoplamiento.
Significa dividir una aplicación en diferentes niveles, donde cada uno tiene una responsabilidad concreta.
En sprinboot:
Controller:Recibir peticiones
Service:Ejecutar la lógica de negocio
Repository:Comunicarse con la base de datos
Database:Almacenar los datos
Principios
Arquitectura por capas: separa el sistema en responsabilidades.
Cohesión: intenta que cada capa/clase tenga responsabilidades fuertemente relacionadas.
Acoplamiento: intenta que las capas/clases dependan lo menos posible unas de otra

• Qué responsabilidad distingue a un controlador, un servicio y un repositorio.
Controller:Recibir peticiones
Service:Ejecutar la lógica de negocio
Repository:Comunicarse con la base de datos
Database:Almacenar los datos
• Qué es una dependencia circular y por qué dificulta el diseño.
Cuando 2 o mas componentes dependen unos de otros formando un círculo.
Que A necesite de B, pero B necesite de A. Esto hace que haya un acoplamiento en el sistema.
• Diferencia entre organizar paquetes por capa y por funcionalidad.
La diferencia está en el criterio que usás para agrupar las clases.
Si tuviera una app bancaria con Account|Client|Transfer
Por Capa:
controller/accoutController/clientController/transferController
service/accoutService/clientService/transferService
repository/accoutRepository/clientRepository/transferRepository
Por Funcionalidades:
account/accoutController/accoutService/accoutRepository
account/clientController/clientService/clientRepository
account/transferController/transferService/transferRepository

Organizar por funcionalidades es mas facil en un proyecto grande mientras que organizar por funcionalidades es mucho mas facil para entrender la estructura del proyecto a la hora de leerlo
• Por qué la clase de arranque suele ubicarse por encima de los demás paquetes.
Porque la ubicación de la clase de arranque determina, por defecto, desde donde Spring empieza a buscar componentes
La clase de arranque suele ubicarse en el paquete raíz para que el component scanning pueda descubrir automáticamente todos los componentes de la aplicación.

### Preguntas de control Etapa 3
1 ¿Dónde viviría la regla de fondos suficientes y por qué?
Service
2 ¿Quién debería saber que la comunicación usa HTTP?
Controller
3 ¿Quién debería saber cómo consultar la base de datos?
Repository
4 ¿Qué síntoma indica que una clase tiene demasiadas responsabilidades?
No puedes describir qué hace en una sola frase sin usar "y". Por ejemplo: "valida el cliente y calcula saldos y envía emails…".
Cambia por muchos motivos distintos. Si la tocas cuando cambia una regla de dinero, cuando cambia el formato del email y cuando cambia la base de datos, tiene varias responsabilidades.
Recibe muchas dependencias en el constructor.
Sus pruebas necesitan mucha preparación para probar una sola cosa.

