package simulador.controller;

import java.io.Serializable;
import java.util.*;

import simulador.controller.batalla.*;
import simulador.controller.persistencia.*;
import simulador.model.entrenador.*;
import simulador.model.pokemon.*;
import simulador.model.pokemones.*;
import simulador.view.*;

public class Controlador implements Serializable {

    //Primera vez entrando a los menús
    private boolean firstGestionarEntrenadores = true;
    private boolean firstSeleccionarEntrenador = true;
    private boolean firstGestionarPokemones = true;
    private boolean firstIniciarBatalla = true;

    //Guardar partida
    private boolean guardar = false; //interacción manual

    //Modificador de tiempo entre mensajes
    final public int sg = 2; //interacción manual -> Original = 2

    //option general de switches
    private int option;

    //seleccionadores
    private int entrenadorSeleccionado;
    private int pokemonSeleccionado;

    private int entrenadorBatalla1 = -1;
    private int entrenadorBatalla2 = -1;

    private Pokemon pokemonBatalla1;
    private Pokemon pokemonBatalla2;

    //listas
    private LinkedList<Pokemon> pokemonesDisponibles = new LinkedList<>();
    private LinkedList<Entrenador> pokeEntrenadores = new LinkedList<>();

    //nombres de archivos
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
        Vista.mostrarLinea(" <> = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = <>");
        for (int i = 0; i < pokemonesDisponibles.size(); i++) {
            Pokemon p = pokemonesDisponibles.get(i);
            Vista.mostrarLinea("    ["+(i+1)+"]  " + p.getNombre() + "     Salud -> " + p.getSalud() + "     Ataque -> " + p.getPuntosDeAtaque() + "     Tipo -> " + p.getTipo());
        }
        Vista.mostrarLinea(" <> = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = <>\n\n");

        int seleccion = Integer.parseInt(Vista.pedirString("Selecciona un pokémon"))-1;

        pokemonSeleccionado = checkPokemon(seleccion);

        Vista.mostrarMensaje(" <> ¡Has seleccionado a « " + pokemonesDisponibles.get(pokemonSeleccionado).getNombre() + " » ! <>");
        wait(sg);

    }//seleccionarPokemon

    //Complejidad temporal: O(log n) Tiempo logarítmico
    public void salir(){

        if(guardar == false){

            String dato = "";

            while(!dato.equalsIgnoreCase("Y") &&  !dato.equalsIgnoreCase("N")){

                Vista.mostrarMensaje("No se guardarán los datos...");
                dato = Vista.pedirString("¿Desea guardar los datos? (Y/N)");

                if(dato.equalsIgnoreCase("Y")){
                    guardar = true;
                }else if(dato.equalsIgnoreCase("N")){
                    guardar = false;
                }else{
                    Vista.mostrarMensaje(" <> ¡Debe seleccionar una opción válida! <>");
                    wait(sg);
                }//if

            }//while

        }//if

        if(guardar == true){

            Vista.mostrarMensaje("Guardando estadísticas...");
            wait(sg);

            Vista.mostrarMensaje("Guardando pokemones...");
            wait(sg);

            guardarPartida(pokemonesDisponibles, pokeEntrenadores);

        }//if

        Vista.mostrarMensaje(" <> » » » Saliendo de PokeChangua « « « <>");
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
            
                if (firstSeleccionarEntrenador == true){
                    Vista.mostrarMensaje(" <> ¡Has seleccionado « Seleccionar Entrenador » ! <>");
                    wait(sg);
                    firstSeleccionarEntrenador = false;
                }//if

                seleccionarEntrenador();

                while(option != 4){
                    Vista.menuOpcionesEntrenador();
                    option = Integer.parseInt(Vista.pedirString("Seleccione una opción"));
                    switchOpcionesEntrenador(option,entrenadorSeleccionado);
                }

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

                switchMenuPrincipal(2);

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

                    switchMenuPrincipal(3);

                }else{

                    pokemonBatalla1 = pokeEntrenadores.get(entrenadorBatalla1).prepararBatalla();
                
                    switchMenuPrincipal(3);

                }
                break;
            case 4:

                if(entrenadorBatalla2 == -1){
                        
                    Vista.mostrarMensaje(" -> ERROR: ¡No has seleccionado un entrenador para la batalla!");
                    wait(sg);

                    switchMenuPrincipal(3);

                }else{

                    pokemonBatalla2 = pokeEntrenadores.get(entrenadorBatalla2).prepararBatalla();

                    switchMenuPrincipal(3);

                }
                break;
            case 5:

                if (entrenadorBatalla1 == -1) {
                    
                    Vista.mostrarMensaje(" -> ERROR: ¡No has seleccionado un entrenador para la batalla!");
                    wait(sg);

                } else if(entrenadorBatalla2 == -1){

                    Vista.mostrarMensaje(" -> ERROR: ¡No has seleccionado un entrenador para la batalla!");
                    wait(sg);

                }else if (pokemonBatalla1.equals(null)) {

                    Vista.mostrarMensaje(" -> ERROR: ¡No has seleccionado un pokémon para la batalla!");
                    wait(sg);
                    
                } else if(pokemonBatalla2.equals(null)) {
                    
                    Vista.mostrarMensaje(" -> ERROR: ¡No has seleccionado un pokémon para la batalla!");
                    wait(sg);
                    
                } else {

                    Batalla battle = new Batalla();
                    String nombrePokemonGanador = battle.iniciarBatalla(pokemonBatalla1, pokemonBatalla2);

                    Vista.mostrarMensaje("   <> POKÉMON GANADOR:   » " + nombrePokemonGanador + " «   <>");
                    wait(sg);

                    switchMenuPrincipal(3);
                }//if 

                break;
            case 6:

                Vista.mostrarMensaje("Regresando al menú principal...");
                wait(sg);

                firstIniciarBatalla = true;
                runMenuPrincipal();

                break;
            case 0:

                salir();

                break;
            default:

                Vista.mostrarMensaje(" <> ¡Debe seleccionar una opción válida! <>");
                wait(sg);

                switchMenuPrincipal(3);
                
                break;
        }//cierra switch
    }//cierra switchIniciarBatalla

    //Complejidad temporal: O(1) Tiempo constante
    public void switchOpcionesEntrenador(int option, int entrenadorSeleccionado){
        switch (option){
            case 1:
                
                Vista.mostrarMensaje(" <> ¡Has seleccionado « Ver equipo de pokemones » ! <>");
                wait(sg);

                pokeEntrenadores.get(entrenadorSeleccionado).mostrarPokemones();
                wait(sg*2);

                break;
            case 2:

                Vista.mostrarMensaje(" <> ¡Has seleccionado « Agregar pokémon al equipo » ! <>");
                wait(sg);

                seleccionarPokemon();

                Pokemon pokemonAñadir =  null;
                switch (pokemonSeleccionado+1) {
                    case 1:
                        pokemonAñadir = newArticuno();
                        break;
                    case 2:
                        pokemonAñadir = newCaterpie();
                        break;
                    case 3:
                        pokemonAñadir = newCharmander();
                        break;
                    case 4:
                        pokemonAñadir = newDiglett();
                        break;
                    case 5:
                        pokemonAñadir = newDrowzee();
                        break;
                    case 6:
                        pokemonAñadir = newHitmonlee();
                        break;
                    case 7:
                        pokemonAñadir = newJolteon();
                        break;
                    case 8:
                        pokemonAñadir = newShellder();
                        break;
                    case 9:
                        pokemonAñadir = newSnorlax();
                        break;
                    case 10:
                        pokemonAñadir = newTentacool();
                        break;
                }//switch

                pokeEntrenadores.get(entrenadorSeleccionado).agregarPokemon(pokemonAñadir);

                Vista.mostrarMensaje("Agregando pokémon...");
                wait(sg);

                Vista.mostrarMensaje("   ☆ ☆ ☆ ¡Pokémon agregado al equipo con éxito! ☆ ☆ ☆   ");
                wait(sg);

                break;
            case 3:

                Vista.mostrarMensaje(" <> ¡Has seleccionado « Entrenar pokémon » ! <>");
                wait(sg);

                if (!pokeEntrenadores.get(entrenadorSeleccionado).getPokeLista().isEmpty()) {
                    
                    pokeEntrenadores.get(entrenadorSeleccionado).mostrarPokemones();
                    pokemonSeleccionado = Integer.parseInt(Vista.pedirString("Seleccione un pokémon"))-1;
                    Pokemon pokemonDeEntrenamiento = pokeEntrenadores.get(entrenadorSeleccionado).getPokeLista().get(pokemonSeleccionado);

                    pokeEntrenadores.get(entrenadorSeleccionado).entrenarPokemon(pokemonDeEntrenamiento);

                    Vista.mostrarMensaje("Entrenando pokémon...");
                    wait(sg);

                    Vista.mostrarMensaje("Creando lazos más fuertes con el entrenador...");
                    wait(sg);

                    Vista.mostrarMensaje("Perfeccionando movimientos...");
                    wait(sg);

                    Vista.mostrarMensaje("Aumentando autoestima...");
                    wait(sg);

                    Vista.mostrarMensaje("   ☆ ☆ ☆ ¡Pokémon entrenado con éxito! ☆ ☆ ☆   ");
                    wait(sg);

                } else {
                    Vista.mostrarMensaje(" <> ¡ Oh oh, no tienes pokemones ! <> ");
                    wait(sg);
                }//if

                break;
            case 4:

                Vista.mostrarMensaje("Regresando al menú anterior...");
                wait(sg);

                switchMenuPrincipal(1);

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
    }//switchOpcionesEntrenador



    // > > > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > H E R R A M I E N T A S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > > > > > < < < < < < < < < < < < < < < < < < < < < < < < //
    @SuppressWarnings("unused")
    //Complejidad temporal: O(1) Tiempo constante
    private void cargarPrimerosPokemon(){
        Pokemon Articuno = newArticuno();
        pokemonesDisponibles.add(Articuno);
        Pokemon Caterpie = newCaterpie();
        pokemonesDisponibles.add(Caterpie);
        Pokemon Charmander = newCharmander();
        pokemonesDisponibles.add(Charmander);
        Pokemon Diglett = newDiglett();
        pokemonesDisponibles.add(Diglett);
        Pokemon Drowzee = newDrowzee();
        pokemonesDisponibles.add(Drowzee);
        Pokemon Hitmonlee = newHitmonlee();
        pokemonesDisponibles.add(Hitmonlee);
        Pokemon Jolteon = newJolteon();
        pokemonesDisponibles.add(Jolteon);
        Pokemon Shellder = newShellder();
        pokemonesDisponibles.add(Shellder);
        Pokemon Snorlax = newSnorlax();
        pokemonesDisponibles.add(Snorlax);
        Pokemon Tentacool = newTentacool();
        pokemonesDisponibles.add(Tentacool);

        ArchivosConexion.guardar(pokemonesDisponibles, Archivo_Pokemones);
        ArchivosConexion.guardar(pokeEntrenadores, Archivo_Entrenadores);
    }//cargarPrimerosPokemon

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newArticuno(){
        return new Articuno("Articuno", 90, 85, EnumSet.of(TipoPokemon.HIELO, TipoPokemon.VOLADOR));
    }

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newCaterpie(){
        return new Caterpie("Caterpie", 45, 30, EnumSet.of(TipoPokemon.HIELO, TipoPokemon.BICHO));
    }

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newCharmander(){
        return new Charmander("Charmander", 39, 52, EnumSet.of(TipoPokemon.FUEGO));
    }

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newDiglett(){
        return new Diglett("Diglett", 10, 55, EnumSet.of(TipoPokemon.TIERRA));
    }

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newDrowzee(){
        return new Drowzee("Drowzee", 60, 48, EnumSet.of(TipoPokemon.PSIQUICO));
    }

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newHitmonlee(){
        return new Hitmonlee("Hitmonlee", 50, 120, EnumSet.of(TipoPokemon.LUCHA));
    }

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newJolteon(){
        return new Jolteon("Jolteon", 65, 65, EnumSet.of(TipoPokemon.ELECTRICO));
    }

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newShellder(){
        return new Shellder("Shellder", 30, 65, EnumSet.of(TipoPokemon.AGUA));
    }

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newSnorlax(){
        return new Snorlax("Snorlax", 160, 110, EnumSet.of(TipoPokemon.NORMAL));
    }

    //Complejidad temporal: O(1) Tiempo constante
    private Pokemon newTentacool(){
        return new Tentacool("Tentacool", 90, 85, EnumSet.of(TipoPokemon.AGUA, TipoPokemon.VENENO));
    }

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
