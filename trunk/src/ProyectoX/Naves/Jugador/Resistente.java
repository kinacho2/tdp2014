package ProyectoX.Naves.Jugador;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Naves.Nave;

public class Resistente extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/resistente.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/resistenteDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/resistenteIzq.gif"));
	private static final int defaultVel = 2;
	private static final int defaultVida = 80;
	
	
	public Resistente(){
		super(defaultVida,defaultVel,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
		
		arma = new MultiplicadorLVI(x, y , 0, 1, this);
	}
	
	public static String getEstadisticas(){
		ImageIcon icon = new ImageIcon(url);
		return ("<html>VIDA: "+defaultVida+"<br>VELOCIDAD: "+defaultVel+"<br>POTENCIA INICIAL: "+2+"<br>ALTO: "+icon.getIconHeight()+"<br>ANCHO: "+icon.getIconWidth()+"</html>");
	}
	
	public void reset(){
		super.reset();
		vida = defaultVida;
		setNewDisparo( new MultiplicadorLVI(x, y , 0, 1, this));
	}
	
	
}