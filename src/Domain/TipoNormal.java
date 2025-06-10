package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoNormal implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoNormal() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Roca", 0.5);
        efectividades.put("Acero", 0.5);
        efectividades.put("Fantasma", 0.0);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Normal";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Placaje", "Normal", "Físico", 40, 100, 35,null));
        movimientos.add(new Movimiento("Poder oculto", "Normal", "Especial", 70, 100, 30,null));
        movimientos.add(new Movimiento("Gruñido", "Normal", "Estado", 0, 100, 40,efectosPredeterminados.getEfecto("Gruñido")));

        return movimientos;
    }
}

