# Apuntes y respuestas: Java y Spring Boot

## Entorno de desarrollo y proyecto

### 1. ¿Por qué instalar solo un entorno de ejecución no alcanza para desarrollar?

El JRE (*Java Runtime Environment*) está destinado a ejecutar aplicaciones Java ya compiladas. No proporciona las herramientas necesarias para desarrollar, como el compilador `javac`.

Para desarrollar necesitamos el JDK (*Java Development Kit*), que incluye las herramientas de desarrollo y el entorno de ejecución. Por ejemplo, `javac` transforma los archivos `.java`, que contienen código fuente, en archivos `.class` que contienen *bytecode*. Luego, la JVM se encarga de ejecutar ese *bytecode*.

### 2. ¿Qué tarea realiza Maven que no debería realizar manualmente el IDE?

Maven automatiza la construcción y gestión del proyecto Java. Entre otras cosas, administra las dependencias declaradas en el `pom.xml`, resuelve sus dependencias transitivas y descarga las versiones necesarias.

Además, permite compilar, ejecutar pruebas y empaquetar la aplicación de forma reproducible e independiente del IDE. El IDE puede interactuar con Maven, pero la configuración real del proyecto no debería depender exclusivamente del IDE.

### 3. ¿Por qué el cliente HTTP no forma parte de la aplicación?

El cliente HTTP no forma parte de la aplicación *backend* porque es un componente externo que consume la API. Puede ser un navegador, una aplicación móvil, otro *backend*, Postman, etc.

El cliente se encarga de enviar peticiones HTTP y recibir respuestas, mientras que el *backend* se encarga de procesar esas peticiones, aplicar la lógica de negocio, validar los datos y, cuando sea necesario, comunicarse con una base de datos u otros servicios.

Por lo tanto, el cliente y el *backend* son componentes separados que se comunican mediante HTTP.

### 4. ¿Qué datos no deberían subirse nunca al repositorio?

Nunca deberían subirse al repositorio credenciales o secretos como contraseñas, claves de API, *tokens*, claves privadas, credenciales de bases de datos o archivos `.env` que contengan información sensible.

Estos datos deberían gestionarse mediante variables de entorno, gestores de secretos o mecanismos de configuración externos. El código fuente sí puede estar en el repositorio, pero los secretos necesarios para ejecutarlo no deberían estar expuestos allí.

---

## Maven, Spring y estructura del proyecto

### 1. ¿Quién descargará las bibliotecas que seleccionaste?

Maven es la herramienta de *build* que, a partir de las dependencias declaradas en `pom.xml`, las resuelve, descarga desde los repositorios configurados y las incorpora al *classpath* del proyecto junto con sus dependencias transitivas.

### 2. ¿Qué diferencia conceptual hay entre Spring y Spring Boot?

Spring es un ecosistema o *framework* de Java que proporciona infraestructura para desarrollar aplicaciones, incluyendo IoC, inyección de dependencias, Spring MVC, acceso a datos, seguridad, transacciones, etc.

Spring Boot está construido sobre Spring y simplifica su configuración y puesta en marcha mediante autoconfiguración, *starters*, configuración convencional y servidores integrados.

### 3. ¿Qué consecuencias tiene elegir un nombre de paquete que no controlas?

El nombre del paquete debería basarse normalmente en un dominio que controlemos, porque los paquetes funcionan como *namespaces* y ayudan a evitar colisiones con otros proyectos. Además, en Spring Boot la ubicación del paquete raíz influye en el escaneo de componentes, por lo que una estructura incorrecta puede impedir que Spring encuentre determinados componentes.

### 4. ¿Por qué conviene comenzar con pocas dependencias?

Conviene comenzar con las dependencias estrictamente necesarias para reducir la complejidad, el acoplamiento y la superficie de configuración del proyecto. Las dependencias adicionales pueden introducir configuraciones automáticas, dependencias transitivas, vulnerabilidades y conflictos de versiones que no necesitamos todavía.

### Errores frecuentes

#### Abrir una carpeta interna en vez de la raíz

Si abrís solamente `src/main/java`, IntelliJ cree que simplemente estás abriendo una carpeta con código Java, no el proyecto Maven completo.

#### Modificar el archivo de Maven mientras todavía está importando

Si lo hacés, puede aparecer el mensaje “Modify Maven file while it is being imported”. Esto pasa porque Maven interpretó que modificaste el `pom.xml` mientras todavía estaba sincronizando o importando. Si modificás el proyecto en ese momento, Maven tiene dos procesos ejecutándose al mismo tiempo.

#### Usar una versión de Java incompatible

Tenemos una versión de Java configurada en los siguientes lugares:

```text
Project SDK → 21
File → Project Structure → Project → SDK: 21
Maven JDK → 21
Settings → Build, Execution, Deployment → Build Tools → Maven → JDK for importer
Run/Debug JDK → 21
<java.version>21</java.version>
```

Además, están configuradas estas propiedades:

```xml
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```

Idealmente, las tres versiones deberían ser la misma.

---

## Etapa 2: Arranque y configuración de Spring Boot

### Investigación

#### ¿Qué es el método de entrada de Java y qué devuelve el arranque de Spring?

El método de entrada de Java es `main`, ya que la JVM, al hacer *run*, lo primero que busca es dónde está `main`.

En Spring Boot tenemos:

```java
SpringApplication.run(WalletApiApplication.class, args);
```

Lo que ocurre es que `main` llama a `SpringApplication.run`.

Spring devuelve el `ApplicationContext`, que es el contenedor de Spring que administra los objetos o *beans*.

#### ¿Qué es el contexto de aplicación?

Contexto = contenedor de Spring = `ApplicationContext`.

Es el encargado de crear, guardar, configurar y conectar los objetos que forman tu aplicación.

**Analogía de restaurante:**

- `ApplicationContext`: el restaurante y su sistema de organización.
- *Beans*: los empleados y recursos del restaurante.
- Spring: el gerente que los crea, configura y coordina.
- Inyección de dependencias: el gerente le entrega a cada empleado las herramientas o personas que necesita.

#### ¿Qué significa servidor web integrado?

Un servidor web integrado en Spring Boot significa que el servidor web viene incluido dentro de tu aplicación, en lugar de tener que instalar y configurar un servidor web por separado.

No ejecutaste `tomcat start` ni instalaste un Tomcat independiente. Tomcat es el componente que recibe las solicitudes HTTP.

#### ¿Qué es un perfil de configuración?

Un perfil de configuración es una forma de decirle a Spring: “Usá esta configuración dependiendo del entorno en el que estoy ejecutando la aplicación”. Ahí aparecen perfiles como:

```text
application.properties
application-dev.properties
application-prod.properties
```

Al arrancar, podés hacer algo como:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

También podés hacerlo mediante una variable de entorno:

```bash
SPRING_PROFILES_ACTIVE=dev
```

En tus registros puede aparecer algo como: `No active profile set, falling back to 1 default profile: "default"`. En este caso, se usa el perfil predeterminado.

#### ¿Cómo distinguir advertencias de errores en los registros?

Al compilar una aplicación de Spring tenemos diferentes niveles de registro:

| Nivel | Significado |
| --- | --- |
| `INFO` | “Te estoy contando qué estoy haciendo”. |
| `WARN` | “Algo merece atención, pero puedo continuar”. |
| `ERROR` | “Tengo un problema”. |
| `FATAL` | “Problema crítico”. |

En Spring Boot, normalmente `FATAL` no es un nivel que vayas a ver habitualmente con la configuración estándar.

### Preguntas de control

#### 1. ¿Qué parte pertenece a Java y qué parte agrega Spring Boot?

```text
WalletApiApplication
│
├── Java
│   ├── public class
│   ├── public static void main()
│   ├── String[] args
│   └── Sintaxis Java
│
└── Spring Boot
    ├── @SpringBootApplication
    └── SpringApplication.run(...)
```

#### 2. ¿Por qué puede existir un servidor aunque todavía no hayas creado una ruta?

No necesitás rutas para tener un servidor, pero sí necesitás un servidor para tener rutas. Si mandamos una solicitud a una ruta del servidor y responde “404 Not Found”, el servidor respondió, pero la ruta no está creada.

#### 3. ¿Qué diferencia hay entre que la aplicación compile y que arranque correctamente?

- **Compilar:** comprobar que tu código puede convertirse en *bytecode* ejecutable mediante `javac`.
- **Arrancar:** ejecutar ese *bytecode* y conseguir que toda la aplicación se inicialice correctamente mediante la JVM.

#### 4. ¿Qué revisarías primero si el puerto ya está ocupado?

Lo primero que haría sería ver quién lo está usando con `lsof -i :8080`. Lo más probable es que sea otra aplicación de Spring Boot.

---

## Etapa 3: Arquitectura por capas

### Investigación

#### ¿Qué significan arquitectura por capas, cohesión y acoplamiento?

Significa dividir una aplicación en diferentes niveles, donde cada uno tiene una responsabilidad concreta.

En Spring Boot:

- **Controller:** recibe peticiones.
- **Service:** ejecuta la lógica de negocio.
- **Repository:** se comunica con la base de datos.
- **Database:** almacena los datos.

**Principios:**

- **Arquitectura por capas:** separa el sistema en responsabilidades.
- **Cohesión:** intenta que cada capa o clase tenga responsabilidades fuertemente relacionadas.
- **Acoplamiento:** intenta que las capas o clases dependan lo menos posible unas de otras.

#### ¿Qué responsabilidad distingue a un controlador, un servicio y un repositorio?

- **Controller:** recibe peticiones.
- **Service:** ejecuta la lógica de negocio.
- **Repository:** se comunica con la base de datos.
- **Database:** almacena los datos.

#### ¿Qué es una dependencia circular y por qué dificulta el diseño?

Ocurre cuando dos o más componentes dependen unos de otros formando un círculo: A necesita de B, pero B necesita de A. Esto genera acoplamiento en el sistema.

#### Diferencia entre organizar paquetes por capa y por funcionalidad

La diferencia está en el criterio que usás para agrupar las clases. Si tuviera una aplicación bancaria con `Account`, `Client` y `Transfer`:

**Por capa:**

```text
controller/
├── accountController
├── clientController
└── transferController

service/
├── accountService
├── clientService
└── transferService

repository/
├── accountRepository
├── clientRepository
└── transferRepository
```

**Por funcionalidad:**

```text
account/
├── accountController
├── accountService
└── accountRepository

client/
├── clientController
├── clientService
└── clientRepository

transfer/
├── transferController
├── transferService
└── transferRepository
```

Organizar por funcionalidades es más fácil en un proyecto grande, mientras que organizar por capas es mucho más fácil para entender la estructura del proyecto a la hora de leerlo.

#### ¿Por qué la clase de arranque suele ubicarse por encima de los demás paquetes?

Porque la ubicación de la clase de arranque determina, por defecto, desde dónde Spring empieza a buscar componentes. La clase de arranque suele ubicarse en el paquete raíz para que el escaneo de componentes pueda descubrir automáticamente todos los componentes de la aplicación.

### Preguntas de control

#### 1. ¿Dónde viviría la regla de fondos suficientes y por qué?

En `Service`.

#### 2. ¿Quién debería saber que la comunicación usa HTTP?

El `Controller`.

#### 3. ¿Quién debería saber cómo consultar la base de datos?

El `Repository`.

#### 4. ¿Qué síntoma indica que una clase tiene demasiadas responsabilidades?

- No podés describir qué hace en una sola frase sin usar “y”. Por ejemplo: “Valida el cliente, calcula saldos y envía correos…”.
- Cambia por muchos motivos distintos. Si la tocás cuando cambia una regla de dinero, cuando cambia el formato del correo y cuando cambia la base de datos, tiene varias responsabilidades.
- Recibe muchas dependencias en el constructor.
- Sus pruebas necesitan mucha preparación para probar una sola cosa.

---

## Etapa 4: Modelar el dominio

### Investigación

#### Diferencia entre entidad, objeto de valor y objeto de transferencia

**Entidad:** un objeto que tiene una identidad única que permanece aunque cambien sus atributos. Tiene un identificador único; esto hace que dos entidades puedan tener los mismos datos, pero aun así se diferencien. Representa una cosa que necesita ser distinguida individualmente mediante una identidad propia.

**Objeto de valor (*Value Object*):** no tiene identidad propia. Lo que importa es su valor. Dos objetos de valor que tienen el mismo valor son iguales independientemente de que sean instancias diferentes en memoria.

Un objeto de valor es un objeto de dominio que representa un concepto mediante sus atributos, carece de identidad propia y se considera igual a otro objeto cuando ambos representan el mismo valor. Normalmente es inmutable y puede encapsular comportamiento relacionado con el valor que representa.

**Objeto de transferencia (DTO):** su objetivo es transportar información entre diferentes partes de la aplicación; su objetivo principal no es tener lógica de negocio.

Un DTO está hecho para transportar información, no para representar el comportamiento del dominio. Su principal objetivo es separar la estructura interna de la aplicación de lo que se expone o recibe externamente.

#### ¿Por qué el dinero requiere precisión decimal exacta?

En un sistema bancario serio necesitamos hacer cálculos y transacciones que sean muy precisos. En Java tenemos el valor `double`, pero al hacer un cálculo con este hay un pequeño margen de error porque `double` no almacena números decimales directamente: los almacena utilizando binario y muchos números decimales no pueden representarse con una cantidad finita de bits.

Esto hace que, al realizar cálculos con un valor, este acabe modificándose. A la larga, en un sistema bancario crearía problemas muy grandes porque los valores estarían alterados.

#### Diferencia entre instante global y fecha y hora sin zona

**Instante global (`Instant`):** representa un instante exacto en la línea temporal, independientemente de dónde esté la persona: fecha, tiempo y lugar (año, mes, día, hora y lugar). Tiene mucha precisión y es lo más adecuado para algo como las transacciones, porque una misma hora puede cambiar mucho dependiendo del lugar.

**Fecha y hora sin zona (`LocalDateTime`):** representa el momento exacto, pero sin el lugar. Puede ser adecuado para ciertos momentos en los que el lugar no determine una variable importante. En otro caso se podría usar `ZonedDateTime`, que especifica el lugar.

#### Ventajas y costos de identificadores numéricos y universales

- **ID numéricos:** pequeños, rápidos y simples, pero normalmente requieren coordinación para generarlos.
- **Universales (UUID, *Universally Unique Identifier*):** grandes y menos eficientes, pero pueden generarse de forma descentralizada y son adecuados para sistemas distribuidos.

#### ¿Qué significan cardinalidad, propiedad de la relación y carga diferida?

**Cardinalidad:** cuántas instancias de una entidad pueden estar relacionadas con una instancia de otra entidad:

- `@OneToOne` → uno a uno; por ejemplo, persona → DNI.
- `@OneToMany` → uno a muchos; por ejemplo, cliente → cuentas.
- `@ManyToOne` → uno a muchos; por ejemplo, clientes → cuentas.
- `@ManyToMany` → muchos a muchos; por ejemplo, alumno → curso.

**Propiedad de la relación:** responde a la pregunta: “¿Qué entidad es la responsable de guardar o mantener la relación en la base de datos?”.

Una entidad puede ser propietaria de otra:

- Cliente → lado inverso.
- Cuenta → lado propietario. 

La propiedad es un concepto de JPA, pero se configura mediante anotaciones en el código.

```java
mappedBy = "cliente"
```

Esto indica: “La relación no la controlo yo. La relación está definida por el atributo `cliente`”.

**Carga diferida:** no cargar todos los datos hasta que realmente los necesites.

```java
fetch = FetchType.LAZY  // "Cárgalo cuando lo necesite."
fetch = FetchType.EAGER // "Cárgalo inmediatamente junto con la entidad."
```

#### ¿Qué implica que un movimiento sea inmutable desde el negocio?

Las reglas de negocio de la aplicación no permiten modificar un movimiento una vez registrado. Todo queda registrado en el sistema y la historia no puede ser modificada.

Los hechos financieros registrados no se editan ni se borran como si fueran datos normales. No se reescribe el pasado; se registran nuevos hechos que expliquen lo que ocurrió después.

### Preguntas de control

#### 1. ¿Por qué el saldo no debería ser un número de punto flotante binario?

Porque las operaciones con `double` no dan un resultado exacto y tienen cierto margen de error en los decimales.

#### 2. ¿Qué diferencia existe entre identificar un movimiento y agrupar los dos lados de una transferencia?

La diferencia radica en lo siguiente:

- **ID de movimiento:** ¿cuál es este movimiento?
- **ID de transferencia:** ¿a qué operación pertenece este movimiento?

Esto se hace porque una transferencia es una sola operación de negocio, pero produce dos efectos contables distintos. La transferencia es el “evento” y los movimientos son las “consecuencias” financieras del evento.

#### 3. ¿Quién puede modificar el saldo y bajo qué condiciones?

En un sistema bien diseñado, el saldo no debería ser modificado arbitrariamente por cualquier parte del sistema. En nuestro sistema debemos preguntarnos quién tiene autoridad y qué condiciones debe cumplir.

No diseñes el saldo como algo que cualquier parte del programa puede cambiar. Diseñá operaciones financieras que, cuando son válidas, produzcan entradas que cambien el balance.

#### 4. ¿Necesitás almacenar una lista de movimientos dentro de la billetera para poder consultarlos?

No. En la base de datos puede haber una entidad que represente los movimientos que ocurrieron:

```text
Billetera ────── Movimiento
    1                N
```

La clave foránea normalmente vive en `Movimiento`.

#### 5. ¿Qué zona horaria usarás internamente y cómo la explicarás?

Para eventos que representan un instante real usaré `Instant`. Para una fecha sin hora usaré `LocalDate`.

Debido a que la aplicación está localizada en Argentina, la base de datos usará:

```java
ZoneId zone = ZoneId.of("America/Argentina/Buenos_Aires");
```

```text
timezone=America/Argentina/Buenos_Aires
```

---

## Persistencia con JPA y Spring Data

### Investigación

#### Diferencia entre JPA, un proveedor ORM, Spring Data y una base de datos

**JPA:** *Jakarta Persistence API* define las reglas y APIs para trabajar con objetos Java persistidos en una base de datos relacional. Define reglas para trabajar con objetos persistidos en una base de datos.

JPA no hace el trabajo por sí mismo; es como una interfaz o contrato.

**ORM:** *Object-Relational Mapping*. Un proveedor ORM, como Hibernate, es una implementación de JPA y además un *framework* ORM.

**Base de datos:** es donde viven los datos de los sistemas.

#### ¿Qué condiciones debe cumplir una entidad persistente?

En JPA, una entidad persistente es una clase Java cuyos objetos pueden ser almacenados y recuperados de una base de datos.

Para que una clase pueda ser una entidad JPA debe cumplir ciertas condiciones:

- Debe estar anotada con `@Entity`: “Esta clase representa una entidad que quiero persistir”.
- Debe tener un identificador mediante `@Id`.
- Debe proporcionar un constructor sin argumentos `public` o `protected`.
- La clase no debería ser `final`.
- Debe permitir el acceso al estado persistente mediante acceso por campos o por propiedades.

Hibernate puede gestionar su ciclo de vida.

#### ¿Cómo se convierten nombres de clases y propiedades en tablas y columnas?

De esto se encarga el ORM. En este caso, Hibernate transforma el modelo de objetos Java en un modelo relacional de una base de datos.

Hibernate sabe cómo tiene que interpretar cada cosa gracias a las anotaciones de JPA. Por eso se llama *Object-Relational Mapping* (ORM): mapea objetos del mundo Java a estructuras relacionales de la base de datos.

#### ¿Qué aportan las restricciones únicas y las claves foráneas?

Sirven para que la base de datos pueda cumplir reglas de integridad, permitir garantizar reglas de dominio y establecer relaciones en una base de datos.

#### ¿Cómo deriva consultas Spring Data a partir de nombres y cuándo deja de ser conveniente?

Spring Data deriva la consulta a través del nombre del método porque tiene un analizador que divide el nombre en partes y las relaciona con las propiedades de la entidad.

Deja de ser conveniente cuando el nombre del método se vuelve demasiado complejo para expresar claramente la consulta. Usá métodos derivados para consultas simples y expresivas. Cuando el nombre del método empieza a ser más complicado que la propia consulta, es momento de utilizar otra forma de definirla.

#### ¿Qué significan carga ansiosa, carga diferida y problema de múltiples consultas repetidas?

**Carga ansiosa (`EAGER`):**

```java
@ManyToOne(fetch = FetchType.EAGER)
```

Cuando obtengo una entidad, también se cargan inmediatamente sus relaciones: “Traeme esto y también sus relaciones”.

**Carga diferida (`LAZY`):**

```java
@ManyToOne(fetch = FetchType.LAZY)
```
JOIN FETCH: pedir explícitamente una relación cuando una consulta concreta la necesita.
DTOs/proyecciones: traer únicamente los datos necesarios.
La relación no se carga hasta que realmente intentás utilizarla.

El problema de múltiples consultas repetidas normalmente se refiere al problema N+1. Hacés una consulta para obtener una lista de objetos y luego hacés otra consulta por cada objeto para obtener una relación. Muchas consultas pequeñas pueden ser mucho más lentas que una consulta bien diseñada.

**N+1:**

```sql
SELECT clientes;
SELECT cuentas del cliente 1;
SELECT cuentas del cliente 2;
SELECT cuentas del cliente 3;
...
```

**Consulta optimizada:**

```sql
SELECT clientes + cuentas relacionadas;
```


### Preguntas de Control

1 ¿Qué trabajo hace el repositorio y cuál no debería hacer?

El repositorio se encarga de acceder y modificar datos persistidos. No debería decidir qué significan esos datos ni contener reglas de negocio.
Puede encargarse de:
- Buscar entidades.
- Guardar entidades.
- Actualizar entidades.
- Eliminar entidades.
- Consultar por determinados criterios.
- Ejecutar consultas JPQL/SQL cuando sea necesario.
- Resolver cómo obtener los datos desde la persistencia.

2 ¿Por qué validar unicidad solo con una consulta previa puede ser insuficiente?

Porque si ocurren 2 consultas simultáneas puede ocurrir de que la base de datos registre amabas o rechazar ambas por lo que la restricción que queremos que se cumpla debe estar también especificada en la base de datos  
@Column(unique = true)
private String email;

Pero si ponemos la restricción en la misma consulta no puede permitir dar un error mas amigable al cliente


3 ¿Qué sucede con los datos de una base en memoria al detener la aplicación?
Los datos normalmente desaparecen
Porque los datos están almacenados en la RAM del proceso, no en un archivo persistente del disco.
4 ¿Qué problema aparece si cargas siempre todas las relaciones?
El problema es que puedes terminar trayendo muchos más datos de los que realmente necesitas, aumentado el costo de las consultas y consumo de memoria emperoando el rendimiento del programa y forzando a hacer un mayor trabajo a la BD.
No diseñes las consultas pensando "¿qué datos podría necesitar?", sino "¿qué datos necesita esta operación concreta?".

5 ¿Por qué generar automáticamente el esquema pede ser útil al aprender y riesgoso en producción?

Porque la generación automática del esquema permite que hibernate/JPA cree o modifique las tablas a partir de tus entidades. Es muy cómoda para aprender pero en producción puede hacer cambios destructivos.
Al arrancar la aplicación, Hibernate puede recrear el esquema.
Eso significa perder todos lo datos existentes
Incluso configuraciones menos agresivas pueden modificar el esquema de maneras que no fueron revisadas o coordinadas.
nunca hay que tener activado spring.jpa.hibernate.ddl-auto=create en producción