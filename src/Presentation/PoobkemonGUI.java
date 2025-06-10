package Presentation;
import javax.swing.*;
import java.awt.*;
import Domain.*;


import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;


public class PoobkemonGUI extends JFrame  implements GameLoaListener {
    private JButton JugarButton, PokeButton,partidaButton;
    private JPanel tablero;
    private PoobkemonGame game;
    private static PoobkemonGUI juego;


    public PoobkemonGUI(){
        setTitle("Poobkemon");
        game = new PoobkemonGame();
        prepareElements();
        prepareActions();
    }

    public static PoobkemonGUI getGame() {
        if (juego == null) {
            juego = new PoobkemonGUI();
        }
        return juego;
    }



    private void prepareElements() {


        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        tablero = new PoobkemonFondoPrincipal("/Recursos/Imagenes/Pantalla_inicial.png");
        tablero.setLayout(new BorderLayout());
        add(tablero, BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));

        JugarButton = new Button("Jugar",  200, 60);
        PokeButton = new Button("Poke", 200, 60);

        JPanel partidaPanel = new JPanel();
        partidaPanel.setOpaque(false);
        partidaPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        partidaButton = new Button("/Recursos/Imagenes/options.png",false, 100, 100);


        JugarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        PokeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 120)));
        botonesPanel.add(Box.createVerticalGlue());
        botonesPanel.add(JugarButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        botonesPanel.add(PokeButton);
        botonesPanel.add(Box.createVerticalGlue());

        partidaPanel.add(partidaButton);

        tablero.add(botonesPanel, BorderLayout.CENTER);
        tablero.add(partidaPanel,BorderLayout.SOUTH);
    }

    private void prepareActions() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                exit();
            }
        });

        JugarButton.addActionListener(e -> {
            this.setVisible(false);
            PokeFrame pokeFrame = new PokeFrameBuilder().setTitle("Modos De Juego").setAnteriorFrame(this).setGameMode().build();
            pokeFrame.setVisible(true);
            pokeFrame.setGameTypeListener((gameType, machineType, player1Pokemons, player1Items, player2Pokemons, player2Items,nameTeam1, nameTeam2) -> {
                game.startBattle(gameType, machineType, player1Pokemons, player1Items, player2Pokemons, player2Items);
                Team team1 = game.getTeam1();
                Team team2 = game.getTeam2();
                team1.setTeamName(nameTeam1);
                team2.setTeamName(nameTeam2);
                PokeBatallaFrame batalla = new PokeBatallaFrame(game, this, team1, team2,gameType);
                batalla.setBattleListener(createBattleListener(batalla, gameType));
                batalla.setVisible(true);
                JOptionPane.showMessageDialog(batalla,game.getTeamActual().getName() + "Comienza!!!");

                Window[] windows = Window.getWindows();

            });
        });

        PokeButton.addActionListener(e -> {
            PokeFrame pokeFrame = new PokeFrameBuilder().setTitle("Pokemones").setAnteriorFrame(this).setPokemonsInGame(new PokemonsInGame()).build();
            pokeFrame.setVisible(true);
        });

        partidaButton.addActionListener(e -> {
            JDialog dialog = new JDialog();
            PokeOptionsPanel pokeoptions = new PokeOptionsPanel(getWidth()-100, getHeight()-100,dialog,game,this);
            dialog.setUndecorated(true);
            dialog.setContentPane(pokeoptions);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });





    }



    private BattleListener createBattleListener(PokeBatallaFrame batalla, String gameType) {
        return new BattleListener() {
            @Override
            public void onMoveSelected(Team team, Movimiento move) {
                game.AttackSelection(move);
                String battleMessage = game.executeBattle();
                batalla.updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (game.isBatallaTerminada()) {
                    JOptionPane.showMessageDialog(batalla, "¡La batalla ha terminado!");
                    batalla.setVisible(false);
                    PoobkemonGUI.this.setVisible(true);
                }
            }

            @Override
            public void onItemUsed(Team team, Item item, Pokemon pokemon) {
                game.ItemSelection(item,pokemon);
                String battleMessage = game.executeBattle();
                batalla.updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (game.isBatallaTerminada()) {
                    JOptionPane.showMessageDialog(batalla, "¡La batalla ha terminado!");
                    batalla.setVisible(false);
                    PoobkemonGUI.this.setVisible(true);
                }
            }

            @Override
            public void onPokemonChanged(Team team, Pokemon pokemon) {
                game.PokemonSelection(pokemon);
                String battleMessage = game.executeBattle();
                batalla.updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (game.isBatallaTerminada()) {
                    JOptionPane.showMessageDialog(batalla, "¡La batalla ha terminado!");
                    batalla.setVisible(false);
                    PoobkemonGUI.this.setVisible(true);
                }
            }

            @Override
            public void onRun(Team team) {
                game.RunSelection();
                String battleMessage = game.executeBattle();
                batalla.updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (game.isBatallaTerminada()) {
                    JOptionPane.showMessageDialog(batalla, "La batalla ha terminado!");
                    batalla.setVisible(false);
                    PoobkemonGUI.this.setVisible(true);
                }
            }

            @Override
            public void onTimeExceed(Team team) {
                game.NoMoreTimeSelection();
                String battleMessage = game.executeBattle();
                batalla.updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (game.isBatallaTerminada()) {
                    JOptionPane.showMessageDialog(batalla, "La batalla ha terminado!");
                    batalla.setVisible(false);
                    PoobkemonGUI.this.setVisible(true);
                }
            }
        };
    }


    @Override
    public void onGameLoaded(PoobkemonGame loadedGame) {
        if (loadedGame == null) {
            JOptionPane.showMessageDialog(this, "Error: No se pudo cargar la partida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.game = loadedGame;
        if (!game.isBatallaTerminada() && game.getTeam1() != null && game.getTeam2() != null) {
            this.setVisible(false);
            Team team1 = game.getTeam1();
            Team team2 = game.getTeam2();
            String gameType = game.getGameType();
            PokeBatallaFrame batalla = new PokeBatallaFrame(game, this, team1, team2,gameType);
            batalla.setBattleListener(createBattleListener(batalla, "Cargada"));
            batalla.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Partida cargada correctamente.");
            this.setVisible(true);
        }
    }





    private void exit() {
        Object[] options = {"Si ", "No"};
        int answer = JOptionPane.showOptionDialog(this,
                "Quieres salir?", "Salir",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }



    public static void main(String[] args){
        PoobkemonGUI pokemonGame = PoobkemonGUI.getGame();
        pokemonGame.setVisible(true);



    }

}
