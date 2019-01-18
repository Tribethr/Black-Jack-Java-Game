/**
 * La clase JParser toma un archivo JSON lo lee y extrae su información
 * 
 * @author (Abraham Meza Vega, Tribeth Rivas Pérez) 
 * @version (1.0 | 08/10/18)
 */
package util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import blackjack.Carta;
import ui.ImageManager;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class JParser {
  
  private JSONParser parser = new JSONParser();
  private JSONArray obj;

  
  /**
   * Lee un json con las cartas y las convierte en objetos Carta.
   * 
   * @param String dir, la direción del archivo.
   * @return ArrayList<Carta> la lista con todos los objetos Carta.
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ParseException
   */
  public ArrayList<Carta> parseCartas(String dir, ImageManager manager) {
    ArrayList<Carta> parsed = new ArrayList<Carta>();
    ArrayList<Image> imagenes = new ArrayList<Image>();
    try {
      obj = (JSONArray) parser.parse(new FileReader(dir));
      JSONObject actual = new JSONObject();
      for(Object ob : obj) {
        actual = (JSONObject)ob;
        Carta carta = new Carta(((Long)actual.get("valor")).intValue(),(String)actual.get("nombre"));
        carta.setIndice(parsed.size());
        imagenes.add(ImageIO.read(new File("src/imagenes/"+carta.getNombre()+".jpg")).getScaledInstance(72, 102, Image.SCALE_SMOOTH));
        parsed.add(carta);
      }
    }catch(Exception e) {
      System.out.println("No pude leer el archivo");
    }
      manager.setImagenes(imagenes);
    return parsed;
  }
  
}
