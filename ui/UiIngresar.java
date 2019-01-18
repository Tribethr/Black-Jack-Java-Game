/**
 * La clase UiIngresar crea una venrana en la cual se ingresa la ip del servidor y el nombre del jugador.
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Pérez) 
 * @version (1.0 | 08/10/18)
 */
package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import servidor.Jugador;

@SuppressWarnings("serial")
public class UiIngresar extends UiFrame implements ActionListener{
  
  private JButton botonIngresar;
  private JTextField ipField;
  private JTextField nombreUsuario;
  private JLabel error;
  private Jugador jugador;
  
  public UiIngresar(Jugador pJugador) {
    super("Ingresar", 600, 400, 600, 400);
    jugador = pJugador;
    initComponents();
  }
  
  
  /**
   * inicia y crea los componentes
   * 
   * @params N/A
   * @return N/A
   */
  public void initComponents() {
    crearLabel("Ingresar",250,0,300,40,25);
    crearLabel("IP", 40, 75, 100, 40, 25);
    crearLabel("Nombre", 20, 175, 150, 40, 25);
    ipField = crearTextField(200, 75, 300, 50, 25);
    nombreUsuario = crearTextField(200, 175, 300, 50, 25);
    botonIngresar = crearBoton("Ingresar", 200, 250, 200, 75, 25);
    botonIngresar.addActionListener(this);
    repaint();
  }
  /**
   * Detecta cuando se pulsó el botón de ingresar
   * 
   * @params ActionEvent el evento que ocurrio
   * @return N/A
   */
  public void actionPerformed(ActionEvent e) {
   jugador.setIpServidor(ipField.getText());
   jugador.setNombre(nombreUsuario.getText());
   try {
     jugador.plantarse();
   }catch(Exception ex) {
     setError();
   }
  }
  /**
   * Crea y muestra el label de error en caso de existir uno.
   * 
   * @params N/A
   * @return N/A
   */
  public void setError() {
    error = crearLabel("Ip no válida", 10, 340, 250, 15, 12);
    error.setForeground(Color.red);
  }
  
}