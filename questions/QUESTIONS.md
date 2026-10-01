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

Organizar por funcionalidades es más fácil en un proyecto grande mientras que organizar por funcionalidades es mucho mas
facil para entrender la estructura del proyecto a la hora de leerlo
• Por qué la clase de arranque suele ubicarse por encima de los demás paquetes.
Porque la ubicación de la clase de arranque determina, por defecto, desde donde Spring empieza a buscar componentes
La clase de arranque suele ubicarse en el paquete raíz para que el component scanning pueda descubrir automáticamente
todos los componentes de la aplicación.

### Preguntas de control Etapa 3

1 ¿Dónde viviría la regla de fondos suficientes y por qué?
Service
2 ¿Quién debería saber que la comunicación usa HTTP?
Controller
3 ¿Quién debería saber cómo consultar la base de datos?
Repository
4 ¿Qué síntoma indica que una clase tiene demasiadas responsabilidades?
No puedes describir qué hace en una sola frase sin usar "y". Por ejemplo: "valida el cliente y calcula saldos y envía
emails…".
Cambia por muchos motivos distintos. Si la tocas cuando cambia una regla de dinero, cuando cambia el formato del email y
cuando cambia la base de datos, tiene varias responsabilidades.
Recibe muchas dependencias en el constructor.
Sus pruebas necesitan mucha preparación para probar una sola cosa.

### Etapa 4 Modelar el dominio

• Diferencia entre entidad, objeto de valor y objeto de transferencia.
Entidad:Un objeto que tiene identidad única que permanece aunque cambie sus atributos.
Tiene un identificado único eso hace que 2 dos entidades puedan tener los datos igualen, pero aun asi se diferencien.
Representa una cosa que necesita ser distinguida individualmente mediante una identidad propia

Objeto de valor (Value Object): Un objeto de valor no tiene identidad propia. Lo que importa es su valor. Se identifica
porque tiene valor.
Dos Value Object que tiene el mismo valor son iguales independientemente de que sean instancias diferentes en memoria.
Un Value Object es un objeto de dominio que representa un concepto mediante sus atributos, carece de identidad propia y
se considera igual a otro objeto cuando ambos representan el mismo valor. Normalmente, es inmutable y puede encapsular
comportamiento relacionado con el valor que representa.
Objeto de transferencia (DTO): Su objetivo es transportar información entre diferentes partes de la aplicación su
objetivo principal no es tener lógica de negocio.
Un dto está hecho para transporter información no para representar el comportamiento del dominio.
Su principal objetivo es separar la estructura interna de la aplicación de lo que se expone o recibe externamente.

• Por qué el dinero requiere precisión decimal exacta.
En un sistema bancario serio necesitamos hacer cálculos y transacciones que sean muy precisas
porque en java tenemos el valor double pero al hacer un calculo con este hay un pequeño margen de error porque double no
almacena números decimales directamente. Los almacena utilizando binario y muchos números decimales y muchos números no
pueden representarse con una cantidad finita de bits. Lo que hace que al hacer cálculos con un valor este acabe
modificándose lo que a la larga en un sistema bancario crearía problemas muy grandes en nuestro sistema porque los
valores están alterados.  
• Diferencia entre instante global y fecha y hora sin zona.
Instante global (Instant):Representa un instante exacto en la línea temporal, independientemente de donde esté la
persona, Fecha Tiempo Lugar (año/mes/día/ hora / lugar) tiene mucha precision y es lo mas adecuado para algo como las
transacciones que una misma hora puede cambiar mucho dependiendo del lugar.
Fecha y hora sin zona (LocalDateTime):Representa el momento exacto, pero sin el lugar, puede ser adecuado para ciertos
momentos en los que el lugar no determine una variable importante. Siendo otro caso se podria usar ZonedDateTime que es
simple pero especifica el lugar

• Ventajas y costos de identificadores numéricos y universales.
ID Numéricos: pequeños, rápido y simple, pero normalmente require de coordination para generarlo
Universales (UUID/Universally Unique Identifier):grande y menos eficiente, pero puede generarse de forma descentralizada
y es adecuado para sistemas distribuidos.
• Qué significan cardinalidad, propiedad de la relación y carga diferida.
Cardinalidad:Cuantas instancias de una entidad pueden estar relacionadas con una instancia de otra entidad:
@OneToOne → uno a uno | Persona → DNI
@OneToMany → uno a muchos | Clientes ⇒ Cuentas
@ManyToOne → uno a muchos | Clientes ⇒ Cuentas
@ManyToMany → muchos a muchos | Alumno ⇒ Curso
Propiedad de la relación:responde a la pregunta -> Qué entidad es la reponsable de guardar/mantener la relación en la
base de datos?
Una entidad puede ser propietaria de otra:
Cliente → lado inverso
Cuenta → lado propietario
La propiedad es un concepto de JPA, pero se configura mediante anotaciones en el código.
mappedBy = "cliente" =>La relación NO la controlo yo. La relación está definida por el atributo cliente
Carga diferida:No cargar todos los datos hasta que realmente los necesites
fetch = FetchType.LAZY = "Cárgalo cuando lo necesite."
fetch = FetchType.EAGER = "Cárgalo inmediatamente junto con la entidad."
• Qué implica que un movimiento sea inmutable desde el negocio.
Las reglas de negocio de la aplicación no permiten modificar un movimiento una vez registrado.
Todo queda registrado en el sistema y la historia no puede ser modificada.
hechos financieros registrados no se editan ni se borran como si fueran datos normales.
No reescribir el pasado;
registrar nuevos hechos que expliquen lo que ocurrió después.

Preguntas de control
1 ¿Por qué el saldo no debería ser un número de punto flotante binario?
Por que las operaciones con doble no dan un resultado exacto y tienen cierto margen de error en los decimales.
2 ¿Qué diferencia existe entre identificar un movimiento y agrupar los dos lados de una transferencia?
La diferencia radica en:
ID movimiento =>Cuál es este movimiento?
ID transferencia => A que operacion pertenece este movimiento?
Esto se hace porque una transferencia es una sola operacion de negocio, pero produce dos efectos contables distintos.
La transferencia es el "evento" y los movimientos son las "consecuencias" financieras del evento.
3 ¿Quién puede modificar el saldo y bajo qué condiciones?
En un sistema bien diseñado el saldo no debería ser modificado arbitrariamente por cualquier parte del sistema. En
nuestro sistema debemos preguntas quien tiene autoridad y que condiciones debe cumplir.
No diseñes el saldo como algo que cualquier parte del programa puede cambiar. Diseñá operaciones financieras que, cuando
son válidas, producen entradas que cambian el balance.

4 ¿Necesitas almacenar una lista de movimientos dentro de la billetera para poder consultarlos?
No, en la base de datos puede haber una entidad que sean los movimientos que ocurrieron
Billetera ────── Movimiento
1                    N
La FK normalmente vive en Movimiento.
5 ¿Qué zona horaria usarás internamente y cómo la explicarás?
Instant y DateTime
