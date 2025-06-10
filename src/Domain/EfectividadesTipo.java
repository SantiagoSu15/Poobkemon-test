package Domain;

import java.util.ArrayList;
import java.util.Map;


/**
 * Interfaz para los tipos de pokemon
 *
 *
 */public interface EfectividadesTipo {
    Map<String, Double> definirEfectividades();
    String getNombre();
    double obtenerEfectividadContra(String tipoDefensor);
    ArrayList<Movimiento> movimientosPredeterminados();
}

