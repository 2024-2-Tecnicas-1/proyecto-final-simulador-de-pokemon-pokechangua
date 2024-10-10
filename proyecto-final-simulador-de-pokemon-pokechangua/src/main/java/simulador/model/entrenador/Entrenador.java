package simulador.model.entrenador;

import java.io.Serializable;
import java.util.LinkedList;

import simulador.model.pokemon.Pokemon;

public class Entrenador implements Serializable{
    private String nombre;
    private LinkedList<Pokemon> pokeLista;
    
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
    }

}
