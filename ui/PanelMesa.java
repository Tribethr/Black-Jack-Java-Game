/**
 * La clase PanelMesa crea un panel en el cual se dibuja la mesa.
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Pérez) 
 * @version (1.0 | 12/10/18)
 */

package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PanelMesa extends JPanel {
  private Image mesa;
  
  public PanelMesa(){
      initComponents();
      repaint();
  }
  
  private void initComponents() {
    setVisible(true);
    pintarMesa();
    repaint();
  }
  
  /*
   * Carga la imagen de la mesa y la redimensiona
   * @param N/A
   * @return N/A
   */
  private void pintarMesa() {
    try {
      mesa = ImageIO.read(new File("src/imagenes/Mesa.jpg")).getScaledInstance(1600, 1300, Image.SCALE_SMOOTH);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void paint(Graphics g) {
    g.drawImage(mesa, -160, -315, this);
  }
}
