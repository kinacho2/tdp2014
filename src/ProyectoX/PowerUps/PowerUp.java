package ProyectoX.PowerUps;

import java.awt.Image;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Jugador.Jugador;

public abstract class PowerUp {
	
	protected int y;
	protected int x;
	private Image image;
	private int velocidad = 1;
	private int width = 20;
	private int height = 20;
	private boolean visible = true;
	protected int puntaje = 50;
	protected String sound = "/ProyectoX/sounds/power.mp3";
	
	public PowerUp(int x, int y, ImageIcon ii){
		image = ii.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
		this.y = y;
		this.x = x;
	}
	
	public void move(){
		y = y + velocidad;
	}
	
	
	
	public boolean colision(Jugador jugador){
		boolean A,B,C,D,E,F,G,H, fColision, toReturn; 
		
		A = x >= jugador.getX();
		B = x <= (jugador.getX() + jugador.getWidth());
		C = y >= jugador.getY();
		D = y <= (jugador.getY() + jugador.getHeight());
		E = (x + width) >= jugador.getX();
		F = (x + width) <= (jugador.getX() + jugador.getWidth());
		G = (y + height) >= jugador.getY();
		H = (y + height) <= (jugador.getY() + jugador.getHeight());
		
		// funcion de colicion que verifica si alguno de los 4 puntos del borde del objeto disparo intersectan con area del objeto pasado por parametro
		fColision = (A && B || E && F) && (C && D || G && H);
		if(jugador.getVisible() && fColision){
			efecto(jugador);
			jugador.setPuntaje(puntaje);
			toReturn = true;
		}
		else if(y > 650){
			toReturn = true;
		}
		else{
			toReturn = false;
		}
		
		return toReturn;
		
	}
	
	protected abstract void efecto(Jugador jugador);
	
	public void setVisible(){
		visible = false;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public Image getImage(){
		return image;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}

