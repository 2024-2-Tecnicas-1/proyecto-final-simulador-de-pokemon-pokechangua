package simulador.model.entrenador;

import java.io.Serializable;
import java.util.LinkedList;

import simulador.controller.Controlador;
import simulador.model.pokemon.Pokemon;
import simulador.model.pokemon.TipoPokemon;
import simulador.view.Vista;

public class Entrenador implements Serializable{
    private String nombre;
    private LinkedList<Pokemon> pokeLista;
    
    Controlador ctrl = new Controlador();

    public Entrenador(String nombre, LinkedList<Pokemon> pokeLista) {
        this.nombre = nombre;
        this.pokeLista = pokeLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Pokemon> getPokeLista() {
        return pokeLista;
    }

    //Complejidad temporal: O(1) Tiempo constante
    public void agregarPokemon(Pokemon pokemon){
        pokeLista.add(pokemon);
    }//agregarPokemon

    //Complejidad temporal: O(1) Tiempo constante
    public void entrenarPokemon(Pokemon pokemon){

        pokemon.entrenar();

    }//entrenarPokemon

    //Complejidad temporal: O(n) Complejidad lineal
    public void mostrarPokemones(){
        
        if (pokeLista.isEmpty()) {

            Vista.mostrarMensaje(" <> ¡ Oh oh, no tienes pokemones ! <> ");
            ctrl.wait(ctrl.sg);

        } else {

            Vista.mostrarMensaje("Cargando lista de pokemones...");
            ctrl.wait(ctrl.sg);

            Vista.espacioVisual();
            Vista.mostrarLinea(" <> = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = <>");
            for (int i = 0; i < pokeLista.size(); i++) {

                String pokeNombre = pokeLista.get(i).getNombre();
                double pokeSalud = pokeLista.get(i).getSalud();
                double pokeAtaque = pokeLista.get(i).getPuntosDeAtaque();
                TipoPokemon pokeTipo = pokeLista.get(i).getTipo();

                Vista.mostrarLinea("    ["+(i+1)+"]  " + pokeNombre + "     Salud -> " + pokeSalud + "     Ataque -> " + pokeAtaque + "     Tipo -> " + pokeTipo);
                
            }// for
            Vista.mostrarLinea(" <> = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = <>\n\n");
            //ctrl.wait(ctrl.sg);

        }//if

    }//mostrarPokemones

    //Complejidad temporal: O(log n) Tiempo logarítmico
    public Pokemon prepararBatalla(){
        
        mostrarPokemones();

        int pokemonSeleccionado = -1;

        int seleccion = Integer.parseInt(Vista.pedirString("Elige el pokémon para la batalla"))-1;

        while (seleccion < 0 && seleccion >= pokeLista.size()){
            
            Vista.mostrarMensaje(" -> ERROR: ¡El dato ingresado es inválido!");
            ctrl.wait(ctrl.sg);
            
            mostrarPokemones();
            seleccion = Integer.parseInt(Vista.pedirString("Elige el pokémon para la batalla"))-1;
        
        }

        pokemonSeleccionado = seleccion;

        Pokemon pokemonBatalla = pokeLista.get(pokemonSeleccionado);
        
        return pokemonBatalla;
    }//prepararBatalla

}//class
