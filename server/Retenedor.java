/**
 * La clase Retenedor se encarga de mantener su conexion con un cliente especifico y manejar los mensajes a ellos.
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Prez) 
 * @version (1.0 | 08/10/18)
 */
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import blackjack.Carta;

class Retenedor extends Thread {
  private String nombre;
  private Socket socket;
  private BufferedReader in;
  private ObjectOutputStream out;
  
  public Retenedor(Socket socket) {
      this.socket = socket;
      
  }
  /**
   * Analiza lo que el cliente le envió y basado en eso toma una desicion de qué debe hacer
   * @param input
   */
  private void analizarInput(String input) {
    try {
      if(input.equals("0")) {
        String mensaje = "plantado";
        out.writeObject(mensaje);
      }else if(input.equals("1")) {
        Carta actual = Servidor.mazo.getCartas().get(Servidor.indiceCarta);
        for(ObjectOutputStream jugador :Servidor.jugadores) {
          jugador.writeObject(actual);
        }
        if(actual.getValor() == 11 && Servidor.puntuaciones[Servidor.turno]>10) {
          Servidor.puntuaciones[Servidor.turno] ++;          
        }else {
          Servidor.puntuaciones[Servidor.turno] += actual.getValor();
        }
        if(Servidor.puntuaciones[Servidor.turno] == 21) {
          for(int i = 0; i<3; i++) {
            if(i == Servidor.turno) {
              Servidor.jugadores.get(i).writeObject(new String("Ganaste"));
            }else {
              Servidor.jugadores.get(i).writeObject(new String("Perdiste"));
            }
            Servidor.jugadores.get(i).writeObject(new String("Desactivar"));
          }
          socket.close();
          Servidor.sePuedeJugar = false;
        }
        if(Servidor.puntuaciones[Servidor.turno] > 21) {
          Servidor.jugadores.get(Servidor.turno).writeObject(new String("Perdiste"));
          Servidor.jugadores.get(Servidor.turno).writeObject(new String("Desactivar"));
        }
        Servidor.indiceCarta++;
      }
    }catch(Exception e) {
      
    }
  }
  /**
   * Indica a los jugadores de quien es el turno actual
   */
  public void cambiarTurno() {
    if(Servidor.sePuedeJugar) { 
      validarCambioTurno();
      while(Servidor.puntuaciones[Servidor.turno] > 21) {
        validarCambioTurno();
      }
      for(ObjectOutputStream jugador : Servidor.jugadores) {
        try {
          jugador.writeObject("Turno"+Servidor.nombres.get(Servidor.turno));
        } catch (IOException e) {
          System.out.println("No pude cambiar el turno");
        }
      }
    }
  }
  /**
   * valida el cambio del turno para siempre estar en un rango aceptable
   */
  public void validarCambioTurno() {
    Servidor.turno++;
    if(Servidor.turno == 3) {
      Servidor.turno = 0;
    }
  }
  
  public void run() {
    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new ObjectOutputStream(socket.getOutputStream());
      String nombre = in.readLine();
      while (true) {      
        if (nombre == null) {
          return;
        }
        synchronized (Servidor.nombres) {
          if (!Servidor.nombres.contains(nombre)) {
            Servidor.nombres.add(nombre);
            Servidor.jugadores.add(out);
            out.writeObject("Aceptado");
            break;
          }
        }
      }
     out.writeObject(Servidor.nombres.get(Servidor.turno++));
     if(Servidor.turno == 3) {
       Servidor.turno = -1;
       cambiarTurno();
       String activar = "ActivarPartida";
       for(ObjectOutputStream jugador :Servidor.jugadores) {
         jugador.writeObject(activar);
       }
       for(int i = 0; i<6 ; i++) {
         analizarInput("1");
         cambiarTurno();
       }
     }
     while (Servidor.sePuedeJugar) {
       String input = in.readLine();
       analizarInput(input);
       cambiarTurno();
     }
     }catch (IOException e) {
        System.out.println(e);
        if(!Servidor.iniciado) {
          Servidor.turno--;
          Servidor.nombres.remove(Servidor.turno);
          Servidor.jugadores.remove(Servidor.turno);
        }else {
          Servidor.nombres.remove(Servidor.turno);
          Servidor.jugadores.remove(Servidor.turno);
          for(ObjectOutputStream jugador: Servidor.jugadores) {
            try {
              jugador.writeObject(new String("Desconexion"));
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          }
        }
     }finally{
        try {
          socket.close();
        } catch (IOException e) {
      }
    }
  }
}