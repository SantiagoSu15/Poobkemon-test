package Presentation;

import Domain.*;
import javax.swing.*;
import java.util.ArrayList;

public class TestBattleScreen {
    public static void main(String[] args) {
        PokemonsInGame pok = new PokemonsInGame();
        ItemsInGame it = new ItemsInGame();
        ArrayList<Item> itemsInGame = it.getItems();
        ArrayList<Pokemon> pokemonsInGame = pok.getPokemons();

        ArrayList<Pokemon> poket1 = new ArrayList<>();
        ArrayList<Pokemon> poket2 = new ArrayList<>();
        ArrayList<Item> ite1 = new ArrayList<>();
        ArrayList<Item> ite2 = new ArrayList<>();
        for (int i = 0; i < 10 && i < pokemonsInGame.size(); i++) {
            if (i % 2 == 0) {
                poket1.add(pokemonsInGame.get(i));
            } else {
                poket2.add(pokemonsInGame.get(i));
            }
        }
        for (int i = 0; i < 2 && i < itemsInGame.size(); i++) {
            if (i % 2 == 0) {
                ite1.add(itemsInGame.get(i));
            } else {
                ite2.add(itemsInGame.get(i));
            }
        }

        PoobkemonGame game = new PoobkemonGame();
        game.startBattle("PvP",null, poket1, ite1,poket2, ite2);
        Team team1 = game.getTeam1();
        Team team2 = game.getTeam2();
        team1.setTeamName("Team 121312313123");
        team2.setTeamName("Team 2");
        PokeBatallaFrame juego = new PokeBatallaFrame(game, null, team1, team2,"PvP");
        juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        juego.setBattleListener(new BattleListener() {
            @Override
            public void onMoveSelected(Team team, Movimiento move) {
                System.out.println("Movimiento seleccionado: " + move.getNombre());
                game.AttackSelection(move);
                String battleMessage = game.executeBattle();
                juego.updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (game.isBatallaTerminada()) {
                    JOptionPane.showMessageDialog(juego, "¡La batalla ha terminado!");
                    juego.dispose();
                }
            }

            @Override
            public void onItemUsed(Team team, Item item,Pokemon pokemon) {
                System.out.println("Ítem usado: " + item.toString());
                game.ItemSelection(item,pokemon);
                String battleMessage = game.executeBattle();
                juego.updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (game.isBatallaTerminada()) {
                    JOptionPane.showMessageDialog(juego, "¡La batalla ha terminado!");
                    juego.dispose();
                }
            }

            @Override
            public void onPokemonChanged(Team team, Pokemon pokemon) {
                System.out.println("Pokémon cambiado: " + pokemon.getNombre());
                game.PokemonSelection(pokemon);
                String battleMessage = game.executeBattle();
                juego.updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (game.isBatallaTerminada()) {
                    JOptionPane.showMessageDialog(juego, "¡La batalla ha terminado!");
                    juego.dispose();
                }
            }

            @Override
            public void onRun(Team team) {
                System.out.println("Huyendo...");
                game.RunSelection();
                String battleMessage = game.executeBattle();
                juego.updateBattleState(game.getTeam1(), game.getTeam2(), 0, battleMessage);
                if (game.isBatallaTerminada()) {
                    JOptionPane.showMessageDialog(juego, "¡La batalla ha terminado!");
                    juego.dispose();
                }
            }
            @Override
            public void onTimeExceed(Team team) {
                System.out.println("se paso");
            }
        });

        juego.setVisible(true);





    }
}