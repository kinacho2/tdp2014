package ProyectoX.Frames;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Esta clase representa objetos que son parte del ambiente del juego como las nubes
 * contienen una imagen y su posicion en la pantalla
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Objeto {

	private Image imagen;
	private int x;
	private int y;
	private int velocidad;
	private int dx;
	private int dy;
	private boolean visible = true;
	
	/**
	 * 
	 * @param ii imagen del objeto
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 * @param vel velocidad de recorrido
	 */
	public Objeto(ImageIcon ii, int x, int y, int dx, int dy, int vel){
		imagen = ii.getImage();
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		velocidad = vel;
	}
	
	/**
	 * retorna la imagen del objeto
	 * @return instancia de Image
	 */
	
	public Image getImage(){
		return imagen;
	}
	
	/**
	 * retorna la posicion en el eje x
	 * @return coordenada x
	 */
	
	public int getX(){
		return x;
	}
	
	/**
	 * retorna la posicion en el eje y
	 * @return coordenada y
	 */
	
	public int getY(){
		return y;
	}
	
	/**
	 * modifica los valores de x e y dependiendo de sus diferenciales y la velocidad
	 */
	
	public void move(){
		x = x + dx*velocidad;
		y = y + dy*velocidad;
		if(y>600)
			visible = false;
	}
	
	 /**
     * indica si el Objeto esta visible en pantalla
     * @return boolean visible
     */
	
	public boolean getVisible(){
		return visible;
	}
	
	/**
	 * se setea desde afuera la posicion inicial en el eje x
	 * @param x posicion inicial en x
	 */
	
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * crea una nueva instancia de objeto con los valores de la instancia que recibe el mensaje
	 * @return instancia de Objeto
	 */
	
	public Objeto clone(){
		return new Objeto(new ImageIcon(imagen),x,y,dx,dy,velocidad);
	}
}
