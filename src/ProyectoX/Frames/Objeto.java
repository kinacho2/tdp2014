package ProyectoX.Frames;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Objeto {

	private Image imagen;
	private int x;
	private int y;
	private int velocidad;
	private int dx;
	private int dy;
	boolean visible = true;
	
	public Objeto(ImageIcon ii, int x, int y, int dx, int dy, int vel){
		imagen = ii.getImage();
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		velocidad = vel;
	}
	
	public Image getImage(){
		return imagen;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void move(){
		x = x + dx*velocidad;
		y = y + dy*velocidad;
		if(y>600)
			visible = false;
	}
	
	public boolean getVisible(){
		return visible;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public Objeto clone(){
		return new Objeto(new ImageIcon(imagen),x,y,dx,dy,velocidad);
	}
}
