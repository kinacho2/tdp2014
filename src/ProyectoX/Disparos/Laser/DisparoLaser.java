package ProyectoX.Disparos.Laser;

import java.awt.Image;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Frames.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Sound.Reproductor;
import ProyectoX.Sound.Sonido;

public abstract class DisparoLaser extends DisparoJugador {
	

	protected Image laser;
	private int delay = 0;
	//delay entre la carga y el disparo
	protected int minDuracion;
	//delay que indica el tiempo que permanece el disparo en la pantalla
	protected int maxDuracion;
	//delay para que el laser no impacte de manera continua
	private int impacto;
	
	//segunda imagen que representa al disparo
	private ImageIcon second;
	//se le asigna false cuando cambia la imagen
	private boolean control = true;
	
	private long init ;
	
	
	/**
	 * Constructor de la clase DisparoLaser
	 * @param dy direccion del laser
	 * @param width ancho del laser
	 * @param height altura del laser
	 * @param first primera imagen del laser que simboliza la carga
	 * @param second segunda imagen del laser que simboliza al disparo
	 * @param nave la Nave que efectua el DisparoLaser
	 */
	
	public DisparoLaser(double dy, int width, int height, ImageIcon first, ImageIcon second, Nave nave) {
		super(0, 0, 0, dy, 0,nave);
		this.nave = nave;
		this.height = 13;
		this.width = first.getIconWidth();
		laser = first.getImage();
		
		this.second = second;
		
		init = System.currentTimeMillis();
		minDuracion = 200;
		maxDuracion = 1500;
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
		boolean A, B, C, D, I, F, toRet = false;
		
		
		long aux = System.currentTimeMillis();
		if(aux  - init > minDuracion){
			if(control){
				laser = second.getImage();
				this.height = second.getIconHeight();
				this.width = second.getIconWidth();
				sonido =  "/ProyectoX/sounds/laser.mp3";
				getSound();
				move();
				control = false;
			}
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
			
		}
		
		
		return toRet;
	}
	
	/**
	 * redefine move() de la clase Disparo
	 * provoca que el laser permanezca pegado a la nave que lo efectuo
	 */
	
	public void move(){
		x = nave.getX() + nave.getWidth()/2 - width/2;
		y = nave.getY() - (int)(dy*height) - (int)(dy-1)*nave.getHeight();
		delay++;
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
	
	private void desarmar(){
		super.setVisible();
	}
	
	/**
	 * setea los distintos delays del disparo
	 * @param min indica el delay entre carga y disparo
	 * @param max el tiempo que permanece el disparo efectivo
	 * @param impacto es un delay que se usa para disminuir la cantidad de colisiones durante el tiempo que dura el disparo efectivo
	 */
	
	protected void setDelays(int min, int max, int impacto){
		maxDuracion = max;
		minDuracion = min;
		this.impacto = impacto;
	}
	
	public DisparoJugador getLaser(){
		return nextLevel();
	}
	
}
