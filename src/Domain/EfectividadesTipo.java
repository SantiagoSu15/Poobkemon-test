package Domain;

import java.util.ArrayList;
import java.util.Map;


//efectividades
public interface EfectividadesTipo {
    Map<String, Double> definirEfectividades();
    String getNombre();
    double obtenerEfectividadContra(String tipoDefensor);
    ArrayList<Movimiento> movimientosPredeterminados();
}

