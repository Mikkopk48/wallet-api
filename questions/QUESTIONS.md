#### 1 ¿Por qué instalar solo un entorno de ejecución no alcanza para desarrollar?

El JRE (Java Runtime Environment) está destinado a ejecutar aplicaciones Java ya compiladas. No proporciona las herramientas necesarias para desarrollar, como el compilador `javac`.
Para desarrollar necesitamos el JDK (Java Development Kit), que incluye las herramientas de desarrollo y el entorno de ejecución. Por ejemplo, `javac` transforma los archivos `.java`, que contienen código fuente, en archivos `.class` que contienen bytecode. Luego, la JVM se encarga de ejecutar ese bytecode.

#### 2 ¿Qué tarea realiza Maven que no debería realizar manualmente el IDE?
Maven automatiza la construcción y gestión del proyecto Java. Entre otras cosas, administra las dependencias declaradas en el `pom.xml`, resuelve sus dependencias transitivas y descarga las versiones necesarias.

Además, permite compilar, ejecutar tests y empaquetar la aplicación de forma reproducible e independiente del IDE. El IDE puede interactuar con Maven, pero la configuración real del proyecto no debería depender exclusivamente del IDE.

#### 3 ¿Por qué el cliente HTTP no forma parte de la aplicación?
El cliente HTTP no forma parte de la aplicación backend porque es un componente externo que consume la API. Puede ser un navegador, una aplicación móvil, otro backend, Postman, etc.

El cliente se encarga de enviar peticiones HTTP y recibir respuestas, mientras que el backend se encarga de procesar esas peticiones, aplicar la lógica de negocio, validar los datos y, cuando sea necesario, comunicarse con una base de datos u otros servicios.

Por lo tanto, el cliente y el backend son componentes separados que se comunican mediante HTTP.
#### 4 ¿Qué datos no deberían subirse nunca al repositorio?
Nunca deberían subirse al repositorio credenciales o secretos como contraseñas, API keys, tokens, claves privadas, credenciales de bases de datos o archivos `.env` que contengan información sensible.

Estos datos deberían gestionarse mediante variables de entorno, gestores de secretos o mecanismos de configuración externos. El código fuente sí puede estar en el repositorio, pero los secretos necesarios para ejecutarlo no deberían estar expuestos allí.