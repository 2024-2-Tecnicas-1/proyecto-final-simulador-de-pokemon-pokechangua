package simulador.view;

import java.util.Scanner;

public class Vista {
    private Scanner sc = new Scanner(System.in);
    
    public static String pedirString(String info){
    	Vista v = new Vista();
        System.out.println(" >> " + info + ": ");
        String dato = v.sc.nextLine();
        return dato;
    }

    // > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > M E N Ú S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < //

    public static void menuPrincipal(){
        System.out.println(
                        "       ,___          .-;'   |  | » » »     MENÚ PRINCIPAL     « « « |  |   ';-.          ___,\n" + //
                        "       `\"-.`\\_...._/`.`     |  |                                    |  |    `.`\\_...._/`.-\"`\n" + //
                        "    ,      \\        /       |  |                                    |  |      \\        /      ,\n" + //
                        " .-' ',    / ()   ()\\       |  |  [1] | Gestionar entrenadores.     |  |      /()   () \\    .' `-.\n" + //
                        "`'._   \\  /()   .   (|      |  |                                    |  |     |)   .   ()\\  /   _.'`\n" + //
                        "    > .' ;,    -'-  /       |  |  [2] | Gestionar pokemones.        |  |      \\  -'-    ,; '. <\n" + //
                        "   / <   |;,     __.;       |  |                                    |  |      ;.__     ,;|   > \\\n" + //
                        "   '-.'-.|  , \\    , \\      |  |  [3] | Iniciar batalla.            |  |     / ,    / ,  |.-'.-'\n" + //
                        "      `>.|;, \\_)    \\_)     |  |                                    |  |    (_/    (_/ ,;|.<'\n" + //
                        "       `-;     ,    /       |  |  [0] | Salir.                      |  |       \\    ,     ;-'\n" + //
                        "          \\    /   <        |  |                                    |  |        >   \\    /\n" + //
                        "           '. <`'-._)       |  |                                    |  |       (_,-'`> .'\n" + //
                        "            '._)            |  | » » »      PokeChangua       « « « |  |           (_,'\n" + //
                        "");
    }

    public static void menuGestionarEntrenadores(){
        System.out.println(
                        "       ,___          .-;'   |  | » » » GESTIONAR ENTRENADORES « « « |  |   ';-.          ___,\n" + //
                        "       `\"-.`\\_...._/`.`     |  |                                    |  |    `.`\\_...._/`.-\"`\n" + //
                        "    ,      \\        /       |  |  [1] | Registrar nuevo entrenador. |  |      \\        /      ,\n" + //
                        " .-' ',    / ()   ()\\       |  |                                    |  |      /()   () \\    .' `-.\n" + //
                        "`'._   \\  /()   .   (|      |  |  [2] | Ver lista de entrenadores.  |  |     |)   .   ()\\  /   _.'`\n" + //
                        "    > .' ;,    -'-  /       |  |                                    |  |      \\  -'-    ,; '. <\n" + //
                        "   / <   |;,     __.;       |  |  [3] | Seleccionar un entrenador.  |  |      ;.__     ,;|   > \\\n" + //
                        "   '-.'-.|  , \\    , \\      |  |                                    |  |     / ,    / ,  |.-'.-'\n" + //
                        "      `>.|;, \\_)    \\_)     |  |  [4] | Volver al menú principal.   |  |    (_/    (_/ ,;|.<'\n" + //
                        "       `-;     ,    /       |  |                                    |  |       \\    ,     ;-'\n" + //
                        "          \\    /   <        |  |  [0] | Salir.                      |  |        >   \\    /\n" + //
                        "           '. <`'-._)       |  |                                    |  |       (_,-'`> .'\n" + //
                        "            '._)            |  | » » »      PokeChangua       « « « |  |           (_,'\n" + //
                        "");
    }

    public static void menuGestionarPokemones(){
        System.out.println(
                        "       ,___          .-;'   |  | » » » GESTIONAR ENTRENADORES « « « |  |   ';-.          ___,\n" + //
                        "       `\"-.`\\_...._/`.`     |  |                                    |  |    `.`\\_...._/`.-\"`\n" + //
                        "    ,      \\        /       |  |  [1] | Registrar nuevo entrenador. |  |      \\        /      ,\n" + //
                        " .-' ',    / ()   ()\\       |  |                                    |  |      /()   () \\    .' `-.\n" + //
                        "`'._   \\  /()   .   (|      |  |  [2] | Ver lista de entrenadores.  |  |     |)   .   ()\\  /   _.'`\n" + //
                        "    > .' ;,    -'-  /       |  |                                    |  |      \\  -'-    ,; '. <\n" + //
                        "   / <   |;,     __.;       |  |  [3] | Seleccionar un entrenador.  |  |      ;.__     ,;|   > \\\n" + //
                        "   '-.'-.|  , \\    , \\      |  |                                    |  |     / ,    / ,  |.-'.-'\n" + //
                        "      `>.|;, \\_)    \\_)     |  |  [4] | Volver al menú principal.   |  |    (_/    (_/ ,;|.<'\n" + //
                        "       `-;     ,    /       |  |                                    |  |       \\    ,     ;-'\n" + //
                        "          \\    /   <        |  |  [0] | Salir.                      |  |        >   \\    /\n" + //
                        "           '. <`'-._)       |  |                                    |  |       (_,-'`> .'\n" + //
                        "            '._)            |  | » » »      PokeChangua       « « « |  |           (_,'\n" + //
                        "");
    }

    public static void menuIniciarBatalla(){

    }

    // > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > O T R O S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < //

    public static void espacioVisual(){
        System.out.println(" \n \n \n \n \n \n \n");
    }

    public static void mostrarMensaje(String mensaje){
        espacioVisual();
        System.out.println(mensaje);
        espacioVisual();
    }
    
    public static void interfazPokeChangua(){
        
    }



}
