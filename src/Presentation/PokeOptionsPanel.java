package Presentation;

import Domain.PoobkemonGame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PokeOptionsPanel extends PoobkemonFondoPrincipal {
    private Button cargar, guardar ;
    private JDialog dad;
    private PoobkemonGame game;
    private GameLoaListener loadListener;

    public PokeOptionsPanel(int width, int height,JDialog dad, PoobkemonGame game, GameLoaListener loadListener) {
        super("/Recursos/Imagenes/options_panel.png");
        this.dad = dad;
        this.game = game;
        this.loadListener = loadListener;
        setBackground(new Color(0, 0, 255, 100));
        setOpaque(false);
        setPreferredSize(new Dimension(width, height));
        setLayout(new FlowLayout(FlowLayout.CENTER));


        cargar = new Button("Cargar Partida","/Recursos/Imagenes/optionsBoton.png",true,width-50,150);
        guardar = new Button("Guardar Partida","/Recursos/Imagenes/optionsBoton.png",true,width-50,150);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(cargar);
        add(guardar);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        Button volverPokemonsButton = new Button("/Recursos/Imagenes/atras_boton.png", false, 20, 20);
        panel.add(volverPokemonsButton);
        add(panel,BorderLayout.SOUTH);
        volverPokemonsButton.addActionListener(e -> dad.dispose());


        cargar.addActionListener(e -> {
            String nombreArchivo = JOptionPane.showInputDialog(
                    null,
                    "Nombre a Abrir",
                    "Abrir Partida",
                    JOptionPane.PLAIN_MESSAGE
            );
            try{
                PoobkemonGame juego = OpenBattle(nombreArchivo);
                loadListener.onGameLoaded(juego);
                dad.dispose();

            }catch (IOException | ClassNotFoundException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }


        });
        guardar.addActionListener(e -> {
            String nombreArchivo = JOptionPane.showInputDialog(
                    null,
                    "Nombre a guardar",
                    "Guardar partida",
                    JOptionPane.PLAIN_MESSAGE
            );

            try {
                if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
                    if (!nombreArchivo.endsWith(".ser")) {
                        nombreArchivo += ".ser";
                    }
                    SaveBattle(nombreArchivo, game);
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de archivo inválido.");
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage());
            }
        });

        if (dad.getOwner() instanceof PokeBatallaFrame) {
            Button pausarButton = new Button("Pausar Partida","/Recursos/Imagenes/optionsBoton.png",true,width-50,150);
            add(pausarButton);

            pausarButton.addActionListener(e -> {
                PokeBatallaFrame padre = (PokeBatallaFrame) dad.getOwner();
                padre.pausarTimer();
                dad.getOwner().setVisible(false);
                dad.dispose();
                JDialog pausarPartidaDialogo = new JDialog();
                pausarPartidaDialogo.setTitle("Partida en pausa");

                JPanel pausarPanel = new PoobkemonFondoPrincipal("/Recursos/Imagenes/options_panel.png");
                pausarPanel.setPreferredSize(new Dimension(width, height));
                pausarPanel.setOpaque(false);
                pausarPartidaDialogo.setLayout(new FlowLayout(FlowLayout.CENTER));
                pausarPartidaDialogo.add(pausarPanel);


                Button volverAtras = new Button("Volver Partida", "/Recursos/Imagenes/optionsBoton.png", true, width - 50, 150);
                pausarPanel.add(volverAtras);

                volverAtras.addActionListener(ev -> {
                    pausarPartidaDialogo.dispose();
                    dad.getOwner().setVisible(true);
                    padre.comenzarTimer();
                });
                pausarPartidaDialogo.pack();
                pausarPartidaDialogo.setLocationRelativeTo(null);
                pausarPartidaDialogo.setVisible(true);
            });

        }


    }

    public void setGameLoadListener(GameLoaListener listener) {
        this.loadListener = listener;
    }




    public void SaveBattle(String nombre, PoobkemonGame game) throws IOException {
        FileOutputStream fichero = new FileOutputStream(nombre);
        ObjectOutputStream obejtoBytes = new ObjectOutputStream(fichero);
        obejtoBytes.writeObject(game);
        obejtoBytes.close();
    }

    public PoobkemonGame OpenBattle(String nombre) throws IOException, ClassNotFoundException {
        FileInputStream fichero = new FileInputStream(nombre);
        ObjectInputStream objetoBytes = new ObjectInputStream(fichero);
        PoobkemonGame game = (PoobkemonGame) objetoBytes.readObject();
        objetoBytes.close();
        return game;
    }

}
