package Domain;
import java.io.Serializable;

public class Movimiento implements Serializable {
    private final String nombre;
    private final String tipo;
    private int potencia;
    private final int precision;
    private int PuntosPoder;
    private int PuntosPoderRestantes;
    private transient Efecto efectoSecundario;
    private String categoria;

    public Movimiento(String nombre, String tipo, String categoria, int potencia, int precision, int ppMax, Efecto efectoSecundario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.potencia = potencia;
        this.precision = precision;
        this.PuntosPoder = ppMax;
        this.PuntosPoderRestantes = ppMax;
        this.efectoSecundario = efectoSecundario;
    }

    public void usarMovimiento(){
        if(PuntosPoderRestantes > 0){
            PuntosPoderRestantes--;
        }else{
            noMorePP();
        }
    }

    public String getNombre() {
        if (this.nombre == null) {
            throw new InvalidMove("Nombre del movimiento nulo");
        }
        return nombre;
    }

    public int getPotencia(){
        if (this.potencia < 0) {
            throw new InvalidMove("Potencia del movimiento negativo");
        }
        return potencia;
    }

    public String noMorePP(){
        if(PuntosPoderRestantes <= 0){
            return "No quedan mas PP";
        }else{
            return "";
        }

    }

    public int getDano(Pokemon atacante, Pokemon defensor, int potencia, String categoria) {
        int ataque = atacante.getAtaque();
        int defensa = defensor.getDefensa();
        int ataqueEspecial = atacante.getAtaqueEspecial();
        int defensaEspecial = defensor.getDefensaEspecial();

        double multiplicador = atacante.getEfectividadesTipo()
                .definirEfectividades()
                .getOrDefault(defensor.getTipo(), 1.0);

        int danoBase;
        if (categoria.equals("Físico")) {
            danoBase = ((42 * potencia * ataque / defensa) / 50) + 2;
        } else if (categoria.equals("Especial")) {
            danoBase = ((42 * potencia * ataqueEspecial / defensaEspecial) / 50) + 2;
        } else {
            return 0;
        }
        return (int) Math.round(danoBase * multiplicador);
    }

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public Efecto getEfectoSecundario() {
        return efectoSecundario;
    }

    public int getPuntosPoder() {return PuntosPoder;}
    public int getPuntosPoderRestantes() {return PuntosPoderRestantes;}
    public void restarPP(){
        PuntosPoderRestantes--;
    }


}

