package simulador.controller.batalla;

import simulador.controller.Controlador;
import simulador.model.pokemon.Estado;
import simulador.model.pokemon.Pokemon;
import simulador.view.Vista;


public class Batalla {

    Controlador ctrl = new Controlador();

    //Complejidad temporal: O(log n) Tiempo logarítmico
    public String iniciarBatalla(Pokemon pokemon1, Pokemon pokemon2){

        String nombrePokemonGanador = "Ninguno ganó";
        boolean firstDuranteLaBatalla = true;
        
        int key = -1;

        while (pokemon1.getSalud() > 0 && pokemon2.getSalud() > 0 && key != 3) {

            if(firstDuranteLaBatalla==true){

                Vista.mostrarMensaje("   ⚔︎ ⚔︎ ⚔︎ ¡BATALLA POKÉMON! ⚔︎ ⚔︎ ⚔︎");
                ctrl.wait(ctrl.sg);

                Vista.mostrarMensaje("   ⚔︎ ⚔︎ ⚔︎   [ " + pokemon1.getNombre() + " ] VS [ " + pokemon2.getNombre() + " ]   ⚔︎ ⚔︎ ⚔︎   ");
                ctrl.wait(ctrl.sg);

                firstDuranteLaBatalla = false;

            }//if

            Vista.espacioVisual();
            Vista.mostrarLinea("   ⚔︎ ⚔︎ ⚔︎   [ " + pokemon1.getNombre() + " ] VS [ " + pokemon2.getNombre() + " ]   ⚔︎ ⚔︎ ⚔︎   \n");
            Vista.mostrarLinea("      [1] | " + pokemon1.getNombre() + " -> ⚔︎ Atacar a " + pokemon2.getNombre() + " ♥︎♡♥︎ [ " + pokemon2.getSalud() + " ] ♡♥︎♡");
            Vista.mostrarLinea("      [1] | " + pokemon2.getNombre() + " -> ⚔︎ Atacar a " + pokemon1.getNombre() + " ♥︎♡♥︎ [ " + pokemon1.getSalud() + " ] ♡♥︎♡");
            Vista.mostrarLinea("      [3] | Finalizar batalla.\n");
            
            key = Integer.parseInt(Vista.pedirString("Seleccione una opción"));

            switch (key){
                case 1:

                    pokemon2.recibirDaño(pokemon1.atacar(pokemon2));

                    Vista.mostrarMensaje("   ⚔︎ ⚔︎ ⚔︎ [ " + pokemon1.getNombre() + " ] atacó a [ " + pokemon2.getNombre() + " ] ⚔︎ ⚔︎ ⚔︎   ");
                    ctrl.wait(ctrl.sg);

                    break;
                case 2:

                    pokemon1.recibirDaño(pokemon2.atacar(pokemon1));

                    Vista.mostrarMensaje("   ⚔︎ ⚔︎ ⚔︎ [ " + pokemon2.getNombre() + " ] atacó a [ " + pokemon1.getNombre() + " ] ⚔︎ ⚔︎ ⚔︎   ");
                    ctrl.wait(ctrl.sg);

                    break;
                case 3:
                    
                    Vista.mostrarMensaje("Finalizando batalla...");
                    ctrl.wait(ctrl.sg);

                    Vista.espacioVisual();
                    Vista.mostrarLinea("   <> Resumen de batalla -> [ " + pokemon1.getNombre() + " ] VS [ " + pokemon2.getNombre() + " ] <>\n");
                    Vista.mostrarLinea("      » | "+ pokemon1.getNombre() + "      Salud ->  " + pokemon1.getSalud() + "      Estado ->  " + pokemon1.getEstado());
                    Vista.mostrarLinea("      » | "+ pokemon2.getNombre() + "      Salud ->  " + pokemon2.getSalud() + "      Estado ->  " + pokemon2.getEstado() + "\n\n");
                    ctrl.wait(ctrl.sg*3);

                    break;
                default:
                    
                    Vista.mostrarMensaje(" -> ERROR: ¡El dato ingresado es inválido!");
                    ctrl.wait(ctrl.sg);
                    
                    break;
            }//switch

        }//while

        if(pokemon2.getEstado().equals(Estado.DEBILITADO)){
            nombrePokemonGanador = pokemon1.getNombre();
        }else if(pokemon1.getEstado().equals(Estado.DEBILITADO)){
            nombrePokemonGanador = pokemon2.getNombre();
        }

        firstDuranteLaBatalla = true;
        return nombrePokemonGanador;

    }//iniciarBatalla
}//class
