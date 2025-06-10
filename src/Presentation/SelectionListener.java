package Presentation;

import Domain.Pokemon;
import Domain.Item;

import java.util.ArrayList;
import java.util.List;

public interface SelectionListener {
    void onSelectionCompleted(String gameType, String machineType, ArrayList<Pokemon> selectedPokemons, ArrayList<Item> selectedItems,String teamName);
}