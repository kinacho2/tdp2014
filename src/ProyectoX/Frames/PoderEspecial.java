package ProyectoX.Frames;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Nave;

public class PoderEspecial extends Explosion{

	private double dy;
	private double dx;
	private Nave nave;
	
	public PoderEspecial(int x, int y, ImageIcon icon, int w, int h, Nave nv) {
		super(x, y, icon, w, h);
		this.nave = nv;
		move();
	}
	
	public void move(){
		x = nave.getX() + nave.getWidth()/2 - width/2;
		y = nave.getY() + nave.getHeight()/2 - height/2;
	}

}
