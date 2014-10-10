package Proyecto2;

import javax.swing.ImageIcon;

public class Rafaga extends Enemigo {

	private static final int defaultWidth = 60;
	private static final int defaultHeight = 48;
	private static final int defaultVel = 5;
	
	
	public Rafaga(){
		super(5,45,600-defaultWidth,new ImageIcon(),defaultWidth,defaultHeight);	}
}
