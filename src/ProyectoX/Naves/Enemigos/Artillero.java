package ProyectoX.Naves.Enemigos;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Nave;

/**
 * Artillero es un Enemigo que aparece en la coordenada x del jugador por encima de la pantalla
 * y va moviendose lentamente hacia abajo mientras dispara hacia el jugador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */
public class Artillero extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/artillero.png"));
	protected static final URL urlUp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/artilleroUP.png"));
	private boolean up;
	private boolean primerMovimiento = true;
	
	/**
	 * Constructor de la clase Artillero
	 * @param up indica si la instancia es especial
	 */
	
	public Artillero(boolean up){
		super(50,4,up? new ImageIcon(urlUp): new ImageIcon(url),60,48);
		
		y = -defaultHeight;
		this.up = up;
		puntaje = 50;
		setFrecuenciaDeDisparo(10,40);//5,70
	}

	/**
	 * redefine isEspecial() de la clase Enemigo
	 * @return true si el enemigo es especial, false en caso contrario
	 */
	public boolean isEspecial() {
		return up;
	}
	
	/**
	 * agrega al Mapa un Disparo con direccion hacia el Jugador
	 * ademas agrega el sonido correspondiente a la accion de disparar de la instancia
	 */

	public void disparar() {
		if(puedeDisparar() && y<500){
			Disparo d = apuntarYDisparar();
			d.setReproductor(reproductor);
			addSonido();
			//d.setPosicion(d.getX(), d.getY() - defaultHeight/2);
			mapa.addDisparoEnemigo(d);
		}
	}

	/**
	 * redefine move() de la clase Nave
	 * setea al Artillero a la altura del Jugador y modifica la coordenada y en cada iteracion, de modo que se mueva hacia abajo
	 * ademas verifica la colision
	 */
	public void move() {
		if(puedeMoverse()){
			if(primerMovimiento){
				x = jugador.getX();
				primerMovimiento = false;
			}
			
			y += velocidad;
		}
		setMove();
		verificarColision();
		setRotacion();
	}
}
