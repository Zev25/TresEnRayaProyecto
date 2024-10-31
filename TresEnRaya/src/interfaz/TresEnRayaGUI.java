package interfaz;

import logica.TresEnRayaLogica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Felipe
 */
public class TresEnRayaGUI extends JFrame implements ActionListener  {
    private JButton[][] botones = new JButton[3][3]; //matriz de botones para el tablero
    private TresEnRayaLogica logica;

    public TresEnRayaGUI() {
        logica = new TresEnRayaLogica();
        initComponents();
        setTitle("Tres en Raya"); //Titulo del juego
        setSize(300,300); //Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Operacion de cierre
        setLayout(new GridLayout(3, 3)); //Diseño de cuadricula 3x3
        
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton("");
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                botones[i][j].setFocusPainted(false);
                botones[i][j].addActionListener(this); //añade el listener de acción
                add(botones[i][j]);
            } 
        }
        setVisible(true); //Hace visible la ventana
    }
    
//Metodo que maneja los eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonPresionado = (JButton) e.getSource();
        //busca el botón presionado
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botones[i][j] == botonPresionado) {
                    if (logica.marcarCasilla(i, j)) {
                        botonPresionado.setText(logica.getJugadorActual());
                        if (logica.verificarVictoria()) { //verifical si el jugador actual ha ganado
                            JOptionPane.showMessageDialog(this, "jugador");
                            logica.reiniciarJuego(); //reinicar la logica del juego
                            reiniciarInterfaz(); //reiniciar la interfaz
                        } else {
                            logica.cambiarTurno(); // cambiar el turno del jugador
                        }
                    }
                }
            }
        }
    }
    //metodo para reinicar la interfaz del juego
    private void reiniciarInterfaz() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("");
            }
        } 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

