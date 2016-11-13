package ProyectoX.Naves.Jugador;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Naves.Nave;

/**
 * Jugador con alta velocidad
 * baja potencia de fuego, baja resistencia y gran velocidad
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Veloz extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/veloz.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/velozDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/velozIzq.gif"));
	
	/**
	 * Constructor de la clase Veloz
	 * setea los atributos propios de su clase
	 */
	
	public Veloz(String nombre){
		super(60,5,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq),nombre);
		vidaEstandar = 60;
	}
	
	/**
	 * Retorna un String con los valores por defecto de la clase
	 * identados con codigo html para que ser seteados en una etiqueta
	 * @return String con valores por defecto
	 */
	public static String getEstadisticas(){
		ImageIcon icon = new ImageIcon(url);
		return ("<html>VIDA: "+30+"<br>VELOCIDAD: "+5+"<br>POTENCIA INICIAL: "+1+"<br>ALTO: "+icon.getIconHeight()+"<br>ANCHO: "+icon.getIconWidth()+"</html>");
	}
	
	/**
	 * redefine reset() de la clase Jugador
	 * Setea los valores por defecto de la clase Veloz
	 * y llama a reset() de la clase Jugador
	 */
	
	public void reset() {
		super.reset();
		vida = defaultVida;
		setNewDisparo( new MultiplicadorLVI(x,y,dx,dy,this));
	}
}
