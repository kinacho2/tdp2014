package ProyectoX.Frames;

import java.awt.Image;
import javax.swing.ImageIcon;


/**
 * Clase que simboliza una explosion
 * contiene una imagen y un delay de espera que por defecto son 1200 milisegundos
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Explosion {
	
	private Image image;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private int delay;
	
	private long init;
	
	/**
	 * Constructor de la clase Explosion
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param icon Imagen de la explosion
	 * @param w ancho 
	 * @param h alto
	 */
	
	public Explosion(int x, int y, ImageIcon icon, int w, int h) {
		
		this.x = x - w/2;
		this.y = y - h/2;
		
		this.width = w;
		this.height = h;

		delay = 1200;
		
		init = System.currentTimeMillis();
		
		ImageIcon aux = new ImageIcon(icon.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
		this.image = aux.getImage();
	}

	/**
	 * retorna la imagen de la explosion
	 * @return instancia de Image
	 */
	
	public Image getImage() {
		return image;
	}
	
	/**
	 * retorna la posicion en el eje y
	 * @return coordenada y
	 */

	public int getY() {
		return y;
	}
	
	/**
	 * retorna la posicion en el eje x
	 * @return coordenada x
	 */
	
	public int getX() {
		return x;
	}
	
	 /**
     * indica si la Explosion esta visible en pantalla
     * @return boolean visible
     */
	
	public boolean getVisible() {
		boolean visible = true;
		long now = System.currentTimeMillis();
		if(now - init > delay)
			visible = false;
		return visible;
	}
	
	/**
	 * @return delay de la explosion
	 */

	public int getDelay() {
		return delay;
	}
	
	/**
	* como la explosión es un gif tarda un tiempo en desaparecer por eso se atrasa unos milisegundos para luego
	* establecer que la explosión no se encuentra visible y la clase encargada de  eliminarlo del arreglo de explosiones
	* realice lo que corresponda
	* @param delay entero que indica el tiempo que permanece en pantalla la explosion
	 */
	
	public void setDelay(int delay){
		this.delay = delay;
	}
	
	public void move(){
		
	}
	
}

