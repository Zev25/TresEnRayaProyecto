package Conexiones;

import java.io.*;
import java.net.*;
/**
 *
 * @author Felipe
 */
public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(12345)) {
            System.out.println("Esperado a un jugador...");
            Socket socket = servidor.accept();
            System.out.println("Jugador conectado");
            
            //maneja la conexión
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        
            //comunicación
            out.println("Bienvenido al servidor de Tres en Raya");
            String mensaje = in.readLine();
            System.out.println("Mensaje del jugador" + mensaje);
                
            //Cierra las conexiones
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
