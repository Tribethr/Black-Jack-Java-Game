/**
 * La clase carta contiene informacin necesaria sobre una carta.
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Prez) 
 * @version (1.0 | 08/10/18)
 */

package blackjack;


public class Carta implements java.io.Serializable{

  private static final long serialVersionUID = 1L;
  private int valor;
  private int indice;
  private String nombre;
    

  public Carta(int pValor, String pNombre){
    valor = pValor;
    nombre = pNombre;
  }
    
  public int getValor(){
    return valor;
  }

  public String getNombre(){
    return nombre;
  }  
  public int getIndice() {
    return indice;
  }

  public void setIndice(int indice) {
    this.indice = indice;
  }

    @Override
  public String toString() {
    return "Nombre: " + nombre + " Valor: "+valor;
  }
}
