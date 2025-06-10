package Domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class ItemsInGame implements Serializable {
    private ArrayList<Item> Items;

    public ItemsInGame(){

        Items = new ArrayList<>();
        itemsDefault();

    }




    private void itemsDefault(){
        Items.add(new Item("potion",new EfectoCurar(20),"/Recursos/Imagenes/Items/potion.png" ));
        Items.add(new Item("potion",new EfectoCurar(20),"/Recursos/Imagenes/Items/potion.png" ));
        Items.add(new Item("potion",new EfectoCurar(20),"/Recursos/Imagenes/Items/potion.png" ));
        Items.add(new Item("Superpotion",new EfectoCurar(50),"/Recursos/Imagenes/Items/Superpotion.png" ));
        Items.add(new Item("Superpotion",new EfectoCurar(50),"/Recursos/Imagenes/Items/Superpotion.png" ));
        Items.add(new Item("Superpotion",new EfectoCurar(50),"/Recursos/Imagenes/Items/Superpotion.png" ));
        Items.add(new Item("Hyperpotion",new EfectoCurar(200),"/Recursos/Imagenes/Items/Hyperpotion.png" ));
        Items.add(new Item("Hyperpotion",new EfectoCurar(200),"/Recursos/Imagenes/Items/Hyperpotion.png" ));
        Items.add(new Item("Hyperpotion",new EfectoCurar(200),"/Recursos/Imagenes/Items/Hyperpotion.png" ));
        Items.add(new Item("Revive",new EfectoRevivir(),"/Recursos/Imagenes/Items/Revive.png" ));
        Items.add(new Item("Revive",new EfectoRevivir(),"/Recursos/Imagenes/Items/Revive.png" ));
        Items.add(new Item("Revive",new EfectoRevivir(),"/Recursos/Imagenes/Items/Revive.png" ));

    }





    public ArrayList<Item> getItems() {
        if(this.Items == null){throw new InvalidItem("Items vacios");}
        return Items;
    }
}