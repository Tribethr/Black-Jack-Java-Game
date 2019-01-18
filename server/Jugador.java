/**
 * La clase Jugador contiene los metodos necesarios para conectarse con el servidor y poder iniciar una partida.
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Prez) 
 * @version (1.0 | 08/10/18)
 */
package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import blackjack.Carta;
import ui.UiJuego;


public class Jugador {

    private ObjectInputStream in;
    private PrintWriter out;
    private Socket socket;
    private Carta carta;
    private boolean jugadorDerecho;
    private boolean sePuedeJugar;
    private UiJuego ui;
    private int cantidadPasos;
    private String jugadorActual;
    private String miNombre;
    public Jugador(String nombreJugador, String ip) {
        try {
          socket = new Socket(ip, 8086);
          in = new ObjectInputStream(socket.getInputStream());
          out = new PrintWriter(socket.getOutputStream(), true);
          out.println(nombreJugador);
          if(((String)in.readObject()).equals("Aceptado")){
            jugadorActual = "";
            miNombre = nombreJugador;
            ui = new UiJuego(this,nombreJugador);
            run();
          }else {
            System.out.println("lo sentimos el nombre ya esta siendo usado");
          }
        } catch (Exception e) {
          System.out.println("Por el momento no puedes acceder a esta partida");
        }
    }
   /**
    * Indica al servidor que no jugará en este momento
    */
    public void plantarse() {
      System.out.print(sePuedeJugar);
      if(miNombre.equals(jugadorActual) && sePuedeJugar) {  
        out.println("0");
        cantidadPasos++;
      }
    }
    /**
     * Indica al servidor que quiere una carta
     */
    public void pedir() {
      if(miNombre.equals(jugadorActual) && sePuedeJugar) {
        out.println("1");
      }
    }
    
    private void run() throws ClassNotFoundException, IOException {
       while(true){
         Object mensaje = in.readObject();
         if(mensaje.getClass() == Carta.class) {
           carta = (Carta)mensaje;
           if(miNombre.equals(jugadorActual)) {
             ui.agregarCartaJugador(carta.getIndice());
           }else if(jugadorDerecho) {
             jugadorDerecho = false;
             ui.agregarCartaJugadorDerecho(carta.getIndice());
           }else {
             jugadorDerecho = true;
             ui.agregarCartaJugadorIzquierdo(carta.getIndice());
           }
           ui.repaint();
         }else if(mensaje.getClass() == String.class) {
           String texto = (String)mensaje;
           if(texto.startsWith("Turno")) {
             if(texto.substring(5).equals(miNombre)) {
               ui.setJugadorActual("Tu turno");
             }else if(sePuedeJugar){
               ui.setJugadorActual("Turno de: "+texto.substring(5));
             }
             jugadorActual = texto.substring(5);
           }else if(texto.equals("ActivarPartida")) {
             sePuedeJugar = true;
           }else if(texto.equals("Ganaste")) {
             ui.setJugadorActual("Ganaste");
           }else if(texto.equals("Perdiste")) {
             ui.setJugadorActual("Perdiste");
           }else if(texto.equals("Desactivar")) {
             sePuedeJugar = false;
           }else if(texto.equals("Desconexion")) {
             ui.setJugadorActual("Alguien se desconectó, se temina la partida");
           }
         }
         
       }
    }

   
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
      String ip = JOptionPane.showInputDialog(null,"Ingrese la ip del servidor:","¡Bienvenido a blackjack!",JOptionPane.QUESTION_MESSAGE);
      String nombreJugador = JOptionPane.showInputDialog(null,"Ingrese un nombre:","¡Bienvenido a blackjack!",JOptionPane.QUESTION_MESSAGE);
      if(nombreJugador != null) {
        Jugador client = new Jugador(nombreJugador,ip);
      }else {
        System.out.println("No debe dejar campos vacios");
      }
    }
}