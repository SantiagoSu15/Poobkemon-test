package Domain;


import java.io.Serializable;

public class EfectoCurar implements EfectosItems, Serializable {
        private int cantidadCura;



    public EfectoCurar( int cantidadCura) {
        this.cantidadCura = cantidadCura;
    }




    /**
     * Sobresicribir el metodo de EfectosItems
     *Si el pokemon esta activo le cura la cantidad
     *
     * @return String.
     */
        @Override
        public String aplicarEfecto(Pokemon p) {
            if (p.getEstado()) {
                p.AumentarPs(cantidadCura);
                return p.getNombre() + " ha sido curado";
            }
            return "No se puede curar un pokemon derrotado";
        }
}

