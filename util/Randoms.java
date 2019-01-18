package util;

import java.util.Random;

/**
 * La clase Randoms se encarga de ofrecer facilidades de aleatoriedad al desarrolador
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Pérez)
 * @version (1.0 01/08/2018)
 */

public class Randoms {

    /**
     * Retorna un entero entre el rango establecido
     * 
     * @param  int inicio y fin del rango
     * @return Retorna un entero entre el rango establecido
     */
    public static int randInt(int min, int max){
        return new Random().nextInt(max-min+1)+min;
    }
    
    /**
     * Selecciona un elemento aleatorio de la lista
     * 
     * @param  lista a evaluar
     * @return un elemento aleatorio de la lista
     */
    public static Object choice(Object[] lista) {
      return lista[randInt(0,lista.length-1)];
    }
    public static Object choice(int[] lista) {
      return lista[randInt(0,lista.length-1)];
    }
    
}
