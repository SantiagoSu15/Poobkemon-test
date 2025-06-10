package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoRoca implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoRoca() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Fuego", 2.0);
        efectividades.put("Hielo", 2.0);
        efectividades.put("Volador", 2.0);
        efectividades.put("Bicho", 2.0);
        efectividades.put("Lucha", 0.5);
        efectividades.put("Tierra", 0.5);
        efectividades.put("Acero", 0.5);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Roca";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }
    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Avalancha", "Roca", "Físico", 75, 90, 10,null));
        movimientos.add(new Movimiento("Lanzarrocas", "Roca", "Físico", 50, 90, 15,null));
        movimientos.add(new Movimiento("Poder Pasado", "Roca", "Especial", 60, 100, 5,null));

        return movimientos;
    }
}

