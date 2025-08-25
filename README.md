# Web Framework Development for REST Services and Static File Management

La intención de este taller, es mostrar el desarrollo para la implementacion de servicios REST utilizando funciones lambda, implementandolas en el servidor previamente hecho (mas infomación en [ArepTaller1WebServer](https://github.com/Waldron63/ArepTaller1WebServer)) con el objetivo de mejorarlo y que el desarrollador tenga herramientas para utilizar peticiones dinámicas y archivos estáticos.

## Contenidos iniciales

las nuevas implementaciones al desarrollo web son:
- Método GET:  Definir rutas en la URL para servicios REST.
- Extracción de Parámetros: Obtener parámetros de las solicitudes o URL.
- Archivos Estáticos: Especificar la carpeta donde se encuentran los archivos estáticos HTML, CSS, JS, PNG, etc.

### Prerrequisitos

Para el correcto uso del servidor, es necesario tener las siguientes aplicaciones instaladas:
- JAVA
     ```sh
   java -version
   ```
- MAVEN
     ```sh
   mvn -v
   ```
- GIT
   ```sh
   git --version
   ```
(NOTA: si alguna de estas aplicaciones no fue instalada, ir a la página oficial de cada una e instalar la versión recomendada).

### Instalación
1. clonar el repositorio con el siguiente comando y ingresar a la carpeta en donde esta incluido el *pom.xml*:

   ```sh
   git clone https://github.com/Waldron63/ArepTaller2Microframeworks.git
   cd ArepTaller2Microframeworks
   ```

2. Construir el proyecto:

   ```sh
   mvn clean package
   ```
  La consola mostrará información parecida a:
  
  <img width="1358" height="236" alt="image" src="https://github.com/user-attachments/assets/f3ae98c7-5748-4f4a-8aba-2cbd18f0e688" />
  
3. Correr la aplicación:

   ```sh
   mvn exec:java -Dexec.mainClass="com.eci.edu.escuelaing.ArepTaller2Microframeworks.ArepTaller2Microframeworks"
   ```

   La consola debería mostrar el siguiente mensaje:

   ```sh
   Listo para recibir ...
   ```

   - Página principal:
     Una vez iniciado, en el buscador ingresar: "http://localhost:35000/" o "http://localhost:35000/index.html" y lo llevará a la página inicial del proyecto:

     <img width="1919" height="961" alt="image" src="https://github.com/user-attachments/assets/5c085ab6-c79b-49d6-9c27-8ddfd415b5d4" />

   - Archivos estáticos:
     Ingresar alguno de los siguientes comandos para mirar cada archivo estático
     ```bash
       http://localhost:35000/imgage.png
       http://localhost:35000/styles.css
       http://localhost:35000/script.js
       http://localhost:35000/form.html
       http://localhost:35000/index.html
     ```
   - servicios Rest:
     Ingresar alguno de los siguientes comandos para mirar cada servicio
     ```bash
       http://localhost:35000/hello?name=Santiago
       http://localhost:35000/pi
       http://localhost:35000/music?music=Rock
     ```

## Arquitectura

<img width="995" height="565" alt="image" src="https://github.com/user-attachments/assets/b6db5e94-c404-4a14-8f2d-12bba2cc08bf" />

La estructura del directorio del proyecto es:

<img width="939" height="674" alt="image" src="https://github.com/user-attachments/assets/ffd7ebe1-c875-4920-88fe-f30285ff4316" />

donde:

- ArepTaller2Microframeworks.java: programa de ejecución base.
- HttpServer.java: programa del servidor principal.
- HttpRequest, HttpResponse, Server: programas para el correcto funcionamiento de los servicios Rest.
- Resources/*: aplicación web(html, js, css y png).
- HttpServerTest.java: pruebas test del servidor para un funcionamiento correcto.
- main.java: pruebas test del servicio Rest para un funcionamiento correcto.

## Reporte de pruebas

### fecha

Fecha: 24/08/2025

### Pruebas unitarias:

<img width="1460" height="304" alt="image" src="https://github.com/user-attachments/assets/b12417ca-bdf8-4f5f-ae92-043dd5f7e003" />

donde cada prueba unitaria (del archivo mainTest, las pruebas de HttpServerTest no cambiaron de la anterior versión) sirve para:
- shouldGetRestPi(): muestra que envía el servicio Rest Get Pi; donde devuelve el numero Pi.
- shouldGetRestMusicWithParam(): muestra que envía el servicio Rest Get Music; donde devuelve el género ingresado por el usuario.
- shouldGetRestNameWithParam(): muestra que envía el servicio Rest Get Hello; donde devuelve el nombre ingresado por el usuario.
- shouldGetRestMusic(): muestra que envía el servicio Rest Get Music; donde devuelve el mensaje predeterminado si no se envia parametro.
- shouldGetRestName():  muestra que envía el servicio Rest Get Hello; donde devuelve el mensaje predeterminado si no se envia parametro.
- shouldLoadStaticFileIndex(): muestra que aun funciona la búsqueda del archivo estático: intex.html
- shouldLoadStaticFileForm(): muestra que aun funciona la búsqueda del archivo estático: form.html
- shouldLoadStaticFileImage(): muestra que aun funciona la búsqueda del archivo estático: image.png     
- shouldLoadStaticFileScript(): muestra que aun funciona la búsqueda del archivo estático: script.js
- shouldLoadStaticFileStyle(): muestra que aun funciona la búsqueda del archivo estático: styles.css

### Pruebas de aceptación

- index.html:
  
  <img width="1560" height="950" alt="image" src="https://github.com/user-attachments/assets/deace7f1-80e0-456c-badd-4a6c64049f51" />

- servicio rest Pi:

  <img width="610" height="138" alt="image" src="https://github.com/user-attachments/assets/54c3f223-a518-4eb0-8294-254dc6079fd7" />

- servicio rest Hello:

  <img width="695" height="137" alt="image" src="https://github.com/user-attachments/assets/bdf44a31-4468-4184-9a13-d392a3c3aebd" />

- servicio rest Music:

  <img width="587" height="150" alt="image" src="https://github.com/user-attachments/assets/d5e85dc8-6bbd-49bb-9f04-82ae0acac81d" />
  
## Construido con

[Maven](https://maven.apache.org/index.html) - Dependency Management

[Git](https://git-scm.com) - Version Control System

## Autor

Santiago Gualdron Rincon - [Waldron63](https://github.com/Waldron63)
