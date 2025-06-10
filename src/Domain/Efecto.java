package Domain;

import java.io.Serializable;

public class Efecto implements Serializable {
    private String nombre;
    private String efecto;
    private int modPs;
    private int modAtk;
    private int modDef;
    private int modSpecialAtk;
    private int modSpecialDef;
    private int modPs2;
    private int modAtk2;
    private int modDef2;
    private int modSpecialAtk2;
    private int modSpecialDef2;


    public Efecto(String nombre, String efecto, int vidaAtacante, int ataqueAtacante, int defensaAtacante, int spcAtaqueAtacante, int spcDefensaAtacante,
                  int vidaDefensor, int ataqueDefensor, int defensaDefensor, int spcAtaqueDefensor, int spcDefensaDefensor) {

        this.nombre = nombre;
        this.efecto = efecto;
        this.modPs = vidaAtacante;
        this.modAtk = ataqueAtacante;
        this.modDef = defensaAtacante;
        this.modSpecialAtk = spcAtaqueAtacante;
        this.modSpecialDef = spcDefensaAtacante;
        this.modPs2 = vidaDefensor;
        this.modAtk2 = ataqueDefensor;
        this.modDef2 = defensaDefensor;
        this.modSpecialAtk2 = spcAtaqueDefensor;
        this.modSpecialDef2 = spcDefensaDefensor;
    }


    /**
     * Aplica un efecto a los pokemons
     * cada efecto tiene determinados atributos
     * @return void.
     */
    public void aplicarEfecto(Pokemon atacante, Pokemon defensor) {
        atacante.AumentarPs(modPs);
        defensor.RecibirDano(modPs2);
        atacante.AumentarAtaque(modAtk);
        defensor.bajarAtaque(modAtk2);
        atacante.AumentarDefensa(modDef);
        defensor.bajarDefensa(modDef2);
        atacante.AumentarAtaqueEspecial(modSpecialAtk);
        defensor.bajarAtaqueEspecial(modSpecialAtk2);
        atacante.AumentarDefensaEspecial(modSpecialDef);
        defensor.bajarDefensaEspecial(modSpecialDef2);
    }

    public String getNombre() {
        return nombre;
    }

    public int getModDef(){
        return modDef;
    }

    public int getmodSpecialDef(){
        return modSpecialDef;
    }


    /**
     * Obtener si el efecto es de ataque
     *
     * @return boolean.
     */
    public boolean isAttackPower() {
        if(modAtk > 0 || modSpecialAtk >0  || modAtk2 >0 || modDef2 >0 || modSpecialDef2 >0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Obtener si el efecto es de semidefensivo
     *
     * @return boolean.
     */

    public boolean isSemiDefensive(){
        if(modPs > 0||modPs2 >0){
            return true;
        }else{
            return false;
        }


    }


}
