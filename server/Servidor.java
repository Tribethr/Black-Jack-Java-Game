/**
 * Implementa el servidor de la aplicacin.
 * 
 * @author Abrahawm Meza, Tribeth Rivas
 * @version 8/10/18 
 */
package servidor;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashSet;


public class Servidor {
   private static final int PUERTO = 8086;
   public static HashSet<ObjectOutputStream> jugadores = new HashSet<ObjectOutputStream>();
   public static ArrayList<String> nombres = new ArrayList<String>();
   public static int indiceCarta = 0;
   public static int turno = 0;
   
   
  public static void main(String[] args) throws Exception {
    System.out.println("El servidor esta corriendo.");
    ServerSocket listener = new ServerSocket(PUERTO);
    int indice = 0;
    try {
      while (indice<3) {
        new Retenedor(listener.accept()).start();
        indice++;
      }
    } finally {
      listener.close();
    }
  }
}
