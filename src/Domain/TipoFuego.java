package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoFuego implements EfectividadesTipo, Serializable {

    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoFuego() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Planta", 2.0);
        efectividades.put("Hielo", 2.0);
        efectividades.put("Bicho", 2.0);
        efectividades.put("Acero", 2.0);
        efectividades.put("Fuego", 0.5);
        efectividades.put("Agua", 0.5);
        efectividades.put("Roca", 0.5);
        efectividades.put("Dragón", 0.5);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Fuego";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }
    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Lanzallamas", "Fuego", "Especial", 90, 100, 15,null));
        movimientos.add(new Movimiento("Ascuas", "Fuego", "Especial", 40, 100, 25,null));
        movimientos.add(new Movimiento("Día Soleado", "Fuego", "Estado", 35, 85, 15,efectosPredeterminados.getEfecto("Dia Soleado")));

        return movimientos;
    }
}

