/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.eci.edu.escuelaing.ArepTaller1WebServer;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Santiago Gualdron
 */
public class HttpServerTest {

    private HttpServer httpServer;
    
    @BeforeEach
    public void setUp() {
        httpServer = new HttpServer();
    }
    
    @Test
    public void shouldNotGetRequest() throws URISyntaxException {
        String requestLine = "GET /noexiste.html HTTP/1.1";
        Socket mockSocket = new SocketTest(requestLine);
        httpServer.ClientRequest(mockSocket);
        assertFalse(new File("src/main/java/resources/noexiste.html").exists());
    }
    
    @Test
    public void shouldGetRequestIndex() throws URISyntaxException {
        String requestLine = "GET /index.html HTTP/1.1";
        Socket mockSocket = new SocketTest(requestLine);
        httpServer.ClientRequest(mockSocket);
        assertFalse(new File("src/main/java/resources/index.html").exists());
    }
    
    @Test
    public void shouldGetRequestName() throws URISyntaxException{
        String requestLine = "GET /getName HTTP/1.1";
        Socket mockSocket = new SocketTest(requestLine);
        httpServer.ClientRequest(mockSocket);
        Map<String, String> names = HttpServer.getNames();
        assertEquals("usuario", names.getOrDefault("name", "usuario"));
    }

    @Test
    public void shouldGetRequestImage() throws URISyntaxException {
        String requestLine = "GET /image.png HTTP/1.1";
        Socket mockSocket = new SocketTest(requestLine);
        httpServer.ClientRequest(mockSocket);
        assertFalse(new File("src/main/java/resources/image.png.html").exists());
    }
    
    @Test
    public void shouldGetRequestScript() throws URISyntaxException {
        String requestLine = "GET /script.js HTTP/1.1";
        Socket mockSocket = new SocketTest(requestLine);
        httpServer.ClientRequest(mockSocket);
        assertFalse(new File("src/main/java/resources/script.js").exists());
    }
    
    @Test
    public void shouldGetRequestStyles() throws URISyntaxException {
        String requestLine = "GET /styles.css HTTP/1.1";
        Socket mockSocket = new SocketTest(requestLine);
        httpServer.ClientRequest(mockSocket);
        assertFalse(new File("src/main/java/resources/styles.css").exists());
    }
    
    @Test
    public void shouldGetRequestForm() throws URISyntaxException {
        String requestLine = "GET /form.html HTTP/1.1";
        Socket mockSocket = new SocketTest(requestLine);
        httpServer.ClientRequest(mockSocket);
        assertFalse(new File("src/main/java/resources/form.html").exists());
    }
    
    @Test
    public void shouldPostRequestName() throws URISyntaxException {
        String requestLine = "POST /api/updateName HTTP/1.1";
        String requestBody = "{\"name\":\"santiago\"}";
        Socket mockSocket = new SocketTest(requestLine, requestBody);
        httpServer.ClientRequest(mockSocket);
        Map<String, String> names = HttpServer.getNames();
        assertEquals("santiago", names.get("name"));
        requestBody = "{\"name\":\"usuario\"}";
        mockSocket = new SocketTest(requestLine, requestBody);
        httpServer.ClientRequest(mockSocket);
    }
    
    // simular un socket en las pruebas
    private static class SocketTest extends Socket {
        private final ByteArrayInputStream inputStream;
        private final ByteArrayOutputStream outputStream;

        public SocketTest(String requestLine) {
            this(requestLine, "");
        }

        public SocketTest(String requestLine, String requestBody) {
            String request = requestLine + "\r\n" +
                    "Content-Length: " + requestBody.length() + "\r\n" +
                    "\r\n" +
                    requestBody;
            this.inputStream = new ByteArrayInputStream(request.getBytes());
            this.outputStream = new ByteArrayOutputStream();
        }

        @Override
        public InputStream getInputStream() {
            return inputStream;
        }

        @Override
        public OutputStream getOutputStream() {
            return outputStream;
        }
    }
}
