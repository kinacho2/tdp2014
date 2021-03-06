package Proyecto2.Naves.Enemigos;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import Proyecto2.Explosiones_Disparos.Disparo;
import Proyecto2.Naves.Nave;

public class Bombardero extends Enemigo {
	
	
	protected static final URL url = (Nave.class.getClassLoader().getResource("Proyecto2/img/Enemigo/bombardero.png"));

	private static final int defaultWidth = 192;
	private static final int defaultHeight = 168;
	private static final int defaultVel = 10;
	private static final int defaultVida = 20;
	
	
	
	//altura de inicio del segundo patron
	private int defaultY = 250;
	
	//cuanta la cantidad de rebotes en los bordes
	private int cont;
	
	//booleanos que indican los 3 patrones de movimiento distintos del bombardero
	//se mueve en diagonal hacia arriba a la derecha
	private boolean primero = true;
	
	//se mueve horizontal hacia la izquierda
	private boolean segundo = true;
	
	//se mueve en diagonal hacia abajo a la derecha
	private boolean tercero = true;
	
	//variables que controlan un patron de disparo
	private int delayDisparo = 10;
	private int bombarderoDis = 0;
	
	public Bombardero(boolean hemisferioInicial) {
		super(defaultVida,7,new ImageIcon(url),defaultWidth,defaultHeight);
		
		cont=0;
		y = minHeight;
		setFrecuenciaDeDisparo(delayDisparo+1,delayDisparo*3);
		
		if (hemisferioInicial) {
			posInicialX = 45;
			
		} else {
			posInicialX = maxWidth - 45 - velocidad*20;
			
		}
		x = posInicialX;
		
		puntaje = 200;
	}
	
	public void move() {
		
		if( y > defaultY ) {
			//se acerca al centro desde abajo
			y -= velocidad;
		} else {
			if( x < posInicialX + velocidad * 20 && primero ) {
				//se mueve en diagonal hacia arriba a la derecha
				x += velocidad;
				y -= velocidad;
				if(x >= posInicialX + velocidad * 20 ) {
					cont++;
				}
			} else {
				//se setea el primer movimiento en false y se mueve hacia la izquierda horizontalmente
				primero = false;
				if(x > posInicialX && segundo) {
					x -= velocidad;
					if(x <= posInicialX) {
						cont++;
					}
				} else {
					//se setea el segundo movimiento en false y se mueve en diagonal hacia la derecha y abajo
					segundo = false;
					if(x < posInicialX + velocidad * 20 && tercero) {
						x += velocidad;
						y += velocidad;
						if(x >= posInicialX + velocidad * 20) {
							cont++;
						}
					} else {
						//se setea el tercer movimiento en false y se mueve hacia la izquierda horizontalmente
						tercero = false;
						if(x > posInicialX) {
							x -= velocidad;
							if(x <= posInicialX) {
								cont++;
							}		
						} else {
							//se setean los tres movimientos en true para su repeticion
							primero = true;
							segundo = true;
							tercero = true;
						}
					}
				}
			}
		
			if (cont >= 8) {
				/*
				cuenta la cantidad de rebotes en los bordes del patron de movimiento
				en el momento que es 8 es decir que realizo el patron 2 veces parte hacia arriba rebotando
				en los bordes verticales
				*/
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

	// El bombardero siempre ser� espcial; es decir, devolver� un PowerUp cuando sea destruido 
	public boolean isEspecial() {
		return true;
	}
	
}
