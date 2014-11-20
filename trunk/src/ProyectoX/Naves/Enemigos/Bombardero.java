package ProyectoX.Naves.Enemigos;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Frames.Explosion;
import ProyectoX.Naves.Nave;

/**
 * Bombardero es un Enemigo que aparece desde abajo describiendo un movimiento especifico
 * dispara rafagas de Disparos hacia el jugador y luego se marcha por la parte superior
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
*/

public class Bombardero extends Enemigo {
	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/bombardero.png"));
	private static final String alarm = "/ProyectoX/sounds/alarm.mp3";
	protected static final URL explode = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/grande.gif"));
	
	/*
	
	*/
	private boolean noSound = true;
	
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
	private int delayDisparo = 50;
	private int bombarderoDis = 0;
	
	/**
	 * Constructor de la clase Bombardero
	 * @param init indica el lugar de la pantalla en el que aparece el Bombardero, si es true aparece a la izquierda, sino a la derecha
	 */
	
	public Bombardero(boolean init) {
		super(200,3,new ImageIcon(url),192,168);
		
		cont=0;
		y = minHeight;
		setFrecuenciaDeDisparo(delayDisparo+1,delayDisparo*3);
		

		if (init) {
			posInicialX = 45;
			
		} else {
			posInicialX = maxWidth - 45 - velocidad*20 - height*2;
			
		}
		x = posInicialX;
		
		puntaje = 200;
	}
	
	/**
	 * redefine move() de la clase Nave
	 * modifica las coordenadas del Bombardero siguiendo un determinado patron
	 * ademas verifica la colision
	 */
	
	public void move() {
		if(puedeMoverse()){
			if (y >= 550 && noSound){
				reproductor.addSound(alarm,false);
				noSound = false;
			}
			
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
		}
		setMove();
		verificarColision();	
	}
	
	
	/**
	 * agrega al Mapa un Disparo con direccion hacia el Jugador
	 * si puede disparar y el Bombardero se encuentra a cierta altura
	 * ademas agrega el sonido correspondiente a la accion de disparar de la instancia
	 */

	public void disparar() {
		
		if(puedeDisparar() && y < 350) {
			Disparo d = apuntarYDisparar();
			d.setReproductor(reproductor);
			addSonido();
			d.setPosicion(d.getX(), d.getY() - defaultHeight/2);
			mapa.addDisparoEnemigo(d);
		}
	}
	
	/**
	 * redefine puedeDisparar() de la clase Nave
	 * @return true si el contador circlico se encuentra en determinadas posiciones, esto produce una rafaga de Disparos
	 */
	
	public boolean puedeDisparar() {
		bombarderoDis = (bombarderoDis + 1) % (delayDisparo*3);
		return super.puedeDisparar() || (bombarderoDis % 8 == 0 && bombarderoDis <= delayDisparo);
	}

	/**
	 * redefine isEspecial() de la clase Enemigo
	 * El bombardero siempre será espcial, es decir, devolverá un PowerUp cuando sea destruido 
	 */
	public boolean isEspecial() {
		return true;
	}
	
public Explosion getExplosion(){
		
		addSonidoExplosion();
		Explosion aux = new Explosion(x + width/2, y + height/2, new ImageIcon(explode), width, height);
		aux.setDelay(3000);
		return aux;
	}
}
