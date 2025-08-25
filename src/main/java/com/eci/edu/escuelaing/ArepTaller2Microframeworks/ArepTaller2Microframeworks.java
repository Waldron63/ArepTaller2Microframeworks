/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.eci.edu.escuelaing.ArepTaller2Microframeworks;

import static com.eci.edu.escuelaing.ArepTaller2Microframeworks.HttpServer.get;
import static com.eci.edu.escuelaing.ArepTaller2Microframeworks.HttpServer.startServer;
import static com.eci.edu.escuelaing.ArepTaller2Microframeworks.HttpServer.staticfiles;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author santiago.gualdron-r
 */
public class ArepTaller2Microframeworks {

    public static void main(String[] args) throws IOException, URISyntaxException {
        staticfiles("src/main/java/com/eci/edu/escuelaing/ArepTaller2Microframeworks/resources");
        get("/hello", (req, resp) -> {
            String name = req.getValues("name");
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
        startServer(args);
    }
}
