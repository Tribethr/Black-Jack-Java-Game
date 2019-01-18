/**
 * La clase carta contiene información necesaria sobre una carta.
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Pérez) 
 * @version (1.0 | 08/10/18)
 */

package library;

import java.awt.Image;

public class Carta{
    
  private int valor;
    private String nombre;
    private Image imagen;
    

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
    
    public void setImagen(Image pImagen) {
      imagen = pImagen;
    }
    public Image getImagen(){
        return imagen;
    }
    
    @Override
    public String toString() {
      return "Nombre: "+nombre+" Valor: "+valor;
    }
}
