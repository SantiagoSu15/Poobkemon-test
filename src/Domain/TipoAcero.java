package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoAcero implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();
    public TipoAcero() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Hielo", 2.0);
        efectividades.put("Roca", 2.0);
        efectividades.put("Hada", 2.0);
        efectividades.put("Fuego", 0.5);
        efectividades.put("Agua", 0.5);
        efectividades.put("Eléctrico", 0.5);
        efectividades.put("Acero", 0.5);
        return efectividades;
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Garra Metal", "Acero", "Físico", 50, 95, 35,null));
        movimientos.add(new Movimiento("Cabezazo Hierro", "Acero", "Físico", 80, 100, 15,null));
        movimientos.add(new Movimiento("Cuerpo Pesado", "Acero", "Físico", 0, 100, 10,null));
        return movimientos;
    }




    @Override
    public String getNombre() {
        return "Acero";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }
}
