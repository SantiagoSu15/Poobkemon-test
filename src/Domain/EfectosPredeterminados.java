package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EfectosPredeterminados implements Serializable {
    private final List<Efecto> efectosSecundarios;




    /**
     * Crear una lista de efectos predeterimanos, se usa EfectoBuilder para crear los efectos
     *
     *
     *
     * @return String.
     */
    public EfectosPredeterminados() {
        efectosSecundarios = new ArrayList<>();

        efectosSecundarios.add(new EfectoBuilder().setNombre("Drenadoras").setEfecto("drenar").setModPs(50).setModPs2(50).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Dia Soleado").setEfecto("defensa propia y rival").setModDef(10).setModDef2(10).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Refugio").setEfecto("defensa propia").setModDef(10).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Disparo Demora").setEfecto("daño rival y defensa propia").setModDef(15).setModPs2(60).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Gruñido").setEfecto("ataque rival").setModAtk2(15).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Tóxico").setEfecto("daño ataque y ataque especial rival").setModPs2(20).setModAtk2(20).setModSpecialAtk2(20).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Ataque Arena").setEfecto("vida propia y defensa rival").setModPs(30).setModDef2(10).setModSpecialAtk2(20).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Danza Pluma").setEfecto("vida defensa y ataque propio").setModPs(30).setModAtk(15).setModDef(10).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Niebla").setEfecto("daño rival y ataque propio").setModAtk(15).setModPs2(50).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Pantalla Luz").setEfecto("ataque y defensa especial propio").setModSpecialAtk(15).setModSpecialDef(15).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Maldición").setEfecto("daño ataques y defensas rivales").setModPs2(20).setModAtk2(15).setModDef2(10).setModSpecialAtk2(15).setModSpecialDef2(10).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Deseo").setEfecto("vida ataques y defensas propias").setModPs(20).setModAtk(15).setModDef(10).setModSpecialAtk(15).setModSpecialDef(10).build());

        efectosSecundarios.add(new EfectoBuilder().setNombre("Golpe Bajo").setEfecto("inmolacion").setModPs(-70).setModSpecialAtk(5).setModPs2(100).build());


    }
    public ArrayList<Efecto> getEfectos() {
        return new ArrayList<>(efectosSecundarios);
    }

    public Efecto getEfecto(String nombre) {
        for (Efecto efecto : efectosSecundarios) {
            if (efecto.getNombre().equalsIgnoreCase(nombre)) {
                return efecto;
            }
        }
        return null;
    }
}
