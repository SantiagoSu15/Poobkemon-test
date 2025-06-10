package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoVeneno implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoVeneno() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Planta", 2.0);
        efectividades.put("Hada", 2.0);
        efectividades.put("Veneno", 0.5);
        efectividades.put("Tierra", 0.5);
        efectividades.put("Roca", 0.5);
        efectividades.put("Fantasma", 0.5);
        efectividades.put("Acero", 0.0);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Veneno";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Picotazo Veneno", "Veneno", "Físico", 15, 100, 35,null));
        movimientos.add(new Movimiento("Ácido", "Veneno", "Especial", 40, 100, 30,null));
        movimientos.add(new Movimiento("Tóxico", "Veneno", "Estado", 0, 90, 10,efectosPredeterminados.getEfecto("Tóxico")));

        return movimientos;
    }
}

