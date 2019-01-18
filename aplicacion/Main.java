package aplicacion;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import blackjack.Mazo;
import blackjack.Carta;
import servidor.Jugador;
import ui.UiIngresar;
import ui.UiJuego;
import util.JParser;

import static util.Randoms.randInt;

public class Main {
  
  public static void print(Object toPrint) {
    System.out.println(toPrint);
  }
  
  public static void main(String [] args) throws FileNotFoundException, IOException, ParseException {
    JParser parser = new JParser();
    //Mazo mazo = new Mazo( parser.parseCartas("cartas.txt"));
    Jugador jugador = null;
    UiJuego ui = new UiJuego();
    /*for (int i = 0; i<5 ; i++){
      ui.getCartasJugador().add(mazo.getCartas().get(randInt(0, mazo.getCartas().size()-1)));
      ui.getCartasJugadorDerecho().add(mazo.getCartas().get(randInt(0, mazo.getCartas().size()-1)));
      ui.getCartasJugadorIzquierdo().add(mazo.getCartas().get(randInt(0, mazo.getCartas().size()-1)));
    }*/
    UiIngresar ingresar = new UiIngresar(jugador);
  }
}
    