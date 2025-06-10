package Domain;

import java.io.Serializable;

public class EfectoBuilder  implements Serializable {
    private String nombre;
    private String efecto;
    private int modPs = 0;
    private int modAtk = 0;
    private int modDef = 0;
    private int modSpecialAtk = 0;
    private int modSpecialDef = 0;
    private int modPs2 = 0;
    private int modAtk2 = 0;
    private int modDef2 = 0;
    private int modSpecialAtk2 = 0;
    private int modSpecialDef2 = 0;


    public EfectoBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public EfectoBuilder setEfecto(String efecto) {
        this.efecto = efecto;
        return this;
    }
    public EfectoBuilder setModPs(int modPs) {
        this.modPs = modPs;
        return this;
    }

    public EfectoBuilder setModAtk(int modAtk) {
        this.modAtk = modAtk;
        return this;
    }
    public EfectoBuilder setModDef(int modDef) {
        this.modDef = modDef;
        return this;
    }
    public EfectoBuilder setModSpecialAtk(int modSpecialAtk) {
        this.modSpecialAtk = modSpecialAtk;
        return this;
    }
    public EfectoBuilder setModSpecialDef(int modSpecialDef) {
        this.modSpecialDef = modSpecialDef;
        return this;
    }
    public EfectoBuilder setModPs2(int modPs2) {
        this.modPs2 = modPs2;
        return this;
    }
    public EfectoBuilder setModAtk2(int modAtk2) {
        this.modAtk2 = modAtk2;
        return this;
    }
    public EfectoBuilder setModDef2(int modDef2) {
        this.modDef2 = modDef2;
        return this;
    }
    public EfectoBuilder setModSpecialAtk2(int modSpecialAtk2) {
        this.modSpecialAtk2 = modSpecialAtk2;
        return this;
    }
    public EfectoBuilder setModSpecialDef2(int modSpecialDef2) {
        this.modSpecialDef2 = modSpecialDef2;
        return this;
    }


    /**
     * Para crear un efecto dependiendo sus atributos
     *
     * @return Efecto.
     */
    public Efecto build(){
        return new Efecto(
                nombre, efecto,
                modPs, modAtk, modDef, modSpecialAtk, modSpecialDef,
                modPs2, modAtk2, modDef2, modSpecialAtk2, modSpecialDef2
        );
    }


}
