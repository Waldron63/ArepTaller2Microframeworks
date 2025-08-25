/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.eci.edu.escuelaing.ArepTaller1WebServer;

import com.eci.edu.escuelaing.ArepTaller2Microframeworks.HttpRequest;
import com.eci.edu.escuelaing.ArepTaller2Microframeworks.HttpResponse;
import com.eci.edu.escuelaing.ArepTaller2Microframeworks.HttpServer;
import static com.eci.edu.escuelaing.ArepTaller2Microframeworks.HttpServer.staticfiles;
import static com.eci.edu.escuelaing.ArepTaller2Microframeworks.HttpServer.get;
import com.eci.edu.escuelaing.ArepTaller2Microframeworks.Service;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Santiago
 */
public class MainTest {
    
    private static final String URL = "http://localhost:35000/";
    @BeforeAll
    public static void startServer() {
        new Thread(() -> {
            try {
                HttpServer.startServer(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // darle tiempo a que levante el socket
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @BeforeEach
    public void setUp() {
        
        HttpServer.getNames().clear();
        HttpServer.getServices().clear();
        staticfiles("src/main/java/com/eci/edu/escuelaing/ArepTaller2Microframeworks/resources");
        get("/hello", (request, respond) -> {
            String name = request.getValues("name");
            if (name == null || name.isEmpty()) {
                name = "usuario";
            }
            return "Hola " + name ;
        });
        get("/pi", (req, resp) -> {
            return String.valueOf(Math.PI); 
        });
        
        get("/music", (req, resp) -> {
            String music = req.getValues("music");
            if (music == null || music.isEmpty()) {
                return "No tienes musica preferida!";
            }
            return "Tu musica favorita es: " + music ;
        });
    }

    @Test
    public void shouldGetRestPi() {
        HttpRequest req = new HttpRequest("/pi");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HttpResponse res = new HttpResponse(out);
        String responseBody = HttpServer.services.get("/pi").executeService(req, res);
        Service handler = HttpServer.getServices().get("/pi");
        assertNotNull(handler, "El manejador para /pi no está registrado.");
        String result = handler.executeService(req, res);
        assertEquals(String.valueOf(Math.PI), result);
    }

    @Test
    public void shouldGetRestMusicWithParam() {
        HttpRequest req = new HttpRequest("/music?music=Vallenato");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HttpResponse res = new HttpResponse(out);
        String responseBody = HttpServer.services.get("/music").executeService(req, res);
        Service handler = HttpServer.getServices().get("/music");
        assertNotNull(handler, "El manejador para /music no está registrado.");
        String result = handler.executeService(req, res);
        assertEquals("Tu musica favorita es: Vallenato", result);
    }

    @Test
    public void shouldGetRestNameWithParam() {
        HttpRequest req = new HttpRequest("/hello?name=Santiago");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HttpResponse res = new HttpResponse(out);
        String responseBody = HttpServer.services.get("/hello").executeService(req, res);
        Service handler = HttpServer.getServices().get("/hello");
        assertNotNull(handler, "El manejador para /hello no está registrado.");
        String result = handler.executeService(req, res);
        assertEquals("Hola Santiago", result);
    }

    @Test
    public void shouldGetRestMusic() {
        HttpRequest req = new HttpRequest("/music");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HttpResponse res = new HttpResponse(out);
        String responseBody = HttpServer.services.get("/music").executeService(req, res);
        Service handler = HttpServer.getServices().get("/music");
        assertNotNull(handler, "El manejador para /music no está registrado.");
        String result = handler.executeService(req, res);
        assertEquals("No tienes musica preferida!", result);
    }

    @Test
    public void shouldGetRestName() {
        HttpRequest req = new HttpRequest("/hello");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HttpResponse res = new HttpResponse(out);
        String responseBody = HttpServer.services.get("/hello").executeService(req, res);
        Service handler = HttpServer.getServices().get("/hello");
        assertNotNull(handler, "El manejador para /hello no está registrado.");
        String result = handler.executeService(req, res);
        assertEquals("Hola usuario", result);
    }
    
    @Test
    public void shouldLoadStaticFileIndex() throws Exception {
        String file = "index.html";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldLoadStaticFileForm() throws Exception {
        String file = "form.html";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldLoadStaticFileImage() throws Exception {
        String file = "image.png";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldLoadStaticFileScript() throws Exception {
        String file = "script.js";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldLoadStaticFileStyle() throws Exception {
        String file = "styles.css";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
