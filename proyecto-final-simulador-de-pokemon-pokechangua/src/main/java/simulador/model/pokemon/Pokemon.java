package simulador.model.pokemon;

import java.io.Serializable;
import java.util.EnumSet;

public class Pokemon implements Serializable{
    protected String nombre;
    protected double salud;
    protected double puntosDeAtaque;
    protected EnumSet<TipoPokemon> tipo;
    protected Estado estado;

    public Pokemon(String nombre, double salud, double puntosDeAtaque, EnumSet<TipoPokemon> tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.puntosDeAtaque = puntosDeAtaque;
        this.tipo = tipo;
        this.estado = Estado.NORMAL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalud() {
        return salud;
    }

    public void setSalud(double salud) {
        this.salud = salud;
    }

    public double getPuntosDeAtaque() {
        return puntosDeAtaque;
    }

    public void setPuntosDeAtaque(double puntosDeAtaque) {
        this.puntosDeAtaque = puntosDeAtaque;
    }

    public EnumSet<TipoPokemon> getTipo() {
        return tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    //Complejidad temporal: O(1) Tiempo constante
    public double atacar(Pokemon oponente) {
        double multiplicador = 1.0;

        for (TipoPokemon tipoAtacante : this.tipo) {
            multiplicador *= TipoPokemon.obtenerMultiplicadorDeDaño(tipoAtacante, oponente.getTipo());
        }

        double daño = this.puntosDeAtaque * multiplicador;
        
        return daño;
    }//atacar

    //Complejidad temporal: O(1) Tiempo constante
    public void recibirDaño(double daño) {
        this.salud -= daño;
        if (this.salud <= 0) {
            this.salud = 0;
            this.estado = Estado.DEBILITADO;
        }
    }//recibirDaño

    //Complejidad temporal: O(1) Tiempo constante
    public void entrenar() {
        this.puntosDeAtaque += 20;
        this.salud += 5;
    }//entrenar
    
}//class
