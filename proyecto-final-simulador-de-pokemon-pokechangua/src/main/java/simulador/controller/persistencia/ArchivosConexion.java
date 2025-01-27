package simulador.controller.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArchivosConexion {
    
    static String ruta = "PokeChangua/proyecto-final-simulador-de-pokemon-pokechangua/datos/";

    public static void guardar(Object objeto, String nombreArchivo){

        File file = new File (ruta + nombreArchivo);

        try (FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
           out.writeObject(objeto);
           System.out.println("El objeto ha sido guardado en: datos/" + nombreArchivo);
        } catch (IOException e) {
           e.printStackTrace();
        }//cierra try-catch

    }//cierra guardar

    public static Object leer(String nombreArchivo){

        Object objeto = null;

        File file = new File(ruta + nombreArchivo);
        
        if (file.exists()) {
            try (FileInputStream fileIn = new FileInputStream(file) ; ObjectInputStream in = new ObjectInputStream(fileIn)) {
                objeto = (Object) in.readObject();
                System.out.println("Objeto leído: " + objeto);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }//cierra try-catch
        } else {
            System.out.println("El archivo " + nombreArchivo + " no existe.");
        }//cierra if-else

        return objeto;

    }//cierra leer

}//cierra class