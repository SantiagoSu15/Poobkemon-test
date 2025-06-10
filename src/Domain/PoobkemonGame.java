package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class PoobkemonGame implements Serializable {
    private PokemonsInGame pokemonsInGame;
    private ItemsInGame itemsInGame;
    private Batalla batalla;
    private TipoDeMaquina tipoMaquina1;
    private TipoDeMaquina tipoMaquina2;
    private String gameType;
    private boolean isSurvivalBattle = false;
    private ModoBatalla modoBatalla;

    /**
     * Crea lo baasico del juego
     * @return .
     */
    public PoobkemonGame() {
        pokemonsInGame = new PokemonsInGame();
        itemsInGame = new ItemsInGame();
        batalla = null;
    }

    /**
     * Comienza una batalla
     * Si es pvp y no hay tipo maquina por defecto se crea un modo survival
     *
     * de lo contrario se revisa el tipo de juego, se crea un modo batalla y la batalla se crea con el metodo de comenzar batalla
     *
     * @return void.
     */
    public void startBattle(String gameType, String machineType, ArrayList<Pokemon> pokemonsTeam1,
                            ArrayList<Item> itemsTeam1, ArrayList<Pokemon> pokemonsTeam2,
                            ArrayList<Item> itemsTeam2) {

        if(gameType == "PvP" && machineType == null && pokemonsTeam1== null && itemsTeam1== null){
            startSurvivalBattle();
            isSurvivalBattle = true;

        }else{
            this.gameType = gameType.toUpperCase();

            if ("PvP".equals(gameType)) {
                modoBatalla = new ModoPvP(new PokemonsInGame(), new ItemsInGame());
                batalla = modoBatalla.comenzarBatalla(pokemonsTeam1, itemsTeam1, pokemonsTeam2, itemsTeam2);

            } else if ("PvM".equals(gameType)) {
                tipoMaquina2 = crearTipoMaquina(machineType);
                 modoBatalla = new ModoPvM(new PokemonsInGame(), new ItemsInGame(), tipoMaquina2);
                batalla = modoBatalla.comenzarBatalla(pokemonsTeam1, itemsTeam1, pokemonsTeam2, itemsTeam2);

            } else if ("MvM".equals(gameType)) {
                tipoMaquina1 = crearTipoMaquina(machineType);
                    tipoMaquina2 = crearTipoMaquina(machineType);
                modoBatalla = new ModoMvM(new PokemonsInGame(), new ItemsInGame(), tipoMaquina1, tipoMaquina2);
                batalla = modoBatalla.comenzarBatalla(pokemonsTeam1, itemsTeam1, pokemonsTeam2, itemsTeam2);
            } else {
                throw new InvalidGameType("Tipo de juego no válido: " + gameType);
            }
        }


    }


    /**
     * Metodos para crear una accion en la batalla, se llama en la gui para el turno del jugador real (humano)
     * @return void.
     */

    public void AttackSelection(Movimiento move){
        batalla.setAccion(new PokeAtaqueAccion(move));
    }

    public void PokemonSelection(Pokemon pokemon){
        batalla.setAccion(new PokeCambiarAccion(pokemon));
    }

    public void ItemSelection(Item item, Pokemon pokemon){
        batalla.setAccion(new PokeUsarItemAccion(item,pokemon));
    }

    public void RunSelection(){
        batalla.setAccion(new PokeAbandonarAccion());
    }

    public void NoMoreTimeSelection(){batalla.setAccion(new PokeNullAction());}

    /**
     * Metodo para ejecutar la siguiente accion de la batalla
     * Dependiendo el modo de juego, si es un modo con maquina la primera condicion se usa para evitar q la maquina tome
     * el equipo del oponente
     *Sirve para tomar una accion de la maquina automaticamente y poder ejecutar la ultima accion y se vea reflejada
     * La accion de la maquina se genera dependiendo el tipo de la maquina, usa el metodo de decidirAccion
     * @return String.
     */
    public String executeBattle() {
        if (isMvM() || (isPvM() && batalla.getTeamActual() == batalla.getTeam2())) {
            Team teamActual = batalla.getTeamActual();
            TipoDeMaquina maquina = (teamActual == batalla.getTeam1()) ? tipoMaquina1 : tipoMaquina2;
            if (maquina != null) {
                PokeBatallaAccion accionMaquina = maquina.decidirAccion(teamActual,
                        teamActual == batalla.getTeam1() ? batalla.getTeam2() : batalla.getTeam1());
                batalla.setAccion(accionMaquina);
            }
        }
        return batalla.ejecutarBatalla();
    }

    public boolean isBatallaTerminada() {
        if (batalla == null) {
            return true;
        }
        return batalla.BatallaTerminada();
    }

    public Team getTeamActual() {
        if (batalla == null) {
            return null;
        }
        return batalla.getTeamActual();
    }


    public Team createTeam(ArrayList<Pokemon> pokemons,ArrayList<Item> Items){
        Team team = new Team();
        for (Pokemon p : pokemons) {
            team.agregarPokemon(p);
        }
        if(Items == null){
            return team;
        }else{
            for (Item item : Items) {
                team.agregarItem(item);
            }
        }

        return team;
    }

    public Team getTeam1() {
        return batalla != null ? batalla.getTeam1() : null;
    }

    public Team getTeam2() {
        return batalla != null ? batalla.getTeam2() : null;
    }


    private ModoBatalla crearModo(String gameType, String machineType1, String machineType2) {
        switch (gameType.toUpperCase()) {
            case "PVP":
                return new ModoPvP(pokemonsInGame, itemsInGame);
            case "PVM":
                return new ModoPvM(pokemonsInGame, itemsInGame, crearTipoMaquina(machineType1));
            case "MVM":

                return new ModoMvM(pokemonsInGame, itemsInGame, crearTipoMaquina(machineType1), crearTipoMaquina(machineType2));
            default:
                throw new InvalidGameType("Modo de juego no válido: " + gameType);
        }
    }

    /**
     * Crea una estrategia para la maquina
     * @return TipoDeMaquina.
     */
    public TipoDeMaquina crearTipoMaquina(String machineType) {
        if (machineType == null) {
            return null;

        }
        switch (machineType) {
            case "Defensiva":
                return  new MaquinaDefensiva();
            case "Ofensiva":
                return new MaquinaAtacante();
            case "Cambiante":
                return new MaquinaCambiante();
            case "Experta":
                return  new MaquinaExperta();
            default:
                throw new InvalidMachine("Tipo de máquina no válido: " + machineType);
        }
    }


    private boolean isPvM() {
        return tipoMaquina1 == null && tipoMaquina2 != null;
    }

    private boolean isMvM() {
        return tipoMaquina1 != null && tipoMaquina2 != null;
    }

    public PokemonsInGame getPokemonsInGame() {
        return pokemonsInGame;
    }

    public ItemsInGame getItemsInGame() {
        return itemsInGame;
    }

    public String getGameType(){return gameType;}



    /**
     * Genera los teams para el modo survival
     * @return void.
     */
    public void startSurvivalBattle() {
        Random random = new Random();

        ArrayList<Pokemon> pokemonesEquipo1 = new ArrayList<>();
        ArrayList<Pokemon> pokemonesEquipo2 = new ArrayList<>();

        int indiceAleatorio;
        int indiceAleatorio2;

        for(int j =0;j<6;j++){
            indiceAleatorio = random.nextInt(pokemonsInGame.getPokemons().size());
            indiceAleatorio2 = random.nextInt(pokemonsInGame.getPokemons().size());


            Pokemon pokeAleatorio = pokemonsInGame.getPokemons().get(indiceAleatorio);
            Pokemon pokeAleatorio2 = pokemonsInGame.getPokemons().get(indiceAleatorio2);

            pokemonesEquipo1.add(pokeAleatorio);
            pokemonesEquipo2.add(pokeAleatorio2);

        }
        Team team1 = createTeam(pokemonesEquipo1,null);
        Team team2 = createTeam(pokemonesEquipo2,null);
        batalla = new Batalla(team1, team2);
        this.gameType = "PVP";
        this.isSurvivalBattle = true;
    }

    public boolean isSurvivalBattle() {
        return isSurvivalBattle;
    }
    public Batalla getBatalla() {
        return batalla;
    }

    public ModoBatalla getModoBatalla() {return modoBatalla;}
}
