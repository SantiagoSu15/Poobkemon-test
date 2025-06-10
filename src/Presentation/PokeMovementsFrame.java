package Presentation;

import Domain.Movimiento;
import Domain.Pokemon;
import Domain.Tipo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class PokeMovementsFrame extends JFrame {
    private final Pokemon pokemon;
    private final JFrame anteriorFrame;
    private JPanel tablero;
    private JPanel currentPanel;
    private int width, height;

    public PokeMovementsFrame(Pokemon pokemon, JFrame anteriorFrame, int width, int height) {
        setTitle("Editor de movimientos - " + pokemon.getNombre());
        this.pokemon = pokemon;
        this.anteriorFrame = anteriorFrame;
        this.width = width;
        this.height = height;
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prepareMovementsFrame();
    }

    private void prepareMovementsFrame() {
        tablero = new PoobkemonFondoPrincipal("/Recursos/Imagenes/Fondo_opciones_tipo.png");
        tablero.setLayout(new BorderLayout());
        add(tablero, BorderLayout.CENTER);

        showTypesPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (anteriorFrame != null) {
                    anteriorFrame.setVisible(true);
                }
            }
        });
    }

    private void showTypesPanel() {
        if (currentPanel != null) {
            tablero.remove(currentPanel);
        }

        currentPanel = new JPanel();
        currentPanel.setOpaque(false);
        currentPanel.setLayout(new GridLayout(6, 3, 10, 10));

        JLabel label = new JLabel("Selecciona un tipo de movimiento:", SwingConstants.CENTER);
        currentPanel.add(label);

        for (String tipo : Tipo.getTipos()) {
            Button tipoButton = new Button(tipo, "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width/6, height / 6);
            tipoButton.addActionListener(e -> showMovesPanel(tipo));
            currentPanel.add(tipoButton);
        }

        Button volverButton = new Button( "/Recursos/Imagenes/atras_boton.png", false, width / 4, height / 10);
        volverButton.addActionListener(e -> this.dispose());
        currentPanel.add(volverButton);


        tablero.add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showMovesPanel(String tipo) {
        if (currentPanel != null) {
            tablero.remove(currentPanel);
        }

        currentPanel = new JPanel();
        currentPanel.setOpaque(false);
        currentPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel movLabel = new JLabel("Selecciona un movimiento:", SwingConstants.CENTER);
        currentPanel.add(movLabel);

        Tipo tipoObj = new Tipo();
        for(Movimiento m :tipoObj.getTiposMovimientos(tipo)){
            Button movButton = new Button(m.getNombre() + " (Potencia: " + m.getPotencia() + ")", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);
            movButton.addActionListener(e -> showReplacePanel(m));
            currentPanel.add(movButton);
        }


        Button volverButton = new Button("/Recursos/Imagenes/atras_boton.png", false, width / 4, height / 10);
        volverButton.addActionListener(e -> showTypesPanel());
        currentPanel.add(volverButton);

        tablero.add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showReplacePanel(Movimiento newMove) {
        if (currentPanel != null) {
            tablero.remove(currentPanel);
        }

        currentPanel = new JPanel();
        currentPanel.setOpaque(false);
        currentPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel replaceLabel = new JLabel("Selecciona el movimiento a reemplazar:", SwingConstants.CENTER);
        currentPanel.add(replaceLabel);

        ArrayList<Movimiento> currentMoves = pokemon.getPokeMoves();
        for (Movimiento current : currentMoves) {
            Button replaceButton = new Button(current.getNombre(), "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);
            replaceButton.addActionListener(e -> {
                pokemon.cambiarMovimiento(newMove, current);
                JOptionPane.showMessageDialog(this, "Movimiento " + newMove.getNombre() + " aprendido.");
                showTypesPanel();
            });
            currentPanel.add(replaceButton);
        }


        Button volverButton = new Button( "/Recursos/Imagenes/atras_boton.png", false, width / 4, height / 10);
        volverButton.addActionListener(e -> showMovesPanel(newMove.getTipo()));
        currentPanel.add(volverButton);

        tablero.add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }






}