# Taller diseño y estructuración de aplicaciones distribuidas en internet

En este taller, se hizo la implementacion de un servidor web local que soporta múlltiples solicitudes seguidas no concurrentes, utilizando el lenguaje JAVA para la estructura base; HTML, CSS y JS para el desarrollo de una aplicación web básica y sin el uso de frameworks web como Spark o Spring.

El servidor web fue diseñado para obtener peticiones http (con solicitudes GET y POST), para que pueda procesar extenciones tipo: .html, .js, .css, .png o .jpg.
Si el servidor recibe alguna otra petición diferente a las ya nombradas, devolverá un mensaje con *error 404* para evitar casos bordes.

## Contenidos iniciales

El servidor web tiene 3 casos importantes a ser tratados para su desarrollo completo:
- Servidor web: Desarrollo implementado en JAVA para recibir peticiones HTTP tanto GET como POST
- programas estáticos: desarrollo de aplicación web básica, con una navegación simple hecho en HTML, CSS y JS que manda peticiones REST al servidor
- Peticiones Rest: comunicación asincrónica del servidor para recibir en cualquier momento las peticiones dadas por la aplicación web, sin el uso de frameworks webs.

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
   git clone https://github.com/Waldron63/ArepTaller1WebServer.git
   cd ArepTaller1WebServer
   ```

2. Construir el proyecto:

   ```sh
   mvn clean package
   ```
  La consola mostrará información parecida a:
  <img width="1464" height="355" alt="image" src="https://github.com/user-attachments/assets/3ce31905-79e9-40e1-95db-d21aa4e9c484" />
  
3. Correr la aplicación:

   ```sh
   mvn exec:java -Dexec.mainClass="com.eci.edu.escuelaing.ArepTaller1WebServer.HttpServer"
   ```

   La consola debería mostrar el siguiente mensaje:

   ```sh
   Listo para recibir ...
   ```
  Una vez iniciado, en el buscador ingresar: "http://localhost:35000/" o "http://localhost:35000/index.html" y lo llevará a la página inicial del proyecto:
  
<img width="1919" height="961" alt="image" src="https://github.com/user-attachments/assets/5c085ab6-c79b-49d6-9c27-8ddfd415b5d4" />

## Arquitectura

La estructura del directorio del proyecto es:
<img width="1127" height="675" alt="image" src="https://github.com/user-attachments/assets/639f65e0-10d7-4072-b25b-08b352d2e0c1" />
donde:
- ArepTaller1WebServer.java: programa de ejecución base (se puede ignorar)
- HttpServer.java: programa del servidor principal para iniciar.
- Resources/*: aplicación web con los prerrequisitos pedidos (html, js, css y png).
- HttpServerTest.java: pruebas test del servidor para un funcionamiento correcto

## Reporte de pruebas

### fecha

Fecha: 12/08/2025

### Pruebas unitarias:

<img width="1467" height="307" alt="image" src="https://github.com/user-attachments/assets/052fdc5a-b323-4156-b41f-305924b5051a" />

donde cada prueba unitaria sirve para:
- shouldNotGetRequest(): revisar que funciona *error 404* mandandole una uri inconrrecta.
- shouldGetRequestIndex(): comprobar que funciona el estándar o index principal de la aplicación web.
- shouldGetRequestName(): revisar que acepta una petición GET con el nombre del usuario (en blanco y lleno)
- shouldGetRequestImage(): comprueba que el servidor acepta formato png y jpg.
- shouldGetRequestScript(): comprueba que el servidor acepta formato js.
- shouldGetRequestStyles(): comprueba que el servidor acepta formato css.
- shouldGetRequestForm(): comprueba que el servidor acepta formato html.
- shouldPostRequestName(): revisar que acepta una petición POST con el nuevo nombre del usuario (el mismo y diferente)

### Pruebas de aceptación

- index.html:
  
  <img width="1560" height="950" alt="image" src="https://github.com/user-attachments/assets/deace7f1-80e0-456c-badd-4a6c64049f51" />
  
- script.js:
  
  <img width="1224" height="914" alt="image" src="https://github.com/user-attachments/assets/b32f0fe3-1ef8-47e8-b09c-6a2e491ad9d8" />
  
- styles.css:
  
  <img width="891" height="947" alt="image" src="https://github.com/user-attachments/assets/5d0d0fc9-760b-404b-b656-70a7d0257be7" />
  
- image.png:
  
  <img width="1563" height="820" alt="image" src="https://github.com/user-attachments/assets/a1f13b66-b87d-4220-b083-9c1cda4264df" />
  
- form.html (enviando petición GET y POST):
  
  <img width="1518" height="860" alt="image" src="https://github.com/user-attachments/assets/7ded5ea0-6f15-4852-8f56-7666ff4e98ba" />

## Construido con

[Maven](https://maven.apache.org/index.html) - Dependency Management

[Git](https://git-scm.com) - Version Control System

## Autor

Santiago Gualdron Rincon - [Waldron63](https://github.com/Waldron63)
