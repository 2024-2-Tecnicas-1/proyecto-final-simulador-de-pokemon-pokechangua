package simulador.controller;

import java.util.*;

import simulador.controller.persistencia.ArchivosConexion;
import simulador.controller.batalla.Batalla;

import simulador.model.entrenador.*;
import simulador.model.pokemon.*;
import simulador.model.pokemones.*;
import simulador.view.Vista;

@SuppressWarnings("unused")
public class Controlador {

    private int sg = 2;
    private int option;
    private String busqueda;
    private int menuActual;
    private int entrenadorSeleccionado;
    private int pokemonSeleccionado;
    private int entrenadorBatalla1;
    private int entrenadorBatalla2;
    private Pokemon pokemonBatalla1;
    private Pokemon pokemonBatalla2;
    private Pokemon pokemonDeEntrenamiento;

    private boolean firstMenuGestionarEntrenadores = true;
    private boolean firstSubMenuSeleccionarEntrenadores = true;
    private boolean firstMenuGestionarPokemones = true;
    private boolean firstMenuIniciarBatalla = true;
    private boolean firstSubMenuDuranteLaBatalla = true;
    private boolean verif = true;
    private String nombrePokemonGanador;

    private LinkedList<Pokemon> pokemonesDisponibles = new LinkedList<>();
    private LinkedList<Entrenador> pokeEntrenadores = new LinkedList<>();

    public void run() {
        
        final String Archivo_Pokemones = "ListaDePokemones.pokemondongo";
        final String Archivo_Entrenadores = "ListaDeEntrenadores.pokemondongo";

        /*/ <- Quitar * entre las barras para añadir por primera vez los 10 pokemones.
        cargarPrimerosPokemon(Archivo_Pokemones, Archivo_Entrenadores);
        //*/

        leerDatosGuardados(Archivo_Pokemones, Archivo_Entrenadores);

        Vista.mostrarMensaje("Cargando...");
        //Vista.interfazPokeChangua();

        while (true) {
            Vista.menuPrincipal();
            int key = Integer.parseInt(Vista.pedirString("Seleccione una opción"));
            switchMenuPrincipal(key);
        }
    }

    // > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > S W I T C H E S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < //

    public void switchMenuPrincipal(int key){
        switch (key){
            case 1:
                Vista.mostrarMensaje("Cargando...");
                wait(sg);
                Vista.menuGestionarEntrenadores(); 
                option = Integer.parseInt(Vista.pedirString("Seleccione una opción"));
                switchMenuGestionarEntrenadores(option);
                break;
            case 2:
                Vista.mostrarMensaje("Cargando...");
                wait(sg);
                Vista.menuGestionarPokemones();
                option = Integer.parseInt(Vista.pedirString("Seleccione una opción"));
                //switchMenuGestionarPokemones(option);
                break;
            case 3:
                Vista.mostrarMensaje("Cargando...");
                wait(sg);
                Vista.menuIniciarBatalla();
                option = Integer.parseInt(Vista.pedirString("Seleccione una opción"));
                //switchIniciarBatalla(option);
                break;
            case 0:
                //salir();
                break;
            default:
                Vista.mostrarMensaje(" <> ¡Debe seleccionar una opción válida! <>");
                wait(sg);
                break;
        }
    }

    public void switchMenuGestionarEntrenadores(int option){
        switch (option){
            case 1:
                
                Vista.mostrarMensaje(" <> ¡Has seleccionado « Registrar nuevo entrenador » ! <>");
                wait(sg);
                
                String nombreEntrenador = Vista.pedirString("Ingrese el nombre del nuevo entrenador");

                Entrenador entrenadorNuevo = new Entrenador(nombreEntrenador, new LinkedList<>());
                pokeEntrenadores.add(entrenadorNuevo);
                //pokeEntrenadores.add(new Entrenador(nombreEntrenador, new LinkedList<>()));

                System.out.println(" \n \n \n \n \n \n"); //Espacio visual
                System.out.println("Creando nuevo entrenador...");
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual

                wait(sg);

                System.out.println("Registrando en la Liga Pókemon...");
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual

                wait(sg);

                System.out.println(" ☆ ☆ ☆ ¡Entrenador registrado con éxito! ☆ ☆ ☆ ");
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual

                wait(sg);

                System.out.println("Regresando al menú anterior...");
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual

                wait(sg);

                menuGestionarEntrenadores();

                break;

            case 2: //✅

                System.out.println(" \n \n \n \n \n \n"); //Espacio visual
                System.out.println("¡Has seleccionado « Ver lista de entrenadores » !");     
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual
            
                wait(sg);

                interfazDeCarga();

                wait(sg);

                if (pokeEntrenadores.isEmpty()) {

                    System.out.println(" \n \n \n \n \n \n"); //Espacio visual
                    System.out.println("¡ No hay entrenadores registrados !");
                    System.out.println(" \n \n \n "); //Espacio visual

                    miniMenu(3);

                } else{

                    System.out.println(" \n \n \n \n \n \n"); //Espacio visual
                    System.out.println(" > > > Lista de Entrenadores < < < ");
                    System.out.println(""); //Espacio visual

                    for (int i = 0; i < pokeEntrenadores.size(); i++) {
                        String pokeNombre = pokeEntrenadores.get(i).getNombre();
                        System.out.println(" > | " + pokeNombre + " |");
                    }//cierra for

                    System.out.println(" \n \n "); //Espacio visual

                    miniMenu(3);

                }//cierra else-if

                menu1();

                break;
            case 3: //✅

                subMenuSeleccionarEntrenadores();

                break;
            case 4: //✅
                
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual
                System.out.println("Regresando al menú principal...");     
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual
    
                wait(sg);

                menu1();

                break;
            case 5: //✅

                System.out.println(" \n \n \n \n \n \n"); //Espacio visual
                System.out.println("Guardando estadísticas...");     
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual
        
                wait(sg);

                System.out.println(" \n \n \n \n \n \n"); //Espacio visual
                System.out.println("Guardando pokemones...");     
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual

                salir();
                
                break;
            default: //✅
                
                System.out.println(" \n \n \n \n \n \n "); //Espacio visual
                System.out.println("¡Debe seleccionar una opción válida!");
                System.out.println(" \n \n \n \n \n \n "); //Espacio visual

                wait(sg);

                System.out.println("Reiniciando...");
                System.out.println(" \n \n \n \n \n \n "); //Espacio visual

                menuGestionarEntrenadores();

                break;
        }
    }

    
    // > > > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > H E R R A M I E N T A S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < < < //
    @SuppressWarnings("unused")
    private void cargarPrimerosPokemon(String Archivo_Pokemones, String Archivo_Entrenadores){
        Pokemon Articuno = new Articuno("Articuno", 90, 85, TipoPokemon.HIELO.VOLADOR); //🅿️ TODO:Dos tipos.
        pokemonesDisponibles.add(Articuno);
        Pokemon Caterpie = new Caterpie("Caterpie", 45, 30, TipoPokemon.HIELO.BICHO); //🅿️ TODO:Dos tipos.
        pokemonesDisponibles.add(Caterpie);
        Pokemon Charmander = new Charmander("Charmander", 39, 52, TipoPokemon.FUEGO);
        pokemonesDisponibles.add(Charmander);
        Pokemon Diglett = new Diglett("Diglett", 10, 55, TipoPokemon.TIERRA);
        pokemonesDisponibles.add(Diglett);
        Pokemon Drowzee = new Drowzee("Drowzee", 60, 48, TipoPokemon.PSIQUICO);
        pokemonesDisponibles.add(Drowzee);
        Pokemon Hitmonlee = new Hitmonlee("Hitmonlee", 50, 120, TipoPokemon.LUCHA);
        pokemonesDisponibles.add(Hitmonlee);
        Pokemon Jolteon = new Jolteon("Jolteon", 65, 65, TipoPokemon.ELECTRICO);
        pokemonesDisponibles.add(Jolteon);
        Pokemon Shellder = new Shellder("Shellder", 30, 65, TipoPokemon.AGUA);
        pokemonesDisponibles.add(Shellder);
        Pokemon Snorlax = new Snorlax("Snorlax", 160, 110, TipoPokemon.NORMAL);
        pokemonesDisponibles.add(Snorlax);
        Pokemon Tentacool = new Tentacool("Tentacool", 90, 85, TipoPokemon.AGUA.VENENO); //🅿️ TODO:Dos tipos.
        pokemonesDisponibles.add(Tentacool);

        ArchivosConexion.guardar(pokemonesDisponibles, Archivo_Pokemones);
        ArchivosConexion.guardar(pokeEntrenadores, Archivo_Entrenadores);
    }

    @SuppressWarnings("unchecked")
    private void leerDatosGuardados(String Archivo_Pokemones, String Archivo_Entrenadores){
        
        pokemonesDisponibles = (LinkedList<Pokemon>)ArchivosConexion.leer(Archivo_Pokemones);
        pokeEntrenadores = (LinkedList<Entrenador>)ArchivosConexion.leer(Archivo_Entrenadores);
    }

    public static void wait(int sec){
        try {
            Thread.sleep(sec * 1000);
         } catch (Exception e) {
            System.out.println(e);
         }
    }

}
