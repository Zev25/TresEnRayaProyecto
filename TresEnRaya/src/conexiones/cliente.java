package Conexiones;

import java.io.*;
import java.net.ServerSocket;
import java.net.*;

/**
 *
 * @author Felipe
 */
public class cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Conectado al servidor");
            
            //maneja la conexion
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //Comunicación
            String mensaje = in.readLine();
            System.out.println("Mensaje del servidor" + mensaje);
            out.println("Hola, servidor");
            
            //cierra las conexiones
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

