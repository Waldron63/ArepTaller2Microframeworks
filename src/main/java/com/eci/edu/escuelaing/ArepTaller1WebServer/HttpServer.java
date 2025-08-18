/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.eci.edu.escuelaing.ArepTaller1WebServer;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author santiago.gualdron-r
 */
public class HttpServer {
    
    private static final String directory = "src/main/java/com/eci/edu/escuelaing/ArepTaller1WebServer/resources";
    private static final Map<String, String> names = new HashMap<>();

    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
       
        boolean running = true;
        while (running){

            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
                ClientRequest(clientSocket);
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        }
        serverSocket.close();
    }
    
    public static void ClientRequest(Socket clientSocket) throws URISyntaxException{
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream()){
            String inputLine, path;

            boolean isFirstLine = true;
            URI requestUri = null;
            String content = "", method;
            File file = null;
            String[] head;
            while ((inputLine = in.readLine()) != null) {
                if (isFirstLine){
                    head = inputLine.split(" ");
                    requestUri = new URI(head[1]);
                    method = head[0];
                    path = requestUri.getPath();
                    if (path.equals("/")){
                        path = "/index.html";
                    }
                    if (path.contains("..")){
                        error404(out);
                        return;
                    }else if (method.equals("GET") && path.contains("/getName")) {
                        makeGetRequest(head[1], out);
                        return;
                    } else if (method.equals("POST") && path.contains("/updateName")) {
                        makePostRequest(in, head[1], out);
                        return;
                    }
                    System.out.println("Path: " + directory + path);
                    file = new File(directory + path);
                    content = getContentType(path);
                    isFirstLine = false;
                    
                    if (content == null || !file.exists() || file.isDirectory()){
                        error404(out);
                        return;
                    }
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            headers(out, content, file);
            out.flush();
            out.close();
            in.close();
            clientSocket.close();
        }catch (IOException e) {
            System.err.println("No se pudo procesar la solicitud.");
            System.exit(1);
        }
    }
    
    private static void makeGetRequest(String path, OutputStream out) throws IOException {
        String savedName = names.getOrDefault("name", "usuario");
        String queryParams = path.split("\\?").length > 1 ? path.split("\\?")[1] : "";
        String[] params = queryParams.split("&");

        for (String param : params) {
            if (param.startsWith("name=")) {
                String name = param.split("=")[1];
                names.put("name", name);
                savedName = name;
            }
        }

        String jsonResponse = "{\"name\": \"" + savedName + "\"}";
        byte[] responseBytes = jsonResponse.getBytes();

        String header = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "Conten-Length: " + responseBytes.length + "\r\n" +
                "\r\n";

        out.write(header.getBytes());
        out.write(responseBytes);
        out.flush();
    }
    
    private static void makePostRequest(BufferedReader in, String path, OutputStream out) throws IOException{
        String line;
        int contentLength = 0;

        while (!(line = in.readLine()).isEmpty()) {
            if (line.startsWith("Content-Length:")) {
                contentLength = Integer.parseInt(line.split(":")[1].trim());
            }
        }

        char[] body = new char[contentLength];
        in.read(body, 0, contentLength);
        String requestBody = new String(body);

        String name = requestBody.replace("{\"name\":\"", "").replace("\"}", "");
        names.put("name", name);

        String jsonResponse = "{\"name\": \"" + name + "\"}";
        byte[] responseBytes = jsonResponse.getBytes();

        String header = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: " + responseBytes.length + "\r\n" +
                "\r\n";

        out.write(header.getBytes());
        out.write(responseBytes);
        out.flush();
    }
    
    private static String getContentType(String fileName) {
        if (fileName.contains(".html")) return "text/html";
        if (fileName.contains(".css")) return "text/css";
        if (fileName.contains(".js")) return "application/javascript";
        if (fileName.contains(".png")) return "image/png";
        if (fileName.contains(".jpg") || fileName.endsWith(".jpeg")) return "image/jpeg";
        if (fileName.contains(".gif")) return "image/gif";
        return null;
    }

    private static void error404(OutputStream out) throws IOException {
        String message = "<h1>404 Not Found</h1>";
        String header = "HTTP/1.1 404 Not Found\r\n"
                    + "content-type: text/html\r\n"
                    + "content-length: " + message.length() + "\r\n"
                    + "\r\n";
        out.write(header.getBytes());
        out.write(message.getBytes());
    }
    
    private static void headers(OutputStream out, String content, File file) throws IOException{
        String outputLine;
        byte[] contentByte = Files.readAllBytes(file.toPath());
        outputLine =  "HTTP/1.1 200 OK\r\n"
            + "content-type: " + content + "\r\n"
            + "content-length: " + contentByte.length + "\r\n"
            + "\r\n";
        out.write(outputLine.getBytes());
        out.write(contentByte);
            
    }
    
    public static Map<String, String> getNames(){
        return names;
    }
}
