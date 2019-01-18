package blackjack;
import java.util.ArrayList;

public class Partida implements java.io.Serializable{

  private static final long serialVersionUID = 1L;
  private ArrayList<Carta> cartasJugador1 = new ArrayList<Carta>();
  private ArrayList<Carta> cartasJugador2 = new ArrayList<Carta>();
  private ArrayList<Carta> cartasJugador3 = new ArrayList<Carta>();
  private ArrayList<Carta> cartasCrupier = new ArrayList<Carta>();
  private String jugadorActual;
  
  public Partida(String jugadorActual) {
    this.cartasJugador1 = new ArrayList<Carta>();
    this.cartasJugador2 = new ArrayList<Carta>();
    this.cartasJugador3 = new ArrayList<Carta>();
    this.jugadorActual = jugadorActual;
  }
  
  public Partida(ArrayList<Carta> cartasJugador1, ArrayList<Carta> cartasJugador2, 
      ArrayList<Carta> cartasJugador3, String jugadorActual) {
    this.cartasJugador1 = cartasJugador1;
    this.cartasJugador2 = cartasJugador2;
    this.cartasJugador3 = cartasJugador3;
    this.jugadorActual = jugadorActual;
  }

  public ArrayList<Carta> getCartasJugador1() {
    return cartasJugador1;
  }

  public void setCartasJugador1(ArrayList<Carta> cartasJugador1) {
    this.cartasJugador1 = cartasJugador1;
  }

  public ArrayList<Carta> getCartasJugador2() {
    return cartasJugador2;
  }

  public void setCartasJugador2(ArrayList<Carta> cartasJugador2) {
    this.cartasJugador2 = cartasJugador2;
  }

  public ArrayList<Carta> getCartasJugador3() {
    return cartasJugador3;
  }

  public void setCartasJugador3(ArrayList<Carta> cartasJugador3) {
    this.cartasJugador3 = cartasJugador3;
  }

  public ArrayList<Carta> getCartasCrupier() {
    return cartasCrupier;
  }

  public void setCartasCrupier(ArrayList<Carta> cartasCrupier) {
    this.cartasCrupier = cartasCrupier;
  }

  public String getJugadorActual() {
    return jugadorActual;
  }

  public void setJugadorActual(String jugadorActual) {
    this.jugadorActual = jugadorActual;
  }
  
}
