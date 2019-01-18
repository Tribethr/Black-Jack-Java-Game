/**
 * La clase mazo contiene la totalidad de las cartas.
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Pérez) 
 * @version (1.0 | 08/10/18)
 */
package blackjack;

import java.util.ArrayList;
import java.util.Random;

import library.Carta;

public class Mazo {
  ArrayList<Carta> cartas = new ArrayList<Carta>();
  
  public Mazo(ArrayList<Carta> cartas) {
    this.cartas = cartas;
  }
  
  public ArrayList<Carta> getCartas(){
    return cartas;
  }
  
  
  /**
   * Desordena la lista de cartas dandole aleatoriedad.
   * 
   * @param  N/A
   * @return N/A
   */
  public void barajar() {
    Random rng = new Random(System.nanoTime());
    for(int i = 0; i < cartas.size(); i++) {
      Carta carta = cartas.get(rng.nextInt(cartas.size()));
      cartas.remove(carta);
      cartas.add(carta);
    }
  }
  
  /**
   * Toma la primera carta de la lista y la elimina.
   * 
   * @param  N/A
   * @return (Carta) el objeto carta que estaba de primero.
   */
  public Carta repartir() {
    Carta carta = cartas.get(0);
    cartas.remove(carta);
    return carta;
  }
  
}
