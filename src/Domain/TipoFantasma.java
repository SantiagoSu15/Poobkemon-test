package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoFantasma implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoFantasma() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Psíquico", 2.0);
        efectividades.put("Fantasma", 2.0);
        efectividades.put("Siniestro", 0.5);
        efectividades.put("Normal", 0.0);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Fantasma";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Lengüetazo", "Fantasma", "Físico", 30, 100, 30,null));
        movimientos.add(new Movimiento("Tinieblas", "Fantasma", "Especial", 50, 100, 15,null));
        movimientos.add(new Movimiento("Maldición", "Fantasma", "Estado", 0, 100, 10,efectosPredeterminados.getEfecto("Maldición")));

        return movimientos;
    }
}

