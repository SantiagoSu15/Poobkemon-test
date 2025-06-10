package Domain;


/**
 * Interfaz para acciones en batalla
 *Para una nueva accion se crea una clase q implemente esto
 *Devuelve un sting q es lo q ocurrio
 */
public interface PokeBatallaAccion {
    String AccionBatalla(Team atacante, Team defensor);

}
