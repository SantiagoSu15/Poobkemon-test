package Presentation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;

public class Button extends JButton {
    private boolean over = false;
    private Color color;
    private Color colorOver, colorClick, borderColor;
    private int radius = 20;
    //private Icon icon;

    //con coords, y colores;
    public Button(String text, int posX, int posY, int width, int height,
                  Color color1, Color colorOver1, Color colorClick1, Color borderColor1) {
        super(text);
        this.color = color1;
        this.colorOver = colorOver1;
        this.colorClick = colorClick1;
        this.borderColor = borderColor1;

        setBounds(posX, posY, width, height);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 14));

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                over = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClick);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(over ? colorOver : color);
                repaint();
            }
        });
    }




    //boton sin fondo
    public Button(String text, int posX, int posY, int width, int height){
        super(text);
        setBounds(posX, posY, width, height);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.BLACK);


        try {
            InputStream fontStream = getClass().getResourceAsStream("/Recursos/Fuentes/pokemon-emerald.ttf");
            if (fontStream == null) {
                System.out.println("No se encontró la fuente: /Recursos/Fuentes/pokemon-emerald.ttf");
                setFont(new Font("Arial", Font.BOLD, 20));
                return;
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            fontStream.close();

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            Font derivedFont = customFont.deriveFont(Font.BOLD, 50f);
            setFont(derivedFont);
        } catch (Exception e) {
            setFont(new Font("Arial", Font.BOLD, 20));
        }
    }


    //boton sin fondo y sin coords
    public Button(String text, int width, int height){
        super(text);
        setSize(width, height);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.BLACK);


        try {
            InputStream fontStream = getClass().getResourceAsStream("/Recursos/Fuentes/pokemon-emerald.ttf");
            if (fontStream == null) {
                System.out.println("No se encontró la fuente: /Recursos/Fuentes/pokemon-emerald.ttf");
                setFont(new Font("Arial", Font.BOLD, 20));
                return;
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            fontStream.close();

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            Font derivedFont = customFont.deriveFont(Font.BOLD, 50f);
            setFont(derivedFont);
        } catch (Exception e) {
            setFont(new Font("Arial", Font.BOLD, 20));
        }
    }


    //boton sin fondo y sin coords
    public Button(String text, int width, int height,int tamano){
        super(text);
        setSize(width, height);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.BLACK);


        try {
            InputStream fontStream = getClass().getResourceAsStream("/Recursos/Fuentes/pokemon-emerald.ttf");
            if (fontStream == null) {
                System.out.println("No se encontró la fuente: /Recursos/Fuentes/pokemon-emerald.ttf");
                setFont(new Font("Arial", Font.BOLD, 20));
                return;
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            fontStream.close();

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            Font derivedFont = customFont.deriveFont(Font.BOLD, tamano);
            setFont(derivedFont);
        } catch (Exception e) {
            setFont(new Font("Arial", Font.BOLD, 20));
        }
    }

    //boton de imagenes
    public Button(String ruta, Boolean redimensionar,int width, int height) {
        Icon icon = new ImageIcon(getClass().getResource(ruta));

        if (redimensionar) {
            Image imagenRedimensionada = ((ImageIcon) icon).getImage()
                    .getScaledInstance(width, height, Image.SCALE_SMOOTH);
            icon = new ImageIcon(imagenRedimensionada);
        }

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.BLACK);
        this.setIcon(icon);
    }




    //boton de imagenes y texto
    public Button(String nombre, String ruta, Boolean redimensionar,int width, int height) {
        super(nombre);
        Icon icon = new ImageIcon(getClass().getResource(ruta));

        if (redimensionar) {
            Image imagenRedimensionada = ((ImageIcon) icon).getImage()
                    .getScaledInstance(width, height, Image.SCALE_SMOOTH);
            icon = new ImageIcon(imagenRedimensionada);
        }
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.BLACK);
        this.setIcon(icon);

        try {
            InputStream fontStream = getClass().getResourceAsStream("/Recursos/Fuentes/pokemon-emerald.ttf");
            if (fontStream == null) {
                System.out.println("No se encontró la fuente: /Recursos/Fuentes/pokemon-emerald.ttf");
                setFont(new Font("Arial", Font.BOLD, 20));
                return;
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            fontStream.close();

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            Font derivedFont = customFont.deriveFont(Font.BOLD, 50f);
            setFont(derivedFont);
        } catch (Exception e) {
            setFont(new Font("Arial", Font.BOLD, 20));
        }

    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (borderColor != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(borderColor);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            g2d.dispose();
        }

        super.paintComponent(g);
    }
}
