package Presentation;
import Domain.PokemonsInGame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;


public class PokeFrameBuilder {
    private String title;
    private JFrame anteriorFrame;
    private boolean isGameMode;
    private boolean isGameType;
    private PokemonsInGame pokemonsInGame;
    private GameTypeListener tipoListener;
    private String gameTypeString;
    private ArrayList<String> types = new ArrayList<>(Arrays.asList("Normal","Supervivencia"));

    public PokeFrameBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PokeFrameBuilder setAnteriorFrame(JFrame frame) {
        this.anteriorFrame = frame;
        return this;
    }

    public PokeFrameBuilder setGameMode() {
        this.isGameMode = true;
        return this;
    }

    public PokeFrameBuilder setGameType() {
        this.isGameType = true;
        return this;
    }

    public PokeFrameBuilder setGameTypeString(String gameType) {
        if (types.contains(gameType)) {
            this.gameTypeString = gameType;
        }else{
            this.gameTypeString = null;
        }
        return this;
    }



    public PokeFrameBuilder setPokemonsInGame(PokemonsInGame pokemonsInGame) {
        this.pokemonsInGame = pokemonsInGame;
        return this;
    }

    public PokeFrameBuilder setGameTypeListener(GameTypeListener listener) {
        this.tipoListener = listener;
        return this;
    }



    public PokeFrame build() {
        PokeFrame frame;

        if (pokemonsInGame != null) {
            frame = new PokeFrame(pokemonsInGame);
        } else {
            frame = new PokeFrame(title, anteriorFrame, isGameMode, isGameType, gameTypeString);
        }

        if (tipoListener != null) {
            frame.setGameTypeListener(tipoListener);
        }

        return frame;
    }
}
