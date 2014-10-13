package Proyecto2;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class Bombardero extends Enemigo {
	
	
	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/bombardero.png"));

	private static final int defaultWidth = 192;
	private static final int defaultHeight = 168;
	private static final int defaultVel = 10;
	private static final int defaultVida = 20;
	
	
	
	//altura de inicio del segundo patron
	private int defaultY = 250;
	
	//cuanta la cantidad de rebotes en los bordes
	private int cont;
	
	//booleanos que indican los 3 patrones de movimiento distintos del bombardero
	//acercamiento desde abajo
	boolean primero = true;
	
	//movimiento de rebote en forma de 8
	boolean segundo = true;
	
	//salida evasiva hacia la parte de arriba
	boolean tercero = true;
	
	private int delayDisparo = 10;
	private int bombarderoDis = 0;
	
	public Bombardero() {
		super(defaultVida,7,0,0,new ImageIcon(url),defaultWidth,defaultHeight);
		
		cont=0;
		y = minHeight;
		setFrecuenciaDeDisparo(delayDisparo+1,delayDisparo*3);
		
		Random rand = new Random();
		boolean hemisferioInicial = rand.nextBoolean();
		if (hemisferioInicial) {
			posInicialX = 45;
			
		} else {
			posInicialX = maxWidth - 45 - velocidad*20;
			
		}
		x = posInicialX;
	}
	
	public void move() {
		
		if( y > defaultY ) {
			y -= velocidad;
		} else {
			if( x < posInicialX + velocidad * 20 && primero ) {
				x += velocidad;
				y -= velocidad;
				if(x >= posInicialX + velocidad * 20 ) {
					cont++;
				}
			} else {
				primero = false;
				if(x > posInicialX && segundo) {
					x -= velocidad;
					if(x <= posInicialX) {
						cont++;
					}
				} else {
					segundo = false;
					
					if(x < posInicialX + velocidad * 20 && tercero) {
						x += velocidad;
						y += velocidad;
						if(x >= posInicialX + velocidad * 20) {
							cont++;
						}
					} else {
						tercero = false;
					
						if(x > posInicialX) {
							x -= velocidad;
							if(x <= posInicialX) {
								cont++;
							}		
						} else {
							primero = true;
							segundo = true;
							tercero = true;
						}
					}
				}
			}
		
			if (cont >= 8) {
				if(y > (-100  -defaultWidth) ) {
					y -= velocidad;
				}
			}
		}
		verificarColision();	
	}
	
	
	// El enemigo dispara directo al jugador
	public void disparar() {
		
		if(puedeDisparar() && y < 350) {
			Disparo d = apuntarYDisparar();
			d.setPosicion(d.getX(), d.getY() - defaultHeight/2);
			mapa.addDisparoEnemigo(d);
		}
	}
	
	public boolean puedeDisparar() {
		bombarderoDis = (bombarderoDis + 1) % (delayDisparo*3);
		return super.puedeDisparar() || (bombarderoDis%2 == 0 && bombarderoDis <= delayDisparo);
	}

	// El bombardero siempre será espcial; es decir, devolverá un PowerUp cuando sea destruido 
	public boolean isEspecial() {
		return true;
	}
	
}
