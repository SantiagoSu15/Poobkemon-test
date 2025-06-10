package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoTierra implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoTierra() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Fuego", 2.0);
        efectividades.put("Electrico", 2.0);
        efectividades.put("Veneno", 2.0);
        efectividades.put("Roca", 2.0);
        efectividades.put("Acero", 2.0);
        efectividades.put("Planta", 0.5);
        efectividades.put("Bicho", 0.5);
        efectividades.put("Volador", 0.0);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Tierra";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Terremoto", "Tierra", "Físico", 100, 100, 10,null));
        movimientos.add(new Movimiento("Excavar", "Tierra", "Físico", 80, 100, 10,null));
        movimientos.add(new Movimiento("Ataque Arena", "Tierra", "Estado", 0, 100, 15,efectosPredeterminados.getEfecto("Ataque Arena")));

        return movimientos;
    }
}

