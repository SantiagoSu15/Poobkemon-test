package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoAgua implements EfectividadesTipo, Serializable {

    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();
    public TipoAgua() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Fuego", 2.0);
        efectividades.put("Roca", 2.0);
        efectividades.put("Tierra", 2.0);
        efectividades.put("Agua", 0.5);
        efectividades.put("Planta", 0.5);
        efectividades.put("Dragón", 0.5);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Agua";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Cascada", "Agua", "Físico", 80, 100, 15, null));
        movimientos.add(new Movimiento("Burbuja", "Agua", "Especial", 40, 100, 30,null));
        movimientos.add(new Movimiento("Refugio", "Agua", "Estado", 40, 100, 25,efectosPredeterminados.getEfecto("Refugio")));
        return movimientos;
    }
}
