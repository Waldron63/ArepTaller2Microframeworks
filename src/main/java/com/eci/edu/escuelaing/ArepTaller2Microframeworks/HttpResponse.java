/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eci.edu.escuelaing.ArepTaller2Microframeworks;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Santiago
 */
public class HttpResponse {
    private final OutputStream out;

    public HttpResponse(OutputStream out) {
        this.out = out;
    }

    /**
     * Envía una respuesta al cliente.
     * @param response La respuesta a enviar.
     */
    public void send(String response) {
        try {
            out.write(response.getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
