package ProyectoX.Naves.Enemigos.Torretas;

import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Enemigo;

/**
 * Esta clase es una instancia de Enemigo que va montada sobre un Jefe, no se mueve y dispara hacia el Jugador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public abstract class Torreta extends Enemigo {
	
	/**
	 * Constructor de la clase Torreta
	 * @param vida cantidad de vida de la Torreta
	 * @param ii ImageIcon que contiene la Image de la Torreta
	 * @param w ancho de la Torreta
	 * @param h alto de la Torreta
	 */
	public Torreta(int vida, ImageIcon ii,int xx, int yy,int w, int h) {
		super(vida, 0, ii, w, h);
		x = xx;
		y = yy;
		velocidadMisil = -6;
		puntaje = 10;
		Random rn = new Random();
		setFrecuenciaDeDisparo(rn.nextInt(50) , 50);
	}
	
	/**
	 * define la operacion disparar() de la clase Nave
	 * funcion que efectua la accion de disparar,
	 * apunta hacia el jugador y dispara cuando puede, 
	 * la Torreta dispara solo cuando esta en pantalla
	 */

	public void disparar() {
		if(puedeDisparar() && y < 600 && y > -width) {
			Disparo d = apuntarYDisparar();
			d.setPosicion((int)(d.getX() + (width/2)*Math.sin(rotacion)), (int)(d.getY() -height/2 - (height/2)*Math.cos(rotacion)));
			mapa.addDisparoEnemigo(d);
			reproductor.addSound(sonido,false);
		}
	}
	
	/**
	 * definde la operacion isEspecial de la clase Enemigo
	 * las instancias de torreta no son especiales
	 * @return false
	 */

	public boolean isEspecial() {
		return false;
	}

	/**
	 * define la operacion move() de la clase Nave
	 * las torretas no se mueven
	 * la operacion move() solo modifica dx (diferencial x) y dy (diferencial y)
	 * de modo que la funcion setRotacion() haga su trabajo
	 */
	
	public void move() {
		dx = jugador.getX() - x;
		dy = jugador.getY() - y;
		setRotacion();
	}
	
	/**
	 * para moverse las Torretas necesitan recibir la nueva posicion por parametro
	 * x e y es la variacion que se debe mover la Torreta
	 * @param xx coordenada x
	 * @param yy coordenada y
	 */
	
	public void setPosition(int xx, int yy){
		x += xx;
		y += yy;
		
	}
	
	/**
	 * las Torretas no colisionan con el Jugador
	 * @return false
	 */
	
	public boolean colision(Nave n){
		return false;
	}
	
	/**
	 * redefine la operacion getRotacion() de la clase Enemigo
	 * crea un AffineTransform con un determinado centro y eje de rotacion para rotar la imagen de la Torreta 
	 * @return instancia de AffineTransform
	 */
	
	public AffineTransform getRotacion() 
    {
        return AffineTransform.getRotateInstance(rotacion + Math.PI, getX() + getWidth()/2, getY() + getHeight()/2);
    }
	
	/**
	 * redefine la operacion bomba() de la clase Enemigo
	 * bomba() solo afecta a las torretas que estan en pantalla
	 * y setea su visibilidad en false
	 */
	
	public int bomba(){
		int p = 0;
		if(x < 800 && x + width > 0 && y < 600 && y + height > 0 ){
			setVisible();
			p = puntaje;
		}
		return p;
		
	}
	
	
}
