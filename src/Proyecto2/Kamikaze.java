package Proyecto2;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class Kamikaze extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/kamikaze.png"));

	private static final int defaultWidth = 40;
	private static final int defaultHeight = 32;
	private static final int defaultVel = 5;
	
	public Kamikaze(){
		super(15,400,200,new ImageIcon(url),defaultWidth,defaultHeight);	
		System.out.println(x+" "+y);
		}
	
	public void move(){
		try {
			MindEnemies.sleep(150);
			double dx=jugador.getX()-x;
			double dy=jugador.getY()-y;
			double mod = Math.sqrt(dy*dy+dx*dx);
			double cos = Math.abs(Math.acos(dx/mod));
			double sin = Math.abs(Math.asin(dx/mod));
			double pi = Math.PI;
			
			//funcion de rotacion de imagen
			if(dx==0){
				if(dy<0){
					setRotacion(0);
				}
				else{
					setRotacion(pi);
				}
			}
			else{
				if(dx>0){
					if (dy==0){
						setRotacion(pi/2);
					}
					else{
						if(dy<0){
							setRotacion(sin);
						}
						else{
							setRotacion(pi/2+cos);
						}
					}
				}
				
				else{
					if (dy==0){
						setRotacion((3/2)*pi);
					}
					else{
						if(dy>0){
							setRotacion(pi+sin);
						}
						else{
							setRotacion(pi/2-cos);
							System.out.println(3*(pi/2));
						}
					}
				}
			}
			
			System.out.println(dx+" "+dy+" "+mod+" "+sin+" "+cos);

			dx+=jugador.getWidth()/2;
			dy+=jugador.getHeight()/2;
			if(dx!=0 || dy!=0){
				dx = dx / mod;
				dy = dy / mod;
			}
			else{
				dy=0.1d;
				dx=0.1d;
			}
			y+=dy*velocidad;
			x+=dx*velocidad;
			
			
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
