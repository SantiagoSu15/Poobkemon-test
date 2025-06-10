package Presentation;


import Domain.Item;
import Domain.Pokemon;

import java.util.ArrayList;

public interface GameTypeListener {
    void onGameTypeSelected(String gameType, String machineType, ArrayList<Pokemon> player1Pokemons, ArrayList<Item> player1Items, ArrayList<Pokemon> player2Pokemons, ArrayList<Item> player2Items,String nameTeam1,String nameTeam2);
}