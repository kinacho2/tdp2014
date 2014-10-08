package Proyecto2;

import javax.swing.ImageIcon;

public abstract class Enemigo extends Nave{
	
	public Enemigo(ImageIcon ii,int w, int h){
		super(ii,w,h);
	}
	
	public void move(){
		x -= 1;
	}

}
