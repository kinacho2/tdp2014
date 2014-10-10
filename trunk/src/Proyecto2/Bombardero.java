package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Bombardero extends Enemigo {
	
	private boolean move;
	
	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/bombardero.png"));

	private static final int defaultWidth = 192;
	private static final int defaultHeight = 168;
	private static final int defaultVel = 10;
	private int defaultX;
	private int defaultY;
	
	public Bombardero(){
		super(7,45,400+defaultWidth,new ImageIcon(url),defaultWidth,defaultHeight);
		defaultX =45;
		defaultY = 250;
		move=true;
	}
	
	public void move(){
		try {
		while(y>defaultY){
			y-=velocidad;
			MindEnemies.sleep(150);	
			
		}
			for (int i=0;i<10;i++){
				while(x<defaultX+velocidad*20){
					x+=velocidad;
					y-=velocidad;
					MindEnemies.sleep(150);	
				}
				
				while(x>defaultX){
					x-=velocidad;
					MindEnemies.sleep(150);
				}
				
				while(x<defaultX+velocidad*20){
					x+=velocidad;
					y+=velocidad;
					MindEnemies.sleep(150);	
				}
				while(x>defaultX){
					x-=velocidad;
					MindEnemies.sleep(150);
				}
				
				
			}
			while(y>(-100-defaultWidth)){
				y-=velocidad;
				MindEnemies.sleep(150);	
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
