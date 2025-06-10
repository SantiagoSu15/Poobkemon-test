package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoHada implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoHada() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Lucha", 2.0);
        efectividades.put("Dragón", 2.0);
        efectividades.put("Siniestro", 2.0);
        efectividades.put("Fuego", 0.5);
        efectividades.put("Veneno", 0.5);
        efectividades.put("Acero", 0.5);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Hada";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }
    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Voz Cautivadora", "Hada", "Especial", 40, 100, 15,null));
        movimientos.add(new Movimiento("Brillo Mágico", "Hada", "Especial", 80, 100, 10,null));
        movimientos.add(new Movimiento("Deseo", "Hada", "Estado", 0, 100, 10,efectosPredeterminados.getEfecto("Deseo")));

        return movimientos;
    }
}
