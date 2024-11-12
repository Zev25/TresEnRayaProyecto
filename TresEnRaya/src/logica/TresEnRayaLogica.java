/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;


public class TresEnRayaLogica {
    private String[][] tablero;
    private String jugadorActual;

    // inicia el tablero y establece el jugador actual
    public TresEnRayaLogica() {
        tablero = new String[3][3];
        jugadorActual = "X";
        // inicia el tablero vacio 
        reiniciarJuego();
    }

    // obtiene jugador actual
    public String getJugadorActual() {
        return jugadorActual;
    }

    // Obtener el tablero
    public String[][] getTablero() { // <-- Nuevo método agregado
        return tablero;
    }

    // marcar una casilla en el tablero
    public boolean marcarCasilla(int fila, int colum) {
        // verifica si la casilla esta vacía
        if (tablero[fila][colum].equals("")) {
            tablero[fila][colum] = jugadorActual;
            return true;
        }
        return false; // devuelve false si la casilla está ocupada
    }

    // verifica si el jugador actual ha ganado
    public boolean verificarVictoria() {
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].equals(jugadorActual) &&
                tablero[i][1].equals(jugadorActual) &&
                tablero[i][2].equals(jugadorActual)) {
                return true;
            }
        }

        // Verificar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j].equals(jugadorActual) &&
                tablero[1][j].equals(jugadorActual) &&
                tablero[2][j].equals(jugadorActual)) {
                return true;
            }
        }

        // Verificar diagonales
        if (tablero[0][0].equals(jugadorActual) &&
            tablero[1][1].equals(jugadorActual) &&
            tablero[2][2].equals(jugadorActual)) {
            return true;
        }

        if (tablero[0][2].equals(jugadorActual) &&
            tablero[1][1].equals(jugadorActual) &&
            tablero[2][0].equals(jugadorActual)) {
            return true;
        }
        return false;
    }

    // cambiar el turno del jugador
    public void cambiarTurno() {
        jugadorActual = jugadorActual.equals("X") ? "O" : "X";
    }

    // reinicia el juego
    public void reiniciarJuego() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = "";
            }
        }
        jugadorActual = "X"; // El jugador "X" siempre empieza
    }
}
