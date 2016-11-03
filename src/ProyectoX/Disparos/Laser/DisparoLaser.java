package ProyectoX.Disparos.Laser;

import java.awt.Image;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Frames.Explosion;
import ProyectoX.Naves.Nave;

/**
 * Clase simboliza un laser, tiene 2 estados uno de carga y uno de disparo que atraviesa toda la pantalla
 * el laser sigue en todo momento la posicion de la Nave que lo lanza
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public abstract class DisparoLaser extends DisparoJugador {
	

	protected Image laser;
	//delay entre la carga y el disparo
	protected int minDuracion;
	//delay que indica el tiempo que permanece el disparo en la pantalla
	protected int maxDuracion;
	
	protected int totalDuracion;

	
	//segunda imagen que representa al disparo
	protected ImageIcon second;
	//se le asigna false cuando cambia la imagen
	private boolean control = true;
	
	protected long init ;
	
	
	/**
	 * Constructor de la clase DisparoLaser
	 * @param dy direccion del laser
	 * @param width ancho del laser
	 * @param height altura del laser
	 * @param first primera imagen del laser que simboliza la carga
	 * @param second segunda imagen del laser que simboliza al disparo
	 * @param nave la Nave que efectua el DisparoLaser
	 */
	
	protected boolean primerDisparo = true;
	
	public DisparoLaser(double dy, int width, int height, ImageIcon first, ImageIcon second, Nave nave) {
		super(0, 0, 0, dy, 0,nave);
		this.nave = nave;
		this.height = 13;
		this.width = first.getIconWidth();
		laser = first.getImage();
		
		this.second = second;
		
		init = System.currentTimeMillis();
		setDelays(200, 1500);
		sonido =  "/ProyectoX/sounds/laserChargue.mp3";
		
	}
	
	/**
	 * redefine getImage() de la clase Disparo
	 * @return instancia de Image
	 */

	public Image getImage(){
		return laser;
	}
	
	/**
	 * redefine colision(Nave nave) de la clase Disparo
	 * verifica si el DisparoLaser colisiono con una Nave
	 * ademas genera el segundo estado del DisparoLaser al cumplirse determinado tiempo 
	 */
	
	public synchronized boolean colision(Nave nave){
		boolean  toRet = false;
		
		if(System.currentTimeMillis()  - init > minDuracion){
			//verificarNuevaImagen();
			toRet = super.colision(nave) || colisionLaser(nave);
		}
		
		
		return toRet;
	}
	
	
	private synchronized boolean colisionLaser(Nave nave){
		boolean A, B, C, D, I, F, toRet = false;
		
	
		//laser cubre al enemigo
		A = x < nave.getX() && x + width > nave.getX() + nave.getWidth();
		//laser pasa por el centro del enemigo
		B = x + width < nave.getX() + nave.getWidth() && x > nave.getX();
		//enemigo adelante del jugador (al alcance del laser)
		C = y + height > nave.getY() + nave.getHeight() && y < nave.getY();
		//laser golpea el extremo derecho del enemigo
		D = x > nave.getX() &&  x < nave.getX() + nave.getWidth();
		//laser golpea el extremo derecho del enemigo
		I =  x + width > nave.getX() &&  x + width < nave.getX() + nave.getWidth();
		
		//enemigo en pantalla
		F = nave.getY() + nave.getHeight()/2 > 0;
		
		toRet = (A || B || D || I) && C && F;

		return toRet;
	}
	
	protected void verificarNuevaImagen(){
			if(control){
				control = false;
				laser = second.getImage();
				this.height = second.getIconHeight();
				this.width = second.getIconWidth();
				move();
				sonido =  "/ProyectoX/sounds/laser.mp3";
				getSound();
				
				
			}
		
	}
	
	
	/**
	 * redefine move() de la clase Disparo
	 * provoca que el laser permanezca pegado a la nave que lo efectuo
	 */
	
	public void move(){
		if(System.currentTimeMillis() - init > minDuracion){
			verificarNuevaImagen();
		}
		
		x = nave.getX() + (nave.getWidth()-4)/2 - width/2;
		y = nave.getY() - (int)(dy*height) - (int)(dy-1)*nave.getHeight();
		
		if(System.currentTimeMillis() - init > maxDuracion){
			desarmar();
		}
	}
	
	/**
	 * redefine newExplosion de la clase Disparo
	 * el DisparoLaser no genera una explosion de si mismo 
	 * pero genera explosiones chicas en el lugar donde impacta
	 * @return instancia de Explosion
	 */
	
	public Explosion newExplosion(int altura) {
		return new Explosion(x + width/2, altura - width/2, explosion, 10, 10);
	}
	
	/**
	 * redefine setVisible() de la clase Disparo
	 * al impactar no debe desaparecer por lo que el setVisible es redefinido
	 */
	
	public void setVisible(){
		
	}
	
	/**
	 * es la nueva funcione que hace desaparecer el disparo cuando se cumple su tiempo
	 */
	
	protected void desarmar(){
		super.setVisible();
		
	}
	
	/**
	 * setea los distintos delays del disparo
	 * @param min indica el delay entre carga y disparo
	 * @param max el tiempo que permanece el disparo efectivo
	 */
	
	protected void setDelays(int min, int max){
		maxDuracion = max;
		minDuracion = min;
		totalDuracion = (max/3)*2 + min;
	}
	
	/**
	 * redefine getLaser() de la clase DisparoJugador
	 * retorna el siguiente nivel de la instancia
	 */
	
	public DisparoJugador getLaser(){
		return nextLevel();
	}
	
}
