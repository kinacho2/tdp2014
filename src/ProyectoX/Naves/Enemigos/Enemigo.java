package ProyectoX.Naves.Enemigos;

import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Frames.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.PowerUps.PowerUp;

/**
 * Clase que representa a los enemigos en pantalla
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
*/

public abstract class Enemigo extends Nave {
	protected static final URL explode = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
	protected static final String sonido = "/ProyectoX/sounds/disparo.mp3";
	private String explodeSound = "/ProyectoX/sounds/explode2.mp3";
	
	//angulo de rotacion en radianes
	protected double rotacion = 0.0;
	
	protected PowerUp power; 
	
	protected int delay = 4;
	private int cont;
	
	
	/**
	 * Constructor de la clase Enemigo
	 * @param vida, cantidad de vida del Enemigo
	 * @param vel la cantidad de pixeles que se mueve por iteracion
	 * @param ii,ImageIcon que contiene la Image del Enemigo
	 * @param w, ancho del Enemigo
	 * @param h, alto del Enemigo
	 */
	public Enemigo(int vida, int vel, ImageIcon ii,int w, int h) {
		super(vida,vel, ii, new ImageIcon(explode), w, h);
		velocidadMisil = -7;
	}
	
	/**
	 * verifica la colision con el Jugador y le resta vida a ambos
	 * tambien verifica la colision con la Defensa del Jugador
	 */
	
	public void verificarColision() {
		if (colision(jugador)) {
			int vd = jugador.getDamageColision();
			jugador.setVida(vida);
			setVida(vd);	
		}
		//verifica la colision con la Defensa del Jugador
		Jugador aux = jugador.getDefensa();
		if(aux != null){
			if (colision(aux)) {
				int vd = aux.getDamageColision();
				aux.setVida(vida);
				setVida(vd);	
			}
		}
		
		// establece que el eneimgo no está en la pantalla, por lo que no está visible
		if(fueraDePantalla()) {
			setVisible();
		}
		
	}
	
	/**
	 * debe ser definida por cada tipo de Enemigo
	 * @return true si el enemigo es especial, false en caso contrario
	 */
	
	public abstract boolean isEspecial();
	
	// devuelve un disparo direccionado al jugador
	protected Disparo apuntarYDisparar() {
		
		double dxAux=jugador.getX() - x + jugador.getWidth()/2;
		double dyAux=jugador.getY() - y + jugador.getHeight();
		double mod = Math.sqrt(dyAux*dyAux+dxAux*dxAux);
		
		if(dxAux != 0 || dyAux != 0) {
			dxAux = dxAux / mod;
			dyAux = dyAux / mod;
		} else {
			dyAux = 0.1d;
			dxAux = 0.1d;
		}
		
		return new Disparo(x + width/2 , y + height, -dxAux, dyAux, velocidadMisil);
	}
	
	/**
	 * devuelve una instancia de la clase Explosion
	 * @return instancia de Explosion
	 */
	
	public Explosion getExplosion() {
		addSonidoExplosion();
		return new Explosion(x + width/2, y + height/2, new ImageIcon(explode), width, height);
	}
	
	/**
	 * crea un AffineTransform con un determinado centro y eje de rotacion para rotar la imagen del Enemigo 
	 * @return instancia de AffineTransform
	 */
	
	public AffineTransform getRotacion(){
		return AffineTransform.getRotateInstance(rotacion, getX() + getWidth()/2, getY() + getHeight()/2);
	}
	
	/**
	 * calcula el angulo de rotacion del Enemigo dependiendo de la ubicacion del Jugador
	 */
	     
    protected void setRotacion() 
    {
    	double mod = Math.sqrt(dx*dx+dy*dy);
		double cos = Math.abs(Math.acos(dx/mod));
		double sin = Math.abs(Math.asin(dx/mod));
		double pi = Math.PI;
		
		//funcion de rotacion de imagen
		if(dx==0)
			if(dy<0)
				rotacion = 0;
			else
				rotacion = (pi);
		else if (dx > 0)
			if (dy == 0)
				rotacion = (pi/2);
			else if(dy < 0)
				rotacion = (sin);
			else
				rotacion = (pi/2+cos);	
		else if (dy == 0)
			rotacion = ((3/2)*pi);
				
		else if(dy>0)
			rotacion = (pi+sin);
		else
			rotacion = (pi/2-cos);
    }

    /**
     * retorna el puntaje asociado a cada instancia de Enemigo
     * @return entero que representa al puntaje
     */

	public int getPuntaje() {
		return puntaje;
	}
	
	/**
	 * le resta al Enemigo la cantidad de vida pasada por parametro
	 * si la vida es menor que 0 le quita la visibilidad, le da puntaje al jugador y, si es especial, agrega un PowerUP nuevo al mapa
	 * @param vd entero 
	 */
	
	public void setVida(int vd) {
		vida -= vd;
		if(vida <= 0 && getVisible()) {
			setVisible();
			jugador.setPuntaje(puntaje);
			//si es especial agrega un powerUp al mapa
			if(isEspecial() )
				mapa.addPower(x + width/2, y + height/2, false);
		}
	}
	
	/**
	 * leindica al enemigo que se lanzo una Bomba
	 * @return el puntaje asociado a cada Enemigo
	 */
	
	public int bomba(){
		setVisible();
		return getPuntaje();
	}
	
	/**
	 * agrega un sonido de Disparo al Reproductor 
	 */
	
	protected void addSonido(){
		reproductor.addSound(sonido,false);
	}
	
	/**
	 * esta funcion se utilizapara relentizar la velocidad de los Enemigos
	 * @return true si el contador circlico es 0, false en caso contrario
	 */
	
	protected boolean puedeMoverse(){
		return cont % delay == 0;
	}
	
	/**
	 * aumenta el contador circlico para crear un delay en el movimiento
	 */
	
	protected void setMove(){
		cont = (cont + 1) % delay;
	}
	
	/**
	 * retorna la ubicacion relativa donde esta el sonido de la Explosion del Enemigo
	 * @return path relativo
	 */
	
	protected String getSonidoExplosion(){
		return explodeSound;
	}
	
	/**
	 * retorna siempre falso dado que un Enemigo nunca es invulnerable
	 * @return false
	 */
	public boolean isInvulnerable(){
		return false;
	}
}
