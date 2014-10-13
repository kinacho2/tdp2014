package Proyecto2;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Explosion {
	
	private Image image;
	private int x;
	private int y;
	private boolean visible;
	private int tiempo ;
	private int delay;
	
	public Explosion(int x, int y, ImageIcon icon, int w, int h){
		
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
	
	public void setVisible(){
		visible = false;
	}
	
	public boolean getVisible(){
		return visible;
	}
	
	public void setTime(int time){
		if(tiempo < delay){
			tiempo += time;
		}else
			setVisible();
	}

	public int getDelay() {
		return delay;
	}
	
}

