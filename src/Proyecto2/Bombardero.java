package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Bombardero extends Enemigo {
	
	
	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/bombardero.png"));

	private static final int defaultWidth = 192;
	private static final int defaultHeight = 168;
	private static final int defaultVel = 10;
	private static final int defaultVida = 5;
	private int defaultX;
	private int defaultY;
	private int cont;
	
	boolean primero;
	boolean segundo;
	boolean tercero;
	
	
	
	public Bombardero(){
		super(defaultVida,7,45,150+defaultWidth,new ImageIcon(url),defaultWidth,defaultHeight);
		defaultX =45;
		defaultY = 250;
		cont=0;
		
		primero = true;
		segundo = true;
		tercero = true;
	}
	
	public void move(){
		
		if(y>defaultY){
			y-=velocidad;
		}
		else{
			if(x<defaultX+velocidad*20 && primero){
				x+=velocidad;
				y-=velocidad;
				if(x>=defaultX+velocidad*20){
					cont++;
				}
			}
			else{
				primero = false;
				if(x>defaultX && segundo){
					x-=velocidad;
					if(x<=defaultX){
						cont++;
					}
				}
				else{
					segundo = false;
					
					if(x<defaultX+velocidad*20 && tercero){
						x+=velocidad;
						y+=velocidad;
						if(x>=defaultX+velocidad*20){
							cont++;
						}
						
					}
					
					else{
						tercero = false;
					
						if(x>defaultX){
							x-=velocidad;
							if(x<=defaultX){
								cont++;
							}
							
						}
						else{
							primero = true;
							segundo = true;
							tercero = true;
						}
					}
				}
			}
		
			if (cont>=16){
				if(y>(-100-defaultWidth)){
					y-=velocidad;
				}
			}
		}
		verificarColision();	
	}
}
