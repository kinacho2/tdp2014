package Proyecto2;

import javax.swing.ImageIcon;

public abstract class Enemigo extends Nave{
	
	
	public Enemigo(int vel, int x, int y, ImageIcon ii,int w, int h){
		super(vel, x, y, ii, w, h);
	}
	
	public void move(){
		x -= 1;
	}

}
