package conexiones;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import logica.TresEnRayaLogica;

/**
 * Servidor para el juego Tres en Raya.
 * Autor: Felipe
 */
public class Servidor {
    public static void main(String[] args) {
        TresEnRayaLogica juego = new TresEnRayaLogica();

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Esperando a un jugador...");

            try (Socket player1Socket = serverSocket.accept();
                 Socket player2Socket = serverSocket.accept();
                 PrintWriter out1 = new PrintWriter(player1Socket.getOutputStream(), true);
                 BufferedReader in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
                 PrintWriter out2 = new PrintWriter(player2Socket.getOutputStream(), true);
                 BufferedReader in2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()))) {

                System.out.println("Jugadores conectados");

                while (true) {
                    PrintWriter currentOut = juego.getJugadorActual().equals("X") ? out1 : out2;
                    BufferedReader currentIn = juego.getJugadorActual().equals("X") ? in1 : in2;

                    // Envía el tablero al jugador actual
                    System.out.println("Enviando tablero al jugador " + juego.getJugadorActual());
                    currentOut.println(getBoardString(juego));

                    // Pide el movimiento del jugador actual
                    System.out.println("Solicitando movimiento del jugador " + juego.getJugadorActual());
                    currentOut.println("Jugador " + juego.getJugadorActual() + ", ingresa tu movimiento (1-9):");

                    // Leer el movimiento
                    try {
                        String input = currentIn.readLine();
                        System.out.println("Movimiento recibido: " + input);
                        int move = Integer.parseInt(input) - 1;

                        // Procesa el movimiento
                        int fila = move / 3;
                        int columna = move % 3;

                        if (juego.marcarCasilla(fila, columna)) {
                            // Verificador de victoria antes de seguir 
                            if (juego.verificarVictoria()) {
                                out1.println(getBoardString(juego));
                                out2.println(getBoardString(juego));
                                out1.println("¡Jugador " + juego.getJugadorActual() + " ha ganado!");
                                out2.println("¡Jugador " + juego.getJugadorActual() + " ha ganado!");
                                juego.reiniciarJuego(); // reinicia el juego
                                continue;
                            }

                            // Verifica si es posible un empate(es decir si el tablero ta lleno)
                            if (isBoardFull(juego)) {
                                out1.println(getBoardString(juego));
                                out2.println(getBoardString(juego));
                                out1.println("¡Es un empate! Reiniciando el tablero...");
                                out2.println("¡Es un empate! Reiniciando el tablero...");
                                juego.reiniciarJuego(); // Reinicia el juego
                                continue;
                            }
                            juego.cambiarTurno();
                        } else {
                            currentOut.println("Movimiento inválido. Inténtalo de nuevo.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error al leer el movimiento del jugador: " + e.getMessage());
                        e.printStackTrace();
                        break; // Sale del bucle si hay un error de lectura
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Convertir el tablero en un string para enviarlo
    private static String getBoardString(TresEnRayaLogica juego) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(juego.getTablero()[i][j].equals("") ? " " : juego.getTablero()[i][j]);
                if (j < 2) sb.append(" | ");
            }
            sb.append("\n");
            if (i < 2) sb.append("---------\n");
        }
        return sb.toString();
    }

    // Verificar si el tablero está lleno
    private static boolean isBoardFull(TresEnRayaLogica juego) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (juego.getTablero()[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}


