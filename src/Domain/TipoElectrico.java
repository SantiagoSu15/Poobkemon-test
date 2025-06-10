package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoElectrico implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoElectrico() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Agua", 2.0);
        efectividades.put("Volador", 2.0);
        efectividades.put("Planta", 0.5);
        efectividades.put("Electrico", 0.5);
        efectividades.put("Dragon", 0.5);
        efectividades.put("Tierra", 0.0);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Electrico";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Puño Trueno", "Eléctrico", "Físico", 75, 100, 15, null));
        movimientos.add(new Movimiento("Rayo", "Eléctrico", "Especial", 90, 100, 15,null));
        movimientos.add(new Movimiento("Trueno", "Eléctrico", "Especial", 110, 70, 10,null));

        return movimientos;
    }
}
