/**
 * La clase UiJuego despliea en pantalla la interfaz del juego.
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Pérez) 
 * @version (1.0 | 08/10/18)
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import servidor.Jugador;
import util.JParser;

@SuppressWarnings("serial")
public class UiJuego extends UiFrame{
  private JPanel panel;
  private ArrayList<Image> cartasJugador;
  private ArrayList<Image> cartasJugadorIzquierdo;
  private ArrayList<Image> cartasJugadorDerecho;
  private JLabel turnoActual;
  private JButton botonPedir;
  private JButton botonPasar;
  private Jugador jugador;
  private ImageManager imagenes;
  
  public UiJuego(Jugador jugador) {
    super("¡BlackJack!", 300, 200, 1330, 720);
    this.jugador = jugador;
    cartasJugador = new ArrayList<Image>();
    cartasJugadorIzquierdo = new ArrayList<Image>();
    cartasJugadorDerecho = new ArrayList<Image>();
    imagenes = new ImageManager();
    JParser parser = new JParser();
    parser.parseCartas("cartas.txt", imagenes);
    initComponents();
  }
  
  /**
   * Inicia y crea los componentes de la interfáz gráfica.
   * 
   * @params N/A
   * @return N/A
   */
  private void initComponents() {
    panel = new PanelMesa();
    panel.setBounds(0,0,1330,720);  
    add(panel);
    setBackground(Color.decode("#38291d"));
    turnoActual = crearLabel("Turno de: ", 550, 0, 400, 30, 25);
    turnoActual.setForeground(Color.WHITE);
    botonPedir = crearBoton("Pedir", 1100, 550, 200, 100, 25);
    botonPedir.setBackground(Color.decode("#309b4d"));
    botonPedir.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        pedirCarta();
      }
    });
    botonPasar = crearBoton("Plantarse", 20, 550, 200, 100, 25);
    botonPasar.setBackground(Color.decode("#dd2323"));
    botonPasar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        pasarTurno();
      }
    });
    repaint();
  }
  
  public void setJugadorActual(String nombre) {
    turnoActual.setText("Turno de: "+nombre);
  }
  
  public void agregarCartaJugador(int indice) {
    cartasJugador.add(imagenes.getImagenes().get(indice));
  }
  
  public void agregarCartaJugadorIzquierdo(int indice) {
    cartasJugadorIzquierdo.add(imagenes.getImagenes().get(indice));
  }
  
  public void agregarCartaJugadorDerecho(int indice) {
    cartasJugadorDerecho.add(imagenes.getImagenes().get(indice));
  }
  
  public void pedirCarta() {
    jugador.pedir();
  }
  
  public void pasarTurno() {
    jugador.plantarse();
  }
  
  public void paint(Graphics g) {
    paintComponents(g);
    pintarCartasJugador(g);
    pintarCartasJugadorIzquierdo(g);
    pintarCartasJugadorDerecho(g);
  }
  
  /**
   * Pinta las cartas del jugador actual.
   * @param Graphics g
   * @return N/A
   */
  private void pintarCartasJugador(Graphics g) {
    int factorMovimiento = 12;
    for(int i = 0; i<cartasJugador.size(); i++) {
      g.drawImage(cartasJugador.get(i), 631+i*factorMovimiento, 495, this);
    }    
  }
  /**
   * Pinta las cartas del jugador Izquierdo.
   * @param Graphics g
   * @return N/A
   */
  public void pintarCartasJugadorIzquierdo(Graphics g){
    int factorMovimientoX = 17;
    int factorMovimientoY = -1;
    Graphics2D jugadorIzquierdo=(Graphics2D)g;
    jugadorIzquierdo.translate(357, 50); 
    jugadorIzquierdo.rotate(1.11);  
    for(int i = 0; i<cartasJugadorIzquierdo.size(); i++) {
      jugadorIzquierdo.drawImage(cartasJugadorIzquierdo.get(i), 200+i*factorMovimientoX, 200+i*factorMovimientoY, this);
    }
  }
  /**
   * Pinta las cartas del jugador Derecho.
   * @param Graphics g
   * @return N/A
   */
  public void pintarCartasJugadorDerecho(Graphics g){
    int factorMovimientoX = -15;
    int factorMovimientoY = 1;
    Graphics2D jugadorDerecho=(Graphics2D)g;
    jugadorDerecho.translate(674, -862); 
    jugadorDerecho.rotate(0.92);  
    for(int i = 0; i<cartasJugadorDerecho.size(); i++) {
      jugadorDerecho.drawImage(cartasJugadorDerecho.get(i), 200+i*factorMovimientoX, 200+i*factorMovimientoY, this);
    }
  }
  
  public static void main(String [] args) {
    UiJuego a = new UiJuego(null);
  }
}

