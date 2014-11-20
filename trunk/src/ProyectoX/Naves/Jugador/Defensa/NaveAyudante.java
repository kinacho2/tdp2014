package ProyectoX.Naves.Jugador.Defensa;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Naves.Jugador.Jugador;

/**
 * Clase que simboliza una Nave que ayuda al Jugador
 * Tiene un Disparo de tipo MultiplicadorLVI
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class NaveAyudante extends Defensa {
	
	protected static URL url = (Defensa.class.getClassLoader().getResource("ProyectoX/img/PUP/Ayudante/Compa.gif"));
	protected static URL urlDer = (Defensa.class.getClassLoader().getResource("ProyectoX/img/PUP/Ayudante/CompaDer.gif"));
	protected static URL urlIzq = (Defensa.class.getClassLoader().getResource("ProyectoX/img/PUP/Ayudante/CompaIzq.gif"));
	private static final int defaultVida = 80;
	protected static final int defaultWidth = 32;
	protected static final int defaultHeight = 32;
	
	private boolean control = true;
	private boolean control2 = true;
	private int xx; 
	private int yy;
	
	/**
	 * Constructor de la clase NaveAyudante
	 * @param jugador el Jugador actual
	 */
	
	public NaveAyudante(Jugador jugador){
		super(defaultVida, jugador.getVelocidad(), new ImageIcon(url), new ImageIcon(urlDer), new ImageIcon(urlIzq));
		xx = jugador.getWidth()/2 - width/2;
		yy = 50 + jugador.getHeight();
		x = jugador.getX() -50;
		y = jugador.getY() +20;
		height = defaultHeight;
		width = defaultWidth;
		
		setJugador(jugador);
		setMapa(jugador.getMapa());
		
		bombas = 0;
		
		setNewDisparo(new MultiplicadorLVI(x, y, dx, dy, this));
		
		setFrecuenciaDeDisparo(0,7);
	}
	
	/**
	 * redefine setVisible de la clase Nave
	 * setea la visibilidad en falseo y agrega una nueva explosion al mapa
	 */
	public void setVisible(){
		super.setVisible();
		mapa.addExposion(getExplosion());
	}
	
	/**
	 * 
	 */
	
	public void move(){
		x = jugador.getX() -50;
		y = jugador.getY() +20;
	}
	
	/**
	 * redefine disparar() de la clase Jugador
	 * verifica si puede disparar y si el jugador esta en pantalla efectua el disparo
	 */
	
	public void disparar(){
		if(puedeDisparar() && getVisible() && jugador.getVisible()){
			super.disparar();
		}
	}
	
}
