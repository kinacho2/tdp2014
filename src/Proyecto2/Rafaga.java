package Proyecto2;

import javax.swing.ImageIcon;

public class Rafaga extends Enemigo {

	private static final int defaultWidth = 60;
	private static final int defaultHeight = 48;
	private static final int defaultVel = 5;
	private static final int defaultVida = 1;
	
	
	public Rafaga(){
		super(defaultVida,defaultVel,45,600-defaultWidth,new ImageIcon(),defaultWidth,defaultHeight);	}


	public boolean isEspecial() {
		// TODO Auto-generated method stub
		return false;
	}

	public void disparar() {
		// TODO Auto-generated method stub
		
	}


	public void move() {
		// TODO Auto-generated method stub
		
	}
}