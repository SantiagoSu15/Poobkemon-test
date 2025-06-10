package Presentation;

import Domain.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public  class PokeTeamCreation extends JFrame {
    private JPanel tablero;
    private Button pokeSeleccionButton, itemsButton, jugarParaSeleccionar, volverFrame,editarPokemons,nombreButton;
    private JFrame anteriorFrame;
    private ArrayList<Pokemon> selectedPokemons;
    private ArrayList<Item> selectedItems;
    private SelectionListener selectionListener;
    private String gameType,nombreTeam;
    private PokemonsInGame pokemonsInGame;
    private ItemsInGame itemsInGame;
    private JPanel botonesPanel;
    private int playerNumber;
    private static final int pokeMinimos = 6;
    private static final int maxItems = 4;
    private JDialog dialogNombre= null;


    public PokeTeamCreation(String gameType,  JFrame anteriorFrame, int playerNumber) {
        setTitle(gameType + " - Jugador " + playerNumber);
        this.gameType = gameType;
        this.anteriorFrame = anteriorFrame;
        this.playerNumber = playerNumber;
        this.pokemonsInGame = new PokemonsInGame();
        this.itemsInGame = new ItemsInGame();
        selectedPokemons = new ArrayList<>();
        selectedItems = new ArrayList<>();
        prepareSelectionFrame();
        prepareActions();
    }


    public void prepareSelectionFrame() {
        int width = 1200;
        int height = 800;
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);

        tablero = new PoobkemonFondoPrincipal("/Recursos/Imagenes/Fondo_opciones_tipo.png");
        tablero.setLayout(new BorderLayout());
        add(tablero, BorderLayout.CENTER);

        botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));

        pokeSeleccionButton = new Button("Pokemones", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 7);
        itemsButton = new Button("Items", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 7);
        jugarParaSeleccionar = new Button("Jugar", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 7);
        editarPokemons = new Button("Editar Pokemones", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height/7);
        nombreButton = new Button("Nombre Del Equipo", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height/7);

        pokeSeleccionButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemsButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        jugarParaSeleccionar.setAlignmentX(Component.LEFT_ALIGNMENT);
        editarPokemons.setAlignmentX(Component.LEFT_ALIGNMENT);
        nombreButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        botonesPanel.add(Box.createHorizontalGlue());
        botonesPanel.add(pokeSeleccionButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(itemsButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(nombreButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(jugarParaSeleccionar);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(editarPokemons);


        tablero.add(botonesPanel, BorderLayout.NORTH);
        volverFrame = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
        tablero.add(volverFrame, BorderLayout.PAGE_END);
    }



    public void preparePokemons() {
        tablero.removeAll();
        tablero.setLayout(new BorderLayout());
        Font fuente = cargarFuentePersonalizada();
        int width = 1200;
        int height = 800;

        JPanel pokemonPanel = new JPanel();
        pokemonPanel.setOpaque(false);
        pokemonPanel.setLayout(new GridLayout(6, 6, 10, 10));

        for (Pokemon pokemon : pokemonsInGame.getPokemons()) {
            Button pokeButton = new Button(pokemon.getIcono(), false, width, height);
            pokeButton.addActionListener(e -> {
                JDialog infoFrame = new JDialog(this, pokemon.getNombre(), true);
                infoFrame.setTitle(pokemon.getNombre());
                PanelPokemon panel = new PanelPokemon("/Recursos/Imagenes/Poke_info_seleccion.png", pokemon, true);
                panel.setLayout(new BorderLayout());
                ImageIcon originalIcon = new ImageIcon(getClass().getResource(pokemon.getIcono()));
                Image scaledImage = originalIcon.getImage().getScaledInstance(350, 400, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                JLabel pokemonPlayerLabel = new JLabel(scaledIcon);
                pokemonPlayerLabel.setBorder(new LineBorder(Color.GREEN, 2));
                pokemonPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
                pokemonPlayerLabel.setBounds(0, 7, 350, 400);

                JPanel westPanel = new JPanel();
                westPanel.setLayout(null);
                westPanel.setOpaque(false);
                westPanel.setPreferredSize(new Dimension(350, 180));
                westPanel.add(pokemonPlayerLabel);

                JPanel movimientosPanel = new JPanel(new GridLayout(2, 2, 0, 0));
                movimientosPanel.setBounds(180, 85, 345, 280);
                movimientosPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                movimientosPanel.setOpaque(false);
                for(Movimiento m : pokemon.getPokeMoves()){
                    JLabel textLabel= new JLabel();
                    textLabel.setHorizontalAlignment(JLabel.CENTER);
                    textLabel.setOpaque(false);
                    textLabel.setText(m.getNombre());
                    textLabel.setFont(fuente);
                    movimientosPanel.add(textLabel);

                }

                JPanel centerPanel = new JPanel();
                centerPanel.setLayout(null);
                centerPanel.setOpaque(false);
                centerPanel.add(movimientosPanel);

                JPanel descripcionPanel = new JPanel();
                descripcionPanel.setOpaque(false);
                descripcionPanel.setPreferredSize(new Dimension(700, 150));
                descripcionPanel.setBorder(new LineBorder(Color.RED, 2));


                JTextArea descripcionArea = new JTextArea(pokemon.getDescripcion());
                descripcionArea.setLineWrap(true);
                descripcionArea.setWrapStyleWord(true);
                descripcionArea.setFont(fuente);
                descripcionArea.setEditable(false);
                descripcionArea.setOpaque(false);
                descripcionArea.setFocusable(false);

                descripcionPanel.setLayout(new BorderLayout());
                descripcionPanel.add(descripcionArea, BorderLayout.CENTER);

                JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                southPanel.setOpaque(false);
                southPanel.setPreferredSize(new Dimension(0, 180));
                southPanel.add(descripcionPanel);

                southPanel.setBorder(new LineBorder(Color.BLUE, 2));
                westPanel.setBorder(new LineBorder(Color.yellow, 2));
                centerPanel.setBorder(new LineBorder(Color.MAGENTA, 2));
                movimientosPanel.setBorder(new LineBorder(Color.black, 2));

                panel.add(southPanel, BorderLayout.SOUTH);
                panel.add(westPanel, BorderLayout.WEST);
                panel.add(centerPanel, BorderLayout.CENTER);



                infoFrame.setLayout(new BorderLayout());
                infoFrame.add(panel, BorderLayout.SOUTH);
                JButton selectButton = new JButton("Seleccionar");
                JButton noSelectButton = new JButton("No Lo Quiero");
                selectButton.addActionListener(ev -> {
                    addSelectedPokemon(pokemon);
                    infoFrame.dispose();
                });
                noSelectButton.addActionListener(ev -> {
                    infoFrame.dispose();
                });
                JPanel northPanel = new JPanel(new GridLayout(1, 2));
                northPanel.add(selectButton);
                northPanel.add(noSelectButton);
                infoFrame.add(northPanel, BorderLayout.NORTH);

                infoFrame.pack();
                infoFrame.setLocationRelativeTo(null);
                infoFrame.setVisible(true);
                infoFrame.setResizable(false);
            });
            pokeButton.addMouseListener(new MouseAdapter() {
                Dimension tamano = pokeButton.getPreferredSize();
                @Override
                public void mouseEntered(MouseEvent e) {
                    Icon icono = new ImageIcon(getClass().getResource(pokemon.getFotoBack()));
                    if(icono != null){
                        pokeButton.setIcon(icono);
                    }else if(icono == null){
                        icono = new ImageIcon(getClass().getResource(pokemon.getFotoFrontal()));
                        pokeButton.setIcon(icono);
                    }else{
                        icono = new ImageIcon(getClass().getResource(pokemon.getIcono()));
                        pokeButton.setIcon(icono);
                    }

                    pokeButton.revalidate();
                    pokeButton.repaint();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    pokeButton.setPreferredSize(tamano);
                    pokeButton.setIcon(new ImageIcon(getClass().getResource(pokemon.getIcono())));
                    pokeButton.revalidate();
                    pokeButton.repaint();
                }

            });


            pokemonPanel.add(pokeButton);
        }

        tablero.add(pokemonPanel, BorderLayout.CENTER);

        Button volverPokemonsButton = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
        volverPokemonsButton.addActionListener(e -> reloadPanel());

        tablero.add(volverPokemonsButton, BorderLayout.PAGE_END);

        revalidate();
        repaint();
    }


    public void preparePokemonsTeam(ArrayList<Pokemon> pokemons) {
        tablero.removeAll();
        tablero.setLayout(new BorderLayout());

        int width = 1200;
        int height = 800;
        JPanel pokemonPanel = new JPanel();
        pokemonPanel.setOpaque(false);
        pokemonPanel.setLayout(new GridLayout(2, 3, 10, 10));
        for (Pokemon pokemon : pokemons) {
            Button pokeButton = new Button(pokemon.getIcono(), false, width / 3, height / 2);
            pokeButton.addActionListener(e -> {
                PokeMovementsFrame editor = new PokeMovementsFrame(pokemon,this,width -200, height -200);
                editor.setVisible(true);
                this.setVisible(false);
            });
            pokemonPanel.add(pokeButton);
        }

        tablero.add(pokemonPanel, BorderLayout.CENTER);

        Button volverPokemonsButton = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
        volverPokemonsButton.addActionListener(e -> reloadPanel());
        tablero.add(volverPokemonsButton, BorderLayout.PAGE_END);

        revalidate();
        repaint();
    }


    public void prepareItems() {
        tablero.removeAll();
        tablero.setLayout(new BorderLayout());
        int width = 1200;
        int height = 800;
        JPanel itemsPanel = new JPanel();
        itemsPanel.setOpaque(false);
        itemsPanel.setLayout(new GridLayout(6, 6, 10, 10));

        for (Item item : itemsInGame.getItems()) {
            Button itemButton = new Button(item.getIcono(), false, width, height);
            itemButton.addActionListener(e -> {
                JFrame infoFrame = new JFrame("Información " + item.getNombre());
                JLabel infoLabel = new JLabel("Ítem: " + item.getNombre(), SwingConstants.CENTER); // Reemplaza con PanelItem si existe
                infoFrame.setLayout(new BorderLayout());
                infoFrame.add(infoLabel, BorderLayout.CENTER);
                JButton selectButton = new JButton("Seleccionar");
                selectButton.addActionListener(ev -> {
                    addSelectedItem(item);
                    infoFrame.dispose();
                });
                infoFrame.add(selectButton, BorderLayout.NORTH);
                infoFrame.pack();
                infoFrame.setLocationRelativeTo(null);
                infoFrame.setVisible(true);
                infoFrame.setResizable(false);
            });
            itemsPanel.add(itemButton);
        }
        tablero.add(itemsPanel, BorderLayout.CENTER);

        Button volverItemsButton = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
        volverItemsButton.addActionListener(e -> reloadPanel());

        tablero.add(volverItemsButton, BorderLayout.PAGE_END);

        revalidate();
        repaint();
    }









    private void reloadPanel() {
        tablero.removeAll();
        tablero.setLayout(new BorderLayout());
        tablero.add(botonesPanel, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    public void addSelectedPokemon(Pokemon pokemon) {
        if (selectedPokemons.size() >= pokeMinimos) {
            JOptionPane.showMessageDialog(this, "No puedes seleccionar más de " + pokeMinimos + " Pokémon.");
            return;
        }
        selectedPokemons.add(pokemon);
        JOptionPane.showMessageDialog(this, "Pokémon " + pokemon.getNombre() + " añadido.");

    }

    public void addSelectedItem(Item item) {
        if (selectedItems.size() >= maxItems) {
            JOptionPane.showMessageDialog(this, "No puedes seleccionar más de " + maxItems + " ítems.");
            return;
        }
        if (!selectedItems.contains(item)) {
            selectedItems.add(item);
            JOptionPane.showMessageDialog(this, "Ítem " + item.getNombre() + " añadido.");
        } else {
            JOptionPane.showMessageDialog(this, "Ítem " + item.getNombre() + " ya está seleccionado.");
        }
    }

    public void prepareActions() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        if (volverFrame != null) {
            volverFrame.addActionListener(e -> {
                if (anteriorFrame != null) {
                    anteriorFrame.setVisible(true);
                }
                this.dispose();
            });

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (anteriorFrame != null) {
                        anteriorFrame.setVisible(true);
                    }
                }
            });
        }

        if (pokeSeleccionButton != null) {
            pokeSeleccionButton.addActionListener(e -> preparePokemons());
        }

        if (itemsButton != null) {
            itemsButton.addActionListener(e -> prepareItems());
        }

        if(editarPokemons != null) {
            editarPokemons.addActionListener(e -> preparePokemonsTeam(selectedPokemons));
        }
        if(nombreButton != null) {
            nombreButton.addActionListener(e ->{
                if (dialogNombre != null && dialogNombre.isShowing()) {
                    return;
                }
                dialogNombre = new JDialog();
                dialogNombre.setTitle("Elegir nombre de equipo");
                JPanel panel = new PoobkemonFondoPrincipal("/Recursos/Imagenes/options_panel.png");
                panel.setPreferredSize(new Dimension(800, 400));
                panel.setOpaque(false);
                dialogNombre.setLayout(new FlowLayout(FlowLayout.CENTER));
                dialogNombre.add(panel);

                JTextField campoTexto = new JTextField(20);
                panel.add(campoTexto);

                dialogNombre.pack();
                dialogNombre.setLocationRelativeTo(null);
                dialogNombre.setVisible(true);


                campoTexto.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        nombreTeam = campoTexto.getText();
                        System.out.println(nombreTeam);
                        campoTexto.setText("");
                        dialogNombre.dispose();
                    }
                });


            });
        }

        if(jugarParaSeleccionar!= null){
            jugarParaSeleccionar.addActionListener(e -> {
                int resta = pokeMinimos - selectedPokemons.size();
                if (selectedPokemons.isEmpty() || selectedItems.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debes seleccionar al menos un Pokémon y un ítem.");
                    return;
                } else if (selectedPokemons.size() < pokeMinimos) {
                    JOptionPane.showMessageDialog(this, "Te quedan " +  resta + " Pokémon .");
                }else if (selectionListener != null) {
                    selectionListener.onSelectionCompleted(gameType, null, selectedPokemons, selectedItems,nombreTeam);
                    this.dispose();
                }

            });

        }

    }

    public void setSelectionListener(SelectionListener listener) {
        this.selectionListener = listener;
    }

    private Font cargarFuentePersonalizada() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/Recursos/Fuentes/pokemon-emerald.ttf");
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            return fuente.deriveFont(Font.BOLD, 35f);
        } catch (IOException | FontFormatException e) {
            System.out.println("Error al cargar la fuente personalizada: " + e.getMessage());
            return null;
        }
    }


}

