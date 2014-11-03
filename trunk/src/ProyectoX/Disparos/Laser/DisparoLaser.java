package ProyectoX.Disparos.Laser;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Explosiones.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;

public abstract class DisparoLaser extends Disparo {
	

	protected Image laser;
	private int delay = 0;
	//delay entre la carga y el disparo
	private int minDelay;
	//delay que indica el tiempo que permanece el disparo en la pantalla
	private int maxDelay;
	//delay para que el laser no impacte de manera continua
	private int impacto;
	
	protected Jugador jugador;
	//segunda imagen que representa al disparo
	private ImageIcon second;
	//se le asigna false cuando cambia la imagen
	private boolean control = true;
	
	public DisparoLaser(int width, int height, ImageIcon first, ImageIcon second, Jugador jugador) {
		super(0, 0, 0, 0, 0);
		this.jugador = jugador;
		this.height = 13;
		this.width = first.getIconWidth();
		laser = first.getImage();
		this.second = second;
		
	}

	public Image getImage(){
		return laser;
	}
	
	public synchronized boolean colision(Nave nave){
		boolean A, B, C, D, I, toRet = false;
		A = x > nave.getX();
		B = x + width < nave.getX() + nave.getWidth();
		C = y + height > nave.getY() + nave.getHeight();
		D = x > nave.getX() &&  x < nave.getX() + nave.getWidth();
		I =  x + width < nave.getX() &&  x + width > nave.getX();
		
		if(delay > minDelay && delay % impacto == 0){
			if(control){
				laser = second.getImage();
				this.height = second.getIconHeight();
				this.width = second.getIconWidth();
				move();
				control = false;
			}
			toRet = A && B && C || D && C || I && C;
		}
		
		return toRet;
	}
	
	//el laser permanece pegado al jugador
	
	public void move(){
		x = jugador.getX() + jugador.getWidth()/2 - width/2;
		y = jugador.getY() - height;
		delay++;
		if(delay > maxDelay){
			desarmar();
		}
	}
	
	public Explosion newExplosion(int altura) {
		return new Explosion(x + width/2, altura - width/2, explosion, width, width);
	}
	
	//al impactar no debe desaparecer por lo que el setVisible es redefinido
	
	public void setVisible(){
		delay++;
	}
	
	//desarmar() es la nueva funcione que hace desaparecer el disparo cuando se cumple su tiempo
	
	private void desarmar(){
		super.setVisible();
	}
	
	//setea los distintos delays del disparo
	
	protected void setDelays(int min, int max, int impacto){
		maxDelay = max;
		minDelay = min;
		this.impacto = impacto;
	}
}
