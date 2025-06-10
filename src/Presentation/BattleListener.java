package Presentation;

import Domain.Item;
import Domain.Movimiento;
import Domain.Pokemon;
import Domain.Team;

public interface BattleListener {
    void onMoveSelected(Team team, Movimiento move);
    void onItemUsed(Team team, Item item,Pokemon pokemon);
    void onPokemonChanged(Team team, Pokemon pokemon);
    void onRun(Team team);
    void onTimeExceed(Team team);
}
