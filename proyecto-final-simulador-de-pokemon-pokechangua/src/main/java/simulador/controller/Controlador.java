package simulador.controller;

import java.util.*;

import simulador.controller.batalla.Batalla;
import simulador.controller.persistencia.ArchivosConexion;
import simulador.model.entrenador.*;
import simulador.model.pokemon.*;
import simulador.model.pokemones.*;
import simulador.view.Vista;

@SuppressWarnings("unused")
public class Controlador {

    public int sg = 2;
    private int option;
    private String busqueda;
    private int menuActual;
    private int entrenadorSeleccionado;
    private int pokemonSeleccionado;
    private int entrenadorBatalla1 = -1;
    private int entrenadorBatalla2 = -1;
    private Pokemon pokemonBatalla1;
    private Pokemon pokemonBatalla2;
    private Pokemon pokemonDeEntrenamiento;

    private boolean firstGestionarEntrenadores = true;
    private boolean firstSeleccionarEntrenador = true;
    private boolean firstGestionarPokemones = true;
    private boolean firstIniciarBatalla = true;
    private boolean firstDuranteLaBatalla = true;
    private boolean verif = true;
    private String nombrePokemonGanador;

    private LinkedList<Pokemon> pokemonesDisponibles = new LinkedList<>();
    private LinkedList<Entrenador> pokeEntrenadores = new LinkedList<>();

    final String Archivo_Pokemones = "ListaDePokemones.pokechangua";
    final String Archivo_Entrenadores = "ListaDeEntrenadores.pokechangua";



    // > > > > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > M É T O D O S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < < //

    //Complejidad temporal: O(1) Tiempo constante
    public void run() {
        
        /*/ <- Quitar * entre las barras para añadir por primera vez los 10 pokemones.
        cargarPrimerosPokemon();
        //*/

        leerDatosGuardados(Archivo_Pokemones, Archivo_Entrenadores);

        Vista.mostrarMensaje("Cargando...");
        wait(sg);
        //Vista.interfazPokeChangua();

       runMenuPrincipal();
    }

    //Complejidad temporal: O(log n) Tiempo logarítmico
    public void runMenuPrincipal(){
        while (true) {
            Vista.menuPrincipal();
            int key = Integer.parseInt(Vista.pedirString("Seleccione una opción"));
            switchMenuPrincipal(key);
        }
    }

    //Complejidad temporal: O(n) Tiempo lineal
    public void seleccionarEntrenador(){

        entrenadorSeleccionado = -1; //Reset

        if (firstSeleccionarEntrenador == true){
            Vista.mostrarMensaje(" <> ¡Has seleccionado « Seleccionar Entrenador » ! <>");
            wait(sg);
            firstSeleccionarEntrenador = false;
        }//if

        if (pokeEntrenadores.isEmpty()) {

            Vista.mostrarMensaje(" -> ERROR: ¡No hay entrenadores registrados!");
            wait(sg);

            Vista.mostrarMensaje("Regresando al menú anterior...");
            wait(sg);

            switchMenuPrincipal(1);
        } else{

            Vista.mostrarMensaje("Cargando lista de entrenadores...");
            wait(sg);

            Vista.espacioVisual();
            Vista.mostrarLinea(" <> = = = = = = = = = = = = = <>");
            for (int i = 0; i < pokeEntrenadores.size(); i++) {
                String nombre = pokeEntrenadores.get(i).getNombre();
                Vista.mostrarLinea("   [" + (i+1) + "] » " + nombre);
            }
            Vista.mostrarLinea(" <> = = = = = = = = = = = = = <>\n");

            int seleccion = Integer.parseInt(Vista.pedirString("Selecciona el entrenador que deseas usar"))-1;

            entrenadorSeleccionado = checkEntrenador(seleccion);

            Vista.mostrarMensaje(" <> ¡Has seleccionado al entrenador « " + pokeEntrenadores.get(entrenadorSeleccionado).getNombre() + " » ! <>");
            wait(sg);
        }//if

    }//cierra seleccionarEntrenador

    //Complejidad temporal: O(n) Tiempo lineal
    public void seleccionarPokemon(){

        pokemonSeleccionado = -1; //Reset

        Vista.mostrarMensaje("Cargando lista de pokemones disponibles...");
        wait(sg);

        Vista.espacioVisual();
        Vista.mostrarLinea(" <> = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = <>");
        for (int i = 0; i < pokemonesDisponibles.size(); i++) {
            Pokemon p = pokemonesDisponibles.get(i);
            Vista.mostrarLinea("    ["+(i+1)+"]  " + p.getNombre() + "     Salud -> " + p.getSalud() + "     Ataque -> " + p.getPuntosDeAtaque() + "     Tipo -> " + p.getTipo());
        }
        Vista.mostrarLinea(" <> = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = <>\n\n");

        int seleccion = Integer.parseInt(Vista.pedirString("Elige el pokémon a registrar"))-1;

        pokemonSeleccionado = checkPokemon(seleccion);

        Vista.mostrarMensaje(" <> ¡Has seleccionado a « " + pokemonesDisponibles.get(pokemonSeleccionado).getNombre() + " » ! <>");
        wait(sg);

    }//seleccionarPokemon

    //Complejidad temporal: O(1) Tiempo constante
    public void salir(){

        Vista.mostrarMensaje("Guardando estadísticas...");
        wait(sg);

        Vista.mostrarMensaje("Guardando pokemones...");
        wait(sg);

        guardarPartida(pokemonesDisponibles, pokeEntrenadores);

        Vista.mostrarMensaje(" <> » » » Saliendo de PokeMondonGo « « « <>");
        wait(sg);

        Vista.pikaPika();

        System.exit(0); //kill program
    }//cierra salir



    // > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > S W I T C H E S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < //

    //Complejidad temporal: O(1) Tiempo constante
    public void switchMenuPrincipal(int key){
        switch (key){
            case 1:

                if(firstGestionarEntrenadores == true){
                    Vista.mostrarMensaje(" <> ¡Has seleccionado « Gestionar entrenadores » ! <>");
                    wait(sg);
                    firstGestionarEntrenadores = false;
                }

                Vista.menuGestionarEntrenadores(); 
                option = Integer.parseInt(Vista.pedirString("Seleccione una opción"));
                switchGestionarEntrenadores(option);

                break;
            case 2:

                if(firstGestionarPokemones == true){
                    Vista.mostrarMensaje(" <> ¡Has seleccionado « Gestionar pokemones » ! <>");
                    wait(sg);
                    firstGestionarPokemones = false;
                }

                Vista.menuGestionarPokemones();
                option = Integer.parseInt(Vista.pedirString("Seleccione una opción"));
                switchGestionarPokemones(option);

                break;
            case 3:

                if(firstIniciarBatalla == true){
                    Vista.mostrarMensaje(" <> ¡Has seleccionado « Iniciar batalla » ! <>");
                    wait(sg);
                    firstIniciarBatalla = false;
                }

                Vista.menuIniciarBatalla();
                option = Integer.parseInt(Vista.pedirString("Seleccione una opción"));
                switchIniciarBatalla(option);

                break;
            case 0:

                salir();
            
                break;
            default:

                Vista.mostrarMensaje(" <> ¡Debe seleccionar una opción válida! <>");
                wait(sg);

                break;
        }//switch
    }//switchMenuPricipal

    //Complejidad temporal: O(n) Tiempo lineal
    public void switchGestionarEntrenadores(int option){
        switch (option){
            case 1:
                
                Vista.mostrarMensaje(" <> ¡Has seleccionado « Registrar nuevo entrenador » ! <>");
                wait(sg-1);

                String nombreEntrenador = Vista.pedirString("Ingrese el nombre del nuevo entrenador");
                LinkedList<Pokemon> pokeLista = new LinkedList<>();

                Entrenador nuevoEntrenador = new Entrenador(nombreEntrenador, pokeLista);

                Vista.mostrarMensaje("Creando nuevo entrenador...");
                wait(sg);

                pokeEntrenadores.add(nuevoEntrenador);

                Vista.mostrarMensaje("Registrando en la Liga Pókemon...");
                wait(sg);

                Vista.mostrarMensaje("   ☆ ☆ ☆ ¡Entrenador registrado con éxito! ☆ ☆ ☆");
                wait(sg);

                switchMenuPrincipal(1);

                break;

            case 2:

                Vista.mostrarMensaje(" <> ¡Has seleccionado « Ver lista de entrenadores » ! <>");
                wait(sg-1);

                if (pokeEntrenadores.isEmpty()) {

                    Vista.mostrarMensaje(" -> ERROR: ¡No hay entrenadores registrados!");
                    wait(sg);

                    Vista.mostrarMensaje("Regresando al menú anterior...");
                    wait(sg);

                    switchMenuPrincipal(1);

                } else {

                    Vista.mostrarMensaje("Cargando lista de entrenadores...");
                    wait(sg);

                    Vista.espacioVisual();
                    Vista.mostrarLinea(" <> = = = = = = = = = = = = = <>");
                    for (int i = 0; i < pokeEntrenadores.size(); i++) {
                        String nombre = pokeEntrenadores.get(i).getNombre();
                        Vista.mostrarLinea("    »  " + nombre);
                    }
                    Vista.mostrarLinea(" <> = = = = = = = = = = = = = <>\n");
                    wait(sg*2);

                    switchMenuPrincipal(1);

                }

                break;
            case 3:

                seleccionarEntrenador();

                firstSeleccionarEntrenador = true;

                break;
            case 4:
                
                Vista.mostrarMensaje("Regresando al menú principal...");
                wait(sg);

                firstGestionarEntrenadores = true;
                runMenuPrincipal();
                
                break;
            case 0:

                salir();
                
                break;
            default:

                Vista.mostrarMensaje(" <> ¡Debe seleccionar una opción válida! <>");
                wait(sg);

                switchMenuPrincipal(1);
                
                break;
        }//switch
    }//switchGestinarEntrenadores

    //Complejidad temporal: O(n) Tiempo lineal
    public void switchGestionarPokemones(int option){
        switch (option){
            case 1:

                Vista.mostrarMensaje(" <> ¡Has seleccionado « Ver pokemones registrados » ! <>");
                wait(sg-1);

                Vista.mostrarLinea(" <> = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = <>");
                for (int i = 0; i < pokemonesDisponibles.size(); i++) {
                    Pokemon p = pokemonesDisponibles.get(i);
                    Vista.mostrarLinea("    »  " + p.getNombre() + "     Salud -> " + p.getSalud() + "     Ataque -> " + p.getPuntosDeAtaque() + "     Tipo -> " + p.getTipo());
                }
                Vista.mostrarLinea(" <> = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = <>\n\n");
                wait(sg*3);

                break;
            case 2:
                
                Vista.mostrarMensaje(" <> ¡Has seleccionado « Registrar nuevo pokémon » ! <>");
                wait(sg-1);

                seleccionarPokemon();

                Vista.mostrarMensaje("Creando tu pokémon...");
                wait(sg);

                Pokemon pokemonRegistrado = pokemonesDisponibles.get(pokemonSeleccionado);

                Vista.mostrarMensaje("Verificando en la Pokédex...");
                wait(sg);

                pokemonesDisponibles.add(pokemonRegistrado);

                Vista.mostrarMensaje("   ☆ ☆ ☆ ¡Pokémon registrado con éxito! ☆ ☆ ☆");
                wait(sg);

                Vista.mostrarMensaje("Regresando al menú anterior...");
                wait(sg);

                firstGestionarPokemones = false;
                switchMenuPrincipal(2);
                break;
            case 3:

                Vista.mostrarMensaje("Regresando al menú principal...");
                wait(sg);

                firstGestionarPokemones = true;
                runMenuPrincipal();

                break;
            case 0:

                salir();

                break;
            default:

                Vista.mostrarMensaje(" <> ¡Debe seleccionar una opción válida! <>");
                wait(sg);

                switchMenuPrincipal(2);

                break;
        }//switch
    }//switchGestionarPokemones

    //Complejidad temporal: O(n) Tiempo lineal
    public void switchIniciarBatalla(int option){
        switch (option){
            case 1:

                seleccionarEntrenador();
        
                entrenadorBatalla1 = entrenadorSeleccionado;

                switchMenuPrincipal(3);

                break;
            case 2:
            
                seleccionarEntrenador();
        
                entrenadorBatalla2 = entrenadorSeleccionado;

                switchMenuPrincipal(3);
                
                break;
            case 3:

                if(entrenadorBatalla1 == -1){
                    
                    Vista.mostrarMensaje(" -> ERROR: ¡No has seleccionado un entrenador para la batalla!");
                    wait(sg);

                    switchIniciarBatalla(1);

                }else{

                    pokemonBatalla1 = pokeEntrenadores.get(entrenadorBatalla1).prepararBatalla();
                
                    switchMenuPrincipal(3);

                }
                break;
            case 4:

                if(entrenadorBatalla2 == -1){
                        
                    Vista.mostrarMensaje(" -> ERROR: ¡No has seleccionado un entrenador para la batalla!");
                    wait(sg);

                    switchIniciarBatalla(2);

                }else{

                    pokemonBatalla2 = pokeEntrenadores.get(entrenadorBatalla2).prepararBatalla();

                    switchMenuPrincipal(3);

                }
                break;
            case 5:
                //TODO: Aquí me quedé
                if (pokeEntrenadores.get(entrenadorBatalla1).equals(null) || pokeEntrenadores.get(entrenadorBatalla2).equals(null)) {
                    System.out.println("> > > Te falta seleccionar un entrenador.");
                    System.out.println(""); //Espacio visual
                    miniMenu(7);
                    break;
                } else if (pokemonBatalla1.equals(null) || pokemonBatalla2.equals(null)) {
                    System.out.println("> > > Te falta seleccionar un pokémon.");
                    System.out.println(""); //Espacio visual
                    miniMenu(7);
                    break;
                } else {
                    Batalla.iniciarBatalla(pokemonBatalla1, pokemonBatalla2, verif, nombrePokemonGanador);
                }


                break;
            case 6: //✅

                System.out.println(" \n \n \n \n \n \n"); //Espacio visual
                System.out.println("Regresando al menú principal...");     
                System.out.println(" \n \n \n \n \n \n"); //Espacio visual

                wait(sg);

                menu1();

                break;
            case 7: //✅
                
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
                System.out.println("¡ Debes seleccionar una opción válida s!");
                System.out.println(" \n \n \n \n \n \n "); //Espacio visual

                wait(sg);

                menuIniciarBatalla();
                
                break;
        }//cierra switch
    }//cierra switchIniciarBatalla



    // > > > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > H E R R A M I E N T A S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < < < //
    @SuppressWarnings("unused")
    //Complejidad temporal: O(1) Tiempo constante
    private void cargarPrimerosPokemon(){
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
    }//cargarPrimerosPokemon

    @SuppressWarnings("unchecked")
    //Complejidad temporal: O(1) Tiempo constante
    private void leerDatosGuardados(String Archivo_Pokemones, String Archivo_Entrenadores){
        
        pokemonesDisponibles = (LinkedList<Pokemon>)ArchivosConexion.leer(Archivo_Pokemones);
        pokeEntrenadores = (LinkedList<Entrenador>)ArchivosConexion.leer(Archivo_Entrenadores);

    }//leerDatosGuardados

    //Complejidad temporal: O(1) Tiempo constante
    public void guardarPartida(LinkedList<Pokemon> pokemonesDisponibles, LinkedList<Entrenador> pokeEntrenadores){

        ArchivosConexion.guardar(pokemonesDisponibles, Archivo_Pokemones);
        ArchivosConexion.guardar(pokeEntrenadores, Archivo_Entrenadores);

    }//guardarPartida

    //Complejidad temporal: O(1) Tiempo constante
    public void wait(int sec){
        try {
            Thread.sleep(sec * 1000);
         } catch (Exception e) {
            System.out.println(e);
         }
    }//wait

    //Complejidad temporal: O(log n) Tiempo logarítmico
    public int checkEntrenador(int seleccion){
        while (seleccion < 0 && seleccion >= pokeEntrenadores.size()){
            Vista.mostrarMensaje(" -> ERROR: ¡El dato ingresado es inválido!");
            wait(sg);
            seleccionarEntrenador();
        }
        return seleccion;
    }//checkEntrenador

    //Complejidad temporal: O(log n) Tiempo logarítmico
    public int checkPokemon(int seleccion){
        while (seleccion < 0 && seleccion >= pokemonesDisponibles.size()){
            Vista.mostrarMensaje(" -> ERROR: ¡El dato ingresado es inválido!");
            wait(sg);
            seleccionarPokemon();
        }
        return seleccion;
    }//checkPokemon

}//class
