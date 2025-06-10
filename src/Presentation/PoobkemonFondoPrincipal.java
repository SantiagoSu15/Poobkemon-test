package Presentation;
import javax.swing.*;
import java.awt.*;


public class PoobkemonFondoPrincipal extends JPanel{
    private Image ImagenFondo;

    public PoobkemonFondoPrincipal(String nombre){
        ImagenFondo = new ImageIcon(getClass().getResource(nombre)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        g2d.drawImage(ImagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
}

