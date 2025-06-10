package Domain;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class PokemonsInGame implements Serializable {
    private ArrayList<Pokemon> pokemons;

    public PokemonsInGame() {
        pokemons = new ArrayList<>();

        loadPokemonsFromJson();

    }

    private void loadPokemonsFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/Recursos/Data/pokemons.json")) {
            if (inputStream == null) {
                System.err.println("No se encontró el archivo pokemons.json en /Recursos");
                pokemonsDefault();
                return;
            }
            PokemonData[] dataPoke = mapper.readValue(inputStream, PokemonData[].class);
            for (PokemonData data : dataPoke) {
                data.validarPokes();
                pokemons.add(new Pokemon(
                        data.getName(),
                        data.getType(),
                        data.getDescription(),
                        data.getPs(),
                        data.getAttack(),
                        data.getDefense(),
                        data.getSpecialAttack(),
                        data.getSpecialDefense(),
                        data.getSpeed(),
                        data.getFrontImage(),
                        data.getBackImage(),
                        data.getIconImage()
                ));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el poke.json: " + e.getMessage());
            pokemonsDefault();
        }
    }




    public void pokemonsDefault(){
        pokemons.add(new Pokemon("Blastoise","Agua","Blastoise pertenece a la primera generación de pokemon, proveniente de Kanto, y es la última evolución de\n" +
                "Squirtle. Es una enorme tortuga bípeda equipada con dos poderosos cañones situados en su espalda capaces\n" +
                "de disparar potentes chorros de agua, con la fuerza suficiente para quebrar muros de hormigón o perforar\n" +
                "en planchas de acero. Para evitar el retroceso, se planta firmemente sus patas traseras en el suelo y, sube\n" +
                "deliberadamente de peso.",362,291,328,280,295,339
                ,"/Recursos/Imagenes/Pokemones/PokemonsFrente/blastoise.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/blastoise.png","/Recursos/Imagenes/Pokemones/Iconos/blastoise.png"));

        pokemons.add(new Pokemon("Charizard","Fuego","Charizard pertenece a la primera generación de pokemon, proveniente de Kanto, y es la última evolución de\n" +
                "Charmander. Es un dragón erguido sobre sus dos patas traseras que posee unas poderosas alas y un\n" +
                "abrasador aliento de fuego. Tiene el cuello largo y una poderosa cola terminada en una llama que arde con\n" +
                "más fuerza si ha vivido duros combates; sin embargo, si esta se apaga el pokemon puede llegar a morir.",
                360,293,280,328,348,295,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/charizard.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/charizard.png","/Recursos/Imagenes/Pokemones/Iconos/charizard.png"));

        pokemons.add(new Pokemon("Venusaur", "Planta", "Venusaur pertenece a la primera generación de pokemon, proveniente de Kanto, y es la última evolución de\n" +
                "Bulbasaur. Es una rana que lleva sobre su lomo el botón de una flor que se abre completamente, dejando\n" +
                "ver una enorme flor rosada que se nutre de la luz solar por fotosíntesis. A través de ella, realiza uno de sus\n" +
                "ataques más potentes: rayo solar.",
                364, 289, 391, 284, 328, 328,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Venusaur.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Venusaur.png","/Recursos/Imagenes/Pokemones/Iconos/Venusaur.png"));

        pokemons.add(new Pokemon("Gengar", "Fantasma", "Gengar pertenece a la primera generación de pokemon, proveniente de Kanto, y es la última evolución de\n" +
                "Gastly. Gengar está basado en el concepto del Doppelgänger y en la gente sombra. Es un pokemon con\n" +
                "extremidades pequeñas con personalidad siniestra y tenebrosa, en estado salvaje. Por las noches sale a\n" +
                "espantar y desorientar a los viajeros para robar sus almas.",
                324, 251, 240, 350, 394, 273,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Gengar.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Gengar.png","/Recursos/Imagenes/Pokemones/Iconos/Gengar.png"));

        pokemons.add(new Pokemon("Dragonite", "Dragon", "Dragonite pertenece a la primera generación de pokemon, proveniente de Kanto, y es la última evolución de\n" +
                "Dratini. Es un pokemon dragón, conocido como el \"Avatar del Mar\". Al igual que sus preevoluciones, es un\n" +
                "pokemon de buen corazón y disfruta volar por el océano rescatando náufragos y guiar barcos perdidos en\n" +
                "las tormentas hasta la costa. Gracias a su constitución, desafia las más fieras tempestades sin sufrir daño.",
                386, 403, 317, 284, 328, 328,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Dragonite.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Dragonite.png","/Recursos/Imagenes/Pokemones/Iconos/Dragonite.png"));

        pokemons.add(new Pokemon("Togetic", "Hada", "Togetic pertenece a la segunda generación de pokemon, proveniente de Johto, siendo la evolución del\n" +
                "pokemon huevo Togepi. Togetic trae la buena suerte y, si detecta a una persona de buen corazón, se aparece\n" +
                "frente a ella y la inunda de felicidad esparciendo un plumón luminoso, llamado \"polvillo de la alegría\". Sin\n" +
                "embargo, si no está con gente amable, se entristece.",
                314, 196, 295, 196, 284, 339,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Togetic.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Togetic.png","/Recursos/Imagenes/Pokemones/Iconos/Togetic.png"));

        pokemons.add(new Pokemon("Tyranitar", "Roca", "Tyranitar pertenece a la segunda generación de pokemon, originaria de Johto, y es la última evolución de\n" +
                "Larvitar. Su cuerpo es muy resistente, que lo hace casi inmune a los ataques, gracias a esto le encanta\n" +
                "desafiar a sus oponentes. Es de una naturaleza insolente y egoísta. En sus garras tiene el poder suficiente\n" +
                "para hacer temblar la tierra y las montañas cambiando con facilidad el paisaje que le rodea.",
                404, 403, 350, 243, 317, 328,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Tyranitar.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Tyranitar.png","/Recursos/Imagenes/Pokemones/Iconos/Tyranitar.png"));

        pokemons.add(new Pokemon("Gardevoir", "Psiquico", "Gardevoir pertenece a la tercera generación de pokemon, proveniente de Hoenn, y es la última evolución de\n" +
                "Ralts. Gardevoir tiene una habilidad que ningún otro pokemon posee: captar los sentimientos de su\n" +
                "entrenador para ayudarle en caso de peligro, independientemente de si su vida está en riesgo. Además, es\n" +
                "capaz prever situaciones futuras. Usando esta capacidad, Gardevoir anticipa cuándo su entrenador estará en\n" +
                "peligro y utiliza su energía psicoquinética para protegerlo.",
                340, 251, 251, 284, 383, 361,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Gardevoir.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Gardevoir.png","/Recursos/Imagenes/Pokemones/Iconos/Gardevoir.png"));

        pokemons.add(new Pokemon("Snorlax", "Normal", "Snorlax pertenece a la primera generación de pokemon, proveniente de Kanto. Snorlax come muchos kilos\n" +
                "de comida al día y, después de comer, lo único que hace es echarse a dormir hasta volver a despertarse por\n" +
                "el hambre. Si lo despiertan, se enfadará y atacará ferozmente, aunque después de un rato se volverá a\n" +
                "dormir. Ttiene la habilidad de hacer que los árboles crezcan más rápidamente después de destruirlos.",
                524, 350, 251, 174, 251, 350,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Snorlax.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Snorlax.png","/Recursos/Imagenes/Pokemones/Iconos/Snorlax.png"));

        pokemons.add(new Pokemon("Metagross", "Acero", "Metagross pertenece a la tercera generación de pokemon, proveniente de Hoenn, y es la última evolución de\n" +
                "Beldum. Metagross es el resultado de la fusión de dos Metang; este a su vez es la fusión de dos Beldum. Esto\n" +
                "hace que Metagross tenga cuatro cerebros, unidos por una compleja red neuronal, capaces de resolver\n" +
                "cálculos complicados más rápido que el mejor superordenador de última generación.",
                364, 405, 394, 262, 317, 306,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Metagross.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Metagross.png","/Recursos/Imagenes/Pokemones/Iconos/Metagross.png"));

        pokemons.add(new Pokemon("Donphan", "Tierra", "Donphan pertenece a la segunda generación de pokemon, proveniente de Johto, siendo la evolución de\n" +
                "Phanpy. Tiene un par de afilados colmillos, los cuales son más largos en los machos, que tardan mucho en\n" +
                "crecer. Es de carácter sociable, tranquilo y viven en manadas donde el estatus se basa en el largo de los\n" +
                "colmillos: entre más largos, mayor es el estatus. En época de celo, ruedan sobre sí mismos para demostrar a\n" +
                "las hembra su fuerza.",
                384, 372, 372, 218, 240, 240,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Donphan.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Donphan.png","/Recursos/Imagenes/Pokemones/Iconos/Donphan.png"));

        pokemons.add(new Pokemon("Machamp", "Lucha", "Machamp pertenece a la primera generación de pokemon, proveniente de Kanto, y es la última evolución de\n" +
                "Machop. Machamp es uno de los pokemon de tipo lucha más fuertes. Es capaz de dar 1000 golpes desde\n" +
                "todos los ángulos en tan solo dos segundos, cada uno de estos puñetazos tiene un megatón de potencia, y si\n" +
                "toma al rival por los pies con sus cuatro brazos, lo lanzará lo más lejos posible hasta perderse por el\n" +
                "horizonte, ganando fácilmente el combate.",
                384, 394, 284, 229, 251, 295,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Machamp.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Machamp.png","/Recursos/Imagenes/Pokemones/Iconos/Machamp.png"));

        pokemons.add(new Pokemon("Delibird", "Hielo", "Delibird pertenece a la segunda generación de pokemon, proveniente de Johto. Este pokemon tiene una\n" +
                "larga cola, en la cual puede llevar alimento, agua y cualquier cantidad de objetos guardados mientras vuela.\n" +
                "Su habilidad principal es la de sacar regalos de su cola en forma de bolsa, algunos de ellos pueden explotar,\n" +
                "aunque otros restauran la salud. Lleva siempre comida consigo, si se encuentra con montañeros o pokemon\n" +
                "que vagan perdidos, la comparte.",
                294, 229, 207, 273, 251, 207,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Delibird.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Delibird.png","/Recursos/Imagenes/Pokemones/Iconos/Delibird.png"));

        pokemons.add(new Pokemon("Raichu", "Electrico", "Raichu pertenece a la primera generación de pokemon, proveniente de Kanto, siendo la evolución de\n" +
                "Pikachu. La habilidad de Raichu de contener corrientes eléctricas es impresionante; es común que sus\n" +
                "ataques eléctricos tengan 10.000 voltios de energía, pero se han registrado casos de hasta 100.000. Incluso\n" +
                "cuando no está luchando, el cuerpo de Raichu emite una débil carga eléctrica que le hace luminoso en la\n" +
                "oscuridad, y le puede dar una buena descarga a cualquiera que ose asustarle o tocarle sin previo aviso.",
                324, 306, 229, 350, 306, 284,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Raichu.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Raichu.png","/Recursos/Imagenes/Pokemones/Iconos/Raichu.png"));

        pokemons.add(new Pokemon("Houndour", "Siniestro", "Houndour sale a cazar con el resto de la manada con total coordinación.\n" +
                "Estos Pokémon se comunican unos con otros usando una serie de aullidos para acorralar a su presa. El compañerismo que existe entre ellos es incomparable.",
                294, 240, 174, 251, 284, 218,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Houndour.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Houndour.png","/Recursos/Imagenes/Pokemones/Iconos/Houndour.png"));

        pokemons.add(new Pokemon("Umbreon", "Siniestro", "Umbreon evolucionó tras haber estado expuesto a ondas lunares. " +
                "Suele esconderse en la oscuridad en silencio y esperar a que su presa se mueva. Cuando se lanza al ataque, le brillan los anillos del cuerpo",
                394, 251, 350, 251, 240, 394,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Umbreon.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Umbreon.png","/Recursos/Imagenes/Pokemones/Iconos/Umbreon.png"));

        pokemons.add(new Pokemon("Arcanine", "Fuego", "Arcanine es conocido por lo veloz que es." +
                " Dicen que es capaz de correr 10.000 kilómetros en 24 horas. El fuego que arde con vigor en el interior de este Pokémon constituye su fuente de energía.",
                384, 350, 284, 317, 328, 284,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Arcanine.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Arcanine.png","/Recursos/Imagenes/Pokemones/Iconos/Arcanine.png"));

        pokemons.add(new Pokemon("Gyarados", "Agua", "Cuando Magikarp evoluciona y se convierte en Gyarados, sufre un cambio estructural en las células del cerebro." +
                " Dicen que esa transformación es la causa de la naturaleza violenta y salvaje de este Pokémon",
                394, 383, 282, 287, 240, 328,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Gyarados.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Gyarados.png","/Recursos/Imagenes/Pokemones/Iconos/Gyarados.png"));

        pokemons.add(new Pokemon("Jolteon", "Electrico", "Las células de Jolteon generan un nivel bajo de electricidad, cuya intensidad aumenta con la electricidad estática" +
                " que acumula en un pelaje formado por agujas cargadas de electricidad. Esta característica le permite lanzar rayos.",
                334, 251, 240, 394, 350, 317,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Jolteon.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Jolteon.png", "/Recursos/Imagenes/Pokemones/Iconos/Jolteon.png"));

        pokemons.add(new Pokemon("Cacturne", "Planta", "Durante el día, Cacturne permanece inmóvil para no perder nada de humedad bajo el sol de justicia del desierto." +
                " Este Pokémon entra en acción por la noche, cuando bajan las temperaturas.",
                334, 361, 240, 229, 361, 240,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Cacturne.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Cacturne.png", "/Recursos/Imagenes/Pokemones/Iconos/Cacturne.png"));

        pokemons.add(new Pokemon("Scyther", "Bicho", "Es espectacular ver lo rápido que es Scyther. Su increíble velocidad refuerza el efecto del par de guadañas que tiene en los brazos," +
                " que ya son de por sí contundentes; rebanan gruesos troncos de un tajo.",
                344, 350, 284, 339, 229, 284,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Scyther.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Scyther.png", "/Recursos/Imagenes/Pokemones/Iconos/Scyther.png"));

        pokemons.add(new Pokemon("Beedrill", "Bicho", "Los Beedrill defienden su territorio a toda costa." +
                " No es conveniente acercarse a su colmena, por seguridad. Si se les molesta, todo un enjambre atacará ferozmente.",
                334, 306, 196, 273, 207, 284,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Beedrill.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Beedrill.png", "/Recursos/Imagenes/Pokemones/Iconos/Beedrill.png"));

        pokemons.add(new Pokemon("Ursaring", "Normal", "En los bosques habitados por Ursaring, dicen que abundan los arroyos y árboles gigantes en los que guarda su alimento." +
                " Este Pokémon se dedica todos los días a pasear por el bosque para buscar comida y guardarla.",
                384, 394, 273, 229, 273, 273,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Ursaring.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Ursaring.png", "/Recursos/Imagenes/Pokemones/Iconos/Ursaring.png"));

        pokemons.add(new Pokemon("Primeape", "Lucha", "Cuando Primeape se enfada," +
                " se le acelera el ritmo cardíaco y se le fortalecen los músculos. Con todo, pierde en inteligencia.",
                334, 339, 240, 317, 240, 262,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Primeape.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Primeape.png", "/Recursos/Imagenes/Pokemones/Iconos/Primeape.png"));

        pokemons.add(new Pokemon("Seviper", "Veneno", "Seviper lleva años de gran enemistad con Zangoose. " +
                "Las cicatrices que tiene por todo el cuerpo dan buena muestra de los encarnizados combates en los que se han enfrentado. Seviper ataca usando su cola con forma de espada.",
                350, 328, 240, 251, 328, 240,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Seviper.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Seviper.png", "/Recursos/Imagenes/Pokemones/Iconos/Seviper.png"));

        pokemons.add(new Pokemon("Koffing", "Veneno", "Si Koffing se pone nervioso, " +
                "aumenta el nivel de toxicidad de los gases que tiene y los expulsa por todo el cuerpo. También suele hincharse mucho hasta llegar a explotar.",
                284, 251, 317, 185, 240, 207,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Koffing.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Koffing.png", "/Recursos/Imagenes/Pokemones/Iconos/Koffing.png"));

        pokemons.add(new Pokemon("Cubone", "Tierra", "A Cubone le ahoga la pena porque no volverá a ver jamás a su madre. La luna le recuerda a veces a ella y se pone a chillar." +
                " Los churretes que tiene en el cráneo que lleva puesto, son debidos a las lágrimas que derrama.",
                304, 218, 317, 285, 296, 218,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Cubone.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Cubone.png", "/Recursos/Imagenes/Pokemones/Iconos/Cubone.png"));

        pokemons.add(new Pokemon("Armaldo", "Roca", "Armaldo tiene una sólida armadura que repele cualquier ataque." +
                " Este Pokémon puede estirar o encoger todo lo que quiera las pinzas que tiene. Con ellas, puede atravesar una plancha de acero.",
                354, 383, 328, 207, 262, 284,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Armaldo.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Armaldo.png", "/Recursos/Imagenes/Pokemones/Iconos/Armaldo.png"));

        pokemons.add(new Pokemon("Articuno", "Hielo", "Articuno es un legendario pájaro Pokémon que puede controlar el hielo." +
                " El batir de sus alas congela el aire. Dicen que consigue hacer que nieve cuando vuela.",
                384, 295, 328, 295, 317, 383,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Articuno.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Articuno.png", "/Recursos/Imagenes/Pokemones/Iconos/Articuno.png"));

        pokemons.add(new Pokemon("Wobbuffet", "Psiquico", "Si dos o más Wobbuffet se encuentran, se volverán competitivos e intentarán superarse en resistencia. " +
                "Pero también puede que intenten aguantar sin comer; algo que los Entrenadores deben tener muy en cuenta",
                584, 181, 236, 181, 181, 236,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Wobbuffet.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Wobbuffet.png", "/Recursos/Imagenes/Pokemones/Iconos/Wobbuffet.png"));

        pokemons.add(new Pokemon("Duskull", "Fantasma", "Duskull puede atravesar los muros, sea cual sea el grosor que tengan." +
                " Cuando este Pokémon elige su objetivo, lo persigue hábilmente hasta el amanecer.",
                244, 196, 306, 163, 174, 306,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Duskull.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Duskull.png", "/Recursos/Imagenes/Pokemones/Iconos/Duskull.png"));

        pokemons.add(new Pokemon("Rayquaza", "Dragon", "Rayquaza vivió durante cientos de millones de años en la capa de ozono sin bajar a la tierra ni una vez." +
                " Según parece, este Pokémon se alimentaba del agua y las partículas que había en la atmósfera.",
                414, 438, 306, 317, 438, 306,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Rayquaza.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Rayquaza.png", "/Recursos/Imagenes/Pokemones/Iconos/Rayquaza.png"));

        pokemons.add(new Pokemon("Aggron", "Acero", "Aggron marca una montaña entera como su territorio y acaba con todo lo que pueda ponerlo en peligro. " +
                "Este Pokémon está continuamente patrullando la zona en defensa de su terreno.",
                344, 350, 504, 218, 240, 240,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Aggron.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/Aggron.png", "/Recursos/Imagenes/Pokemones/Iconos/Aggron.png"));

        pokemons.add(new Pokemon("Clefable", "Hada", "Clefable se mueve dando saltitos como si fuera haciendo uso de las alas. Estos pequeños brincos le permiten caminar por el agua." +
                " De todos es sabido que le encanta darse paseos por los lagos en tranquilas noches de luna llena.",
                394, 262, 269, 240, 317, 306,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Clefable.png", "/Recursos/Imagenes/Pokemones/PokemonsEspalda/Clefable.png", "/Recursos/Imagenes/Pokemones/Iconos/Clefable.png"));

        pokemons.add(new Pokemon("Pidgeot", "Volador", "El plumaje de este Pokémon es bonito e hipnótico. " +
                "Muchos Entrenadores se quedan embobados ante la belleza impactante de las plumas que tiene en la cabeza; lo que les lleva a elegir a Pidgeot como su Pokémon.",
                370, 284, 273, 331, 262, 262,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Pidgeot.png", "/Recursos/Imagenes/Pokemones/PokemonsEspalda/Pidgeot.png", "/Recursos/Imagenes/Pokemones/Iconos/Pidgeot.png"));

        pokemons.add(new Pokemon("Aerodactyl", "Volador", "Los orígenes de Aerodactyl datan de la era de los dinosaurios. Se regeneró a partir de material genético contenido en ámbar. " +
                "Se supone que fue el amo de los cielos en épocas pasadas.",
                364, 364, 251, 394, 240, 273,
                "/Recursos/Imagenes/Pokemones/PokemonsFrente/Aerodactyl.png", "/Recursos/Imagenes/Pokemones/PokemonsEspalda/Aerodactyl.png", "/Recursos/Imagenes/Pokemones/Iconos/Aerodactyl.png"));

    }





    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {

    }
}
class   PokemonData  implements Serializable{
    private String name;
    private String type;
    private String description;
    private int ps;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private String frontImage;
    private String backImage;
    private String iconImage;

    public void validarPokes()throws InvalidPokemon{
        if(name == null || name.isBlank()) throw new InvalidPokemon("El nombre no puede estar vacio");
        if(type == null || type.isBlank()) throw  new InvalidPokemon("El tipo no puede estar vacio");
        if(description == null) throw new InvalidPokemon("El descripcion no puede estar vacio");
        if(ps < 1) throw new InvalidPokemon("La vida no puede ser menor o igual a 0");
        if(attack<1|| specialAttack <1) throw new InvalidPokemon("El ataque no puede ser menor o igual a 0");
        if(defense<1 || specialDefense<1) throw new InvalidPokemon("La defensa no puede ser menor o igual a 0");
        if (frontImage == null || frontImage.isBlank()) throw new InvalidPokemon("El frontImage no puede estar vacio");
        if(backImage == null || backImage.isBlank()) throw new InvalidPokemon("El backImage no puede estar vacio");
        if(iconImage== null || iconImage.isBlank()) throw new InvalidPokemon("El iconImage no puede estar vacio");


    }






    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getPs() { return ps; }
    public void setPs(int ps) { this.ps = ps; }
    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }
    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }
    public int getSpecialAttack() { return specialAttack; }
    public void setSpecialAttack(int specialAttack) { this.specialAttack = specialAttack; }
    public int getSpecialDefense() { return specialDefense; }
    public void setSpecialDefense(int specialDefense) { this.specialDefense = specialDefense; }
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public String getFrontImage() { return frontImage; }
    public void setFrontImage(String frontImage) { this.frontImage = frontImage; }
    public String getBackImage() { return backImage; }
    public void setBackImage(String backImage) { this.backImage = backImage; }
    public String getIconImage() { return iconImage; }
    public void setIconImage(String iconImage) { this.iconImage = iconImage; }




}