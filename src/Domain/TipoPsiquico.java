package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoPsiquico implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoPsiquico() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Lucha", 2.0);
        efectividades.put("Veneno", 2.0);
        efectividades.put("Psiquico", 0.5);
        efectividades.put("Acero", 0.5);
        efectividades.put("Siniestro", 0.0);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Psíquico";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }
    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Psíquico", "Psíquico", "Especial", 90, 100, 10,null));
        movimientos.add(new Movimiento("Confusión", "Psíquico", "Especial", 50, 100, 25,null));
        movimientos.add(new Movimiento("Pantalla Luz", "Psíquico", "Estado", 0, 100, 30,efectosPredeterminados.getEfecto("Pantalla Luz")));

        return movimientos;
    }
}
