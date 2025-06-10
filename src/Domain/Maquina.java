package Domain;

import java.io.Serializable;

public class Maquina extends Team implements Serializable {
    private TipoDeMaquina estrategia;


    /**
     * Se le da una estrategia objeto de cualquier clase q implemente tipoMaquina
     *
     * @return .
     */
    public Maquina(TipoDeMaquina estrategia) {
        super();
        if (estrategia == null) {throw new InvalidMachine("La maquina no tiene una estrategia");}
        this.estrategia = estrategia;
    }


}