package ProyectoX.Naves.Jugador;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Naves.Nave;

/**
 * Jugador de alta resistencia
 * alta potencia de fuego de fuego, alta resistencia y velocidad baja
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Resistente extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/resistente.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/resistenteDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/resistenteIzq.gif"));
	
	/**
	 * Constructor de la clase Resistente
	 * setea los atributos propios de su clase
	 */
	
	public Resistente(){
		super(80,2,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
		
		arma = new MultiplicadorLVI(x, y , 0, 1, this);
	}
	
	/**
	 * Retorna un String con los valores por defecto de la clase
	 * identados con codigo html para que ser seteados en una etiqueta
	 * @return String con valores por defecto
	 */
	
	public static String getEstadisticas(){
		ImageIcon icon = new ImageIcon(url);
		return ("<html>VIDA: "+80+"<br>VELOCIDAD: "+2+"<br>POTENCIA INICIAL: "+2+"<br>ALTO: "+icon.getIconHeight()+"<br>ANCHO: "+icon.getIconWidth()+"</html>");
	}
	
	/**
	 * redefine reset() de la clase Jugador
	 * Setea los valores por defecto de la clase Resistente
	 * y llama a reset() de la clase Jugador
	 */
	
	public void reset(){
		super.reset();
		vida = defaultVida;
		setNewDisparo( new MultiplicadorLVI(x, y , 0, 1, this));
	}
	
	
}