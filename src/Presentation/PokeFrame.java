package Presentation;

import Domain.Pokemon;
import Domain.PokemonsInGame;
import Domain.ItemsInGame;
import Domain.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class PokeFrame extends JFrame{
    private JPanel tablero;
    private PokemonsInGame pokemonsInGame;
    private Button modoNormal;
    private Button modoSupervivencia;
    private Button volverFrame;
    private JFrame anteriorFrame;
    private GameTypeListener tipoListener;
    private JPanel gameTypePanel;
    private ArrayList<Pokemon> player1Pokemons;
    private ArrayList<Item> player1Items;
    private ArrayList<Pokemon> player2Pokemons;
    private ArrayList<Item> player2Items;
    private String nameTeam1, nameTeam2;

    public PokeFrame(String nombre, JFrame anteriorFrame, Boolean isGameMode, Boolean isGameType,String type) {
        setTitle(nombre);
        this.anteriorFrame = anteriorFrame;
        this.player1Pokemons = new ArrayList<>();
        this.player1Items = new ArrayList<>();
        this.player2Pokemons = new ArrayList<>();
        this.player2Items = new ArrayList<>();
        if (isGameMode) {
            prepareGameMode();
        } else if (isGameType) {
            prepareGameType(type);
        }
        prepareActions();
    }

    public PokeFrame(PokemonsInGame pokemonsInGame) {
        this.pokemonsInGame = pokemonsInGame;
        setTitle("Pokemones");
        preparePokemons();
        prepareActions();
    }

    public void preparePokemons() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1200;
        int height = 800;
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);

        tablero = new PoobkemonFondoPrincipal("/Recursos/Imagenes/pokemon_campo.png");
        tablero.setLayout(new GridLayout(6, 6, 10, 10));
        add(tablero, BorderLayout.CENTER);

        for (Pokemon pokemon : pokemonsInGame.getPokemons()) {
            Button pokeButton = new Button(pokemon.getIcono(), false, width, height);
            pokeButton.addActionListener(e -> {
                JFrame infoFrame = new JFrame("Información de " + pokemon.getNombre());
                PanelPokemon panel = new PanelPokemon("/Recursos/Imagenes/Texto_info.png", pokemon);
                infoFrame.setLayout(new BorderLayout());
                infoFrame.add(panel, BorderLayout.SOUTH);
                infoFrame.pack();
                infoFrame.setLocationRelativeTo(null);
                infoFrame.setVisible(true);
                infoFrame.setResizable(false);
            });
            tablero.add(pokeButton);
        }
    }

        public void prepareGameMode() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1200;
        int height = 800;
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);

        tablero = new PoobkemonFondoPrincipal("/Recursos/Imagenes/Fondo_opciones.png");
        tablero.setLayout(new BorderLayout());
        add(tablero, BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));

        modoNormal = new Button("Normal", "/Recursos/Imagenes/Texto_opciones.png", true, width, height / 4);
        modoSupervivencia = new Button("Supervivencia", "/Recursos/Imagenes/Texto_opciones.png", true, width, height / 4);

        modoNormal.setAlignmentX(Component.LEFT_ALIGNMENT);
        modoSupervivencia.setAlignmentX(Component.LEFT_ALIGNMENT);

        botonesPanel.add(Box.createHorizontalGlue());
        botonesPanel.add(modoNormal);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(modoSupervivencia);
        botonesPanel.add(Box.createHorizontalGlue());

        tablero.add(botonesPanel, BorderLayout.NORTH);

        volverFrame = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
        tablero.add(volverFrame, BorderLayout.PAGE_END);
    }

    public void prepareGameType(String  type) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1200;
        int height = 800;
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);

        tablero = new PoobkemonFondoPrincipal("/Recursos/Imagenes/Fondo_opciones_tipo.png");
        tablero.setLayout(new BorderLayout());
        add(tablero, BorderLayout.CENTER);

        gameTypePanel = new JPanel();
        gameTypePanel.setOpaque(false);
        gameTypePanel.setLayout(new BoxLayout(gameTypePanel, BoxLayout.Y_AXIS));

        Button playerVsPlayer = new Button("PvP", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);
        Button playerVsMachine = new Button("PvM", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);
        Button machineVsMachine = new Button("MvM", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);

        playerVsPlayer.setAlignmentX(Component.LEFT_ALIGNMENT);
        playerVsMachine.setAlignmentX(Component.LEFT_ALIGNMENT);
        machineVsMachine.setAlignmentX(Component.LEFT_ALIGNMENT);

        gameTypePanel.add(Box.createHorizontalGlue());
        gameTypePanel.add(playerVsPlayer);
        if(type == "Normal") {
            gameTypePanel.add(Box.createRigidArea(new Dimension(0, 10)));
            gameTypePanel.add(playerVsMachine);
            gameTypePanel.add(Box.createRigidArea(new Dimension(0, 10)));
            gameTypePanel.add(machineVsMachine);
        }

        tablero.add(gameTypePanel, BorderLayout.NORTH);

        volverFrame = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
        tablero.add(volverFrame, BorderLayout.PAGE_END);

        playerVsPlayer.addActionListener(e -> {
            preparePlayerOption("PvP");
        });

        playerVsMachine.addActionListener(e -> {
            prepareMachineOption("PvM");
        });

        machineVsMachine.addActionListener(e -> {
            prepareMachineOption("MvM");
        });
    }

    public void prepareMachineOption(String gameType) {
        tablero.removeAll();
        tablero.setLayout(new BorderLayout());

        int width = 1200;
        int height = 800;

        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));

        Button defensivo = new Button("Máquina Defensiva", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);
        Button ofensivo = new Button("Máquina Ofensiva", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);
        Button cambiante = new Button("Máquina Cambiante", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);
        Button experto = new Button("Máquina Experta", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);

        defensivo.setAlignmentX(Component.LEFT_ALIGNMENT);
        ofensivo.setAlignmentX(Component.LEFT_ALIGNMENT);
        cambiante.setAlignmentX(Component.LEFT_ALIGNMENT);
        experto.setAlignmentX(Component.LEFT_ALIGNMENT);

        botonesPanel.add(Box.createHorizontalGlue());
        botonesPanel.add(defensivo);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(ofensivo);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(cambiante);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(experto);

        tablero.add(botonesPanel, BorderLayout.NORTH);

        Button volverMachineButton = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
        volverMachineButton.addActionListener(e -> {
            tablero.removeAll();
            tablero.setLayout(new BorderLayout());
            tablero.add(gameTypePanel, BorderLayout.NORTH);
            tablero.add(volverFrame, BorderLayout.PAGE_END);
            revalidate();
            repaint();
        });

        tablero.add(volverMachineButton, BorderLayout.PAGE_END);

        defensivo.addActionListener(e -> {
            String tipo = "Defensiva";
            if ("PvM".equals(gameType)) {
                PokeTeamCreation seleccion = new PokeTeamCreation(gameType, this, 1);
                seleccion.setSelectionListener((gType, mTypeIgnored, selectedPokemons, selectedItems,nombreTeam) -> {
                    player1Pokemons = new ArrayList<>(selectedPokemons);
                    player1Items = new ArrayList<>(selectedItems);
                    if (tipoListener != null) {
                        this.setVisible(false);
                        tipoListener.onGameTypeSelected(gameType, tipo, player1Pokemons, player1Items, null, null,nombreTeam,null);
                    }
                });
                seleccion.setVisible(true);
                this.setVisible(false);
            } else {
                if (tipoListener != null) {
                    this.setVisible(false);
                    tipoListener.onGameTypeSelected(gameType, tipo, null, null, null, null,null,null);
                }
            }
        });




        ofensivo.addActionListener(e -> {
            String tipo = "Ofensiva";
            if ("PvM".equals(gameType)) {
                PokeTeamCreation seleccion = new PokeTeamCreation(gameType, this, 1);
                seleccion.setSelectionListener((gType, mTypeIgnored, selectedPokemons, selectedItems,nombreTeam) -> {
                    player1Pokemons = new ArrayList<>(selectedPokemons);
                    player1Items = new ArrayList<>(selectedItems);
                    if (tipoListener != null) {
                        tipoListener.onGameTypeSelected(gameType, tipo, player1Pokemons, player1Items, null, null,nombreTeam,null);
                        this.setVisible(false);
                    }
                });
                seleccion.setVisible(true);
                this.setVisible(false);
            } else {
                if (tipoListener != null) {
                    tipoListener.onGameTypeSelected(gameType, tipo, null, null, null, null,null,null);
                    this.setVisible(false);
                }
            }
        });

        cambiante.addActionListener(e -> {
            String tipo = "Cambiante";
            if ("PvM".equals(gameType)) {
                PokeTeamCreation seleccion = new PokeTeamCreation(gameType, this, 1);
                seleccion.setSelectionListener((gType, mTypeIgnored, selectedPokemons, selectedItems,nombreTeam) -> {
                    player1Pokemons = new ArrayList<>(selectedPokemons);
                    player1Items = new ArrayList<>(selectedItems);
                    if (tipoListener != null) {
                        tipoListener.onGameTypeSelected(gameType, tipo, player1Pokemons, player1Items, null, null,nombreTeam,null);
                        this.setVisible(false);
                    }
                });
                seleccion.setVisible(true);
                this.setVisible(false);
            } else {
                if (tipoListener != null) {
                    tipoListener.onGameTypeSelected(gameType, tipo, null, null, null, null,null,null);
                    this.setVisible(false);
                }
            }
        });

        experto.addActionListener(e -> {
            String tipo = "Experta";
            if ("PvM".equals(gameType)) {
                PokeTeamCreation seleccion = new PokeTeamCreation(gameType, this, 1);
                seleccion.setSelectionListener((gType, mTypeIgnored, selectedPokemons, selectedItems,nombreTeam) -> {
                    player1Pokemons = new ArrayList<>(selectedPokemons);
                    player1Items = new ArrayList<>(selectedItems);
                    if (tipoListener != null) {
                        tipoListener.onGameTypeSelected(gameType, tipo, player1Pokemons, player1Items, null, null,nombreTeam,null);
                        this.setVisible(false);
                    }
                });
                seleccion.setVisible(true);
                this.setVisible(false);
            } else {
                if (tipoListener != null) {
                    tipoListener.onGameTypeSelected(gameType, tipo, null, null, null, null,null,null);
                    this.setVisible(false);
                }
            }
        });

        revalidate();
        repaint();
    }

    public void preparePlayerOption(String gameType) {
        tablero.removeAll();
        tablero.setLayout(new BorderLayout());

        int width = 1200;
        int height = 800;

        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));

        Button player1 = new Button("Jugador 1", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);
        Button player2 = new Button("Jugador 2", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);
        Button jugar = new Button("Jugar", "/Recursos/Imagenes/Texto_opciones_tipo.png", true, width, height / 5);

        player1.setAlignmentX(Component.LEFT_ALIGNMENT);
        player2.setAlignmentX(Component.LEFT_ALIGNMENT);
        jugar.setAlignmentX(Component.LEFT_ALIGNMENT);

        botonesPanel.add(Box.createHorizontalGlue());
        botonesPanel.add(player1);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(player2);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(jugar);

        tablero.add(botonesPanel, BorderLayout.NORTH);

        Button volverButton = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
        volverButton.addActionListener(e -> {
            tablero.removeAll();
            tablero.setLayout(new BorderLayout());
            tablero.add(gameTypePanel, BorderLayout.NORTH);
            tablero.add(volverFrame, BorderLayout.PAGE_END);
            revalidate();
            repaint();
        });

        tablero.add(volverButton, BorderLayout.PAGE_END);

        player1.addActionListener(e -> {
            PokeTeamCreation seleccion = new PokeTeamCreation(gameType,  this, 1);
            seleccion.setSelectionListener((gType, mType, selectedPokemons, selectedItems,nombreTeam) -> {
                player1Pokemons = new ArrayList<>(selectedPokemons);
                player1Items = new ArrayList<>(selectedItems);
                nameTeam1 = nombreTeam;
                this.setVisible(true);
            });
            seleccion.setVisible(true);
            this.setVisible(false);
        });

        player2.addActionListener(e -> {
            PokeTeamCreation seleccion = new PokeTeamCreation(gameType,  this, 2);
            seleccion.setSelectionListener((gType, mType, selectedPokemons, selectedItems,nombreTeam) -> {
                player2Pokemons = new ArrayList<>(selectedPokemons);
                player2Items = new ArrayList<>(selectedItems);
                nameTeam2 = nombreTeam;
                this.setVisible(true);
            });
            seleccion.setVisible(true);
            this.setVisible(false);
        });

        jugar.addActionListener(e -> {
            if (player1Pokemons.isEmpty() || player1Items.isEmpty() || player2Pokemons.isEmpty() || player2Items.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ambos jugadores deben seleccionar al menos un Pokémon y un ítem.");
                return;
            }
            if (tipoListener != null) {
                tipoListener.onGameTypeSelected(gameType, null, player1Pokemons, player1Items, player2Pokemons, player2Items,nameTeam1,nameTeam2);
                this.setVisible(false);
            }
        });

        revalidate();
        repaint();
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

        if (modoSupervivencia != null && modoNormal != null) {
            modoSupervivencia.addActionListener(e -> {
                if (tipoListener != null) {
                    tipoListener.onGameTypeSelected("PvP", null, null, null, null, null,"Equipo 1","Equipo 2");
                    this.setVisible(false);
                }
            });
            modoNormal.addActionListener(e -> {
                this.setVisible(false);
                PokeFrame pokeFrame = new PokeFrameBuilder()
                        .setTitle("Modo Normal")
                        .setAnteriorFrame(this)
                        .setGameType()
                        .setGameTypeString("Normal")
                        .setGameTypeListener(tipoListener)
                        .build();
                pokeFrame.setVisible(true);
            });
        }
    }



    public void setGameTypeListener(GameTypeListener listener) {
        this.tipoListener = listener;
    }
}