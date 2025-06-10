package Presentation;

import Domain.Pokemon;
import java.io.InputStream;
import javax.swing.*;
import java.awt.*;

public class PanelPokemon extends PoobkemonFondoPrincipal {

    public PanelPokemon(String rutaImagenFondo, Pokemon pokemon) {
        super(rutaImagenFondo);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1200;
        int height = 800;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height / 2+50));

        Font fuentePersonalizada;
        try {
            InputStream fontStream = getClass().getResourceAsStream("/Recursos/Fuentes/pokemon-emerald.ttf");
            if (fontStream == null) {
                System.out.println("No se encontró la fuente.");
                fuentePersonalizada = new Font("Arial", Font.PLAIN, 16);
            } else {
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
                fuentePersonalizada = customFont.deriveFont(Font.PLAIN, 22f);
                fontStream.close();
            }
        } catch (Exception e) {
            fuentePersonalizada = new Font("Arial", Font.PLAIN, 16);
        }

        JLabel nombreLabel = new JLabel("Nombre: " + pokemon.getNombre());
        JLabel tipoLabel = new JLabel("Tipo: " + pokemon.getTipo());
        JTextArea descripcionArea = new JTextArea(pokemon.getDescripcion());
        JLabel vidaLabel = new JLabel("Vida: " + pokemon.getPs());
        JLabel ataqueLabel = new JLabel("Ataque: " + pokemon.getAtaque());
        JLabel defensaLabel = new JLabel("Defensa: " + pokemon.getDefensa());
        JLabel velocidadLabel = new JLabel("Velocidad: " + pokemon.getVelocidad());
        JLabel ataque2Label = new JLabel("Ataque Especial: " + pokemon.getAtaqueEspecial());
        JLabel defensa2Label = new JLabel("Defensa Especial: " + pokemon.getDefensaEspecial());

        nombreLabel.setFont(fuentePersonalizada);
        tipoLabel.setFont(fuentePersonalizada);
        descripcionArea.setFont(fuentePersonalizada);
        vidaLabel.setFont(fuentePersonalizada);
        ataqueLabel.setFont(fuentePersonalizada);
        defensaLabel.setFont(fuentePersonalizada);
        velocidadLabel.setFont(fuentePersonalizada);
        ataque2Label.setFont(fuentePersonalizada);
        defensa2Label.setFont(fuentePersonalizada);

        descripcionArea.setLineWrap(true);
        descripcionArea.setWrapStyleWord(true);
        descripcionArea.setEditable(false);
        descripcionArea.setOpaque(false);

        JScrollPane scroll = new JScrollPane(descripcionArea);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        descripcionArea.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBorder(BorderFactory.createEmptyBorder());

        add(nombreLabel);
        add(tipoLabel);
        add(scroll);
        add(vidaLabel);
        add(ataqueLabel);
        add(defensaLabel);
        add(velocidadLabel);
        add(ataque2Label);
        add(defensa2Label);
    }



    public PanelPokemon(String rutaImagenFondo, Pokemon pokemon,boolean isSelection) {
        super(rutaImagenFondo);
        if (isSelection){
            int width = 1200;
            int height = 800;

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(width-120, height -144));

            ImageIcon iconoPokemon = new ImageIcon(pokemon.getIcono());
            JLabel iconoPoke = new JLabel(iconoPokemon);
            int x = 38;
            int y = 81;
            Image img = iconoPokemon.getImage().getScaledInstance(width-120, height -144, Image.SCALE_SMOOTH);
            iconoPoke.setIcon(new ImageIcon(img));
            add(iconoPoke);

        }
    }




}
