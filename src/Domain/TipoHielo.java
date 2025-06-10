package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoHielo implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoHielo() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Planta", 2.0);
        efectividades.put("Tierra", 2.0);
        efectividades.put("Volador", 2.0);
        efectividades.put("Dragón", 2.0);
        efectividades.put("Fuego", 0.5);
        efectividades.put("Agua", 0.5);
        efectividades.put("Hielo", 0.5);
        efectividades.put("Acero", 0.5);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Hielo";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Rayo Hielo", "Hielo", "Especial", 90, 100, 10,null));
        movimientos.add(new Movimiento("Ventisca", "Hielo", "Especial", 110, 70, 5,null));
        movimientos.add(new Movimiento("Niebla", "Hielo", "Estado", 0, 100, 30,efectosPredeterminados.getEfecto("Niebla")));

        return movimientos;
    }
}
