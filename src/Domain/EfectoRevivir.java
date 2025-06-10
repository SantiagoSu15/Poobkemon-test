package Domain;



import java.io.Serializable;

public class EfectoRevivir implements EfectosItems, Serializable {

    public EfectoRevivir() {
    }



    /**
     * Sobresicribir el metodo de EfectosItems
     *revive un pokemon
     *
     * @return String.
     */
    @Override
    public String aplicarEfecto(Pokemon p) {
        if (!p.getEstado()) {
            p.AumentarPs(p.getMaxPs() / 2);
            p.setEstado(true);
            return p.getNombre() + "ha sido revivido";
        } else {
            return "no se pudo utilizar el item";
        }
    }
}
