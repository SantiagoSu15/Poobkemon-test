package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoPlanta implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoPlanta() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Agua", 2.0);
        efectividades.put("Roca", 2.0);
        efectividades.put("Tierra", 2.0);
        efectividades.put("Fuego", 0.5);
        efectividades.put("Planta", 0.5);
        efectividades.put("Veneno", 0.5);
        efectividades.put("Volador", 0.5);
        efectividades.put("Bicho", 0.5);
        efectividades.put("Dragón", 0.5);
        efectividades.put("Acero", 0.5);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Planta";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }
    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Hoja Afilada", "Planta", "Físico", 55, 95, 25,null));
        movimientos.add(new Movimiento("Rayo Solar", "Planta", "Especial", 120, 100, 10, null));
        movimientos.add(new Movimiento("Drenadoras", "Planta", "Estado", 0, 90, 10,efectosPredeterminados.getEfecto("Drenadoras")));

        return movimientos;
    }
}

