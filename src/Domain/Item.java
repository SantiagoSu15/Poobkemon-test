package Domain;

import java.io.Serializable;

public class Item implements Serializable{
    private String nombre;
    private EfectosItems efecto;
    private String icono;

    public Item(String nombre, EfectosItems efecto, String Icono) throws InvalidItem{
        if(nombre == null || efecto == null || Icono == null){throw new InvalidItem("Item no valido");}
        this.nombre = nombre;
        this.efecto = efecto;
        this.icono = Icono;
    }





    //A determinado pokemon se le aplica el efecto del item
    // e efecto fue asignado (creado) cuando se creo el item
    //efecto es una clase cualquiera q implemente EfectosItems, efecto al implentar esa interfaz puede llamar a sus metodos
    public String usarItemEn(Pokemon pokemon){
        return efecto.aplicarEfecto(pokemon);
    }



    public String getNombre() {
        return nombre;
    }

    public EfectosItems getEfecto() {

        return efecto;
    }
    public String getIcono(){return icono;}
}