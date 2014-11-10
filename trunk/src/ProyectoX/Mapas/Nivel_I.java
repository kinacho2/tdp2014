package ProyectoX.Mapas;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Enemigos.EnemiesBuilder;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Jefes.Jefe;
import ProyectoX.Naves.Enemigos.Jefes.JefeAvion;
import ProyectoX.Naves.Enemigos.Jefes.JefeTanque;
import ProyectoX.PowerUps.PUPBuilder;
import ProyectoX.Sound.Sonido;

public class Nivel_I extends Mapa{
	
	protected static final URL fondo = (Mapa.class.getClassLoader().getResource("ProyectoX/img/Fondos/fondoNivel1.png"));
	protected static final int initY = -8072 + 600;
	protected static final String soundBoss = "/ProyectoX/sounds/music/level1boss.mp3";
	protected static final String sound = "/ProyectoX/sounds/music/level1.mp3";

	
	public Nivel_I(){
		super();
		rn = new Random(7);
		cantEnemies = 10;
		power = new PUPBuilder(7);
		enBuilder = new EnemiesBuilder(5);
		ImageIcon ii = new ImageIcon(fondo);
		imagenFondo = ii.getImage();
		
		y = initY;
		jefe = new JefeTanque(); 
		jefe.addReproductor(reproductor);
		sonido = sound;
		sonidoJefe = soundBoss;
		
		reproductor.addSound(new Sonido(sonido,true));
		
	}

	/*
	public Enemigo nextEnemigo() {
		Enemigo m;
		if(indiceEnemigos < cantEnemies) {	
			m = super.nextEnemigo();    
		} else {
			if(!estaJefe){
				m = new JefeTanque();
				mindEnemies.addBoss((Jefe)m);
				estaJefe = true;
				m.setJugador(jugador);
				m.setMapa(this);
				m = null;
				
			}
			else{
				m = null;
			}
		}
		
		return m;
	}*/
	
	public int getY(){
		if(delay % delayVel  == 0){
			y++;
			if(y  >= 0 ){
				y = initY;
			}
		}
		
		
		delay = (delay + 1) % delayVel;
		return y;
	}
	
}
