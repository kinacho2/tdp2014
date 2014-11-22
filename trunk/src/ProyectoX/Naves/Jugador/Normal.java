package ProyectoX.Naves.Jugador;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

/**
 * Jugador con atributos equilibrados
 * baja potencia de fuego, mediana resistencia y velocidad
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Normal extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/normal.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/normalDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/normalIzq.gif"));

	/**
	 * Constructor de la clase Nornal
	 * setea los atributos propios de su clase
	 */
	public Normal(String nombre){
		super(50,3,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq),nombre);
		
		
	}
	
	/**
	 * Retorna un String con los valores por defecto de la clase
	 * identados con codigo html para que ser seteados en una etiqueta
	 * @return String con valores por defecto
	 */
	
	public static String getEstadisticas(){
		ImageIcon icon = new ImageIcon(url);
		
		return ("<html>VIDA: "+50+"<br>VELOCIDAD: "+3+"<br>POTENCIA INICIAL: "+1+"<br>ALTO: "+icon.getIconHeight()+"<br>ANCHO: "+icon.getIconWidth()+"</html>");
	}

	/**
	 * redefine reset() de la clase Jugador
	 * Setea los valores por defecto de la clase Normal
	 * y llama a reset() de la clase Jugador
	 */

	public void reset() {
		super.reset();
		vida = defaultVida;
		setNewDisparo(new DisparoJugador(x + width/2 , y, 0, 1, velocidadMisil,this));
	}
	
}

