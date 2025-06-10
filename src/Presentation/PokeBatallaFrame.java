package Presentation;

import Domain.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Timer;


public class PokeBatallaFrame extends JFrame {
    private JPanel tablero;
    private Button volverFrame, fightButton, runButton, bagButton, pokemonButton,pausarButton;
    private PoobkemonGame game;
    private JFrame anteriorFrame;
    private JPanel botonesPanel;
    public JPanel currentPanel;
    private Team team1Data, team2Data;
    private BattleListener battleListener;
    private JLabel tiempoLabel,pokemonPlayerLabel, pokemonOpponentLabel;
    private String gameType;
    private int segundosTimer = 0;
    private Timer timer;

    public PokeBatallaFrame(PoobkemonGame game, JFrame anteriorFrame, Team team1, Team team2, String gameType) {
        this.setTitle("PokeBatalla");
        this.game = game;
        this.anteriorFrame = anteriorFrame;
        this.team1Data = team1;
        this.team2Data = team2;
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        prepareElements();
        prepareActions();
        this.gameType = gameType.toUpperCase();
        if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
            checkMachineTurn();
        }
    }

    private void prepareElements() {
        tablero = new PoobkemonFondoPrincipal("/Recursos/Imagenes/Batalla/Fondo_Arena_opciones(2).png");
        tablero.setLayout(new BorderLayout());
        add(tablero, BorderLayout.CENTER);
        prepareBattle();
    }

        public void preparePokemonsTeam(ArrayList<Pokemon> pokemons, boolean isTeam1) {
            tablero.removeAll();
            tablero.setLayout(new BorderLayout());

            int width = 1200, height = 800;
            currentPanel = new JPanel();
            currentPanel.setOpaque(false);
            currentPanel.setLayout(new GridLayout(2, 3, 10, 10));
            for (Pokemon pokemon : pokemons) {
                Button pokeButton = new Button(pokemon.getIcono(), false, width / 3, height / 2);
                pokeButton.addActionListener(e -> {
                    if (battleListener != null) {
                        battleListener.onPokemonChanged(isTeam1 ? team1Data : team2Data, pokemon);
                        //String battleMessage = game.executeBattle();
                        //updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                        if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
                            checkMachineTurn();
                        }
                    }
                });
                currentPanel.add(pokeButton);
            }

            Button volverPokemonsButton = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
            volverPokemonsButton.addActionListener(e -> {
                prepareBattle();
                if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
                    checkMachineTurn();
                }
            });
            tablero.add(volverPokemonsButton, BorderLayout.PAGE_END);
            tablero.add(currentPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }

    public void prepareBattle() {
        tablero.removeAll();
        tablero.setLayout(new BorderLayout());
        reiniciarTimer();
        comenzarTimer();

        Font fuente = cargarFuentePersonalizada();
        currentPanel = new PoobkemonFondoPrincipal("/Recursos/Imagenes/Batalla/Fondo_Arena_opciones(2).png");
        currentPanel.setLayout(new BorderLayout());
        currentPanel.setOpaque(false);






        JPanel miniPanel = new JPanel();
        miniPanel.setOpaque(false);
        miniPanel.setLayout(new GridLayout(2, 2, 0, 0));
        miniPanel.setPreferredSize(new Dimension(570, 210));

        JPanel pokemonPanel = new JPanel();
        pokemonPanel.setOpaque(false);
        pokemonPanel.setLayout(null);

        Pokemon pokeActual = game.getTeamActual() != null ? game.getTeamActual().getPokeActual() : null;
        if (pokeActual != null) {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(pokeActual.getFotoBack()));
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            pokemonPlayerLabel = new JLabel(scaledIcon);
            pokemonPlayerLabel.setBorder(new LineBorder(Color.GREEN, 2));
            pokemonPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
            pokemonPlayerLabel.setBounds(160, 270, 300, 300);
            pokemonPanel.add(pokemonPlayerLabel);
            barraVida barra = new barraVida(864,420,265,28,pokeActual.getMaxPs());
            barra.setVidaActual(pokeActual.getPs());
            barra.setOpaque(false);
            pokemonPanel.add(barra);


            JLabel nombrelabel = new JLabel(pokeActual.getNombre());
            nombrelabel.setFont(fuente);
            nombrelabel.setBounds(700, 360, 350, 50);
            pokemonPanel.add(nombrelabel);

            JLabel vidaLabel = new JLabel(String.valueOf(pokeActual.getPs()));
            vidaLabel.setFont(fuente);
            vidaLabel.setBounds(1030, 360, 100, 50);
            pokemonPanel.add(vidaLabel);

        }

        Pokemon pokeActualOponente = game.getTeamActual() == team1Data ? team2Data.getPokeActual() : team1Data.getPokeActual();
        if (pokeActualOponente != null) {
            ImageIcon originalIcon2 = new ImageIcon(getClass().getResource(pokeActualOponente.getFotoFrontal()));
            Image scaledImage2 = originalIcon2.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
            pokemonOpponentLabel = new JLabel(scaledIcon2);
            pokemonOpponentLabel.setBorder(new LineBorder(Color.GREEN, 2));
            pokemonOpponentLabel.setHorizontalAlignment(JLabel.CENTER);
            pokemonOpponentLabel.setBounds(750, 100, 250, 250);
            pokemonPanel.add(pokemonOpponentLabel);
            barraVida barra = new barraVida(228,148,260,28,pokeActualOponente.getMaxPs());
            barra.setVidaActual(pokeActualOponente.getPs());
            barra.setOpaque(false);
            pokemonPanel.add(barra);

            JLabel nombrelabel = new JLabel(pokeActualOponente.getNombre());
            nombrelabel.setFont(fuente);
            nombrelabel.setBounds(50, 85, 350, 50);
            pokemonPanel.add(nombrelabel);

            JLabel vidaLabel = new JLabel(String.valueOf(pokeActualOponente.getPs()));
            vidaLabel.setFont(fuente);
            vidaLabel.setBounds(380, 85, 100, 50);
            pokemonPanel.add(vidaLabel);
        }

        fightButton = new Button("FIGHT", 160, 75);
        bagButton = new Button("BAG", 160, 75);
        pokemonButton = new Button("POKEMON", 160, 75);
        runButton = new Button("RUN", 160, 75);

        fightButton.addActionListener(e -> {
            Team currentTeam = game.getTeamActual();
            if (currentTeam != null && currentTeam.getPokeActual() != null) {
                prepareFight(currentTeam.getPokeActual(), currentTeam);
            } else {
                JOptionPane.showMessageDialog(this, "Error: No hay Pokémon activo");
            }
        });

        bagButton.addActionListener(e -> {
            Team currentTeam = game.getTeamActual();
            if (currentTeam != null && !currentTeam.getInventario().isEmpty()) {
                prepareBag(currentTeam.getInventario(), currentTeam);
            } else {
                JOptionPane.showMessageDialog(this, "Error: No hay ítems disponibles");
            }
        });

        pokemonButton.addActionListener(e -> {
            Team currentTeam = game.getTeamActual();
            if (currentTeam != null && !currentTeam.getPokemons().isEmpty()) {
                preparePokemonsTeam(currentTeam.getPokemons(), currentTeam == team1Data);
            } else {
                JOptionPane.showMessageDialog(this, "Error: No hay Pokémon disponibles");
            }
        });

        runButton.addActionListener(e -> {
            if (battleListener != null) {
                battleListener.onRun(game.getTeamActual());
                String battleMessage = game.executeBattle();
                updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
                    checkMachineTurn();
                }

            }
        });

        miniPanel.add(fightButton);
        miniPanel.add(bagButton);
        miniPanel.add(pokemonButton);
        miniPanel.add(runButton);

        JLabel southLabel = new JLabel(game.getTeamActual().getName());
        southLabel.setFont(fuente);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setOpaque(false);
        southPanel.add(southLabel);
        southPanel.add(Box.createRigidArea(new Dimension(150, 220)));
        southPanel.add(miniPanel);
        southPanel.setPreferredSize(new Dimension(0, 220));




        JPanel cargarPanel = new JPanel();
        cargarPanel.setOpaque(false);
        cargarPanel.setLayout(null);
        cargarPanel.setBounds(10, 10, 50, 50);

        Button cargarButton = new Button("/Recursos/Imagenes/options.png", true, 50, 50);
        cargarButton.setBounds(0, 0, 50, 50);
        cargarButton.addActionListener(e -> {
            JDialog dialog = new JDialog(PokeBatallaFrame.this);

            GameLoaListener loadListener = new GameLoaListener(){
                @Override
                public void onGameLoaded(PoobkemonGame loadedGame) {
                    if (loadedGame == null) {
                        JOptionPane.showMessageDialog(PokeBatallaFrame.this,
                                "Error: No se pudo cargar la partida.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    game = loadedGame;
                    team1Data = loadedGame.getTeam1();
                    team2Data = loadedGame.getTeam2();
                    gameType = loadedGame.getGameType() != null ? loadedGame.getGameType().toUpperCase() : "PVP";
                    updateBattleState(team1Data, team2Data, 0, "Partida cargada correctamente.");
                }
            };

            PokeOptionsPanel pokeoptions = new PokeOptionsPanel(getWidth() - 100, getHeight() - 100, dialog, game,loadListener);
            dialog.setUndecorated(true);
            dialog.setContentPane(pokeoptions);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
        cargarPanel.add(cargarButton);
        pokemonPanel.add(cargarPanel);



        currentPanel.add(pokemonPanel, BorderLayout.CENTER);
        currentPanel.add(southPanel, BorderLayout.SOUTH);

        tiempoLabel = new JLabel(String.valueOf(segundosTimer), SwingConstants.CENTER);
        tiempoLabel.setFont(fuente);
        tiempoLabel.setBounds(600, 0, 70, 70);
        pokemonPanel.add(tiempoLabel);
        tiempoLabel.setBorder(new LineBorder(Color.black, 2));

        cargarPanel.setBorder(new LineBorder(Color.RED, 2));
        pokemonPanel.setBorder(new LineBorder(Color.YELLOW, 2));
        southPanel.setBorder(new LineBorder(Color.BLUE, 2));
        miniPanel.setBorder(new LineBorder(Color.RED, 2));

        tablero.add(currentPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void prepareBag(ArrayList<Item> items, Team team) {
        if(items.isEmpty()) {
            JLabel noItemsLabel = new JLabel("No hay items disponibles");
            currentPanel.add(noItemsLabel);
        }else{
            tablero.removeAll();
            tablero.setLayout(new BorderLayout());

            int width = 500, height = 500;
            currentPanel = new JPanel();
            currentPanel.setOpaque(false);
            currentPanel.setLayout(new GridLayout(6, 6, 10, 10));

            for (Item item : items) {
                Button itemButton = new Button(item.getIcono(), false, width, height);
                itemButton.addActionListener(e -> {
                    if (battleListener != null) {
                        if(item.getEfecto() instanceof EfectoRevivir){
                            prepareRevival(team,item);
                        }else{
                            battleListener.onItemUsed(team, item,team.getPokeActual());
                        }
                        //String battleMessage = game.executeBattle();
                        //updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                        if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
                            checkMachineTurn();
                        }
                    }
                });
                currentPanel.add(itemButton);
            }

            Button volverButton = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
            volverButton.addActionListener(e -> {
                prepareBattle();
                if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
                    checkMachineTurn();
                }
            });
            tablero.add(volverButton, BorderLayout.PAGE_END);
            tablero.add(currentPanel, BorderLayout.CENTER);

            revalidate();
            repaint();

        }

    }

    public void prepareRevival(Team team, Item item) {
        tablero.removeAll();
        tablero.setLayout(new BorderLayout());
        Font fuente = cargarFuentePersonalizada();
        currentPanel = new PoobkemonFondoPrincipal("/Recursos/Imagenes/Batalla/Fondo_arena_ataques.png");
        currentPanel.setLayout(new BorderLayout());
        currentPanel.setOpaque(false);

        int width = 500, height = 500;
        currentPanel = new JPanel();
        currentPanel.setOpaque(false);
        currentPanel.setLayout(new GridLayout(6, 6, 10, 10));

        for (Pokemon p : team.getPokemons()) {
            if(!p.getEstado()){
                Button pokeButton = new Button(p.getIcono(), false, width, height);
                pokeButton.addActionListener(e -> {
                    battleListener.onItemUsed(team, item,p);
                    if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
                        checkMachineTurn();
                    }
                    prepareBattle();
                });
                currentPanel.add(pokeButton);
            }
        }

        Button volverButton = new Button("/Recursos/Imagenes/atras_boton.png", false, 0, 0);
        volverButton.addActionListener(e -> {
            prepareBattle();
            if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
                checkMachineTurn();
            }
        });
        tablero.add(volverButton, BorderLayout.PAGE_END);
        tablero.add(currentPanel, BorderLayout.CENTER);

        revalidate();
        repaint();

    }



    public void prepareFight(Pokemon pokemon, Team team) {
        tablero.removeAll();
        tablero.setLayout(new BorderLayout());

        Font fuente = cargarFuentePersonalizada();
        currentPanel = new PoobkemonFondoPrincipal("/Recursos/Imagenes/Batalla/Fondo_arena_ataques.png");
        currentPanel.setLayout(new BorderLayout());
        currentPanel.setOpaque(false);

        JPanel miniPanel = new JPanel();
        miniPanel.setOpaque(false);
        miniPanel.setLayout(new GridLayout(2, 2, 0, 0));
        miniPanel.setPreferredSize(new Dimension(0, 150));

        JPanel movePanel = new JPanel();
        movePanel.setOpaque(false);
        movePanel.setLayout(new GridLayout(2, 2, 0, 0));
        movePanel.setPreferredSize(new Dimension(450, 150));

        JPanel pokemonPanel = new JPanel();
        pokemonPanel.setOpaque(false);
        pokemonPanel.setLayout(null);

        JLabel tipoLabel = new JLabel();
        JLabel ppLabel = new JLabel();
        JLabel pprestantesLabel = new JLabel();

        miniPanel.add(ppLabel);
        miniPanel.add(pprestantesLabel);
        miniPanel.add(tipoLabel);

        Pokemon pokeActual = game.getTeamActual() != null ? game.getTeamActual().getPokeActual() : null;
        if (pokeActual != null) {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(pokeActual.getFotoBack()));
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            pokemonPlayerLabel = new JLabel(scaledIcon);
            pokemonPlayerLabel.setBorder(new LineBorder(Color.GREEN, 2));
            pokemonPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
            pokemonPlayerLabel.setBounds(160, 270, 300, 300);
            pokemonPanel.add(pokemonPlayerLabel);
            barraVida barra = new barraVida(864,420,265,28,pokeActual.getMaxPs());
            barra.setVidaActual(pokeActual.getPs());
            barra.setOpaque(false);
            pokemonPanel.add(barra);
            JLabel nombrelabel = new JLabel(pokeActual.getNombre());
            nombrelabel.setFont(fuente);
            nombrelabel.setBounds(700, 360, 350, 50);
            pokemonPanel.add(nombrelabel);
            JLabel vidaLabel = new JLabel(String.valueOf(pokeActual.getPs()));
            vidaLabel.setFont(fuente);
            vidaLabel.setBounds(1030, 360, 100, 50);
            pokemonPanel.add(vidaLabel);

        }

        Pokemon pokeActualOponente = game.getTeamActual() == team1Data ? team2Data.getPokeActual() : team1Data.getPokeActual();
        if (pokeActualOponente != null) {
            ImageIcon originalIcon2 = new ImageIcon(getClass().getResource(pokeActualOponente.getFotoFrontal()));
            Image scaledImage2 = originalIcon2.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
            pokemonOpponentLabel = new JLabel(scaledIcon2);
            pokemonOpponentLabel.setBorder(new LineBorder(Color.GREEN, 2));
            pokemonOpponentLabel.setHorizontalAlignment(JLabel.CENTER);
            pokemonOpponentLabel.setBounds(750, 100, 250, 250);
            pokemonPanel.add(pokemonOpponentLabel);
            barraVida barra = new barraVida(228,148,260,28,pokeActualOponente.getMaxPs());
            barra.setVidaActual(pokeActualOponente.getPs());
            barra.setOpaque(false);
            pokemonPanel.add(barra);

            JLabel nombrelabel = new JLabel(pokeActualOponente.getNombre());
            nombrelabel.setFont(fuente);
            nombrelabel.setBounds(50, 85, 350, 50);
            pokemonPanel.add(nombrelabel);
            JLabel vidaLabel = new JLabel(String.valueOf(pokeActualOponente.getPs()));
            vidaLabel.setFont(fuente);
            vidaLabel.setBounds(380, 85, 100, 50);
            pokemonPanel.add(vidaLabel);
        }

        for (Movimiento move : pokemon.getPokeMoves()) {
            Button moveButton = new Button(move.getNombre(), 100, 40);
            moveButton.addActionListener(e -> {
                if (battleListener != null) {
                    battleListener.onMoveSelected(team, move);
                    //String battleMessage = game.executeBattle();
                    //updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                    if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
                        checkMachineTurn();
                    }
                }
            });
            moveButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    ppLabel.setText(String.valueOf(move.getPuntosPoder()));
                    pprestantesLabel.setText(String.valueOf(move.getPuntosPoderRestantes()));
                    tipoLabel.setText(move.getTipo());
                    pprestantesLabel.setFont(new Font("Arial", Font.BOLD, 40));
                    tipoLabel.setFont(new Font("Arial", Font.BOLD, 40));
                    ppLabel.setFont(new Font("Arial", Font.BOLD, 40));
                    miniPanel.revalidate();
                    miniPanel.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    tipoLabel.setText("");
                    ppLabel.setText("");
                    pprestantesLabel.setText("");
                    miniPanel.revalidate();
                    miniPanel.repaint();
                }
            });
            movePanel.add(moveButton);
        }

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.setOpaque(false);
        southPanel.setPreferredSize(new Dimension(0, 220));
        southPanel.add(movePanel);
        southPanel.add(miniPanel);

        currentPanel.add(pokemonPanel, BorderLayout.CENTER);
        currentPanel.add(southPanel, BorderLayout.SOUTH);

        pokemonPanel.setBorder(new LineBorder(Color.YELLOW, 2));
        southPanel.setBorder(new LineBorder(Color.BLUE, 2));
        miniPanel.setBorder(new LineBorder(Color.RED, 2));
        movePanel.setBorder(new LineBorder(Color.orange, 2));

        tablero.add(currentPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void prepareActions() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (anteriorFrame != null) {
                    anteriorFrame.setVisible(true);
                }
                dispose();
            }
        });
    }

    private void checkMachineTurn() {
        if (!game.isBatallaTerminada()) {
            if ("PVM".equals(gameType) && game.getTeamActual() == team2Data) {
                String battleMessage = game.executeBattle();
                updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
            } else if ("MVM".equals(gameType)) {
                String battleMessage = game.executeBattle();
                updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (!game.isBatallaTerminada()) {
                    javax.swing.Timer timer = new javax.swing.Timer(1000, e -> checkMachineTurn());
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        }
    }

    public void setBattleListener(BattleListener listener) {
        this.battleListener = listener;
    }

    public void updateBattleState(Team team1, Team team2, int turnoActual, String battleMessage) {
        this.team1Data = team1;
        this.team2Data = team2;
        prepareBattle();
        if (battleMessage != null && !battleMessage.isEmpty()) {
            JOptionPane.showMessageDialog(this, battleMessage);
        }
        if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
            checkMachineTurn();
        }
    }

    private Font cargarFuentePersonalizada() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/Recursos/Fuentes/pokemon-emerald.ttf");
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            return fuente.deriveFont(Font.BOLD, 60f);
        } catch (IOException | FontFormatException e) {
            System.out.println("Error al cargar la fuente personalizada: " + e.getMessage());
            return null;
        }
    }


    public void comenzarTimer(){
        if(timer != null){
            timer.cancel();
        }
        if(!game.isBatallaTerminada()){
            timer = new Timer();


            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    segundosTimer++;

                    SwingUtilities.invokeLater(() -> {
                        tiempoLabel.setText(String.valueOf(segundosTimer));
                        tiempoLabel.repaint();
                    });

                    if (segundosTimer >= 20) {
                        Team currentTeam = game.getTeamActual();
                        battleListener.onTimeExceed(currentTeam);
                        if ("PVM".equals(gameType) || "MVM".equals(gameType)) {
                            checkMachineTurn();
                        }
                        segundosTimer = 0;
                    }
                }
            }, 0, 1000);
        }

    }

    public void pausarTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void reiniciarTimer() {
        if (timer != null) {
            timer.cancel();
            segundosTimer = 0;
        }
    }

}