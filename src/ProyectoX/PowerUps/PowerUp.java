package ProyectoX.PowerUps;

import java.awt.Image;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Jugador.Jugador;

/**
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public abstract class PowerUp {
	
	protected int y;
	protected int x;
	private Image image;
	private int velocidad = 1;
	private int width = 20;
	private int height = 20;
	private boolean visible = true;
	protected int puntaje = 50;
	protected String sound = "/ProyectoX/sounds/power.mp3";
	
	/**
	 * constructor de la clase PowerUP
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param ii ImageIcon que contiene la imagen del powerUP
	 */
	
	public PowerUp(int x, int y, ImageIcon ii){
		image = ii.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
		this.y = y;
		this.x = x;
	}
	
	/**
	 * modifica la coordenada y haciendo que la imagen se mueva hacia abajo
	 */
	
	public void move(){
		y = y + velocidad;
	}
	
	/**
	 * verifica la colision de la instancia con el Jugador
	 * @param jugador instancia actual de Jugador
	 * @return true si el PowerUP colisiono con el Jugador
	 */
	
	public boolean colision(Jugador jugador){
		boolean A,B,C,D,E,F,G,H, fColision, toReturn; 
		
		A = x >= jugador.getX();
		B = x <= (jugador.getX() + jugador.getWidth());
		C = y >= jugador.getY();
		D = y <= (jugador.getY() + jugador.getHeight());
		E = (x + width) >= jugador.getX();
		F = (x + width) <= (jugador.getX() + jugador.getWidth());
		G = (y + height) >= jugador.getY();
		H = (y + height) <= (jugador.getY() + jugador.getHeight());
		
		// funcion de colicion que verifica si alguno de los 4 puntos del borde del objeto disparo intersectan con area del objeto pasado por parametro
		fColision = (A && B || E && F) && (C && D || G && H);
		if(jugador.getVisible() && fColision){
			efecto(jugador);
			jugador.setPuntaje(puntaje);
			toReturn = true;
		}
		else if(y > 650){
			toReturn = true;
		}
		else{
			toReturn = false;
		}
		
		return toReturn;
		
	}
	
	/**
	 * funcion que genera un efecto sobre el Jugador
	 * @param jugador instancia actual de Jugador
	 */
	protected abstract void efecto(Jugador jugador);
	
	/**
	 * setea la visibilidad en false
	 */
	public void setVisible(){
		visible = false;
	}
	
	/**
	 * @return true si el PowerUP esta visible, false en caso contrario
	 */
	
	public boolean isVisible(){
		return visible;
	}
	
	/**
	 * @return instancia de Image asociada al PowerUP
	 */
	
	public Image getImage(){
		return image;
	}
	
	/**
	 * @return coordenada x
	 */
	
	public int getX(){
		return x;
	}
	

	/**
	 * @return coordenada y
	 */
	
	public int getY(){
		return y;
	}
	
}

