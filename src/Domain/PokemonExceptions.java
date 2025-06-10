package Domain;

import java.io.Serializable;

public class PokemonExceptions extends RuntimeException implements Serializable {
    public PokemonExceptions(String message){
        super(message);
    }
    public PokemonExceptions(String message, Throwable cause){
        super(message, cause);
    }
}

class InvalidPokemon extends PokemonExceptions implements Serializable{
    public InvalidPokemon(String message){
        super(message);
    }
}

class InvalidItem extends PokemonExceptions implements Serializable{
    public InvalidItem(String message){
        super(message);
    }
}

class InvalidMove extends PokemonExceptions implements Serializable{
    public InvalidMove(String message){
        super(message);
    }
}

class InvalidPokeTypeClass extends PokemonExceptions implements Serializable{
    public InvalidPokeTypeClass(String message,Throwable cause){
        super(message,cause);
    }
}
class InvalidGameType extends PokemonExceptions implements Serializable{
    public InvalidGameType(String message){
        super(message);
    }
}
class InvalidTeam extends PokemonExceptions implements Serializable{
    public InvalidTeam(String message){
        super(message);
    }
}

class InvalidAction extends PokemonExceptions implements Serializable{
    public InvalidAction(String message){
        super(message);
    }
}

class InvalidEffect extends PokemonExceptions implements Serializable{
    public InvalidEffect(String message){
        super(message);
    }
}

class InvalidMachine extends PokemonExceptions implements Serializable{
    public InvalidMachine(String message){
        super(message);
    }
}