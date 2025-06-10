package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoVolador implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoVolador() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Planta", 2.0);
        efectividades.put("Lucha", 2.0);
        efectividades.put("Bicho", 2.0);
        efectividades.put("Electrico", 0.5);
        efectividades.put("Roca", 0.5);
        efectividades.put("Acero", 0.5);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Volador";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Ataque Ala", "Volador", "Físico", 60, 100, 35,null));
        movimientos.add(new Movimiento("Golpe Aéreo", "Volador", "Físico", 60, 100, 20,null));
        movimientos.add(new Movimiento("Danza Pluma", "Volador", "Estado", 0, 100, 15,efectosPredeterminados.getEfecto("Danza Pluma")));

        return movimientos;
    }
}
