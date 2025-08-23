/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eci.edu.escuelaing.ArepTaller2Microframeworks;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santiago
 */
public class HttpRequest {
    
    private String path;
    private String query;
    private Map<String, String> queryParams;

    public HttpRequest(String path) {
        String[] parts = path.split("\\?", 2);
        this.path = parts[0];
        this.query = (parts.length > 1) ? parts[1] : "";
        
        Map<String, String> params = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=", 2);
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
            
        }
        this.queryParams = params;
    }
    

    public String getPath() {
        return path;
    }

    public String getValues(String key) {
        return queryParams.getOrDefault(key, null);
    }
}
