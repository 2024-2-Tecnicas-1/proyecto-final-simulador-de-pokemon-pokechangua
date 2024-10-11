package simulador.view;

import java.util.Scanner;

public class Vista {
    private Scanner sc = new Scanner(System.in);
    
    public static String pedirString(String info){
    	Vista v = new Vista();
        System.out.println(" >> " + info + ": ");
        String dato = v.sc.nextLine();
        return dato;
    }//pedirString

    // > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > M E N Ú S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < //

    public static void menuPrincipal(){
        System.out.println(
                        "       ,___          .-;'   "+  "|  | » » »     MENÚ PRINCIPAL     « « « |  |   ';-.          ___,\n" + //
                        "       `\"-.`\\_...._/`.`     "+"|  |                                    |  |    `.`\\_...._/`.-\"`\n" + //
                        "    ,      \\        /       "+ "|  |                                    |  |      \\        /      ,\n" + //
                        " .-' ',    / ()   ()\\       "+ "|  |  [1] | Gestionar entrenadores.     |  |      /()   () \\    .' `-.\n" + //
                        "`'._   \\  /()   .   (|      "+ "|  |                                    |  |     |)   .   ()\\  /   _.'`\n" + //
                        "    > .' ;,    -'-  /       "+  "|  |  [2] | Gestionar pokemones.        |  |      \\  -'-    ,; '. <\n" + //
                        "   / <   |;,     __.;       "+  "|  |                                    |  |      ;.__     ,;|   > \\\n" + //
                        "   '-.'-.|  , \\    , \\      "+"|  |  [3] | Iniciar batalla.            |  |     / ,    / ,  |.-'.-'\n" + //
                        "      `>.|;, \\_)    \\_)     "+"|  |                                    |  |    (_/    (_/ ,;|.<'\n" + //
                        "       `-;     ,    /       "+  "|  |  [0] | Salir.                      |  |       \\    ,     ;-'\n" + //
                        "          \\    /   <        "+ "|  |                                    |  |        >   \\    /\n" + //
                        "           '. <`'-._)       "+  "|  |                                    |  |       (_,-'`> .'\n" + //
                        "            '._)            "+  "|  | » » »      PokeChangua       « « « |  |           (_,'\n" + //
                        "");
    }//menuPrincipal

    public static void menuGestionarEntrenadores(){//1
        System.out.println(
                        "       ,___          .-;'   "+  "|  | » » » GESTIONAR ENTRENADORES « « « |  |   ';-.          ___,\n" + //
                        "       `\"-.`\\_...._/`.`     "+"|  |                                    |  |    `.`\\_...._/`.-\"`\n" + //
                        "    ,      \\        /       "+ "|  |  [1] | Registrar nuevo entrenador. |  |      \\        /      ,\n" + //
                        " .-' ',    / ()   ()\\       "+ "|  |                                    |  |      /()   () \\    .' `-.\n" + //
                        "`'._   \\  /()   .   (|      "+ "|  |  [2] | Ver lista de entrenadores.  |  |     |)   .   ()\\  /   _.'`\n" + //
                        "    > .' ;,    -'-  /       "+  "|  |                                    |  |      \\  -'-    ,; '. <\n" + //
                        "   / <   |;,     __.;       "+  "|  |  [3] | Seleccionar un entrenador.  |  |      ;.__     ,;|   > \\\n" + //
                        "   '-.'-.|  , \\    , \\      "+"|  |                                    |  |     / ,    / ,  |.-'.-'\n" + //
                        "      `>.|;, \\_)    \\_)     "+"|  |  [4] | Volver al menú principal.   |  |    (_/    (_/ ,;|.<'\n" + //
                        "       `-;     ,    /       "+  "|  |                                    |  |       \\    ,     ;-'\n" + //
                        "          \\    /   <        "+ "|  |  [0] | Salir.                      |  |        >   \\    /\n" + //
                        "           '. <`'-._)       "+  "|  |                                    |  |       (_,-'`> .'\n" + //
                        "            '._)            "+  "|  | » » »      PokeChangua       « « « |  |           (_,'\n" + //
                        "");
    }//menuGestionarEntrenadores

    public static void menuGestionarPokemones(){//2
        System.out.println(
                        "       ,___          .-;'   "+  "|  | » » »  GESTIONAR  POKEMONES  « « « |  |   ';-.          ___,\n" + //
                        "       `\"-.`\\_...._/`.`     "+"|  |                                    |  |    `.`\\_...._/`.-\"`\n" + //
                        "    ,      \\        /       "+ "|  |                                    |  |      \\        /      ,\n" + //
                        " .-' ',    / ()   ()\\       "+ "|  |  [1] | Ver pokemones registrados.  |  |      /()   () \\    .' `-.\n" + //
                        "`'._   \\  /()   .   (|      "+ "|  |                                    |  |     |)   .   ()\\  /   _.'`\n" + //
                        "    > .' ;,    -'-  /       "+  "|  |  [2] | Registrar nuevo pokémon.    |  |      \\  -'-    ,; '. <\n" + //
                        "   / <   |;,     __.;       "+  "|  |                                    |  |      ;.__     ,;|   > \\\n" + //
                        "   '-.'-.|  , \\    , \\      "+"|  |  [3] | Volver al menú principal.   |  |     / ,    / ,  |.-'.-'\n" + //
                        "      `>.|;, \\_)    \\_)     "+"|  |                                    |  |    (_/    (_/ ,;|.<'\n" + //
                        "       `-;     ,    /       "+  "|  |  [0] | Salir.                      |  |       \\    ,     ;-'\n" + //
                        "          \\    /   <        "+ "|  |                                    |  |        >   \\    /\n" + //
                        "           '. <`'-._)       "+  "|  |                                    |  |       (_,-'`> .'\n" + //
                        "            '._)            "+  "|  | » » »      PokeChangua       « « « |  |           (_,'\n" + //
                        "");
    }//menuGestionarPokemones

    public static void menuIniciarBatalla(){//3
        System.out.println(
                        "       ,___          .-;'   "+  "|  | » » »    INICIAR  BATALLA    « « « |  |   ';-.          ___,\n" + //
                        "       `\"-.`\\_...._/`.`     "+"|  |                                    |  |    `.`\\_...._/`.-\"`\n" + //
                        "    ,      \\        /       "+ "|  |  [1] | Elegir entrenador #1.       |  |      \\        /      ,\n" + //
                        " .-' ',    / ()   ()\\       "+ "|  |  [2] | Elegir entrenador #2.       |  |      /()   () \\    .' `-.\n" + //
                        "`'._   \\  /()   .   (|      "+ "|  |  [3] | Seleccionar pokémon         |  |     |)   .   ()\\  /   _.'`\n" + //
                        "    > .' ;,    -'-  /       "+  "|  |           del entrenador#1.        |  |      \\  -'-    ,; '. <\n" + //
                        "   / <   |;,     __.;       "+  "|  |  [4] | Seleccionar pokémon         |  |      ;.__     ,;|   > \\\n" + //
                        "   '-.'-.|  , \\    , \\      "+"|  |           del entrenador#2.        |  |     / ,    / ,  |.-'.-'\n" + //
                        "      `>.|;, \\_)    \\_)     "+"|  |  [5] | Comenzar batalla.           |  |    (_/    (_/ ,;|.<'\n" + //
                        "       `-;     ,    /       "+  "|  |  [6] | Volver al menú principal.   |  |       \\    ,     ;-'\n" + //
                        "          \\    /   <        "+ "|  |  [0] | Salir.                      |  |        >   \\    /\n" + //
                        "           '. <`'-._)       "+  "|  |                                    |  |       (_,-'`> .'\n" + //
                        "            '._)            "+  "|  | » » »      PokeChangua       « « « |  |           (_,'\n" + //
                        "");
    }//menuIniciarBatalla

    public static void pikaPika(){
        System.out.print(
                        "🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨\n" + //
                        "🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨\n" + //
                        "🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨\n" + //
                        "🟨⬛⬜🟨🟨🟨🟨🟨⬛⬜🟨  ██████  ██ ██   ██  █████      ██████  ██ ██   ██  █████  \n" + //
                        "🟨⬛⬛🟨🟨🟨🟨🟨⬛⬛🟨  ██   ██ ██ ██  ██  ██   ██     ██   ██ ██ ██  ██  ██   ██ \n" + //
                        "🟨⬛⬛🟨🟨⬛🟨🟨⬛⬛🟨  ██████  ██ █████   ███████     ██████  ██ █████   ███████\n" + //
                        "🟥🟨🟨🟨🟨🟨🟨🟨🟨🟨🟥  ██      ██ ██  ██  ██   ██     ██      ██ ██  ██  ██   ██ \n" + //
                        "🟥🟥🟨🟨🟨⬛🟨🟨🟨🟥🟥  ██      ██ ██   ██ ██   ██     ██      ██ ██   ██ ██   ██ \n" + //
                        "🟥🟥🟨🟨⬛🟨⬛🟨🟨🟥🟥\n" + //
                        "🟥🟨🟨🟨🟨🟨🟨🟨🟨🟨🟥\n" + //
                        "🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨\n" + //
                        " \n \n \n");
    }//pikaPika

    public static void interfazPokeChangua(){
        
    }//interfazPokeChangua

    // > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > O T R O S < < < < < < < < < < < < < < < < < < //
    // > > > > > > > > > > > > > > > > > > > > - < < < < < < < < < < < < < < < < < < < < //

    public static void mostrarLinea(String mensaje){
        System.out.println(mensaje);
    }

    public static void espacioVisual(){
        System.out.println(" \n \n \n \n \n \n \n");
    }

    public static void mostrarMensaje(String mensaje){
        espacioVisual();
        System.out.println(mensaje);
        espacioVisual();
    }

}
