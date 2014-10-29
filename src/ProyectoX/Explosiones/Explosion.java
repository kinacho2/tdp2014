package ProyectoX.Explosiones;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Explosion {
	
	private Image image;
	private int x;
	private int y;
	private boolean visible;
	private int tiempo ;
	private int delay;
	
	public Explosion(int x, int y, ImageIcon icon, int w, int h) {
		
		this.x = x - w/2;
		this.y = y - h/2;
		visible = true;
		tiempo = 0;
		delay = 2500;
		ImageIcon aux = new ImageIcon(icon.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
		this.image = aux.getImage();
	}

	public Image getImage() {
		return image;
	}

	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setVisible() {
		visible = false;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	/*
	como la explosión es un gif tarda un tiempo en desaparecer por eso se atrasa unos milisegundos para luego
	establecer que la explosión no se encuentra visible y la clase encargada de  eliminarlo del arreglo de explosiones
	realice lo que corresponda
	 */
	public void setTime(int time) {
		if(tiempo < delay) {
			tiempo += time;
		}else
			setVisible();
	}

	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay){
		this.delay = delay;
	}
	
}

