package Presentation;

import javax.swing.*;
import java.awt.*;

public class barraVida extends JPanel {

    private int vidaMaxima;
    private int vidaActual;

    public barraVida(int x, int y, int width, int height, int vidaMaxima) {
        this.setBounds(x, y, width, height);
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
    }

    // Método para actualizar la vida
    public void setVidaActual(int vida) {
        this.vidaActual = vida;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int vidaPorcentaje = (vidaActual * 100) / vidaMaxima;
        if (vidaPorcentaje > 50) {
            g2d.setColor(Color.GREEN);
        } else if (vidaPorcentaje > 20) {
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.RED);
        }

        g2d.fillRoundRect(0, 0, (int) (getWidth() * ((double) vidaActual / vidaMaxima)), getHeight(), 15, 15);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    }
}

