package simulador.model.pokemones;

import java.util.EnumSet;

import simulador.model.pokemon.Pokemon;
import simulador.model.pokemon.TipoPokemon;

public class Caterpie extends Pokemon{

    public Caterpie(String nombre, double salud, double puntosDeAtaque, EnumSet<TipoPokemon> tipo) {
        super(nombre, salud, puntosDeAtaque, tipo);
    }
}